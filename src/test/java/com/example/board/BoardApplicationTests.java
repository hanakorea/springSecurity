package com.example.board;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;
import com.example.board.repository.UserRepository;

@SpringBootTest
class BoardApplicationTests {

	@Autowired // 필요한 객체를 불러오기위한 어노테이션
	private UserRepository userRepository; 
	
	@Autowired
	private PostRepository postRepository;
	
	@Test
	void contextLoads() {
		User user = userRepository.findById(5).get();
		
		for(int i=0; i<100; i++) {
			Post post  = new Post();
			post.setTitle("임시 게시물 제목" + i);
			post.setContent("임시 게시물 내용" + i);
			post.setUser(user);
			
			postRepository.save(post);
		}
	}

}
