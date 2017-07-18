<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.javalec.gapi.JPlace"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="org.json.JSONObject"%>
<%
	ArrayList<JPlace> jplace = (ArrayList<JPlace>) request.getAttribute("place");
	String name = (String) request.getAttribute("city_name");
	String API_KEY = "AIzaSyDC2VbtwngPu8iC0mla2B2EM3MonDYSeFQ";
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


		<!-- Image header -->
		<!-- <header class="w3-display-container w3-wide w3-margin-bottom" id="home">
			<img class="w3-image w3-col"
				src="CmRaAAAAg_n2JbTzeZSSEE2OWjP88DwJH6u6QyVQQlzSpa-v0m0OT1YRbr2LF_AwWtxdjeEs50bUtIYAN2CPB07kgCo4nI2_H2a9DssyU66OwqilVksTkKEWnKJJoSbcMBLNpZ4REhCEnid8B3AAvneHFIRcL5imGhQkhwGcPrX0LOrkfl2HkU_efYjVqQ">
		</header> -->


		<!-- Grid -->
		<div class="w3-row w3-padding w3-border">

			<!-- Blog entries -->
			<div class="w3-col">
				<!-- google place api Jplace Array List 가져오기. -->
				<%
					for (int i = 0; i < jplace.size(); i++) {
						JPlace place = jplace.get(i);
						String review_id = "review_modal" + i;
				%>
				<!-- Blog entry -->
				<div class="w3-container w3-white w3-margin w3-padding-large">
					<div class="w3-center">
						<!-- place의 이름 , 주소, 평점-->
						<h3><%=place.getName()%></h3>
						<p>
							주소 : <span class="w3-opacity"><%=place.getFormatted_address()%></span>
						</p>
						<p class="w3-right">
							평점 :
							<%=place.getPlace_rating()%>
						</p>
					</div>

					<div class="w3-justify">
						<%
							String img_url = "https://maps.googleapis.com/maps/api/place/photo?" + "maxwidth=400"
										+ "&photoreference=" + place.getPhoto_reference() + "&key=" + API_KEY;
						%>
						<img src="<%=img_url%>" alt="이미지가 없습니다." style="width: 100%"
							class="w3-padding-16">

						<div class="show">
							<a class="w3-center"><span class="w3-tag w3-blue">상세정보를
									보려면 클릭하세요!</span></a>
							<div class="description">
								<p>감천문화마을은 1950년대 6.25 피난민의 힘겨운 삶의 터전으로 시작되어 현재에 이르기까지 부산의
									역사를 그대로 간직하고 있는 곳이다. 산자락을 따라 질서정연하게 늘어선 계단식 집단 주거형태와 모든 길이 통하는
									미로같은 골목길의 경관은 감천만의 독특함을 보여준다.</p>
							</div>
						</div>
						<p class="w3-left">
							<button class="w3-button w3-white w3-border"
								onclick="likeFunction(this)">
								<b><i class="fa fa-thumbs-up"></i> Like</b>
							</button>
						</p>
						<p class="w3-right">
							<button class="w3-button w3-black"
								onclick="document.getElementById('<%=review_id%>').style.display='block'"
								id="myBtn">
								<b>Review</b> <span class="w3-tag w3-white">1</span>
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
						<div
							class="w3-container">
							<%
								JSONArray review_array = jplace.get(i).getReviews();
									for (int j = 0; j < review_array.length(); j++) {
										JSONObject review_obj = (JSONObject) review_array.get(j);
							%>
							<div class="w3-row w3-section w3-card ">
								<div class="w3-col" style="width: 50px;margin-left: 30px;">
									<i class="w3-xxlarge fa fa-user"></i>
								</div>
								<div class="w3-rest">
									<p><%=review_obj.getString("author_name")%></p>
								</div>
								
								<div class="w3-rest">
									<p class="w3-right">평점 : <%=review_obj.getDouble("rating")%></p>
								</div>
								<p><%=review_obj.getString("text")%></p>
							</div>

							<%
								}
							%>
						</div>
						<footer class="w3-container w3-teal w3-grey">
							<p>닫으시려면 우측 상단의 x를 클릭하세요.</p>
						</footer>
					</div>
				</div>

				<script>
					var modal = document.getElementById(
				<%=review_id%>
					);
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