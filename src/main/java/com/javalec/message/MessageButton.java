package com.javalec.message;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

public class MessageButton {
	private String label;
	private String url;
	Gson gson = new Gson();
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getMessageButton(){
		JSONObject message_button = new JSONObject();
		message_button.put("label", label);
		message_button.put("url", url);
		
		return message_button;
	}
	
	@Override
	public String toString() {
		return gson.toJson(getMessageButton());
	}
}
