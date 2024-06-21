

	$(document).ready(function() {

 $.ajaxSetup({ cache: false });
 
 $('#search').keyup(function(){
  $('#result').html('');
  
  console.log( $('#search').val());
  
  var searchField = $('#search').val();
  var expression = new RegExp(searchField, "i");
  $.getJSON('AjaxSearch', function(data) {
  $('#result').html('');
  
   $.each(data, function(key, value){
    if (value.Titolo.search(expression) != -1 )
    {
     $('#result').append( ' <div class="item"> <a href="deteilControl?id='+ value.id+'"><img class="thumb" height=100 width=100 src="'+value.img+'" href="deteilControl?id='+ value.id+'"></a> <div><a href="deteilControl?id='+ value.id+'"> '+value.Titolo+'</a>   </div> </div>');
    
    }
   });   
   
   
  });
 });
 
 $('#search').blur(function(){
	 $('#result').html('');
 });
 
 
});