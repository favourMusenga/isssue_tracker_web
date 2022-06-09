package com.favourmusenga.issuetracker.equipment;

import com.favourmusenga.issuetracker.location.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    private EquipmentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new EquipmentService(equipmentRepository);
    }

    @Test
    @DisplayName("get All Equipment")
    void shouldGetAllEquipment() {
        underTest.getAllEquipment();

        verify(equipmentRepository).findAll();
    }

    @Test
    @DisplayName("get Equipment Id")
    void getEquipmentId() {
        Location location = new Location(1L,"palm driver chelston",5,"lusaka");
        Equipment equipment = new Equipment(1L,"Transformer","Amplify electricity",location);

        when(equipmentRepository.findById(equipment.getId())).thenReturn(Optional.of(equipment));

        underTest.getEquipmentById(equipment.getId());

        verify(equipmentRepository).findById(equipment.getId());
    }

    @Test
    @DisplayName("save Equipment")
    void saveEquipment() {
        Location location = new Location(1L,"palm driver chelston",5,"lusaka");
        Equipment equipment = new Equipment("Transformer","Amplify electricity",location);

        underTest.saveEquipment(equipment);
        ArgumentCaptor<Equipment> equipmentArgumentCaptor = ArgumentCaptor.forClass(Equipment.class);
        verify(equipmentRepository).save(equipmentArgumentCaptor.capture());

        Equipment equipmentCaptured = equipmentArgumentCaptor.getValue();

        assertThat(equipmentCaptured).isEqualTo(equipment);
    }
}