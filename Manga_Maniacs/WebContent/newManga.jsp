<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%
Collection<?> Generi = (Collection<?>) request.getAttribute("generi");
Collection<?> Depositi = (Collection<?>) request.getAttribute("depositi");
%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,bean.depositoBean,bean.genereBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/addManga.css">
<title>AddManga</title>
</head>
<body>
<article class="card-body">
						<form action="mangaModyficControl" method="post" onsubmit="">
							<div class="">
								<div class="">
									<label for="Titolo">Titolo</label>
										<input type="text" id="Titolo"  request name="Titolo"  placeholder="Titolo">
										<div class="invalid-feedback"  id="errorid">Inserisci un Titolo valido</div>
			  						
								</div>
								
								<div class="col form-group">
									<label>Editore</label>
								  	<input type="text" id="editore" request name="editore" placeholder="Editore">
								  	<div class="invalid-feedback" id="errorid">Inserisci un Editore valido</div>
			  						
								</div>
								
								
							</div>
							<div class="form-row">
								<div class="col form-group">
									<label>Autore</label>
								  <input type="text" id="autore" request name="autore"  placeholder="Autore">
								  	<div class="invalid-feedback" id="errorid">Inserisci un Autore valido</div>
			  					
								</div>
								
								<div class="col form-group">
											<label>Descrizione</label>
											
					     
						 <td>  <textarea name="description"  placeholder="description"></textarea> </td>
								
								
								<div class="col form-group">
									<label>Iva</label>
								  <input type="text" id="iva" request name="iva" min="1"  placeholder="Iva">
								  	<div class="invalid-feedback" id="errorid">Inserisci un valore Iva valida</div>
			  						
								</div>
							</div>
							
							<div class="form-row">
								<div class="col form-group">
									<label>Generi</label>
									
								<%
				if ( Generi != null && Generi.size() != 0) {
					int i =0;
					Iterator<?> it = Generi.iterator();
					while (it.hasNext()) {
				
						genereBean genere = (genereBean) it.next();
			%>
							<input type="checkbox"value="<%=genere.getId()%>" name="generi"><%=genere.getNome() %></option>
							
										
													
									
								
						<%
				}
			} else{
		%>
		<div class="form-group">
			No depositi available
	</div>
		<%
			}
		%>
						
										</select>
								</div>
							
							<div class="form-row">
								<div class="form-group">
									<label>Prezzo</label>
								  	<input type="text" class="form-control" placeholder=" " request name="prezzo" id="prezzo">
								  	<div class="invalid-feedback" id="errorid">Inserisci un prezzo valido</div>
			  					
								</div>
								</div>
								
				<div class="form-row">
			
			 	<%
				if ( Depositi != null && Depositi.size() != 0) {
					int i =0;
					Iterator<?> it = Depositi.iterator();
					while (it.hasNext()) {
						i++;
						depositoBean deposito = (depositoBean) it.next();
			%>
			
					<div class="form-group">
					      <label>Deposito <%=deposito.getNome() %></label>
					       <input type="radio" id="" name="deposito" request value=<%=deposito.getId()%>  >		
					      <input type="number" id="" name="Quantita" min="0" >
			
			</div>
					<%
				}
			} else{
		%>
		<div class="form-group">
			No depositi available
	</div>
		<%
			}
		%>
								<div class="col form-group">
									<label>Foto(URL)</label>
								  	<input type="text" class="form-control" placeholder=" " name="foto" id="foto">
								  	<div class="invalid-feedback" id="errorid">Inserisci un URL valido</div>
			  					
								</div>
							</div>
						    <div class="form-group">
						    	<input type="hidden" name="action" value="add">
						        <button type="submit" class="btn btn-primary btn-block"> Aggiungi al catalogo  </button>
						    </div>             
						</form>
					</article>
</body>
</html>