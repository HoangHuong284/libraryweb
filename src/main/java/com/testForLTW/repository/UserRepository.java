package com.testForLTW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testForLTW.entity.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findById(int id);
	User findByEmail(String email);
	User findByIdAndRole(int id, String role);
	void deleteById(int id);
	
}
