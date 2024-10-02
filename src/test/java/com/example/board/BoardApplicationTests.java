package com.example.board;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.repository.UserRepository;

@SpringBootTest
class BoardApplicationTests {

	@Autowired // 필요한 객체를 불러오기위한 어노테이션
	private UserRepository userRepository; 
	
	@Test
	void contextLoads() {
		// id가 1번인 레코드를 삭제
		userRepository.deleteById(1);
		
		
		
//	// id가 1번인  사람의 이름을 park으로 수정	
//		
//		Optional<User> user = userRepository.findById(1);
//		
//		User u = user.get();
//		
//		u.setUsername("park");
//		
//		userRepository.save(u);
//		
		
		
		
//		// 검색어
//		String keyword = "ef";
//		
//		// 해당 검색어를 포함하는 레코드를 조회
//		// 포함하는 이라는 걸로 처리되도록 만능문자가 있어야함
//		keyword = "%" + keyword + "%";
//		
//		List<User> result  =  userRepository.findByUsernameLike(keyword);
//		
//		for(User u : result) {
//			System.out.println(u);
//		}
	
		// select * from users where id=?;
//		Optional<User> result = userRepository.findById(5); // id는 기본키를 가리킴, optional이라는 객체로 리턴됨	
		
		
//		//username과 password를 기준으로 검색
//		User result = userRepository.findByUsernameAndPassword("kim", "1234");
//		System.out.println(result);
		
		
		
// 		username을 기준으로 검색한 결과	
//		User result = userRepository.findByUsername("kim");
//		
//		System.out.println(result);
		
		
//		System.out.println(result.isPresent()); // 있는지 없는지 확인해주는 메서드
//		
//		if(result.isPresent()) {
//			User  u = result.get();
//			System.out.println(u);
//		}else {
//			System.out.println("검색한 결과가 없음");
//		}
		
//		List<User> users = userRepository.findAll(); // User 형식으로 값이 나옴, 여러개 일수 있어서 List형식으로 저장
//		
//		System.out.println(users.size());
//		
//		for(User u : users) {
//			System.out.println(u);
//		}
		
		
	}

}
