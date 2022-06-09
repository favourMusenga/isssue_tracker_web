package com.favourmusenga.issuetracker.appuser;

import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.role.RoleService;
import com.favourmusenga.issuetracker.shared.CustomResponseBody;
import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.shared.exceptions.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class AppUserController {
    private final AppUserService userService;
    private final RoleService roleService;

    @GetMapping("/all")
    ResponseEntity<CustomResponseBody<List<AppUser>>> getAllUser(){
        return  ResponseEntity.ok().body(new CustomResponseBody<>(OK.value(), userService.getAllUser()));
    }

    @PostMapping
    ResponseEntity<?> addNewUser(@Valid @RequestBody AppUserRequest appUserRequest) throws CustomNotFoundException, CustomBadRequestException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
        Role role = roleService.getRole(appUserRequest.getStatus());

        userService.saveUser(new AppUser(appUserRequest.getEmail(), appUserRequest.getPassword(), new UserName(appUserRequest.getFirstName(), appUserRequest.getMiddleName(), appUserRequest.getLastName()),role));

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    ResponseEntity<CustomResponseBody<AppUser>> getUser(@RequestParam(name = "email") String email) throws CustomNotFoundException {
        return ResponseEntity.ok().body(new CustomResponseBody<>(OK.value(),userService.getUserByEmail(email)));
    }
}
