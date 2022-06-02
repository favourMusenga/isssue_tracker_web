package com.favourmusenga.issuetracker.inspection;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/Inspection")
public class InspectionController {
    private final InspectionRepository inspectionRepository;
}
