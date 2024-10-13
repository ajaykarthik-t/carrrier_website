package com.main.career_site_main.components;

import com.main.career_site_main.datamodel.Job;
import com.main.career_site_main.datamodel.Application;
import com.main.career_site_main.services.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class adminRest {

    @Autowired
    private adminService adminService;

    // To view all job postings
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = adminService.getAllJobs();
        // Log the fetched jobs
        // System.out.println("Jobs fetched from DB: " + jobs);
        return ResponseEntity.ok(jobs);
    }

    // To view applicants for a specific job
    @GetMapping("/jobs/{jobId}/applicants")
    public ResponseEntity<List<Application>> getApplicantsByJobId(@PathVariable Integer jobId) {
        List<Application> applicants = adminService.getApplicantsByJobId(jobId);
        if (applicants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(applicants);
    }

    // To change application status
    @PutMapping("/applications/{applicationId}/status")
    public ResponseEntity<Application> updateApplicationStatus(
            @PathVariable Integer applicationId,
            @RequestParam String statusName) {
        try {
            Application updatedApplication = adminService.updateApplicationStatus(applicationId, statusName);
            return ResponseEntity.ok(updatedApplication);
        } catch (RuntimeException e) {
            // Handle application not found or other errors
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // To delete job posting
    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer jobId) {
        try {
            adminService.deleteJob(jobId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            // Handle job not found or other errors
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // To create new job posting
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job newJob = adminService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(newJob);
    }
}

