<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="dbAccess.DataHandler, java.sql.ResultSet, java.util.ArrayList" %>
<%
DataHandler data = (DataHandler) session.getAttribute("data");
ResultSet rs = data.getProofs();
%>
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
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">  
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
                        <div class="logo">
                           AUS
                        </div>
                        <!-- ***** Logo End ***** -->
                        <!-- ***** Menu Start ***** -->
                        <ul class="nav">
                            <li><a href="adminindex.jsp">Home</a></li>
                            <li><a href="adminindex.jsp#book">Manage</a></li>
                 
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

	<div class="right-image-decor"></div>
	<div class="right-img"
		data-scroll-reveal="enter right _move 30px over 0.6s after 0.4s"></div>
	<!-- Profile Start -->
	<div class="container">
		<!-- <div class = "row"> -->
		<div class="main-body"
			data-scroll-reveal="enter right move 30px over 0.6s after 0.4s">
			<div class="row gutters-sm">
				<div class="col-md-8">
					<div class="card mb-3">
						<div class="card-body">
							<% 
								while(rs.next())
								{ 
									ResultSet rs2 = data.getAccountInformation(rs.getInt("user_ID"));
									String id = rs.getString("user_ID");
									String pid = rs.getString("pic_ID");
									String name = rs2.getString("fname") + " " + rs2.getString("lname");
									String email = rs2.getString("email");
									%>
									<form id="picture-select" method="post" action="ApproveDenyProofServlet">
									<label for="picture"><%=id%> <%=name%> <br><%=email%> <br>
									<input type="checkbox" name="checkedPics" value=<%=pid%>> <br>
									<a href="DisplayImage?pic_ID=<%=pid%>" target="new">
									<img width="400" height="300" src="DisplayImage?pic_ID=<%=pid%>">
									</a>
									</label>
									<% 
								}
							%>
						<hr>
						<button type="submit" id="form-submit" name="deny" class="main-button" style="float: right;">Deny</button>
						<button type="submit" id="form-submit" name="approve"class="main-button" style="float: right;">Approve</button>
						</form>
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