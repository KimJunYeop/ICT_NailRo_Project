package com.javalec.spring_ex_pjt;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.json.JSONArray;
import org.json.JSONObject;
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

import com.javalec.Response.ResRecommendRegion;
import com.javalec.Response.ResWeather;
import com.javalec.Response.ResDiscountCoupon;
import com.javalec.gapi.Descending;
import com.javalec.gapi.GooglePlace;
import com.javalec.gapi.JPlace;
import com.javalec.message.Keyboard;
import com.javalec.message.Message;
import com.javalec.message.MessageButton;
import com.javalec.message.Photo;
import com.javalec.message.RequestMessage;
import com.javalec.message.ResponseMessageVO;
import com.javalec.s3.S3UploadAndList;
import com.javalec.tourAPI.TourAPI;

@Controller
public class ChatbotController {

	/*
	 * keyboard api
	 */
	@ResponseBody
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	public Keyboard keyboard() {
		// 초반에 1번 keyboard 초기화
		Keyboard keyboard = new Keyboard(new String[] { "메뉴얼", "할인혜택", "추천코스검색", "여행지정보", "오픈채팅방입장" });

		return keyboard;
	}

	/*
	 * message api
	 * 
	 * @RequestBody 는 html을 java 객체로 변환해준다.
	 */
	@ResponseBody
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ResponseMessageVO message(@RequestBody RequestMessage req_msg) throws SQLException, IOException {
		ResponseMessageVO res_vo = new ResponseMessageVO();
		Message msg = new Message();
		Keyboard keyboard = new Keyboard();

		if (req_msg.getContent().equals("처음으로")) {
			msg.setText("내일로 봇에 오신것을 환영합니다!\n" + "내일로 봇으로 여행정보를 얻으세요!\n " + "추천코스, 여행지정보 또한 할인혜택까지!(하트뿅)");
			keyboard = new Keyboard(new String[] { "메뉴얼", "할인혜택", "추천코스검색", "여행지정보", "오픈채팅방입장" });
		} else if (req_msg.getContent().equals("추천코스검색")) {
			Photo photo = new Photo();
			photo.setUrl("https://s3.ap-northeast-2.amazonaws.com/ictnailro/s3/nailro_recommend_course.png");
			photo.setWidth(200);
			photo.setHeight(100);

			msg.setPhoto(photo);
			msg.setText("당신에게 딱 맞는 추천코스!\n 바로 여기에!(하트)\n 맞줌형 추천코스, 도별 추천코스 중 선택하세요.");

			keyboard = new Keyboard(new String[] { "맞춤형 추천코스", "도별 추천코스" });
		} else if (req_msg.getContent().equals("맞춤형 추천코스")) {
			msg.setText("추천 받으실 도시의 이름을 코스와 함께 입력해주세요! \n ex ) 코스서울");
			keyboard = new Keyboard();
		} else if (req_msg.getContent().matches("코스.*.*")) {
			// 코스 추천. 코스서울 입력시 서울에 대한 관광코스 제공
			msg.setText(req_msg.getContent());
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
		} else if (req_msg.getContent().equals("여행지정보")) {
			msg.setText("여행지 정보를 얻으세요! \n" + "알고자하는 도시의 이름을 입력하세요. \n" + "EX ) 부산");
		} else if (req_msg.getContent().equals("오픈채팅방입장")) {
			String text = "내일로 오픈채팅방에 오신 것을 환영합니다!\n" + "아래 링크를 클릭하세요.";
			msg = messageWithMessageButton(msg, text, "오픈채팅방입장", "https://open.kakao.com/o/gUUCJQx");
		} else if (req_msg.getContent().equals("할인혜택")) {
			msg.setText("내일로 봇의 다양한 할인 혜택을 만나보세요!");
			keyboard = new Keyboard(new String[] { "전라도의 혜택", "경상도의 혜택", "강원도의 혜택", "충청도의 혜택" });
		} else if (req_msg.getContent().equals("전라도의 혜택")) {
			msg = responseDiscountCoupon("전라도", msg);
		} else if (req_msg.getContent().equals("경상도의 혜택")) {
			msg = responseDiscountCoupon("경상도", msg);
		} else if (req_msg.getContent().equals("강원도의 혜택")) {
			msg = responseDiscountCoupon("강원도", msg);
		} else if (req_msg.getContent().equals("충청도의 혜택")) {
			msg = responseDiscountCoupon("충청도", msg);
		} else {
			ResWeather weather = new ResWeather();
			weather.response(req_msg.getContent());
			String text = weather.getText();
			msg = messageWithMessageButton(msg, text, "URL", "http://13.124.143.250:8080/ICT_Nailro_Project/region/" + req_msg.getContent());
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

		s3.uploadFile(upload_file);

		response.sendRedirect("http://13.124.143.250:8080/ICT_Nailro_Project/awsTest");
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
		JSONArray details = tour.search(name);
		
		model.addAttribute("city_name", name);
		model.addAttribute("details", details);

		return "region_infomation";
	}

	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public String google(Locale locale, Model model) throws SQLException {
		return "google";
	}
}