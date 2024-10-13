package com.main.career_site_main.datamodel;


import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Lob;
import jakarta.persistence.Enumerated;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(of = { "userId", "userName", "email" })
@NoArgsConstructor
public class Users {
	
	public enum Role {
	    ADMIN, USER
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String psw;

    @Column(nullable = false)
    private String qualification;

    @Lob
    @Column(name = "resume", nullable = true)
    private byte[] resume;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favourites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private Set<Job> favoriteJobs;
    
    public Set<Job> getFavoriteJobs() {
        return favoriteJobs;
    }

    public void setFavoriteJobs(Set<Job> favoriteJobs) {
        this.favoriteJobs = favoriteJobs;
    }
}