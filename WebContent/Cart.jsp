

<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,Bean.mangaBean, Bean.UserBean"%>
<%

	double totOrdine = 0, totIvaOrdine = 0;
	
%>

<%
	Collection<?> mangasError = (Collection<?>) request.getSession().getAttribute("mangasError");
%>


<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<style>
body { 
    padding-top: 60px; 
   
}
a{
	text-decoration: none;
}
</style>
<meta charset="ISO-8859-1">
<title>Carrello</title>
</head>
<body>
<%@include file="Header.jsp"  %>
		<%@include file="navbar.jsp"  %>	
	<%
		if(cart == null || cart.size() == 0){
	%>
	<section class="vh-100"">
		<div class="container py-5 h-100">
    		<div class="row d-flex justify-content-center align-items-center h-100">
      			<div class="col col-xl-10">
			        <div class="card" style="border-radius: 1rem;">
			          <div class="row g-0">
			            <div class="col-md-6 col-lg-5 d-none d-md-block">
<!-- 			              <img -->
<!-- 			                src="img/smartmangas.jpg" -->
<!-- 			                alt="Carrello Vuoto" -->
<!-- 			                class="img-fluid" style="border-radius: 1rem 0 0 1rem;" -->
<!-- 			              /> -->
			            </div>
            			<div class="col-md-6 col-lg-7 d-flex align-items-center">    
             				<div class="card-body p-4 p-lg-5 text-black">
             					<h1>Oooops!<br>Il tuo carrello è vuoto</h1>
              					<a href="manga" class="btn btn-dark btn-lg btn-block">Torna al catalogo</a>
							</div>
            			</div>
          			  </div>
        			</div>
      			</div>
    		</div>
  		</div>
	</section>
	<%
		}else{
	%>
	<h1 style="padding-left: 20px;">Il tuo carrello</h1>
	<%
		if(mangasError!=null && mangasError.size() != 0){
			
	%>
	
	<h3>Ops! Uno o più prodotti sono esauriti!</h3>
	
	<%
		}
	%>
	
	<table class="table">
		 <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Foto</th>
		      
		      <th scope="col">Titolo</th>
		      <th scope="col">Prezzo</th>
		      <th scope="col">Quantità</th>
		      <th scope="col"></th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <%
			ArrayList <mangaBean> mangas = cart.getProducts(); 
			ArrayList<Integer> click = cart.getQuantity();
			for (int i = 0; i < mangas.size(); i++) {
				mangaBean manga = mangas.get(i); 
				int clicks = click.get(i);
			%>
				<tr>
			      	<th scope="row"><%=i+1%></th>
				    <td><img src="<%=manga.getImg() %>" alt="<%=manga.getTitolo() %>" class="img-fluid" 
				    	 height="60" width="60"/>
				    </td>
					
					<td><%=manga.getTitolo() %></td>
					<td>&euro;<%=manga.getPrezzo() + (manga.getPrezzo()*manga.getIva()/100) %></td>
					<td id="clicks"><%=clicks %></td>
					<td>
						<form action="CartServlet" method="post">
							<input type="hidden" value="plus" name="action">
							<input type="hidden" value="<%=manga.getId() %>" name="id">
							<input type="hidden" value="Cart.jsp" name="redirect">
							<input type="submit" class="btn btn-sm btn-outline-secondary" value="+">
						</form>
						<!-- <input type="submit" onclick="add()" class="btn btn-sm btn-outline-secondary" value="+"> -->
					</td>
					<td>
						<form action="CartServlet" method="post">
							<input type="hidden" value="minus" name="action">
							<input type="hidden" value="<%=manga.getId() %>" name="id">
							<input type="hidden" value="Cart.jsp" name="redirect">
							<input type="submit" class="btn btn-sm btn-outline-secondary" value="-">
						</form> 
						<!-- <input type="submit" onclick="remove()" class="btn btn-sm btn-outline-secondary" value="-"> -->
					</td>
					<td>
						<form action="CartServlet" method="post">
							<input type="hidden" value="delete" name="action">
							<input type="hidden" value="<%=manga.getId() %>" name="id">
							<input type="hidden" value="Cart.jsp" name="redirect">
							<input type="submit" class="btn btn-sm btn-outline-secondary" value="Rimuovi">
						</form>
					</td>
				</tr>
				<%totOrdine = totOrdine + (manga.getPrezzo() * clicks);
				totIvaOrdine = totIvaOrdine +   (manga.getPrezzo() * clicks) *manga.getIva()/100; %>
			
  		<%} %>
		</table>
		<hr>
	<div style="padding-left: 20px;">
		<p>Totale ordine: <b><%=totOrdine %></b></p>
		<p>di cui con IVA: <%=totIvaOrdine %></p><br>
		<form action="OrderControl" method="post">
			<input type="hidden" value="completeOrder" name="action">
			<input type="hidden" value="1" name="step">
			<input type="submit" class="btn btn-sm btn-outline-secondary" value="Procedi all'acquisto">
		</form>
	</div>
	<%
		if(mangasError!=null && mangasError.size() != 0){
			
	%>
	<hr>
	<h3><b>
	Prodotti del carrello esauriti!
	</b></h3>
	<table class="table">
		 <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Foto</th>
		      
		      <th scope="col">Titolo</th>
		      <th scope="col">Prezzo</th>
		      <th scope="col">Quantità</th>
		      
		    </tr>
		  </thead>
		  <%
			
		  if (mangasError != null && mangasError.size() != 0) {
				Iterator<?> it = mangasError.iterator();
				int i=0;
				while (it.hasNext()) {
					i++;
					mangaBean manga = (mangaBean) it.next();
	
				
				
			%>
				<tr>
			      	<th scope="row"><%=i+1%></th>
				    <td><img src="<%=manga.getImg() %>" alt="<%=manga.getTitolo() %>" class="img-fluid" 
				    	 height="60" width="60"/>
				    </td>
					
					<td><%=manga.getTitolo() %></td>
					<td>&euro;<%=manga.getPrezzo() + (manga.getPrezzo()*manga.getIva()/100) %></td>
					<td id="clicks">Esaurito</td>
					<td>
						
					</td>
				</tr>
				
			
  		<%}
				}
		  } 
		} 
  		%>
		</table>
	
	
	

	
	<%@include file="footer.jsp"  %>

</body>
</html>