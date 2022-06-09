package com.favourmusenga.issuetracker.inspection;

import java.util.List;

public interface IInspection {
    Inspection getInspectionById(Long id);
    void saveInspection(Inspection inspection);
    List<Inspection> getAllInspections();
}
