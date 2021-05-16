<%@page import="model.StudentSchedule"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="TemplateMo">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>View Schedule</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">  

    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">

    <link rel="stylesheet" href="assets/css/templatemo-lava.css">

    <link rel="stylesheet" href="assets/css/owl-carousel.css">
    
     <link rel="stylesheet" href="assets/css/tablestyle.css">
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
<div class ="table">
	<table id="myTable" class="table" data-page-length="25"
		data-order="[[ 1, &quot;asc&quot; ]]">

		<thead>
					<h2>Display a Session
	</h2>
			<tr style="background-color: rgb(164, 62, 46)">
				<th>Booking ID</th>
				<th>CRN</th>
				<th>User ID</th>
				<th>Reservation Owner</th>
				<th>Room Name</th>	
				<th>Date</th>
				<th>FROM</th>	
				<th>TO </th>
				<th>Nature of Booking </th>					
			</tr>
		</thead>
		<tbody>
			<%List<StudentSchedule> list =  (List<StudentSchedule>)request.getAttribute("list"); 
        for(StudentSchedule s:list){%>
			<%-- Arranging data in tabular form 
        --%>
			<tr>
				<td><%=s.getBooking_ID()%></td>
				<td><%=s.getCourse_code()%></td>
				<td><%=s.getUser_ID()%></td>
				<td><%=s.getUser_Name()%></td>
				<td><%=s.getRoom_ID()%></td>
				<td><%=s.getDate()%></td>	
				<td><%=s.getStart()%></td>	
				<td><%=s.getEnd()%></td>	
				<td><%=s.getType()%></td>	
			</tr>
			<%}%>
		</tbody>
	</table>
</div>
 <div class="right-image-decor"></div>
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