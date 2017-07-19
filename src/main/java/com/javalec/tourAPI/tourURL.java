package com.javalec.tourAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TourURL {
	static final private String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";
	static final private String serviceKey = "9%2FgsanrvJGqg3eF9InVtlFrz2SOHea2S3MOgQE%2BwO0PPiHgPf2jIdgxy1vvpUhV%2BWlZ0httNIoeTJKmhZjgE7g%3D%3D";
	private String type;
	private String option;
	private String areaCode;
	private String keyword;
	private String contentID;
	
	public TourURL(String areaCode, String content) throws IOException{
		this.type = "searchKeyword";
		this.option = "&MobileOS=ETC&MobileApp=nailrochat&numOfRows=100";
		this.areaCode = areaCode;
		this.keyword = URLEncoder.encode(content, "UTF-8");
	}
	
	public TourURL(String contentID){
		this.type = "detailCommon";
		this.option = "&MobileOS=ETC&MobileApp=nailrochat&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y";
		this.contentID = contentID;
	}
	
	public String request(String addr) throws IOException{
		URL url = new URL(addr);
		
		//connect openAPI
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	    
	    int RC = conn.getResponseCode();
	    //Read XML data
	    BufferedReader buffer;
	    if(RC == HttpURLConnection.HTTP_OK) {
	        buffer = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	    }  else {
	        buffer = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
	    }
	    String totalLine = "";
	    String line;
	    while ((line = buffer.readLine()) != null) { 
	    	totalLine += line;
	    }
	    buffer.close();
	    conn.disconnect();
	    
	    // XML 데이터를 String 형식으로 Return
	    return totalLine;
	}
	
	public String getUrl() {
		if(this.type.equals("searchKeyword")){
			return this.url + this.type + "?ServiceKey=" + this.serviceKey + this.option +"&areaCode=" + this.areaCode + "&keyword=" + this.keyword;
		}
		else{
			return this.url + this.type + "?ServiceKey=" + this.serviceKey + this.option +"&contentId=" + this.contentID;
		}
	}
	
}
