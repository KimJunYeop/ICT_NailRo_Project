package com.javalec.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.javalec.message.Keyboard;
import com.javalec.message.Message;
import com.javalec.object.Course;

public class ResRecommendRegion {
	DataSource dataSource;
	String region;
	Course course;
	String text;
	private JdbcTemplate jdbcTemplate;

	public ResRecommendRegion() {

	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	};

	public void setRegion(String region) throws SQLException {
		this.course = getCourse(region);
		this.text = cutPath(getCourse(region).getPath());
	}

	public String cutPath(String str) {
		String[] real_path = str.split("-");
		String print_path = "";

		for (int i = 0; i < real_path.length; i++) {
			if (i == real_path.length - 1) {
				print_path += real_path[i];
			} else {
				print_path += real_path[i] + "\n" + "▽" + "\n";
			}
		}

		return print_path;
	}

	

	/*
	 * getCourse : RECOMMEND_PATH_REGION 에서 Data 값을 가져온다.
	 */
	public Course getCourse(String region) throws SQLException {
		
		return this.jdbcTemplate.queryForObject("SELECT * FROM RECOMMEND_PATH_REGION WHERE REGION = ?",new Object[] {region}, new RowMapper<Course>(){
			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Course course = new Course();
				course.setRegion(rs.getString("region"));
				course.setPath(rs.getString("path"));
				course.setDescription(rs.getString("description"));
				
				return course;
			}
			
		});
		
//		Connection c = dataSource.getConnection();
//		PreparedStatement ps = c.prepareStatement("SELECT * FROM RECOMMEND_PATH_REGION WHERE REGION = ?");
//
//		ps.setString(1, region);
//
//		ResultSet rs = ps.executeQuery();
//		rs.next();
//
//		Course course = new Course();
//		course.setRegion(rs.getString("region"));
//		course.setPath(rs.getString("path"));
//		course.setDescription(rs.getString("description"));
//
//		rs.close();
//		ps.close();
//		c.close();
//
//		return course;
	}

	public String getRecommendRegion() {
		return "지역별 추천코스 : " + course.getRegion() + "\n" + course.getDescription() + "\n\n" + text + "\n\n"
				+ "지역 정보 입력 ex)광주";
	}

	@Override
	public String toString() {
		return "지역별 추천코스 : " + course.getRegion() + course.getDescription() + "\n" + text;
	}
}
