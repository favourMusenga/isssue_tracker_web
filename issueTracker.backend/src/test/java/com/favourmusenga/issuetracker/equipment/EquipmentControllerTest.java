package com.favourmusenga.issuetracker.equipment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.favourmusenga.issuetracker.location.Location;
import com.favourmusenga.issuetracker.location.LocationService;
import org.junit.jupiter.api.DisplayName;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EquipmentController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class EquipmentControllerTest {

    @MockBean
    private EquipmentService equipmentService;

    @MockBean
    private LocationService locationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("get All Equipment")
    void shouldGetAllEquipment() throws Exception {
        Location location1 = new Location(1L,"zz3/907 chachacha lunda road",3, "kitwe");
        Location location2 = new Location(2L,"plot number 9 kafue  road",3, "lusaka");
        Location location3 = new Location(3L,"plot number 20 palm  road",1, "lusaka");

        Equipment equipment1 = new Equipment(1L,"Transformer","Amplify electricity",location1);
        Equipment equipment2 = new Equipment(2L,"electrical wire",null ,location2);
        Equipment equipment3 = new Equipment(3L,"electricity meter",null,location3);

        List<Equipment> mockEquipments = new ArrayList<>(Arrays.asList(equipment1,equipment2,equipment3));

        when(equipmentService.getAllEquipment()).thenReturn(mockEquipments);

        mockMvc.perform(get("/api/equipment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[1].name", is(equipment2.getName())))
                .andExpect(jsonPath("$.data",hasSize(3)));
    }

    @Test
    @DisplayName("add New Equipment")
    void ShouldAddNewEquipment() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        EquipmentRequest requestData = new EquipmentRequest("Transformer",null,1L);

        String requestDataJson = objectMapper.writeValueAsString(requestData);

        mockMvc.perform(post("/api/equipment").contentType(MediaType.APPLICATION_JSON).content(requestDataJson))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("not able to add New Equipment")
    void ShouldNotAddNewEquipment() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        EquipmentRequest requestData = new EquipmentRequest(null,null,1L);

        String requestDataJson = objectMapper.writeValueAsString(requestData);

        mockMvc.perform(post("/api/equipment").contentType(MediaType.APPLICATION_JSON).content(requestDataJson))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}