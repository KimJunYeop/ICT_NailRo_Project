package com.javalec.discount;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DisCountCouponObjMapper implements RowMapper<DisCountCouponObj>{

	@Override
	public DisCountCouponObj mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		DisCountCouponObj discount_obj = new DisCountCouponObj();
		
		discount_obj.setDis_id(rs.getInt("ID"));
		discount_obj.setDis_barcode(rs.getString("BARCODE"));
		discount_obj.setDis_owner_name(rs.getString("OWNER_NAME"));
		discount_obj.setDis_shop_addr(rs.getString("SHOP_ADDR"));
		discount_obj.setDis_shop_description(rs.getString("SHOP_DESC"));
		discount_obj.setDis_shop_name(rs.getString("SHOP_NAME"));
		discount_obj.setDis_shop_photo(rs.getString("SHOP_PHOTO"));
		discount_obj.setDis_type(rs.getString("DIS_TYPE"));
		
		return discount_obj;
	}

}
