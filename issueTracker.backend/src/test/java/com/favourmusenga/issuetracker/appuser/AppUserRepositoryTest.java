package com.favourmusenga.issuetracker.appuser;

import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.role.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    @DisplayName("EmailExists")
    void itShouldCheckThatEmailExists() {

        Role role = roleRepository.save(new Role("Inspector"));
        appUserRepository.save(new AppUser("moses@gmail.com", "test1234", new UserName("moses",null, "banda"), role));
        String givenValue = "moses@gmail.com";

        AppUser appUser = appUserRepository.findByEmail(givenValue);
        assertNotNull(appUser);
    }

    @Test
    @DisplayName("Email not Exists")
    void itShouldCheckThatEmailNotExists(){
        String givenValue = "moses@gmail.com";

        AppUser appUser = appUserRepository.findByEmail(givenValue);
        assertNull(appUser);
    }
}