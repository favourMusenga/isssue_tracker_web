package com.favourmusenga.issuetracker.inspection;

import com.favourmusenga.issuetracker.appuser.AppUser;

import java.util.List;

public interface IInspectionService {
    Inspection getInspectionById(Long id);
    void saveInspection(Inspection inspection);
    List<Inspection> getAllInspections();
    List<Inspection> getAllInspectionsByUser(AppUser appUser);

    void updateInspection(Inspection inspection);
}
