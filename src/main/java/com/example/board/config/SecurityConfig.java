package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // security관련된 환경설정 활성화 시켜줌
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		// 메서드 체이닝
		// 권한 설정+url설정+모든 사용자 사용 가능
		http.authorizeHttpRequests()
			.antMatchers("/", "/auth/**", "/js/**", "/img/**", "/webjars/**") // webjars는 프론트 관련 라이브러리
			.permitAll()
			.anyRequest().authenticated(); // 나머지 url은 인증 받아야 함
		
		// 구현한 웹페이지가 아닌 다른 웹페이지에서도 요청 -> 토큰을 받아 확인(공격 방어), post요청 시 토큰 필요
		http.csrf().disable(); // csrf토큰 비활성화-작업할 때 편하게 하기 위해
		
		http.formLogin().loginPage("/auth/login");
		
		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
		
		return http.build(); 
	}
	
	// 인증매니저 - 검사결과를 이용해 비교하고 결과를 보내줌
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean // password에 암호화(해싱)
	PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}
}
