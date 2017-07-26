	package com.javalec.tourAPI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.javalec.gapi.UrlMake;
import com.javalec.object.JTourCourse;
import com.javalec.object.JTourCourseContent;
import com.javalec.object.JTourCourseOverview;

/*
 * 관광정보 API 과련 코스 정보 얻어오는 Class
 * 개발일 : 2017-07-24
 * 개발자 : 김준엽
 */

public class JTourApi {
	// http://api.visitkorea.or.kr/openapi/service
	// /rest/KorService/searchKeyword?ServiceKey=인증키
	// &keyword=%EC%84%9C%EC%9A%B8&areaCode=&sigunguCode=&cat1=C01&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=O&numOfRows=12&pageNo=1
	private static final String TOUR_API_BASE = "http://api.visitkorea.or.kr/openapi/service/rest/KorService";
	private static final String API_KEY = "9oq2u2e8J%2FmhhEbg4LqT%2F9dCcvWYU0DlVEzp1DlkHF6vBFpqgr1xKZA5XWZKjHIrnpltgxM%2FR5qS27aHE%2BQbSA%3D%3D";
	private static final String MOBILE = "&MobileOS=AND&MobileApp=nailrobot";
	private static final String TYPE = "&_type=json";
	UrlMake url_make;
	ArrayList<JTourCourse> jtourcourse_list;

	public JTourApi() {

	}

	public JTourApi(String keyword) throws UnsupportedEncodingException {
//		String request = URLEncoder.encode(keyword, "UTF-8");
//
//		jtourcourse_list = new ArrayList<JTourCourse>();
//
//		StringBuilder keyword_search = tourKeywordSearch(request);
//		System.out.println(keyword_search);
//		jtourcourse_list = tourKeywordSearchResult(keyword_search);
//		for (int i = 0; i < jtourcourse_list.size(); i++) {
//			StringBuilder content_search = tourCourseSearch(jtourcourse_list.get(i).getContentid(),
//					jtourcourse_list.get(i).getContenttypeid());
//			tourSetCourseResult(content_search, jtourcourse_list.get(i));
//		}
	}

	public ArrayList<JTourCourse> getCourseList() {
		return jtourcourse_list;
	}

	/*
	 * keyword를 이용한 search
	 */
	public StringBuilder tourKeywordSearch(String str) {
		String service = "/searchKeyword?ServiceKey=";
		String tour_url = TOUR_API_BASE + service + API_KEY;
		String tour_url_2 = "&areaCode=&sigunguCode=&cat1=C01&cat2=&cat3=&listYN=Y";
		String tour_url_3 = "&arrange=P&numOfRows=12&pageNo=1";
		String[] url = { tour_url, "&keyword=" + str, tour_url_2, MOBILE, tour_url_3, TYPE };
		url_make = new UrlMake();
		StringBuilder jsonResults = url_make.urlMake(url);
		System.out.println(url_make.getUrl());
		return jsonResults;
	}

	/*
	 * ContentId를 이용한 Course Search
	 */
	public StringBuilder tourCourseSearch(int contentid, int contenttypeid) {
		String service = "/detailInfo?ServiceKey=";
		String tour_url = TOUR_API_BASE + service + API_KEY;
		String tour_url_2 = "&contentTypeId=" + contenttypeid + "&contentId=" + contentid;
		String tour_url_3 = "&listYN=Y";
		String[] url = { tour_url, tour_url_2, tour_url_3, MOBILE, TYPE };
		url_make = new UrlMake();
		StringBuilder jsonResults = url_make.urlMake(url);
		return jsonResults;
	}
	
	/*
	 * Course overview 가져오기! 
	 */
	
	public StringBuilder tourCourseOverviewSearch(int contentid, int contenttypeid) {
		String service = "/detailCommon?ServiceKey=";
		String tour_url = TOUR_API_BASE + service + API_KEY;
		String tour_url_2 = "&contentTypeId=" + contenttypeid + "&contentId=" + contentid;
		String tour_url_3 = "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y";
		String[] url = { tour_url, tour_url_2, tour_url_3, MOBILE, TYPE };
		url_make = new UrlMake();
		StringBuilder jsonResults = url_make.urlMake(url);
		return jsonResults;
	}

	/*
	 * keyword를 이용한 search 결과물.
	 */
	public ArrayList<JTourCourse> tourKeywordSearchResult(StringBuilder jsonResults) {
		JSONObject tour_obj = new JSONObject(jsonResults.toString()).getJSONObject("response").getJSONObject("body")
				.getJSONObject("items");
		JSONArray tour_array = tour_obj.getJSONArray("item");

		ArrayList<JTourCourse> jtour_recommend_list = new ArrayList<JTourCourse>();

		for (int i = 0; i < tour_array.length(); i++) {
			JTourCourse jtour_recommend_obj = new JTourCourse();
			String title = tour_array.getJSONObject(i).getString("title");
			jtour_recommend_obj.setTitle(title);
			if (tour_array.getJSONObject(i).has("firstimage")) {
				String image = tour_array.getJSONObject(i).getString("firstimage");
				jtour_recommend_obj.setImage(image);
			}

			int contenttypeid = tour_array.getJSONObject(i).getInt("contenttypeid");
			jtour_recommend_obj.setContenttypeid(contenttypeid);
			int contentid = tour_array.getJSONObject(i).getInt("contentid");
			jtour_recommend_obj.setContentid(contentid);

			jtour_recommend_list.add(jtour_recommend_obj);
		}

		return jtour_recommend_list;
	}

	/*
	 * Conetne를 이용한 Course 결과물.
	 */
	public void tourSetCourseResult(StringBuilder jsonResults, JTourCourse jtour_course) {
		JSONObject tour_course_obj = new JSONObject(jsonResults.toString()).getJSONObject("response")
				.getJSONObject("body").getJSONObject("items");
		JSONArray tour_course_array = tour_course_obj.getJSONArray("item");

		ArrayList<JTourCourseContent> jtour_course_list = new ArrayList<JTourCourseContent>();

		for (int i = 0; i < tour_course_array.length(); i++) {
			JTourCourseContent jtour_content = new JTourCourseContent();
			String subdetailoverview = tour_course_array.getJSONObject(i).getString("subdetailoverview");
			jtour_content.setSubdetailoverview(subdetailoverview);

			if (tour_course_array.getJSONObject(i).has("subdetailimg")) {
				String subdetailimg = tour_course_array.getJSONObject(i).getString("subdetailimg");
				jtour_content.setSubdetailimg(subdetailimg);
			}

			String subname = tour_course_array.getJSONObject(i).getString("subname");
			jtour_content.setSubname(subname);

			jtour_course_list.add(jtour_content);
		}

		jtour_course.setCourse(jtour_course_list);
	}

	/*
	 * Course 상세정보만 가져오는 method
	 */
	public ArrayList<JTourCourseContent> tourGetCourseResult(StringBuilder jsonResults) {
		JSONObject tour_course_obj = new JSONObject(jsonResults.toString()).getJSONObject("response")
				.getJSONObject("body").getJSONObject("items");
		JSONArray tour_course_array = tour_course_obj.getJSONArray("item");

		ArrayList<JTourCourseContent> jtour_course_content_list = new ArrayList<JTourCourseContent>();
		for (int i = 0; i < tour_course_array.length(); i++) {
			JTourCourseContent content = new JTourCourseContent();

			String subdetailoverview = tour_course_array.getJSONObject(i).getString("subdetailoverview");
			content.setSubdetailoverview(subdetailoverview);

			if (tour_course_array.getJSONObject(i).has("subdetailimg")) {
				String subdetailimg = tour_course_array.getJSONObject(i).getString("subdetailimg");
				content.setSubdetailimg(subdetailimg);
			}

			String subname = tour_course_array.getJSONObject(i).getString("subname");
			content.setSubname(subname);
			
			jtour_course_content_list.add(content);
		}

		return jtour_course_content_list;
	}
	
	/*
	 *	OverView 를 가져오는 메소드 
	 *	tourGetCourseResult 의 결과물을 이용.
	 */
	public JTourCourseOverview tourCourseOverviewGet(StringBuilder jsonResults){
		JSONObject tour_overview_obj = new JSONObject(jsonResults.toString()).getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item");
		JTourCourseOverview jtour_course_overview = new JTourCourseOverview();
		
		Double mapx = null;
		Double mapy = null;
		
		if(tour_overview_obj.has("mapx")) {
			mapx = tour_overview_obj.getDouble("mapx");
		}
		
		if(tour_overview_obj.has("mapy")) {
			mapy = tour_overview_obj.getDouble("mapy");
		}
		
		String overview = tour_overview_obj.getString("overview");
		String title = tour_overview_obj.getString("title");
		String image = "";
		
		
		if(tour_overview_obj.has("firstimage")){
			image = tour_overview_obj.getString("firstimage");
		}
		
		jtour_course_overview.setTitle(title);
		jtour_course_overview.setOverview(overview);
		jtour_course_overview.setFirstimage(image);
		jtour_course_overview.setMapx(mapx);
		jtour_course_overview.setMapy(mapy);
		
		return jtour_course_overview;
	}
}
