<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="java.util.*,Bean.UserBean,Bean.genereBean"%>
      <%
	Collection<?> generi = (Collection<?>) request.getAttribute("generi");
	if(generi == null) {
		response.sendRedirect("./adminControl");	
		
	}
	//mangaBean product = (mangaBean) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/listUtente.css">
<title>Lista Generi</title>
</head>
<body>
<%
	 UserBean user = (UserBean)request.getSession().getAttribute("user");
	
		Boolean admin =false;
		if(user!=null){
			admin = user.isAmministratore();
		if (!admin) 
			response.sendRedirect("./adminControl");	
			
		}
		

	%>

<%@include file="Header.jsp"  %>	
<%@include file="AdminNavbar.jsp"  %>	

	
	<label for="searchGenere" class="form-label">Ricerca con genere </label>
	<div class="conteiner_search">
				
					<input class="search" id="searchGeneri" type="search">
				</div>
	
	<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">nome</th>
      <th scope="col">descrizione</th>
      <th scope="col">elimina</th>
    </tr>
  </thead>
   <tbody>
 	
	<%
			if (generi != null && generi.size() != 0) {
				Iterator<?> it = generi.iterator();
				while (it.hasNext()) {
					genereBean genere = (genereBean) it.next();
				
			
			%>
	<tr class="rigaGenere">
	
	
 	 <th scope="row" class="id"><%=genere.getId() %></th>
	 	<td data-val="genereName"> <%=genere.getNome() %></td>
	 	<td><%=genere.getDescrizione() %> </td>
	 	<td><button type="button" class="btn-close"  aria-label="Close"></button> </td>
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

 <script src="js/ModificaGenere.js"></script>
</body>
</html>