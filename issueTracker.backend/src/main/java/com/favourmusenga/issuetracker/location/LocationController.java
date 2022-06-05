package com.favourmusenga.issuetracker.location;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/location")
public class LocationController {
    private final LocationService locationService;
}
