package com.javalec.message;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;

public class Message {
	private String text;
	private Photo photo;
	private MessageButton message_button;
	Gson gson = new Gson();
	
	public Message(){
		
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Photo getPhoto() {
		return photo;
	}
	
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	public MessageButton getMessage_button() {
		return message_button;
	}
	
	public void setMessage_button(MessageButton message_button) {
		this.message_button = message_button;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getMessage(){
		JSONObject message = new JSONObject();
		message.put("text", text);
		message.put("photo", photo);
		message.put("message_button", message_button);
		return message;
	}
	

	@Override
	public String toString() {
		return gson.toJson(getMessage());
	} 

	
	
}
