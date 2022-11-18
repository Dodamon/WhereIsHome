package com.ssafy.happyhouse.member.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.member.dto.Member;
import com.ssafy.happyhouse.member.dto.SecVO;
import com.ssafy.happyhouse.member.mapper.MemberMapper;
import com.ssafy.happyhouse.util.OpenCrypt;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    public Member login(Map<String, String> map) throws Exception {
    	String id = map.get("id");
    	String pw = map.get("pw");
    	
    	SecVO sec=memberMapper.selectSecurity(id);
    	if(sec != null) {
    	
	    	String encPw = pw_to_sha(id, pw);
	    	map.put("pw", encPw);
	    	
	    	Member result = memberMapper.login(map);
	    	try {
	    		result.setName( OpenCrypt.aesDecrypt(result.getName(), OpenCrypt.hexToByteArray(sec.getSecKey()  )   ) );
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return result;
    	}
    	return null;
    }
    public int register(Member m) throws Exception {
    	byte[] key=OpenCrypt.generateKey("AES",128);
    	SecVO sec=new SecVO(m.getId(), UUID.randomUUID().toString(), OpenCrypt.byteArrayToHex(key));
    	memberMapper.insertSecurity(sec);
    	m.setName(OpenCrypt.aesEncrypt(m.getName(), key)); // 멤버 네임을 암호화, key는 vo에 저장되어 있음 (바로 위 코드 )
        m.setPw(new String(OpenCrypt.getSHA256(m.getPw(), sec.getSalt())));
    	return memberMapper.register(m);
    }
    public Member userinfo(Member m) throws Exception {
    	Member result = memberMapper.userinfo(m);
    	result.setPw("");
    	SecVO sec=memberMapper.selectSecurity(m.getId());
    	
    	result.setName(OpenCrypt.aesDecrypt(result.getName(), OpenCrypt.hexToByteArray(sec.getSecKey())));
    	return result;
    }
	public int update(Map<String, String> map) throws Exception {
		SecVO sec=memberMapper.selectSecurity(map.get("id"));
		
		
		
		map.put("pw", new String(OpenCrypt.getSHA256(map.get("pw"), sec.getSalt())));
		map.put("name", OpenCrypt.aesEncrypt(map.get("name"),OpenCrypt.hexToByteArray(sec.getSecKey())));
		
		return memberMapper.update(map);
	}
	public void delete(String id) throws Exception {
		memberMapper.delete(id);
		memberMapper.deleteSec(id);
		
	}
	
	public String pw_to_sha(String id, String pw) {
//        SecVO sec=memberDao.selectSecurity(id);
        SecVO sec=memberMapper.selectSecurity(id);
        
        String encrypted_pw = new String(OpenCrypt.getSHA256(pw, sec.getSalt()));
        
        return encrypted_pw;
    }
	
	public void insertSecurity(SecVO secVo) {
		memberMapper.insertSecurity(secVo);
	}
	public int checkId(String id) throws Exception {
		return memberMapper.checkId(id);
	}
	
}