package com.ssafy.happyhouse.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.member.dto.Member;
import com.ssafy.happyhouse.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Transactional
	@PostMapping("/register")
    @ResponseBody
    public Member register(@RequestParam String id, 
    		@RequestParam String pw, 
    		@RequestParam String name) throws Exception {
    	
		try {
			int idx= memberService.register(new Member(name, id, pw));
			if(idx >0) {
				System.out.println("회원가입 성공");
	            return new Member("sign in success", "");
	        }else {
	        	System.out.println("회원가입 실패1");
	            return new Member("sign in fail", "");
	        }
			
		}catch(Exception e){
			System.out.println("회원가입 실패2");
            return new Member("sign in fail", "");
		}
    }
	
	@PostMapping("/checkid")
	@ResponseBody
	public int checkId(@RequestParam String id) {
		try {
			int i = memberService.checkId(id);
			if(i == 0) return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Transactional
	@PostMapping("/login")
	@ResponseBody
	public Member login(@RequestParam String id, @RequestParam String pw, HttpServletRequest req) throws Exception {
		System.out.println("id=" + id);
		System.out.println("pw=" + pw);
		
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		Member m = memberService.login(map);
		System.out.println(m);
//		Map<String, String> map = new HashMap<>();
	
		if(m != null) {
			System.out.println(1);
			HttpSession session = req.getSession();
			session.setAttribute("member", m);	
			return new Member(m.getName(), m.getId());
		} else {
			 return new Member();
		}
		
	}
	
    @PostMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
    	System.out.println("로그아웃 시작");
        HttpSession session = request.getSession(false);
        if(session != null) {
        	System.out.println("session invalidate");
            session.invalidate();
        }
        return "로그아웃 되었습니다.";
    }
    
    @GetMapping("/userinfo")
    @ResponseBody
    public Member userInfo(HttpServletRequest request) {
    	System.out.println("유저정보가져오기");
    	HttpSession session = request.getSession(false);
		if(session != null) {
			Member m = (Member) session.getAttribute("member");
			System.out.println("before try" + m);
			try {
				m = memberService.userinfo(m);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(m);
			return m;			
		}else {
			return null;
		}
    }
    
    @PostMapping("/update")
    @ResponseBody
    public Member update(@RequestBody Map<String, String> map, HttpServletRequest request) {
    	//System.out.println("유저정보 업데이트하기");
    
		try {
			System.out.println(map);
			int i = memberService.update(map);
			if(i >0) {
				System.out.println("업데이트 성공");
				Member m = userInfo(request);
				return m;
	        }else {
	        	System.out.println("업데이트실패1");
	           return null;
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    
    @PostMapping("/delete")
    @ResponseBody
    public Boolean delete(@RequestParam String id, HttpServletRequest request) {
        System.out.println("회원 삭제" + id);
        
        // 회원삭제를 다시 확인한다
        HttpSession session=request.getSession(false);
        if(session != null) {
            Member m = (Member) session.getAttribute("member");
            if(m.getId().equals(id)) {
                try {
                    memberService.delete(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                session.invalidate();
                return true;
            }
        }
        System.out.println("해킹 시도감지!!!!");
        return false;
    }
    
}