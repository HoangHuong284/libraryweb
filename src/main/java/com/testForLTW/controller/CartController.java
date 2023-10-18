package com.testForLTW.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.testForLTW.entity.Book;
import com.testForLTW.entity.Cart;
import com.testForLTW.entity.User;
import com.testForLTW.service.BookService;
import com.testForLTW.service.CartService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	CartService cartService;
	@Autowired
	BookService bookService;
	@Autowired
	HttpSession session;
	
	@GetMapping("/cart")
	public String cartView(Model model) throws Exception{
		User user = (User) session.getAttribute("acc");
		if(user == null) {
			session.setAttribute("NoSignIn", "Vui lòng đăng nhập trước khi thực hiện thao tác!");
			return "redirect:/user_home";
		} else {
			List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
			int Total = 0 ;
			for ( Cart y : listCart) {
				Total = Total + y.getCount()*y.getBook().getPrice();
			}
			
			if( listCart != null) {
				model.addAttribute("Total", Total);
				model.addAttribute("listCart", listCart);
				session.setAttribute("listCart", listCart);
				session.setAttribute("Total",Total);
			}
			
			return "shopping-cart";
		}
		
	}
	
	@GetMapping("/deleteCart/{id}")
	public String getDeleteCart(@PathVariable int id , Model model , HttpServletRequest request) throws Exception{
		String referer = request.getHeader("Referer");
		User user = (User) session.getAttribute("acc");
		if( user == null) {
			return "redirect:" + referer;
		} else {
			System.out.println(id);
			cartService.deleteById(id);
			session.setAttribute("CartDelete", id);
			List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
			session.setAttribute("countCart", listCart.size());
			return "redirect:/cart" ;
		}
	}
	
	@PostMapping("/updateCart")
	public String UpdateCart(HttpServletRequest request , Model model ) throws Exception{
		@SuppressWarnings("unchecked")
		List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
		int i = 0 ;
		for ( Cart o : listCart) {
//			System.out.println("count"+i);
//			String a=(String) model.getAttribute("count" + i);
			String a = request.getParameter("count" + i);
			int count = Integer.parseInt(a);
			System.out.println(count);
			o.setCount(count);
			i++;
		}
		
		for(Cart o : listCart) {
			cartService.saveCart(o);
		}
		return "redirect:/cart";
	}
	
	
	
	
	
	@GetMapping("/addToCart/{id}")
	public String AddToCart(@PathVariable int id , Model model ,HttpServletRequest request) throws Exception{
		String referer = request.getHeader("Referer");
		User user = (User) session.getAttribute("acc");
		if(user == null) {
			session.setAttribute("AddToCartErr", "Vui lòng đăng nhập trước khi thực hiện thao tác");
			return "redirect:" + referer;
		} else {
			List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
			Book book = bookService.findById(id);
			int cart = 0;
			for (Cart y : listCart) {
				if (y.getBook().getId() == id) {
					cart++;
				}
			}
			if( cart > 0) {
				for ( Cart y : listCart) {
					if(y.getBook().getId() == id) {
						y.setCount(y.getCount() + 1);
						cartService.saveCart(y);
					}
				}
			} else {
				Cart newCart = new Cart();
				newCart.setCount(1);
				newCart.setBook(book);
				newCart.setUser(user);
				cartService.saveCart(newCart);				
			}
			listCart = cartService.GetAllCartByUser_id(user.getId());
			session.setAttribute("countCart", listCart.size());
			return "redirect:" + referer;
			
			
		}
	}
 
	@PostMapping("/addToCart")
	public String AddToCartPost(@ModelAttribute("book_id") int book_id , @ModelAttribute("count") String a,
		Model model , HttpServletRequest request) throws Exception{
		int count = Integer.parseInt(a);
		String referer = request.getHeader("Referer");
		User user = (User) session.getAttribute("acc");
		if(user == null) {
			session.setAttribute("AddToCartErr", "Vui lòng đăng nhập trước khi thực hiện thao tác");
			return "redirect:" + referer;
		} else {
			List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
			Book book = bookService.findById(book_id);
			int cart = 0;
			for ( Cart y : listCart) {
				if(y.getBook().getId() == book_id) {
					y.setCount(y.getCount()+ count);
					cartService.saveCart(y);
					cart++;
				}
			}
			
			if ( cart == 0) {
				Cart newCart = new Cart();
				newCart.setCount(count);
				newCart.setBook(book);
				newCart.setUser(user);
				cartService.saveCart(newCart);
			}
			
			listCart = cartService.GetAllCartByUser_id(user.getId());
			session.setAttribute("countCart", listCart.size());
			return "redirect:" + referer;
			
		}
	}
	
}
