 <%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*, Bean.PagamentoBean, model.PagamentoModel, Bean.UserBean, Bean.addressBean"%>
 
 <%
 	Collection<?> carte = (Collection<?>) request.getAttribute("payment");
 	Collection<?> address = (Collection<?>) request.getAttribute("address");
 	UserBean user = (UserBean)request.getSession().getAttribute("user");
 %>
    
	


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<%@include file="Header.jsp"  %>
	<%@include file="navbar.jsp"  %>

<body>

	<div class="container infoContainer">
		
		<div class="raw">
		
			<%
				if((user.getNome()==null || user.getNome().equalsIgnoreCase("")) && (user.getCognome()==null || user.getCognome().equalsIgnoreCase(""))){
			
			%>
			
			<form action="AMU" method="post">
			
			<input type="hidden" name="action" value="cambiaAnagrafica">
			
				<label for="mNome">Nome</label> 
            	
              <input type="text"  class="form-control" name="nome" value="" placeholder="Nome"> 
              
            
            <label for="mCognome">Cognome</label>
            
              <input type="text"  class="form-control" name="cognome" value="" placeholder="Cognome"> 
              
              <input type="submit"  class="btn btn-outline-warning" value="registra">
            
				
			
			</form>
			
			<%}else{%>
			
			<h3 id="cliente"><b>Cliente:<%=" "+ user.getNome()+" " + user.getCognome() %> </b></h3>
			
			
			<%}
			%>
			
		
		</div>
		
		
		<%
			if((carte != null && carte.size() != 0) && (address != null && address.size() != 0)){
		%>
		<form action="OrderControl">
		<%} %>
		<div class="raw">
			<h3><b>Seleziona Metodo Di Pagamento</b></h3>
			
  				
  		
			
		<%
			if (carte != null && carte.size() != 0) {
				
				Iterator<?> it = carte.iterator();
				while (it.hasNext()) {
					PagamentoBean carta = (PagamentoBean) it.next();

				
		%>
		
		<div class="col-sm-6">
   			
   			
   			<div class="card">
   		
      			<div class="card-body">
        			<h5 class="card-title">Numero carta: <%=carta.getNumeroCarta() %> </h5>
        				<p class="card-text">Scadenza:   <%=carta.getScadenza() %></p>
        				<input class="input-form" type="radio" value="<%=carta.getNumeroCarta() %>" name="numeroCarta">
        				
      			</div>
    		</div>
    		
  		</div>
		
		<%
			}
			}else{
				
				%>
				
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
			  						
			  						<input type="hidden" value="aggiungiMetodoPagamento" name="action">
						        	<button type="submit" class="btn btn-outline-warning"> Registra la Carta  </button>
							</div>
							</form>
											
				<%
			}
				%>
			
		</div>
		<div class="raw">
		
		<h3><b>Seleziona Indrizzo Di Spedizione</b></h3>
			
			
		<%
			if (address != null && address.size() != 0) {
				
				Iterator<?> it = address.iterator();
				while (it.hasNext()) {
					addressBean ad = (addressBean) it.next();

				
		%>
		
		<div class="col-sm-6">
   			<div class="card">
   		
      			<div class="card-body">
      			
        			<h5 class="card-title"><%=ad.getComune() %> (<%=ad.getProvincia() %>)</h5>
        				<p class="card-text">Via/Piazza <%=ad.getVia() %>, <%=ad.getCivico() %></p>
        				<input class="input-form" type="radio" value="<%=ad.getVia() + " " + ad.getCivico() + " " + ad.getComune() + " " + ad.getProvincia() +" (" +  ad.getCap() + " )" %>" name="indirizzo">
        				
      			</div>
    		</div>
  		</div>
		
		<%
			}
			}else{
				
				%>
				
				<h3><b>Nuovo Indirizzo</b></h3>
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
							<input type="hidden" value="addPagamento" name="action">
						        <button type="submit" class="btn btn-outline-warning"> Registra l'indirizzo</button>
						        
						</form>
				
				<% 
			}
				%>
			
		</div>
	
		<%
			if((carte != null && carte.size() != 0) && (address != null && address.size() != 0)){
		%>
			<input type="hidden" value="completeOrder" name="action">
			<input type="hidden" value="2" name="step">
			<input type="submit" class="btn btn-sm btn-outline-secondary" disabled id="btnConferm" value="Conferma Aquisto">
		
		</form>
		
		<%} %>
		</div>
	

	<script src="js/pagamento.js"></script>
	<script src="js/address.js"></script>
   <script src="js/validazioneCarta.js"></script>

</body>
</html>