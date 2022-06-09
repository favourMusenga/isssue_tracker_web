package com.favourmusenga.issuetracker.appuser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.role.RoleService;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = AppUserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class AppUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService appUserService;

    @MockBean
    private RoleService roleService;

    private ObjectMapper objectMapper;

    private Role role1;
    private Role role2;
    private AppUser appUser1;
    private AppUser appUser2;
    private AppUser appUser3;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        role1 = new Role("Inspector");
        role2 = new Role("Supervisor");
        appUser1 = new AppUser(1L,"john@gmail.com","1234", new UserName("john",null, "doe"),role2);
        appUser2 = new AppUser(2L,"jane@gmail.com","1234", new UserName("jane",null, "doe"),role1);
        appUser3 = new AppUser(3L,"peter@gmail.com","1234", new UserName("peter","noah", "doe"),role1);
    }

    @Test
    @DisplayName("get all user")
    void shouldGetAListOfUsers() throws Exception {
        List<AppUser> users = new ArrayList<>(Arrays.asList(appUser1, appUser2, appUser3));
        when(appUserService.getAllUser()).thenReturn(users);
        mockMvc.perform(get("/api/user/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0].email", is("john@gmail.com")));
    }

    @Test
    @DisplayName("add new user")
    void shouldBeAbleToAddNewUser() throws Exception {
        AppUserRequest mockNewUser = new AppUserRequest("mark", "banda", null,"mark@test.com","test123",role1.getName());
        String mockNewUserJson = objectMapper.writeValueAsString(mockNewUser);

        mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockNewUserJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("not add new user")
    void shouldNotBeAbleToAddNewUser() throws Exception {
        AppUserRequest mockNewUser = new AppUserRequest(null, "banda", null,null,"test123",role1.getName());
        String mockNewUserJson = objectMapper.writeValueAsString(mockNewUser);

        mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(mockNewUserJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("get user")
    void shouldGetUserInformationByEmail() throws Exception {
        when(appUserService.getUserByEmail(appUser1.getEmail())).thenReturn(appUser1);
        mockMvc.perform(get("/api/user?email="+appUser1.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data.email",is(appUser1.getEmail())))
                .andExpect(jsonPath("$.data.id",is(appUser1.getId().intValue())));
    }
}