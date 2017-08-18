package com.javalec.discount;

public class DisCountCouponObj {
	int dis_id;
	String dis_owner_name;
	String dis_shop_name;
	String dis_shop_addr;
	String dis_shop_photo;
	String dis_shop_description;
	String dis_barcode;
	String dis_type;

	public DisCountCouponObj() {

	}

	public DisCountCouponObj(String dis_owner_name, String dis_shop_name, String dis_shop_addr,
			String dis_shop_description, String dis_shop_photo, String dis_barcode, String dis_type) {
		this.dis_owner_name = dis_owner_name;
		this.dis_shop_name = dis_shop_name;
		this.dis_shop_addr = dis_shop_addr;
		this.dis_shop_description = dis_shop_description;
		this.dis_shop_photo = dis_shop_photo;
		this.dis_barcode = dis_barcode;
		this.dis_type = dis_type;
	}

	public String getDis_type() {
		return dis_type;
	}

	public void setDis_type(String dis_type) {
		this.dis_type = dis_type;
	}

	public int getDis_id() {
		return dis_id;
	}

	public void setDis_id(int dis_id) {
		this.dis_id = dis_id;
	}

	public String getDis_shop_description() {
		return dis_shop_description;
	}

	public void setDis_shop_description(String dis_shop_description) {
		this.dis_shop_description = dis_shop_description;
	}

	public String getDis_owner_name() {
		return dis_owner_name;
	}

	public void setDis_owner_name(String dis_owner_name) {
		this.dis_owner_name = dis_owner_name;
	}

	public String getDis_shop_name() {
		return dis_shop_name;
	}

	public void setDis_shop_name(String dis_shop_name) {
		this.dis_shop_name = dis_shop_name;
	}

	public String getDis_shop_addr() {
		return dis_shop_addr;
	}

	public void setDis_shop_addr(String dis_shop_addr) {
		this.dis_shop_addr = dis_shop_addr;
	}

	public String getDis_shop_photo() {
		return dis_shop_photo;
	}

	public void setDis_shop_photo(String dis_shop_photo) {
		this.dis_shop_photo = dis_shop_photo;
	}

	public String getDis_barcode() {
		return dis_barcode;
	}

	public void setDis_barcode(String dis_barcode) {
		this.dis_barcode = dis_barcode;
	}

}
