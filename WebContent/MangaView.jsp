
<%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,bean.mangaBean,bean.userBean"%>



<!DOCTYPE html>
<html>
<%@ page contentType="text/html;" session="true"%>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	 userBean user = (userBean)request.getSession().getAttribute("user");
		String nome = "";
		if(user!=null){
		if (user.getUsername() != null) {

			nome = user.getUsername();
		}

		if (!(nome.equals(""))) 
			System.out.println("Utenteloggato " + nome);
		}
	%>
<%@include file="Header.jsp"  %>

<%
	Boolean admin =false;
	if(user!=null)
	 admin = user.isAmministratore();
	
	if(user == null){
		
		 
		 System.out.println("weee "+admin);
		%>
		<%@include file="navbar.jsp"  %>
	<%}else if( admin){%>
		<%@include file="AdminNavbar.jsp"  %>	
	<%}else
	{%>
	
		<%@include file="navbar.jsp"  %>
	<% } %>
	
		<%@include file="catalogo.jsp" %>
 	
	
	
	<%@include file="footer.jsp" %>
	
</body>
</html>