package com.javalec.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.javalec.message.Photo;
import com.javalec.object.DiscountCoupon;

public class ResDiscountCoupon {
	/*
	 * ResDiscountCoupon클래스는 DiscountCoupon의 컨테이너 클래스로 setRegion을 통해 지역이름을 입력받으면
	 * DB에서 해당 지역과 대응되는 쿠폰을 가져옵니다.
	 * logic 1. setRegion으로 지역이름을 입력받은 경우 쿠폰 객체를 생성합니다. 이때, 초기화된 Region값으로 DB에서 문자열에 따라 구분하여 정보를 가져오도록 합니다.
	*/
	DataSource dataSource;
	String region;
	DiscountCoupon discountCoupon;
	Photo photo;
	public Photo getPhoto() {	//쿠폰의 사진을 출력하는 메소드
		this.photo = new Photo();
		photo.setUrl(this.discountCoupon.getPhotoURL());
		photo.setWidth(640);
		photo.setHeight(480);
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	String text;
	
	public String getText() {	//쿠폰에 대한 정보를 합쳐서 출력하는 메소드
		text = region + "지역의 쿠폰입니다. 해당 쿠폰을 들고 가맹점에 찾아가시면 할인혜택을 받으실 수 있어요!\n";
		text = text + "발급일자 : " + this.discountCoupon.getIssueDate() + "\n";
		text = text + "쿠폰 번호 : " + this.discountCoupon.getSerialNum() + "\n";
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ResDiscountCoupon() {
		
	}
	public DataSource getDataSource() throws SQLException {
		return dataSource;
	}
	public void setRegion(String region) throws SQLException {	//...1
		this.region = region;
		this.discountCoupon = new DiscountCoupon();
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM RESPONSE_DISCOUNT_COUPON WHERE AREA = ?");
		ps.setString(1, region);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		discountCoupon.setArea(rs.getString("area"));
		discountCoupon.setIssueDate(rs.getString("issuedate"));
		discountCoupon.setPhotoURL(rs.getString("photourl"));
		discountCoupon.setSerialNum(rs.getString("serialnum"));
		
		rs.close();
		ps.close();
		c.close();
	}
	public DiscountCoupon getDiscountCoupon() {
		return discountCoupon;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public String getRegion() {
		return region;
	}
	
	public void setDiscountCoupon(DiscountCoupon discountCoupon) {
		this.discountCoupon = discountCoupon;
	}
	
	
}
