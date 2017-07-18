package com.javalec.message;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

public class Photo {
	private String url;
	private int width;
	private int height;
	Gson gson = new Gson();
	
	
	public Photo(){
		
	}
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getPhoto(){
		JSONObject photo_json = new JSONObject();
		photo_json.put("url", this.url);
		photo_json.put("height", this.height);
		photo_json.put("width", this.width);
		
		return photo_json;
	}
	
	@Override
	public String toString() {
		return gson.toJson(getPhoto());
	} 
}
