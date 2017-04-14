<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect("profile.jsp");
		}
	%>
	<!-- if login not correct this message will show up -->
	<p>
		<c:out value="${message}"></c:out>
	</p>
	<form action="UserController?action=login" method="post">
		Username: <input type="text" name="username" required="required" /><br>
		Password: <input type="password" name="password" required="required" /><br>
		<input type="submit" value="Signin" />
	</form>

</body>
</html>