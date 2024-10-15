package com.example.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.domain.Post;
import com.example.board.domain.Reply;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;
import com.example.board.repository.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

	public void insertReply(int postId, Reply reply, User user) {
		Post post = postRepository.findById(postId).get();
		
		reply.setPost(post);
		reply.setUser(user);
		
		replyRepository.save(reply);
		
	}
	
	public void deleteReply(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
