package com.ssafy.happyhouse.member.dto;

public class SecVO {
	
    private String userId;
    private String salt;
    private String secKey;

    
    public SecVO() {}
	public SecVO(String userId, String salt, String secKey) {
		super();
		setUserId(userId);
		setSalt(salt);
		setSecKey(secKey);
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		if(userId != null  && !userId.isEmpty()) {
	        this.userId = userId;
	    }else {
	        throw new IllegalArgumentException();
	    }
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		if(salt != null  && !salt.isEmpty()) {
	        this.salt = salt;
	    }else {
	        throw new IllegalArgumentException();
	    }
	}
	public String getSecKey() {
		return secKey;
	}
	public void setSecKey(String secKey) {
		if(secKey != null  && !secKey.isEmpty()) {
	        this.secKey = secKey;
	    }else {
	        throw new IllegalArgumentException();
	    }
	}
	
	@Override
	public String toString() {
		return "SecVO [userId=" + userId + ", salt=" + salt + ", secKey=" + secKey + "]";
	}
	
	
	
}
