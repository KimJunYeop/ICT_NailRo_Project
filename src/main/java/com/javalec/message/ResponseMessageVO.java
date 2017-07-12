package com.javalec.message;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

public class ResponseMessageVO {
	private Message message;
	private Keyboard keyboard;
	Gson gson = new Gson();
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Keyboard getKeyboard() {
		return keyboard;
	}
	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getResponseMessage(){
		JSONObject response_msg = new JSONObject();
		response_msg.put("message", message.getMessage());
		response_msg.put("keyboard", keyboard.getKeyboard());
		
		return response_msg;
	}
	
	
	@Override
	public String toString(){
		return gson.toJson(getResponseMessage());
	}
}
