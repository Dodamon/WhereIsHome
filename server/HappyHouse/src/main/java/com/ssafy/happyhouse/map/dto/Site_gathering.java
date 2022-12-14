package com.ssafy.happyhouse.map.dto;

import java.util.Date;

public class Site_gathering {
	
	private int code;
	private String title, latitude, longitude;
	Date date;
	private int max_people, min_people, cur_people;
	private String sidoName, gugunName, dongName;
	public Site_gathering(int code, String title, String latitude, String longitude, Date date, int max_people,
			int min_people, int cur_people, String sidoName, String gugunName, String dongName) {
		super();
		this.code = code;
		this.title = title;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.max_people = max_people;
		this.min_people = min_people;
		this.cur_people = cur_people;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getMax_people() {
		return max_people;
	}
	public void setMax_people(int max_people) {
		this.max_people = max_people;
	}
	public int getMin_people() {
		return min_people;
	}
	public void setMin_people(int min_people) {
		this.min_people = min_people;
	}
	public int getCur_people() {
		return cur_people;
	}
	public void setCur_people(int cur_people) {
		this.cur_people = cur_people;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	@Override
	public String toString() {
		return "Site_gathering [code=" + code + ", title=" + title + ", latitude=" + latitude + ", longitude="
				+ longitude + ", date=" + date + ", max_people=" + max_people + ", min_people=" + min_people
				+ ", cur_people=" + cur_people + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName="
				+ dongName + "]";
	}

}
