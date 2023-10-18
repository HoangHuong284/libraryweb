package com.testForLTW.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testForLTW.entity.Comment;
import com.testForLTW.repository.CommentRepository;
import com.testForLTW.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	
	CommentRepository commentRepository;
	
	@Override
	public void deleteById(int id) {
		commentRepository.deleteById(id);
		
	}

	@Override
	public List<Comment> GetAllCommentByUser_id(int user_id) {
		return commentRepository.findAllByUser_id(user_id);
	}

	@Override
	public void saveComment(Comment comment) {
		 commentRepository.save(comment);
	}

}
