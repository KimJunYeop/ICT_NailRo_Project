<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.javalec.object.JTourCourseContent"%>
<%@ page import="com.javalec.object.JTourCourseOverview"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<JTourCourseContent> jtour_course_list = (ArrayList<JTourCourseContent>) request
			.getAttribute("jtour_course");

	JTourCourseOverview jtour_overview = (JTourCourseOverview) request.getAttribute("jtour_overview");
%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>코스추천Page</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/course_template/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Theme CSS -->
<link
	href="${pageContext.request.contextPath}/resources/course_template/css/freelancer.min.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/resources/course_template/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<style>
  #map {
        height: 400px;
        width: 100%;
       }
</style>

</head>

<body id="page-top" class="index">
	<div id="skipnav">
		<a href="#maincontent">Skip to main content</a>
	</div>
	<!-- Navigation -->
	<nav id="mainNav"
		class="navbar navbar-default navbar-fixed-top navbar-custom">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> Menu <i
					class="fa fa-bars"></i>
			</button>
			<a class="navbar-brand" href="#page-top">코스추천</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<li class="page-scroll"><a href="#portfolio">코스추천</a></li>
				<li class="page-scroll"><a href="#about">위치</a></li>
				<li class="page-scroll"><a href="#contact">Contact</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- Header -->
	<header>


	<div class="container" id="maincontent" tabindex="-1">
		<div class="row">
			<div class="col-lg-12">
				<!-- <img class="img-responsive" src="${pageContext.request.contextPath}/resources/busan_logo.jpg" alt=""> -->
				<%
					if (jtour_overview.getFirstimage() == "") {
				%>
				<img class="img-responsive"
					src="https://s3.ap-northeast-2.amazonaws.com/ictnailro/s3/nailro.png"
					alt="">
				<%
					} else {
				%>
				<img class="img-responsive"
					src="<%=jtour_overview.getFirstimage()%>" alt="">
				<%
					}
				%>

				<div class="intro-text">
					<h2 class="name"><%=jtour_overview.getTitle()%></h1>
						<hr class="star-light">
						<span class="skills"><%=jtour_overview.getOverview()%></span>
				</div>
			</div>
		</div>
	</div>
	</header>

	<!-- Portfolio Grid Section -->
	<section id="portfolio">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center page-header">
				<h2>코스추천 <br> <small> 자세한 정보는 사진을 터치하세요.</small></h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<%
				for (int i = 0; i < jtour_course_list.size(); i++) {
			%>
			<div style="margin-left: 10px;">
				<h3><%=i + 1%>
					<%=jtour_course_list.get(i).getSubname()%>
				</h3>
			</div>

			<div class="portfolio-item">
				<a href="#portfolioModal<%=i%>" class="portfolio-link"
					data-toggle="modal">
					<div class="caption">
						<div class="caption-content">
							<i class="fa fa-search-plus fa-3x"></i>
						</div>
					</div> 
				<% if (jtour_course_list.get(i).getSubdetailimg() == null) { %> 
				<img src="https://s3.ap-northeast-2.amazonaws.com/ictnailro/s3/noimage.png" class="img-responsive center-block"> 
				<%	} else { %> 
					<img src="<%=jtour_course_list.get(i).getSubdetailimg()%>" class="img-responsive"> 
			 	<% } %>
				
				</a>
			</div>
			</br>
			<%
				}
			%>
		</div>
	</div>
	</section>


	<!-- About Section -->
	<section class="success" id="about">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>위치</h2>
				<hr class="star-light">
			</div>
		</div>
		<div class="row">
		
			<div id="map">
			</div>
			
		</div>
	</div>
	</section>

	<!-- Contact Section -->
	<!-- 
	<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Contact Me</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<form name="sentMessage" id="contactForm" novalidate>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label for="name">Name</label> <input type="text"
								class="form-control" placeholder="Name" id="name" required
								data-validation-required-message="Please enter your name.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label for="email">Email Address</label> <input type="email"
								class="form-control" placeholder="Email Address" id="email"
								required
								data-validation-required-message="Please enter your email address.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label for="phone">Phone Number</label> <input type="tel"
								class="form-control" placeholder="Phone Number" id="phone"
								required
								data-validation-required-message="Please enter your phone number.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label for="message">Message</label>
							<textarea rows="5" class="form-control" placeholder="Message"
								id="message" required
								data-validation-required-message="Please enter a message."></textarea>
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<br>
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Send</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
 	-->
	<!-- Footer -->
	<footer class="text-center">
	<div class="footer-above">
		<div class="container">
			<div class="row">
				
			</div>
		</div>
	</div>
	
	<div class="footer-below">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">Copyright &copy; Your Website 2016</div>
			</div>
		</div>
	</div>
	</footer>
	

	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div
		class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
		<a class="btn btn-primary" href="#page-top"> <i
			class="fa fa-chevron-up"></i>
		</a>
	</div>

	<!-- Portfolio Modals -->
	<%
		for (int i = 0; i < jtour_course_list.size(); i++) {
	%>
	<div class="portfolio-modal modal fade" id="portfolioModal<%=i%>"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<h2><%=jtour_course_list.get(i).getSubname()%></h2>
							<hr class="star-primary">
							<%
								if (jtour_course_list.get(i).getSubdetailimg() == null) {
							%>
							<img
								src="https://s3.ap-northeast-2.amazonaws.com/ictnailro/s3/noimage.png"
								class="img-responsive center-block">

							<%
								} else {
							%>
							<img src="<%=jtour_course_list.get(i).getSubdetailimg()%>"
								class="img-responsive">
							<%
								}
							%>
							<p class="text-left">
								<%=jtour_course_list.get(i).getSubdetailoverview()%>
							</p>
							</br>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<i class="fa fa-times"></i> Close
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
	
	<!-- google map 사용 webpage -->
	<script>
	 function initMap() {
		 	var mapX= <%= jtour_overview.getMapx() %>
		 	var mapY= <%= jtour_overview.getMapy() %>
		 	var map;
			var marker; 
			var latlng = new google.maps.LatLng(mapY,mapX);
			
			var myOptions = {
			zoom: 15,
			center: latlng,
			mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			map = new google.maps.Map(document.getElementById("map"), myOptions);
			marker = new google.maps.Marker({
				position:map.getCenter(),
				map:map,
				draggable:true   
			    }); 
		 	
	      }
	</script>


	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/resources/course_template/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/course_template/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

	<!-- Contact Form JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/course_template/js/jqBootstrapValidation.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/course_template/js/contact_me.js"></script>

	<!-- Theme JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/course_template/js/freelancer.min.js"></script>
		
	<!-- Google Map -->
	<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCAORoQxE7xFn2X2t53D8iLF29VR5J9ChI&callback=initMap">
    </script>

</body>

</html>
