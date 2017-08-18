package com.javalec.gapi;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.javalec.discount.DisCountCoupon;
import com.javalec.discount.DisCountCouponObj;
import com.javalec.discount.JBarcode;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

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

	// @Test
	// public void tourTest2() throws UnsupportedEncodingException {
	// String request = "코스부산";
	// String real_request = request.replaceFirst("코스", "");
	//
	// JTourApi j_tour = new JTourApi(real_request);
	// String response_message = "";
	// ArrayList<JTourCourse> j_tour_list = j_tour.getCourseList();
	//
	//
	// for(int i = 0 ; i < j_tour_list.size() ; i ++){
	// response_message += j_tour_list.get(i).getTitle();
	// response_message += "\n";
	// response_message +=
	// "http://localhost:8080/spring_ex_pjt/course?"+"content_id="+j_tour_list.get(i).getContentid()+"&content_type_id="+j_tour_list.get(i).getContenttypeid();
	// response_message += "\n";
	// }
	// System.out.println(response_message);
	//
	//
	//
	//// System.out.println(real_request);
	//// String response_message = "";
	//// ArrayList<JTourCourse> j_tour_list = j_tour.getCourseList();
	//// for(int i = 0 ; i < j_tour_list.size() ; i ++){
	//// response_message = j_tour_list.get(i).getTitle();
	//// response_message += "\n";
	//// response_message += "url";
	//// response_message += "\n";
	//// }
	////
	//// JTourApi j_tour = new JTourApi("부산");
	//// ArrayList<JTourCourse> j_tour_list = j_tour.getCourseList();
	////
	////
	//// for(int i = 0; i < j_tour_list.size(); i++) {
	//// System.out.println("--------------------" + i +
	// "-----------------------------");
	//// System.out.print("get title : ");
	//// System.out.println(j_tour_list.get(i).getTitle());
	//// System.out.print("get Content : ");
	//// System.out.println(j_tour_list.get(i).getContentid());
	//// System.out.print("get ContentTypeId : ");
	//// System.out.println(j_tour_list.get(i).getContenttypeid());
	////
	//// for(int j = 0; j < j_tour_list.get(i).getCourse().size(); j++){
	//// System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	//// System.out.println("content_subname : " +
	// j_tour_list.get(i).getCourse().get(j).getSubname());
	//// System.out.println("content_subdetailoverview : " +
	// j_tour_list.get(i).getCourse().get(j).getSubdetailoverview());
	//// System.out.println("content_subdetailimg : " +
	// j_tour_list.get(i).getCourse().get(j).getSubdetailimg());
	//// }
	////
	//// }
	//
	// }

	// @Test
	// public void testJTour() throws UnsupportedEncodingException{
	//
	// String request = "코스부산";
	// String real_request = request.replaceFirst("코스", "");
	// int contentid = 0;
	// int contenttypeid = 0;
	// JTourApi j_tour = new JTourApi(real_request);
	// ArrayList<JTourCourse> j_tour_list = j_tour.getCourseList();
	//
	//// for(int i = 0; i < j_tour_list.size() ; i++){
	////// System.out.println("name :" + j_tour_list.get(i).getTitle());
	////// System.out.println("content id : " +
	// j_tour_list.get(i).getContentid());
	//// contentid = j_tour_list.get(i).getContentid();
	////// System.out.println("contentd type id :" +
	// j_tour_list.get(i).getContenttypeid());
	//// contenttypeid = j_tour_list.get(i).getContenttypeid();
	//// }
	//
	// System.out.println(contentid + " " + contenttypeid);
	// ArrayList<JTourCourseContent> jtour_course_content_list =
	// j_tour.tourGetCourseResult(j_tour.tourCourseSearch(2393457, 25));
	//// StringBuilder jtour_result = jtour.tourCourseSearch(25, 2525);
	//
	//// for(int i = 0 ; i < jtour_course_content_list.size(); i++){
	////
	//// System.out.println("subname : " +
	// jtour_course_content_list.get(i).getSubname());
	//// System.out.println("img : " +
	// jtour_course_content_list.get(i).getSubdetailimg());
	//// System.out.println("overview : " +
	// jtour_course_content_list.get(i).getSubdetailoverview());
	//// }
	//
	// System.out.println(j_tour.tourCourseOverviewSearch(2022923, 25));
	// JTourCourseOverview over = new JTourCourseOverview();
	// over =
	// j_tour.tourCourseOverviewGet(j_tour.tourCourseOverviewSearch(2022923,
	// 25));
	// System.out.println(over.getFirstimage());
	// System.out.println(over.getOverview());
	// System.out.println(over.getTitle());
	// System.out.println(over.getMapx());
	// System.out.println(over.getMapy());

	//// }
	// @Test
	// public void test() throws UnsupportedEncodingException {
	// String request = "부산";
	// request = URLEncoder.encode(request, "UTF-8");
	// JTourApi j_tour = new JTourApi();
	// StringBuilder result = j_tour.tourKeywordSearch(request);
	//
	//
	//
	// String response_message = request + "의 코스 추천 정보입니다!\n 자세한 사항을 알고 싶다면 url을
	//// 클릭하세요~! \n\n";
	// ArrayList<JTourCourse> j_tour_list =
	//// j_tour.tourKeywordSearchResult(result);
	// for(int i = 0 ; i < j_tour_list.size() ; i ++){
	// response_message += j_tour_list.get(i).getTitle();
	// response_message += "\n";
	// response_message +=
	//// "http://13.124.143.250:8080/ICT_Nailro_Project/course/"+request;
	// response_message += "\n";
	// }
	//
	// System.out.println(response_message);
	// }
	@Test
	public void barcodeTest() throws BarcodeException, OutputException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		JBarcode bar = new JBarcode();
		
		DisCountCoupon dis = context.getBean("disCountCoupon", DisCountCoupon.class);
		
		DisCountCouponObj obj = new DisCountCouponObj();
		obj = dis.getDisCountCoupon();
		
		System.out.println("!@#!@#!@#!@#!@#!@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("barcode :" + obj.getDis_barcode());
		System.out.println("shopname :" + obj.getDis_shop_name());
		System.out.println("ownername :" + obj.getDis_owner_name());
		System.out.println("shopPhoto :" + obj.getDis_shop_photo());
		System.out.println("shop_dsc :" + obj.getDis_shop_description());
		System.out.println("shop_addr :" + obj.getDis_shop_addr());
		
		bar.getBarcode(obj.getDis_barcode());
		
		
	}

}
