package com.main.career_site_main.services;

import com.main.career_site_main.datamodel.*;
import com.main.career_site_main.repo.*;
//import com.dhyan.career_site.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminImplementation implements adminService {

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private ApplicationRepo applicationRepo;

    //to view all job postings
    @Override
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }
    

    //to view applicants for a particular job
    @Override
    public List<Application> getApplicantsByJobId(Integer jobId) {
        return applicationRepo.findByJobJobId(jobId);
    }

    //to change application status
    @Override
    public Application updateApplicationStatus(Integer applicationId, String statusName) {
        Application application = applicationRepo.findById(applicationId)
            .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatusName(statusName);
        return applicationRepo.save(application);
    }

    //to delete job posting
    @Override
    public void deleteJob(Integer jobId) {
        jobRepo.deleteById(jobId);
    }

    //to create new job posting
    @Override
    public Job createJob(Job job) {
        return jobRepo.save(job);
    }
}