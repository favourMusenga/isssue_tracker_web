package com.favourmusenga.issuetracker;

import com.favourmusenga.issuetracker.appuser.AppUser;
import com.favourmusenga.issuetracker.appuser.AppUserService;
import com.favourmusenga.issuetracker.appuser.UserName;
import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.role.RoleService;
import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.status.Status;
import com.favourmusenga.issuetracker.status.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/init")
@RequiredArgsConstructor
public class InitController {
    private RoleService roleService;
    private StatusService statusService;
    private AppUserService appUserService;


    @GetMapping
    public void initApp() throws CustomBadRequestException {
        statusService.saveNewStatus(new Status("working", "machine is working"));
        statusService.saveNewStatus(new Status("not working", "machine is not working"));
        statusService.saveNewStatus(new Status("under servicing", "machine is being worked"));

        Role supervisor = new Role("supervisor");
        Role inspector = new Role("inspector");

        roleService.saveNewRole(supervisor);
        roleService.saveNewRole(inspector);

        appUserService.saveUser(new AppUser("supervisor@test", "test1234", new UserName("supervisor", null, "supervisor"), supervisor));
        appUserService.saveUser(new AppUser("inspector@test", "test1234", new UserName("inspector", null, "inspector"), inspector));
    }
}
