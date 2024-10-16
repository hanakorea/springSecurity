package com.example.board.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.board.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//사용자에 대한 다양한 정보들을 저장하는 class구현
// DB에서 정보를 가져와 로그인 성공 시 그에 대한 객체 필요 -> 이를 담아주는 class(정보를 담을 수 있는 필드 필요)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	// 권한들을 리턴 시켜주는 메서드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//  collection은 부모격임
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(()->{
			return "ROLE_" + user.getRole();
		});
		return roleList;
	}

	@Override
	public String getPassword() {
		// security는 비밀번호는 암호화되어있음 {noop}은 암호화 설정 x
		
		//return "{noop}"+ user.getPassword();
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 만료된 계정인지 리턴시켜주는 메서드(만료안됨 : true)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겼는지 리턴시켜주는 메서드(안잠김 : true)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 자격증명 만료된건지 리턴(만료안됨 : true)
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정 비활성화 여부를 리턴(활성화 : true)
		return true;
	}
	
	
}
