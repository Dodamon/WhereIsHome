package com.ssafy.happyhouse.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.ssafy.happyhouse.board.dto.Board;
import com.ssafy.happyhouse.board.dto.Dongcode;

@Mapper
public interface BoardMapper {
	
	public List<Board> selectAll() throws DataAccessException;

	public long write(Board board);
	
	public Board selectOne(String code);

	public void modify(String code, String title, String content);

	public void delete(String code);

	public List<Dongcode> getSido();

}