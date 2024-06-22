
$(document).ready(function() {

	
	
	

	$("#btnAddManga").click(function(){
		var Titolo  =$("#Titolo").val();

		
		var editore  =$("#editore").val();	
		var autore =$("#autore").val();
		var  description=$("#description").val();
		var  prezzo =$("#prezzo").val();
		var  IVA=$("#IVA").val();
		var  QuantitaDep=$("#QuantitaDep").val();
		var  data=$("#data").val();
		var img =$("#img").val();
		 var messaggio="";
		var risultato="-";
		var error=0;
		
		if(Titolo==""){
			error++;
			$("#Titolo").css("border-color","red");
			 risultato+="Titolo-";
		}else{
			$("#Titolo").css("border-color","#ced4da");
			
		}
	
		if(editore==""){
			error++;
			$("#editore").css("border-color","red");
			risultato+="editore-";
		}else{
			$("#editore").css("border-color","#ced4da");
			
		}
		if(autore==""){
			error++;
			$("#autore").css("border-color","red");
			risultato+="autore-";
		}else{
			$("#autore").css("border-color","#ced4da");
			
		}
		if(description==""){
			error++;
			$("#description").css("border-color","red");
			risultato+="description-";
		}else{
			$("#description").css("border-color","#ced4da");
			
		}
		if(QuantitaDep==""){
			error++;
			$("#QuantitaDep").css("border-color","red");
			risultato+="QuantitaDeposito-";
		}else{
			$("#QuantitaDep").css("border-color","#ced4da");
			
		}
		if(data==""){
			error++;
			$("#data").css("border-color","red");
			risultato+="Data-";
		}else{
			$("#data").css("border-color","#ced4da");
			
		}
		if(prezzo==""){
			error++;
			$("#prezzo").css("border-color","red");
			risultato+="Prezzo-";
		}else{
			$("#prezzo").css("border-color","#ced4da");
			
		}
		if(IVA==""){
			error++;
			$("#IVA").css("border-color","red");
			risultato+="IVA-";
		}else{
			$("#IVA").css("border-color","#ced4da");
			
		}
		if(img==""){
			error++;
			$("#img").css("border-color","red");
			risultato+="fileImg-";
		}else{
			$("#img").css("border-color","#ced4da");
			
		}
		if(error==0)
			{
			$("#formNewManga").submit();
			}else{
				$("#ErRisultato").empty(risultato);
				$("#ErRisultato").append(risultato);
				$("#exampleModal").modal('show');	
			}
		
		
		
		
	})
	

});
