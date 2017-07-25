package com.javalec.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.javalec.object.AreaCode;
import com.javalec.object.OpenAPI;
import com.javalec.object.Weather;

public class ResWeather {
	private static final String URI = "http://apis.skplanetx.com/weather/current/minutely?appKey=";
	private static final String API_KEY = "052414d2-e81e-3f92-9a14-657f8805366e";
	private static final String OPTION = "&version=1";
	Weather weather = new Weather();
	DataSource dataSource;
	
	public ResWeather(){
		
	}
	
	public void response(String areaName) throws  IOException, SQLException{
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		AreaCode area = context.getBean("AreaCode", AreaCode.class);
		
		// DB에서 해당 지역이름에 해당하는 코드정보를 받아옴
		ArrayList<String> areaCode = area.search(areaName);
		String stnid = areaCode.get(2);
		
		//SK_Planet OpenAPI URL 설정
		OpenAPI weatherPlanet = new OpenAPI(URI, API_KEY, OPTION);
		weatherPlanet.addUrl("stnid", stnid);
		//API에서 정보 요청
		String data = weatherPlanet.request();
		
		//json 형식으로 받아온 정보를 읽음
		JSONObject json = new JSONObject(data);
		JSONObject weather = new JSONObject(json.getJSONObject("weather").toString());
		JSONArray minutely = weather.getJSONArray("minutely");
		JSONObject sky = new JSONObject(minutely.getJSONObject(0).get("sky").toString());
		JSONObject temperature = new JSONObject(minutely.getJSONObject(0).get("temperature").toString());
//		JSONObject sky = new JSONObject(today.getJSONObject("sky").toString());
//		JSONObject temperature = new JSONObject(today.getJSONObject("temperature").toString());
		
		//Weather 객체에 저장
		this.weather.setRegion(areaName);
		this.weather.setSky(sky.getString("name"));
		this.weather.setTc(temperature.getString("tc"));
		this.weather.setHumidity(minutely.getJSONObject(0).getString("humidity"));
//		this.weather.setTmax(temperature.getString("tmax"));
//		this.weather.setTmin(temperature.getString("tmin"));
	}
	
	public String getText(){
		String result = "";
		if(this.weather.getSky().equals("맑음")){
			result =  weather.getRegion() +"에 대한 자세한 관광지 정보는 아래 URL을 클릭해주세요!\n 맑음 (" + weather.getTc() + "도)  (해)";	
		}
		else if(this.weather.getSky().equals("구름조금")){
			result =  weather.getRegion() +"에 대한 자세한 관광지 정보는 아래 URL을 클릭해주세요!\n 구름 조금 (구름) (" + weather.getTc() + "도)";		
		}
		else if(this.weather.getSky().equals("구름많음")){
			result =  weather.getRegion() +"에 대한 자세한 관광지 정보는 아래 URL을 클릭해주세요!\n 구름 많음 (구름)(구름) (" + weather.getTc() + "도)";
		}
		else if(this.weather.getSky().equals("흐림")){
			result =  weather.getRegion() +"에 대한 자세한 관광지 정보는 아래 URL을 클릭해주세요!\n 날이 흐림 (훌쩍) (" + weather.getTc() + "도)";
		}
		else if(this.weather.getSky().equals("비")){
			result =  weather.getRegion() +"에 대한 자세한 관광지 정보는 아래 URL을 클릭해주세요!\n 비가 내리고~ (비) (" + weather.getTc() + "도)";
		}
		else if(this.weather.getSky().equals("눈")){
			result =  weather.getRegion() +"에 대한 자세한 관광지 정보는 아래 URL을 클릭해주세요!\n 펑펑 눈이옵니다 (눈) (" + weather.getTc() + "도)";
		}
		else if(this.weather.getSky().equals("비 또는 눈")){
			result =  weather.getRegion() +"에 대한 자세한 관광지 정보는 아래 URL을 클릭해주세요!\n 비(비) 또는 눈(눈) (" + weather.getTc() + "도) ";
		}
		else{
			result = weather.getRegion() + "에 대한 자세한 관광지 정보는 아래 URL을 클릭해주세요\n";
		}

		return result;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
