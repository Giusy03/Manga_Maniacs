<%@ page language="java" import="java.util.*,model.genereModel,Bean.genereBean"%>
     <%
     genereModel gen = new genereModel();
	Collection<?> generi = gen.doRetrieveAll();
	if(generi == null) {
		response.sendRedirect("./manga");	
		
	}
	//mangaBean product = (mangaBean) request.getAttribute("product");
%>



<head>
 <link rel="icon" href="./img/favicon.png" type="image/png">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link  rel="stylesheet" href="css/MyDrop.css">
  
  <style type="text/css">
  
  nav .nav-item{
  margin-left:30%;
  }
 .nav-link{
  color:black;
  
  
  }
  a.nav-link:hover{
  
  color :black;
  text-shadow: 1px -1px 15px hsl(0deg 0% 0%);
  }
	#navbarBtn {
     
      background-color:#ff8000;
  }
  .opFiltro{
  padding-bottom: 1%;
  }
  .menuOp{
      margin: 0% 25% 0% 25%;
  }
  .filtro
  {
 
  display:none;
    margin: 0% 0% 0% 0%;
    padding:0% 0% 0% 0%;
  }
  .btnFiltro,  .btnFiltroRadius{
      display: block;
    width: 100%;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #212529;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
  }

  </style>
</head>
<body>

<nav class="navbar navbar-expand-sm  justify-content-center" id="navbarBtn">
 

  <!-- Links -->
 
  <ul class="navbar-nav" >
    <li class="nav-item">
      <a class="nav-link link" href="manga">HOME </a>
    </li>
    <li class="nav-item">
 <a class="nav-link link dropdown-toggle "   role="button" id="btnNavbarFiltro">Filtro </a>
    </li>

    <!-- Dropdown -->
    <li class="nav-item ">
	    	<div class="dropdown">
	    		<a class="nav-link dropdown-toggle" href="#" id="GenereDrop" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	     		  Generi 
	    		  </a>
	     <ul class="dropdown-menu" aria-labelledby="GenereDrop" >
     <%
			
			if (generi != null && generi.size() != 0) {
				Iterator<?> it2 = generi.iterator();
				while (it2.hasNext()) {
					genereBean genere = (genereBean) it2.next();
		%>
		<a class="dropdown-item" href="manga?genere=<%=genere.getId() %>">  <%=genere.getNome()  %> </a>
			<%
				}
			} else {
		%>
		     Nessun Genere
		<%
				
			} 
		%>
	    </ul>
		
      
      
        
       
      </div>
    </li>
  </ul>
</nav>

<div class="container-fluid filtro">
<div class="row">
<h3>Filtro</h3>


<form action="manga">
		 	<div class="container-fluid">
		 		<div class="row menuOp">
			 		<div class=" col-3 opFiltro" >
			 			<input type="search" class="form-control" style= "margin-bottom: 10%;" name="titolo" autocomplete="off" placeholder="Cerca...Titolo" role="combobox">
			 		</div>
				 	<div class="dropdown col-3 opFiltro" >
						  <button type="button" class="dropdown-toggle btnFiltro" set="0" nameBtn="Autore" selectop="0" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside" >
						   Autore 
						  </button>
						  
			
					</div>
					<div class="dropdown col-3 opFiltro" >
						  <button type="button" class="dropdown-toggle  btnFiltro" set="0" nameBtn="Editore" selectop="0" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside" >
						   Editore 
						  </button>
						  
			
					</div>
					<div class="dropdown col-3 opFiltro" >
						  <button type="button" class="dropdown-toggle  btnFiltro" set="0" nameBtn="Anno" selectop="0" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside" >
						   Anno 
						  </button>
						  
			
					</div>
					<div class="dropdown col-3 opFiltro" >
						  <button type="button" class="  btnFiltro  dropdown-toggle" set="0" nameBtn="Generi" selectop="0" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside" >
						   Generi 
						  </button>
						
			
					</div>
					<div class="dropdown col-3 opFiltro" >
						  <button type="button" class=" btnFiltroRadius  dropdown-toggle" set="0" nameBtn="Ordina per" selectop="0" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
						   Ordina per 
						  </button>
							<div class=" modal-sm dropdown-menu" data-popper-placement="bottom-start" style="position: absolute; inset: 0px auto auto 0px; margin: 0px; transform: translate(12px, 40px);"> 
								 <div class="conteiner">
									 <div class="raw overflow-auto" style="max-height: 124.922px;overflow-y: auto;min-height: 11px;">
											 <div class="col">
											 	<input class="form-radio-input" check="0" name="Ordina" type="radio" value="A-Z" id="idA-Z"> 
											 	<label class="form-radio-label" for="idA-Z"> A-Z </label>
											 </div>
											 <div class="col">
											 	<input class="form-radio-input" check="0" name="Ordina" type="radio" value="Z-A" id="idZ-A"> 
											 	<label class="form-radio-label" for="idZ-A"> Z-A </label>
										 	</div>
										 	<div class="col">
											 	<input class="form-radio-input" check="0" name="Ordina" type="radio" value="Meno recenti" id="idMeno recenti"> 
											 	<label class="form-radio-label" for="idMeno recenti"> Meno recenti </label>
										 	</div>
										 	<div class="col">
											 	<input class="form-radio-input" check="0" name="Ordina" type="radio" value="Piu recenti" id="idPiu recenti">
											 	 <label class="form-radio-label" for="idPiu recenti"> Piu recenti </label>
										 	 </div>
								 	 	</div> 
								 	 </div>
							 </div>
					</div>
					<div class="dropdown col-3 opFiltro" >
						
			
					</div>
					<div class="dropdown col-3 opFiltro" >
						<input type="hidden"  name="action" value="filter">
					<input class="btn btn-primary" type="submit" value="Cerca">
					</div>
		  		</div>
		  		
			</div>
			
				
			</form>
  </div>
</div>



</body>
<script src="js/Filtro.js" ></script>

</html>

