package com.javalec.object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class AreaCode {
	DataSource dataSource;
	
	public AreaCode(){
		
	}
	
	public ArrayList<String> search(String areaName) throws SQLException{
		/*
		 *  DB에 접속해서 AREACODE 테이블에서 코드정보를 가져옴
		 *  데이터가 존재하는 경우 해당 지역 정보를 받아오고, 
		 *  데이터가 없는 경우 areaCode=0을 반환함.
		 *  
		 * */
		
		ArrayList<String> result = new ArrayList<String>();		
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM AREACODE WHERE AREANAME = ?");
		ps.setString(1, areaName);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){
		//tourAPI에 요청할 때 필요한 areaCode와 sigunguCode를 저장
		result.add(rs.getString("areacode"));
		result.add(rs.getString("sigungucode"));
		result.add(rs.getString("stnid"));
		}
		else{
			result.add("0");
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return result;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
