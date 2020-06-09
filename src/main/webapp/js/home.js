/**
 * 
 */
$(document)
		.ready(
				function() {
					
					$("#highchart_container2").hide();

  if(sessionStorage.getItem("roleID") != 3){
	  
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
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/PAYGTL_LORA_BLE/homedashboard/"
					+ sessionStorage.getItem("roleID") + "/"
					+ sessionStorage.getItem("ID"),
			dataType : "JSON",

			success : function(d) {

				$('#highchart_container').highcharts(
						{
							chart : {
								type : 'bar',
									backgroundColor: 'transparent'
							},
							title : {
								text : ''
							},
							/*subtitle : {
								text : 'Status'
							},*/
							xAxis : {
								categories : [ 'Active',
										'In-Active', 'Live',
										'Non-Live', 'Low Battery',
										'Emergency Credit' ],
										labels: {
							                style: {
							                    fontWeight: 'bold',
							                    color: 'black'
							                }
							            },

								title : {
									text : null
								},
							},
							yAxis : {
								min : 0,
								title : {
									text : 'Chart',
									align : 'high'
								},
								labels : {
									overflow : 'justify'
								},
								min : 0,
								max : 100

							},
							tooltip : {
								valueSuffix : ''
							},
							plotOptions : {
								bar : {
									dataLabels : {
										enabled : true
									}
								}
							},
							/*
							legend: {
							    layout: 'vertical',
							    align: 'right',
							    verticalAlign: 'top',
							    x: -40,
							    y: 100,
							    floating: true,
							    borderWidth: 1,
							    backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
							    shadow: true
							},
							 */
							credits : {
								enabled : false
							},
							series : [ {
								data : [ d.activePercentage,
										d.inActivePercentage,
										d.livePercentage,
										d.nonLivePercentage,
										d.lowBatteryPercentage, 
										d.emergencyPercentage
										],
										labels: {
							                style: {
							                    fontWeight: 'bold',
							                    color: 'black'
							                }
							            },
								name : '(%)'
							} ]

						});
			}
		});
		}
		if(sessionStorage.getItem("roleID") == 3){
			
			
			$(document).on('click', '#view', function () {
				
				var month = $("#month").val();
				var year = $("#start_date").val();
				
				$("#highchart_container1").hide();
				$("#highchart_container2").show();
				
				
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "/PAYGTL_LORA_BLE/graph/"
							+ $("#start_date").val() + "/"
							+ $("#month").val()+"/"+sessionStorage.getItem("ID"),
					dataType : "JSON",

					success : function(d) {

						$('#highchart_container2').highcharts(
								{
									chart : {
										type : 'line',
											backgroundColor: 'transparent'
									},
									title : {
										text : 'Consumption Graph'
									},
									subtitle : {
										text : sessionStorage.getItem("ID")
									},
									xAxis : {
										categories : d.xAxis,

										title : {
											text : null
										},
									},
									yAxis : {
										min : 0,
										title : {
											text : 'Chart',
											align : 'high'
										},
										labels : {
											overflow : 'justify'
										}
										

									},
									tooltip : {
										valueSuffix : ''
									},
									plotOptions : {
										bar : {
											dataLabels : {
												enabled : true
											}
										}
									},
									
									legend: {
									    layout: 'vertical',
									    align: 'right',
									    verticalAlign: 'top',
									    x: -40,
									    y: 100,
									    floating: true,
									    borderWidth: 1,
									    backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
									    shadow: true
									},
									 
									credits : {
										//enabled : false
									},
									series : [ {
										data : d.yAxis ,
										name : ''
									} ]

								});
					}
				});
				
				
			});
			
			 $.getJSON("/PAYGTL_LORA_BLE/dashboard/" +sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
				 $.each(data.data, function(i, item) {
					 alert();
		    	  document.querySelector("#lastBillAmount").innerText = item.lastTopupAmount;
		    	  document.querySelector("#lastBillDate").innerText = item.lastRechargeDate;
		    	  document.querySelector("#community").innerText = item.communityName;
		    	  document.querySelector("#block").innerText = item.blockName;
		    	  document.querySelector("#CRN_Number").innerText = item.CRNNumber;
		    	  document.querySelector("#balance").innerText = item.balance;
		    	  document.querySelector("#valveStatus").innerText = item.valveStatus;
		    	  document.querySelector("#meterStatus").innerText = item.tamperStatus;
		    	  document.querySelector("#batteryStatus").innerText = item.battery;
				
				 });
				 if(data.data.length == 0){
					 document.querySelector(".balance").innerText = "---";
			    	 document.querySelector(".valveStatus").innerText = "---";
			    	 document.querySelector("#lastBillAmount").innerText = "---";
			    	  document.querySelector("#lastBillDate").innerText = "---";
				 }
				 
				});
			 
			 
			 $.getJSON("/PAYGTL_LORA_BLE/customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
					$.each(data.data, function(i, item) {
						
							document.querySelector(".community").innerText = item.communityName;
				    	  document.querySelector(".block").innerText = item.blockName;
				    	  document.querySelector(".CRN_Number").innerText = item.CRNNumber;
				    	  
						
					});
				});
			 
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/PAYGTL_LORA_BLE/graph/"
					+ 0 + "/"
					+ 0+"/"+sessionStorage.getItem("ID"),
			dataType : "JSON",

			success : function(d) {

				$('#highchart_container1').highcharts(
						{
							chart : {
								type : 'line'
							},
							title : {
								text : 'Consumption Graph'
							},
							subtitle : {
								text : sessionStorage.getItem("ID")
							},
							xAxis : {
								categories : d.xAxis,

								title : {
									text : null
								},
							},
							yAxis : {
								min : 0,
								title : {
									text : 'Chart',
									align : 'high'
								},
								labels : {
									overflow : 'justify'
								},
								min : 0,
								max : 100

							},
							tooltip : {
								valueSuffix : ''
							},
							plotOptions : {
								bar : {
									dataLabels : {
										enabled : true
									}
								}
							},
							
							legend: {
							    layout: 'vertical',
							    align: 'right',
							    verticalAlign: 'top',
							    x: -40,
							    y: 100,
							    floating: true,
							    borderWidth: 1,
							    backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
							    shadow: true
							},
							 
							credits : {
								//enabled : false
							},
							series : [ {
								data : d.yAxis ,
								name : ''
							} ]

						});
			}
		});
		}
		
 });