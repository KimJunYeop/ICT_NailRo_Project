package com.javalec.object;
public class DiscountCoupon {
	String market_name;
	String serialNum;
	String photoURL;
	String issueDate;
	String area;
	public String getMarket_name() {
		return market_name;
	}
	public void setMarket_name(String market_name) {
		this.market_name = market_name;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate.substring(0, 11);
		//substring으로 문자열을 자르는 이유는 DB에 저장되어 있는 Sytdate로 인해 자바 객체 string이 초기화 되는 포맷이 2017-07-20 HH:MM:SS.S의 형태이기 때문에 
		//날짜만 가져오기 위해 문자열을 자릅니다.
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
