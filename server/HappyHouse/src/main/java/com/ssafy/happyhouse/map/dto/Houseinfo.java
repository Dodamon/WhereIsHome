package com.ssafy.happyhouse.map.dto;

public class Houseinfo {
	
	private long aptCode;
	private int buildYear;
	private String roadName, roadNameBonbun, roadNameBubun,	roadNameSeq,roadNameBasementCode, roadNameCode, 
					dong, bonbun, bubun, sigunguCode, eubmyundongCode, dongCodelandCode, apartmentName, jibun, lng, lat;
	
	
	
	public Houseinfo(long aptCode, int buildYear, String roadName, String roadNameBonbun, String roadNameBubun,
			String roadNameSeq, String roadNameBasementCode, String roadNameCode, String dong, String bonbun,
			String bubun, String sigunguCode, String eubmyundongCode, String dongCodelandCode, String apartmentName,
			String jibun, String lng, String lat) {
		super();
		this.aptCode = aptCode;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.roadNameBonbun = roadNameBonbun;
		this.roadNameBubun = roadNameBubun;
		this.roadNameSeq = roadNameSeq;
		this.roadNameBasementCode = roadNameBasementCode;
		this.roadNameCode = roadNameCode;
		this.dong = dong;
		this.bonbun = bonbun;
		this.bubun = bubun;
		this.sigunguCode = sigunguCode;
		this.eubmyundongCode = eubmyundongCode;
		this.dongCodelandCode = dongCodelandCode;
		this.apartmentName = apartmentName;
		this.jibun = jibun;
		this.lng = lng;
		this.lat = lat;
	}
	public Houseinfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}
	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}
	public String getRoadNameBubun() {
		return roadNameBubun;
	}
	public void setRoadNameBubun(String roadNameBubun) {
		this.roadNameBubun = roadNameBubun;
	}
	public String getRoadNameSeq() {
		return roadNameSeq;
	}
	public void setRoadNameSeq(String roadNameSeq) {
		this.roadNameSeq = roadNameSeq;
	}
	public String getRoadNameBasementCode() {
		return roadNameBasementCode;
	}
	public void setRoadNameBasementCode(String roadNameBasementCode) {
		this.roadNameBasementCode = roadNameBasementCode;
	}
	public String getRoadNameCode() {
		return roadNameCode;
	}
	public void setRoadNameCode(String roadNameCode) {
		this.roadNameCode = roadNameCode;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getBonbun() {
		return bonbun;
	}
	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}
	public String getBubun() {
		return bubun;
	}
	public void setBubun(String bubun) {
		this.bubun = bubun;
	}
	public String getSigunguCode() {
		return sigunguCode;
	}
	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}
	public String getEubmyundongCode() {
		return eubmyundongCode;
	}
	public void setEubmyundongCode(String eubmyundongCode) {
		this.eubmyundongCode = eubmyundongCode;
	}
	public String getDongCodelandCode() {
		return dongCodelandCode;
	}
	public void setDongCodelandCode(String dongCodelandCode) {
		this.dongCodelandCode = dongCodelandCode;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "Houseinfo [aptCode=" + aptCode + ", buildYear=" + buildYear + ", roadName=" + roadName
				+ ", roadNameBonbun=" + roadNameBonbun + ", roadNameBubun=" + roadNameBubun + ", roadNameSeq="
				+ roadNameSeq + ", roadNameBasementCode=" + roadNameBasementCode + ", roadNameCode=" + roadNameCode
				+ ", dong=" + dong + ", bonbun=" + bonbun + ", bubun=" + bubun + ", sigunguCode=" + sigunguCode
				+ ", eubmyundongCode=" + eubmyundongCode + ", dongCodelandCode=" + dongCodelandCode + ", apartmentName="
				+ apartmentName + ", jibun=" + jibun + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
	
	

}
