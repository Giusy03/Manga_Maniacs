$(document).ready(function() {
	
$(".input-form").change(function(){
	if($('input:checked').length > 1 && $("#cliente").length )
		{
			$("#btnConferm").prop("disabled", false);
		}else
			{
			$("#btnConferm").prop("disabled",true);
			}
	
})

	
});

