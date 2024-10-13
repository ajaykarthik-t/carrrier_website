package com.main.career_site_main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.main.career_site_main.datamodel.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer> {

    // Find status by status name
    Status findByStatusName(String statusName);

}
