package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HelloController {
	
	@GetMapping("/html")
	public String html() {
		System.out.println("HTML요청 받음");
		
		return "redirect:hello.html";
	}
	
	@GetMapping("/text")
	@ResponseBody
	public String text() {
		return "hello text";
	}

	@GetMapping("/img")
	public String img() {
		return "redirect :img/kakaofriends.gif";
	}
	
	@GetMapping("/jsp")
	public String jsp(Model model) {
		model.addAttribute("name", "홍길동");
		
		return "hello";
	}
	
}
