package com.ssafy.happyhouse.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.ssafy.happyhouse.board.dto.Board;
import com.ssafy.happyhouse.board.dto.Dongcode;
import com.ssafy.happyhouse.board.dto.Housedeal;
import com.ssafy.happyhouse.board.dto.Houseinfo;

@Mapper
public interface BoardMapper {
	
	public List<Board> selectAll() throws DataAccessException;

	public long write(Board board);
	
	public Board selectOne(String code);

	public void modify(String code, String title, String writer, String content);

	public void delete(String code);

	public List<Dongcode> getSido();

	public List<Dongcode> getGugun(String selected_sido);

	public List<Dongcode> getDong(String sidoName, String gugunName);

	public String getDongcode(String sidoName, String gugunName, String dongName);

	public List<Houseinfo> getHouseinfo(String dongcode);

	public List<Housedeal> getHousedeal(String aptCode);

}