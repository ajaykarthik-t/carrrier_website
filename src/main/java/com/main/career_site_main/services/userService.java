package com.main.career_site_main.services;

import com.main.career_site_main.datamodel.Users;
import com.main.career_site_main.datamodel.Job;
import com.main.career_site_main.datamodel.Application;

import java.util.List;

public interface userService {

    // Create a new user
    Users createUser(Users user);

    // Get a user by ID
    Users getUserById(Integer userId);

    // Get a user by email
    Users getUserByEmail(String email);

    // Update user details
    Users updateUser(Integer userId,Users user);

    // Delete a user by ID
    void deleteUser(Integer userId);

    // Get all jobs
    List<Job> getAllJobs();

    // Get all applications for a specific user
    List<Application> getApplicationsByUserId(Integer userId);

    // Add a job to the user's favorites
    void addJobToFavorites(Integer userId, Integer jobId);

    // Remove a job from the user's favorites
    void removeJobFromFavorites(Integer userId, Integer jobId);
    
    // Method to apply for a job
    Application applyForJob(Integer userId, Integer jobId, Application application);

    // Get all favorite jobs for a user
    List<Job> getFavoriteJobs(Integer userId);
}
