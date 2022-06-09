package com.favourmusenga.issuetracker.equipment;

import java.util.List;

public interface IEquipmentService {
    List<Equipment> getAllEquipment();
    Equipment getEquipmentById(Long id);
    void saveEquipment(Equipment equipment);
}
