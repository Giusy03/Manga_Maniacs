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
<link rel ="icon" href="getPicture?id=20" type="image/x-icona">
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
<%
	 UserBean user = (UserBean)request.getSession().getAttribute("user");
Boolean admin =false;
if(user!=null)
 admin = user.isAmministratore();

if(user == null){
	response.sendRedirect("./manga");
}else if(!admin)
{
	response.sendRedirect("./manga");
}
	%>



<%@include file="Header.jsp"  %>
<%@include file="AdminNavbar.jsp"  %>	


	<div class="container">
	
	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
	
	<div class="col">
	<input type="date" class="form-control" id="dataInizio">
	</div>
	<div class="col">
	<input type="date" class="form-control" id="dataFine">
	</div>
	<div class="col">
	<input type="button" class="btn btn-sm btn-outline-secondary" value="Ricerca" id="btnRicercaOrder">
	</div>
	
	
	</div>
	
	</div>
	

	<table class="table">
  <thead>
   <tr>
			<th scope="col">Id ordine</th>
			<th scope="col">Data ordine</th>
			<th scope="col">Totale Pagato</th>
			<th scope="col">Dettagli Ordine</th>
		 </tr>
  </thead>
  <tbody>
		
		<%
			if (order != null && order.size() != 0) {
				Iterator<?> it = order.iterator();
				while (it.hasNext()) {
					OrderBean bean = (OrderBean) it.next();
		%>
		
		<tr>
		
			<th scope="row"><%=bean.getId() %></td>
			<td data-value="data"><%=bean.getData_ordine() %></td>
			<td><%=bean.getSomma_tot() %></td>
			<td><a class ="btn btn-sm btn-outline-secondary" href="OrderControl?action=orderDetails&id=<%=bean.getId()%>">Dettagli</a></td>
			
		
		</tr>
			<%
				}
			} else {
		%>
		     Nessun Ordine
		<%
				
			} 
		%>
		
	  </tbody>
</table>
	<%@include file="footer.jsp" %>
	 <script src="js/orderAdmin.js"></script>
</body>
</html>