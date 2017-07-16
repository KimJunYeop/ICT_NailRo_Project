package com.javalec.Response;
import com.javalec.object.DiscountCoupon;

public class ResDiscountCoupon {
	//쿠폰 객체를 관리하기위한 컨테이너 클래스입니다. 향후 DB에서 등록된 쿠폰의 정보를 입력하여야 합니다. 
	DiscountCoupon discountCoupon;	//서버의 응답으로 보내줄 쿠폰 객체입니다.
	
	public ResDiscountCoupon(){
		//2017-07-16 임시로 쿠폰의 멤버변수를 초기화합니다. 향후 DB에서 쿠폰의 정보를 받아와야 합니다.
		this.discountCoupon.setSerialNum("123-456-789");
		this.discountCoupon.setArea(8);
	}
	
	public DiscountCoupon getDiscountCoupon(){
		return this.discountCoupon;
	}
	public void setDiscountCoupon(DiscountCoupon discountCoupon){
		this.discountCoupon = discountCoupon;
	}
}
