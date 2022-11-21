package com.ssafy.happyhouse.board.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.happyhouse.board.dto.Board;
import com.ssafy.happyhouse.board.service.BoardService;
import com.ssafy.happyhouse.map.dto.Dongcode;
import com.ssafy.happyhouse.map.dto.Housedeal;
import com.ssafy.happyhouse.map.dto.Houseinfo;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;

//	@GetMapping("selectAll")
//	public List<Board> selectAll() {
//		List<Board> list = boardService.selectAll();
//		System.out.println(list);
//		return list;
//	}
	
	
	@PostMapping("/deleteArticle")
	@ResponseBody
	public void deleteArticle(@RequestParam String code, HttpServletRequest request){	
		boardService.delete(code);
		
	}
	

	@PostMapping("/modifyArticle")
	@ResponseBody
	public void modifyArticle(@RequestParam String code, String title, String content, String writer, HttpServletRequest request){	
		System.out.println(code +  title+ writer+ content);
		
		boardService.modify(code, title, writer, content);
		
	}
	

	@PostMapping("/clickArticle")
	@ResponseBody
	public Board clickArticle(@RequestParam String code, HttpServletRequest request){	
		Board board = boardService.selectOne(code);
		
		return board;
		
	}
	

	@PostMapping("/selectAll")
	@ResponseBody
	public PageInfo<Board> selectAll(HttpServletRequest request) {
		System.out.println("selectAll session : " + request.getSession().getId());
		System.out.println("page no = "+request.getParameter("pageNum"));
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		System.out.println(pageNum);
		System.out.println(pageSize);
		
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		List<Board> list=boardService.selectAll();
		
		return PageInfo.of(list);
	}
	

//	@GetMapping("/sido")
//	@ResponseBody
//	public PageInfo<Dongcode> getSido() {
////		System.out.println("sido 진입");
//		
//		List<Dongcode> list = boardService.getSido();
//		
//		System.out.println("DB에서 가져온 시도 갯수 : " + list.size());
//		
//		
//		return PageInfo.of(list);
//	}
//	
//
//	@GetMapping("/gugun")
//	@ResponseBody
//	public PageInfo<Dongcode> getGugun(HttpServletRequest request) {
////		System.out.println("gugun 진입");
//		String sidoName = request.getParameter("sidoName"); 
//		 
////		System.out.println(sidoName);
//		List<Dongcode> list = boardService.getGugun(sidoName);
//		System.out.println("DB에서 가져온 구군이 갯수 : " + list.size());
////		System.out.println("DB에서 가져온 구군이름 첫번째 : " + list.get(1));
//		
//		
//		return PageInfo.of(list);
//	}
//	

//	@GetMapping("/dong")
//	@ResponseBody
//	public PageInfo<Dongcode> getDong(HttpServletRequest request) {
////		System.out.println("gugun 진입");
//		String sidoName = request.getParameter("sidoName");
//		String gugunName = request.getParameter("gugunName");
//		
//		 
////		System.out.println(sidoName);
//		List<Dongcode> list = boardService.getDong(sidoName, gugunName);
//		System.out.println("DB에서 가져온 동 갯수 : " + list.size());
////		System.out.println("DB에서 가져온 구군이름 첫번째 : " + list.get(1));
//		
//		
//		return PageInfo.of(list);
//	}
//	

//	@GetMapping("/houseinfo")
//	@ResponseBody
//	public PageInfo<Houseinfo> getHouseinfo(HttpServletRequest request) {
//		System.out.println("getHouseinfo 진입");
//		String sidoName = request.getParameter("sidoName");
//		String gugunName = request.getParameter("gugunName");
//		String dongName = request.getParameter("dongName");
//		
//		String dongcode = boardService.getDongcode(sidoName, gugunName, dongName);
//		 
//		System.out.println("DB에서 가져온 동코드 : " + dongcode);
//		
//		List<Houseinfo> list = boardService.getHouseinfo(dongcode);
//		System.out.println("DB에서 가져온 아파트 갯수 : " + list.size());
////		System.out.println("DB에서 가져온 구군이름 첫번째 : " + list.get(1));
//		
//
//		return PageInfo.of(list);
//	}
//	

//	@GetMapping("/housedeal")
//	@ResponseBody
//	public PageInfo<Housedeal> getHousedeal(HttpServletRequest request) {
//		System.out.println("getHouseinfo 진입");
//		String aptCode = request.getParameter("aptCode");
//		
//		List<Housedeal> list = boardService.getHousedeal(aptCode);
//		 
//		System.out.println("DB에서 가져온 아파트 갯수 : " + list.size());
////		System.out.println("DB에서 가져온 구군이름 첫번째 : " + list.get(1));
//		
//
//		return PageInfo.of(list);
//	}

	

//	@GetMapping("/write")
//	public String writeBoard(HttpSession session) {
//		// CSRF token 발행
//		String csrfToken = UUID.randomUUID().toString();
//		System.out.println(csrfToken);
//		session.setAttribute("CSRF_TOKEN", csrfToken);
//		
//		return "boardWrite";
//	}
	
		

	@PostMapping("/write")
	@ResponseBody
	public String writeBoard(HttpSession session, HttpServletRequest request) {
//		System.out.println(board);
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println(writer);
		System.out.println(title);
		System.out.println(content);
		
		Board board = new Board(1, 1, title, content, writer, new Date());
		
		long i = boardService.write(board);
		System.out.println(i);
		
		return null;
//		System.out.println("받은 토큰" + csrf_token);
		
		
		
		
		
//		String CSRF_TOKEN=(String)session.getAttribute("CSRF_TOKEN");
//
//		if(CSRF_TOKEN!=null && CSRF_TOKEN.equals(csrf_token)) {
//			if(board.getFile().getSize() == 0) {
//				System.out.println("파일 없음");
//			}else {
//				System.out.println("파일 명 : " + board.getFile().getOriginalFilename());
//				board.setFile_name(board.getFile().getOriginalFilename());
//			}
//			
//			long cnt = boardService.write(board);
//			if(cnt > 0) {
//				try {
//					if(board.getFile().getSize() != 0) {
//						board.getFile().transferTo(new File("c:\\board\\upload\\"+board.getFile().getOriginalFilename()));
//					}
//					
//					System.out.println("등록 완료");
//					return "ok";
//				} catch (Exception e) {
//					e.printStackTrace();
//					return "fail";
//				}
//			}
//			return "fail";
//		}else {
//			System.out.println(request.getRemoteAddr()+" 해킹 시도 감지");
//			return "fail";
//		}
	}
}
