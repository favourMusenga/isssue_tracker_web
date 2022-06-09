package com.favourmusenga.issuetracker.inspection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InspectionService implements IInspection {
    private final InspectionRepository inspectionRepository;

    @Override
    public Inspection getInspectionById(Long id) {
        return inspectionRepository.findById(id).get();
    }

    @Override
    public void saveInspection(Inspection inspection) {
        inspectionRepository.save(inspection);
    }

    @Override
    public List<Inspection> getAllInspections() {
        return inspectionRepository.findAll();
    }
}
