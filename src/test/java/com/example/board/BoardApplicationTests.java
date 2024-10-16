package com.example.board;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;
import com.example.board.repository.UserRepository;

@SpringBootTest
class BoardApplicationTests {

	@Test
	void contextLoads() {
		// 비밀번호를 해싱(암호화하는)
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pw = "123zcvs@#!@#";
		String encodePw = encoder.encode(pw);
		
		System.out.println("암호화 전 비번 : " + pw);
		System.out.println("암호화 후 비번 : " + encodePw);
		System.out.println("두개 비교 : " + pw.equals(encodePw));
		System.out.println("진짜 두개 비교 :" + encoder.matches(pw, encodePw));
	}

}
