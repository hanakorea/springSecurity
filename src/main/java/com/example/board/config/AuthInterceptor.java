package com.example.board.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.board.domain.User;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(); // session 객체를 요청정보에서 가져옴
		
		User principal = (User)session.getAttribute("principal"); // key vlaue(object형태) 형태로 저장함
		//session에 key,value 형태로 모든 정보를 넣을 수 있음 -> value형태는 object임 
		
		if(principal == null) {
			response.sendRedirect("/auth/login"); // 로그인 정보가 없을 때 다른 페이지로 이동
		}
		
		return true;
	}

}
