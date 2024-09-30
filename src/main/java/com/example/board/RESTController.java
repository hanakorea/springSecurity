package com.example.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class RESTController {
	
	@GetMapping("/rest")
	public String get() {
		return "get 요청 처리";
	}
	
	@PostMapping("/rest")
	public String post() {
		return "post 요청 처리";
	}
	
	@PutMapping("/rest")
	public String put() {
		return  "put 요청 처리";
	}
	
	@DeleteMapping("/rest")
	public String delete() {
		return "delete 요청 처리";
	}
	
	@GetMapping("/test")
	public List<TestVO> getTest() {
		System.out.println("test요청 받음");
		
		TestVO vo = new TestVO("홍길동", "1234");
		TestVO vo2 = new TestVO("고길동", "qwer");
		TestVO vo3 = new TestVO("둘리", "DUlli");
		
		List<TestVO> list = new ArrayList<>();
		
		list.add(vo);
		list.add(vo2);
		list.add(vo3);
		
		return list;
	}
	// html에 form태그로 테이터를 전송할 경우
	@PostMapping("/test")
	public String postTest(TestVO vo) {
		System.out.println(vo.getName());
		System.out.println(vo.getPw());
		
		return "정상처리";
	}
	
	// json으로 데이터를 전송
	@PostMapping("/test2")
	public String postTest2(@RequestBody TestVO vo) {
		System.out.println(vo.getName());
		System.out.println(vo.getPw());
		
		return "정상처리";
	}
	
	@DeleteMapping("/test/{no}")
	public String delete(@PathVariable Integer no) {
		return "delete 요청 : " + no;
	}

	@PutMapping("/test")
	public String put(@RequestParam Integer page) {
		return "요청한 페이지 번호: "  + page;
	}
	
	@GetMapping("/user")
	public User user() {
		// db에서 특정 유저 정보를 가져왔음
		User user1 = new User(1, "kim","1234", "aaaa@gmail");
		
		return user1;
	}
	
	@PostMapping("/user")
	public String printUser(User user) {
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		
		return "정상처리";
	}
	
	@PutMapping("/user")
	public void putInf(@RequestParam int id) {
		System.out.println(id);
	}
	
}
