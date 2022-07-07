package com.favourmusenga.issuetracker.inspection;

import com.favourmusenga.issuetracker.appuser.AppUser;
import com.favourmusenga.issuetracker.appuser.UserName;
import com.favourmusenga.issuetracker.equipment.Equipment;
import com.favourmusenga.issuetracker.location.Location;
import com.favourmusenga.issuetracker.role.Role;
import com.favourmusenga.issuetracker.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InspectionServiceTest {

    private AppUser mockAppUser;
    @Mock
    private InspectionRepository inspectionRepository;

    private InspectionService underTest;
    private Inspection mockInspection;

    @BeforeEach
    void setUp() {
        underTest = new InspectionService(inspectionRepository);
        Location location = new Location(1L,"palm driver chelston",5,"lusaka");
        Equipment equipment = new Equipment("Transformer","Amplify electricity",location);


        mockAppUser = new AppUser
                ("moses@gmail.com", "test1234", new UserName("moses",null, "banda"), new Role(1L, "hfhfh"));
        mockInspection = new Inspection(1L,null,"12-08-2020",mockAppUser,new Status("work",null),equipment);
    }

    @Test
    void shouldGetInspectionById() {
      when(inspectionRepository.findById(mockInspection.getId())).thenReturn(Optional.ofNullable(mockInspection));

      underTest.getInspectionById(mockInspection.getId());

      verify(inspectionRepository).findById(mockInspection.getId());
    }

    @Test
    void shouldSaveInspection() {
        underTest.saveInspection(mockInspection);
        ArgumentCaptor<Inspection> inspectionArgumentCaptor = ArgumentCaptor.forClass(Inspection.class);
        verify(inspectionRepository).save(inspectionArgumentCaptor.capture());

        Inspection inspectionCaptured = inspectionArgumentCaptor.getValue();

        assertThat(inspectionCaptured).isEqualTo(mockInspection);
    }

    @Test
    void shouldGetAllInspections() {
        underTest.getAllInspections();

        verify(inspectionRepository).findAll();
    }

    @Test
    void  shouldGetAllInspectionsByUser() {

        underTest.getAllInspectionsByUser(mockAppUser);

        ArgumentCaptor<AppUser> appUserArgumentCaptor = ArgumentCaptor.forClass(AppUser.class);

        verify(inspectionRepository).findInspectionsByAppUser(appUserArgumentCaptor.capture());

        AppUser expected = appUserArgumentCaptor.getValue();

        assertThat(expected).isEqualTo(mockAppUser);
    }
}