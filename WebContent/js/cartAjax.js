$(document).ready(function() {
	
$("input[value|='Aggiungi al carrello']").click(function(){
	
	var form=$(this).parent();
	var id=form.find("[name|='id'").val();
	
	
	
	 $.ajax({
		 url: "CartServlet",
		 
		 type:"post",
		 dataType : 'json',
		  data: "action=add&id="+id, 	
	  });
	 var t =$("#tagHeader");
	 if($("#tagHeader").length> 0){
		 var num =0;
		 num=$("#tagHeader").text();
		 num++;
		 $("#tagHeader").text(num);
		 
		 $(this).val("Aggiunto al carrello");
		 $(this).prop("disabled", true);
	 }else{
		 $("#linckShop").append("<span id='tagHeader' style='z-index: 5;position: relative;top: -49px!important;left: 71px!important;' class=' top-0 start-100 translate-middle badge rounded-pill bg-danger'>1</span>");
		 $(this).val("Aggiunto al carrello");
		 $(this).prop("disabled", true);
	 }
});



	
	
	
});
