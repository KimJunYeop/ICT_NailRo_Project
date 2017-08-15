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
<title><%=name%> 상세정보</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif;}
body, html {
    height: 100%;
    color: #777;
    line-height: 1.8;
}

/* Create a Parallax Effect */
.bgimg-1, .bgimg-2, .bgimg-3 {
    background-attachment: fixed;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}

/* First image (Logo. Full height) */
.bgimg-1 {
	background-image: url("${pageContext.request.contextPath}/resources/image/main_logo.jpg");
    min-height: 10%;
}

/* Second image (Portfolio) */
.bgimg-2 {
    background-image: url("/w3images/parallax2.jpg");
    min-height: 400px;
}

/* Third image (Contact) */
.bgimg-3 {
    background-image: url("/w3images/parallax3.jpg");
    min-height: 400px;
}

.w3-wide {letter-spacing: 10px;}
.w3-hover-opacity {cursor: pointer;}

/* Turn off parallax scrolling for tablets and phones */
@media only screen and (max-device-width: 1024px) {
    .bgimg-1, .bgimg-2, .bgimg-3 {
        background-attachment: scroll;
    }
}
</style>
<body>

<!-- First Parallax Image with Logo Text -->
<div class="bgimg-1 w3-display-container" id="home">
	<div class="w3-display-middle" style="white-space:nowrap;">
   		<span class="w3-xlarge w3-wide"><b><%=name%></b></span>
	</div>
</div>
	
<!-- Container (About Section) -->
<div id="main" class="w3-content  w3-display-container">
	<%
		for (int i = 0; i < details.length(); i++) {
			JSONObject detail = details.getJSONObject(i);
			JSONObject intro = intros.getJSONObject(i);
	%>
  	<div class="w3-display-container mySlides">
  		<div class="w3-col w3-center">
   			<p class= "w3-xlarge"><b><%=detail.get("title").toString()%></b></p>
   			<img src="<%=detail.get("firstimage")%>" style="width:97%; max-height: 300px">
   		</div>
   		
   		<div class="w3-bar w3-border-bottom">
   			<button class="w3-bar-item w3-button w3-small  tablink w3-hover-black" onclick="openMenu(event,'overview<%=i%>')">Overview</button>
    		<button class="w3-bar-item w3-button w3-small  tablink w3-hover-black" onclick="openMenu(event,'detail<%=i%>')">Detail</button>
    		<button class="w3-bar-item w3-button w3-small  tablink w3-hover-black" onclick="openMenu(event,'homepage<%=i%>')">Homepage</button>
			<button class="w3-bar-item w3-button w3-small  tablink w3-hover-black" onclick="openMenu(event,'images<%=i%>')">Images</button>			
			<a class="w3-bar-item w3-button w3-small w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
   				<i class="fa fa-bars"></i>
   			</a>
		</div>
		
		<div id="overview<%=i%>" class="w3-container menu" style="display:none">
    		<p><%=detail.get("overview").toString() %></p>
 		</div>
 		
 		<div id="detail<%=i%>" class="w3-container menu" style="display:none">
    		<p style="line-height:2em">
				<font size="2">
					<strong>· 문의 및 안내: </strong><%=intro.get("infocenter")%><br>
					<strong>· 이용 시기: </strong><%=intro.get("useseason")%><br>
					<strong>· 이용 시간: </strong><%=intro.get("usetime")%><br>
					<strong>· 쉬는 날: </strong><%=intro.get("restdate")%><br>
					<strong>· 주차시설: </strong><%=intro.get("parking")%><br>
					<strong>· 신용카드 가능 여부: </strong><%=intro.get("chkcreditcard")%><br>	
				</font>
			</p>
  		</div>
 		
  		<div id="homepage<%=i%>" class="w3-container menu" >
    		<p><%=detail.get("homepage").toString() %></p>
  		</div>
  		
  		<div id="images<%=i%>" class="w3-container menu" >
    		<p><%=detail.get("homepage").toString() %></p>
  		</div>
	</div>
	<%}%>

	<button class="w3-button w3-display-topleft w3-hover-white w3-padding-32" onclick="plusDivs(-1)">&#10094;</button>
	<button class="w3-button w3-display-topright w3-hover-white w3-padding-32" onclick="plusDivs(1)">&#10095;</button>
</div>

<script>

var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  if (n > x.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";  
  }
  x[slideIndex-1].style.display = "block";  
}

// Change style of navbar on scroll
window.onscroll = function() {myFunction()};
function myFunction() {
    var navbar = document.getElementById("myNavbar");
    if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        navbar.className = "w3-bar" + " w3-card-2" + " w3-animate-top" + " w3-white";
    } else {
        navbar.className = navbar.className.replace(" w3-card-2 w3-animate-top w3-white", "");
    }
}

// Used to toggle the menu on small screens when clicking on the menu button
function toggleFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}

function openMenu(evt, mainMenu) {
	  var i, x, tablinks;
	  x = document.getElementsByClassName("menu");
	  for (i = 0; i < x.length; i++) {
	      x[i].style.display = "none";
	  }
	  tablinks = document.getElementsByClassName("tablink");
	  for (i = 0; i < x.length; i++) {
	      tablinks[i].className = tablinks[i].className.replace(" w3-black", "");
	  }
	  document.getElementById(mainMenu).style.display = "block";
	}
</script>	

</body>
</html>