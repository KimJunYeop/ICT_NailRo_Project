package com.javalec.spring_ex_pjt;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.javalec.Response.ResRecommendRegion;
import com.javalec.message.Keyboard;
import com.javalec.message.Message;
import com.javalec.message.MessageButton;
import com.javalec.message.RequestMessage;
import com.javalec.message.ResponseMessageVO;
import com.javalec.s3.S3UploadAndList;

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
	public ResponseMessageVO message(@RequestBody RequestMessage req_msg) throws SQLException {
		ResponseMessageVO res_vo = new ResponseMessageVO();
		Message msg = new Message();
		Keyboard keyboard = new Keyboard();

		if (req_msg.getContent().equals("처음으로")) {
			msg.setText("내일로 봇에 오신것을 환영합니다!\n" + "내일로 봇으로 여행정보를 얻으세요!\n " + "추천코스, 여행지정보 또한 할인혜택까지!(하트뿅)");
			keyboard = new Keyboard(new String[] { "메뉴얼", "할인혜택", "추천코스검색", "여행지정보", "오픈채팅방입장" });
		} else if (req_msg.getContent().equals("추천코스검색")) {
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
		} else if (req_msg.getContent().equals("부산")) {
			String text = "부산은 아름다운 항구도시입니다.\n" + "부산에 대한 자세한 관광지 정보는 아래 url을 클릭하세요!\n";
			msg = messageWithMessageButton(msg, text, "URL", "http://13.124.143.250:8080/ICT_Nailro_Project/busan");
		} else if (req_msg.getContent().equals("오픈채팅방입장")) {
			String text = "내일로 오픈채팅방에 오신 것을 환영합니다!\n" + "아래 링크를 클릭하세요.";
			msg = messageWithMessageButton(msg, text, "오픈채팅방입장", "https://open.kakao.com/o/gUUCJQx");
		} else if (req_msg.getContent().equals("할인혜택")) {

		} else {
			msg.setText("해당 단어에 대한 정보가 없습니다.\n");
			keyboard = new Keyboard(new String[] { "처음으로" });
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
		res_region.setRegion("강원도");
		msg.setText(res_region.getRecommendRegion());
		return msg;
	}

	/*
	 * 여행지 검색시 route
	 */
	@RequestMapping(value = "/busan", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws SQLException {
		return "busan";
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

		System.out.println(upload_file);
		s3.uploadFile(upload_file);

		response.sendRedirect("http://13.124.143.250:8080/ICT_Nailro_Project/awsTest");
	}

	@RequestMapping(value = "/s3list", method = RequestMethod.POST)
	public void getS3List(HttpServletResponse response) throws IOException {
		response.sendRedirect("http://13.124.143.250:8080/ICT_Nailro_Project/awsTest");
	}

	// @Test
	// public void test(){
	// S3UploadAndList s3 = new S3UploadAndList();
	// System.out.println(s3.getS3File());
	// }

	// @Test
	// public void test() throws SQLException{
	// ResponseMessageVO res_vo = new ResponseMessageVO();
	// Message msg = new Message();
	// Keyboard keyboard = new Keyboard();
	// MessageButton msg_button = new MessageButton();
	// @SuppressWarnings("resource")
	// ApplicationContext context = new
	// GenericXmlApplicationContext("applicationContext.xml");
	//
	// ResRecommendRegion res_region = context.getBean("resRecommendRegion",
	// ResRecommendRegion.class);
	// res_region.setRegion("전라도");
	// msg.setText(res_region.getRecommendRegion());
	// keyboard = new Keyboard();
	//
	// res_vo.setKeyboard(keyboard);
	// res_vo.setMessage(msg);
	//
	// System.out.print(msg);
	// }

}