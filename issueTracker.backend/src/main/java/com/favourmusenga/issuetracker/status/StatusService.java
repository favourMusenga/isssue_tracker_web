package com.favourmusenga.issuetracker.status;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatusService {
    private final StatusRepository statusRepository;
}
