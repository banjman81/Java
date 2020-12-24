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
	<div class="d-flex justify-content-center my-5">
		<div class="mx-auto my-3">
			<form:form method="POST" action="/registration" modelAttribute="user">
			<h3 class="text-center">Register</h3>
				<table class="mx-auto my-3">
					<tr>
						<td class="p-3 pt-4"><form:label path="name"><h5>Name:</h5></form:label></td>
						<td class="p-3"><form:input path="name"/></td>
					</tr>
					<tr>
						<td colspan="2"><h6 class="text-center text-danger"><form:errors path="name"/></h6></td>
					</tr>
					<tr>
						<td class="p-3 pt-4"><form:label path="email"><h5>Email:</h5></form:label></td>
						<td class="p-3"><form:input type="email" path="email"/></td>
					</tr>
					<tr>
						<td colspan="2"><h6 class="text-center text-danger">${email_error}<form:errors path="email"/></h6></td>
					</tr>
					<tr>
						<td class="p-3 pt-4"><form:label path="password"><h5>Password:</h5></form:label></td>
						<td class="p-3"><form:password path="password"/>
					</tr>
					<tr>
						<td colspan="2"><h6 class="text-center text-danger"><form:errors path="password"/></h6></td>
					</tr>
					<tr>
						<td class="p-3 pt-4"><form:label path="passwordConfirmation"><h5>Password Confirmation:</h5></form:label></td>
						<td class="p-3"><form:password path="passwordConfirmation"/>
					</tr>
					<tr>
						<td colspan="2"><h6 class="text-center text-danger">${password_error}</h6></td>
					</tr>
					<tr class="text-right">
						<td colspan="2" class="p-3"><input class="col-8 btn btn-primary" type="submit" value="Register"/></td>
					</tr>
				</table>
			</form:form>
		</div>
		<div class="mx-auto my-3">
			<form method="POST" action="/login">
			<h3 class="text-center">Login</h3>
				<table class="mx-auto my-3">
					<tr>
						<td class="p-3 pt-4"><label for="email"><h5>Email:</h5></label></td>
						<td class="p-3"><input type="text" id="email" name="email"/>
					</tr>
					<tr>
						<td colspan="2" class="text-center text-danger"><h5>${q_error}</h5></td>
					</tr>
					<tr>
						<td class="p-3 pt-4"><label for="password"><h5>Password:</h5></label></td>
						<td class="p-3"><input type="password" id="password" name="password"/>
					</tr>
					<tr class="text-right">
						<td colspan="2" class="p-3"><input class="col-8 btn btn-primary" type="submit" value="Login"/></td>
					</tr>
				</table>
			</form>
			<h5 class="text-center text-danger">${log_error}</h5>
			<h5 class="text-center text-danger">${login_error}</h5>
		</div>
	</div>
</body>
</html>