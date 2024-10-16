package com.example.board.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.EqualsAndHashCode.Exclude;

@Configuration
public class BoardWebMvcConfig implements WebMvcConfigurer{
	
	// mapper를 스프링컨테이너에 보관하게 시켜 의존성주입으로 사용가능하게 함
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
