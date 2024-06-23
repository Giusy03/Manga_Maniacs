<%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,Bean.mangaBean,Bean.genereBean"%>
     <%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./manga");	
		
	}
	//mangaBean product = (mangaBean) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<meta charset="ISO-8859-1">
<title>Catalogo</title>
</head>
<body>
	<table border="1">
			<tr>
				<th>Immagine </th>
				<th>Titolo <a href="manga?sort=nomeProdotto">Ordina</a></th>
				<th>Prezzo <a href="manga?sort=prezzo">Ordina</a></th>
				<th>Editore <a href="manga?sort=editore">Ordina</a></th>
				<th>Autore <a href="manga?sort=autore">Ordina</a></th>
				<th>Iva <a href="manga?sort=ivaprodotto">Ordina</a></th>
				
				<th>Description <a href="manga?sort=descrizione">Ordina</a></th>
				<th>Quantita <a href="manga?sort= quantita">Ordina</a></th>
				<th>Generi </th>
				
				<th></th>
				
				
				
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
				<%
				Collection<?> generi = (Collection<?>) bean.getGeneri();
			if (generi != null && generi.size() != 0) {
				Iterator<?> it2 = generi.iterator();
				while (it.hasNext()) {
					genereBean gen = (genereBean) it.next();
		%>
		 <a href="controlCatalogo?sort=<%=gen.getId() %>"> <%=gen.getNome()  %></a>
			<%
				}
			} else {
		%>
		     Nessun Genere
		<%
				
			} 
		%>
			
			</td>
			<td><a href="CartServlet?action=add&id=<%= bean.getId() %>&redirect=MangaView.jsp">Aggiungi al carrello</a></td>
		
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
	</table>
</body>
</html>