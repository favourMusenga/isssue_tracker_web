package com.favourmusenga.issuetracker.appuser;

import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.role.RoleRepository;
import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.shared.exceptions.CustomNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    private AppUserService underTest;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        underTest = new AppUserService(appUserRepository, passwordEncoder);
    }



    @Test
    @DisplayName("Can add user")
    void canSaveUser() throws CustomBadRequestException {

        //given
        given(roleRepository.save(any())).willReturn(new Role(1L,"Inspector"));
        Role role = roleRepository.save(new Role("Inspector"));

        AppUser mockAppUser = new AppUser("moses@gmail.com", "test1234", new UserName("moses",null, "banda"), role);

        // when
        underTest.saveUser(mockAppUser);

        // then
        ArgumentCaptor<AppUser> appUserArgumentCaptor = ArgumentCaptor.forClass(AppUser.class);

        verify(appUserRepository).save(appUserArgumentCaptor.capture());

        AppUser capturedAppUser = appUserArgumentCaptor.getValue();

        assertThat(capturedAppUser).isEqualTo(mockAppUser);
    }

    @Test
    @DisplayName("Can't add user")
    void willThrowErrorWhenEmailExist()  {

        //given
        given(roleRepository.save(new Role("Inspector"))).willReturn(new Role(1L,"Inspector"));
        Role role = roleRepository.save(new Role("Inspector"));

        AppUser mockAppUser = new AppUser("moses@gmail.com", "test1234", new UserName("moses",null, "banda"), role);

        // when
        // then
        given(appUserRepository.findByEmail(anyString())).willReturn(new AppUser("moses@gmail.com", "test1234", new UserName("moses",null, "banda"), role));

        assertThatThrownBy(() -> underTest.saveUser(mockAppUser))
                .isInstanceOf(CustomBadRequestException.class)
                .hasMessageContaining("Email already exist");

        verify(appUserRepository,never()).save(any());
    }


    @Test
    @DisplayName("get user by email")
    void canGetUser() throws CustomNotFoundException {
        //given
        given(roleRepository.save(new Role("Inspector"))).willReturn(new Role(1L,"Inspector"));
        Role role = roleRepository.save(new Role("Inspector"));
        String mockEmail = "moses@gmail.com";

        AppUser mockUser = new AppUser("moses@gmail.com", "test1234", new UserName("moses",null, "banda"), role);

        given(appUserRepository.findByEmail(anyString())).willReturn(mockUser);

        //when
        AppUser expected = underTest.getUserByEmail(mockEmail);

        assertThat(mockUser).isEqualTo(expected);
    }

    @Test
    @DisplayName("will not user by email")
    void willThrowAnErrorEmailNotExists() {
        //given
        String mockEmail = "moses@gmail.com";

        // when
        // then

        //when
        assertThatThrownBy(()->underTest.getUserByEmail(mockEmail))
                .isInstanceOf(CustomNotFoundException.class)
                .hasMessageContaining("user does not exist");
    }

    @Test
    @DisplayName("get all users")
    void getAllUser() {
        // when
        underTest.getAllUser();
        // then
        verify(appUserRepository).findAll();
    }
}