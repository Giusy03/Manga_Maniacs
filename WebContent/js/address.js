function addressCheck(form){
	var comune = $("#comune").val();
	var provincia = $("#provincia").val();
	var cap = $("#cap").val();
	var via = $("#via").val();
	var civico = $("#civico").val();
	var flag = true;

	var patternLettere = /^[a-zA-Z ]+$/;
	if (!comune.match(patternLettere) || comune.length < 1 || comune.length > 50) {
		flag=false;
		$("#comune").removeClass("is-valid");
		$("#comune").addClass("is-invalid");
	} else {
		$("#comune").removeClass("is-invalid");
		$("#comune").addClass("is-valid");
	}

	if(provincia == "provincia"){
		flag=false;
		$("#provincia").removeClass("is-valid");
		$("#provincia").addClass("is-invalid");
	} else {
		$("#provincia").removeClass("is-invalid");
		$("#provincia").addClass("is-valid");
	}

	var patternCAP = /^[0-9]{5}$/;
	if (!cap.match(patternCAP) ) {
		flag=false;
		$("#cap").removeClass("is-valid");
		$("#cap").addClass("is-invalid");
	} else {
		$("#cap").removeClass("is-invalid");
		$("#cap").addClass("is-valid");
	}

	if (!via.match(patternLettere) || via.length < 1 || via.length > 50) {
		flag=false;
		$("#via").removeClass("is-valid");
		$("#via").addClass("is-invalid");
	} else {
		$("#via").removeClass("is-invalid");
		$("#via").addClass("is-valid");
	}

	var numberpattern= /^[-+]?[0-9]+$/;
	if(!civico.match(numberpattern)){
		flag=false;
		$("#civico").removeClass("is-valid");
		$("#civico").addClass("is-invalid");
	} else {
		$("#civico").removeClass("is-invalid");
		$("#civico").addClass("is-valid");
	}
	
	if (flag) {
		form.submit();
	}

}

