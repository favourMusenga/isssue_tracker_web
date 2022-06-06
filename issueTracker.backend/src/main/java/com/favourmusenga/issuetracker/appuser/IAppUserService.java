package com.favourmusenga.issuetracker.appuser;

import com.favourmusenga.issuetracker.shared.exceptions.BadRequestException;
import com.favourmusenga.issuetracker.shared.exceptions.NotFoundException;

import java.util.List;

public interface IAppUserService {
    void saveUser(AppUser appUser) throws BadRequestException;
    AppUser getUser(String email) throws NotFoundException;
    List<AppUser> getAllUser();

}
