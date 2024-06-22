$(document).ready(function() {
	
	
$(document).on("click",".btn-close",function(){
		
	
	var rigaGenere =$(this).parent().parent();
	
		var id=rigaGenere.children(".id").text();
		$.ajax({
	   		 url: "AddGen",
	   		 
	   		 type:"post",
	   		 dataType : 'text',
	   		  data: "action=remove"+"&id="+id,
	   		  success: function(data){

	   			
	   			  
	   				rigaGenere.remove();
	   			
	   			
	     			
	   			
	   			
	   		  },
	   		  error: function(textStatus,errorThrown){
	   			  console.log(textStatus,errorThrown);
	   		  }
			})
		
		
	})
	
 $('#searchGeneri').keyup(function(){
  
  console.log( $('#searchGeneri').val());
  
  var searchField = $('#searchGeneri').val();
  var expression = new RegExp(searchField, "i");
  
 
  $("[data-val='genereName']").each(function() {
	  console.log($(this).text());
	  console.log($(this).parent());
  if ($(this).text().search(expression) != -1 )
  {
	  $(this).parent().show();
  	  
  	
  }else
	  
	  $(this).parent().hide();
  

	  });
 });
	
});