package com.example.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.repository.UserRepository;
import com.example.board.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/auth/insertuser")
	public String insertUser() {
		return "user/insertUser";
	}
//	 요청한것을 받는건 컨트롤러만함/ 요청받게되면 보내준 데이터도 같이 받음 요청한 기능을 다처리하고 결과를 응답(요청, 응답)
//	 클라이언트와 컨트롤러끼리만 요청 응답함
//	 DB관련 작업은 repository만 함
	@PostMapping("/auth/insertuser")// json형태로 받아올 수 있음
	@ResponseBody // json으로 변경하기 위함 - 현재 string 사용시 jsp형태로 인식하기 때문
	public ResponseDTO<?> insertUser(@RequestBody User user) {
		
		// 아이디 중복검사
		User findUser = userService.getUser(user.getUsername());
		
		if(findUser.getUsername() == null) {
		// 클라이언트에게 전달받은 user정보를 서비스로 넘겨줌
		userService.insertUser(user);
		// 정상적으로 끝나면 클라이언트한테 회원가입 완료했다고 응답
		// 응답해주는 class만들어 사용
		return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername()+"님 회원가입 성공"); // 200이동
		}else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),user.getUsername() + "는 중복된 아이디입니다."); //400이동
		}
		}
	// 페이지 요청 gettmapping
	@GetMapping("/auth/login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping("/auth/login")
	@ResponseBody
	public ResponseDTO<?> login(@RequestBody User user, HttpSession session) {
		User findUser = userService.getUser(user.getUsername());
		if(findUser.getUsername() == null) {
			// 없는 아이디
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "없는 아이디");
		}else {
		// 아이디는 맞음 -> 비번 검사
			if(findUser.getPassword().equals(user.getPassword())) {
			// 로그인 성공
				session.setAttribute("principal", findUser); // 로그인시 저장 객체가 필요=>브라우저 상관없이 계속 진행되야함
				return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername()+ "님 로그인 성공");
			}else {
			// 비번 틀림
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호 틀림");
			}
		
		}
	}
	@GetMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/auth/userinfo")
	public String userinfo(HttpSession session, Model model) {
		User user = (User)session.getAttribute("principal"); // odject 형태로 나오기 때문에 형변환 필요
		System.out.println(user);
		
		User userInfo = userRepository.findById(user.getId()).get(); // 가입한사람 모든 정보
		
		model.addAttribute("userInfo", userInfo);
		
		return "user/userinfo";
	}
	
	
	@PutMapping("/auth/update")
	@ResponseBody
	public ResponseDTO<?> revise(@RequestBody User user, HttpSession session) {
		
		User userinfo = userRepository.findById(user.getId()).get();
		
		if(!user.getPassword().equals(""))
			userinfo.setPassword(user.getPassword());
		
		userinfo.setEmail(user.getEmail());
		
		userRepository.save(userinfo);
		
		session.setAttribute("pincipal", userinfo);
		return new ResponseDTO<>(HttpStatus.OK.value(), "회원정보 수정완료");
	}
	
	@DeleteMapping("/auth/delete")
	@ResponseBody
	public ResponseDTO<?> delete(@RequestBody User user, HttpSession session) {
		User userDe = userRepository.findById(user.getId()).get();
		userRepository.deleteById(userDe.getId());
//		userRepository.deleteById(user.getId());
		
		session.setAttribute("pincipal", null);
		return new ResponseDTO<>(HttpStatus.OK.value(), "회원탈퇴 성공");
	}
	
	
	
	// form테그 입력 시 name으로 설정한 것과 객체의 이름이 동일해 객체 생성 바로 됨
//	@PostMapping("/auth/insertuser")
//	public String insertuser(User user) {
//		userService.insertUser(user);
//		
//		return "index";
//	}
//	

}
