package com.javalec.object;

public class ManualContactUser {
	String user_name;
	String user_message;
	String user_phone;
	String user_email;

	public ManualContactUser(String user_name,String user_email,String user_phone, String user_message){
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_message = user_message;
		
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_message() {
		return user_message;
	}

	public void setUser_message(String user_message) {
		this.user_message = user_message;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

}
