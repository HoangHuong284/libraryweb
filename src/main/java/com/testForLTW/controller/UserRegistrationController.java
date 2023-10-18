package com.testForLTW.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.testForLTW.entity.User;
import com.testForLTW.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserRegistrationController {
	private UserService userService;
	
	@ModelAttribute("user")
	public User UserRegistration() {
		return new User();
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") User user) {
		if(userService.checkUserByEmail(user.getEmail())) {
			return "redirect:/registration?emailexist";
		}
		user.setRole("user");
		userService.saveUser(user);
		return "redirect:/registration?success";
	}
	
	
}
