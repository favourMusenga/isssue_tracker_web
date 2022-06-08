package com.favourmusenga.issuetracker.role;


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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RoleController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class RoleControllerTest {

    @MockBean
    private RoleService roleService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Get All role")
    void shouldGetAllRole() throws Exception {
        Role role1 = new Role(1L,"Inspector");
        Role role2 = new Role(2L,"Supervisor");

        List<Role> roles = new ArrayList<>(Arrays.asList(role1, role2));

        when(roleService.getAllRole()).thenReturn(roles);

        mockMvc.perform(get("/api/role"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].name", is("Inspector")));
    }

    @Test
    @DisplayName("add new role")
    void shouldBeToAddNewRole() throws Exception {
        Role role1 = new Role("Inspector");

        ObjectMapper objectMapper = new ObjectMapper();
        String mockNewRoleJson = objectMapper.writeValueAsString(role1);

        mockMvc.perform(post("/api/role").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockNewRoleJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}