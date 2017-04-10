<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show all users</title>
</head>
<body>
	<table border=1>
		<thead>
			<tr>
				<th>User id</th>
				<th>Username</th>
				<th>Password</th>
				<th>Email</th>
				<th>Registration Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.id}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.password}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.registrationDate}" /></td>
					<td><a
						href="UserController?action=edit&userId=<c:out value="${user.username}"/>">Update</a></td>
					<td><a
						href="UserController?action=delete&userId=<c:out value="${user.username}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>