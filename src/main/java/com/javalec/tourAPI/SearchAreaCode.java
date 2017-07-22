package com.javalec.tourAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class SearchAreaCode {
	DataSource dataSource;
	
	public SearchAreaCode(){
		
	}
	
	public ArrayList<String> get(String areaName) throws SQLException{
		/*
		 *  DB에 접속해서 AREACODE 테이블에서 코드정보를 가져옴
		 * */
		
		ArrayList<String> result = new ArrayList<String>();		
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM AREACODE WHERE AREANAME = ?");
		ps.setString(1, areaName);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		//tourAPI에 요청할 때 필요한 areaCode와 sigunguCode를 저장
		result.add(rs.getString("areacode"));
		result.add(rs.getString("sigungucode"));
		
		rs.close();
		ps.close();
		c.close();
		
		return result;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
