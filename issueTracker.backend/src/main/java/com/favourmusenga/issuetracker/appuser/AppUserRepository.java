package com.favourmusenga.issuetracker.appuser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public AppUser findByEmail(String email);
}
