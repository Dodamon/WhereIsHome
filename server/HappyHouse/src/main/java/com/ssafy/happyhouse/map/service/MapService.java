package com.ssafy.happyhouse.map.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.map.dto.Dongcode;
import com.ssafy.happyhouse.map.dto.Housedeal;
import com.ssafy.happyhouse.map.dto.Houseinfo;
import com.ssafy.happyhouse.map.dto.Site_gathering;
import com.ssafy.happyhouse.map.mapper.MapMapper;


@Service
public class MapService {
	
	@Autowired
	MapMapper mapMapper;
	
	public List<Dongcode> getSido() {
		return mapMapper.getSido();
	}

	public List<Dongcode> getGugun(String selected_sido) {
		return mapMapper.getGugun(selected_sido);
	}

	public List<Dongcode> getDong(String sidoName, String gugunName) {
		return mapMapper.getDong(sidoName, gugunName);
	}

	public String getDongcode(String sidoName, String gugunName, String dongName) {
		return mapMapper.getDongcode(sidoName, gugunName, dongName);
	}

	public List<Houseinfo> getHouseinfo(String dongcode) {
		return mapMapper.getHouseinfo(dongcode);
	}

	public List<Housedeal> getHousedeal(String aptCode) {
		return mapMapper.getHousedeal(aptCode);
	}

	public long writeImjang(Site_gathering site_gathering) {
		return mapMapper.writeImjang(site_gathering);
	}

	public List<Site_gathering> getGatheringinfo(String sidoName, String gugunName, String dongName) {
		return mapMapper.getGatheringinfo(sidoName, gugunName, dongName);
	}

}
