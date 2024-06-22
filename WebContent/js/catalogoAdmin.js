$(document).ready(function() {
	
	
	
	
	$('#searchMangaCA').keyup(function(){
		
		  
		  console.log( $('#searchMangaCA').val());
		  
		  var searchField = $('#searchMangaCA').val();
		  var expression = new RegExp(searchField, "i");
		  
		  
		  $("[data-val='mangaTitolo']").each(function() {
		  
		  if ($(this).text().search(expression) != -1 )
		  {
			  $(this).parent().parent().parent().parent().parent().show();
		  	  
		  	
		  }else
			  
			  $(this).parent().parent().parent().parent().parent().hide();
		  

			  });
		  
	
	
	
	
	
});
});