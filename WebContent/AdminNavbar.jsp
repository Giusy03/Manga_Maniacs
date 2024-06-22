
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/NavStyle.css">
<title>Insert title here</title>


 
  <style type="text/css">
  
  nav .nav-item{
  margin-left:5%;
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
      <a class="nav-link link " href="adminControl?action=Dashboard">Dashboard</a>
    </li>
    <li class="nav-item">
		<a class="nav-link link " href="OrderControl?action=viewOrder">Visualizza ordini</a>
    </li>
<li class="nav-item">
		 <a  class="nav-link link" href="adminControl?action=modCatalogo">Gestisci catalogo</a>
    </li>
    <li class="nav-item">
		<a class="nav-link link " href="adminControl?action=viewUtenti">Lista clienti</a>
    </li>
    <!-- Dropdown -->
    <li class="nav-item ">
	    	<div class="dropdown">
	    		<a class="nav-link dropdown-toggle" href="#" id="GenereDrop" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	     		  Generi 
	    		  </a>
	    		 <ul class="dropdown-menu" aria-labelledby="GenereDrop" >
   
					<a class="dropdown-item" href="adminControl?action=addGenere">Aggiungi Genere   </a>
					<a class="dropdown-item" href="adminControl?action=modyficaGenere">  Modifica Genere </a>
			
	 			   </ul>
		
      
      
        
       
      </div>
    </li>
  </ul>
</nav>




























</body>
</html>