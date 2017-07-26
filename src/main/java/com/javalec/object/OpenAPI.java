package com.javalec.object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenAPI {
	private String url;
	
	public OpenAPI(String uri, String serviceKey) {
		this.url = uri + serviceKey;
	}
	
	public OpenAPI(String uri, String serviceKey, String option) {
		this.url = uri + serviceKey + option;
	}
	
	public void addUrl(String key, String value) {
		this.url = url + "&" + key + "=" + value;
	}
	
	public void setUrl(String uri, String serviceKey){
		this.url = uri + serviceKey;
	}
	
	public void setUrl(String uri, String serviceKey, String option){
		this.url = uri + serviceKey + option;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public String request() throws IOException{
//		System.out.println(url);
		URL url = new URL(this.url);
		
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
	
}