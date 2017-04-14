<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
	<form method="POST" action='UserController' name="user">
		<p>
			Username : <input type="text" readonly="readonly" name="username"
				value="<c:out value="${user.username}" />" /> <br />
		</p>
		<p>
			Password: <input type="password" name="password"
				value="<c:out value="${user.password}" />" /> <br />
		</p>
		<p>
			Email: <input type="text" name="email"
				value="<c:out value="${user.email}" />" /> <br />
		
		</p>
		<p>
			Registration date: <input type="text" name="registrationDate"
				value="<c:out value="${user.registrationDate}" />" /> <br />
		</p>
		<input type="submit" value="Edit">
	</form>

</body>


</html>