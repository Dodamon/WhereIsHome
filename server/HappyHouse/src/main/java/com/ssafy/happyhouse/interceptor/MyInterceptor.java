package com.ssafy.happyhouse.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.member.dto.Member;
import com.ssafy.happyhouse.member.dto.MySecured;
import com.ssafy.happyhouse.member.dto.Role;

@Component
public class MyInterceptor implements HandlerInterceptor{
	
	@Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("preHandler");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        MySecured mySecured = handlerMethod.getMethodAnnotation(MySecured.class);

        // Case 1: 비회원도 이용가능한 기능
        // method에 @MySequred가 없는 경우, 즉 인증이 필요 없는 요청 비회원도 이용할 수 있는 경우
        if (mySecured == null) {
        	System.out.println("case 1");
            return true;
        }

        // Case 2: 회원만 이용할 수 있는 기능 (비회원이 접근)
        // @MySequred가 있는 경우이므로, 세션이 있는지 체크
        HttpSession session = request.getSession(false);
        System.out.println("여기서 세션_________" + session);
        if (session == null) {
        	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "500 오류");
        	System.out.println("case 2");
            return false;
        }
        System.out.println("Interceptor Session: " + session.getId());
        
        // Case 3: 회원의 세션 시간이 만료된경우
        // 세션이 존재하면 유효한 유저인지 확인
        Member m = (Member) session.getAttribute("member");
        System.out.println("Interceptor Member:  " + m);
        if (m == null) {
        	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "500 오류");
            response.sendRedirect("/error");
            System.out.println("case 3");
            return false;
        }
       
        // Case 4: 진짜 해커가 접근하는 경우
        // 서버의 JSESSIONID와 클라이언트의 JSESSIONID가 다른경우
        Cookie[] cookies = request.getCookies();
        String JSESSIONID = null;
        for(Cookie c: cookies) {
        	if("JSESSIONID".equals(c.getName())) {
        		JSESSIONID = c.getValue();
        	}
        }
        System.out.println("Cookie JSESSIONID : " + JSESSIONID);
        if(!JSESSIONID.equals(session.getId())) {
        	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "500 오류");
        	System.out.println("case 4");
        	return false;
        }
        

        // Case 5: 유저가 URL로 ADMIN Page에 접근하는 경우
        String role = mySecured.role().toString();
        if(role != null) {
            if ("ADMIN".equals(role)) {
                if (m.getRole() != Role.ADMIN) {
                	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "500 오류");
                    response.sendRedirect("/error");
                    System.out.println("case 5");
                    return false;
                }
            }
        }

        // 접근허가
        return true;
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
