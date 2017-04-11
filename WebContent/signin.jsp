<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% if(request.getSession().getAttribute("username") != null){
	response.sendRedirect("profile.jsp");
}
%>
<form action="LoginServlet" method="post">
	Username: <input type="text" name="username" required="required"/><br>
	Password: <input type="password" name="password" required="required" /><br>
	<input type="submit" value="Signin"/>
</form>

</body>
</html>