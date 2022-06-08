package com.favourmusenga.issuetracker.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public void saveNewLocation(Location location){
        locationRepository.save(location);
    }

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {

        return locationRepository.findById(id).get();
    }
}
