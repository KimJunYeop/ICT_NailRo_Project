package com.javalec.message;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

public class Keyboard{
	private String type;
	private String[] buttons;
	Gson gson = new Gson();
	
	public Keyboard(){
		this.type = "text";
	}
	
	public Keyboard(String[] buttons){
		this.type = "buttons";
		this.buttons = buttons;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getButtons() {
		return buttons;
	}

	public void setButtons(String[] buttons) {
		this.buttons = buttons;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getKeyboard(){
		JSONObject keyboard_json = new JSONObject();
		JSONArray buttonsarray = new JSONArray();
		
		if(buttons!=null){
			for(int i = 0 ; i < buttons.length; i++){
				buttonsarray.add(buttons[i]);
			}
		}
		
		keyboard_json.put("buttons", buttonsarray);
		keyboard_json.put("type" , type);
		
		return keyboard_json;
	}
	
	@Override
	public String toString(){
		return gson.toJson(getKeyboard());
	}
	

//	@Override
//	public String toString() {
//		return getKeyboard();
//	}
	
}
