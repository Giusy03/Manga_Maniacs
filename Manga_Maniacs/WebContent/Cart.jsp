

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,bean.mangaBean, model.CartModel"%>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	
	<%	
		CartModel cart =null;
	
	if( request.getSession().getAttribute("cart")!=null)
	cart=(CartModel) request.getSession().getAttribute("cart");
			
		
		
	%>
		
	
		<% if(cart != null) { %>
		
		<h2>Shopping Cart</h2>
		<table border="1">
		<tr>
			<th>Immagine</th>
			<th>Nome</th>
			<th>Prezzo</th>
			<th>delete</th>
			<th>Quantità</th>
			<th>add</th>
		</tr>
		<% List<mangaBean> prodcart = cart.getProducts();
			List<Integer> quantity =cart.getQuantity();
			System.out.println(prodcart.toString());
		  for(int i =0;i<prodcart.size();i++) {
			  mangaBean manga = prodcart.get(i); 
			  int q = quantity.get(i);
				
		%>
		<tr>
			<td><img src="<%=manga.getImg()%>" height=100 width=100></td>
			<td><%=manga.getTitolo()%></td>
			<td><%=manga.getPrezzo()%></td>
			<td>
						<form action="CartServlet" method="post">
							<input type="hidden" value="minus" name="action">
							<input type="hidden" value="Cart.jsp" name="redirect">
							<input type="hidden" value="<%=manga.getId() %>" name="id">
							<input type="submit"  value="-">
						</form>
			</td>
			<td> <%=q%></td>
			<td>
						<form action="CartServlet" method="post">
							<input type="hidden" value="plus" name="action">
							<input type="hidden" value="Cart.jsp" name="redirect">
							<input type="hidden" value="<%=manga.getId() %>" name="id">
							<input type="submit"  value="+">
						</form>
				
					</td>
			<td><a href="CartServlet?action=delete&id=<%=manga.getId()%>&redirect=Cart.jsp">Delete from cart</a></td>
		</tr>
		
		<%} %>
	</table>
	
	<form action ="OrderControl" method="post">
		<input type="hidden" value="completeOrder" name="action"></input>
		<button type="submit">Procedi all'ordine</button>
	</form>		
	
	<% }else{ %>
	<h1>Non c'è niente...</h1>
		<%} %>	
</body>
</html>