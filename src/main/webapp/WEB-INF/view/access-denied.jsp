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
			<h3>
				Access denied - You are not authorized to access this resource.
			</h3>
			
			<a href="${pageContext.request.contextPath}/">Back to main page</a>
		</div>
	</div>
</body>
</html>