package com.ssafy.happyhouse.map.dto;

import java.util.List;

public class Infomation {
	
	private List<Houseinfo> houses ;
	private List<Site_gathering> imjangs;
	public Infomation(List<Houseinfo> houses, List<Site_gathering> imjangs) {
		super();
		this.houses = houses;
		this.imjangs = imjangs;
	}
	public List<Houseinfo> getHouses() {
		return houses;
	}
	public void setHouses(List<Houseinfo> houses) {
		this.houses = houses;
	}
	public List<Site_gathering> getImjangs() {
		return imjangs;
	}
	public void setImjangs(List<Site_gathering> imjangs) {
		this.imjangs = imjangs;
	}
	@Override
	public String toString() {
		return "Infomation [houses=" + houses + ", imjangs=" + imjangs + "]";
	}

}
