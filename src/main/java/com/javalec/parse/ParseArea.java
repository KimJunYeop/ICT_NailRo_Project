package com.javalec.parse;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.javalec.object.AreaCode;

public class ParseArea {
	private String areaName;
	private String contentType ="전체";
	
	public ParseArea(){
	}
	
	public boolean isTrue(String text) throws SQLException{
		/*
		 * 입력받은 테스트의 앞 두글자 / 세글자를 추출해서 해당 글자가
		 * DB에 존재하는 지역명일 경우 True, 아니면 False를 반환한다.
		 */
		
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		AreaCode area = context.getBean("AreaCode", AreaCode.class);
		
		if(text.length() < 2){
			return false;
		}
		else if(text.length()==2){
			String areaName = text.substring(0, 2);
			
			if(area.search(areaName).get(0).equals("0")){
				return false;
			}
			else{
				this.areaName = areaName;
				return true;
			}
			
		}
		else {
			String areaName = text.substring(0, 2);
			String areaName2 = text.substring(0,3);
			
			if(area.search(areaName).get(0).equals("0") && area.search(areaName2).get(0).equals("0")){
				return false;
			}
			else if(!area.search(areaName).get(0).equals("0") && area.search(areaName2).get(0).equals("0")){
				this.areaName = areaName;
				return true;
			}
			else{
				this.areaName = areaName2;
				return true;
			}
		}
	}
	
	public String getAreaName(){
		return this.areaName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String text) {
		
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		AreaCode area = context.getBean("AreaCode", AreaCode.class);
		
		if(text.length() > 2){
			String type = text.substring(text.length()-2, text.length());
			
			if(type.equals("관광")){
				this.contentType = "관광";
			} else if(type.equals("문화")){
				this.contentType = "문화";
			} else if(type.equals("축제")){
				this.contentType = "축제";
			} else if(type.equals("숙박")){
				this.contentType = "숙박";
			} else if(type.equals("쇼핑")){
				this.contentType = "쇼핑";
			} else if(type.equals("음식")){
				this.contentType = "음식";
			} else{
				this.contentType = "전체";
			}
		}

	}

}
