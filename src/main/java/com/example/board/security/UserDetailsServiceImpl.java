package com.example.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.board.domain.User;
import com.example.board.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User principal = userRepository.findByUsername(username).orElseThrow(()->{
			return new UsernameNotFoundException(username+"은 없는 아이디입니다.");
		});
		
		return new UserDetailsImpl(principal);
	}
	
}
