package com.favourmusenga.issuetracker.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Status s WHERE s.name = ?1")
    Boolean doesStatusExists(String name);
}
