<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>You've been login successfully!</h3>
	<%
		if(session.getAttribute("username") == null) {
			response.sendRedirect("index.jsp");
		}
	
	%>
	<jsp:useBean id="user" class="com.luke.dto.User" scope="session">
		<jsp:setProperty property="username" name="user" />
		<jsp:setProperty property="password" name="user" />
	</jsp:useBean>

	<div>Welcome
	<jsp:getProperty property="username" name="user" />
	</div>
	<a href="UserController?action=logout">Logout</a>
	<a href="UserController?action=edit&username=${user.username}">Edit</a>
	<a href="UserController?action=delete&username=${user.username}">Delete</a>
	
</body>
</html>