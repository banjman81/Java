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
	<div class="my-5">
		<div class="mx-auto my-3">
			<form:form method="POST" action="/create/course" modelAttribute="course">
			<h3 class="text-center">Create a new Course</h3>
				<table class="mx-auto my-3 bg-secondary rounded text-light">
					<tr>
						<td class="p-3 pt-4"><form:label path="name"><h5>Name:</h5></form:label></td>
						<td class="p-3"><form:input path="name"/></td>
					</tr>
					<tr>
						<td class="p-3 pt-4"><form:label path="instructor"><h5>Instructor:</h5></form:label></td>
						<td class="p-3"><form:input path="instructor"/></td>
					</tr>
					<tr>
						<td class="p-3 pt-4"><form:label path="capacity"><h5>Capacity:</h5></form:label></td>
						<td class="p-3"><form:input type="number" required="true" path="capacity"/>
					</tr>
					<tr>
						<td><form:label path="user"/></td>
						<td><form:hidden path="user" value="${user.id}"/></td>
					</tr>
					<tr class="text-right">
						<td colspan="2" class="p-3"><input class="col-8 btn btn-primary" type="submit" value="Add"/></td>
					</tr>
				</table>
			</form:form>
			<h5 class="text-center text-danger"><form:errors path="course.*"/></h5>
		</div>
	</div>
</body>
</html>