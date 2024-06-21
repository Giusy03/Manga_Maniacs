/**
 * 
 */


	$(document).ready(function() {

 $.ajaxSetup({ cache: false });
 
 $('#search').keyup(function(){
  $('#result').html('');
  
  console.log( $('#search').val());
  
  var searchField = $('#search').val();
  var expression = new RegExp(searchField, "i");
  $.getJSON('AjaxSearchUtenti', function(data) {
  $('#result').html('');
  
  var table = '<table>';
	  table +='<tr>';		
 		table+= '<th>USERNAME</th>'
		  table +='	<th>psw</th>';
			  table +='	<th>email</th>';
				  table +='	<th>nome</th>';
					  table +='	<th>cognome</th>';
						  table +='	<th>amministratore</th>';
							  table +='</tr>';
  
   $.each(data, function(key, value){
	   table += 	'<tr>'
	   console.log(value.username)
    if (value.username.search(expression) != -1 )
    {
     table += '<td>'+ value.username+' </td> ';
     table += '<td>'+ value.psw+' </td> ';
   table += '<td>'+ value.email+' </td> ';
  table += '<td>'+ value.nome+' </td> ';
 table +=  '<td>'+ value.cognome+' </td> ';
	table += '<td>'+ value.ruolo+' </td> ';
    }
	   table += 	'</tr>';
	   
   });
   table +='</table>';  
   $('#result').append( table);
   
   
  });
 });
 
 $('#search').blur(function(){
	 
	 if($('#search').val() ==""){
		 $('#result').html('');
	 }
	 
 });
 
 
 
});