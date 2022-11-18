package com.ssafy.happyhouse.member.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.member.dto.Member;
import com.ssafy.happyhouse.member.dto.SecVO;

@Mapper
public interface MemberMapper {

	public Member login(Map<String, String> map) throws Exception;
	public int register(Member member) throws Exception;
	public Member userinfo(Member member) throws Exception;
	public int update(Map<String, String> map) throws Exception;
	public void delete(String id)throws Exception;
	public SecVO selectSecurity(String id);
	public void insertSecurity(SecVO secVo);
	public void deleteSec(String id);
	public int checkId(String id)throws Exception;;
}