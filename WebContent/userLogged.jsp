<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Hello 
		<%if((boolean)session.getAttribute("adminRoles")){%>
	
				Admin
			<%}else{%>
				 Utente
		<%} %>
	<%= session.getAttribute("username") %></h1> 
</body>
</html>