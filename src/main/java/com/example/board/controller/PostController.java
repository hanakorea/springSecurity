package com.example.board.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.PageDTO;
import com.example.board.domain.Post;
import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.dto.PostDTO;
import com.example.board.service.PostService;



@Controller
public class PostController {

	@GetMapping("/post")
	public String insertPost() {
		return "post/insertPost";
	}
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/post")
	@ResponseBody
	public ResponseDTO<?> insertPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, HttpSession session) {
		
//		if(bindingResult.hasErrors()) {
//			
//			List<String> errors = new ArrayList<>();
//			for(FieldError error : bindingResult.getFieldErrors()) {
//				errors.add(error.getDefaultMessage());
//			}
//			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errors);
//		}
		
		Post post = modelMapper.map(postDTO, Post.class);
		
		// 세션에 있는 유저 정보를 추출
		User principal = (User)session.getAttribute("principal");

		postService.insertPost(post, principal);
		// 저장이 끝나면 결과를 응답
		return new ResponseDTO<>(HttpStatus.OK.value(), "게시물 등록 완료");
		}
		
	// @pageableDefault는 page의 설정하는 어노테이션
	@GetMapping({"","/"})
	public String getPostList(Model model,@PageableDefault(size = 10, sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<Post> postList = postService.getPostList(pageable);
		
		model.addAttribute("postList",postList);
		
		// pageDTO이용한것
		model.addAttribute("pageDTO", new PageDTO(postList));
		
		return "index";
	}
	
	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model){
		Post postInfo = postService.getPost(id);
		
		model.addAttribute("postInfo", postInfo);
		
		return "/post/detail";
	}
	
	@GetMapping("/post/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		
		return "post/modify";
	}
	
	@PutMapping("/post")
	@ResponseBody
	public ResponseDTO<?> modify(@RequestBody Post post){
		postService.modify(post);
		return new ResponseDTO<>(HttpStatus.OK.value(),post.getId() + "번 수정 완료");
	}
	
	@DeleteMapping("/post/{id}")
	@ResponseBody
	public ResponseDTO<?> delete(@RequestBody int id){
		postService.delete(id);
		return new ResponseDTO<>(HttpStatus.OK.value(),id + "삭제 됨");
	}
	
	//게시물 검색
	@GetMapping("/post/search")
	public String search(String keyword, Model model, @PageableDefault(size = 2, sort="id", direction = Direction.DESC) Pageable pageable){
		Page<Post> result = postService.search(keyword, pageable);
		model.addAttribute("postList", result);
		model.addAttribute("pageDTO", new PageDTO(result));
		model.addAttribute("keyword",keyword);
		return "index";
	}
	
}
