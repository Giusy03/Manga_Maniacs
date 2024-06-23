<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, Bean.mangaBean,Bean.depositoBean,Bean.genereBean"%>

<%
mangaBean manga = (mangaBean) request.getAttribute("manga");
if(manga==null){
	response.sendRedirect("./manga");	
}
Collection<?> Depositi = (Collection<?>) request.getAttribute("depositi");
Collection<?> GeneriProdotto = (Collection<?>) manga.getGeneri();
Collection<?> Generi = (Collection<?>) request.getAttribute("generi");
if(Depositi==null){
	response.sendRedirect("./manga");
}
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<meta charset="ISO-8859-1">
<title>ModificaManga</title>

</head>
<body>

<%@include file="Header.jsp"  %>
<%@include file="AdminNavbar.jsp"  %>

<style type="text/css">
.conteinerQuantity{
display:none;
}
.col-2 {
    flex: 1 0 auto;
    width: 25.666667%;
}

</style>
<div class="parent"><h1 style="text-align: center;"><%=manga.getTitolo() %></h1></div>
		<hr>
		
			
			<form action = "mangaModyficControl"  id="formModific"method = "post" enctype="multipart/form-data">
			
			<div class="container">
			  <div class="row">
			    <div class="col">
			      <img src="<%=manga.getImg() %>" width="400px" height="400px">
			       <div class="mb-3">
			      	  <label for="img" class="form-label"> Modifica Immagine</label>
								  <input type="file" class="form-control" id="img"  name="img" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
								 
					</div>
				<div class="container">
											<div class="row">
											   
																										<%
												
													if ( Generi != null && Generi.size() != 0) {
												
														Iterator<?> it = Generi.iterator();
														while (it.hasNext()) {
												
												
													
															genereBean genere = (genereBean) it.next();
															
																													%>
															 <div class="col-2">
																<div class="form-check form-switch">
																
																
																<%
																
																Boolean esiste =false;
																if ( GeneriProdotto != null && GeneriProdotto.size() != 0) {
																		
																		Iterator<?> it2 = GeneriProdotto.iterator();
																		while (it2.hasNext()) {
																
																				genereBean genereProdotto = (genereBean) it2.next();
																			if(genereProdotto.getId()==genere.getId())
																			{
																				esiste=true;
																				break;
																			}
																		}
																	}
																
														
																
																if(esiste){
																	
																	
																%>
																	 <input class="form-check-input" type="checkbox" checked  name="generi" value="<%=genere.getId()%>" id="idGenere<%=genere.getId()%>">
																<%
																}else{
															%>		
															 <input class="form-check-input" type="checkbox"   name="generi" value="<%=genere.getId()%>" id="idGenere<%=genere.getId()%>">
															<%
														}
																										%>
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
												No depositi available
										</div>
								<%
									}
								%>
						</div>
						
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="inv" <%=(manga.isInv())?"checked":"" %> value="true" id="inv1" >
					  <label class="form-check-label" for="flexRadioDefault1">
					   Invisibile
					  </label>
					</div>
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="inv" value="false"  id="inv2" <%=(manga.isInv())?"":"checked" %>>
					  <label class="form-check-label" for="flexRadioDefault2">
					    Visibile
					  </label>
					</div>
			    </div>
			    <div class="col">
			     
			     <div class="mb-3">
				  <label for="Titolo" class="form-label"> Titolo</label>
				  <input type="text" id="Titolo" name="Titolo"   class="form-control" value="<%=manga.getTitolo() %>" request placeholder="<%=manga.getTitolo() %>">
				  
				</div>
				
				     <div class="mb-3">
				  <label for="Editore" class="form-label"> Editore</label>
				  <input type="text" id="Editore" name="editore"   class="form-control"value="<%=manga.getEditore() %>" requestplaceholder="<%=manga.getEditore() %>">
				  
				</div>
				
				
				     <div class="mb-3">
				  <label for="Autore" class="form-label"> Autore</label>
				  <input type="text" id="Autore" name="autore"   class="form-control" value="<%=manga.getAutore() %>" request placeholder="<%=manga.getAutore() %>">
				  
				</div>
				  <div class="  mb-3 ">
										<label for="prezzo" class="floatingInput">Prezzo &euro;</label>
								  	<input type="number" step ="0.01" class="form-control" placeholder=" " request name="prezzo" id="prezzo" value="<%=manga.getPrezzo() %>" placeholder="<%=manga.getPrezzo() %>">
								  
								  	
								</div>
							<div class="  mb-3 ">
									<label for="prezzo" class="floatingInput">IVA%</label>
								  	<input type="number" step ="0.01" max="100" min="0.01"  class="form-control" placeholder=" " request name="IVA" id="IVA" value="<%=manga.getIva() %>" placeholder="<%=manga.getIva() %>" >
								  	
								  	
								</div>
				
			     <div class=" mb-3">
			    				  <label for="description" class="floatingInput">Descrizione</label>
								  <textarea class="form-control" placeholder="description" id="description" name="description"  style="height: 100px"><%=manga.getDescrizione() %></textarea>
								 
							</div>
				  <div class=" mb-3">
				    	<label for="deposito" class="floatingInput">seleziona deposito</label>
			    					<select class="form-select " name="deposito" id="deposito" aria-label=".form-select-sm example" style="width: 50%;">
					  								<option value="null">Seleziona Deposito </option>													<%
									if ( Depositi != null && Depositi.size() != 0) {
										int i =0;
										Iterator<?> it = Depositi.iterator();
										while (it.hasNext()) {
											i++;
											depositoBean deposito = (depositoBean) it.next();
																										%>
									  <option value="<%=deposito.getId()%>"><%=deposito.getNome() %> </option>
									 
									<%
											}
										} else{
									%>
											<option value="NULL">NULL</option>
									<%
										}
									%>
							</select>
								<div class="  mb-3 ">
								<%
								if ( Depositi != null && Depositi.size() != 0) {
										int i =0;
										Iterator<?> it = Depositi.iterator();
										while (it.hasNext()) {
											i++;
											depositoBean deposito = (depositoBean) it.next();
																										%>
									<div class="conteinerQuantity"  id="QuantitaBy<%=deposito.getId()%>">
											<label for="QuantitaBy<%=deposito.getId()%>" class="floatingInput">QuantitaDeposito:<%=deposito.getNome() %></label>
								  		<input type="number"   class="form-select " name="Quantita<%=deposito.getId()%>" min="0" value=<%= deposito.getQuantity() %> placeholder="<%= deposito.getQuantity() %>">
									 </div>
									<%
											}
										} else{
									%>
											<option value="null">NULL</option>
									<%
										}
									%>
									  	
							
								  	
								</div>
						
								 <div class=" mb-3">
			    				  <label for="description" class="floatingInput">data</label>
							<input type="month" class="form-control" placeholder="month" id="month" name="data" value="<%=manga.getDataProdotto() %>" >
								 
							</div>
							<div class=" mb-3">
			    				  <label for="description" class="floatingInput">Lingua</label>
							<input type="text" class="form-control" placeholder="lingua" id="lingua" size="3" name="lingua" value="<%=manga.getLingua() %>" >
								 
							</div>
								 
								 
							</div>			
			 
			  </div>
			  
			  
			  
			</div>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			<input type="hidden" value="<%=manga.getId() %>" name="id">
				<input type="hidden" value="modify" name="action">
				
				</div>
			</form>
			<input type="botton" id="btnModifica"class="btn btn-outline-primary" value="Modifica">
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
		
		<script src="js/ModificaManga.js"></script>
</body>
</html>