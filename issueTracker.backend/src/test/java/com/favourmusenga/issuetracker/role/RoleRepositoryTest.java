package com.favourmusenga.issuetracker.role;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository underTestRepo;

    @AfterEach
    void tearDown(){
        underTestRepo.deleteAll();
    }

    @Test
    @DisplayName("find role by it name")
    void shouldFindRoleWitName() {
        String given = "Supervisor";
        Role givenRole = new Role(given);

        underTestRepo.save(givenRole);

        Role expectRole = underTestRepo.findByName(given);

        String expected = expectRole.getName();

        assertEquals(expected,given);
    }

    @Test
    @DisplayName("name doesn't exist")
    void shouldNotFindRoleWithTheGivenName(){
        Role role = underTestRepo.findByName("Admin");

        assertNull(role);
    }

    @Test
    void doesRoleExists() {
        String givenRoleName = "Supervisor";
        Role givenRole = new Role(givenRoleName);

        underTestRepo.save(givenRole);

        Boolean doesRoleNameExist = underTestRepo.doesRoleExists(givenRoleName);

        assertThat(doesRoleNameExist).isTrue();
    }

    @Test
    void doesNotRoleExists() {
        String givenRoleName = "Supervisor";

        Boolean doesRoleNameExist = underTestRepo.doesRoleExists(givenRoleName);

        assertThat(doesRoleNameExist).isFalse();
    }
}