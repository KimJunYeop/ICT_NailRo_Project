<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="org.json.JSONObject"%>
<%
	JSONArray details = (JSONArray) request.getAttribute("details");
	JSONArray images = (JSONArray) request.getAttribute("images");
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
.bgimg{
	background-image: url("${pageContext.request.contextPath}/resources/image/main_logo.jpg");
    min-height: 10%;
    background-attachment: fixed;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}

#main{
	padding: 0px 16px 5px 16px;
}

.w3-wide {letter-spacing: 10px;}
.w3-hover-opacity {cursor: pointer;}

.firstImages {
display:none;
width:100%; 
max-height: 300px
}

.secondImages {
display:none;
width:100%; 
max-height: 300px
}
.demo {cursor:pointer}

.imageSlide {white-space: nowrap; overflow-x: auto; overflow-y: hidden;}
.thumbnail {max-height: 11vh ; width: 30%; display: inline-block; overflow-y: hidden;}

/* Turn off parallax scrolling for tablets and phones */
@media only screen and (max-device-width: 1024px) {
    .bgimg {
        background-attachment: scroll;
    }
}
</style>
<body>

<!-- First Parallax Image with Logo Text -->
<div class="bgimg w3-opacity-min w3-display-container" id="home">
	<div class="w3-display-middle" style="white-space:nowrap;">
   		<span class="w3-xlarge w3-wide"><b><%=name%></b></span>
	</div>
</div>
	
<!-- Container (About Section) -->
<div id="main" class="w3-content  w3-display-container">
	<%
		int imageIndex = 1;
		for (int i = 0; i < details.length(); i++) {
			JSONObject detail = details.getJSONObject(i);
			JSONObject image = images.getJSONObject(i);
	%>
  	<div class="w3-display-container mySlides">
  		<div class="w3-col w3-center">
   			<p class= "w3-xlarge"><b><%=detail.get("title").toString()%></b></p>
   			<img class="firstImages" src="<%=detail.get("firstimage")%>">
   			<img class="secondImages" src="<%=detail.get("firstimage")%>">
   			<% for(int j=0; j<image.length()-1; j++){	%>
   				<img class="secondImages" src="<%=image.get("img" + j) %>">
 			<% 
 			}%>
 		</div>
   		
   		<div class="w3-bar w3-border-bottom">
   			<button class="w3-bar-item w3-button w3-small  tablink w3-black w3-hover-black" onclick="openMenu(event,'overview<%=i%>')">Overview</button>
    		<button class="w3-bar-item w3-button w3-small  tablink w3-hover-black" onclick="openMenu(event,'homepage<%=i%>')">Homepage</button>
    		<button class="w3-bar-item w3-button w3-small  tablink w3-hover-black" onclick="openMenu(event,'images<%=i%>')">Images</button>			
			<a class="w3-bar-item w3-button w3-small w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
   				<i class="fa fa-bars"></i>
   			</a>
		</div>
		
		<div id="overview<%=i%>" class="w3-container menu">
    		<p><font size="2"><%=detail.get("overview").toString() %></font></p>
 		</div>
 		
 		<div id="homepage<%=i%>" class="w3-container menu" style="display:none">
    		<p style="line-height:2em">
				<font size="2">
					<strong>·</strong><%=detail.get("homepage").toString() %><br>	
				</font>
			</p>
  		</div>
  		
  		<div id="images<%=i%>" class="menu"  style="display:none">
    		<div class = "imageSlide">
    			<div class="thumbnail">
      				<img class="demo w3-opacity w3-hover-opacity-off" src="<%=detail.get("firstimage")%>" style="width:100%" onclick="currentDiv(<%=imageIndex++%>)">
   				</div>
    			<%
    				for(int j=0; j<image.length()-1; j++) {
    			%>
    			<div class="thumbnail">
      				<img class="demo w3-opacity w3-hover-opacity-off" src="<%=image.get("img" + j)%>" style="width:100%" onclick="currentDiv(<%=imageIndex++%>)">
   				</div>
    			<%}%>
    			
    		</div>
  		</div>
	</div>
	<%}%>

	<button class="w3-button w3-display-topleft w3-hover-white w3-padding-32" onclick="plusDivs(-1)">&#10094;</button>
	<button class="w3-button w3-display-topright w3-hover-white w3-padding-32" onclick="plusDivs(+1)">&#10095;</button>
</div>

<script>

// change page
var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
	showDivs(slideIndex += n);
}

function currentDiv(n) {
	showImage(n);
}

function showDivs(n) {
	var i;
 	var x = document.getElementsByClassName("mySlides");
 	var y = document.getElementsByClassName("firstImages");
 	var z = document.getElementsByClassName("secondImages");
 	if (n > x.length) {slideIndex = 1}    
  	if (n < 1) {slideIndex = x.length}
  	for (i = 0; i < x.length; i++) {
    	 x[i].style.display = "none";  
  	}
  	x[slideIndex-1].style.display = "block"; 
	y[slideIndex-1].style.display = "block";
	for(i=0; i<z.length();i++){
		z[i].style.dispaly = "none";
	}
}

function showImage(n) {
	  var i;
	  var x = document.getElementsByClassName("secondImages");
	  var y = document.getElementsByClassName("firstImages");
	  var dots = document.getElementsByClassName("demo");
	  for (i = 0; i < y.length; i++) {
			 y[i].style.display = "none";
		  }
	  for (i = 0; i < x.length; i++) {
	     x[i].style.display = "none";
	  }
	  for (i = 0; i < dots.length; i++) {
	     dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
	  }
	  x[n-1].style.display = "block";
	  dots[n-1].className += " w3-opacity-off";
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
	  evt.currentTarget.className += " w3-black";
	}
</script>	

</body>
</html>