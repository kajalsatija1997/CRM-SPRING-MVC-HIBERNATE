<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page import="com.springLessons.springdemo.util.SortUtils"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<br>
	<input type="button" class="add-button"
		onclick="window.location.href='showFormForAdd'; return false;"
		value="Add Customer">

	<br>

	<!--  add a search box -->
	<form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />

		<input type="submit" value="Search" class="add-button" />
	</form:form>

	<!-- construct a sort link for first name -->
	<c:url var="sortLinkFirstName" value="/customer/list">
		<c:param name="sort"
			value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
	</c:url>

	<!-- construct a sort link for last name -->
	<c:url var="sortLinkLastName" value="/customer/list">
		<c:param name="sort"
			value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
	</c:url>

	<!-- construct a sort link for email -->
	<c:url var="sortLinkEmail" value="/customer/list">
		<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
	</c:url>
	
	<div id="container">
		<div id="content">
			<!-- Add HTML Table here -->
			<table>

				<tr>
					<th><a href=${sortLinkFirstName}>First Name</a></th>
					<th><a href=${sortLinkLastName}>Last Name</a></th>
					<th><a href=${sortLinkEmail}>Email</a></th>
					<th>Action</th>
				</tr>

				<!-- Loop over and print our customers -->

				<c:forEach var="tempCustomers" items="${customers}">

					<!-- Construct an update link with customer id -->
					<!-- If we just add the <a> tag it wont work as it will multiple links holding no value -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomers.id}"></c:param>
					</c:url>
					<!-- Delete link to delete the customer -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomers.id}"></c:param>
					</c:url>

					<tr>
						<td>${tempCustomers.firstName}</td>
						<td>${tempCustomers.lastName}</td>
						<td>${tempCustomers.email}</td>
						<td>
							<!-- Display the update link --> <a href=${updateLink}>Update</a>
							<!-- Display the delete link -->| <a href=${deleteLink
							}
							onclick="if(!(confirm('Are you sure you want to delete the customer?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>