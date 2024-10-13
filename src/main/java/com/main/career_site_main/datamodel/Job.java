package com.main.career_site_main.datamodel;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(of = { "jobId", "jobTitle", "jobDescription", "requirement", "location", })
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String jobDescription;

    @Column(nullable = false)
    private String requirement;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date uploadedDate;

    @Column(nullable = false)
    private String location;
    
    @ManyToMany (mappedBy = "favoriteJobs")
    private Set<Users> usersWhoFavorited;
    
    @OneToMany(mappedBy = "job")
    private Set<Application> applications;
}


