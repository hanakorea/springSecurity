package com.example.board.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByTitleContaining(String keyword);
	
	// 페이징 처리
	Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}
