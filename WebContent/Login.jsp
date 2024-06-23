<head>
<link rel="icon" href="./img/favicon.png" type="image/png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="style/login.css">
</head>
<body>
	<a href="manga" style="text-decoration:none;" > <img src="./img/Logo.png" alt="MangaManiacs" class="logo" style="width: 100px; height: auto;"></a>
 	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Accedi</label>
			<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Registrati</label>
			<div class="login-form">
				<div class="sign-in-htm">
					<form action="login" method="post">
						<div class="group">
							<label for="username_in" class="label"><b>username</b></label>
						<input id="username_in" type ="text" name="username" placeholder = "inserisci qui l'username" class="input"> <br>
						</div>
						<div class="group">
							<label for="password_in" class="label"><b>password</b></label>
							<input class="input" id="password_in" type ="password" name="password" placeholder = "inserisci qui la password"> <br>
						</div>
						<div class="group">
							<input id="check" type="checkbox" class="check" checked>
							<label for="check"><span class="icon"></span> Conserva L'accesso</label>
						</div>
						<div class="group">
							<input type="submit" class="button" value="Accedi">
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<a href="#forgot">Password Dimenticata?</a>
						</div>
					</form>
				</div>
			<div class="sign-up-htm">
				<form action="registration" method="post" onsubmit="event.preventDefault(); validate(this)" >
							<div class="group">
								<label for="username_up" class="label"><b>username</b></label>
						<input id="username_up" type ="text" name="username" placeholder = "inserisci qui l'username" class="input"> <br>
						<div class="inv_feedback" id="erroreUsername">Nome utente non valido</div>
						
						</div>
						<div class="group">
							<label for="password_up" class="label"><b>password</b></label>
							<input class="input" id="password_up" type ="password" name="password" placeholder = "inserisci qui la password"> <br>
							<div class="inv_feedback" id="errorePsw">Password errata</div>
						</div>
							<div class="group">
								<label for="username_up_conferm" class="label">Ripeti Password</label>
								<input id="username_up_conferm" type="password" class="input" data-type="password" placeholder = "ripeti la password">
								<div class="inv_feedback" id="errorPswCon">La password non combacia </div>
							</div>
							<div class="group">
								<label for="email_up" class="label">Indirizzo Email</label>
								<input id="email_up" type="text" name="email" class="input" placeholder = "mangamaniacs@mangamaniacs.it">
								<div class="inv_feedback" id="erroreEmail">Email non valida</div>
							</div>
							<div class="group">
								<input id="button_submit" type="submit" class="button" value="Registrati">
							</div>
							<div class="hr"></div>
							<div class="foot-lnk">
								<label for="tab-1">Già membro?</a>
							
							</div>
					</form>
						</div>
					</div>
		
		
	</div>
</div>
<script type="text/javascript" src="Javascript\RegisterValidation.js"></script>
</body>