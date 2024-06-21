<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="java.util.*,bean.userBean"%>
      <%
	Collection<?> utenti = (Collection<?>) request.getAttribute("Utenti");
	if(utenti == null) {
		response.sendRedirect("./manga");	
		
	}
	//mangaBean product = (mangaBean) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/listUtente.css">
<title>Lista utenti</title>
</head>
<body>
<%
	 userBean user = (userBean)request.getSession().getAttribute("user");
		String nome = "";
		if(user!=null){
		if (user.getUsername() != null) 
			nome = user.getUsername();
			
		}else
			response.sendRedirect("./manga");	

	%>


<%
	Boolean admin =false;
	if(user!=null)
	 admin = user.isAmministratore();
	
	
		%>
		
	<%if( admin){%>
		<%@include file="AdminNavbar.jsp"  %>	
	<%}else
	{%>
	<%response.sendRedirect("./manga");	 %>
			
	<% } %>
	
	<div class="conteiner_search">
				<form>
					<input class="search" id="search" type="search">
				</form>
				<div class="sugerimentoUtenti">
		
 	<div class="list-group" id="result" style="display: block;">
					
						
 	</div>
					
				</div>
				</div>
	
	
	
 <table>
 	<tr>
 		<th>USERNAME</th>
 		<th>psw</th>
 		<th>email</th>
 		<th>nome</th>
 		<th>cognome</th>
 		<th>amministratore</th>
 	</tr>
 	<%
			if (utenti != null && utenti.size() != 0) {
				Iterator<?> it = utenti.iterator();
				while (it.hasNext()) {
					userBean utente = (userBean) it.next();
				
			
			%>
	
 	<tr>
 	<td> <%=utente.getUsername() %></td>
 	<td><%=utente.getPwd() %> </td>
 	<td> <%=utente.getEmail() %></td>
 	<td><%=utente.getNome() %> </td>
 	<td> <%=utente.getCognome() %></td>
 	<td><%=utente.isAmministratore() %> </td>
 	</tr>
 
<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
		
 
 </table>
 <script type="text/javascript" src="Javascript\RicercaUtente.js"></script>
</body>
</html>