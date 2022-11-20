package com.ssafy.happyhouse.map.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.map.dto.Dongcode;
import com.ssafy.happyhouse.map.dto.Housedeal;
import com.ssafy.happyhouse.map.dto.Houseinfo;
import com.ssafy.happyhouse.map.dto.Site_gathering;

@Mapper
public interface MapMapper {
	
	public List<Dongcode> getSido();

	public List<Dongcode> getGugun(String selected_sido);

	public List<Dongcode> getDong(String sidoName, String gugunName);

	public String getDongcode(String sidoName, String gugunName, String dongName);

	public List<Houseinfo> getHouseinfo(String dongcode);

	public List<Housedeal> getHousedeal(String aptCode);

	public long writeImjang(Site_gathering site_gathering);

}
