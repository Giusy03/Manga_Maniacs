<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%
Collection<?> Generi = (Collection<?>) request.getAttribute("generi");

Collection<?> Depositi = (Collection<?>) request.getAttribute("depositi");
if(Depositi==null){
	response.sendRedirect("./manga");
}
%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,Bean.depositoBean,Bean.genereBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/addManga.css">
<style type="text/css">
.form-floating>.form-control{
width: 50%;
}
.inputTitoloConteiner{
display: flex;
}




</style>
<title>AddManga</title>
</head>
<body>
<%@include file="Header.jsp"  %>
<%@include file="AdminNavbar.jsp"  %>
<article class="card-body">
						<form action="mangaModyficControl" id="formNewManga"method="post" enctype="multipart/form-data">
						
							<div class="form-floating inputTitoloConteiner mb-3">
								 
									<input type="text" id="Titolo"  request 	name="Titolo"  placeholder="Titolo"		class="form-control" >
									 <label for="Titolo" class="floatingInput">Titolo</label>
									
								 	
							</div>
							<div class="form-floating mb-3">
								 
										<input type="text" id="editore" request 	name="editore" 		placeholder="Editore"	class="form-control">
									 <label for="editore" class="floatingInput">Editore</label>
							</div>
								<div class="form-floating mb-3">
								 
									 <input type="text" id="autore" request name="autore"  placeholder="Autore" 	class="form-control">	
								  	<label for="autore" class="floatingInput">Autore</label>
							</div>
							<div class="form-floating mb-3">
								  <textarea class="form-control" placeholder="description" id="description" name="description"  style="height: 100px"></textarea>
								  <label for="description" class="floatingInput">Descrizione</label>
							</div>
							<div class="form-floating  mb-3 ">
									
								  	<input type="number" step ="0.01" class="form-control" placeholder=" " request name="prezzo" id="prezzo">
								  	<label for="prezzo" class="floatingInput">Prezzo &euro;</label>
								  	
								</div>
							<div class="form-floating  mb-3 ">
									
								  	<input type="number" step ="0.01" max="100" min="0.01"  class="form-control" placeholder=" " request name="IVA" id="IVA">
								  	<label for="prezzo" class="floatingInput">IVA%</label>
								  	
								</div>
					
							
					<label>Generi</label>
						<div class="container">
											<div class="row">
											   
																										<%
												
													if ( Generi != null && Generi.size() != 0) {
														int i =0;
														Iterator<?> it = Generi.iterator();
														while (it.hasNext()) {
												
												
													
															genereBean genere = (genereBean) it.next();
																													%>
															 <div class="col-2">
																<div class="form-check form-switch">
																	 <input class="form-check-input" type="checkbox" role="switch" name="generi" value="<%=genere.getId()%>" id="idGenere<%=genere.getId()%>">
																 	<label class="form-check-label" for="idGenere<%=genere.getId()%>"><%=genere.getNome() %></label>
																</div>
															</div>
															
																										<%
														}
																										%>
										</div>
					
																						<%
								} else{
																						%>
											<div class="form-group">
												Nessun deposito disponibile
										</div>
								<%
									}
								%>
						</div>
								
							<select class="form-select " id ="deposito" name="deposito" aria-label=".form-select-sm example" style="width: 50%;">
					  																					<%
									if ( Depositi != null && Depositi.size() != 0) {
										int i =0;
										Iterator<?> it = Depositi.iterator();
										while (it.hasNext()) {
											i++;
											depositoBean deposito = (depositoBean) it.next();
																										%>
									  <option value="<%=deposito.getId()%>"><%=deposito.getNome() %></option>
									<%
											}
										} else{
									%>
											<option value="NULL">NULL</option>
									<%
										}
									%>
							</select>
							
								<div class="form-floating  mb-3 ">
									
								  	<input type="number" step ="1" max="50" min="1"  class="form-control" placeholder=" " request name="Quantita" id="QuantitaDep">
								  	<label for="QuantitaDep" class="floatingInput">QuantitaDeposito</label>
								  	
								</div>
								<div class="form-floating  mb-3 ">
			    				  
							<input type="month" class="form-control" placeholder="data" id="data" name="data" value="" >
								 
							</div>
							<div class="form-floating  mb-3 ">
			    				
								<select  class="form-select" placeholder="lingua" id="lingua"  name="lingua"  >
								<option value="ita">ITA</option>
								 </select>
							</div>
								
								
								<div class="form-floating  mb-3 ">
									<div class="input-group">
									  <input type="file" class="form-control" id="img"  name="img" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
									 
									</div>
								</div>
			
			 	
			
							<input type="hidden" name="action" value="add">
						               
						</form>
						<div class="form-group">
						    	
						        <button type="botton"  id="btnAddManga" class="btn btn-primary btn-block"> Aggiungi al catalogo  </button>
						    </div>  
					</article>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Errore</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="msError">
    		Non puoi aggiungere il prodotto 
    		<br>
    		Compila tutte le caselle:
    		 <div id="ErRisultato"></div>
      </div>
     
    </div>
  </div>
</div>
		<script src="js/newManga.js"></script>
</body>
</html>