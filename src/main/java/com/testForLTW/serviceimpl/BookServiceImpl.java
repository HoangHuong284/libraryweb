package com.testForLTW.serviceimpl;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testForLTW.entity.Book;
import com.testForLTW.repository.BookRepository;
import com.testForLTW.service.BookService;

@Service

public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deleteBookById(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public Book getBookByTieude(String tieude) {
		return bookRepository.findByTieude(tieude);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Boolean checkBookByTieudeAndTacgia(String tieude, String tacgia) {
//		Book book = bookRepository.findByTieude(tieude);
//		if(book.getTieude().equalsIgnoreCase(tieude) && book.getTacgia().equalsIgnoreCase(tacgia)) {
//			return true;
//		}
//		return false;
		Book book = bookRepository.findByTieudeAndTacgia(tieude, tacgia);
		if( book == null) return false;
		return true;
	}

	
	@Override
	public Book findById(int id) {
		return bookRepository.findById(id);
	}

	@Override
	public Book findByTieudeAndTacgia(String tieude, String tacgia) {
		
		return bookRepository.findByTieudeAndTacgia(tieude, tacgia);
	}

}
