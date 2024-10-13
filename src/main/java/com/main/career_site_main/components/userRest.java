package com.main.career_site_main.components;

import com.main.career_site_main.datamodel.Users;
import com.main.career_site_main.datamodel.Job;
import com.main.career_site_main.datamodel.Application;
import com.main.career_site_main.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class userRest {

    @Autowired
    private userService userService;

    // To view the current user's profile
    @GetMapping("/users/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer userId) {
        Users user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    // To update the current user's profile
    @PutMapping("/users/{userId}")
    public ResponseEntity<Users> updateUser(
            @PathVariable Integer userId,
            @RequestBody Users updatedUser) {
        try {
            Users user = userService.updateUser(userId, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            // Handle user not found or other errors
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // To get all jobs favorited by a user
    @GetMapping("/users/{userId}/favorite-jobs")
    public ResponseEntity<List<Job>> getFavoriteJobs(@PathVariable Integer userId) {
        List<Job> favoriteJobs = userService.getFavoriteJobs(userId);
        if (favoriteJobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favoriteJobs);
    }

    // To apply for a job
    @PostMapping("/users/{userId}/jobs/{jobId}/apply")
    public ResponseEntity<Application> applyForJob(
            @PathVariable Integer userId,
            @PathVariable Integer jobId,
            @RequestBody Application application) {
        try {
            Application newApplication = userService.applyForJob(userId, jobId, application);
            return ResponseEntity.status(HttpStatus.CREATED).body(newApplication);
        } catch (RuntimeException e) {
            // Handle errors such as user or job not found
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // To get all applications for a user
    @GetMapping("/users/{userId}/applications")
    public ResponseEntity<List<Application>> getUserApplications(@PathVariable Integer userId) {
        List<Application> applications = userService.getApplicationsByUserId(userId);
        if (applications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(applications);
    }
}

