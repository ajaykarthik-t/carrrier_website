package com.main.career_site_main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.main.career_site_main.datamodel.Application;
import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer> {

    // Find applications by user ID
    List<Application> findByUserUserId(Integer userId);

    // Find applications by job ID
    List<Application> findByJobJobId(Integer jobId);

    // Find applications by status name
    List<Application> findByStatusName(String statusName);

    // Find applications by user ID and job ID
    Application findByUserUserIdAndJobJobId(Integer userId, Integer jobId);
}
