package com.javalec.gapi;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlMake {
	
	public StringBuilder urlMake(String[] str) {
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < str.length; i++) {
				sb.append(str[i]);
			}
			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
		} catch (Exception e) {
			System.out.println("Url Make Error : " + e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return jsonResults;
	}
	
}
