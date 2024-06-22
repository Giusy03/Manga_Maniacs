$(document).ready(function() {

 $.ajaxSetup({ cache: false });
 
 $('#search').keyup(function(){
  $('#result').html('');
  
  console.log( $('#search').val());
  setTimeout(1000);
  var searchField = $('#search').val();
  
  
  if(searchField!=""){
	  $.ajax({
 		 url: "AS",
 		 
 		 type:"post",
 		 dataType : 'json',
 		  data: "titolo="+searchField,
 		  success: function(data){
 			  
 				  $('#resultManga').html('');
 				  $.each(data, function(key, value){
 					  
 				    	
 				    	
 				    	var result =' <div class="list-group-item list-group-item-action">  ';
 				    	result += '<a href="deteilControl?id='+ value.id+'"><img class="thumb" height=100 width=100 src="'+value.img+'" href="deteilControl?id='+ value.id+'"></a>';
 				    	result+= '<div class="info">';
 				    	result+='<p> Titolo: ';
 				    	result+= '<a href="deteilControl?id='+ value.id+'">'+value.Titolo+' </a>';
 				    	result+='</p> ';
 				    	result+='<p> Generi: ';
 				    	
 				         
 				       
 				         for (let x in value.generi ) {
 				        	 console.log(value.generi[x].nome);
 				        	 result+='<a href="manga?genere='+value.generi[x].id+'">'+value.generi[x].nome+'</a> ';
 				         }
 				         result+='</p> ';
 				         result+= '</div>'+'</div>';
 				         
 				        
 				         
 				    
 				         $('#resultManga').append( result);
 				    
 				   });
 			
 		  },
 		  error: function(textStatus,errorThrown){
 			  console.log(textStatus,errorThrown);
 		  }
 		  
 		
 		 
 		 	
	  });
	  

	  
  }
  
 });
 $('#search').blur(function(){
	 
	 if($('#search').val() ==""){
		 $('#resultManga').empty();
	 }
 });
 

 
 
});