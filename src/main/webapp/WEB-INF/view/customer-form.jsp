<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Form</title>

<!-- The ordering of both the stylesheets play a role- if want to use table tag style from add-customer css it should be below  -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />


</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM-Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Customer</h3>
		<form:form action="saveCustomer" modelAttribute="customer"
			method="POST">
			
			<!-- Need to associate this data with customer id -->
			<form:hidden path="id"/>
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><form:input path="firstName" /></td>

					</tr>
					<tr>

						<td><label>Last Name:</label></td>
						<td><form:input path="lastName" /></td>

					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" /></td>

					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" class="save" value="Save" /></td>
					</tr>
				</tbody>

			</table>

		</form:form>
		<div style="clear(); both();"></div>

		<p>
			<a href="${pageContext.request.contextPath}/customer/list">
			Back to List</a>
		</p>

	</div>

</body>
</html>