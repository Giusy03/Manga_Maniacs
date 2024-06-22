<%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,Bean.mangaBean,Bean.UserBean,Bean.genereBean"%>
     <%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./manga");	
		
	}
	//mangaBean product = (mangaBean) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
 <style>
    body{
    padding-top:60px;
    }
    
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }
      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      .parent{
		text-align:center;
		}

.detDescrizione{
	  display:none;
	  overflow:auto;
	  max-height:150px;
}

    </style>
<meta charset="ISO-8859-1">
<title>MangaManiacs</title>
</head>

<body>
	<%@include file="Header.jsp"  %>
	<%UserBean user = (UserBean)request.getSession().getAttribute("user");
	Boolean admin = false;
	if(user == null){ %>
		<%@include file="navbar.jsp"  %>
	<%}else {
		admin=user.isAmministratore();
	if(user != null && admin){%>
		<%@include file="AdminNavbar.jsp"  %>		
	<%}else{ %>
		<%@include file="navbar.jsp"  %>
	<%}} %>
	
	
	
	 <div class="album py-5 bg-light">
	    	<div class="container">
	    	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
	    	<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					mangaBean manga = (mangaBean) it.next();
			%>
			<div class="col ">
          		<div class="card shadow-sm prodottoContainer">
          		<div class="parent">
            		<img src="<%=manga.getImg() %>" width="200px" height="200px" >

            		<div class="card-body">
              		<p class="card-text">
              		
              			<b><%=manga.getTitolo() %></b>
              			<br>
              			&euro;<%=manga.getPrezzo() + (manga.getPrezzo()*manga.getIva()/100) %>
              		</p>
              		<p>	
              		<%
              	 
				Collection<?> generi = (Collection<?>) manga.getGeneri();
			if (generi != null && generi.size() != 0) {
				Iterator<?> it2 = generi.iterator();
				while (it2.hasNext()) {
					genereBean gen = (genereBean) it2.next();
		%>
		 <a href="manga?genere=<%=gen.getId() %>">- <%=gen.getNome()  %></a>
			<%
				}
			} else {
		%>
		     Nessun Genere
		<%
				
			} 
		%>
		</p>
              		
             <%if( admin){ %>
             <div align="center">
             <form action="deteilControl" method="post">
             	<input type="hidden" value="<%=manga.getId() %>" name="id">
              	<input type="submit" class="btn btn-sm btn-outline-secondary" value="Dettagli">
              </form>
             </div>
             <%} else {%> 		
              <div class="d-flex justify-content-between align-items-center">
             <form action="deteilControl" method="post" >
             	<input type="hidden" value="<%=manga.getId() %>" name="id">
              	<input type="submit" class="btn btn-sm btn-outline-secondary btnDettagli" value="Dettagli">
              </form>
              <%if(manga.getQuantita()<=0)
            	  {
            	  
            	  
            	 
            	  
            	  %>
            	   <form action="">
            	  <input type="button"  class="btn btn-sm btn-outline-secondary" disabled value="Esaurito">
            	      </form>
            	     </div>
            	  <%}else if(cart!=null && cart.prodottoExist(manga.getId())){
            	  
            	  
            	  
            	  %>
		            	     <form action="">
		            	  <input type="button"  class="btn btn-sm btn-outline-secondary" disabled value="Aggiunto al carrello">
		            	      </form>
		            	      </div>
            	  <% }else{ %>
                  <form action="CartServlet" method="post"  onsubmit=" addCart()">
               	<input type="hidden" value="add" name="action">
               	<input type="hidden" value="<%=manga.getId() %>" name="id">
               	<input type="hidden" value="manga" name="redirect">
                	<input type=button class="btn btn-sm btn-outline-secondary" value="Aggiungi al carrello">
                </form>
                </div>
                <%}} %>
              <br>
              
              <p class="detDescrizione"><%=manga.getDescrizione() %></p>
              
           
              
              </div>
            </div>
          </div>
        </div>
			<%
	
				}
				}else{%>
			<h1>Nessun Prodotto</h1>	
			<%
				}
				%>
			
      	</div>
      	</div>
    </div>

	
	

	
	<%@include file="footer.jsp" %>
	<script src="js/catalogo.js"></script>
	<script src="js/cartAjax.js"></script>

</body>
</html>