<%@ page contentType="text/html;charset=utf-8"%>
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
				<b>부산</b>
			</h1>
			<h6>
				이국적인 바다풍경의 <span class="w3-tag">부산</span>
			</h6>
		</header>


		<!-- Image header -->
		<header class="w3-display-container w3-wide w3-margin-bottom" id="home">
			<img class="w3-image w3-col"
				src="https://s3.ap-northeast-2.amazonaws.com/ictnailro/busan_logo.jpg">
		</header>
	
		
		<!-- Grid -->
		<div class="w3-row w3-padding w3-border">

			<!-- Blog entries -->
			<div class="w3-col">

				<!-- Blog entry -->
				<div class="w3-container w3-white w3-margin w3-padding-large">
					<div class="w3-center">
						<h3>부산 감천문화마을</h3>
						<p>
							주소 : <span class="w3-opacity">부산광역시 사하구 감내2로 203 (감천동)</span>
						</p>
					</div>

					<div class="w3-justify">
						<img
							src="https://s3.ap-northeast-2.amazonaws.com/ictnailro/example_busan.jpg"
							alt="Girl Hat" style="width: 100%" class="w3-padding-16">
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
							<button class="w3-button w3-black" onclick="myFunction('demo1')"
								id="myBtn">
								<b>Replies  </b> <span class="w3-tag w3-white">1</span>
							</button>
						</p>
					</div>
				</div>

				<div class="w3-container w3-white w3-margin w3-padding-large">
					<div class="w3-center">
						<h3>광안리해수욕장</h3>
						<p>
							주소 : <span class="w3-opacity">부산광역시 수영구 광안해변로 219 (광안동)</span>
						</p>
					</div>

					<div class="w3-justify">
						<img
							src="https://s3.ap-northeast-2.amazonaws.com/ictnailro/busan_sea.jpg"
							alt="Girl Hat" style="width: 100%" class="w3-padding-16">
						<div class="show">
							<a class="w3-center"><span class="w3-tag w3-blue">상세정보를
									보려면 클릭하세요!</span></a>
							<div class="description">
								<p>
									* 남태평양 코발트빛 해변을 닮은 도심 속 해변, 광안리해수욕장 *<br /> &nbsp;광안리해수욕장은
									부산광역시 수영구 광안2동에 있으며 해운대 해수욕장의 서쪽에 위치하고 있다. 총면적 82,000㎡, 길이
									1.4km, 사장폭은 25~110m의 질 좋은 모래사장이 있고, 지속적인 수질 정화를 실시하여 인근의 수영강에
									다시 고기가 살 수 있을 정도로 깨끗한 수질을 자랑하며, 특히 젊은이들이 즐겨 찾는 명소이다. 광안리에서는
									해수욕뿐 아니라 독특한 분위기를 자아내는 레스토랑, 카페 등과 시내 중심가 못지않은 유명 패션상가들이 즐비하며,
									다양한 먹을거리, 볼거리가 있어서 피서의 즐거움을 더해준다. 특히 밤이 되면 광안대교의 아름다운 야경이 장관이다.<br />
									<br /> 해수욕장 주변에는 낭만이 깃든 카페거리와 300여 곳의 횟집이 있고 야외무대가 설치되어 있어서 부산
									바다축제를 비롯한 다양한 축제가 개최되고 있으며, 해변을 찾는 피서객을 위한 공연도 있다. 인근의 수영강에서는
									낚시를 할 수도 있고, 싱싱한 회를 즉석에서 맛볼 수도 있으며 올림픽 요트 경기장이 있어서 요트를 탈 수도 있다.
									숙박시설도 잘 갖추어져 있다. 해변과 인접해 있는 호텔을 이용해도 되고 알뜰한 피서를 원한다면 인근 금련산에
									소재한 청소년수련원를 이용하면 된다. 이곳에는 텐트 설치가 가능하며 숙박동도 대여해 주고 취사시설도 완비되어
									있다. 해수욕장 인근에는 다양한 문화시설들이 있는데 남천해변의 자유바다를 비롯하여 KBS, MBC 방송국이
									있으며, MBC 내에는 개봉관인 시네마홀 극장도 있다. 피서철에는 다양한 축제가 열리므로 피서객들에게 즐길 수
									있는 문화공간도 제공한다.<br /> <br /> * 광안리해변 카페거리 *<br /> 광안리해변에는
									100여 개의 카페가 있다. 음악과 칵테일과 낭만이 깃든 카페에서 바라보는 해수욕장과 광안대교는 아름답기
									그지없다. 광안대교에서 이곳을 바라보면 마치 동화 속 유럽의 한 도시를 여행하고 있는 듯한 착각을 할 만큼 예쁘게
									꾸며져 있다. 광안리 해수욕장과 인접해 있어 가족단위나 친구·연인과의 만남을 위한 장소이기도 하다. 또한
									이곳에서는 음식과 술뿐만 아니라 야외음악도 감상할 수 있다. <br>
								</p>

							</div>
						</div>
						<p class="w3-left">
							<button class="w3-button w3-white w3-border"
								onclick="likeFunction(this)">
								<b><i class="fa fa-thumbs-up"></i> Like</b>
							</button>
						</p>
						<p class="w3-right">
							<button class="w3-button w3-black" onclick="myFunction('demo1')"
								id="myBtn">
								<b>Replies  </b> <span class="w3-tag w3-white">1</span>
							</button>
						</p>
					</div>
				</div>


				<hr>
				<!-- END About/Intro Menu -->
			</div>
			<!-- END GRID -->
		</div>
		<!-- END w3-content -->
	</div>

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
