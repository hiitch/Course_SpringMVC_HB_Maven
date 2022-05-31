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
			
			<ul style="width: 71%;">
				<li><a href="${pageContext.request.contextPath}/menu/admin">Admin panel</a></li>
				<li><a href="${pageContext.request.contextPath}/menu/manager">Manager panel</a></li>
			</ul>
			
			<h3>
				Hello only admins can access to this page !<br>
				If you see this page it means you are an admin and you can handle route with spring security.
			</h3>
			
			<a href="${pageContext.request.contextPath}/">Back to main page</a>
		</div>
	</div>
</body>
</html>