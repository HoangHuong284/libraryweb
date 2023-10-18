package com.testForLTW.service;

import java.util.List;

import com.testForLTW.entity.Comment;

public interface CommentService {
	
	void deleteById(int id);
	
	List<Comment> GetAllCommentByUser_id(int id);
	
	void saveComment(Comment comment);
}
