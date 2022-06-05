package com.favourmusenga.issuetracker.role;

import java.util.List;

public interface IRoleService {
    Role saveNewRole(Role role);
    List<Role> getAllRole();
    Role getRole(String name);
}
