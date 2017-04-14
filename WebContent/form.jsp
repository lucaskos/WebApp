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
	<c:if test="${user != null }">
		<form method="POST" action='UserController?action=insert' name="user">
			<h3>
				Edit user:
				<c:out value="${user.username }" />
			</h3>
	</c:if>
	<c:if test="${user == null }">
		<form method="POST" action='UserController?action=register'
			method="post">
			<h3>Register new user</h3>

			<p>
				Username : <input type="text" name="username" /> <br />
			</p>
	</c:if>

	<p>
		Password: <input type="password" name="password" id="password"
			value="<c:out value="${user.password}" />" /> <br />
	</p>
	<p>
		Confirm Password: <input type="password" name="password-confirm"
			id="password-confirm" oninput="checkPassword(this)"
			value="<c:out value="${user.password}" />" />
		<c:out value="${message }"></c:out>
		<br />
		<script language='javascript' type='text/javascript'>
			function checkPassword(input) {
				if (input.value != document.getElementById('password').value) {
					input.setCustomValidity('Passwords Must be Matching.');
				} else {
					// input is valid -- reset the error message
					input.setCustomValidity('');
				}
			}
		</script>
	</p>
	<p>
		Email: <input type="text" name="email"
			value="<c:out value="${user.email}" />" /> <br />

	</p>
	<c:if test="${user != null }">
		<p>
			Registration date:
			<c:out value="${user.registrationDate }"></c:out>
		</p>
	</c:if>
	<input type="submit" value="Save">
	</form>

</body>


</html>