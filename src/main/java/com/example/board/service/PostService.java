package com.example.board.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;
import com.example.board.repository.UserRepository;

@Service
public class PostService {
	
	
	@Autowired
	private PostRepository postRepository;


	public void insertPost(Post post, User user){
		// 추출한 유저 정보를 post객체에 넣어줘야 함
		post.setUser(user);
		// cnt도 0으로 설정
		post.setCnt(0);
		// 설정이 끝난 post객체를 db에 저장	
		postRepository.save(post);	
		}
	
	// DB에서 게시글을 모두 가져 오기
	@Transactional(readOnly = true) // 작업을 하나로 묶어줌(작업이 잘 못되면 다같이 롤백)
	public Page<Post> getPostList(Pageable pageable){
		return postRepository.findAll(pageable);
	}
	
	public Post getPost(int id){
//		Post findPost = postRepository.findById(id).orElseGet(()->{
//			return new Post();
//		});
//		return findPost;
		Optional<Post> data = postRepository.findById(id);
		
		if(data.isPresent()) {
			return data.get();
		}else {
			return null;
		}
	}
	
	public void modify(Post p){
		Post post = postRepository.findById(p.getId()).get();
		post.setTitle(p.getTitle());
		post.setContent(p.getContent());
		postRepository.save(post);
	}
	
	public void delete(int id) {
		postRepository.deleteById(id);
	}
	
	}

