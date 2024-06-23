<%@ page language="java" pageEncoding="ISO-8859-1" import="java.util.*,Bean.mangaBean"%>
     <%
	Collection<?> products = (Collection<?>) request.getAttribute("mangas");
	if(products == null) {
		response.sendRedirect("./manga");	
		
	}
	//mangaBean product = (mangaBean) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<style type="text/css">
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

</style>

<title>AreaAdmin</title>
</head>
<body>
	<%@include file="Header.jsp" %>
	<%@include file="AdminNavbar.jsp"  %>
		<div class="d-flex justify-content-between align-items-center">
<form action="adminControl" method="post">
			<input type="hidden" value="addPage" name="action">
			<input type="submit" class="btn btn-outline-secondary btn-lg" value="Aggiungi un nuovo articolo">
		</form>
		<div>
		<label for="searchMangaCA" class="form-label">Ricerca tramite username </label>
			<input class="search" id="searchMangaCA" type="search">
		</div>
	</div>
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
              		
              			<b data-val="mangaTitolo"><%=manga.getTitolo() %></b>
              			<br>
              			<div class="d-flex justify-content-between align-items-center">
              			<p>&euro;<%=manga.getPrezzo() %></p>
              		<p>	Quantita:<%=manga.getQuantita() %></p>
              			</div>
             
         
              		
           
             <div align="center">
           
             	 <div class="d-flex justify-content-between align-items-center">
			
						<form action="adminControl" method="post">
							<input type="hidden" value="remove" name="action">
							<input type="hidden" value="<%=manga.getId() %>" name="id">
							<input type="submit" class="btn btn-sm btn-outline-secondary" value="Rimuovi">
						</form>
				
						<form action="adminControl" method="post">
							<input type="hidden" value="modifyPage" name="action">
							<input type="hidden" value="<%=manga.getId() %>" name="id">
							<input type="submit" class="btn btn-sm btn-outline-secondary" value="Modifica">
						</form>
				
          </div>
              
           
              
              </div>
            </div>
          </div>
        </div>
        	</div>
			<%
				
				}
				}else {%>
			<h1>Nessun Prodotto</h1>	
			<%
				}
				%>
			
      
      	</div>
    </div>
    </div>
	
	
	<script src="js/catalogoAdmin.js"></script>
</body>
</html>