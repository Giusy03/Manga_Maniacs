/**
 * 
 */


	$(document).ready(function() {


 
 $('#searchUtente').keyup(function(){
  $('#result').html('');
  
  console.log( $('#searchUtente').val());
  
  var searchField = $('#searchUtente').val();
  var expression = new RegExp(searchField, "i");
  
  
  $("[data-val='username']").each(function() {
  
  if ($(this).text().search(expression) != -1 )
  {
	  $(this).parent().show();
  	  
  	
  }else
	  
	  $(this).parent().hide();
  

	  });
  
  
  
 
 
 
 
 
 
  
 });
 
});