


	$(document).ready(function() {


 
 $('#btnRicercaOrder').click(function(){
  
  
  
  
 var dataInizio = $('#dataInizio').val();
 var dataFine =  $('#dataFine').val();
 

  
  
  $("[data-value='data']").each(function() {
	  
	  var dataOrder = $(this).text();
  
  if (dataOrder>=dataInizio&&dataOrder<=dataFine)
  {
	  $(this).parent().show();
  	  
  	
  }else
	  
	  $(this).parent().hide();
  

	  });
  
  
  
 
 
 
 
 
 
  
 });
 
});