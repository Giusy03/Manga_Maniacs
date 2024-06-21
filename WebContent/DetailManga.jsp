<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,bean.mangaBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<nav><a href="./manga">catalogo</a></nav>
<h2>Products</h2>
 <%
	mangaBean bean = (mangaBean) request.getAttribute("MangaDetail");
	if(bean == null) {
		response.sendRedirect("./manga");	
		
	}
	//mangaBean product = (mangaBean) request.getAttribute("product");
%>
	<table border="1">
		<tr>
				<th>Immagine </th>
				<th>Titolo <a href="manga?sort=nomeProdotto">Sort</a></th>
				<th>Prezzo <a href="manga?sort=prezzo">Sort</a></th>
				<th>Editore <a href="manga?sort=editore">Sort</a></th>
				<th>Autore <a href="manga?sort=autore">Sort</a></th>
				<th>Iva <a href="manga?sort=ivaprodotto">Sort</a></th>
				
				<th>Description <a href="manga?sort=descrizione">Sort</a></th>
				
	<tr>
		<td><img src="<%=bean.getImg()%>" height=100 width=100></td>
			<td><a href="deteilControl?id=<%= bean.getId() %>"><%= bean.getTitolo() %></a></td>
			<td><%= bean.getPrezzo()  %></td>
			<td><%= bean.getEditore()  %></td>
			<td><%= bean.getAutore()  %></td>
			<td><%= bean.getIva() %></td>
			<td><%= bean.getDescrizione() %></td>
			<td><a href="CartServlet?action=add&id=<%= bean.getId() %>&redirect=DetailManga.jsp">Aggiungi al carrello</a></td>
		
		
			</tr>
	</table>
</body>
</html>