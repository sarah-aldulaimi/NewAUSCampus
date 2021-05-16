<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dbAccess.DataHandler, java.util.ArrayList" %>
<% 
System.out.println(session.getAttribute("id"));
System.out.println(session.getAttribute("type"));
DataHandler data = new DataHandler(); 
ArrayList<String> course_names = data.getAvailableCourseNames((int)session.getAttribute("id"), (int)session.getAttribute("type"));
ArrayList<String> course_CRNs = data.getAvailableCourseCRNs((int)session.getAttribute("id"), (int)session.getAttribute("type"));
ArrayList<String> rooms = data.getAvailableRooms();
ArrayList<String> timings = data.getAvailableTimings();
ArrayList<String> timing_IDs = data.getAvailableTimingIDs();
%>
<!DOCTYPE html>
<html lang="en">

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
<title>Book</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">  
<link rel="stylesheet" type="text/css"
	href="assets/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="assets/css/font-awesome.css">

<link rel="stylesheet" href="assets/css/templatemo-lava.css">

<link rel="stylesheet" href="assets/css/owl-carousel.css">

<link rel="stylesheet" href="assets/css/formstyle.css">
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
                        <div class="logo">
                           AUS
                        </div>
                        <!-- ***** Logo End ***** -->
                        <!-- ***** Menu Start ***** -->
                        <ul class="nav">
                            <li><a href="staffindex.html">Home</a></li>
                            <li><a href="staffindex.html#book">Book</a></li>
                            <li><a href="staffindex.html#faq">FAQ</a>
                            </li>
  
								<li class="submenu">
                                <a href="javascript:;"><i class="fa fa-user-circle"></i></a>
                                <ul>
                                    <li><a href="ViewProfile.jsp">View Profile</a></li>
         
                                    <li><a href="index.html" >Sign out</a></li>
  
                                </ul>
                            </li>
						</ul>
                        <a class='menu-trigger'>
                            <span>Menu</span>
                        </a>
                        <!-- ***** Menu End ***** -->
                    </nav>
                </div>
            </div>
        </div>
        	
    </header>
    <!-- ***** Header Area End ***** -->

	<div class="left-image-decor"></div>

	<!-- ***** Features Big Item Start ***** -->
	<section class="section" id="promotion">
		<div class="container">
			<div class="row">
				<div
					class="left-image col-lg-5 col-md-12 col-sm-12 mobile-bottom-fix-big"
					data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">
					<img src="assets/images/palm2.png"
						class="rounded img-fluid d-block mx-auto" alt="App">
				</div>
				<div
					class="right-text offset-lg-1 col-lg-6 col-md-12 col-sm-12 mobile-bottom-fix"
						data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">
					<div class="wrapper" id="form-wrapper">
						<div class="inner">
							<form id="booking-form" method="post" action="ReserveServlet">
								<h3>Exam Form</h3>
								<div class="form-group">
									<div class="form-wrapper">
										<label for="">Course</label>
										<div class="box">
											<select name = "course">
											<%
											for(int i=0;i<course_names.size();i++)
											{
												String cname = course_CRNs.get(i) + " - " + course_names.get(i);
												System.out.println(cname);
												System.out.println(session.getAttribute("id"));
											%>
											<option value="<%=course_CRNs.get(i)%>"><%=cname%></option>
											<% 
											}
											%>
											</select>
										</div>
									</div>
									<div class="form-wrapper">
										<label for="">Room</label> 					
										<div class="box">
											<select name = "room">
											<%
											for(int i=0;i<rooms.size();i++)
											{
												System.out.println(rooms.get(i));
											%>
											<option value="<%=rooms.get(i)%>"><%=rooms.get(i)%></option>
											<% 
											}
											%>
											</select>
										</div>
									</div>
								</div>
						<div class="form-wrapper">
									<label for="">Date</label> 
									<input type="date" id ="date" name="date" placeholder="yyyy-mm-dd" />
								</div>
									<div class="form-group">
	
								<div class="form-wrapper">
									<label for="">Time</label>  
									<div class="box">
								  		<select name = "time">
											<%
											for(int i=0;i<timings.size();i++)
											{
												System.out.println(timings.get(i));
											%>
											<option value="<%=timing_IDs.get(i)%>"><%=timings.get(i)%></option>
											<% 
											}
											%>
										</select>
										</div>
								</div>			
</div>
								<div class="checkbox">
									<label> <input type="checkbox"> I accept the
										Terms of Use & Privacy Policy. <span class="checkmark"></span>
									</label>
								</div>
								<button type="submit" id="form-submit" class="main-button">Submit</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ***** Features Big Item End ***** -->

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
	<script src="assest.datepicker.js"></script>

	<!-- Global Init -->
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/main.js"></script>
</body>
</html>