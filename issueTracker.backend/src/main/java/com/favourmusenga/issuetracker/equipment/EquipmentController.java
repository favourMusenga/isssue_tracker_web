package com.favourmusenga.issuetracker.equipment;

import com.favourmusenga.issuetracker.location.Location;
import com.favourmusenga.issuetracker.location.LocationService;
import com.favourmusenga.issuetracker.shared.CustomResponseBody;
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
@RequestMapping("/api/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<?> addNewEquipment(@Valid @RequestBody EquipmentRequest equipmentRequest){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role").toUriString());
        Location location = locationService.getLocationById(equipmentRequest.getId());

        Equipment equipment = new Equipment(equipmentRequest.getName(),equipmentRequest.getDescription(),location);
        equipmentService.saveEquipment(equipment);

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<CustomResponseBody<List<Equipment>>> getAllEquipment(){
        return ResponseEntity.ok().body(new CustomResponseBody<>(HttpStatus.OK.value(), equipmentService.getAllEquipment()));
    }
}
