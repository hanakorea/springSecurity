package com.example.board.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.ResponseDTO;
import com.example.board.domain.RoleType;
import com.example.board.domain.User;
import com.example.board.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	//회원가입 처리 해주는 메서드 만들것임
	@Transactional
	public void insertUser(User user) {
		user.setRole(RoleType.USER);
		
		userRepository.save(user);

	}
	// userName으로 검색한 결과가 DB있으면 해당 객체를 리턴
	// 없으면 빈객체를 리턴
	public User getUser(String username) {
		// orElseGet 람다 함수 - 익명함수랑 같은 역할
		// 있으면 검색 결과의 객체가 들어가고 아니면 빈객체가 들어감
		User findUser = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		
		return findUser;
	}
	
	
}
// 서버쪽에서 클라이언트가 회원가입에 필요한 데이터를 보내주면 
// 그 데이터들을 DB에 저장되도록 처리 

// 회원가입요청을 하면 그 요청을 처리해주는 컨트롤러

// 그 데이터들을 DB에 저장하려면 해당 엔티티와 레파지토리가 필요함(구현되어있음)

// 서비스가 필요함? ( 서비스는 컨트롤러와 레파지토리의 중간다리 역할 클래스) - 유지보수를 위함/별도로 처리해야할 기능들 모아둔 것
// -컨트롤러와 레파지터리 사이에서 작업을 하게됨