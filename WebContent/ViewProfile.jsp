<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="TemplateMo">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>View Profile</title>

<link rel="stylesheet" type="text/css"
	href="assets/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="assets/css/font-awesome.css">

<link rel="stylesheet" href="assets/css/templatemo-lava.css">

<link rel="stylesheet" href="assets/css/profile.css">

</head>

<body>

	<!-- ***** Preloader Start ***** -->
	<div id="preloader">
		<div class="jumper">
			<div></div>
			<div></div>
			<div></div>
		</div>
	</div>
	<!-- ***** Preloader End ***** -->


	<!-- ***** Header Area Start ***** -->
	<header class="header-area header-sticky">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav class="main-nav">
						<!-- ***** Logo Start ***** -->
						<a href="index.html" class="logo"> AUS </a>
						<!-- ***** Logo End ***** -->
						<!-- ***** Menu Start ***** -->
						<ul class="nav">
							<li><a href="staffindex.html">Home</a></li>
							<li><a href="staffindex.html#book">Book</a></li>
							<li><a href="staffindex.html#faq">FAQ</a></li>
							<li><a href="staffindex.html#contact-us">Contact Us</a></li>
							<li class="submenu"><a href="javascript:;"><i
									class="fa fa-user-circle"></i></a>
								<ul>
									<li><a href="ViewProfile.html">View Profile</a></li>
									<li><a href="">Sign out</a></li>
								</ul></li>
						</ul>
						<a class='menu-trigger'> <span>Menu</span>
						</a>
						<!-- ***** Menu End ***** -->
					</nav>
				</div>
			</div>
		</div>
	</header>
	<!-- ***** Header Area End ***** -->

	<div class="right-image-decor"></div>
	<div class="right-img"
		data-scroll-reveal="enter right _move 30px over 0.6s after 0.4s"></div>
		<%
String name = request.getParameter("full_name");
String email = request.getParameter("email");
// String id = request.getAttribute("id").toString();
%>
	<!-- Profile Start -->
	<div class="container">
		<!-- <div class = "row"> -->
		<div class="main-body"
			data-scroll-reveal="enter right move 30px over 0.6s after 0.4s">
			<div class="row gutters-sm">
				<div class="col-md-8">
					<div class="card mb-3">
						<div class="card-body">
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Full Name</h6>
								</div>
								<div class="col-sm-9 text-secondary"><%out.println(name);%></div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Email</h6>
								</div>
								<div class="col-sm-9 text-secondary"><%out.println(email);%></div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">ID</h6>
								</div>
								<div class="col-sm-9 text-secondary">(239) 816-9029</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Mobile</h6>
								</div>
								<div class="col-sm-9 text-secondary">(320) 380-4539</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Major</h6>
								</div>
								<div class="col-sm-9 text-secondary">Bay Area, San
									Francisco, CA</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Covid documents -->
			<div class="row gutters-sm">
				<div class="col-md-8">
					<div class="card mb-3">
						<div class="card-body">
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Vaccine Card</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="file" id="Vfile" name="Vfile">
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Date of Dose</h6>
								</div>
								<div class="col-sm-9 text-secondary">Date</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">PCR Test</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="file" id="PCRfile" name="PCRfile">
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Date of Test</h6>
								</div>
								<div class="col-sm-9 text-secondary">Date</div>
							</div>
							<hr>
							<button type="submit" id="form-submit" class="main-button"
								style="float: right;">Submit</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- jQuery -->
	<script src="assets/js/jquery-2.1.0.min.js"></script>

	<!-- Bootstrap -->
	<script src="assets/js/popper.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- Plugins -->
	<script src="assets/js/owl-carousel.js"></script>
	<script src="assets/js/scrollreveal.min.js"></script>
	<script src="assets/js/waypoints.min.js"></script>
	<script src="assets/js/jquery.counterup.min.js"></script>
	<script src="assets/js/imgfix.min.js"></script>

	<!-- Global Init -->
	<script src="assets/js/custom.js"></script>

</body>
</html>