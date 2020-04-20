/**
 * 
 */

$(document)
		.ready(
				function() {
					$("#topupSummary")
							.click(
									function() {

										var selectcommunityName = $(
												"#selectcommunityName")
												.val();

										alert($("#selectBlockBasedonCommunity").val());
										
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
										
										if ($("#selectHouseBasedonBlock").val() == "null" || $("#selectHouseBasedonBlock").val() == "Select House") {

											bootbox
											.alert("Select House Name");
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
										data1["customerID"] = $(
												"#selectHouseBasedonBlock")
												.val();
										data1["meterID"] = $("#AMR_topup")
												.val();
										data1["fromDate"] = $("#start_date")
												.val();
										data1["toDate"] = $("#end_date").val();

										alert("===>" + JSON.stringify(data1));
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/topupsummary",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(d) {
														alert("data"
																+ JSON
																		.stringify(d));
														//if (data.result == "Success") {
														
														$("#form").hide();
														$("#tablereport").show();

														 table = $('#topupsummaryTable').DataTable(
																	{
																		"dom": "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f<br/>i>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-12'p<br/>i>>",
																		"processing" : false,
																		"serverSide" : false,
																		"bDestroy" : true,
																		"pagging" : true,
																		"bPaginate": true,
																		"bProcessing" : false,
																		"ordering" : true,
																		"order" : [ 0, "desc" ],
																		"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
																		"pageLength" : 5,
																		"scrollY" : 324,
																		"scrollX" : true,
																		"data" : d.data,
																		"columns" : [ {
																			
																			"data" : "firstName"
																		},{
																			
																			"data" : "lastName"
																		},{
																			
																			"data" : "houseNumber"
																		}, {
																			"data" : "meterID"
																		}, {
																			"data" : "rechargeAmount"
																		}, {
																			"data" : "modeOfPayment"
																		}, {
																			"data" : "status"
																		}, {
																			"data" : "transactedByUserName"
																		}, {
																			"data" : "transactedByRoleDescription"
																		}, {
																			"data" : "dateTime"
																		}], "buttons": [
																			   /* 'csvHtml5',
																			'excelHtml5',
																		'pdfHtml5'*/
																			
																			{extend: 'excel',
																		        footer: 'true',
																		        text: 'Excel',
																		        title:'Top Up Summary'  },
																		         
																		        {extend: 'pdf',
																		        footer: 'true',
																		        exportOptions: {
																		            columns: [1,2,3,4,5,6,7,8,9]
																		        },
																		        text: 'pdf',
																		        orientation: 'landscape',
																		        title:'Top Up Summary'  }
																		]
																	});
															//table.ajax.reload()
													//	}
													}
												});
										return false;
									});
				});

$(document).ready(
		function() {
			
		});