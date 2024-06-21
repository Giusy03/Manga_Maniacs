<%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,bean.mangaBean"%>
     <%
	Collection<?> products = (Collection<?>) request.getAttribute("mangas");
	if(products == null) {
		response.sendRedirect("./manga");	
		
	}
	//mangaBean product = (mangaBean) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<%@include file="Header.jsp" %>
	<%@include file="AdminNavbar.jsp"  %>
		
	
	<table border="1">
			<tr>
				<th>Immagine </th>
				<th>Titolo <a href="manga?sort=nomeProdotto">Sort</a></th>
				<th>Prezzo <a href="manga?sort=prezzo">Sort</a></th>
				<th>Editore <a href="manga?sort=editore">Sort</a></th>
				<th>Autore <a href="manga?sort=autore">Sort</a></th>
				<th>Iva <a href="manga?sort=ivaprodotto">Sort</a></th>
				
				<th>Description <a href="manga?sort=descrizione">Sort</a></th>
				<th>Quantita <a href="manga?sort= quantita">Sort</a></th>
				<th scope="col"></th>
		     	<th scope="col"></th>			
				
				
				
				<th>Delete</th>
			</tr>
			<%
				if (products != null && products.size() != 0) {
					Iterator<?> it = products.iterator();
					while (it.hasNext()) {
						mangaBean bean = (mangaBean) it.next();
			%>
			<tr>
			<td><img src="<%=bean.getImg()%>" height=100 width=100></td>
			<td><a href="deteilControl?id=<%= bean.getId() %>"><%= bean.getTitolo() %></a></td>
			<td><%= bean.getPrezzo()  %></td>
			<td><%= bean.getEditore()  %></td>
			<td><%= bean.getAutore()  %></td>
			<td><%= bean.getIva() %></td>
			<td><%= bean.getDescrizione() %></td>
			<td><%= bean.getQuantita() %></td>
			
			
				<td>
						<form action="adminControl" method="post">
							<input type="hidden" value="remove" name="action">
							<input type="hidden" value="<%=bean.getId() %>" name="id">
							<input type="submit" class="" value="Rimuovi">
						</form>
					</td>
		      	<td>
						<form action="adminControl" method="post">
							<input type="hidden" value="modifyPage" name="action">
							<input type="hidden" value="<%=bean.getId() %>" name="id">
							<input type="submit" class="" value="Modifica">
						</form>
					</td>
			</tr>
				<%
				}
			} else{
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
	</table>
	<form action="adminControl" method="post">
			<input type="hidden" value="addPage" name="action">
			<input type="submit" class="btn btn-sm btn-outline-secondary" value="Aggiungi un nuovo articolo">
		</form>
	
</body>
</html>