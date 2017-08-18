package com.javalec.discount;

import java.io.File;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.javalec.object.ManualContactUser;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class DisCountCoupon {
	DataSource dataSource;
	DisCountCouponObj disCountCouponObj;
	JdbcTemplate jdbcTemplate;
	
	public DisCountCoupon(){
	}

	public DisCountCouponObj getDisCountCouponeObj() {
		return disCountCouponObj;
	}

	public void setDisCountCouponeObj(DisCountCouponObj disCountCouponObj) {
		this.disCountCouponObj = disCountCouponObj;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public String createBarcode() throws BarcodeException, OutputException {
		Random generator = new Random();
		int random_num;
		random_num = generator.nextInt(10000000) + 1000000;
		
		return Integer.toString(random_num);
	}
	
	/*
	 * 할인쿠폰을 가져오는 메소드.
	 * random하게 가져와서 뿌려준다.
	 */
	public DisCountCouponObj getDisCountCoupon(){
		//갯수랑 숫자 맞춰줘야한다. 
		Random random = new Random();
		String sql_count = "SELECT COUNT(*) FROM DISCOUNT_COUPON";
		Integer row_number = this.jdbcTemplate.queryForObject(sql_count,Integer.class);
		int random_num = random.nextInt(row_number)+1;
		
		String sql_get_row = "SELECT * FROM DISCOUNT_COUPON WHERE ID = ?";
		DisCountCouponObj obj = this.jdbcTemplate.queryForObject(sql_get_row,new Object[]{random_num},new DisCountCouponObjMapper());
		
		return obj;
	}

	public void insertDisCountCoupon(DisCountCouponObj disCountCouponObj) {
		this.jdbcTemplate.update(
				"INSERT INTO DISCOUNT_COUPON(ID,SHOP_NAME,OWNER_NAME,SHOP_ADDR,SHOP_DESC,SHOP_PHOTO,BARCODE,DIS_COUNT,DIS_TYPE) VALUES (DIS_ID.NEXTVAL,?,?,?,?,?,?,?,?)",
				disCountCouponObj.getDis_shop_name(), disCountCouponObj.getDis_owner_name(),
				disCountCouponObj.getDis_shop_addr(), disCountCouponObj.getDis_shop_description(),
				disCountCouponObj.getDis_shop_photo(), disCountCouponObj.getDis_barcode(),100, disCountCouponObj.getDis_type());
	}
}
