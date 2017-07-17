package com.javalec.object;

public class DiscountCoupon {

	String serialNum;	//쿠폰의 일련번호 혹은 바코드 넘버를 나타내기 위한 변수입니다.
	int area;			//지역을 각각 정수로 나타내어 표시하기 위한 용도입니다.
	//향후 이미지 추가
	public String getSerialNum(){
		return this.serialNum;
	}
	public void setSerialNum(String serialNum){
		this.serialNum = serialNum;
	}
	public int getArea(){
		return this.area;
	}
	public void setArea(int area){
		this.area = area;
	}
}
