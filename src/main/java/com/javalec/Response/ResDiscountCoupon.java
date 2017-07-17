package com.javalec.Response;
import com.javalec.object.DiscountCoupon;

public class ResDiscountCoupon {
	//쿠폰 객체를 관리하기위한 컨테이너 클래스입니다. 향후 DB에서 등록된 쿠폰의 정보를 입력하여야 합니다. 
	DiscountCoupon discountCoupon;	//서버의 응답으로 보내줄 쿠폰 객체입니다.
	
	public ResDiscountCoupon(){
		
	}
	
	public DiscountCoupon getDiscountCoupon(){
		/*2017-07-17 이 부분에서 DB에서 가져온 데이터를 초기화 해주세요*/
		return this.discountCoupon;
	}
	public void setDiscountCoupon(DiscountCoupon discountCoupon){
		/*2017-07-17 임의로 설정한 데이터 입니다. 후에 DB에서 가져올 경우 함수 또는 코드를 지워주세요*/
		this.discountCoupon = discountCoupon;
		this.discountCoupon.setSerialNum("123-456-789");
		this.discountCoupon.setArea(8);
	}
}
