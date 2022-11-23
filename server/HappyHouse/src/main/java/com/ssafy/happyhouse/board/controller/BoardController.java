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
import com.ssafy.happyhouse.member.dto.Member;
import com.ssafy.happyhouse.member.dto.MySecured;
import com.ssafy.happyhouse.member.dto.Role;

@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080"})
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@MySecured(role = Role.USER)
	@PostMapping("/deleteArticle")
	@ResponseBody
	public void deleteArticle(@RequestParam String code, HttpServletRequest request){	
		boardService.delete(code);
		
	}
	
	@MySecured(role = Role.USER)
	@PostMapping("/modifyArticle")
	@ResponseBody
	public void modifyArticle(@RequestParam String code, String title, String content, String writer, HttpServletRequest request){	
		System.out.println(code +  title+ writer+ content);
		
		boardService.modify(code, title, writer, content);
		
	}
	
	@MySecured(role = Role.USER)
	@PostMapping("/clickArticle")
	@ResponseBody
	public Board clickArticle(@RequestParam String code, HttpServletRequest request){	
		Board board = boardService.selectOne(code);
		
		return board;
		
	}
	
	@MySecured(role = Role.USER)
	@PostMapping("/selectAll")
	@ResponseBody
	public PageInfo<Board> selectAll(HttpServletRequest request) {
//		System.out.println("selectAll session : " + request.getSession().getId());
		System.out.println("page no = "+request.getParameter("pageNum"));
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		System.out.println(pageNum);
		System.out.println(pageSize);
		
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		List<Board> list=boardService.selectAll();
		
		return PageInfo.of(list);
	}
		
	@MySecured(role = Role.USER)
	@PostMapping("/write")
	@ResponseBody
	public String writeBoard(HttpSession session, HttpServletRequest request) {
//		System.out.println(board);
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println(writer);
		System.out.println(title);
		
		if(session!=null) {
			session = request.getSession();
			System.out.println(session);
		
			Member m = (Member) session.getAttribute("member");
			System.out.println(m);
		
			if((m.getId()).equals(writer)) {
				Board board = new Board(1, m.getCode(), title, content, writer, new Date());
				long i = boardService.write(board);
				System.out.println(i);
				if(i > 0) {
					return "ok";
				}
			}
		} 
		return "fail";
	}
	
	@MySecured(role = Role.USER)
	@GetMapping("/getSize")
    @ResponseBody
    public int getSize(HttpServletRequest request) {
        int len = boardService.getSize();

        return len;
    }
}
