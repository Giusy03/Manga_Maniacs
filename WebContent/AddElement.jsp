<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<meta charset="ISO-8859-1">
<title>Aggiungi Genere</title>
<style type="text/css">
.addBody{
border:solid;
border-radius: 27px;
}


</style>
</head>
<body>
	<%@include file="Header.jsp" %>
	<%@include file="AdminNavbar.jsp"  %>
	

	
	<div class="container">
	<div class="row">
	 	<div class="d-flex justify-content-between align-items-center">	
			<input type="button" id="btnAddGenere"  class="btn btn-outline-secondary btn-lg" value="Aggiungi Genere">
		
			<input type="button" id="btnAddInviaGenere"  class="btn btn-outline-secondary btn-lg" value="Invia">
	 </div>
	</div>
		<div class="row addsBody" >
				<div class="addBody">
				<h3><b>Aggiungi Genere</b></h3>
				 <button type="button" class="btn-close"  aria-label="Close"></button>
				<div class="mb-3">
	            <label class="col-form-label">Nome :</label>
	            <input type="text" class="form-control nomeGenere"  name="nomeGenere" >
	          </div>
	          <div class="mb-3">
	            <label  class="col-form-label">Descrizione:</label>
	            <textarea class="form-control descrizione" name="descrizione"></textarea>
	          </div>
			</div>
		
		</div>
	
	</div>
		<script src="js/addElement.js"></script>
</body>
</html>