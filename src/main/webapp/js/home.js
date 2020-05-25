/**
 * 
 */
$(document)
		.ready(
				function() {

  $.getJSON("/PAYGTL_LORA_BLE/homedashboard/" +sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
			//var Options = "";
    	  document.querySelector("#adminActive").innerText = data.active;
    	  document.querySelector("#adminInActive").innerText = data.inActive;
    	  document.querySelector("#adminNonLive").innerText = data.nonLive;
    	  document.querySelector("#adminLive").innerText = data.live;
    	  document.querySelector("#adminBattery").innerText = data.lowBattery;
    	  document.querySelector("#adminEmergency").innerText = data.emergency;
    	  document.querySelector("#admincomption").innerText = data.consumption;
    	  document.querySelector("#admindayTopup").innerText = data.topup;
    	  document.querySelector("#adminAMR").innerText = data.amr;
			
		});
 });