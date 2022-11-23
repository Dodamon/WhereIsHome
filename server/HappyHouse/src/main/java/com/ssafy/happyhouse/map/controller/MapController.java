package com.ssafy.happyhouse.map.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ssafy.happyhouse.capcha.ApiExamCaptchaImage;
import com.ssafy.happyhouse.capcha.ApiExamCaptchaNkey;
import com.ssafy.happyhouse.map.dto.Dongcode;
import com.ssafy.happyhouse.map.dto.Housedeal;
import com.ssafy.happyhouse.map.dto.Houseinfo;
import com.ssafy.happyhouse.map.dto.Infomation;
import com.ssafy.happyhouse.map.dto.Site_gathering;
import com.ssafy.happyhouse.map.service.MapService;
import com.ssafy.happyhouse.member.dto.Member;



@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/map")
public class MapController {
	@Autowired
	MapService mapService;
	
	@Autowired ApiExamCaptchaNkey apiExamCaptchaNkey;
	@Autowired
	 ApiExamCaptchaImage apiExamCaptchaImage;

	
	@GetMapping("/sido")
	@ResponseBody
	public PageInfo<Dongcode> getSido(HttpServletRequest request) {
//		System.out.println("sido 진입");
		
		//Cookie[] cookies = request.getCookies();
		
//		for(Cookie c: cookies) {
//			//System.out.println(c.getValue());
//		}
		
//		HttpSession s = request.getSession();
		//System.out.println("session : " + s.getId());
		
		//System.out.println(Arrays.toString(cookies));
		
		
		List<Dongcode> list = mapService.getSido();
		
		//System.out.println("DB에서 가져온 시도 갯수 : " + list.size());
		
		
		return PageInfo.of(list);
	}
	
	@GetMapping("/gugun")
	@ResponseBody
	public PageInfo<Dongcode> getGugun(HttpServletRequest request) {
//		System.out.println("gugun 진입");
		String sidoName = request.getParameter("sidoName"); 
		 
//		System.out.println(sidoName);
		List<Dongcode> list = mapService.getGugun(sidoName);
		System.out.println("DB에서 가져온 구군이 갯수 : " + list.size());
//		System.out.println("DB에서 가져온 구군이름 첫번째 : " + list.get(1));
		
		
		return PageInfo.of(list);
	}
	
	@GetMapping("/dong")
	@ResponseBody
	public PageInfo<Dongcode> getDong(HttpServletRequest request) {
//		System.out.println("gugun 진입");
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		
		 
//		System.out.println(sidoName);
		List<Dongcode> list = mapService.getDong(sidoName, gugunName);
		System.out.println("DB에서 가져온 동 갯수 : " + list.size());
//		System.out.println("DB에서 가져온 구군이름 첫번째 : " + list.get(1));
		
		
		return PageInfo.of(list);
	}
	
	@GetMapping("/houseinfo")
	@ResponseBody
	public Infomation getHouseinfo(HttpServletRequest request) {
		System.out.println("getHouseinfo 진입");
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		String dongcode = mapService.getDongcode(sidoName, gugunName, dongName);
		 
		System.out.println("DB에서 가져온 동코드 : " + dongcode);
		
		List<Houseinfo> list = mapService.getHouseinfo(dongcode);
		List<Site_gathering>listg = mapService.getGatheringinfo(sidoName, gugunName, dongName);
		System.out.println("DB에서 가져온 아파트 갯수 : " + list.size());
		System.out.println("DB에서 가져온 모임 갯수 : " + listg.size());
//		System.out.println("DB에서 가져온 구군이름 첫번째 : " + list.get(1));
		
		return new Infomation(list, listg);
	}
	
	@GetMapping("/housedeal")
	@ResponseBody
	public PageInfo<Housedeal> getHousedeal(HttpServletRequest request) {
		System.out.println("getHouseinfo 진입");
		String aptCode = request.getParameter("aptCode");
		
		List<Housedeal> list = mapService.getHousedeal(aptCode);
		 
		System.out.println("DB에서 가져온 아파트 갯수 : " + list.size());
//		System.out.println("DB에서 가져온 구군이름 첫번째 : " + list.get(1));
		
		return PageInfo.of(list);
	}
	
	@PostMapping("/writeImjang")
	@ResponseBody
	public long writeImjang(HttpServletRequest request) throws ParseException {
		System.out.println("writeImjang 진입");
		String title = request.getParameter("title");
		int min_people = Integer.parseInt(request.getParameter("min_people"));
		int max_people = Integer.parseInt(request.getParameter("max_people"));
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String date_string = request.getParameter("date");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(date_string);
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String dong = request.getParameter("dong");
		System.out.println(sido + " " + gugun + " " + dong );
		
		Site_gathering s = new Site_gathering(0, title, latitude, longitude, date, max_people, min_people, 0, sido, gugun, dong);
		
		long i = mapService.writeImjang(s);
		
		HttpSession session = request.getSession(false);
		Member m = (Member) session.getAttribute("member");
		System.out.println(m);
		
		i = mapService.joinImjang(m.getCode(), s.getCode(), 1);
		 
//		System.out.println("DB에서 가져온 아파트 갯수 : " + list.size());
//		System.out.println("DB에서 가져온 구군이름 첫번째 : " + list.get(1));
		
		return i;
	}
	
	@PostMapping("/enrolledGathering")
	@ResponseBody
	public int getEnrolledGathering(HttpServletRequest request) {
		System.out.println("getEnrolledGathering 진입");
		HttpSession session = request.getSession(false);
		
		Member member = (Member) session.getAttribute("member");
		int user_code = member.getCode();
		
		int site_gathering_code = Integer.parseInt(request.getParameter("site_gathering_code"));
		
		System.out.println(user_code + "---------" + site_gathering_code);
		int result = mapService.getEnrolledGathering(user_code, site_gathering_code);
		
		System.out.println("result : " + result);
		
		return result;
	}
	
	@PostMapping("/joinImjang")
	@ResponseBody
	public long joinImjang(HttpServletRequest request) {
		System.out.println("joinGathering 진입");
		
		int duplicated = getEnrolledGathering(request);
		System.out.println("duplicate = " + duplicated);
		
		if(duplicated > 0) {
			return -1;
		}else {
			HttpSession session = request.getSession(false);
			Member member = (Member) session.getAttribute("member");
			int user_code = member.getCode();
			
			int site_gathering_code = Integer.parseInt(request.getParameter("site_gathering_code"));
			
			long i = mapService.joinImjang(user_code, site_gathering_code, 0);
			
			mapService.updateCount(site_gathering_code);
			
			return i;
		}
		
//		int user_code = Integer.parseInt(request.getParameter("user_code"));
//		int site_gathering_code = Integer.parseInt(request.getParameter("site_gathering_code"));
//		
//		System.out.println(user_code + "---------" + site_gathering_code);
//		int result = mapService.getEnrolledGathering(user_code, site_gathering_code);
//		
//		System.out.println("result : " + result);
//		
//		return result;
	}
	
	@GetMapping("/capcha")
	@ResponseBody
	public void  capcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("capcha controller");
		
//		ApiExamCaptchaNkey apiExamCaptchaNkey = new ApiExamCaptchaNkey();
		
		String result = apiExamCaptchaNkey.ApiExamCaptchaNkey_main();
		
		String file_name = apiExamCaptchaImage.getFile_name();
		
		System.out.println("파일 이름 " + file_name);
		
		//String path = "C:/Users/multicampus/git/1123/pair08_leeyeeun_jisunho/server/HappyHouse/";
		System.out.println("context ::" + request.getContextPath());
	    
	    byte[] fileByte = FileUtils.readFileToByteArray(apiExamCaptchaImage.getFile());

//	    response.setContentType("application/octet-stream");
//	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("tistory.png", "UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");

	    response.getOutputStream().write(fileByte);
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
		
//		try {
//            String path = "C:/Users/j/Desktop/capcha/HappyHouse/";
//            FileSystemResource resource = new FileSystemResource(path+file_name + ".jpg");
//            if (!resource.exists()) {
//                throw new Exception();
//            }
//            HttpHeaders header = new HttpHeaders();
//            Path filePath = null;
//            filePath = Paths.get(path+file_name);
//            header.add("Content-Type", Files.probeContentType(filePath));
//            
//            File f = apiExamCaptchaImage.getFile();
//            
//            response.setHeader("Content-Length", Long.toString(f.length()));
//            response.setHeader("Content-Transfer-Encoding", "binary");
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + "\";");
//            return f;
            
            
            
            
//            response.setContentType("application/octet-stream");
//
//			response.setHeader("Content-Length", Long.toString(f.length()));
//			
//			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("download", "UTF-8") + ";");
//			
//			response.setHeader("Content-Transfer-Encoding", "binary");
//			
//			response.setHeader("Content-Type", "application/octet-stream");
//			
//			response.setHeader("filename", URLEncoder.encode(originalName, "UTF-8"));
//			
//			response.getOutputStream().write(fileByte);
//			
//			response.getOutputStream().flush();
//			
//			response.getOutputStream().close();
            
//            return new ResponseEntity<Resource>(resource, HttpStatus.OK);
//        } catch (Exception e) {
//            throw new Exception();
//        }

//		return result;
	}

}
