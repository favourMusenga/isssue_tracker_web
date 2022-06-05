package com.favourmusenga.issuetracker.status;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/status")
public class StatusController {
    private final StatusService statusService;
}
