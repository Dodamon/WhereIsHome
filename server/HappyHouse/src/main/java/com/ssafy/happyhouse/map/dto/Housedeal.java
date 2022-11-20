package com.ssafy.happyhouse.map.dto;

public class Housedeal {
	private long no, aptCode;
	private int dealYear, dealMonth, dealDay;
	private String dealAmount, area, floor, cancelDealType;
	
	public Housedeal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Housedeal(long no, long aptCode, int dealYear, int dealMonth, int dealDay, String dealAmount, String area,
			String floor, String cancelDealType) {
		super();
		this.no = no;
		this.aptCode = aptCode;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.dealAmount = dealAmount;
		this.area = area;
		this.floor = floor;
		this.cancelDealType = cancelDealType;
	}
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getCancelDealType() {
		return cancelDealType;
	}
	public void setCancelDealType(String cancelDealType) {
		this.cancelDealType = cancelDealType;
	}
	
	@Override
	public String toString() {
		return "Housedeal [no=" + no + ", aptCode=" + aptCode + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth
				+ ", dealDay=" + dealDay + ", dealAmount=" + dealAmount + ", area=" + area + ", floor=" + floor
				+ ", cancelDealType=" + cancelDealType + "]";
	}
	
	

}
