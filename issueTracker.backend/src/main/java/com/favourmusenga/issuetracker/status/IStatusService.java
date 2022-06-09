package com.favourmusenga.issuetracker.status;

import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;

import java.util.List;

public interface IStatusService {
    void saveNewStatus(Status status) throws CustomBadRequestException;
    List<Status> getAllStatus();
    Status getStatusById(Long statusId);
}
