<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%
mangaBean manga = (mangaBean) request.getAttribute("manga");
Collection<?> Depositi = (Collection<?>) request.getAttribute("depositi");
Collection<?> inDepositi = (Collection<?>) request.getAttribute("inDepositi");
%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, bean.mangaBean,bean.depositoBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ModificaManga</title>
</head>
<body>
<%@include file="AdminNavbar.jsp"  %>

<div class="parent"><h1 style="float:center"><%=manga.getTitolo() %></h1></div>
		<hr>
		<div class="">
		<div class="">
			<div class="">
				<img src="<%=manga.getImg() %>" width="400px" height="400px">
			</div> 
			<form action = "mangaModyficControl" method = "post">
				<table class="table" style="width: 500px; ">
			
			<tr>
					      <th scope="row">Titolo</th>
					      <td><input type="text" id="Titolo" name="Titolo" value="<%=manga.getTitolo() %>" placeholder="<%=manga.getTitolo() %>"></td>
			 </tr>
			<tr>
					      <th scope="row">Editore</th>
					      <td><input type="text" id="editore" name="editore" value="<%=manga.getEditore() %>"placeholder="<%=manga.getEditore() %>"></td>
			 </tr>
			 <tr>
					      <th scope="row">Autore</th>
					      <td><input type="text" id="autore" name="autore" value="<%=manga.getAutore() %>" placeholder="<%=manga.getAutore() %>"></td>
			 </tr>
			  <tr>
					      <th scope="row">Iva</th>
					      <td><input type="text" id="iva" name="iva" min="1" value="<%=manga.getIva() %>" placeholder="<%=manga.getIva() %>"></td>
			 </tr>
			  <tr>
					      <th scope="row">prezzo</th>
					      <td><input type="text" id="prezzo" name="prezzo" min="1" value="<%=manga.getIva() %>" placeholder="<%=manga.getIva() %>"></td>
			 </tr>
			  <tr>
					      <th scope="row">Descrizione</th>
						 <td>  <textarea name="description"  placeholder="<%=manga.getDescrizione() %>"><%=manga.getDescrizione() %></textarea> </td>
						  
			 </tr>
			 	<%
				if ( Depositi != null && Depositi.size() != 0) {
					int i =0;
					Iterator<?> it = Depositi.iterator();
					while (it.hasNext()) {
				
						depositoBean deposito = (depositoBean) it.next();
			%>
			<tr>
					      <th scope="row">Deposito <%=deposito.getNome() %> </th>
					      <td> <input type="radio" id="" name="deposito" request value=<%=deposito.getId()%>  >		</td>
					      <td><input type="number" id="" name="Quantita" min="0" value=<%= deposito.getQuantity() %> placeholder="<%= deposito.getQuantity() %>"></td>
			
			</tr>
					<%
				}
			} else{
		%>
		<tr>
			<td colspan="6">Il prodotto non e contenuto in nessun deposito</td>
		</tr>
		<%
			}
		%>
		
		
			<%
				if ( inDepositi != null && inDepositi.size() != 0) {
					int i =0;
					Iterator<?> it = inDepositi.iterator();
					while (it.hasNext()) {
				
						depositoBean deposito = (depositoBean) it.next();
			%>
			<tr>
					      <th scope="row">addDeposito <%=deposito.getNome() %> </th>
					      <td> <input type="radio" id="" name="addDeposito" request value=<%=deposito.getId()%>  >		</td>
					      <td><input type="number" id="" name="QuantitAddDeposito" min="0" value=<%= deposito.getQuantity() %> placeholder="<%= deposito.getQuantity() %>"></td>
			
			</tr>
					<%
				}
			} else{
		%>
		<tr>
			<td colspan="6">Non ci sono depositi aggiuntivi</td>
		</tr>
		<%
			}
		%>
		
			 
			 
			  <tr>
					      <th scope="row">Invalid</th>
					      <td><input type="radio" id="inv" name="inv" value="false">
					      <input type="radio" id="inv" name="inv" value="true"></td>
			 </tr>
			
			
			
			
			
			
			
			
			
			
			
			
			</table>
			<input type="hidden" value="<%=manga.getId() %>" name="id">
				<input type="hidden" value="modify" name="action">
				<input type="submit" class="" value="Modifica">
			</form>
		</div>
		</div>
</body>
</html>