package com.testForLTW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testForLTW.entity.Book;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
	Book findById(int id);
	Book findByTieude(String tieude);
	Book findByTieudeAndTacgia(String tieude, String tacgia);
	void deleteById(int id);
	
	
}
