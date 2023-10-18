package com.testForLTW.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.testForLTW.entity.Book;
import com.testForLTW.entity.User;
import com.testForLTW.repository.BookRepository;
import com.testForLTW.service.BookService;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionIdListener;

@Controller
public class UserController {
	@Autowired
	BookService bookService;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	HttpSession session;
	@GetMapping("/user_home")
	public String userhome(Model model) throws Exception{
		List<Book> listBooks = bookRepository.findAll();
		model.addAttribute("listBooks", listBooks);
		return "user_home";
	}
	
	@GetMapping("/user_bookdetail/{id}")
	public String user_bookdetail(@PathVariable int id , Model model) {
		Book book = bookRepository.findById(id);
		if(book != null) {
			model.addAttribute("book", book);
			return "user_bookdetail";
		}else {
			return "redirect:/user_home";
		}
	}
	
}
