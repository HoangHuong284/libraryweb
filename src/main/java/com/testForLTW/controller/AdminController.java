package com.testForLTW.controller;

import java.io.IOException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.testForLTW.entity.Book;
import com.testForLTW.repository.BookRepository;
import com.testForLTW.service.BookService;
import com.testForLTW.service.CloudinaryService;
import com.testForLTW.serviceimpl.BookServiceImpl;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
//@SessionAttributes("book")
public class AdminController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	BookService bookService;
	
	public Book book() {
		return new Book();
	}
	

	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		List<Book> listBooks = bookRepository.findAll();
		model.addAttribute("listBooks", listBooks);
		return "user_home";
	}
	
	@GetMapping("/admin_home")
	public String getBooks(Model model) throws IOException{
		List<Book> books = bookRepository.findAll();
		model.addAttribute("books", books);
	    return "admin_home";
		
	}
	@GetMapping("/logout")
	public String logout(Model model , HttpSession session) {
		session.removeAttribute("email");
		session.removeAttribute("acc");
		List<Book> listBooks = bookRepository.findAll();
		model.addAttribute("listBooks", listBooks);
		return "user_home";
	}
	
	@GetMapping("/addBook")
	public String showAddForm(Model model , HttpSession session) {
		Book b = new Book();
		model.addAttribute("book", b);
		model.addAttribute("mode", "Add book");
		model.addAttribute("btn", "Add");
		session.setAttribute("Hmm", "trung");
		return "admin_bookdetail";
	}
	
	@GetMapping("/book/view/{id}")
	public String viewBook(Model model, @PathVariable("id") int id ) {
		Book book = bookServiceImpl.findById(id);
		model.addAttribute("book", book);
		model.addAttribute("mode", "View book");
		model.addAttribute("btn", "Edit");
		return "admin_bookdetail";
	}
	
	@PostMapping("/book/edit/{id}")
	public String editBook(Model model , @PathVariable("id") int id  , @ModelAttribute("listImage") MultipartFile[] listImage , HttpSession session)throws Exception {
		Book book = bookServiceImpl.findById(id);
		model.addAttribute("book", book);
		model.addAttribute("mode", "Edit book");
		model.addAttribute("btn", "Save");
	    model.addAttribute("Hmm", "khongphaitrung");
	    session.setAttribute("Hmm", "khongphaitrung");
		return "admin_bookdetail";
	}
	
	@PostMapping("/book/save")
	public String saveBook(Model model , @ModelAttribute("book") Book book , @ModelAttribute("listImage" ) MultipartFile[] listImage , HttpSession session)throws Exception  {
            String a = (String) session.getAttribute("Hmm");
			if(bookService.checkBookByTieudeAndTacgia(book.getTieude(), book.getTacgia())&& a.equalsIgnoreCase("khongphaitrung") == false ) {				
				return "redirect:/addBook?trungsach";
			} 
				bookRepository.save(book);
				book.setPrice(1);
				book.setQuantity(100);
				book.setSold(1);
				if(listImage != null) {
					for(MultipartFile y : listImage) {
						String urlImg = cloudinaryService.uploadFile(y);
						book.setImg(urlImg);
						bookRepository.save(book);
					}
				}
				return "redirect:/admin_home";
	
	}
	
	@DeleteMapping("/book/delete/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookRepository.deleteById(id);
		return "redirect:/admin_home";
	}
	
}
