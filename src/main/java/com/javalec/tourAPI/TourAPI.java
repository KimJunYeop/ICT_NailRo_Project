package com.javalec.tourAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.javalec.tourAPI.tourURL;

public class TourAPI {
	
	public TourAPI(){
	}
	
	public JSONObject Search(String areaName, String content) throws XPathExpressionException, IOException, SAXException, ParserConfigurationException{
		TourAPI tour = new TourAPI();
		
		String contentid = tour.ContentID(areaName, content);
		JSONObject result =tour.ContentDetail(contentid);
		
		return result;
	}
	
	public String ContentID(String areaName,String content) throws UnsupportedEncodingException, IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		String areaCode = "";
		if(areaName.equals("서울")){
			areaCode = "1";
		}
		else if(areaName.endsWith("인천")){
			areaCode = "2";
		}
		else if(areaName .equals("대전")){
			areaCode = "3";
		}
		else {
			areaCode = "6";
		}
		
		String keyword = URLEncoder.encode(content, "UTF-8");
		tourURL addr = new tourURL(areaCode, keyword);
		
		URL url = new URL(addr.getUrl());
		
		//connect openAPI
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
//	    System.out.println("\n" + url);
	    
	    int RC = conn.getResponseCode();
//	    System.out.println("Response code: " + RC);
	   
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
	    System.out.println(totalLine);
	   
//	    JSONObject json = new JSONObject();
	    
	    //xml Parsing
	    InputSource is = new InputSource(new StringReader(totalLine));
	    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	    XPath xpath = XPathFactory.newInstance().newXPath();

	    NodeList contentid = (NodeList)xpath.evaluate("/response/body/items/item/contentid", document, XPathConstants.NODESET);

	    if(contentid.item(0) != null){
	    	return contentid.item(0).getTextContent();
	    }
	    else{
	    	return null;
	    }
	}
	
	public JSONObject ContentDetail(String ContentID) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		tourURL addr = new tourURL(ContentID);
		
		URL url = new URL(addr.getUrl());
		
		//connect openAPI
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
//	    System.out.println("\n" + ContentID);
	    
	    int RC = conn.getResponseCode();
//	    System.out.println("Response code: " + RC);
	   
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
//	    System.out.println(totalLine);
	   
	    JSONObject detail = new JSONObject();
	    
	    //xml Parsing
	    InputSource is = new InputSource(new StringReader(totalLine));
	    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	    XPath xpath = XPathFactory.newInstance().newXPath();
	 
	    NodeList totalCount = (NodeList)xpath.evaluate("/response/body/totalCount", document, XPathConstants.NODESET);
	    NodeList title = (NodeList)xpath.evaluate("/response/body/items/item/title", document, XPathConstants.NODESET);
	    NodeList overview = (NodeList)xpath.evaluate("/response/body/items/item/overview", document, XPathConstants.NODESET);
//	    NodeList tel = (NodeList)xpath.evaluate("/response/body/items/item/tel", document, XPathConstants.NODESET);
//	    NodeList telname = (NodeList)xpath.evaluate("/response/body/items/item/telname", document, XPathConstants.NODESET);
	    
//	    System.out.println(totalCount.item(0).getTextContent());
	    if(totalCount.item(0).getTextContent().equals("0")){
	    	detail.put("title", ContentID);
		    detail.put("overview", "해당 관광지에 대한 정보가 없습니다.");
//		    detail.put("tel", "");
//		    detail.put("telname", "");
	    }
	    else {
	    	detail.put("title", title.item(0).getTextContent());
	    	detail.put("overview", overview.item(0).getTextContent());
//	    	detail.put("tel", tel.item(0).getTextContent());
//	    	detail.put("telname", telname.item(0).getTextContent());
	    }
	    
	    return detail;
	}
}