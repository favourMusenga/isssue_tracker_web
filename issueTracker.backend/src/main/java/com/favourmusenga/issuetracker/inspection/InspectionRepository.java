package com.favourmusenga.issuetracker.inspection;

import com.favourmusenga.issuetracker.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    @Query("SELECT i FROM Inspection  i WHERE i.appUser = ?1")
    List<Inspection> findInspectionsByAppUser(AppUser appUser);

}
