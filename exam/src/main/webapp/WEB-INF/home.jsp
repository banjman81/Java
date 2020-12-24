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
		<a href="/logout" class="btn btn-warning m-3">Logout</a>
	</div>
	<div class="container">
		<h1>Welcome, ${user.name}</h1>
		<div>
			<table class="table border border-dark">
				<thead>
					<tr class="table-dark">
						<th style="width:30%">Courses</th>
						<th style="width:30%">Instructor</th>
						<th style="width:30%"  class="text-center">Capacity</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courses}" var="c">
					<tr>
						<td><a href="/courses/${c.id}">${c.name}</a></td>
						<td>${c.instructor}</td>
						<td class="text-center">${c.capacity}</td>
						<td>
							<form:form method="Post" action="/enroll" modelAttribute="enroll">
							<form:hidden path="user" value="${user.id}"/>
							<form:hidden path="course" value="${c.id}"/>
								<c:if test="${user.courses.contains(c)}">
									Enrolled
								</c:if>
								<c:if test="${!user.courses.contains(c)}">
									<input type="submit" value="Add Course">
								</c:if>
							</form:form>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/courses/new" class="btn btn-dark m-3">Add course</a>
		</div>
	</div>
</body>
</html>