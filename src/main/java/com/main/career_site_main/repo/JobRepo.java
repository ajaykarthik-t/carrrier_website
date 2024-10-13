package com.main.career_site_main.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.main.career_site_main.datamodel.Job;


@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {

	List<Job> findByJobTitle(String jobTitle);
    
    // Custom query method to find jobs by location
    List<Job> findByLocation(String location);

    // Custom query method to find jobs by requirement
    List<Job> findByRequirement(String requirement);
    
    // Custom query method to find a job by its job ID
    Optional<Job> findById(Integer jobId);
    
    @Query("SELECT j FROM Job j JOIN j.usersWhoFavorited u WHERE u.userId = :userId")
    List<Job> findByUserId(@Param("userId") Integer userId);
}
