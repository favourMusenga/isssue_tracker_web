package com.favourmusenga.issuetracker.role;

import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.shared.exceptions.CustomNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock private RoleRepository roleRepository;
    private RoleService underTestService;



    @BeforeEach
    void setUp() {
        underTestService = new RoleService(roleRepository);
    }

    @Test
    @DisplayName("adding new role")
    void canAddNewRole() throws CustomBadRequestException {
        // given
        Role mockRole = new Role("admin");

        underTestService.saveNewRole(mockRole);

        ArgumentCaptor<Role> roleArgumentCaptor = ArgumentCaptor.forClass(Role.class);

        verify(roleRepository).save(roleArgumentCaptor.capture());

        Role captureRole = roleArgumentCaptor.getValue();

        assertThat(captureRole).isEqualTo(mockRole);
    }

    @Test
    @DisplayName("not save new role")
    void willNotSaveButThrowAnErrorBecauseRoleExist()  {
        // given
        Role mockRole = new Role("admin");

        given(roleRepository.doesRoleExists(anyString())).willReturn(true);

        assertThatThrownBy(()->underTestService.saveNewRole(mockRole))
                .isInstanceOf(CustomBadRequestException.class)
                .hasMessageContaining("Role already exists");

        verify(roleRepository, never()).save(mockRole);
    }


    @Test
    @DisplayName("getting all users")
    void shouldGetAllTheRoles() {
        underTestService.getAllRole();

        verify(roleRepository).findAll();
    }

    @Test
    @DisplayName("getting a role by name")
    void shouldGetRoleByName() throws CustomNotFoundException {
        // given
        Role givenRole = new Role("admin");

        given(roleRepository.findByName(anyString())).willReturn(givenRole);

        Role expected = underTestService.getRole(givenRole.getName());

        assertThat(expected).isEqualTo(givenRole);
    }

    @Test
    @DisplayName("wil not role by name")
    void willNotGetRoleByNameButThrowAnError() {
        // given
        String givenRoleName = "admin";

        given(roleRepository.findByName(anyString())).willReturn(null);

        assertThatThrownBy(()-> underTestService.getRole(givenRoleName))
                .isInstanceOf(CustomNotFoundException.class)
                .hasMessageContaining("No role with the name " + givenRoleName + " exists");

    }
}