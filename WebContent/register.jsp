<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="registerUser" method="post">
		Username: <input type="text" name="username" required="required"/><br/> 
		Password: <input type="password" name="password" required="required"/><br/> 
		Confirm password <input	type="password" name="password-confirmed" required="required"/> <br />
		Email: <input type="email" name="email" /><br />
		<input type="submit" value="Register"/>
	</form>
</body>
</html>