<%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,Bean.mangaBean,Bean.UserBean, Bean.OrderBean"%>

<%
	Collection<?> order = (Collection<?>) request.getAttribute("order");
	if(order == null) {
		response.sendRedirect("./manga");	
		
	}
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<meta charset="ISO-8859-1">
<title>Riepilogo Ordini</title>
</head>
<body>
<%@include file="Header.jsp"  %>
<%@include file="navbar.jsp"  %>
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
					OrderBean bean = (OrderBean) it.next();
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
		     Nessun Ordine
		<%
				
			} 
		%>
		
	</table>

	<%@include file="footer.jsp" %>
</body>
</html>