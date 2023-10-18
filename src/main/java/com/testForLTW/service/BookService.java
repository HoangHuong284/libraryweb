package com.testForLTW.service;

import java.util.List;

import com.testForLTW.entity.Book;



public interface BookService {
	List<Book> getAllBooks();
	
	Book saveBook(Book book);
	Book updateBook(Book book);
	void deleteBookById(int id);
	Book getBookByTieude(String tieude);
	Book findById(int id);
	Book findByTieudeAndTacgia(String tieude, String tacgia);
	List<Book> findAll();
	Boolean checkBookByTieudeAndTacgia(String tieude,String tacgia);
	
	
}
