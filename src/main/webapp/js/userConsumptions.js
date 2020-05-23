/**
 * 
 */

$(document)
		.ready(
				function() {
					$("#userconsumption")
							.click(
									function() {

										var selectcommunityName = $(
												"#selectcommunityName")
												.val();

										//alert($("#selectBlockBasedonCommunity").val());
										
										if ($("#selectcommunityName").val() == "-1") {
											
											bootbox
											.alert("Select Community Id");
											return false;
										}

										if ($("#selectBlockBasedonCommunity").val() == "null" || $("#selectBlockBasedonCommunity").val() == "Select Block") {

											bootbox
											.alert("Select Block Name");
											return false;
										}
										
										if ($("#selectHouseBasedonBlock").val() == "null" || $("#selectHouseBasedonBlock").val() == "Select CRN") {

											bootbox
											.alert("Select CRN Number");
											return false;
										}


										if ($("#start_date").val() == "null" || $("#start_date").val() == "") {

											bootbox
											.alert("Select Start Date");
											return false;
										}
										
										if ($("#end_date").val() == "null" || $("#end_date").val() == "") {

											bootbox
											.alert("Select End Date");
											return false;
										}
										
										
										var data1 = {}
										data1["communityID"] = $(
												"#selectcommunityName").val();
										data1["blockID"] = $(
												"#selectBlockBasedonCommunity")
												.val();
										data1["CRNNumber"] = $(
												"#selectHouseBasedonBlock")
												.val();
										data1["meterID"] = $("#AMR_topup")
												.val();
										data1["fromDate"] = $("#start_date")
												.val();
										data1["toDate"] = $("#end_date").val();

										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/userconsumptionreports",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(d) {
														
														//if (data.result == "Success") {
														
														$("#form").hide();
														$("#tablereport").show();

														 table = $('#userConsumptionsTable').DataTable(
																	{
																		"dom": "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
																		"responsive" : true,
																		/*"processing" : true,*/
																		"serverSide" : false,
																		"bDestroy" : true,
																		"bPaginate": true,
																		"pagging" : true,
																		"bProcessing" : true,
																		"ordering" : true,
																		"order" : [ 0, "desc" ],
																		"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
																		"pageLength" : 5,
																		"scrollY" : 324,
																		"scrollX" : false,
																		"data" : d.data,
																		"columns" : [ {
																			"data" : "CRNNumber"
																		}, {
																			"data" : "meterID"
																		}, {
																			"data" : "reading"
																		}, {
																			"data" : "balance"
																		}, {
																			"data" : "battery"
																		}, {
																			"data" : "tariff"
																		}, {
																			"data" : "alarmCredit"
																		}, {
																			"data" : "emergencyCredit"
																		}, {
																			"data" : "dateTime"
																		}],
																		"columnDefs" : [ {
																			targets : [ 0 ],
																			"className": "dt-center", "targets": "_all"
																		}], "buttons": [
																			   /* 'csvHtml5',
																			'excelHtml5',
																		'pdfHtml5'*/
																			
																			{extend: 'excel',
																		        footer: 'true',
																		        text: 'Excel',
																		        title:'User Consumption Report'  },
																		         
																		        {extend: 'pdf',
																		        footer: 'true',
																		        exportOptions: {
																		            columns: [0,1,2,3,4,5,6,7,8]
																		        },
																		        text: 'pdf',
																		        orientation: 'landscape',
																		        title:'User Consumption Report'  }
																		]
																	});
														 $("div.headname").html('<h3>User Consumptions</h3>');
															//table.ajax.reload()
														 
														 $("div.addevent").html('<button id="back" onClick="returnBack()"'
																 +'class="btn btn-raised btn-primary float-right"'
																	+'>'
																+'	<span>Back</span>'
															+'</button>');
													//	}
													}
												});
										return false;
									});
				});

$(document).ready(
		function() {
			$("#back")
			.click(
					function() {
		
						window.location = "userConsumptions.jsp";
						return false
						
					});
		});