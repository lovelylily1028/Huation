package com.site.interceptor;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggerIntercepter implements HandlerInterceptor {

	// 로그인이 필요한 페이지
	public List loginEssential 
		= Arrays.asList("/board/**","/ajax/**","/chart/**","/room");

	// 로그인이 필요하지 않은 페이지
	public List loginInessential
	    = Arrays.asList("/resources/**","/util/**","/user/**");
	
	
	// 컨트롤러가 실행되기 전 수행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String loginId = (String)request.getSession().getAttribute("loginId");
		System.out.println("==================== BEGIN ====================");
		System.out.println("");
		System.out.println("Request URI :  " + request.getRequestURI());
		System.out.println("    loginId :  " + loginId);
		System.out.println("");
		System.out.println("===============================================");
		
		if(loginId != null) {return true;}
		
		else {
			String destUri = request.getRequestURI();
			
			String destQuery = request.getQueryString();
			String dest = (destQuery == null) ? destUri : destUri+"?"+destQuery;
            
            request.getSession().setAttribute("dest", dest);
            
           // System.out.println("dest : " + dest);
           // System.out.println("destQuery : " + destQuery);
        
            response.sendRedirect("/user/loginPage");
            return false;
		}
		
	}

	
	
	// 컨트롤러 실행 된 후 수행
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("===================== END =====================");
		System.out.println("");
	}

	// view 까지의 모든 진행이 완료 되었을 때
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	
	 
	
	
	
	
}
