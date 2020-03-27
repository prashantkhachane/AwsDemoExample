<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Registration</title>
</head>
<body>
	<div align="center">
		<form:form method="post" action="save" modelAttribute="employee">
			<table>
				<tr>
					<td>
					<th>Employee Registration</th>
					</td>

				</tr>
				<tr>
					<td>Name</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>PhoneNo</td>
					<td><form:input path="phoneNo" /></td>
				</tr>
				<tr>
					<td>City</td>
					<td><form:input path="city" /></td>
				</tr>
				<tr>
					<td>State</td>
					<td><form:input path="state" /></td>
				</tr>
				<tr>
					<td>Country</td>
					<td><form:input path="country" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="reset"><input type="submit"></td>
					
				</tr>

			</table>
		</form:form>
		<form:form action="download" method="post" id="downloadPdf">
			<input id="submitId" type="submit" value="Download pdf">
		</form:form>
	</div>

</body>
</html>