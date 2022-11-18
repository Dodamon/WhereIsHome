package com.ssafy.happyhouse.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.board.dto.Board;
import com.ssafy.happyhouse.board.dto.Dongcode;
import com.ssafy.happyhouse.board.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	public List<Board> selectAll(){
		return boardMapper.selectAll();
	}

	public long write(Board board) {
		return boardMapper.write(board);
		
	}
	
	public Board selectOne(String code) {
		return boardMapper.selectOne(code);
	}

	public void modify(String code, String title, String content) {
		boardMapper.modify(code, title, content);
		
	}

	public void delete(String code) {
		boardMapper.delete(code);
		
	}

	public List<Dongcode> getSido() {
		return boardMapper.getSido();
	}

}