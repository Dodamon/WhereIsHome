package com.ssafy.happyhouse.board.controller;

import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.happyhouse.board.dto.Board;
import com.ssafy.happyhouse.board.dto.Dongcode;
import com.ssafy.happyhouse.board.service.BoardService;


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
	public void modifyArticle(@RequestParam String code, String title, String content, HttpServletRequest request){	
		 boardService.modify(code, title, content);
		
	}
	
	@PostMapping("/clickArticle")
	@ResponseBody
	public Board clickArticle(@RequestParam String code, HttpServletRequest request){	
		Board board = boardService.selectOne(code);
		
		return board;
		
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/selectAll")
	@ResponseBody
	public PageInfo<Board> selectAll(HttpServletRequest request) {
		System.out.println("page no = "+request.getParameter("pageNum"));
		PageHelper.startPage(request);
		List<Board> list=boardService.selectAll();
		
		return PageInfo.of(list);
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/sido")
	@ResponseBody
	public PageInfo<Dongcode> getSido(HttpSession session) {
		System.out.println("sido 진입");
		
		List<Dongcode> list = boardService.getSido();
		
		System.out.println(list.get(1));
		
		
		return PageInfo.of(list);
	}

	

	@GetMapping("/write")
	public String writeBoard(HttpSession session) {
		// CSRF token 발행
		String csrfToken = UUID.randomUUID().toString();
		System.out.println(csrfToken);
		session.setAttribute("CSRF_TOKEN", csrfToken);
		
		return "boardWrite";
	}
	
		
	
	@PostMapping("/write")
	@ResponseBody
	public String writeBoard(Board board, HttpSession session, HttpServletRequest request) {
		System.out.println(board);
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
