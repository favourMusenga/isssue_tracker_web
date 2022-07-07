package com.favourmusenga.issuetracker.inspection;

import com.favourmusenga.issuetracker.appuser.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InspectionService implements IInspectionService {
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

    @Override
    public List<Inspection> getAllInspectionsByUser(AppUser appUser) {
        return inspectionRepository
                .findInspectionsByAppUser(appUser);
    }

    @Override
    public void updateInspection(Inspection inspection) {

        inspectionRepository.save(inspection);
    }
}
