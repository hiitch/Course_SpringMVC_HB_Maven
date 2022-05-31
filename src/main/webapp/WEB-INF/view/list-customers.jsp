<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.webcustomer.utils.SortField" %>

<!DOCTYPE html>
<html>
<head>
	<title>List customers</title>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<h3>Hello, <security:authentication property="principal.username" /> <security:authentication property="principal.authorities" /></h3>
			
			<ul style="width: 71%;">
				<security:authorize access="hasRole('ADMIN')">	
					<li><a href="${pageContext.request.contextPath}/menu/admin">Admin panel</a></li>
				</security:authorize>
				<security:authorize access="hasRole('MANAGER')">
					<li><a href="${pageContext.request.contextPath}/menu/manager">Manager panel</a></li>
				</security:authorize>
			</ul>
			
			<div style="width: 71%; clear:both; padding-top:15px">
				<input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;"
					class="add-button"
				/>
				<form:form action="${pageContext.request.contextPath}/logout" method="POST" id="logout-button">
					<input type="submit" value="Logout" class="add-button" />
				</form:form>
			</div>
			<form:form action="search" method="GET">
				<input type="text" name="customerName" />
				<input type="submit" value="Search" class="add-button" />
			</form:form>
			
			<c:url var="sortLinkFirstName" value="/customer/list">
				<c:param name="sort" value="<%= Integer.toString(SortField.FIRST_NAME.sortIndex) %>" />
			</c:url>					
			<c:url var="sortLinkLastName" value="/customer/list">
				<c:param name="sort" value="<%= Integer.toString(SortField.LAST_NAME.sortIndex) %>" />
			</c:url>
			<c:url var="sortLinkEmail" value="/customer/list">
				<c:param name="sort" value="<%= Integer.toString(SortField.EMAIL.sortIndex) %>" />
			</c:url>
			<table>
				<thead>
					<tr>
						<th><a href="${sortLinkFirstName}">First name</a></th>
						<th><a href="${sortLinkLastName}">Last name</a></th>
						<th><a href="${sortLinkEmail}">Email</a></th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="customer" items="${customers}">
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${customer.id}" />
						</c:url>
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${customer.id}" />
						</c:url>
						
						<tr>
							<td>${customer.firstName}</td>
							<td>${customer.lastName}</td>
							<td>${customer.email}</td>
							<td ><a href="${updateLink}">Update</a> 
								| <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this ?'))) {return false;}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>