package com.javalec.gapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.junit.Test;

import com.javalec.object.JTourCourse;
import com.javalec.tourAPI.JTourApi;

public class JunitTest {

	// @Test
	// public void test() throws ApiException, InterruptedException,
	// IOException{
	// GooglePlace test = new GooglePlace();
	// ArrayList<JPlace> place = new ArrayList<JPlace>();
	// place = test.search("곡성");
	// System.out.println("------------------");
	// System.out.println(place);
	// Descending descending = new Descending();
	// Collections.sort(place,descending);
	//
	// System.out.println("what the...");
	// for(int i = 0; i < place.size(); i++){
	// System.out.println(" -----------------" + i +
	// "------------------------");
	// JSONArray array = place.get(i).getReviews();
	// for(int j = 0; j < array.length(); j++){
	// JSONObject obj = (JSONObject) array.get(j);
	//
	// System.out.println("author name : " + obj.getString("author_name"));
	// System.out.println("rating : " + obj.getDouble("rating"));
	// System.out.println("text : " + obj.getString("text"));
	// }
	// }
	// }

	// @Test
	// public void chatTest(){
	// String str = "코스부산";
	//
	// if(str.matches("코스.*.*")){
	// System.out.println("success");
	// System.out.println(str);
	// }else{
	// System.out.println("fail");
	// }
	//
	// }

	// @Test
	// public void jdbctest() throws SQLException{
	// Course course = new Course();
	// ApplicationContext context = new
	// GenericXmlApplicationContext("applicationContext.xml");
	// ResRecommendRegion res_region = context.getBean("resRecommendRegion",
	// ResRecommendRegion.class);
	// System.out.println(res_region.getCourse("경상도").getPath());
	// }

	// @Test
	// public void tourTest() throws UnsupportedEncodingException {
	// JTourApi j_tour = new JTourApi();
	// String keyword = "무주";
	// String request = URLEncoder.encode(keyword, "UTF-8");
	//
	// ArrayList<JTourCourse> jtour_course = new ArrayList<JTourCourse>();
	// jtour_course =
	// j_tour.tourKeywordSearchResult(j_tour.tourKeywordSearch(request));
	//
	// for(int i = 0 ; i < jtour_course.size(); i++){
	// System.out.println("-------------------" + i +
	// "-----------------------------");
	// System.out.println("title : " + jtour_course.get(i).getTitle());
	// System.out.println("contentId : " + jtour_course.get(i).getContentid());
	// System.out.println("contenttypeId : " +
	// jtour_course.get(i).getContenttypeid());
	// System.out.println("image : " + jtour_course.get(i).getImage());
	//
	// System.out.println("@@@@@@@@@@@@@@@@@@@@course
	// detail@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	// System.out.println(j_tour.tourCourseSearch(jtour_course.get(i).getContentid(),
	// jtour_course.get(i).getContenttypeid()));
	// StringBuilder builder =
	// j_tour.tourCourseSearch(jtour_course.get(i).getContentid(),
	// jtour_course.get(i).getContenttypeid());
	//
	// }
	//
	// }
	//

	@Test
	public void tourTest2() throws UnsupportedEncodingException {
		String request = "코스부산";
		String real_request = request.replaceFirst("코스", "");
		
		JTourApi j_tour = new JTourApi(real_request);
		String response_message = "";
		ArrayList<JTourCourse> j_tour_list = j_tour.getCourseList();
		
		
		for(int i = 0 ; i < j_tour_list.size() ; i ++){
			response_message += j_tour_list.get(i).getTitle();
			response_message += "\n";
			response_message += "http://13.124.143.250:8080/ICT_Nailro_Project/course/"+j_tour_list.get(i).getContentid()+"&"+j_tour_list.get(i).getContenttypeid();
			response_message += "\n";
		}
		System.out.println(response_message);
//		System.out.println(real_request);
//		String response_message = "";
//		ArrayList<JTourCourse> j_tour_list = j_tour.getCourseList();
//		for(int i = 0 ; i < j_tour_list.size() ; i ++){
//			response_message = j_tour_list.get(i).getTitle();
//			response_message += "\n";
//			response_message += "url";
//			response_message += "\n";
//		}
//		
//		JTourApi j_tour = new JTourApi("부산");
//		ArrayList<JTourCourse> j_tour_list = j_tour.getCourseList();
//		
//		
//		for(int i = 0; i < j_tour_list.size(); i++) {
//			System.out.println("--------------------" + i + "-----------------------------");
//			System.out.print("get title : ");
//			System.out.println(j_tour_list.get(i).getTitle());
//			System.out.print("get Content : ");
//			System.out.println(j_tour_list.get(i).getContentid());
//			System.out.print("get ContentTypeId : ");
//			System.out.println(j_tour_list.get(i).getContenttypeid());
//			
//			for(int j = 0; j < j_tour_list.get(i).getCourse().size(); j++){
//				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//				System.out.println("content_subname : " + j_tour_list.get(i).getCourse().get(j).getSubname());
//				System.out.println("content_subdetailoverview : " + j_tour_list.get(i).getCourse().get(j).getSubdetailoverview());
//				System.out.println("content_subdetailimg : " + j_tour_list.get(i).getCourse().get(j).getSubdetailimg());
//			}
//		
//		}
		
		
	
	}

}
