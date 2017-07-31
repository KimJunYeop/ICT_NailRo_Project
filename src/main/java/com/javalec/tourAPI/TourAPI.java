package com.javalec.tourAPI;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.javalec.object.OpenAPI;
import com.javalec.object.AreaCode;

public class TourAPI {
	private static final String AREABASED_URI="http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=";
	private static final String AREABASED_OPT = "&MobileOS=ETC&MobileApp=nailrochat&arrange=B";
	private static final String DETAIL_URI = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=";
	private static final String DETAIL_OPT= "&MobileOS=ETC&MobileApp=nailrochat&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y&_type=json";
	private static final String INTRO_URI = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=";
	private static final String INTRO_OPT= "&MobileOS=ETC&MobileApp=nailrochat&_type=json";
	private static final String API_KEY = "9%2FgsanrvJGqg3eF9InVtlFrz2SOHea2S3MOgQE%2BwO0PPiHgPf2jIdgxy1vvpUhV%2BWlZ0httNIoeTJKmhZjgE7g%3D%3D";
	
	public TourAPI(){
		
	}
	
	public ArrayList<String> areaBased(String areaName, String contentTypeId, String numOfRows) throws UnsupportedEncodingException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, SQLException {
		/*
		 *  지역기반으로 해당 지역의 관광지 정보를 요청
		 *  지역명과, 검색타입(관광지, 숙소 등), 요청할 정보 갯수를 입력받음
		 *   ArrayList로 관광지 정보를 리턴
		 * 
		 */
		
		// DB에서 해당 지역이름에 해당하는 코드정보를 받아옴
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		AreaCode area = context.getBean("AreaCode", AreaCode.class);
		ArrayList<String> areaCode = area.search(areaName);
		
		//API 주소 설정
		OpenAPI tour = new OpenAPI(AREABASED_URI, API_KEY, AREABASED_OPT);
		if(areaCode.get(0).length() == 1){ 	// 광역시와 특별시의 경우, sigunguCode를 사용하지 않음
			tour.addUrl("areaCode", areaCode.get(0));
			tour.addUrl("contentTypeId", contentTypeId);
			tour.addUrl("numOfRows", numOfRows);
		}
		else { //일반 시,군을 위한 검색
			tour.addUrl("areaCode", areaCode.get(0));
			tour.addUrl("sigunguCode", areaCode.get(1));
			tour.addUrl("contentTypeId", contentTypeId);
			tour.addUrl("numOfRows", numOfRows);
		}
		//TourAPI에서 검색
		String totalLine = tour.request();
		
	    //xml Parsing
	    InputSource is = new InputSource(new StringReader(totalLine));
	    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	    XPath xpath = XPathFactory.newInstance().newXPath();

	    // XML에서 <contentid>에 해당하는 값을 찾음
	    NodeList contentid = (NodeList)xpath.evaluate("/response/body/items/item/contentid", document, XPathConstants.NODESET);
	    
	    ArrayList<String> result = new ArrayList<String>();
	    for(int i=0; i<contentid.getLength(); i++){
	    	result.add(contentid.item(i).getTextContent());
	    }
	   
	   return result;
	}
	
	public JSONArray contentDetail(ArrayList<String> ContentID) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		/*
		 * 입력받은 각각의 관광지ID의 세부정보를 요청해서
		 * 각 관광지의 세부정보를 JSON형식 정리해서 반환
		 * 
		 * */
		
		//OpenAPI에 검색을 요청하는 객체 생성
		OpenAPI addr = new OpenAPI(DETAIL_URI, API_KEY, DETAIL_OPT);
		
		String totalLine = new String();
		JSONArray result = new JSONArray();
		JSONObject json = new JSONObject();
		JSONObject response = new JSONObject();
	    JSONObject body = new JSONObject();
	    JSONObject items = new JSONObject();
		JSONObject item = new JSONObject();
		JSONObject detail = new JSONObject();
		
	    
		for(int i=0; i<ContentID.size(); i++){
			//API 주소 설정
			addr.setUrl(DETAIL_URI, API_KEY, DETAIL_OPT);
			addr.addUrl("contentId", ContentID.get(i));

			//TourAPI에서 관광지 json형식으로 요청
			totalLine = addr.request();
			
			//전체 데이터에서 key값이 "item"인 데이터 추출 
			json = new JSONObject(totalLine);
			response = json.getJSONObject("response");
			body = response.getJSONObject("body");
			items = body.getJSONObject("items");
			item = items.getJSONObject("item");
			detail = new JSONObject();
	
			//item에서 필요한 항목을 따로 분류해서 json형식으로 저장
			detail.put("title", item.get("title").toString());
			detail.put("overview", item.get("overview").toString());
			if(item.has("addr1")){
				detail.put("addr1", item.get("addr1").toString());
			} else{
				detail.put("addr1", "주소 정보가 없습니다.");
			}
			if(item.has("firstimage")){
				detail.put("firstimage", item.get("firstimage").toString());
			} else{
				detail.put("firstimage", "");
			}
			if(item.has("tel")){
				detail.put("tel", item.get("tel").toString());
			} else{
				detail.put("tel", "");
			}
			if(item.has("homepage")){
				detail.put("homepage", item.get("homepage").toString());
			} else{
				detail.put("homepage", "홈페이지 정보가 없습니다.");
			}
			
			//분류한 항목을 JSONArray에 추가
			result.put(detail);
		}
	    
	   //JSONArray 반환
	    return result;
	}


	public JSONArray introFood(ArrayList<String> ContentID) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		/*
		 * 입력받은 각각의 관광지ID의 세부정보를 요청해서
		 * 각 관광지의 세부정보를 JSON형식 정리해서 반환
		 * 
		 * */
	
		//OpenAPI에 검색을 요청하는 객체 생성
		OpenAPI addr = new OpenAPI(INTRO_URI, API_KEY, INTRO_OPT);
	
		String totalLine = new String();
		JSONArray result = new JSONArray();
		JSONObject json = new JSONObject();
		JSONObject response = new JSONObject();
		JSONObject body = new JSONObject();
		JSONObject items = new JSONObject();
		JSONObject item = new JSONObject();
		JSONObject detail = new JSONObject();
    
		for(int i=0; i<ContentID.size(); i++){
			//API 주소 설정
			addr.setUrl(INTRO_URI, API_KEY, INTRO_OPT);
			addr.addUrl("contentId", ContentID.get(i));
			addr.addUrl("contentTypeId", "39");
			System.out.println(addr.getUrl());
		
			//TourAPI에서 관광지 json형식으로 요청
			totalLine = addr.request();
		
			//전체 데이터에서 key값이 "item"인 데이터 추출 
			json = new JSONObject(totalLine);
			response = json.getJSONObject("response");
			body = response.getJSONObject("body");
			items = body.getJSONObject("items");
			item = items.getJSONObject("item");
			detail = new JSONObject();
		
			//item에서 필요한 항목을 따로 분류해서 json형식으로 저장
			if(item.has("chkcreditcardfood")){
				detail.put("chkcreditcardfood",  item.get("chkcreditcardfood").toString());
			} else{
				detail.put("chkcreditcardfood", "");
			}
			if(item.has("restdatefood")){
				detail.put("restdatefood", item.get("restdatefood").toString());
			} else{
				detail.put("restdatefood", "");
			}
			if(item.has("reservationfood")){
				detail.put("reservationfood", item.get("reservationfood").toString());
			} else{
				detail.put("reservationfood", "");
			}
			if(item.has("packing")){
				detail.put("packing", item.get("packing").toString());
			} else{
				detail.put("packing", "");
			}
			if(item.has("opentimefood")){
				detail.put("opentimefood", item.get("opentimefood").toString());
			} else{
				detail.put("opentimefood", "");
			}
			if(item.has("firstmenu")){
				detail.put("firstmenu", item.get("firstmenu").toString());
			} else{
				detail.put("firstmenu", "");
			}
			if(item.has("treatmenu")){
				detail.put("treatmenu", item.get("treatmenu").toString());
			} else{
				detail.put("treatmenu", "");
			}
			if(item.has("smoking")){
				detail.put("smoking", item.get("smoking").toString());
			} else{
				detail.put("smoking", "");
			}
		
			//분류한 항목을 JSONArray에 추가
			result.put(detail);
		}
    
		//JSONArray 반환
		return result;
	}
	
	public JSONArray introInn(ArrayList<String> ContentID) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		/*
		 * 입력받은 각각의 관광지ID의 세부정보를 요청해서
		 * 각 관광지의 세부정보를 JSON형식 정리해서 반환
		 * 
		 * */
	
		//OpenAPI에 검색을 요청하는 객체 생성
		OpenAPI addr = new OpenAPI(INTRO_URI, API_KEY, INTRO_OPT);
	
		String totalLine = new String();
		JSONArray result = new JSONArray();
		JSONObject json = new JSONObject();
		JSONObject response = new JSONObject();
		JSONObject body = new JSONObject();
		JSONObject items = new JSONObject();
		JSONObject item = new JSONObject();
		JSONObject detail = new JSONObject();
    
		for(int i=0; i<ContentID.size(); i++){
			//API 주소 설정
			addr.setUrl(INTRO_URI, API_KEY, INTRO_OPT);
			addr.addUrl("contentId", ContentID.get(i));
			addr.addUrl("contentTypeId", "32");
			System.out.println(addr.getUrl());
		
			//TourAPI에서 관광지 json형식으로 요청
			totalLine = addr.request();
		
			//전체 데이터에서 key값이 "item"인 데이터 추출 
			json = new JSONObject(totalLine);
			response = json.getJSONObject("response");
			body = response.getJSONObject("body");
			items = body.getJSONObject("items");
			item = items.getJSONObject("item");
			detail = new JSONObject();
		
			//item에서 필요한 항목을 따로 분류해서 json형식으로 저장
			if(item.has("reservationlodging")){
				detail.put("reservationlodging",  item.get("reservationlodging").toString());
			} else{
				detail.put("reservationlodging", "");
			}
			if(item.has("roomcount")){
				detail.put("roomcount", item.get("roomcount").toString());
			} else{
				detail.put("roomcount", "");
			}
			if(item.has("roomtype")){
				detail.put("roomtype", item.get("roomtype").toString());
			} else{
				detail.put("roomtype", "");
			}
			if(item.has("checkintime")){
				detail.put("checkintime", item.get("checkintime").toString());
			} else{
				detail.put("checkintime", "");
			}
			if(item.has("checkouttime")){
				detail.put("checkouttime", item.get("checkouttime").toString());
			} else{
				detail.put("checkouttime", "");
			}
			if(item.has("chkcooking")){
				detail.put("chkcooking", item.get("chkcooking").toString());
			} else{
				detail.put("chkcooking", "");
			}
			if(item.has("foodplace")){
				detail.put("foodplace", item.get("foodplace").toString());
			} else{
				detail.put("foodplace", "");
			}
			if(item.has("subfacility")){
				detail.put("subfacility", item.get("subfacility").toString());
			} else{
				detail.put("subfacility", "");
			}
		
			//분류한 항목을 JSONArray에 추가
			result.put(detail);
		}
    
		//JSONArray 반환
		return result;
	}
}