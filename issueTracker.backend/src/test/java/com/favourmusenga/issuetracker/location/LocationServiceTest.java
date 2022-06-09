package com.favourmusenga.issuetracker.location;

import com.favourmusenga.issuetracker.role.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    private LocationService underTestService;

    @BeforeEach
    void setUp(){
        underTestService = new LocationService(locationRepository);
    }

    @Test
    @DisplayName("save new location")
    void canSaveNewLocation() {
        // given
        Location mockLocation = new Location(1L,"zz3/907 chachacha lunda road",3, "kitwe");

        underTestService.saveNewLocation(mockLocation);

        ArgumentCaptor<Location> locationArgumentCaptor = ArgumentCaptor.forClass(Location.class);

        verify(locationRepository).save(locationArgumentCaptor.capture());

        Location captureLocation = locationArgumentCaptor.getValue();

        assertThat(captureLocation).isEqualTo(mockLocation);
    }

    @Test
    @DisplayName("get All Locations")
    void ShouldGetAllLocations() {
        underTestService.getAllLocations();

        verify(locationRepository).findAll();
    }

    @Test
    void shouldGetLocationById() {
        Long given = 1L;
        Location location = new Location(given,"zz3/907 chachacha lunda road",3, "kitwe");

        when(locationRepository.findById(given)).thenReturn(Optional.of(location));
        underTestService.getLocationById(given);


        verify(locationRepository).findById(given);


    }
}