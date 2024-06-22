$(document).ready(function() {
	
	
	$(document).on("click",".form-check-input",	function(){
		console.log()
		var check =$(this);
		var btn =check.parentsUntil( ".menuOp" ).children(".btnFiltro");
		var num=parseInt(btn.attr( "selectop" ));
		var nomeBtn=btn.attr( "nameBtn" );
		var fleg=check.attr( "check" );
		console.log(fleg=="0");
		if(fleg=="0"){
			check.attr( "check",1 );
		}else{
			check.attr( "check",0 );
		}
		console.log(check.attr( "check" ));
		if(fleg=="0"){
				 num++;
		}else{
					num--;
		}
		btn.attr( "selectop",num);
		if(num>0){
			
			btn.text(nomeBtn+":"+num);
		}
		else
			{
				
				btn.text(nomeBtn);
			}
	});
	



	
	
	$(document).on("click",".form-radio-input",function(){
	
		var check =$(this);
		var btn =check.parentsUntil( ".menuOp" ).children(".btn");
		var nomeBtn=btn.attr( "nameBtn" );
		var nomeCheck=check.val();
		
				
				btn.text(nomeBtn+nomeCheck);
			
	});

	$("#btnNavbarFiltro").click(function(){
		$(".filtro").toggle();
	})
	
	$("#btnNavbarFiltro").one("click",function(){
		var collectionBtn =$(".btnFiltro");
		
		collectionBtn.each(function() {
		var btn=$(this);
		console.log(btn);
		var nomeBtn=btn.attr( "nameBtn" );
		
		
	
		 
		  var result =" <div class=' dropdown-menu modal-sm menudropOp'style='min-width:11rem'>  <div class='conteiner'>	" +
		  	"<div class='raw'>" +
		  	"	<input type='search' class='form-control optionSearch' autocomplete='off' placeholder='Cerca...' role='combobox' > " +
		  	"	</div>";
		result+=" <div class='raw overflow-auto opzioni' style='max-height: 124.922px;overflow-y: auto;min-height: 11px;'>"
		 
		
		 $.ajax({
    		 url: "AF",
    		 
    		 type:"post",
    		 dataType : 'json',
    		  data: "action="+nomeBtn,
    		  success: function(data){
    			  $.each(data, function(key, value){
    				  result+="<div class='col' >"+
			   			"<input class='form-check-input' check='0'name='"+nomeBtn+"' type='checkbox' value='"+value.Valore+"' id='id"+value.Valore+"'>"+
						 " <label class='form-check-label' for='id"+value.Valore+"'> "+value.Valore+" </label>"+
			    	 "</div>";
    			  });
    			  result+="	</div> </div> </div>";
    			  
    			 btn.parent().append( result);
      			
      			
    			
    			
    		  },
    		  error: function(textStatus,errorThrown){
    			  console.log(textStatus,errorThrown);
    		  }
    		  
    		
    		 
    		 	
   	  });
	
	
		
			 
		});
		
	})
	$(document).on("keyup",".optionSearch",function(){
		 var search=$(this);
		var opzioni =search.parentsUntil( ".container" ).children(".opzioni").children();
		 console.log( opzioni);
		 
		 
		  console.log( search.val());
		  
		  var searchField =search.val();
		  var expression = new RegExp(searchField, "i");
		  
		  
		  opzioni.each(function() {
		  
			  
			  var check =$(this).children(".form-check-input");
			  
		  if (check.val().search(expression) != -1 )
		  {
			  $(this).show();
		  	  
		  	
		  }else
			  
			  $(this).hide();
		  

			  });
		  
		  
		  
		 
		 

		 
		  
		 });
		
		
	

	
	
	
	






});