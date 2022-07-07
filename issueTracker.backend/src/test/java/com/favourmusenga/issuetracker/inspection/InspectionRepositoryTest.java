package com.favourmusenga.issuetracker.inspection;

import com.favourmusenga.issuetracker.appuser.AppUser;
import com.favourmusenga.issuetracker.appuser.AppUserRepository;
import com.favourmusenga.issuetracker.appuser.UserName;
import com.favourmusenga.issuetracker.equipment.Equipment;
import com.favourmusenga.issuetracker.equipment.EquipmentRepository;
import com.favourmusenga.issuetracker.location.Location;
import com.favourmusenga.issuetracker.location.LocationRepository;
import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.role.RoleRepository;
import com.favourmusenga.issuetracker.status.Status;
import com.favourmusenga.issuetracker.status.StatusRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InspectionRepositoryTest {

    private AppUser mockAppUser;
    @Autowired
    private InspectionRepository underTest;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private RoleRepository roleRepository;


    @BeforeEach
    void setup(){
        Location location = locationRepository.save(new Location("palm driver chelston",5,"lusaka"));
        Equipment equipment = equipmentRepository.save(new Equipment("Transformer","Amplify electricity",location));

        Status status = statusRepository.save(new Status("work",null));

        Role role = roleRepository.save(new Role(1L, "hfhfh"));

        mockAppUser = appUserRepository.save(new AppUser
                ("moses@gmail.com", "test1234", new UserName("moses",null, "banda"), role));
        Inspection mockInspection = new Inspection(1L,null,"12-08-2020",mockAppUser,status,equipment);

        underTest.save(mockInspection);

    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findInspectionsByAppUser() {
        List<Inspection> expected =  underTest.findInspectionsByAppUser(mockAppUser);

        assertEquals(expected.size(), 1);
    }
}