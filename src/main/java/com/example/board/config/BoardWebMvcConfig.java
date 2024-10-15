package com.example.board.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.EqualsAndHashCode.Exclude;

@Configuration
public class BoardWebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터 추가해 주는 메서드
		registry.addInterceptor(new AuthInterceptor()) // 만들어준 interceptor 객체 넣어주기
				.addPathPatterns("/", "/post/**");// 실행될 주소만 넣어주기
//					.addPathPatterns("/", "/auth/**");// auth시작하는 모든 경로넣어주기
//					.excludePathPatterns("/auth/userinfo") // 제외 시켜 줌
	}
	
	// mapper를 스프링컨테이너에 보관하게 시켜 의존성주입으로 사용가능하게 함
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
