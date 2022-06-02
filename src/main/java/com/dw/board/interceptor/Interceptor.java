package com.dw.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//Component : 내가 직접 작성한 클래스를 스프링에게 빈(스프링이 관리하는 클래스)으로 등록하라는 뜻 
@Component
public class Interceptor implements HandlerInterceptor{

	//preHandle 컨트롤러에 도착하기 전에 요청을 가로채는 함수
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		String url = request.getRequestURI();
		String ip = request.getHeader("X-Forwarded-For");
		if(ip == null) ip = request.getRemoteAddr();
		System.out.println("접속한 IP ==> "+ip);
		System.out.println("요청 받은 URL ==> "+url);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	
	}

}
