




$(document).ready(function() {

$(".nav-link").click(function(){

    $(".nav-link").removeClass("active");
    $(this).addClass("active");
});

$("#btnIMA").click(function(){
    
    $(".page").removeClass("visibile");
    $("#conIMA").addClass("visibile");

});
$("#btnI").click(function(){
   
    $(".page").removeClass("visibile");
    $("#conI").addClass("visibile");

});
$("#btnIMO").click(function(){
 
    $(".page").removeClass("visibile");
    $("#conIMO").addClass("visibile");

});

$("#btnP").click(function(){
	 
    $(".page").removeClass("visibile");
    $("#conP").addClass("visibile");
    
});
$("#btnAddr").click(function(){
	 
    $(".page").removeClass("visibile");
    $("#conAddres").addClass("visibile");


});




});
