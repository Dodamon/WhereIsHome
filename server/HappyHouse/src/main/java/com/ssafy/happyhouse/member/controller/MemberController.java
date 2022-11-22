package com.ssafy.happyhouse.member.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.member.dto.Member;
import com.ssafy.happyhouse.member.dto.Role;
import com.ssafy.happyhouse.member.service.MemberService;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/member")
public class MemberController {
    
    @Autowired
    MemberService memberService;
    
    @Transactional
    @PostMapping("/register")
    @ResponseBody
    public int register(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        
        
        int idx= memberService.register(new Member(2, id, pw, name, phone, address));
        if(idx >0) {
            System.out.println("회원가입 성공");
            return 1;
        }else {
            System.out.println("회원가입 실패1");
            return 0;
        }
    }
    
    @PostMapping("/checkid")
    @ResponseBody
    public int checkId(@RequestParam String id) {
        try {
            int i = memberService.checkId(id);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    @Transactional
    @PostMapping("/login")
    @ResponseBody
    public Member login(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        System.out.println("id=" + id);
        System.out.println("pw=" + pw);
        
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("pw", pw);
        Member m = memberService.login(map);
        System.out.println(m);

    
        if(m != null) {
            System.out.println(1);
            
            // 관리자일 경우 임의로 설정
            // 나중에 무조건 지워야하는 코드!!!!!!!!!!!!!!!!!!
            if("admin@admin.com".equals(id)) {
            	m.setRole(Role.ADMIN);
            } else {
            	m.setRole(Role.USER);
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("member", m);    
            System.out.println("로그인 시 생성한 세션 : " + session.getId());
            return m;
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
            System.out.println("로그아웃 완료" + request.getSession(false));
        }
        return "로그아웃 되었습니다.";
    }
    
    @PostMapping("/userinfo")
    @ResponseBody
    public Member userInfo(HttpServletRequest request) {
//        System.out.println("유저정보가져오기");
//        HttpSession session = request.getSession(false);
//        if(session != null) {
//            Member m = (Member) session.getAttribute("member");
//            System.out.println("before try" + m);
//            try {
//                m = memberService.userinfo(m);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println(m);
//            return m;            
//        }else {
//            return null;
//        }
        HttpSession session = request.getSession(false);
        String id = request.getParameter("id");
        try {
            Member m = memberService.userinfo(new Member(0, id, "", "", "", ""));
            System.out.println(m);
            System.out.println("userinfo from session : " + session.getAttribute("member"));
            return m;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        
        
    }
    
    @PostMapping("/update")
    @ResponseBody
    public Member update(String id, String password, String name, String address, String phone, HttpServletRequest request) {
    
        try {
            int i = memberService.update(id, password, name, address, phone);
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
//        HttpSession session=request.getSession(false);
//        if(session != null) {
//            Member m = (Member) session.getAttribute("member");
//            if(m.getId().equals(id)) {
//                try {
//                    memberService.delete(id);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                session.invalidate();
//                return true;
//            }
//        }
        try {
            memberService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("해킹 시도감지!!!!");
        return false;
    }
    
}