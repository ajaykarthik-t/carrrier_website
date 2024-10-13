package com.main.career_site_main.services;

import com.main.career_site_main.datamodel.Job;
import com.main.career_site_main.datamodel.Application;

import java.util.List;

public interface adminService {

    //to view all job postings
    List<Job> getAllJobs();

    //to view applicants for a particular job
    List<Application> getApplicantsByJobId(Integer jobId);

    //to change application status
    Application updateApplicationStatus(Integer applicationId, String statusName);

    //to delete job posting
    void deleteJob(Integer jobId);

    //to create a new job posting
    Job createJob(Job job);
}
