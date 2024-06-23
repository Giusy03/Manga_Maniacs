 
			
		
<html><head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/HeaderStyle.css">

<style type="text/css">

.nav-link{
color: #ff8000 ;

}
#OPT{
margin-left: 20%;
    margin-right: 36%;
 	

}
.list-group-item 
{
	
 display: flex !important;
}
.dropdown-item
{
color: #ff8000;
}
.start-100 {
    left: 98%!important;
}


</style>

<style type="text/css">@font-face { font-family: Roboto; src: url("chrome-extension://mcgbeeipkmelnpldkobichboakdfaeon/css/Roboto-Regular.ttf"); }</style></head>
<body>
<%@ page import=" model.CartModel"%>
<%
CartModel cart =null;
if( request.getSession().getAttribute("cart")!=null)
cart=(CartModel) request.getSession().getAttribute("cart");
%>
<nav class="navbar navbar-expand-sm">
  <div class="container-fluid">
  <div class="col">
    <a href="manga" style="text-decoration:none;" > <img src="./img/Logo.png" alt="MangaManiacs" class="logo" style="width: 100px; height: auto;"></a> 
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon">Ric</span>
    </button>
   </div> 
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
   
   <div class="col">
    	  <form class="d-flex" action="manga" role="search">
    	  <input class="form-control me-2" type="search" name="titolo" id="search"placeholder="Cerca" aria-label="Search">
   		  <button class="btn btn-outline-success" type="submit">Cerca</button>
   		  </form>
   		  <div class=" sugerimento" id="resultManga"></div>
				     </div>
    </div>
    
    <div class="col">
     <ul class="navbar-nav " id="OPT">
     <%
				if(request.getSession().getAttribute("user")!=null){
					%>
		<li class="nav-item dropdown">
			<div class="dropdown">
				 <a class="nav-link dropdown-toggle" href="profilo?action=profiloView" id="menuProfilo" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	          		 Profilo
	        	 </a>
          		 <ul class="dropdown-menu" aria-labelledby="menuProfilo">
             		<li><a class="dropdown-item" href="profilo?action=profiloView">Area Personale</a></li>
           			 <li><a class="dropdown-item" href="Logout">Disconettiti</a></li>
       			 </ul>
          </div>
         
        
        </li>
					<%
				}else{
				%>
				
				
					<li><a class="nav-link active" aria-current="page"href="Login.jsp">Accedi</a></li>
			 <%
				}
				%>
       
				
					
					
     
       
         <li class="nav-item" style="max-width: 20px;">
          <a class="nav-link active " id="linckShop"  aria-current="page" href="Cart.jsp"> Carrello
          <%if(cart != null && cart.totProdotti()>0){ %>
         		 <span id="tagHeader" style="z-index: 5;position: relative;top: -49px!important;left: 71px!important;" class=" top-0 start-100 translate-middle badge rounded-pill bg-danger"><%=cart.totProdotti() %></span>
          <%
			}
			%>
          </a>
        </li>
      </ul>
    </div>
    </div>
     

</nav>
<script 
  src = "https://code.jquery.com/jquery-3.4.1.min.js" 
  integrità = "sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" 
  crossorigin = "anonimo" > </script >
<script src="js/Ricerca.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>


</body></html>