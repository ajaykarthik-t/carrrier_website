package com.main.career_site_main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.main.career_site_main.datamodel.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    // Custom query method to find a user by email
    Users findByEmail(String email);
    
    // Custom query method to find a user by username
    Users findByUserName(String userName);
    
    // Finds users by qualification
    List<Users> findByQualification(String qualification);
}
