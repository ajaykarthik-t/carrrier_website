package com.main.career_site_main.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.main.career_site_main.datamodel.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
	
	// Custom query method to find admin by email
    Admin findByEmail(String email);
    
    // Custom query method to find admin by adminName
    Admin findByAdminName(String adminName);
}
