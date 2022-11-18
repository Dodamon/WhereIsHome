package com.ssafy.happyhouse.board.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


public class Board{
	
	private int code, user_code;
	private String title, content, writer;
	private Date reg_datetime;
	
	
	
	public Board(int code, int user_code, String title, String content, String writer, Date reg_datetime) {
		super();
		this.code = code;
		this.user_code = user_code;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.reg_datetime = reg_datetime;
	}

	public Board() {
		super();
		// TODO Auto-generated constructor stub 
	}

	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	public int getUser_code() {
		return user_code;
	}
	public void setUser_code(int user_code) {
		this.user_code = user_code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getReg_datetime() {
		return reg_datetime;
	}
	public void setReg_datetime(Date reg_datetime) {
		this.reg_datetime = reg_datetime;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private int code,originNo,groupOrd,groupLayer;
//	private String title,content,writer, file_name;
//	private Date reg_datetime;
//	private MultipartFile file;
	
	
	
//	public Board() {
//		super();
//	}
//	
//	public int getCode() {
//		return code;
//	}
//	public void setCode(int code) {
//		this.code = code;
//	}
//	public int getOriginNo() {
//		return originNo;
//	}
//	public void setOriginNo(int originNo) {
//		this.originNo = originNo;
//	}
//	public int getGroupOrd() {
//		return groupOrd;
//	}
//	public void setGroupOrd(int groupOrd) {
//		this.groupOrd = groupOrd;
//	}
//	public int getGroupLayer() {
//		return groupLayer;
//	}
//	public void setGroupLayer(int groupLayer) {
//		this.groupLayer = groupLayer;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		if(title != null)
//			this.title = title;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		if(content != null)
//			this.content = content;
//	}
//	public String getWriter() {
//		return writer;
//	}
//	public void setWriter(String writer) {
//		if(writer != null)
//			this.writer = writer;
//	}
//	public String getFile_name() {
//		return file_name;
//	}
//	public void setFile_name(String file_name) {
//		if(file_name != null)
//			this.file_name = file_name;
//	}
//	public Date getReg_datetime() {
//		return reg_datetime;
//	}
//	public void setReg_datetime(Date reg_datetime) {
//		if(reg_datetime != null)
//		this.reg_datetime = reg_datetime;
//	}
//	public MultipartFile getFile() {
//		return file;
//	}
//	public void setFile(MultipartFile file) {
//		if(file != null)
//			this.file = file;
//	}
//	@Override
//	public String toString() {
//		return "Board [code=" + code + ", originNo=" + originNo + ", groupOrd=" + groupOrd + ", groupLayer="
//				+ groupLayer + ", title=" + title + ", content=" + content + ", writer=" + writer + ", file_name="
//				+ file_name + ", reg_datetime=" + reg_datetime + ", file=" + file + "]";
//	}
}