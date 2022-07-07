package com.favourmusenga.issuetracker.inspection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.favourmusenga.issuetracker.appuser.AppUser;
import com.favourmusenga.issuetracker.appuser.AppUserService;
import com.favourmusenga.issuetracker.appuser.UserName;
import com.favourmusenga.issuetracker.equipment.Equipment;
import com.favourmusenga.issuetracker.equipment.EquipmentService;
import com.favourmusenga.issuetracker.location.Location;
import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.status.Status;
import com.favourmusenga.issuetracker.status.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InspectionController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class InspectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private  InspectionService inspectionService;
    @MockBean private  EquipmentService equipmentService;
    @MockBean private  AppUserService appUserService;
    @MockBean private  StatusService statusService;
    private Inspection mockInspection;

    @BeforeEach
    void setUp() {
        Location location = new Location(1L,"palm driver chelston",5,"lusaka");
        Equipment equipment = new Equipment("Transformer","Amplify electricity",location);


        AppUser mockAppUser = new AppUser
                ("moses@gmail.com", "test1234", new UserName("moses",null, "banda"), new Role(1L, "hfhfh"));
        mockInspection = new Inspection(1L,null,"12-08-2020",mockAppUser,new Status("work",null),equipment);
    }

    @Test
    void shouldGetAllInspection() throws Exception {
        List<Inspection> inspections = new ArrayList<>(List.of(mockInspection));
        AppUser mockAppUser = new AppUser
                ("moses@gmail.com", "test1234", new UserName("moses",null, "banda"), new Role(1L, "hfhfh"));
        when(appUserService.getUserByEmail(anyString())).thenReturn(mockAppUser);
        when(inspectionService.getAllInspections()).thenReturn(inspections);
        mockMvc.perform(get("/api/inspection").param("email", "moses@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(mockInspection.getId().intValue())));
    }

    @Test
    void ShouldAddNewInspection() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String mockInspectionJson = objectMapper.writeValueAsString(new InspectionRequest(null,"12-06-1999","test@test.com",1L,1L));
        mockMvc.perform(post("/api/inspection").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockInspectionJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void ShouldNotAddNewInspection() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String mockInspectionJson = objectMapper.writeValueAsString(new InspectionRequest(null,"","",1L,1L));
        mockMvc.perform(post("/api/inspection").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockInspectionJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}