/**
 * 
 */

$(document).ready(function () {
    	var flag =true;
      $('.button-left').click(function () {
        $('.left ').toggleClass('fliph');
		flag = !flag;
		if(flag == true){
			document.querySelector('.left_nav').className="left_nav col-md-2 pl-0 pr-0";
			document.querySelector('.right_data').className="right_data col-md-10 mt-4 mb-4";
		}else if(flag == false){
			document.querySelector('.left_nav').className="left_nav col-md-1 pl-0 pr-0";	
			document.querySelector('.right_data').className="right_data col-md-10 mt-4 mb-4";
			var oTable = $("#liveTable").dataTable();
			
		}
      });
      
      
      
      var pageURL = $(location). attr("href");
		//alert(pageURL.split('LORA_BLE/')[1]);
		let  url = pageURL.split('LORA_BLE/')[1].split("?")[0] =="LoginAction.jsp"?"home.jsp":pageURL.split('LORA_BLE/')[1];
		document.querySelector("a[href='"+url+"']").className = "active";
    });

$(window).on('load', function() { 
  $('#status').fadeOut(); 
  $('#preloader').delay(0).fadeOut('slow'); 
  $('body').delay(0).css({'overflow':'visible'});
})

function returnBack(){
	window.location.reload();
}

function redirection(obj){
	sessionStorage.setItem("filterId",obj);
	window.location = "LiveDashBoard.jsp";
}

 /*$(document).ready(function () {
  //if($(window).width()<768){
		alert("check");
       $('.left').addClass('moblieclass');
	   $('.button-left').click(function () {
		   var w = $(window).width();
		   alert("#"+w);
		   if($(window).width()<768){
			   alert("#");
	    $('.left ').removeClass('fliph');
        $('.left').addClass('moblieclassActive');
		}else{
			alert("i");
		}
	   
    }); 
   
});*/
