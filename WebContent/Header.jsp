 
			
		

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/HeaderStyle.css">
</head>
<body>

	<div class="header">
		<nav>
			<h2 class="logo">Unlimited Manga</h2>
			<div class="conteiner_search">
				<form>
					<input class="search" id="search" type="search">
				</form>
				<div class="sugerimento">
					<div class="list-group" id="result" style="display: block;"></div>
				</div>
				</div>
				<ul class="header-link">
				<%
				if(request.getSession().getAttribute("user")!=null){
					%>
					<li><a href="profilo?action=profiloView">Profilo</a></li>
					<%
				}else{
				%>
				
				
					<li><a href="Login.jsp">Login Here</a></li>
			 <%
				}
				%>
					<li><a href="Cart.jsp">Shopping Cart</a></li>
				</ul>
		</nav>
	</div>
	
	<script type="text/javascript" src="Javascript\Ricerca.js"></script>
</body>
</html>