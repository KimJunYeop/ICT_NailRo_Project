package com.javalec.tourAPI;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private static final String FESTIVAL_URI="http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey=";
	private static final String FESTIVAL_OPT = "&MobileOS=ETC&MobileApp=nailrochat&arrange=B";
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
	
	public ArrayList<String> searchFestival(String areaName, String numOfRows) throws UnsupportedEncodingException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, SQLException {
		/* 
		 *  지역명과, 요청할 정보 갯수를 입력받음
		 *  오늘 날짜를 기반으로 ArrayList로 축제 정보를 리턴
		 */
		
		// DB에서 해당 지역이름에 해당하는 코드정보를 받아옴
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		AreaCode area = context.getBean("AreaCode", AreaCode.class);
		ArrayList<String> areaCode = area.search(areaName);
		
		//현재 날짜를 api 요청에 필요한 형식으로 변환 
		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		
		//API 주소 설정
		OpenAPI tour = new OpenAPI(FESTIVAL_URI, API_KEY, FESTIVAL_OPT);
		if(areaCode.get(0).length() == 1){ 	// 광역시와 특별시의 경우, sigunguCode를 사용하지 않음
			tour.addUrl("areaCode", areaCode.get(0));
		}
		else { //일반 시,군을 위한 검색
			tour.addUrl("areaCode", areaCode.get(0));
			tour.addUrl("sigunguCode", areaCode.get(1));
		}
		tour.addUrl("eventStartDate", date.format(d));
		tour.addUrl("numOfRows", numOfRows);
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
	
	public JSONArray introAttraction(ArrayList<String> ContentID) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		/*
		 * 관광지의 세부정보를 JSON형식 정리해서 반환
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
			addr.addUrl("contentTypeId", "12");
		
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
			if(item.has("infocenter")){
				detail.put("infocenter",  item.get("infocenter").toString());
			} else{
				detail.put("infocenter", "");
			}
			if(item.has("useseason")){
				detail.put("useseason", item.get("useseason").toString());
			} else{
				detail.put("useseason", "");
			}
			if(item.has("usetime")){
				detail.put("usetime", item.get("usetime").toString());
			} else{
				detail.put("usetime", "");
			}
			if(item.has("restdate")){
				detail.put("restdate", item.get("restdate").toString());
			} else{
				detail.put("restdate", "");
			}
			if(item.has("parking")){
				detail.put("parking", item.get("parking").toString());
			} else{
				detail.put("parking", "");
			}
			if(item.has("chkcreditcard")){
				detail.put("chkcreditcard", item.get("chkcreditcard").toString());
			} else{
				detail.put("chkcreditcard", "");
			}
			//분류한 항목을 JSONArray에 추가
			result.put(detail);
		}
		//JSONArray 반환
		return result;
	}
	
	public JSONArray introCulture(ArrayList<String> ContentID) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		/*
		 * 문화시설의 세부정보를 JSON형식 정리해서 반환
		 */
	
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
			addr.addUrl("contentTypeId", "14");
		
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
			if(item.has("infocenterculture")){
				detail.put("infocenterculture",  item.get("infocenterculture").toString());
			} else{
				detail.put("infocenterculture", "");
			}
			if(item.has("usefee")){
				detail.put("usefee", item.get("usefee").toString());
			} else{
				detail.put("usefee", "");
			}
			if(item.has("usetimeculture")){
				detail.put("usetimeculture", item.get("usetimeculture").toString());
			} else{
				detail.put("usetimeculture", "");
			}
			if(item.has("restdateculture")){
				detail.put("restdateculture", item.get("restdateculture").toString());
			} else{
				detail.put("restdateculture", "");
			}
			if(item.has("parkingculture")){
				detail.put("parkingculture", item.get("parkingculture").toString());
			} else{
				detail.put("parkingculture", "");
			}
			if(item.has("chkcreditcardculture")){
				detail.put("chkcreditcardculture", item.get("chkcreditcardculture").toString());
			} else{
				detail.put("chkcreditcardculture", "");
			}
			//분류한 항목을 JSONArray에 추가
			result.put(detail);
		}
		//JSONArray 반환
		return result;
	}
	
	public JSONArray introFood(ArrayList<String> ContentID) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		/*
		 * 음식점의 세부정보를 JSON형식 정리해서 반환
		 */
	
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
	
	public JSONArray introFestival(ArrayList<String> ContentID) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		/*
		 * 지역축제의 세부정보를 JSON형식 정리해서 반환
		 */
	
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
			addr.addUrl("contentTypeId", "15");
		
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
			if(item.has("sponsor1")){
				detail.put("sponsor1",  item.get("sponsor1").toString());
			} else{
				detail.put("sponsor1", "");
			}
			if(item.has("sponsor1tel")){
				detail.put("sponsor1tel", item.get("sponsor1tel").toString());
			} else{
				detail.put("sponsor1tel", "");
			}
			if(item.has("sponsor2")){
				detail.put("sponsor2", item.get("sponsor2").toString());
			} else{
				detail.put("sponsor2", "");
			}
			if(item.has("sponsor2tel")){
				detail.put("sponsor2tel", item.get("sponsor2tel").toString());
			} else{
				detail.put("sponsor2tel", "");
			}
			if(item.has("eventstartdate")){
				detail.put("eventstartdate", item.get("eventstartdate").toString());
			} else{
				detail.put("eventstartdate", "");
			}
			if(item.has("eventenddate")){
				detail.put("eventenddate", item.get("eventenddate").toString());
			} else{
				detail.put("eventenddate", "");
			}
			if(item.has("playtime")){
				detail.put("playtime", item.get("playtime").toString());
			} else{
				detail.put("playtime", "");
			}
			if(item.has("eventplace")){
				detail.put("eventplace", item.get("eventplace").toString());
			} else{
				detail.put("eventplace", "");
			}
			if(item.has("spendtimefestival")){
				detail.put("spendtimefestival", item.get("spendtimefestival").toString());
			} else{
				detail.put("spendtimefestival", "");
			}
			if(item.has("agelimit")){
				detail.put("agelimit", item.get("agelimit").toString());
			} else{
				detail.put("agelimit", "");
			}
			if(item.has("program")){
				detail.put("program", item.get("program").toString());
			} else{
				detail.put("program", "");
			}
			//분류한 항목을 JSONArray에 추가
			result.put(detail);
		}
		//JSONArray 반환
		return result;
	}
}