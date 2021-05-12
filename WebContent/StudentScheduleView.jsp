<%@page import="model.StudentSchedule"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Schedule</title>
</head>
<body>

	<h2 class="center">
		<b>Display a Session</b>
	</h2>
	<table id="myTable" class="table table-white table-bordered compact"
		style="width: 90%; background-color: white" data-page-length="25"
		data-order="[[ 1, &quot;asc&quot; ]]">
		<thead>
			<tr style="background-color: green">
				<th>Booking ID</th>
				<th>Course Code</th>
				<th>Student ID</th>
				<th>Classroom</th>				
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
				<td><%=s.getRoom_ID()%></td>

			</tr>
			<%}%>
		</tbody>
	</table>
	<div class="container">
		<button onclick="goBack()">Go Back</button>
	</div>

</body>
</html>