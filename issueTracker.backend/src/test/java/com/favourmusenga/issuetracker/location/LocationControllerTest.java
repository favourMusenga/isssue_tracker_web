package com.favourmusenga.issuetracker.location;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LocationController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    @Test
    @DisplayName("Get All location")
    void shouldGetAllLocation() throws Exception {
        Location location1 = new Location(1L,"zz3/907 chachacha lunda road",3, "kitwe");
        Location location2 = new Location(2L,"plot number 9 kafue  road",3, "lusaka");
        Location location3 = new Location(3L,"plot number 20 palm  road",1, "lusaka");

        List<Location> locations = new ArrayList<>(Arrays.asList(location1, location2, location3));

        when(locationService.getAllLocations()).thenReturn(locations);

        mockMvc.perform(get("/api/location"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0].town", is(location1.getTown())));
    }

    @Test
    @DisplayName("add new location")
    void shouldBeToAddNewLocation() throws Exception {
        Location location = new Location("zz3/907 chachacha lunda road",3, "kitwe");

        ObjectMapper objectMapper = new ObjectMapper();
        String mockNewLocationJson = objectMapper.writeValueAsString(location);

        mockMvc.perform(post("/api/location").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockNewLocationJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("not add new location")
    void shouldNoteTAddNewLocation() throws Exception {
        Location role1 = new Location("zz3/907 chachacha lunda road",null, null);

        ObjectMapper objectMapper = new ObjectMapper();
        String mockNewLocationJson = objectMapper.writeValueAsString(role1);

        mockMvc.perform(post("/api/location").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockNewLocationJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}