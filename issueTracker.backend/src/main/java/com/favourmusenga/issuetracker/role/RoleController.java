package com.favourmusenga.issuetracker.role;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/role")
public class RoleController {
    private final RoleService roleService;
}
