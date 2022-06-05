package com.favourmusenga.issuetracker.appuser;

import java.util.List;

public interface IAppUserService {
    AppUser saveUser(AppUser appUser);
    AppUser getUser(String email);
    List<AppUser> getAllUser();
}
