package com.favourmusenga.issuetracker.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StatusRepositoryTest {

    @Autowired
    StatusRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    @DisplayName("status name exist")
    void shouldGetStatusTrue() {
        Status status = new Status("working", "check if machine is working");

        underTest.save(status);

        Boolean isExist = underTest.doesStatusExists(status.getName());

        assertTrue(isExist);
    }

    @Test
    @DisplayName("status name not exist")
    void shouldGetStatusFalse() {

        Boolean isExist = underTest.doesStatusExists("Down");

        assertFalse(isExist);
    }
}