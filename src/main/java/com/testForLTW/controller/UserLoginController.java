package com.testForLTW.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.testForLTW.entity.Cart;
import com.testForLTW.entity.User;
import com.testForLTW.entity.UserDto;
import com.testForLTW.service.CartService;
import com.testForLTW.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class UserLoginController {
	private UserService userService;
	@Autowired
	CartService cartService;
	@ModelAttribute("userdto")
	public UserDto userDto() {
		return new UserDto();
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String Login(@ModelAttribute("userdto") UserDto userdto, Model model , HttpSession session) {
		if(userService.checkUserByEmail(userdto.getEmail()) == false) {
			return"redirect:/login?emailwrong";
		}
		User user = userService.getUserByEmail(userdto.getEmail());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("acc", user);
		
		if(userService.checkPasswordUser(userdto.getEmail(), userdto.getPassword())) {
			if(user.getRole().equalsIgnoreCase("admin")) {
				return "redirect:/admin_home";
			} else {
				List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
				session.setAttribute("countCart", listCart.size());
				return "redirect:/user_home";
			}
			
		}
		return "redirect:/login?passwordwrong";
	}
	
}
