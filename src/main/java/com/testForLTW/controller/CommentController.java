package com.testForLTW.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.testForLTW.entity.Book;
import com.testForLTW.entity.Comment;
import com.testForLTW.entity.User;
import com.testForLTW.service.BookService;
import com.testForLTW.service.CommentService;
import com.testForLTW.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {
	@Autowired
	BookService bookService;
	@Autowired
	UserService userService;
	@Autowired
	CommentService commentService;
	@Autowired
	HttpSession session;
	
	
	
	
	
	
}
