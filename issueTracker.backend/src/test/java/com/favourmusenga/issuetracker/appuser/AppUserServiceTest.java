package com.favourmusenga.issuetracker.appuser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserServiceTest {
    @Autowired
    AppUserService appUserService;

    @Test
    @DisplayName("list of users")
    void getAllUserTest() {
        List<AppUser> listOfUsers = appUserService.getAllUser();

        assertTrue(listOfUsers.size() >= 0);
    }
}