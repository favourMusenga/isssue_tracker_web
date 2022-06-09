package com.favourmusenga.issuetracker.status;

import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.shared.CustomResponseBody;
import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
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
@RequestMapping("/api/status")
public class StatusController {
    private final StatusService statusService;

    @GetMapping
    ResponseEntity<CustomResponseBody<List<Status>>> getAllStatuses(){
        return ResponseEntity.ok().body(new CustomResponseBody<>(HttpStatus.OK.value(), statusService.getAllStatus()));
    }

    @PostMapping
    ResponseEntity<?> addNewRole(@Valid @RequestBody Status status) throws CustomBadRequestException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/status").toUriString());
        statusService.saveNewStatus(status);
        return ResponseEntity.created(uri).build();
    }
}
