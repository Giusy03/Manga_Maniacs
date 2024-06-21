/*REGULAR EXPRESSIONS FORM*/



$(document).ready(function(){
$("#username_up").blur(function(){
	
	if(!checkUsername($("#username_up").val())){
		
		$("#username_up").addClass("error");
		$("#erroreUsername").show(600);
		 
	} 
	else{
		$("#username_up").removeClass("error");
		$("#erroreUsername").hide(600);
		
	}

})

$("#password_up").blur(function(){
	if(!checkPassword($("#password_up").val())){
		
		
		$("#password_up").addClass("error");
		$("#errorePsw").show(600);
		
		
		
	}
	else{
		$("#password_up").removeClass("error");
		$("#errorePsw").hide(600);
	
	}


})


$("#username_up_conferm").blur(function(){
	

	if((!($("#password_up").val()==$("#username_up_conferm").val())))
		{
		 $("#username_up_conferm").addClass("error");
		 $("#errorPswCon").show(600);
	
		
		}else
			{
			 $("#username_up_conferm").removeClass("error");
			 $("#errorPswCon").hide(600);
		
			
			}
})

$("#email_up").blur(function(){

	if(!checkEmail($("#email_up").val())){
		
		$("#email_up").addClass("error");
		$("#erroreEmail").show(600);
		
		
		
	}
	else{
	
		$("#email_up").removeClass("error");
		$("#erroreEmail").hide(600);
	
	}
})



});







function checkEmail(inputtxt){
	
	var email = /^\w([\.-]?\w)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	
	if(inputtxt.match(email)){
		return true;
	}
	return false;
}

function checkUsername(inputtxt){
	
	var username = /^[a-zA-Z0-9]+$/;
	

	if(inputtxt.match(username)){
		return true;
	}
	return false;
}

function checkPassword(inputtxt){
	
	var error="Manca ";
	var reg=/[A-Z]/g;
	
	if(!inputtxt.match(reg)){
		
		var error = error + "-Lettera Maiuscola-";
	}

	reg=/[a-z]+/g;

	if(!inputtxt.match(reg)){
		
		var error = error +"-Lettera Minuscola-";
	}

	reg=/[0-9]/g;
if(!inputtxt.match(reg)){
	
	var error = error +"-Numero 0a9-";;
}

if(error=="Manca "){
	$("#errorePsw").hide(600);
	return true;
}else{
	
	$("#errorePsw").text(error);
	return false;
}
}


function validate(obj) {	
	

	var valid=[false,false,false,false];
	
	
	if(!checkUsername($("#username_up").val())){
		
		$("#username_up").addClass("error");
		$("#erroreUsername").show(600);
		valid[1] = false;
	} 
	else{
		$("#username_up").removeClass("error");
		$("#erroreUsername").hide(600);
		valid[1] = true;
	}




	if(!checkPassword($("#password_up").val())){
		
		
		$("#password_up").addClass("error");
		$("#errorePsw").show(600);
	
		valid[2] = false;
		
	}
	else{
		$("#password_up").removeClass("error");
		$("#errorePsw").hide(600);
		valid[2] = true;
	}






	

	if((!($("#password_up").val()==$("#username_up_conferm").val())))
		{
		 $("#username_up_conferm").addClass("error");
		 $("#errorPswCon").show(600);
		 valid[3] = false;
		
		}else
			{
			 $("#username_up_conferm").removeClass("error");
			 $("#errorPswCon").hide(600);
		
			 valid[3] = true;
			}




	if(!checkEmail($("#email_up").val())){
		
		$("#email_up").addClass("error");
		$("#erroreEmail").show(600);
		valid[4] = false;
		
		
	}
	else{
		
		$("#email_up").removeClass("error");
		$("#erroreEmail").hide(600);
		valid[4] = true;
	}




	
	if(valid[1]&&valid[2]&&valid[3]&&valid[4])
		obj.submit();
	
	 


}