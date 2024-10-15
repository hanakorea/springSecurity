package com.example.board.test;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.dto.UserDTO;

@Controller
public class TestController {
	
	@GetMapping("/jointest")
	public String test(){
		return "test/join";
	}
	
	@PostMapping("/test/join")
	public String join(@Valid UserDTO userDTO, BindingResult bindigResult, Model model) {
		// 유효성 검사 결과에 따라서 회원가입 처리를 할건지, 못하게 막을건지
		
		// 유효성 검사에 에러가 있냐 없냐를 리턴
		if(bindigResult.hasErrors()) {
			// 입력한것에 문제가 있으므로 오류메세지를 담아서 보내줌
			// 오류 메세지를 담아줄 컬렉션을 생성 + 결과 메세지를 하나씩 넣어줌
			List<String> errorMsg = new ArrayList<>();
			for(FieldError error : bindigResult.getFieldErrors()) {
				errorMsg.add(error.getDefaultMessage());
			}
			model.addAttribute("errorMsg", errorMsg);
			
			// 회원가입 실패해도 기존 입력내용이 남아있어야 하므로 모델에 저장
			model.addAttribute("userDTO", userDTO);
			
			return "test/join";
		}else {
			// 문제가 없으므로 회원가입 처리
			return "redirect:/";
		}
	}
}
