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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inspection")
public class InspectionController  {
    private final InspectionService inspectionService;
    private final EquipmentService equipmentService;
    private final AppUserService appUserService;
    private final StatusService statusService;

    @GetMapping
    ResponseEntity<CustomResponseBody<List<Inspection>>> getAllInspection(@RequestParam(name = "email") String email) throws CustomNotFoundException {
        AppUser appUser = appUserService.getUserByEmail(email);
        List<Inspection> inspections = appUser.getRole().getName().equalsIgnoreCase("supervisor")
                ? inspectionService.getAllInspectionsByUser(appUser)
                : inspectionService.getAllInspections();
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

    @PutMapping
    ResponseEntity<?> updateInspection(@RequestBody Inspection inspection){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/Inspection").toUriString());

        inspectionService.updateInspection(inspection);

        return ResponseEntity.ok().build();
    }
}
