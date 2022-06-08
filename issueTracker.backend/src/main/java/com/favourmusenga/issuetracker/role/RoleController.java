package com.favourmusenga.issuetracker.role;

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
@RequestMapping(("/api/role"))
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    ResponseEntity<CustomResponseBody<List<Role>>> getAllUser(){
        return ResponseEntity.ok().body(new CustomResponseBody<>(HttpStatus.OK.value(), roleService.getAllRole()));
    }

    @PostMapping
    ResponseEntity<?> addNewRole(@Valid @RequestBody Role role) throws CustomBadRequestException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role").toUriString());
        roleService.saveNewRole(role);
        return ResponseEntity.created(uri).build();
    }


}
