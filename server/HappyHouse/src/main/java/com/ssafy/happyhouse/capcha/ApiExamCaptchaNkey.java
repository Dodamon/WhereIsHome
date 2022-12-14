package com.ssafy.happyhouse.capcha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// 네이버 캡차 API 예제 - 키발급
@Component
public class ApiExamCaptchaNkey {
	
	public static String key;
	
	

//    public static void main(String[] args) throws ParseException {
//    	
//    	
//    }
	
	public static String getKey() {
		return key;
	}

	@Autowired
	static ApiExamCaptchaImage apiExamCaptchaImage;
    
    public static String ApiExamCaptchaNkey_main() throws ParseException {
    	String key = getKey_or_image("0");
    	
    	
    	
    	System.out.println("key : " + key);
    	
//    	ApiExamCaptchaImage apiExamCaptchaImage = new ApiExamCaptchaImage();
    	
    	String responsebody = apiExamCaptchaImage.getImage(key);
    	
    	System.out.println("결과 : " + responsebody);
    	return responsebody;
    }
    
    
    
    private static String getKey_or_image(String code) throws ParseException {
    	String clientId = "oEeHZNIsPZi_8lQPEQ6G"; //애플리케이션 클라이언트 아이디값";
        String clientSecret = "QRhBraRXBg"; //애플리케이션 클라이언트 시크릿값";

//        String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);
//        JSONParser jsonParser = new JSONParser();
        JSONParser jsonParser = new JSONParser();
        
        //3. To Object
        Object obj = jsonParser.parse(responseBody);
        
        //4. To JsonObject
        JSONObject jsonObj = (JSONObject) obj;
        
        responseBody = (String) jsonObj.get("key");
        
        key = responseBody;
        
        //print
        System.out.println("변경 후 : " + responseBody); //sim
      
        
        return responseBody;
    }
    

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}