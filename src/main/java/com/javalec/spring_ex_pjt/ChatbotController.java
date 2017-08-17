package com.javalec.spring_ex_pjt;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.xml.sax.SAXException;

import com.javalec.Response.ResDiscountCoupon;
import com.javalec.Response.ResRecommendRegion;
import com.javalec.Response.ResWeather;
import com.javalec.discount.DisCountCoupon;
import com.javalec.discount.DisCountCouponObj;
import com.javalec.manual.ManualContactMessage;
import com.javalec.message.Keyboard;
import com.javalec.message.Message;
import com.javalec.message.MessageButton;
import com.javalec.message.Photo;
import com.javalec.message.RequestMessage;
import com.javalec.message.ResponseMessageVO;
import com.javalec.object.JTourCourse;
import com.javalec.object.JTourCourseContent;
import com.javalec.object.JTourCourseOverview;
import com.javalec.object.ManualContactUser;
import com.javalec.parse.ParseArea;
import com.javalec.s3.S3UploadAndList;
import com.javalec.tourAPI.JTourApi;
import com.javalec.tourAPI.TourAPI;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

@Controller
public class ChatbotController {

	/*
	 * keyboard api
	 */
	@ResponseBody
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	public Keyboard keyboard() {
		// 초반에 1번 keyboard 초기화
		Keyboard keyboard = new Keyboard(new String[] { "메뉴얼", "할인혜택", "추천코스검색", "여행지정보", "축제정보", "오픈채팅방입장" });

		return keyboard;
	}

	/*
	 * message api
	 * 
	 * @RequestBody 는 html을 java 객체로 변환해준다.
	 */
	@ResponseBody
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ResponseMessageVO message(@RequestBody RequestMessage req_msg)
			throws SQLException, IOException, UnsupportedEncodingException {
		ResponseMessageVO res_vo = new ResponseMessageVO();
		Message msg = new Message();
		Keyboard keyboard = new Keyboard();
		ParseArea area = new ParseArea();

		if (req_msg.getContent().equals("처음으로")) {
			msg.setText("내일로 봇에 오신것을 환영합니다!\n" + "내일로 봇으로 여행정보를 얻으세요!\n " + "추천코스, 여행지정보 또한 할인혜택까지!(하트뿅)\n");
			keyboard = new Keyboard(new String[] { "메뉴얼", "할인혜택", "추천코스검색", "여행지정보", "오픈채팅방입장" });
		} else if (req_msg.getContent().equals("추천코스검색")) {
			Photo photo = new Photo();
			photo.setUrl("https://s3.ap-northeast-2.amazonaws.com/ictnailro/s3/nailro_recommend_course.png");
			photo.setWidth(200);
			photo.setHeight(100);

			msg.setPhoto(photo);
			msg.setText("당신에게 딱 맞는 추천코스!\n 바로 여기에!(하트)\n 맞줌형 추천코스, 도별 추천코스 중 선택하세요.");

			keyboard = new Keyboard(new String[] { "맞춤형 추천코스", "도별 추천코스" });
		} else if (req_msg.getContent().equals("메뉴얼")) {
			String text = "내일로 챗봇의 홈페이지입니다. 내일로 챗봇 팀원 및 사용방법을 보시려면 url을 클릭하세요!";
			;
			msg = messageWithMessageButton(msg, text, "오픈채팅방입장",
					"http://13.124.143.250:8080/ICT_Nailro_Project/manual");
		} else if (req_msg.getContent().equals("맞춤형 추천코스")) {
			// 추천 코스로 들어가기 위한 안내문.
			msg.setText("추천 받으실 도시의 이름을 코스와 함께 입력해주세요! \n ex ) 코스서울");
			keyboard = new Keyboard();
		} else if (req_msg.getContent().matches("코스.*.*.*.*")) {
			// 코스 추천. 코스서울 입력시 서울에 대한 관광코스 제공
			String request = req_msg.getContent().replaceFirst("코스", "");
			String request_encoder = URLEncoder.encode(request, "UTF-8");

			JTourApi j_tour = new JTourApi();
			StringBuilder result = j_tour.tourKeywordSearch(request_encoder);
			String response_message = null;

			ArrayList<JTourCourse> j_tour_list = j_tour.tourKeywordSearchResult(result);
			if (j_tour_list.size() > 0) {
				response_message = request + "의 코스 추천 정보입니다!\n 자세한 사항을 알고 싶다면 url을 클릭하세요~! \n\n";
				for (int i = 0; i < j_tour_list.size(); i++) {
					response_message += ((i + 1) + " " + j_tour_list.get(i).getTitle());
					response_message += "\n";
					response_message += "http://13.124.143.250:8080/ICT_Nailro_Project/course?" + "id="
							+ j_tour_list.get(i).getContentid() + "&type=" + j_tour_list.get(i).getContenttypeid();
					response_message += "\n\n";
				}
				msg.setText(response_message);
			} else {
				response_message = request + "에 대한 관광정보가 없습니다. 죄송합니다. \n 처음을 돌아가시려면 \"처음으로\" 를 입력하세요";
			}

			msg.setText(response_message);
			keyboard = new Keyboard();
		} else if (req_msg.getContent().equals("도별 추천코스")) {
			msg.setText("지역 추천코스");
			keyboard = new Keyboard(new String[] { "전라도", "경상도", "강원도", "충청도" });
		} else if (req_msg.getContent().equals("전라도")) {
			msg = regionRecommendLogic("전라도", msg);
		} else if (req_msg.getContent().equals("경상도")) {
			msg = regionRecommendLogic("경상도", msg);
		} else if (req_msg.getContent().equals("강원도")) {
			msg = regionRecommendLogic("강원도", msg);
		} else if (req_msg.getContent().equals("충청도")) {
			msg = regionRecommendLogic("충청도", msg);
		} else if (req_msg.getContent().equals("오픈채팅방입장")) {
			String text = "내일로 오픈채팅방에 오신 것을 환영합니다!\n" + "아래 링크를 클릭하세요.";
			msg = messageWithMessageButton(msg, text, "오픈채팅방입장", "https://open.kakao.com/o/gUUCJQx");
		} else if (req_msg.getContent().equals("할인혜택")) {
			msg.setText("내일로 봇의 다양한 할인 혜택을 만나보세요! \n 아래의 쿠폰받기를 눌러주세요!");
			keyboard = new Keyboard(new String[] { "쿠폰받기" });
		} else if (req_msg.getContent().equals("쿠폰받기")) {
			DisCountCoupon disCC = new DisCountCoupon();
			//DisCountCouopon 조회 후 출력.
		} else if (req_msg.getContent().equals("여행지정보")) {
			msg.setText("여행지 정보를 얻으세요! \n" + "도시와 카테고리를 입력해주세요. \n" + "도시만 입력한 경우, 통합결과를 제공해요(씨익)\n"
					+ " ---------------------- \n\n" + "1. 관광\n" + "2. 문화\n" + "3. 축제\n" + "4. 음식\n"
					+ "\nEX ) 서울, 부산축제, 강릉관광");
		} else if (area.isTrue(req_msg.getContent().toString())) {
			// 입력받은 값의 앞 두글자가 DB상에
			// 존재하는 지역이름인 경우 여행지의 타입을 설정
			area.setContentType(req_msg.getContent().toString());

			// 해당 지역의 날씨 정보를 조회
			ResWeather weather = new ResWeather();
			weather.response(area.getAreaName());
			String text = weather.getText();

			msg = messageWithMessageButton(msg, text, "URL", "http://13.124.143.250:8080/ICT_Nailro_Project/region/"
					+ area.getAreaName() + area.getContentType());
		} else {
			msg.setText("입력하신 문장이 적절하지 않습니다. 다시 입력하시거나 \n 처음 메뉴로 돌아가고 싶으시면 \"처음으로\"를 입력해주세요.");
		}

		res_vo.setKeyboard(keyboard);
		res_vo.setMessage(msg);
		return res_vo;
	}

	/*
	 * MessageButton 생성 method
	 */
	private Message messageWithMessageButton(Message msg, String text, String label, String url) {
		MessageButton msg_button = new MessageButton();
		msg.setText(text);
		msg_button.setLabel(label);
		msg_button.setUrl(url);
		msg.setMessage_button(msg_button);
		return msg;
	}

	/*
	 * 지역추천 message 호출 method
	 */
	private Message regionRecommendLogic(String region, Message msg) throws SQLException {
		@SuppressWarnings("resource")
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		ResRecommendRegion res_region = context.getBean("resRecommendRegion", ResRecommendRegion.class);
		res_region.setRegion(region);
		msg.setText(res_region.getRecommendRegion());
		return msg;
	}

	// 할인쿠폰을 발급해주는 method, DB에서 쿠폰에 대한 정보를 가져온다.
	private Message responseDiscountCoupon(String region, Message msg) throws SQLException {
		@SuppressWarnings("resource")
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		// 할인쿠폰 컨테이너 클래스를 가져오는 로직
		ResDiscountCoupon resDiscountCoupon = context.getBean("resDiscountCoupon", ResDiscountCoupon.class);
		// 컨테이너 클래스에 입력받은 파라미터의 지역을 기록하는 로직
		resDiscountCoupon.setRegion(region);
		// Message 객체에 담는 로직
		msg.setText(resDiscountCoupon.getText());
		msg.setPhoto(resDiscountCoupon.getPhoto());
		return msg;
	}

	/*
	 * discount page
	 */
	@RequestMapping(value = "/discount", method = RequestMethod.GET)
	public String discountPageLoading() {
		return "discount_page";
	}

	/*
	 * discount create
	 */
	@RequestMapping(value = "/discount_create", method = RequestMethod.POST)
	public String discountCreate(MultipartHttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException, BarcodeException, OutputException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		String dis_owner_name = request.getParameter("dis_owner_name");
		String dis_shop_name = request.getParameter("dis_shop_name");
		String dis_shop_addr = request.getParameter("dis_shop_addr");
		String dis_shop_description = request.getParameter("dis_shop_description");
		String dis_shop_photo = "https://s3.ap-northeast-2.amazonaws.com/ictnailro/discount/";
		String dis_barcode = "";

		DisCountCoupon disCC = context.getBean("disCountCoupon", DisCountCoupon.class);
		dis_barcode = disCC.createBarcode();

		// s3에 파일 업로드
		S3UploadAndList s3 = new S3UploadAndList();
		MultipartFile report = request.getFile("uploadfile");
		File upload_file;
		upload_file = s3.multipartToFile(report);
		s3.uploadFile(upload_file, "discount");
		dis_shop_photo += upload_file.getName();

		DisCountCouponObj discc_obj = new DisCountCouponObj(dis_owner_name, dis_shop_name, dis_shop_addr,
				dis_shop_description, dis_shop_photo, dis_barcode);
		System.out.println("discc_obj.getname() : " + discc_obj.getDis_owner_name());
		System.out.println(dis_barcode);
		System.out.println(discc_obj.getDis_barcode());
		disCC.insertDisCountCoupon(discc_obj);

		return "discount_page";
	}

	@RequestMapping(value = "/awsTest", method = RequestMethod.GET)
	public String awsTest(Locale local, Model model) {
		// model.addAttribute("test","123");
		S3UploadAndList s3 = new S3UploadAndList();
		ArrayList<ArrayList<String>> s3_list = new ArrayList<ArrayList<String>>();

		s3_list = s3.getS3File();
		model.addAttribute("s3_list", s3_list);

		return "javaS3";
	}

	@RequestMapping(value = "/s3upload", method = RequestMethod.POST)
	public void submitReport(MultipartHttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {

		S3UploadAndList s3 = new S3UploadAndList();
		MultipartFile report = request.getFile("uploadfile");
		File upload_file;
		upload_file = s3.multipartToFile(report);

		s3.uploadFile(upload_file, "s3");
	}

	@RequestMapping(value = "/s3list", method = RequestMethod.POST)
	public void getS3List(HttpServletResponse response) throws IOException {
		response.sendRedirect("http://13.124.143.250:8080/ICT_Nailro_Project/awsTest");
	}

	/*
	 * 여행지 검색시 route
	 */
	@RequestMapping(value = "/region/{str}", method = RequestMethod.GET)
	public String home(@PathVariable("str") String name, Locale locale, Model model)
			throws SQLException, XPathExpressionException, IOException, SAXException, ParserConfigurationException {

		TourAPI tour = new TourAPI();
		JSONArray details = new JSONArray();
		JSONArray intros = new JSONArray();
		JSONArray images = new JSONArray();
		String type = new String();
		String region = new String();

		type = name.substring(name.length() - 2, name.length());
		region = name.split(type)[0];

		if (type.equals("관광")) {
			ArrayList<String> contentid = tour.areaBased(region, "12", "3");
			details = tour.contentDetail(contentid);
			intros = tour.introAttraction(contentid);
			images = tour.imageDetail(contentid, "12");

			model.addAttribute("city_name", region);
			model.addAttribute("details", details);
			model.addAttribute("intros", intros);
			model.addAttribute("images", images);

			return "attraction";
		} else if (type.equals("문화")) {
			ArrayList<String> contentid = tour.areaBased(region, "14", "3");
			details = tour.contentDetail(contentid);
			intros = tour.introCulture(contentid);
			images = tour.imageDetail(contentid, "14");

			model.addAttribute("city_name", region);
			model.addAttribute("details", details);
			model.addAttribute("intros", intros);
			model.addAttribute("images", images);

			return "culture";
		} else if (type.equals("축제")) {
			ArrayList<String> contentid = tour.searchFestival(region, "3");
			details = tour.contentDetail(contentid);
			intros = tour.introFestival(contentid);
			images = tour.imageDetail(contentid, "15");

			model.addAttribute("city_name", region);
			model.addAttribute("details", details);
			model.addAttribute("intros", intros);
			model.addAttribute("images", images);

			return "festival";
		} else if (type.equals("음식")) {
			ArrayList<String> contentid = tour.areaBased(region, "39", "3");
			details = tour.contentDetail(contentid);
			intros = tour.introFood(contentid);
			images = tour.imageDetail(contentid, "39");

			model.addAttribute("city_name", region);
			model.addAttribute("details", details);
			model.addAttribute("intros", intros);
			model.addAttribute("images", images);

			return "food";
		} else {
			ArrayList<String> contentid = tour.areaBased(region, "", "3");
			details = tour.contentDetail(contentid);
			images = tour.imageDetail(contentid, "15");

			model.addAttribute("city_name", region);
			model.addAttribute("details", details);
			model.addAttribute("images", images);

			return "region_infomation";
		}
	}

	/*
	 * 코스 정보 url
	 */

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String course(@RequestParam(value = "id") int content_id, @RequestParam(value = "type") int content_type_id,
			Locale locale, Model model) {
		JTourApi j_tour = new JTourApi();
		JTourCourseOverview overview = new JTourCourseOverview();

		overview = j_tour.tourCourseOverviewGet(j_tour.tourCourseOverviewSearch(content_id, content_type_id));

		ArrayList<JTourCourseContent> jtour_course_content_list = j_tour
				.tourGetCourseResult(j_tour.tourCourseSearch(content_id, content_type_id));
		model.addAttribute("jtour_course", jtour_course_content_list);
		model.addAttribute("jtour_overview", overview);

		return "course";
	}

	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public String google(Locale locale, Model model) throws SQLException {
		return "google";
	}

	/*
	 * 메뉴얼 페이지
	 */
	@RequestMapping(value = "/manual", method = RequestMethod.GET)
	public String discount(Locale locale, Model model) {

		return "manual_page";
	}

	/*
	 * 메뉴얼 Contact
	 */
	@RequestMapping(value = "/manual_contact", method = RequestMethod.POST)
	public String manualContact(HttpServletRequest httpServeletRequest) {
		@SuppressWarnings("resource")
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

		String user_name = httpServeletRequest.getParameter("user_name");
		String user_email = httpServeletRequest.getParameter("user_email");
		String user_phone = httpServeletRequest.getParameter("user_phone");
		String user_message = httpServeletRequest.getParameter("user_message");

		ManualContactUser mc_user = new ManualContactUser(user_name, user_email, user_phone, user_message);
		System.out.println(mc_user.getUser_message());
		System.out.println(mc_user.getUser_name());
		System.out.println(mc_user.getUser_email());
		System.out.println(mc_user.getUser_phone());

		ManualContactMessage mc_message = context.getBean("menualContactMessage", ManualContactMessage.class);
		mc_message.insertContactMessage(mc_user);

		return "manual_page";
	}
}