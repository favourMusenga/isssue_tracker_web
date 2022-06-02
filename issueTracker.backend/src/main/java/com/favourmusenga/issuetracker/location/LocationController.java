package com.favourmusenga.issuetracker.location;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("location")
public class LocationController {
    private final LocationService locationService;
}
