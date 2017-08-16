package com.javalec.manual;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.javalec.object.ManualContactUser;

public class ManualContactMessage {
	DataSource dataSource;
	ManualContactUser manualContactUser;
	JdbcTemplate jdbcTemplate;
	
	public ManualContactUser getManualContactUser() {
		return manualContactUser;
	}

	public void setManualContactUser(ManualContactUser manualContactUser) {
		this.manualContactUser = manualContactUser;
	}

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}
	
	/*
	 * ContactMessage 를 저장한다.
	 * Increment sql 구문.
	 * CREATE SEQUENCE INCREMENT_ID START WITH 1 INCREMENT BY 1 MAXVALUE 10000 CYCLE NOCACHE;
	 */
	public void insertContactMessage(ManualContactUser manualContactUser){
		this.jdbcTemplate.update("INSERT INTO CONTACT_MESSAGE(ID,USER_NAME,USER_EMAIL,USER_PHONE,USER_MESSAGE) VALUES (INCREMENT_ID.NEXTVAL,?,?,?,?)",
				manualContactUser.getUser_name(),manualContactUser.getUser_email(),manualContactUser.getUser_phone(),manualContactUser.getUser_message());
	}
	
}
