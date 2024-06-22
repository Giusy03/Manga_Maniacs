
$(document).ready(function() {
	$(".card").mouseenter(function(){
		
		$(this).css("border-color","black").css('border', 'outset');
	});
	
	$(".card").mouseleave(function(){
		$(this).css("border-color","rgb(0 0 0 / 25%)").css('border', 'none');
		});
	$(".parent").click(function(){
		
		$(this).find(".formDettagli").submit();
		
		
		
		
		});
	$(".prodottoContainer ").mouseenter(function(){
		$( this ).css('z-index', 2);
		$( this ).children(".parent").children(".card-body").children(".detDescrizione").show()  ;
			  
	});
	
	
	$(".prodottoContainer ").mouseleave(function(){
		$( this ).children(".parent").children(".card-body").children(".detDescrizione").hide() 
		
		});
});