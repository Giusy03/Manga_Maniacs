
    <%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,Bean.mangaBean,Bean.UserBean , Bean.OrderBean,Bean.addressBean, Bean.PagamentoBean"%>
    <%
	 UserBean user = (UserBean)request.getSession().getAttribute("user");
		String nome = "";
		if(user==null){
			response.sendRedirect("./manga");
		
		}
		Collection<?> order = (Collection<?>) request.getAttribute("order");
		if(order == null) {
			response.sendRedirect("./manga");	
			
		}
			
	
		Collection<?> address = (Collection<?>) request.getAttribute("address");
		
		Collection<?> pagamenti = (Collection<?>) request.getAttribute("payment");
		
		

	%>
		
	
 
    
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<%@include file="Header.jsp"  %>
	<%@include file="navbar.jsp"  %>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Profilo utente</title>
  <style>
    #NavBarProfiloUtente > .nav-link,
    .nav-tabs .nav-link.active {
      color: #EA4D00;
      background-color: #fff;
      border-color: #dee2e6 #dee2e6 #fff;
    }
    .btn-outline-warning {
    color: #EA4D00;
    border-color: #EA4D00;
    ;
}

.list-group-item{
    max-width: 500px;
}




.list-group-item  {
    display: flex;
}


.page{
  display: none;
}
.visibile{
  display: block;
}

  </style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>




</head>
<body>







 <div id="NavBarProfiloUtente" class="container ">
    <div class="container nav">
      <div class="row">

        <ul class="nav nav-tabs">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page"id="btnIMA" href="#">Il Mio Account</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="btnIMO" href="#">I Miei ordini</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="btnI" href="#">Informazioni</a>
          </li>
          <li class="nav-item">

            <a class="nav-link" id="btnP" href="#">Pagamenti</a>
          </li>
          <li class="nav-item">
          <a class="nav-link  "  id="btnAddr">Modifica indirizzi</a>

          </li>
        </ul>

      </div>


    </div>
    <div id="conIMA" class="container page visibile ">

      <div class="container px-4">
        <div class="row gx-5">
          <div class="col">
            <div class="p-3 ">

              <h3>Dati account</h3>
              <ul class="list-group list-group-flush">
                <li class="list-group-item "id="datiUsername">Username:		<%=user.getUsername() %></li>
                <li class="list-group-item" id=" datiNome">Nome:    		<%=user.getNome() %></li>
                <li class="list-group-item "id="datiCognome">Cognome:		<%=user.getCognome() %></li>
                <li class="list-group-item "id="datiE-mail">E-mail: 		<%=user.getEmail() %></li>
                <li class="list-group-item"></li>
              </ul>
            </div>
          </div>
       
           
        </div>
      </div>

    </div>

  </div>

 
  <div id="conIMO" class="container page  ">
      <h3 class="title">I miei ordini</h3>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Numero ordine</th>
      <th scope="col">Data ordine</th>
      <th scope="col">Totale Pagato</th>
      <th scope="col"></th>
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
      
     <td><%=bean.getId() %></td>
			<td><%=bean.getData_ordine() %></td>
			<td><%=bean.getSomma_tot() %></td>
			<td><a href="OrderControl?action=orderDetails&id=<%=bean.getId()%>">Dettagli</a></td>
			<td><a href="OrderControl?action=generateInvoice&id=<%=bean.getId()%>">Scarica la fattura</a></td>
		
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

  </div>



  <div id="conI" class="container page  ">
    <h3>
      Informazioni sull account
    </h3>
   
    
  <div class="container px-4">
    <div class="row gx-5">
      <div class="col">
        <div class="p-3 ">

          <h3>Dati account</h3> 
          <ul class="list-group list-group-flush">
           <label for="mNome">Nome:</label>
            <li class="list-group-item">
            
              <input type="text"  class="form-control-plaintext" id="mNome" value="<%=user.getNome()%>" placeholder="<%=user.getNome()%>"> 
              
            </li>
            <label for="mCognome">Cognome</label>
            <li class="list-group-item">
              <input type="text"  class="form-control-plaintext" id="mCognome" value="<%=user.getCognome()%>" placeholder="<%=user.getCognome()%>"> 
            </li>
            <label for="mEmail">E-Mail</label>
            <li class="list-group-item">
              <input type="text"  class="form-control-plaintext" id="mEmail" value="<%=user.getEmail()%>" placeholder="<%=user.getEmail()%>"> 
            </li>
            <li class="list-group-item">
              <button type="button" class="btn btn-outline-warning " id="modelconfermDati"  >Modifica Dati</button>
            </li>
            
            
          </ul>
        </div>
      </div>
      <div class="col">
        <div class="p-3 ">
          <h3>Cambia password</h3>
          <ul class="list-group list-group-flush">
            
            <li class="list-group-item">
              <label for="Password attuale">Password attuale</label>
              <input type="password" id="PasswordAttuale" class="form-control-plaintext" ></li>
           
            <li class="list-group-item">
              <label for="password">Nuova Password</label>
              <input type="password" id="NuovaPassword" class="form-control-plaintext"  ></li>
                 <div id="errorNpsw" style="display:none" class="text-danger ">  </div>
              <div id="successNpsw" style="display:none" class="text-success "> OK </div>
            <li class="list-group-item">
              <label for="ConfermaNuovaPassword">Conferma nuova Password</label>
              <input type="password" id="ConfermaNuovaPassword" class="form-control-plaintext" ></li>
                 <div id="errorCpsw" style="display:none" class="text-danger "> La Password non corrisponde </div>
              <div id="successCpsw" style="display:none" class="text-success "> OK </div>
            <li class="list-group-item">
            <button type="button" id="btnModificaPsw" class="btn btn-outline-warning">Modifica passworld</button>
          </ul>
        </div>
      </div>
      
    </div>
    <div class="row gx-5">
      <div class="col">
        <div class="p-1 ">
          <h3>Cambia Username</h3>
          <ul class="list-group list-group-flush">
            
            <li class="list-group-item">
              <label for="Username">Username</label>
              <input type="Username" id="Username" class="form-control-plaintext" ></li>
            <li class="list-group-item">
              <label for="UsernamePassword">Password</label>
              <input type="text" id="UsernamePassword" class="form-control-plaintext"  ></li>
            
            <li class="list-group-item">
            <button type="button"id="btnModificaUsername" class="btn btn-outline-warning">Modifica Username</button>
          </ul>
        </div>
      </div>
      

    </div>
</div>

  


<!-- Modal -->
<div class="modal fade" id="modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="staticBackdropLabel">Avviso</h5>
      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
   
  		
     
 		
  
  


    </div>
    <div class="modal-footer">
      <button type="button"  id="confermaModifica" class="btn btn-primary ">Modifica</button>
    </div>
  </div>
</div>
</div>

<!-- Modal error -->

<div class="modal fade" id="modalError" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="staticBackdropLabel">Avviso</h5>
      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
    

    </div>
    <div class="modal-footer">
      <button type="button" id="confermaModifica" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
      
    </div>
  </div>
</div>
</div>

     

</div>


  <div id="conP" class="container page  ">
    <h3>
      Metodi di pagamento
    </h3>
   
    
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">Carta di credito/debito</th>
	      <th scope="col">Data di scadenza</th>
	      <th scope="col"></th>
	    </tr>
	  </thead>
	  <tbody>
	  
	   <%
			if (pagamenti != null && pagamenti.size() != 0) {
				Iterator<?> itt = pagamenti.iterator();
				while (itt.hasNext()) {
					PagamentoBean pagamento = (PagamentoBean) itt.next();
				
			
		%>
		
		<tr>
		
			<td><%=pagamento.getNumeroCarta()%></td>
			<td><%=pagamento.getScadenza() %></td>
			<td><form action="PagamentoServlet">
      							<input type="hidden" value="rimuoviMetodo" name="action" >
        						<input type="hidden" value="<%=pagamento.getNumeroCarta()%>" name="numeroCarta">
						       <button type="submit" class="btn-close"  aria-label="Close" ></button>
				</form>
			</td>
			
		</tr>
		
		<%
				}
			} else {
		%>
		     Nessun Metodo di Pagamento
		<%
				
			} 
		%>
		
		</tbody>
	</table>
		
	 	<h3><b>Inserisci un nuovo metodo di pagamento</b></h3>
						<form action="PagamentoServlet" method="get" onsubmit="event.preventDefault(); cardcheck(this)" >
							<div class="form-row">
								<div class="col form-group">
									<label>Numero Carta </label>   
			  						<input type="text" class="form-control" placeholder="" id="numeroCarta" name="numeroCarta">
									<div class="invalid-feedback" id="errorid">Inserisci un numero di carta valido</div>
			  						<div class="valid-feedback" id="errorName">Corretto</div>
			  					</div>
			  					<div class ="form-row">
			  						<label>Scadenza Carta </label>   
			  						<input type="text" class="form-control" placeholder="" id="scadenza" name="scadenza">
									<div class="invalid-feedback" id="errorid">Inserisci una scadenza valida</div>
			  						<div class="valid-feedback" id="errorName">Corretto</div>
			  					</div>
			  					
			  					<div class ="form-row">
			  						<label>Cvv</label>   
			  						<input type="text" class="form-control" placeholder="" id="cvv" name="cvv">
									<div class="invalid-feedback" id="errorid">Inserisci un cvv valido</div>
			  						<div class="valid-feedback" id="errorName">Corretto</div>
			  					</div>
			  					
			  					<div class ="form-row">
			  						<label>Nome titolare </label>   
			  						<input type="text" class="form-control" placeholder="" id="nomeTitolare" name="nomeTitolare">
									<div class="invalid-feedback" id="errorid">Inserisci un nome valido</div>
			  						<div class="valid-feedback" id="errorName">Corretto</div>
			  					</div>
			  					
			  					<div class ="form-row">
			  						<label>Cognome titolare </label>   
			  						<input type="text" class="form-control" placeholder="" id="cognomeTitolare" name="cognomeTitolare">
									<div class="invalid-feedback" id="errorid">Inserisci un cognome valido</div>
			  						<div class="valid-feedback" id="errorName">Corretto</div>
			  					</div>
			  						
			  						<input type="hidden" value="aggiungiMetodo" name="action">
						        	<button type="submit" class="btn btn-outline-warning"> Registra la Carta  </button>
	 
	  

</div>
  


<!-- Modal -->
<div class="modal fade" id="modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="staticBackdropLabel">Avviso</h5>
      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
   
  		
     
 		
  
  


    </div>
    <div class="modal-footer">
      <button type="button"  class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
      <button type="button"  id="confermaModifica" class="btn btn-primary ">Modifica</button>
    </div>
  </div>
</div>
</div>

<!-- Modal error -->

<div class="modal fade" id="modalError" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="staticBackdropLabel">Avviso</h5>
      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
    

    </div>
    <div class="modal-footer">
      <button type="button" id="confermaModifica" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
      
    </div>
  </div>
</div>
</div>

     

</div>

 <div id="conAddres" class="container page  ">
 <div class="row">
<article class="card">
<h3><b>I miei Indirizzi</b></h3>

	<div class="row" style=" padding-right: 15px;padding-left: 15px;">
		<%
			if (address != null && address.size() != 0) {
				Iterator<?> it = address.iterator();
				while (it.hasNext()) {
					addressBean ad = (addressBean) it.next();
		%>
		<form action=""></form>
		<div class="col-sm-6">
   			
   		<form action=""></form>
      			<div class="card-body">
      			<form action="addressC">
      							<input type="hidden" value="remove" name="action" >
        						<input type="hidden" value="<%=ad.getId() %>" name="id">
						       <button type="submit" class="btn-close"  aria-label="Close" ></button>
				</form>
        			<h5 class="card-title"><%=ad.getComune() %> (<%=ad.getProvincia() %>)</h5>
        				<p class="card-text">Via/Piazza <%=ad.getVia() %>, <%=ad.getCivico() %></p>
        				
      			</div>
    		</div>
  		</div>
		<%}
				}%>
	</div>
	
	</article>
	
<div class="row">
<article class="card">
<h3><b>New indirizzo</b></h3>
						<form action="addressC" method="post" onsubmit="event.preventDefault(); addressCheck(this)">
							<div class="form-row">
								<div class="col form-group">
									<label>Comune </label>   
			  						<input type="text" class="form-control" placeholder="" id="comune" name="comune">
									<div class="invalid-feedback" id="errorid">Inserisci un comune valido</div>
			  						<div class="valid-feedback" id="errorName">Corretto</div>
								</div>
								<div class="col form-group">
									<label>Provincia</label>
								  	<select class="form-control" name="provincia" id="provincia">
								  		<option value="provincia">Provincia</option>
								  		<option value="AG">AG</option>
										<option value="AL">AL</option>
										<option value="AN">AN</option>
										<option value="AO">AO</option>
										<option value="AQ">AQ</option>
										<option value="AR">AR</option>
										<option value="AP">AP</option>
										<option value="AT">AT</option>
										<option value="AV">AV</option>
										<option value="BA">BA</option>
										<option value="BT">BT</option>
										<option value="BL">BL</option>
										<option value="BN">BN</option>
										<option value="BG">BG</option>
										<option value="BI">BI</option>
										<option value="BO">BO</option>
										<option value="BZ">BZ</option>
										<option value="BS">BS</option>
										<option value="BR">BR</option>
										<option value="CA">CA</option>
										<option value="CL">CL</option>
										<option value="CB">CB</option>
										<option value="CI">CI</option>
										<option value="CE">CE</option>
										<option value="CT">CT</option>
										<option value="CZ">CZ</option>
										<option value="CH">CH</option>
										<option value="CO">CO</option>
										<option value="CS">CS</option>
										<option value="CR">CR</option>
										<option value="KR">KR</option>
										<option value="CN">CN</option>
										<option value="EN">EN</option>
										<option value="FM">FM</option>
										<option value="FE">FE</option>
										<option value="FI">FI</option>
										<option value="FG">FG</option>
										<option value="FC">FC</option>
										<option value="FR">FR</option>
										<option value="GE">GE</option>
										<option value="GO">GO</option>
										<option value="GR">GR</option>
										<option value="IM">IM</option>
										<option value="IS">IS</option>
										<option value="SP">SP</option>
										<option value="LT">LT</option>
										<option value="LE">LE</option>
										<option value="LC">LC</option>
										<option value="LI">LI</option>
										<option value="LO">LO</option>
										<option value="LU">LU</option>
										<option value="MC">MC</option>
										<option value="MN">MN</option>
										<option value="MS">MS</option>
										<option value="MT">MT</option>
										<option value="VS">VS</option>
										<option value="ME">ME</option>
										<option value="MI">MI</option>
										<option value="MO">MO</option>
										<option value="MB">MB</option>
										<option value="NA">NA</option>
										<option value="NO">NO</option>
										<option value="NU">NU</option>
										<option value="OG">OG</option>
										<option value="OT">OT</option>
										<option value="OR">OR</option>
										<option value="PD">PD</option>
										<option value="PA">PA</option>
										<option value="PR">PR</option>
										<option value="PV">PV</option>
										<option value="PG">PG</option>
										<option value="PU">PU</option>
										<option value="PE">PE</option>
										<option value="PC">PC</option>
										<option value="PI">PI</option>
										<option value="PT">PT</option>
										<option value="PN">PN</option>
										<option value="PZ">PZ</option>
										<option value="PO">PO</option>
										<option value="RG">RG</option>
										<option value="RA">RA</option>
										<option value="RC">RC</option>
										<option value="RE">RE</option>
										<option value="RI">RI</option>
										<option value="RN">RN</option>
										<option value="RM">RM</option>
										<option value="RO">RO</option>
										<option value="SA">SA</option>
										<option value="SS">SS</option>
										<option value="SV">SV</option>
										<option value="SI">SI</option>
										<option value="SR">SR</option>
										<option value="SO">SO</option>
										<option value="TA">TA</option>
										<option value="TE">TE</option>
										<option value="TR">TR</option>
										<option value="TO">TO</option>
										<option value="TP">TP</option>
										<option value="TN">TN</option>
										<option value="TV">TV</option>
										<option value="TS">TS</option>
										<option value="UD">UD</option>
										<option value="VA">VA</option>
										<option value="VE">VE</option>
										<option value="VB">VB</option>
										<option value="VC">VC</option>
										<option value="VR">VR</option>
										<option value="VV">VV</option>
										<option value="VI">VI</option>
										<option value="VT">VT</option>
								  	</select>
								  	<div class="invalid-feedback" id="errorid">Inserisci una provincia valida</div>
			  						<div class="valid-feedback" id="errorName">Corretto</div>
								</div>
								<div class="col form-group">
									<label>CAP</label>
									<input type="text" class="form-control" placeholder="" name="cap" id="cap">
									<div class="invalid-feedback" id="errorid">Inserisci un CAP valido</div>
			  						<div class="valid-feedback" id="errorName">Corretto</div>
								</div> 
							</div> 
							<div class="form-row">
								<div class="col form-group">
									<label>Via</label>
								  	<input type="text" class="form-control" placeholder=" " name="via" id="via">
									<div class="invalid-feedback" id="errorid">Inserisci una via valida</div>
			  						<div class="valid-feedback" id="errorName">Corretto</div>
								</div> 
								<div class="col form-group">
								<label>Civico</label>
								<input type="text" class="form-control" placeholder="" name="civico" id="civico">
								<div class="invalid-feedback" id="errorid">Inserisci un civico valido</div>
			  					<div class="valid-feedback" id="errorName">Corretto</div>
							</div>
							</div>
							<input type="hidden" value="add" name="action">
						        <button type="submit" class="btn btn-outline-warning"> Registra l'indirizzo</button>
						</form>
						 
					</article> 
</div>






















</div>



  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
  <script src="js/profilo.js"></script>
  <script src="js/checkFormProfilo.js"></script>
    <script src="js/address.js"></script>
   <script src="js/validazioneCarta.js"></script> 

</body>
</html>