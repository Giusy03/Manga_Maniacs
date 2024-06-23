<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,Bean.mangaBean,Bean.genereBean,Bean.UserBean"%>
     <%
	mangaBean bean = (mangaBean) request.getAttribute("MangaDetail");
	if(bean == null) {
		response.sendRedirect("./manga");	
		
	}
	//mangaBean product = (mangaBean) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<meta charset="ISO-8859-1">
<title><%= bean.getTitolo() %></title>
 <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <style>
    .conteiner{
       padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
    }
    .page-title-wrapper {
    background-color: transparent;
    text-align: center;
    background-image: none;
    padding: 10px 0;
    border-bottom: #fa9c2f solid 12px;
}
.row{
  margin: 1%;
 
}
.price {
    font-size: 22px;
}
type{
     display: block;
    text-decoration: underline;
}
.product-info-main-attributes span {
    margin-right: 10px;
    font-weight: 700;
    display: block;
}
.product-info-main-attributes ul {
  list-style: none;
    margin: 0;
    padding-left: 0rem;
}
.product-info-main-attributes li
{
  padding-bottom: 10px;
}
.description{
  border-top: #fa9c2f solid 6px;
    padding-top: 30px;
}
.imgProdotto
{
max-width:500px;
}


  </style>
  
</head>
<body>

<%
	 UserBean user = (UserBean)request.getSession().getAttribute("user");
		String nome = "";
		if(user!=null){
		if (user.getUsername() != null) {

			nome = user.getUsername();
		}

		if (!(nome.equals(""))) 
			System.out.println("Utenteloggato " + nome);
		}
	%>
<%@include file="Header.jsp"  %>

<%
	Boolean admin =false;
	if(user!=null)
	 admin = user.isAmministratore();
	
	if(user == null){
		
		 
		 System.out.println("weee "+admin);
		%>
		<%@include file="navbar.jsp"  %>
	<%}else if( admin){%>
		<%@include file="AdminNavbar.jsp"  %>	
	<%}else
	{%>
	
		<%@include file="navbar.jsp"  %>
	<% } %>
	
	
 	
	
	
	

<div class="page-title-wrapper">
    <div class="container">
	    <h1 class="page-header">
	   	 <span ><%=bean.getTitolo() %></span>
	    </h1>
    </div>
 </div>
<div class="container">
      <div class="row">
      <div class="col-sm-6 col-xs-12">
        <div class="imgProdotto">
          <img src="<%= bean.getImg() %>" class="img-fluid" >

        </div>

      </div>
       <div class="col-sm-6 col-xs-12">
        <div class="product-detail-infomation-content">
          <div class="row">
            <div class="product-info-price">
              <span class="price">&euro;<%=bean.getPrezzo() + (bean.getPrezzo()*bean.getIva()/100) %></span>
              <span class="info">Iva Inclusa</span>
            </div>
          </div>


        </div>
        <div class="product-info-main-attributes">
          <div class="row">
            <div class="primary col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <ul>
            <li><span class="type">Editore:</span><%=bean.getEditore() %></li>
            <li><span class="type">Autore:</span><%=bean.getAutore() %></li>
            <li><span class="type">Genere:</span>
            
            <%
				Collection<?> generi = (Collection<?>) bean.getGeneri();
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

           </li>
            </ul>
            </div>
            <div class="secondary col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <ul>
                <li><span class="type">Lingua</span><%=bean.getLingua() %></li>
                <li><span class="type">Uscita:</span><%=bean.getDataProdotto() %></li>
                
              </ul>
              
            </div>
              <div class="col-xs-10">
              <form action="CartServlet">
              	<input type="hidden" name="action" value="add"  >
              	<input type="hidden" name="id" value="<%=bean.getId() %>"  >
              	<input type="hidden" name="redirect" value="manga"  >
              	<%if(bean.getQuantita()>1){ 
              	%>
              	<%
              	if(cart!=null && cart.prodottoExist(bean.getId())){  %>
	            	 	  <input type="button"  class="btn btn-outline-warning" disabled value="Aggiunto al carrello">
	              <%}else{%>
              	
              	
			              <button type="submit" title="Aggiungi al Carrello" class="btn btn-outline-warning" id="product-addtocart-button">
			             	 <span>Aggiungi al Carrello</span>
			              </button>
	               <%}%>
	               <%}else{%>
	               			<h5>Prodotto Esaurito </h5>
	                <%}%>
              </form>
              </div>


            </div>


        </div>
        <div class="description">
          <p><strong>Descrizione: <%=bean.getDescrizione() %> </strong></p>
          </div>
      </div>

      
    </div>
    </div>

<div class="modal fade" id="modal"  data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="staticBackdropLabel">Avviso</h5>
      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
   
  		
     <h3>Prodotto aggiunto nel carrello </h3>
 		
  
  


    </div>
   
  </div>
</div>
</div>




<script src="js/Dettagli.js"></script>

<%@include file="footer.jsp" %>


</body>
</html>