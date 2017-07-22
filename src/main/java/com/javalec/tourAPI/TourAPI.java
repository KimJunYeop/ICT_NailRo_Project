package com.javalec.tourAPI;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.javalec.tourAPI.TourURL;

public class TourAPI {
	
	public TourAPI(){
	}
	
	public JSONObject search(String areaName, String content) throws XPathExpressionException, IOException, SAXException, ParserConfigurationException, SQLException{
		TourAPI tour = new TourAPI();
		
		String contentid = tour.contentID(areaName, content);
		JSONObject result =tour.contentDetail(contentid);
		
		return result;
	}
	
	public String contentID(String areaName,String content) throws UnsupportedEncodingException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, SQLException {
		/*
		 *  키워드 검색을 위한 method
		 *  해당 지역이름과 관광지 이름으로 세부검색에 필요한 관좡지 코드를 검색 
		 * 
		 */
		
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		SearchAreaCode area = context.getBean("searchAreaCode", SearchAreaCode.class);
		
		// DB에서 해당 지역이름에 해당하는 코드정보를 받아옴
		ArrayList<String> areaCode = area.get(areaName);
		
		//TourAPI에서 검색
		String totalLine = "";
		if(areaCode.get(0).length() == 1){ 	// 광역시와 특별시의 경우, sigunguCode를 사용하지 않음
			TourURL addr = new TourURL(areaCode.get(0), content);
			totalLine = addr.request(addr.getUrl());
		}
		else { //일반 시,군을 위한 검색
			TourURL addr = new TourURL(areaCode.get(0), areaCode.get(1), content);
			totalLine = addr.request(addr.getUrl());
		}
		
	    //xml Parsing
	    InputSource is = new InputSource(new StringReader(totalLine));
	    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	    XPath xpath = XPathFactory.newInstance().newXPath();

	    // XML에서 <contentid>에 해당하는 값을 찾음
	    NodeList contentid = (NodeList)xpath.evaluate("/response/body/items/item/contentid", document, XPathConstants.NODESET);

	    if(contentid.item(0) != null){ //결과값 존재 유무 확인
	    	// contentID를 String 형식으로 반환
	    	return contentid.item(0).getTextContent();
	    }
	    else{
	    	return null;
	    }
	}
	
	public JSONObject contentDetail(String ContentID) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		TourURL addr = new TourURL(ContentID);
		
		//TourAPI에서 관관지 검색
		String totalLine = addr.request(addr.getUrl());
	    JSONObject detail = new JSONObject();
	    
	    //xml Parsing
	    InputSource is = new InputSource(new StringReader(totalLine));
	    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	    XPath xpath = XPathFactory.newInstance().newXPath();
	 
	    NodeList totalCount = (NodeList)xpath.evaluate("/response/body/totalCount", document, XPathConstants.NODESET);
	    NodeList title = (NodeList)xpath.evaluate("/response/body/items/item/title", document, XPathConstants.NODESET);
	    NodeList overview = (NodeList)xpath.evaluate("/response/body/items/item/overview", document, XPathConstants.NODESET);
	    
	    if(totalCount.item(0).getTextContent().equals("0")){// 결과값 존재 유무 확인
	    	//json 형식으로 저장
	    	detail.put("title", ContentID);
		    detail.put("overview", "해당 관광지에 대한 정보가 없습니다.");
	    }
	    else {
	    	detail.put("title", title.item(0).getTextContent());
	    	detail.put("overview", overview.item(0).getTextContent());
	    }
	    
	    //결과값을 json형식으로 반환
	    return detail;
	}
}