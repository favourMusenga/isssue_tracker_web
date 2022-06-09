package com.favourmusenga.issuetracker.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = StatusController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  StatusService statusService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("get All Statuses")
    void shouldGetAllStatuses() throws Exception {
        Status mockStatus1 = new Status(1L,"working", "machine is working");
        Status mockStatus2 = new Status(2L,"not working", "machine is not working");
        Status mockStatus3 = new Status(3L,"under maintenance", "machine is being worked on");

        List<Status> mockStatuses = new ArrayList<>(Arrays.asList(mockStatus1,mockStatus2,mockStatus3));

        when(statusService.getAllStatus()).thenReturn(mockStatuses);

        mockMvc.perform(get("/api/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[1].name", is(mockStatus2.getName())))
                .andExpect(jsonPath("$.data",hasSize(3)));
    }

    @Test
    @DisplayName("add New Role")
    void shouldAddNewRole() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Status mockStatus = new Status("working", "machine is working");

        String mockStatusJson = objectMapper.writeValueAsString(mockStatus);

        mockMvc.perform(post("/api/status").contentType(MediaType.APPLICATION_JSON).content(mockStatusJson))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("won't add  New Role")
    void shouldNotAddNewRole() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Status mockStatus = new Status(null, "machine is working");

        String mockStatusJson = objectMapper.writeValueAsString(mockStatus);

        mockMvc.perform(post("/api/status").contentType(MediaType.APPLICATION_JSON).content(mockStatusJson))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}