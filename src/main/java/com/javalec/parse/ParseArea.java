package com.javalec.parse;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.javalec.object.AreaCode;

public class ParseArea {
	private String areaName;
	
	public ParseArea(){
	}
	
	public boolean isTrue(String text) throws SQLException{
		/*
		 * 입력받은 테스트의 앞 두글자를 추출해서 해당 글자가
		 * DB에 존재하는 지역명일 경우 True, 아니면 False를 반환한다.
		 * 
		 * */
		
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		AreaCode area = context.getBean("AreaCode", AreaCode.class);
		
		if(text.length() < 2){
			return false;
		}
		else {
			String areaName = text.substring(0, 2);
			
			if(area.search(areaName).get(0).equals("0")){
				return false;
			}
			else{
				this.areaName = areaName;
				return true;
			}
		}
	}
	
	public String getAreaName(){
		return this.areaName;
	}

}
