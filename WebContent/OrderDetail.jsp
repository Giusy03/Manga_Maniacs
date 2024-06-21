<%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,bean.mangaBean,bean.userBean, bean.orderBean"%>

<%
	orderBean order = (orderBean)request.getAttribute("order");
	if(order == null) {
		response.sendRedirect("./OrderUser");	
	}
	else if(order!=null){
		
		
	}
	
	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dettaglio ordine</title>
</head>
<body>

	<h3>Dettaglio ordine selezionato</h3>

	<table>
		<tr>
			<th>Titolo</th>
			<th>Editore</th>
			<th>Autore</th>
			<th>Iva</th>
			<th>Prezzo</th>
			<th>Quantità</th>
			<th>Data ordine</th>
			<th>Immagine Prodotto</th>
			
		</tr>
		
		<%
			
		ArrayList<mangaBean> mangas = order.getManga();
					
				for(int i=0; i<mangas.size();i++){
					
				mangaBean manga = mangas.get(i);
			
		%>
		
		<tr>
		
			<th><%=manga.getTitolo() %></th>
			<th><%=manga.getEditore() %></th>
			<th><%=manga.getAutore() %></th>
			<th><%=manga.getIva() %></th>
			<th><%=manga.getPrezzo() %></th>
			<th><%=manga.getQuantita() %></th>
			
			<th><%=order.getData_ordine() %></th>	
			<th><img src="<%=manga.getImg() %>" width=100></th>
			
		
		</tr>
			<%
				}
			%>
			
			<h3>Totale pagato:<%=order.getSomma_tot() %></h3>
		
		
	</table>

</body>
</html>