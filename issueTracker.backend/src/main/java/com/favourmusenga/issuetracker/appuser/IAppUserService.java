package com.favourmusenga.issuetracker.appuser;

import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import com.favourmusenga.issuetracker.shared.exceptions.CustomNotFoundException;

import java.util.List;

public interface IAppUserService {
    void saveUser(AppUser appUser) throws CustomBadRequestException;
    AppUser getUserByEmail(String email) throws CustomNotFoundException;
    List<AppUser> getAllUser();

}
