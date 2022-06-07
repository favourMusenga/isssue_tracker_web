package com.favourmusenga.issuetracker.role;

import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.shared.exceptions.CustomNotFoundException;

import java.util.List;

public interface IRoleService {
    void saveNewRole(Role role) throws CustomBadRequestException;
    List<Role> getAllRole();
    Role getRole(String name) throws CustomNotFoundException;
}
