package com.favourmusenga.issuetracker.inspection;

import com.favourmusenga.issuetracker.appuser.AppUser;
import com.favourmusenga.issuetracker.appuser.AppUserService;
import com.favourmusenga.issuetracker.equipment.Equipment;
import com.favourmusenga.issuetracker.equipment.EquipmentService;
import com.favourmusenga.issuetracker.shared.CustomResponseBody;
import com.favourmusenga.issuetracker.shared.exceptions.CustomNotFoundException;
import com.favourmusenga.issuetracker.status.Status;
import com.favourmusenga.issuetracker.status.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/inspection")
public class InspectionController  {
    private final InspectionService inspectionService;
    private final EquipmentService equipmentService;
    private final AppUserService appUserService;
    private final StatusService statusService;

    @GetMapping
    ResponseEntity<CustomResponseBody<List<Inspection>>> getAllInspection(@RequestParam(name = "email") String email) throws CustomNotFoundException {
        log.warn(email);
        AppUser appUser = appUserService.getUserByEmail(email);
        log.warn(appUser.toString());
        log.warn(String.valueOf(appUser.getRole().getName().equalsIgnoreCase("supervisor")));
        List<Inspection> inspections = appUser.getRole().getName().equalsIgnoreCase("supervisor")
                ? inspectionService.getAllInspections()
                : inspectionService.getAllInspectionsByUser(appUser);
        return ResponseEntity.ok().body(new CustomResponseBody<>(HttpStatus.OK.value(), inspections));
    }

    @PostMapping
    ResponseEntity<?> addNewInspection(@Valid @RequestBody InspectionRequest inspectionRequest) throws  CustomNotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/Inspection").toUriString());
        Equipment equipment = equipmentService.getEquipmentById(inspectionRequest.getEquipmentId());
        Status status = statusService.getStatusById(inspectionRequest.getStatusId());
        AppUser appUser = appUserService.getUserByEmail(inspectionRequest.getUserEmail());

        inspectionService.saveInspection(new Inspection(inspectionRequest.getComment(),inspectionRequest.getDate(),appUser,status,equipment));

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateInspection(@PathVariable(name = "id") Long id, @RequestBody InspectionRequest inspectionRequest){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/Inspection").toUriString());

        Inspection inspection = inspectionService.getInspectionById(id);

        inspection.setEquipment(equipmentService.getEquipmentById(inspectionRequest.getEquipmentId()));
        inspection.setStatus(statusService.getStatusById(inspectionRequest.getStatusId()));
        inspection.setComment(inspectionRequest.getComment());

        inspectionService.updateInspection(inspection);

        return ResponseEntity.ok().build();
    }
}
