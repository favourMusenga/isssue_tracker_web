package com.favourmusenga.issuetracker;

import com.favourmusenga.issuetracker.appuser.AppUser;
import com.favourmusenga.issuetracker.appuser.AppUserService;
import com.favourmusenga.issuetracker.appuser.UserName;
import com.favourmusenga.issuetracker.location.Location;
import com.favourmusenga.issuetracker.location.LocationService;
import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.role.RoleService;
import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.status.Status;
import com.favourmusenga.issuetracker.status.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/init")
public class InitController {
    final private RoleService roleService;

    final private StatusService statusService;
    final private AppUserService appUserService;

    final private LocationService locationService;


    @GetMapping
    public String initApp() throws CustomBadRequestException {
        statusService.saveNewStatus(new Status("working", "machine is working"));
        statusService.saveNewStatus(new Status("not working", "machine is not working"));
        statusService.saveNewStatus(new Status("under servicing", "machine is being worked"));

        Role supervisor = new Role("supervisor");
        Role inspector = new Role("inspector");

        roleService.saveNewRole(supervisor);
        roleService.saveNewRole(inspector);

        appUserService.saveUser(new AppUser("supervisor@test.com", "test1234", new UserName("supervisor", null, "supervisor"), supervisor));
        appUserService.saveUser(new AppUser("inspector@test.com", "test1234", new UserName("inspector", null, "inspector"), inspector));

        locationService.saveNewLocation(new Location("main campus cbu, riverside",5,"kitwe"));
        locationService.saveNewLocation(new Location("main campus unza, great east road",6 ,"location"));
        locationService.saveNewLocation(new Location("Buntungwa, miseshi mindolo",3,"kitwe"));
        locationService.saveNewLocation(new Location("Hillcrest secondary school, mushili way",3,"livingstone"));

        return "added initialized";
    }
}
