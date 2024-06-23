<%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,Bean.mangaBean,Bean.UserBean, Bean.OrderBean"%>

<%
	OrderBean order = (OrderBean)request.getAttribute("order");
	if(order == null) {
		response.sendRedirect("./OrderUser");	
	}
	else if(order!=null){
		
		
	}
	
	
%>


<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<meta charset="ISO-8859-1">
<title>Dettaglio ordine</title>
</head>
<body>
<%@include file="Header.jsp"  %>
<%@include file="navbar.jsp"  %>

	<h3>Dettaglio ordine selezionato</h3>

	<h3>Totale pagato:<%=order.getSomma_tot() %>&euro;</h3>

	
	<table class="table">
	  <thead>
	    <tr>
	     <th class="col">Titolo</th>
		
			
			<th class="col">Iva</th>
			
			<th class="col">Prezzo</th>
			
			<th class="col">Quantità</th>
			
			<th class="col">Data ordine</th>
			
			
	    </tr>
	  </thead>
		<tbody>
		<%
			
		ArrayList<mangaBean> mangas = order.getManga();
					
				for(int i=0; i<mangas.size();i++){
					
				mangaBean manga = mangas.get(i);
			
		%>
		
		<tr>
		
			<th><%=manga.getTitolo() %></th>
			
			
			<th><%=manga.getIva() %>%</th>
			
			<th><%=manga.getPrezzo() %>&euro;</th>
			
			<th><%=manga.getQuantita() %></th>
			
			
			<th><%=order.getData_ordine() %></th>
			
		
			
		
		</tr>
			<%
				}
			%>
			</tbody>
		
		
		
	</table>

	<%@include file="footer.jsp" %>
</body>
</html>