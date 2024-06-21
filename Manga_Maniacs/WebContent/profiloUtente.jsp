
    <%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,bean.mangaBean,bean.userBean , bean.orderBean"%>
    <%
	 userBean user = (userBean)request.getSession().getAttribute("user");
		String nome = "";
		if(user==null){
			response.sendRedirect("./manga");
		
		}
		Collection<?> order = (Collection<?>) request.getAttribute("order");
		if(order == null) {
			response.sendRedirect("./manga");	
			
		}
			
		
	%>
 
    
<!DOCTYPE html>
<html>
<head>

<%@include file="Header.jsp"  %>
	<%@include file="navbar.jsp"  %>

  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Profilo utente</title>
  <style>
    .nav-link,
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







 <div id="" class="container ">
    <div class="container nav">
      <div class="row">

        <ul class="nav nav-tabs">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page"id="btnIMA" href="#">il mio account</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="btnIMO" href="#">I Miei ordini</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="btnI" href="#">informazioni</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled">Disabled</a>
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
          <div class="col">
            <div class="p-3 ">
              <h3>Metodo di pagamento</h3>
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
      <th scope="col">Prodotti contenuti nell'ordin</th>
      <th scope="col">Data ordineì</th>
      <th scope="col">Totale Pagato</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  
  <%
			if (order != null && order.size() != 0) {
				Iterator<?> it = order.iterator();
				while (it.hasNext()) {
					orderBean bean = (orderBean) it.next();
		%>
  
  
  
    <tr>
      
     <td><%=bean.getId() %></td>
			<td><%=bean.getData_ordine() %></td>
			<td><%=bean.getSomma_tot() %></td>
			<td><a href="OrderControl?action=orderDetails&id=<%=bean.getId()%>">Dettagli</a></td>
		
    </tr>
  
  
  
  	<%
				}
			} else {
		%>
		     nessun ordine
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
          <h3>Cambia passworld</h3>
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
      <button type="button"  class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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
      <button type="button" id="confermaModifica" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      
    </div>
  </div>
</div>
</div>

     

</div>







  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
  <script src="js/profilo.js"></script>
  <script src="js/checkFormProfilo.js"></script>

</body>
</html>