package com.favourmusenga.issuetracker.location;

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
@RequestMapping("/api/location")
public class LocationController {
    private final LocationService locationService;

    @PostMapping
    ResponseEntity<?> addNewLocation(@Valid @RequestBody Location location){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role").toUriString());
        locationService.saveNewLocation(location);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    ResponseEntity<CustomResponseBody<List<Location>>> getAllLocation(){
        return ResponseEntity.ok().body(new CustomResponseBody<>(HttpStatus.OK.value(), locationService.getAllLocations()));
    }
}
