<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Questions</title>
</head>
<body>
	<div class="text-right">
		<a href="/courses" class="btn btn-info m-3">Dashboard</a>
		<a href="/logout" class="btn btn-warning m-3">Logout</a>
	</div>
	<div class="container">
		<h1>${course.name}</h1>
		<h4>Instructor: ${course.instructor}</h4>
		<h4>Capacity: ${course.capacity}</h4>
		
		<div>
			<table class="table border border-dark">
				<thead>
					<tr class="table-dark">
						<th style="width:40%">Courses</th>
						<th style="width:40%">Sign Up Date</th>
						<th style="width:20%">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${course.users}" var="u">
					<tr>
						<td>${u.name}</td>
						<td>${u.createdAt}</td>
						<td class="text-center">
							
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<div><a href="/courses/${course.id}/edit" class="btn btn-dark m-3">Edit course</a></div> 
			    <div><form action="/courses/${course.id}/delete" method="POST">
					<input type="hidden" name="_method" value="delete">
					<input class="btn btn-dark m-3" type="submit" value="delete"/>
				</form></div> 
			</div>
		</div>
	</div>
</body>
</html>