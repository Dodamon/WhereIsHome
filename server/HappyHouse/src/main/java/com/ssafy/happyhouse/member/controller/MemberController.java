package com.ssafy.happyhouse.member.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.capcha.ApiExamCaptchaImage;
import com.ssafy.happyhouse.capcha.ApiExamCaptchaNkey;
import com.ssafy.happyhouse.capcha.ApiExamCaptchaNkeyResult;
import com.ssafy.happyhouse.member.dto.Member;
import com.ssafy.happyhouse.member.dto.MySecured;
import com.ssafy.happyhouse.member.dto.Role;
import com.ssafy.happyhouse.member.service.MemberService;

@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080"})
@Controller
@RequestMapping("/member")
public class MemberController {
    
    @Autowired
    MemberService memberService;
    
    @Autowired ApiExamCaptchaNkey apiExamCaptchaNkey;
	@Autowired ApiExamCaptchaImage apiExamCaptchaImage;
	@Autowired ApiExamCaptchaNkeyResult apiExamCaptchaNkeyResult;
    
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
            System.out.println("???????????? ??????");
            return 1;
        }else {
            System.out.println("???????????? ??????1");
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
            
            // ???????????? ?????? ????????? ??????
            // ????????? ????????? ??????????????? ??????!!!!!!!!!!!!!!!!!!
            if("admin@admin.com".equals(id)) {
            	System.out.println(":::::::::::::::::::admin?????? ????????? ??????");
            	m.setRole(Role.ADMIN);
            } else {
            	System.out.println("::::::::::::::::::::::user?????? ????????? ??????");
            	m.setRole(Role.USER);
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("member", m);    
            System.out.println("????????? ??? ????????? ?????? : " + session.getId());
            return m;
        } else {
             return new Member();
        }
        
    }
    
    @PostMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        System.out.println("???????????? ??????");
        HttpSession session = request.getSession(false);
        if(session != null) {
            System.out.println("session invalidate");
            session.invalidate();
            System.out.println("???????????? ??????" + request.getSession(false));
        }
        return "???????????? ???????????????.";
    }
    
    @MySecured(role = Role.USER)
    @PostMapping("/userinfo")
    @ResponseBody
    public Member userInfo(HttpServletRequest request) {
//        System.out.println("????????????????????????");
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
    
    @MySecured(role = Role.USER)
    @PostMapping("/update")
    @ResponseBody
    public Member update(String id, String password, String name, String address, String phone, HttpServletRequest request) {
    
        try {
            int i = memberService.update(id, password, name, address, phone);
            if(i >0) {
                System.out.println("???????????? ??????");
                Member m = userInfo(request);
                return m;
            }else {
                System.out.println("??????????????????1");
               return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @MySecured(role = Role.USER)
    @PostMapping("/delete")
    @ResponseBody
    public Boolean delete(@RequestParam String id, HttpServletRequest request) {
        System.out.println("?????? ??????" + id);
        
        // ??????????????? ?????? ????????????
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
//        System.out.println("?????? ????????????!!!!");
        return false;
    }
    
    @GetMapping("/getCapcha")
	@ResponseBody
	public void  capcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("capcha controller");
		
		String result = apiExamCaptchaNkey.ApiExamCaptchaNkey_main();
		
		String file_name = apiExamCaptchaImage.getFile_name();
		
	    
	    byte[] fileByte = FileUtils.readFileToByteArray(apiExamCaptchaImage.getFile());

	    response.setHeader("Content-Transfer-Encoding", "binary");

	    response.getOutputStream().write(fileByte);
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
		
	}
    
    @PostMapping("/checkCapcha")
	@ResponseBody
	public String  checkCapcha(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("checkCapcha controller");
		
		String original_key = apiExamCaptchaNkey.getKey();
		String sended_key = code;
		
		System.out.println("???????????? ??? -> " + original_key + " : " + sended_key);
		
		String responseBody = apiExamCaptchaNkeyResult.ApiExamCaptchaNkeyResult_main(code);
		
		JSONParser jsonParser = new JSONParser();
        
        //3. To Object
        Object obj = jsonParser.parse(responseBody);
        
        //4. To JsonObject
        JSONObject jsonObj = (JSONObject) obj;
        
        Boolean result = (Boolean) jsonObj.get("result");
		System.out.println(responseBody);
		return responseBody;
		
//		String result = apiExamCaptchaNkey.ApiExamCaptchaNkey_main();
		
//		String file_name = apiExamCaptchaImage.getFile_name();

		
	}
    
    
    
}