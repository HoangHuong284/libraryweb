package com.testForLTW.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testForLTW.entity.User;
import com.testForLTW.repository.UserRepository;
import com.testForLTW.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {		
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByIdAndRole(int id, String role) {
		return userRepository.findByIdAndRole(id, role);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Boolean checkUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if(user == null) return false;
		return true;
	}

	@Override
	public Boolean checkPasswordUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user.getPassword().equals(password)) return true;
		return false;
	}

}
