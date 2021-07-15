package com.site.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id") == null) {
			System.out.println("로그인 된 아이디가 없습니다.");
			return false;
		}
		
		return true;
	}
	
	
	
}
