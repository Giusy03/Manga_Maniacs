

$(document).ready(function() {

	
	$("#deposito").change(function(){
		$(".conteinerQuantity").hide();
	$("#QuantitaBy"+$("#deposito").val()).show();
	})
	
	
	$("#btnModifica").click(function(){
		var Titolo  =$("#Titolo").val();

		
		var editore  =$("#Editore").val();	
		var autore =$("#Autore").val();
		var  description=$("#description").val();
		var  prezzo =$("#prezzo").val();
		var  IVA=$("#IVA").val();
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
			$("#Editore").css("border-color","red");
			risultato+="editore-";
		}else{
			$("#Editore").css("border-color","#ced4da");
			
		}
		if(autore==""){
			error++;
			$("#Autore").css("border-color","red");
			risultato+="autore-";
		}else{
			$("#Autore").css("border-color","#ced4da");
			
		}
		if(description==""){
			error++;
			$("#description").css("border-color","red");
			risultato+="description-";
		}else{
			$("#description").css("border-color","#ced4da");
			
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
		
		if(error==0)
			{
			$("#formModific").submit();
			}else{
				$("#ErRisultato").empty(risultato);
				$("#ErRisultato").append(risultato);
				$("#exampleModal").modal('show');	
			}





	});

});