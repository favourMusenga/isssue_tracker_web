package com.favourmusenga.issuetracker.inspection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InspectionService {
    private final InspectionRepository inspectionRepository;
}
