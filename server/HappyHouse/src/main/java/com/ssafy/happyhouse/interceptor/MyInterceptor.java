package com.ssafy.happyhouse.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.member.dto.Member;


public class MyInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("여기는 preHandle입니다");
		HttpSession session=request.getSession(false); 
		Cookie[] cookies = request.getCookies();
        boolean isCookiesHasJsessionid = false;
        
        System.out.println("interceptor cookie : " + cookies);
        if(cookies != null) {
	        for(Cookie c : cookies) {
	            if(c.getValue().equals(session.getId())) {
	                isCookiesHasJsessionid = true;
	                break;
	            }
	        }
        }
		
		
		System.out.println("interceptor session : " + session);
		if(session!=null) {
			Member vo =(Member) session.getAttribute("member");
			
			if(vo!=null && vo.getName()!=null && isCookiesHasJsessionid) {	
				System.out.println("name : " + vo.getName());
				return true;
			}else {//login 안되었을 때
				response.sendRedirect("/");
				return false;
			}
		}
		response.sendRedirect("/");
		return false;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("여기는 postHandle입니다");
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("여기는 afterCompletion입니다");
		
	}

}
