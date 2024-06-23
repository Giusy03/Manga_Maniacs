<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="java.util.*,Bean.UserBean"%>
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
<link rel="icon" href="./img/favicon.png" type="image/png">
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/listUtente.css">
<title>Lista utenti</title>
</head>
<body>
<%
	 UserBean user = (UserBean)request.getSession().getAttribute("user");
		String nome = "";
		if(user!=null){
		if (user.getUsername() != null) 
			nome = user.getUsername();
			
		}else
			response.sendRedirect("./manga");	

	%>

<%@include file="Header.jsp"  %>	
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
	<label for="searchUtente" class="form-label">Ricerca tramite username </label>
	<div class="conteiner_search">
				
					<input class="search" id="searchUtente" type="search">
				
				<div class="sugerimentoUtenti">
		
 	<div class="list-group" id="result" style="display: block;">
					
						
 	</div>
					
				</div>
				</div>
	
	<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">USERNAME</th>
      <th scope="col">psw</th>
      <th scope="col">email</th>
      <th scope="col">nome</th>
      <th scope="col">cognome</th>
      <th scope="col">amministratore</th>
    </tr>
  </thead>
   <tbody>
 	
	<%
			if (utenti != null && utenti.size() != 0) {
				Iterator<?> it = utenti.iterator();
				while (it.hasNext()) {
					UserBean utente = (UserBean) it.next();
				
			
			%>
	<tr >
	
	
 	 <th scope="row"><%=utente.getId() %></th>
	 	<td data-val="username"> <%=utente.getUsername() %></td>
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
			<td colspan="6">Nessun prodotto disponibile</td>
		</tr>
		<%
			}
		%>
		

		</tbody>
 
 </table>
	
 
 	

   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

 <script src="js/RicercaUtente.js"></script>
</body>
</html>