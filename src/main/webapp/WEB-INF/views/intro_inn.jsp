<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="org.json.JSONObject"%>
<%
	JSONArray details = (JSONArray) request.getAttribute("details");
	JSONArray intros = (JSONArray) request.getAttribute("intros");
	String name = (String) request.getAttribute("city_name");
%>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Oswald">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open Sans">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
h1, h2, h3, h4, h5, h6 {
	font-family: "Oswald"
}

body {
	font-family: "Open Sans"
}

.show a {
	cursor: pointer;
}

.show .description {
	display: none;
}
</style>
<body class="w3-light-grey">
	<div class="w3-content" style="max-width: 1600px">
		<!-- Header -->
		<header class="w3-container w3-center w3-padding-48 w3-white">
			<h1 class="w3-xxxlarge">
				<b><%=name%></b>
			</h1>
		</header>

		<!-- Grid -->
		<div class="w3-row w3-padding w3-border">

			<!-- Blog entries -->
			<div class="w3-col">
				<!-- google place api Jplace Array List 가져오기. -->
				<%
					for (int i = 0; i < details.length(); i++) {
						String review_id = "review_modal" + i;
						JSONObject detail = details.getJSONObject(i);
						JSONObject intro = intros.getJSONObject(i);
				%>
				<!-- Blog entry -->
				<div class="w3-container w3-white w3-margin w3-padding-large">
					<div class="w3-center">
						<!-- place의 이름 , 주소, 평점-->
						<h3><%=detail.get("title").toString() %></h3>
						<p>
							<span class="w3-opacity"><%=detail.get("addr1") %></span>
						</p>
						<p class="w3-right">
							TEL. <%=detail.get("tel") %>
						</p>
					</div>

					<div class="w3-justify">
						<img src="<%=detail.get("firstimage")%>" alt="이미지가 없습니다." style="width: 100%"
							class="w3-padding-16">

						<div class="show">
							<a class="w3-center"><span class="w3-tag">상세정보 보기</span></a>
							<div class="description">
								<p style="line-height:2em">
									<font size="2">
										<strong>· 예약 안내: </strong><%=intro.get("reservationlodging")%><br>
										<strong>· 객실 수: </strong><%=intro.get("roomcount")%><br>
										<strong>· 객실 유형: </strong><%=intro.get("roomtype")%><br>
										<strong>· 체크 인: </strong><%=intro.get("checkintime")%><br>
										<strong>· 체크 아웃: </strong><%=intro.get("checkouttime")%><br>
										<strong>· 취사 여부: </strong><%=intro.get("chkcooking")%><br>	
										<strong>· 식음료장: </strong><%=intro.get("foodplace")%><br>
										<strong>· 부대시설: </strong><%=intro.get("subfacility")%><br>
									</font>
								</p>
							</div>
						</div>
						<p class="w3-left">
							▶ <%=detail.get("homepage") %>
						</p>
						<p class="w3-right">
							<button class="w3-button w3-black"
								onclick="document.getElementById('<%=review_id%>').style.display='block'"
								id="myBtn">
								<b>Review</b> <span class="w3-tag w3-white"></span>
							</button>
						</p>
					</div>
				</div>

				<div id=<%=review_id%> class="w3-modal">
					<div class="w3-modal-content w3-card-4">
						<header class="w3-container w3-teal w3-grey">
							<span
								onclick="document.getElementById('<%=review_id%>').style.display='none'"
								class="w3-button w3-display-topright w3-large">&times;</span>
							<h2>사용자 리뷰 목록</h2>
						</header>
						<div class="w3-container">
							<div class="w3-row w3-section w3-card ">
								<div class="w3-col" style="width: 50px; margin-left: 30px;">
									<i class="w3-xxlarge fa fa-user"></i>
								</div>
								<div class="w3-rest">
									<p></p>
								</div>

								<div class="w3-rest">
									<p class="w3-right">
										평점 :
										</p>
								</div>
								<p></p>
							</div>

						</div>
						<footer class="w3-container w3-teal w3-grey">
							<p>닫으시려면 우측 상단의 x를 클릭하세요.</p>
						</footer>
					</div>
				</div>

				<script>
					var modal = document.getElementById(<%=review_id%>);
					// When the user clicks anywhere outside of the modal, close it
					window.onclick = function(event) {
						if (event.target == modal) {
							modal.style.display = "none";
						}
					}
				</script>
				<!-- for문 끝 -->
				<%
					}
				%>
				<hr>
			</div>
			<!-- END About/Intro Menu -->
		</div>
		<!-- END GRID -->
	</div>
	<!-- END w3-content -->
	<!-- Footer -->
	<footer class="w3-container w3-dark-grey" style="padding: 32px">
		<p class="w3-left w3-margin-left">
			Powered by <a href="https://www.w3schools.com/w3css/default.asp"
				target="_blank">Nailro</a>
		</p>
		<a href="#"
			class="w3-button w3-black w3-right w3-padding-large w3-margin-bottom"><i
			class="fa fa-arrow-up w3-margin-right"></i>To the top</a>

	</footer>

	<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
	<script>
		// html dom 이 다 로딩된 후 실행된다.
		$(document).ready(function() {
			// menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
			$(".show>a").click(function() {
				var submenu = $(this).next("div");

				// submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
				if (submenu.is(":visible")) {
					submenu.slideUp();
				} else {
					submenu.slideDown();
				}
			});

		});
	</script>


</body>
</html>
