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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>Id ordine</th>
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