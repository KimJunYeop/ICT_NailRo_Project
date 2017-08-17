package com.javalec.discount;
import com.javalec.object.DiscountCoupon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
public class InitDiscountCoupon {
	DataSource dataSource;
	DiscountCoupon discountCoupon;
	
	public DiscountCoupon getDiscountCoupon() {
		return discountCoupon;
	}

	public void setDiscountCoupon(DiscountCoupon discountCoupon) {
		this.discountCoupon = discountCoupon;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void initCouponData(String market_name, String serialNum, String area) throws SQLException {
		if(this.discountCoupon == null) 
			this.discountCoupon = new DiscountCoupon();
		
		this.discountCoupon.setMarket_name(market_name);
		this.discountCoupon.setSerialNum(serialNum);
		this.discountCoupon.setArea(area);
		this.discountCoupon.setPhotoURL("http://t1.daumcdn.net/thumb/R600x0/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fqna%2Fimage%2F50fd991d7f9c588cf25f08f1a47231178ba1e495");
		
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("insert into response_discount_coupon(serialnum, photourl, issuedate, area, market_name) values(?, ?, SYSDATE, ?, ?)");
		ps.setString(1, this.discountCoupon.getSerialNum());
		ps.setString(2, this.discountCoupon.getPhotoURL());
		ps.setString(3, this.discountCoupon.getArea());
		ps.setString(4, this.discountCoupon.getMarket_name());
		
		ps.executeUpdate();
		
		if(ps != null) ps.close();
		if(c != null) c.close();
		}
	
}
