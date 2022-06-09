package com.favourmusenga.issuetracker.status;

import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StatusService implements IStatusService {
    private final StatusRepository statusRepository;

    @Override
    public void saveNewStatus(Status status) throws CustomBadRequestException {
        Boolean doesStatusExist = statusRepository.doesStatusExists(status.getName());
        if(doesStatusExist)
            throw new CustomBadRequestException("status already exist");
        statusRepository.save(status);
    }

    @Override
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public Status getStatusById(Long statusId) {
        return statusRepository.findById(statusId).get();
    }
}
