<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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