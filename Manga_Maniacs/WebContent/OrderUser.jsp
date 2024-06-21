<%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,bean.mangaBean,bean.userBean, bean.orderBean"%>

<%
	Collection<?> order = (Collection<?>) request.getAttribute("order");
	if(order == null) {
		response.sendRedirect("./manga");	
		
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo Ordini</title>
</head>
<body>

	<table>
		<tr>
			<th>Prodotti contenuti nell'ordine</th>
			<th>Data ordine</th>
			<th>Totale Pagato</th>
		</tr>
		
		<%
			if (order != null && order.size() != 0) {
				Iterator<?> it = order.iterator();
				while (it.hasNext()) {
					orderBean bean = (orderBean) it.next();
		%>
		
		<tr>
		
			<td><%=bean.getId() %></td>
			<td><%=bean.getData_ordine() %></td>
			<td><%=bean.getSomma_tot() %></td>
			<td><a href="OrderControl?action=orderDetails&id=<%=bean.getId()%>">Dettagli</a></td>
		
		</tr>
			<%
				}
			} else {
		%>
		     nessun ordine
		<%
				
			} 
		%>
		
	</table>

</body>
</html>