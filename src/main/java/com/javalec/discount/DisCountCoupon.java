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
//		Random generator = new Random();
//		int random_num;
//		random_num = generator.nextInt(10000000) + 1000000;
//		String barcode_name = "";
//		barcode_name = Integer.toString(random_num);
//		
//		Barcode Makebarcode = BarcodeFactory.createCode128B(barcode_name);
//		String dirPath = "src/main/webapp/resources/barcode/";
//		String filePath = dirPath + barcode_name + ".jpg";
//		File file = new File(filePath);
//		System.out.println(file);
//
//		BarcodeImageHandler.saveJPEG(Makebarcode, file);
		Random generator = new Random();
		int random_num;
		random_num = generator.nextInt(10000000) + 1000000;
		
		return Integer.toString(random_num);
	}
	
	public void getDisCountCoupon(){
		
	}

	public void insertDisCountCoupon(DisCountCouponObj disCountCouponObj) {
		this.jdbcTemplate.update(
				"INSERT INTO DISCOUNT_COUPON(ID,SHOP_NAME,OWNER_NAME,SHOP_ADDR,SHOP_DESC,SHOP_PHOTO,BARCODE,DIS_COUNT) VALUES (DISCOUNT_ID.NEXTVAL,?,?,?,?,?,?,?)",
				disCountCouponObj.getDis_shop_name(), disCountCouponObj.getDis_owner_name(),
				disCountCouponObj.getDis_shop_addr(), disCountCouponObj.getDis_shop_description(),
				disCountCouponObj.getDis_shop_photo(), disCountCouponObj.getDis_barcode(),100);
	}
}
