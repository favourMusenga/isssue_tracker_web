package com.favourmusenga.issuetracker.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;
}
