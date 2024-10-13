package com.main.career_site_main.services;

import com.main.career_site_main.datamodel.Users;
import com.main.career_site_main.datamodel.Job;
import com.main.career_site_main.datamodel.Application;
import com.main.career_site_main.repo.UsersRepo;
import com.main.career_site_main.repo.JobRepo;
import com.main.career_site_main.repo.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userImplementation implements userService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private ApplicationRepo applicationRepo;

    @Override
    public Users createUser(Users user) {
        return usersRepo.save(user);
    }

    @Override
    public Users getUserById(Integer userId) {
        return usersRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Users getUserByEmail(String email) {
        return usersRepo.findByEmail(email);
    }

    @Override
    public Users updateUser(Integer userId,Users user) {
        return usersRepo.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        usersRepo.deleteById(userId);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    @Override
    public List<Application> getApplicationsByUserId(Integer userId) {
        return applicationRepo.findByUserUserId(userId);
    }

    @Override
    public void addJobToFavorites(Integer userId, Integer jobId) {
        Users user = getUserById(userId);
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        user.getFavoriteJobs().add(job);
        usersRepo.save(user);
    }

    @Override
    public void removeJobFromFavorites(Integer userId, Integer jobId) {
        Users user = getUserById(userId);
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        user.getFavoriteJobs().remove(job);
        usersRepo.save(user);
    }

    @Override
    public List<Job> getFavoriteJobs(Integer userId) {
        Users user = getUserById(userId);
        return List.copyOf(user.getFavoriteJobs());
    }

	@Override
	public Application applyForJob(Integer userId, Integer jobId, Application application) {
		Users user = usersRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Set the user and job for the application
        application.setUser(user);
        application.setJob(job);

        // Save the application
        return applicationRepo.save(application);
	}
}

