package com.testForLTW.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.testForLTW.entity.Comment;

@Repository
public interface CommentRepository {
	 
	void deleteById(int id);
	void save(Comment comment);
	List<Comment> findAllByUser_id(int user_id);
}
