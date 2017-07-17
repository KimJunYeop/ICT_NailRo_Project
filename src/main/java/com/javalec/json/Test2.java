package com.javalec.json;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.javalec.Response.ResRecommendRegion;
import com.javalec.object.Course;

public class Test2 {

	public void test() throws SQLException{
//		JSONObject obj1 = new JSONObject();
//		obj1.put("1", "2");
//		
//		JSONObject obj2 = new JSONObject();
//		obj2.put("5", "6");
//		obj2.put("7", "8");
//		
//		JSONArray array = new JSONArray();
//		array.add(obj1);
//		array.add(obj2);
//		
//		System.out.println(array);
//		
//		JSONObject obj3 = new JSONObject();
//		obj3.put("연예인" , array);
//		System.out.println(obj3);	
		
//		JSONObject keyboard = new JSONObject();
//		keyboard.put("type", "buttons");
//		
//		JSONArray array = new JSONArray();
//		array.add("안녕");
//		array.add("하세요");
//		array.add("으하하핳");
//		
//		keyboard.put("buttons" , array);
//		
//		System.out.println(keyboard);
//		
//		Keyboard keyboard = new Keyboard();
//		System.out.println(keyboard);
//		
//		Photo photo = new Photo();
//		System.out.println(photo);
		
//		JSONObject object = new JSONObject();
//		Message message = new Message();
//		object.put("message", message.getMessage());
//		object.put("keyboard", keyboard.getKeyboard());
//		System.out.println(object);
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		ResRecommendRegion res = context.getBean("resRecommendRegion", ResRecommendRegion.class);
		res.setRegion("전라도");
		System.out.println(res);
//		Course course = new Course();
//		course = res.getCourse("전라도");
//		System.out.print(res.cutPath(course.getPath()));
//		System.out.println(course.getPath());
	}
	
}
