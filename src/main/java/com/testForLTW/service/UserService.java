package com.testForLTW.service;

import java.util.List;

import com.testForLTW.entity.User;



public interface UserService {
	
	List<User> getAllUsers();
	
	User saveUser(User user);
	
	User updateUser(User user);
	
	void deleteUserById(int id);
	
	User getUserByEmail(String email);
	
	User findByIdAndRole(int id, String role);
	
	List<User> findAll();
	
	Boolean checkUserByEmail(String email);
	Boolean checkPasswordUser(String email, String password);
}
