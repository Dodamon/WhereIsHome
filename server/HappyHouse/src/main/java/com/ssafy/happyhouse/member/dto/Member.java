package com.ssafy.happyhouse.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class Member {
	
	private String name,id;
	private String pw;
	
	public Member(String name, String id, String pw) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
	}

	public Member(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	public Member() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null)
			this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id != null)
			this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		if(id != null)
			this.pw = pw;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", id=" + id + ", pw=" + pw + "]";
	}

}