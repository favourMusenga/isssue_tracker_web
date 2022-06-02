package com.favourmusenga.issuetracker.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;
}
