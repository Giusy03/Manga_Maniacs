<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<meta charset="ISO-8859-1">
<title>Acquisto effettuato</title>
<style>
body { 
    padding-top: 30px; 
   
}
</style>
</head>
<body>
<%@include file="Header.jsp"  %>
<%@include file="navbar.jsp"  %>
<section class="vh-100" >
		<div class="container py-5 h-100">
    		<div class="row d-flex justify-content-center align-items-center h-100">
      			<div class="col col-xl-10">
			        <div class="card" style="border-radius: 1rem;">
			          <div class="row g-0">
			            <div class="col-md-6 col-lg-5 d-none d-md-block">
			              
			            </div>
            			<div class="col-md-6 col-lg-7 d-flex align-items-center">    
             				<div class="card-body p-4 p-lg-5 text-black">
             					<h1>Acquisto effettuato con successo!</h1>
             					<h3>Grazie per aver scelto MangaManiacs!</h3>
              					<form action="manga" method="post">
									<input type="submit" class="btn btn-dark btn-lg btn-block" value="Torna alla homepage">
								</form>
							</div>
            			</div>
          			  </div>
        			</div>
      			</div>
    		</div>
  		</div>
	</section>
<%@include file="footer.jsp"  %>
</body>
</html>