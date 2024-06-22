function cardcheck(form){
	
	var numeroCarta = $("#numeroCarta").val();
	var scadenza = $("#scadenza").val();
	var cvv = $("#cvv").val();
	var nomeTitolare = $("#nomeTitolare").val();
	var cognomeTitolare = $("#cognomeTitolare").val();
	
	var flag = true;
	
	var patternCarta = /^[0-9]{16}$/;
	if (!numeroCarta.match(patternCarta) ) {
		flag=false;
		$("#numeroCarta").removeClass("is-valid");
		$("#numeroCarta").addClass("is-invalid");
	} else {
		$("#numeroCarta").removeClass("is-invalid");
		$("#numeroCarta").addClass("is-valid");
	}
	
	var patternCvv = /^[0-9]{3}$/;
	if (!cvv.match(patternCvv) ) {
		flag=false;
		$("#cvv").removeClass("is-valid");
		$("#cvv").addClass("is-invalid");
	} else {
		$("#cvv").removeClass("is-invalid");
		$("#cvv").addClass("is-valid");
	}
	
	
	
	var annoInserito = scadenza.substring(0,4);
	var meseInserito = scadenza.substring(5);
	if(meseInserito.substring(0,1) == 0){
		meseInserito = meseInserito.substring(1, 2);
	}
	var date = new Date();
	var meseCorrente = date.getMonth() + 1;
	var annoCorrente = date.getFullYear();
	if ((annoInserito < annoCorrente) || (meseInserito < meseCorrente && annoInserito <= annoCorrente)) {
		flag=false;
		$("#scadenza").removeClass("is-valid");
		$("#scadenza").addClass("is-invalid");
	} else {
		$("#scadenza").removeClass("is-invalid");
		$("#scadenza").addClass("is-valid");
	}
	
	var patternLettere = /^[a-zA-Z ]+$/;
	if (!nomeTitolare.match(patternLettere) || nomeTitolare.length < 1 || nomeTitolare.length > 20) {
		flag=false;
		$("#nomeTitolare").removeClass("is-valid");
		$("#nomeTitolare").addClass("is-invalid");
	} else {
		$("#nomeTitolare").removeClass("is-invalid");
		$("#nomeTitolare").addClass("is-valid");
	}
	
	if (!cognomeTitolare.match(patternLettere) || cognomeTitolare.length < 1 || cognomeTitolare.length > 50) {
		flag=false;
		$("#cognomeTitolare").removeClass("is-valid");
		$("#cognomeTitolare").addClass("is-invalid");
	} else {
		$("#cognomeTitolare").removeClass("is-invalid");
		$("#cognomeTitolare").addClass("is-valid");
	}

	if (flag) {
		form.submit();
	}
}