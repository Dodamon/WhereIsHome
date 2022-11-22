package com.ssafy.happyhouse.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.happyhouse.board.service.BoardService;
import com.ssafy.happyhouse.map.dto.Site_gathering;
import com.ssafy.happyhouse.map.service.MapService;
import com.ssafy.happyhouse.member.dto.Member;
import com.ssafy.happyhouse.member.service.MemberService;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired MapService mapService;
	
	@Autowired MemberService memberService;
	
	@Autowired BoardService boardService;

	
	@PostMapping("/selectMembers")
	@ResponseBody
	public PageInfo<Member> getMembers(HttpServletRequest request) throws Exception {

		
		
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		List<Member> list = memberService.getMembers();
		System.out.println("DB에서 가져온 멤버 수 : " + list.size());
		
		
		return PageInfo.of(list);
	}
	
	@PostMapping("/updateMember")
	@ResponseBody
	public void updateMember(HttpServletRequest request) throws Exception {

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		Member member = new Member(-1, id, "", name, phone, address);
		
		memberService.updateMemberByAdmin(member);
		
	}
	
	@PostMapping("/selectImjang")
	@ResponseBody
	public PageInfo<Site_gathering> getImjangs(String pageNum, String pageSize, HttpServletRequest request) throws Exception {

		
		
		
		
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		
		List<Site_gathering> list = mapService.getGatherings();
		System.out.println(list.size());
		
		return PageInfo.of(list);
		
	}
	
	@PostMapping("/clickImjang")
	@ResponseBody
	public Site_gathering clickImjang(int code, HttpServletRequest request) throws Exception {

		
		Site_gathering imjang = mapService.clickImjang(code);
		
		
		return imjang;
		
	}
	
	@PostMapping("/modifyImjang")
	@ResponseBody
	public int modifyImjang(int code, String title, HttpServletRequest request) throws Exception {
		System.out.println("modify imjang 진입");
		
		Site_gathering site_gathering = new Site_gathering(code, title, "", "", new Date(), 0, 0, 0, "", "", "");
				
		int i = mapService.modifyImjang(site_gathering);
		
		return i;
	}
	
	@PostMapping("/deleteImjang")
	@ResponseBody
	public int deleteImjang(int code, HttpServletRequest request) throws Exception {
		System.out.println("deleteImjang 진입");
				
		int i = mapService.deleteImjang(code);
		
		return i;
	}
	
	@GetMapping("/getBoardSize")
	@ResponseBody
	public int getBoardSize(HttpServletRequest request) throws Exception {
		System.out.println("getBoardSize 진입");
				
		int i = boardService.getSize();
		
		return i;
	}
	
	@GetMapping("/getMemberSize")
	@ResponseBody
	public int getMemberSize(HttpServletRequest request) throws Exception {
		System.out.println("getMemberSize 진입");
				
		int i = memberService.getMemberSize();
		
		return i;
	}
	
	@GetMapping("/getImjangSize")
	@ResponseBody
	public int getImjangSize(HttpServletRequest request) throws Exception {
		System.out.println("getImjangSize 진입");
				
		int i = mapService.getImjangSize();
		
		return i;
	}
	

}
