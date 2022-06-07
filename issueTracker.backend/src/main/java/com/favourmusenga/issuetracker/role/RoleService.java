package com.favourmusenga.issuetracker.role;

import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.shared.exceptions.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService implements IRoleService{
    private final RoleRepository roleRepository;

    @Override
    public void saveNewRole(Role role) throws CustomBadRequestException {
        Boolean roleExist = roleRepository.doesRoleExists(role.getName());
        if (roleExist){
            throw new CustomBadRequestException("Role already exists");
        }
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(String name) throws CustomNotFoundException {
        Role role = roleRepository.findByName(name);
        if (role == null){
            throw new CustomNotFoundException("No role with the name " + name + " exists");
        }
        return role;
    }
}
