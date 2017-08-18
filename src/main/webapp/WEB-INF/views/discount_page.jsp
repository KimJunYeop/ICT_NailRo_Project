<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!--[if lt IE 7]><html lang="en" class="no-js ie6"><![endif]-->
<!--[if IE 7]><html lang="en" class="no-js ie7"><![endif]-->
<!--[if IE 8]><html lang="en" class="no-js ie8"><![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<!--<![endif]-->

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>내일로챗봇</title>

<!--  커스텀 CSS -->
<link
	href="${pageContext.request.contextPath}/resources/manual_template/css/manual.css"
	rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/manual_template/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/resources/manual_template/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<!-- Theme CSS -->
<link
	href="${pageContext.request.contextPath}/resources/manual_template/css/discount.css"
	rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" integrity="sha384-0s5Pv64cNZJieYFkXYOTId2HMA2Lfb6q2nAcx2n0RTLUnCAoTTsS0nKEO27XyKcY" crossorigin="anonymous"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" integrity="sha384-ZoaMbDF+4LeFxg6WdScQ9nnR1QC2MIRxA1O9KWEXQwns1G8UNyIEZIQidzb0T1fo" crossorigin="anonymous"></script>
    <![endif]-->

</head>

<body id="page-top" class="index">

	<!-- Navigation -->
	<nav id="mainNav"
		class="navbar navbar-default navbar-custom navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> Menu <i
					class="fa fa-bars"></i>
			</button>
			<a class="navbar-brand page-scroll" href="#page-top">모두의 내일로</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<li><a class="page-scroll" href="#discount">할인쿠폰 등록하기</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- Header -->
	<header>
	<div class="container">
		<div class="intro-text">
			<div class="intro-lead-in">카카오톡 플러스친구에서</div>
			<div class="intro-lead-in">"내일로챗봇" 을 검색하세요.</div>
			<div class="intro-heading">내일로챗봇 할인쿠폰 등록 page</div>
			<!-- <a href="#services" class="page-scroll btn btn-xl">Tell Me More</a> !-->
			</br> </br> </br> </br> </br>
		</div>
	</div>
	</header>
	<!-- Services Section -->
	<section id="discount">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">할인쿠폰 등록!</h2>
				<h3 class="section-subheading text-muted">할인쿠폰을 등록해서 내일러들에게 매장을
					홍보하세요!</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<form action="discount_create" method="post"
					enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="Your Name *" name="dis_owner_name" required
									data-validation-required-message="Please enter your name.">
								<p class="help-block text-danger"></p>
							</div>
							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="Your Shop Name *" name="dis_shop_name" required
									data-validation-required-message="Please enter your shopname.">
								<p class="help-block text-danger"></p>
							</div>
							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="Your Shop Address *" name="dis_shop_addr" required
									data-validation-required-message="Please enter your shop address.">
								<p class="help-block text-danger"></p>
							</div>
							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="Your Discount Type *       ex) 할인 10%" name="dis_type" required
									data-validation-required-message="Please enter your discount type.">
								<p class="help-block text-danger"></p>
							</div>
							<div class="form-group">
								<!-- <input type="text" class="form-control"
									placeholder="Your Shop Photo *" name="dis_shop_photo" required
									data-validation-required-message="Please upload your shop photo."> -->
								
								<h2><span class="label label-warning">매장 사진 등록</span></h2></br>
								 <input type="file" name="uploadfile" required
									data-validation-required-message="Please upload your shop photo.">
								 <p class="help-block text-danger"></p>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<textarea class="form-control"
									placeholder="Your Shop Description *"
									name="dis_shop_description" required
									data-validation-required-message="Please enter a shop descriotion."
									rows="8"></textarea>
								<p class="help-block text-danger"></p>
							</div>
						</div>
						<div class="clearfix"></div>
						</br>
						<div class="col-lg-12 text-center">
							<div id="success"></div>
							<button type="submit" class="btn btn-xl">Create Coupon</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="row text-center"></div>
	</div>
	</section>




	<footer>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<span class="copyright">Copyright &copy; Nailro_Project 2017</span>
			</div>

			<div class="col-md-4"></div>

			<div class="col-md-4">
				<ul class="list-inline quicklinks">
					<li><a href="#">내일로 챗봇</a></li>
				</ul>
			</div>
		</div>
	</div>
	</footer>



	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/resources/manual_template/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/manual_template/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"
		integrity="sha384-mE6eXfrb8jxl0rzJDBRanYqgBxtJ6Unn4/1F7q4xRRyIw7Vdg9jP4ycT7x1iVsgb"
		crossorigin="anonymous"></script>

	<!-- Contact Form JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/manual_template/js/jqBootstrapValidation.js"></script>

	<!-- Theme JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/manual_template/	js/agency.min.js"></script>

</body>

</html>
