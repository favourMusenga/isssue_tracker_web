package com.favourmusenga.issuetracker.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EquipmentService implements IEquipmentService {
    private final EquipmentRepository equipmentRepository;


    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).get();
    }

    @Override
    public void saveEquipment(Equipment equipment) {
        equipmentRepository.save(equipment);
    }
}
