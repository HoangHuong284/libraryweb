package com.testForLTW.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testForLTW.entity.Book;
import com.testForLTW.entity.Cart;
import com.testForLTW.entity.Order;
import com.testForLTW.entity.Order_Item;
import com.testForLTW.entity.User;
import com.testForLTW.repository.CartRepository;
import com.testForLTW.service.BookService;
import com.testForLTW.service.CartService;
import com.testForLTW.service.OrderService;
import com.testForLTW.service.Order_ItemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	CartService cartService;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	Order_ItemService order_ItemService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("checkout")
	public String checkOutView(Model model) {
		User user = (User) session.getAttribute("acc");
		if(user == null) {
			session.setAttribute("AddToCartErr", "Vui lòng đăng nhập trước khi thực hiện thao tác!");
			return "redirect:/user_home";
		}else {
			List<Cart> Cart = cartService.GetAllCartByUser_id(user.getId());
			if(!Cart.isEmpty()) {
				Integer tt = (Integer) session.getAttribute("Total");
				String a = tt.toString();
				
				int Total = Integer.parseInt(a);
				System.out.println("This is total :");
				System.out.println(Total);
				model.addAttribute("Total", a);
				@SuppressWarnings("unchecked")
				List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
				model.addAttribute("listCart", listCart);
				return "checkout";
			}else {
				session.setAttribute("CartIsEmpty", "CartIsEmpty");
				return "redirect:/cart";
			}
			
		}
	}
	
	
	@PostMapping("checkout")
	public String checkOut(@ModelAttribute("fullname") String fullname , 
			@ModelAttribute("address") String address, @ModelAttribute("phone") String phone,
			@ModelAttribute("email") String email, Model model,HttpServletResponse resp )throws Exception {
		
		
		@SuppressWarnings("unchecked")
		List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
		User user = (User) session.getAttribute("acc");
		Integer tt = (Integer) session.getAttribute("Total");
		String a = tt.toString();
		int Total = Integer.parseInt(a);
		String status = "Pending";
		
		Order newOrder = new Order();
		newOrder.setTotal(Total);
		newOrder.setAddress(address);
		newOrder.setEmail(email);
		newOrder.setFullname(fullname);
		newOrder.setPhone(phone);
		newOrder.setUser(user);
		
		orderService.saveOrder(newOrder);
		List<Order> listOrder = orderService.getAllOrderByUser_Id(user.getId());
		newOrder = listOrder.get(listOrder.size() -1);
		
		
		for(Cart y :listCart) {
			Book book = y.getBook();
			System.out.println("this is listCart.Book");
			System.out.println(book);
			book.setQuantity(book.getQuantity() - y.getCount());
			book.setSold(book.getSold() + y.getCount());
			bookService.saveBook(book);
			
//			Order_Item newOrder_Item = new Order_Item();
//			newOrder_Item.setCount(y.getCount());
//			newOrder_Item.setOrder(newOrder);
//			newOrder_Item.setBook(y.getBook());
//			order_ItemService.saveOrder_Item(newOrder_Item);
			
			//cartService.deleteById(y.getId());
			cartRepository.deleteById(y.getId());
			System.out.println("This is size of list cart");
			System.out.println(listCart.size());
			
			
		}
		
		for(Cart y : listCart) {
			System.out.println("this is cart : listCart");
			System.out.println(y);
			int id = y.getId();
			System.out.println("this is id of cart");
			System.out.println(id);
			cartService.deleteById(id);
			session.setAttribute("CartDelete", id);
			listCart = cartService.GetAllCartByUser_id(user.getId());
			session.setAttribute("countCart", listCart.size());
			
		}
		
		listOrder = orderService.getAllOrderByUser_Id(user.getId());
		
		newOrder = listOrder.get(listOrder.size() - 1);
		session.setAttribute("order", newOrder);
		return "redirect:/invoice";
		
		
	}
	
	
	@GetMapping("invoice")
	public String Invoice(Model model) {
		Order order = (Order) session.getAttribute("order");
		String invoiceView = (String) session.getAttribute("invoiceView");
		session.setAttribute("invoiceView", null);
		List<Order_Item> listOrder_Item = order_ItemService.getAllByOrder_Id(order.getId());
		for ( Order_Item y : listOrder_Item) {
			System.out.println(" thiss  iss list order_item");
			System.out.println(y);
			System.out.println(y.getBook().getTieude());
			
		}
		
		
		model.addAttribute("invoiceView", invoiceView);
		model.addAttribute("listOrder_Item", listOrder_Item);
		session.setAttribute("listOrder_Item", listOrder_Item);
		model.addAttribute("order", order);
		return "invoice";
	}
	
	@GetMapping("/invoice/{id}")	
	public String InvoiceView(@PathVariable int id , Model model, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
		Order order = orderService.findById(id);
		session.setAttribute("order", order);
		session.setAttribute("invoiceView", "view");
		
		List<Order_Item> listOrder_Item = order_ItemService.getAllByOrder_Id(order.getId());
		for( Order_Item y : listOrder_Item) {
			System.out.println("This is List_OrderItem");
			System.out.println();
		}
		model.addAttribute("listOrder_Item", listOrder_Item);
		return "redirect:/invoice";
		
	}
	
	@GetMapping("/myhistory")
	public String Myhistory(Model model , HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		User user = (User) session.getAttribute("acc");
		if (user == null) {
			return "redirect:" + referer;
		} else {
			List<Order> listOrder = orderService.getAllOrderByUser_Id(user.getId());
			Collections.reverse(listOrder);
			model.addAttribute("listOrder", listOrder);
			System.out.println(listOrder);
			
			for (Order y : listOrder) {
				
				System.out.println(y.getOder_Item());
			}
		}
		return "myhistory";
			
	}
	
	
}
