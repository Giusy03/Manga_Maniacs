<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%
	UserBean user = (UserBean) request.getSession().getAttribute("user");
%>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, Bean.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/img/Logo.png">
<style>
body { 
    margin: auto;
    padding: 65px;
   
}
.col-sm-6 {
    padding-bottom: 3%;
    flex: 0 0 auto;
    width: 50%;
}
</style>
<meta charset="ISO-8859-1">
<title><%=user.getUsername() %></title>
</head>
<body>
		<%@include file="Header.jsp"  %>
		<%@include file="AdminNavbar.jsp"  %>

<hr>
<h3>Area personale di <%=user.getUsername() %></h3>
<hr>

<div class="row" style=" padding-right: 15px;padding-left: 15px;">
  <div class="col-sm-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Catalogo</h5>
        <p class="card-text">Gestisci il catalogo</p>
        <form action="adminControl" method="post">
			<input type="hidden" value="modCatalogo" name="action">
			<input type="submit" class="btn btn-outline-secondary" value="Vai!">
		</form>
      </div>
    </div>
  </div>
  <div class="col-sm-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Ordini</h5>
        <p class="card-text">Visualizza gli ordini dei tuoi clienti</p>
        <form action="OrderControl" method="post">
			<input type="hidden" value="viewOrder" name="action">
			<input type="submit" class="btn btn-outline-secondary" value="Vai!">
		</form>
      </div>
    </div>
  </div>
  <div class="col-sm-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Utenti</h5>
        <p class="card-text">Visualizza la lista degli utenti registrati</p>
        <form action="adminControl" method="post">
			<input type="hidden" value="viewUtenti" name="action">
			<input type="submit" class="btn btn-outline-secondary" value="Vai!">
		</form>
      </div>
    </div>
  </div>
   <div class="col-sm-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Generi</h5>
          <div class="container">
          	 <div class="row">
          		<div class="col-sm-6">
         <p class="card-text">Modifica Generi</p>
        <form action="adminControl" method="post">
			<input type="hidden" value="modyficaGenere" name="action">
			<input type="submit" class="btn btn-outline-secondary" value="Vai!">
		</form>
         
         </div>
           <div class="col-sm-6">
         <p class="card-text">Aggiungi Generi</p>
        <form action="adminControl" method="post">
			<input type="hidden" value="addGenere" name="action">
			<input type="submit" class="btn btn-outline-secondary" value="Vai!">
		</form>
         
         </div>
          
          </div>
          
          </div>
        
      </div>
    </div>
  </div>
</div>

<%@include file="footer.jsp"  %>
</body>
</html>