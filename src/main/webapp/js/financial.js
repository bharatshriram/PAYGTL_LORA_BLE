/**
 * 
 */

$(document)
		.ready(
				function() {
					$("#financialReport")
							.click(
									function() {

										var selectcommunityName = $(
												"#selectcommunityName")
												.val();

										if ($("#selectcommunityName").val() == "-1") {
											
											bootbox
											.alert("Select Community Id");
											return false;
										}

										/*if ($("#selectBlockBasedonCommunity").val() == "null" || $("#selectBlockBasedonCommunity").val() == "Select Block") {

											bootbox
											.alert("Select Block Name");
											return false;
										}
										
										if ($("#selectHouseBasedonBlock").val() == "null" || $("#selectHouseBasedonBlock").val() == "Select House") {

											bootbox
											.alert("Select House Name");
											return false;
										}*/


										if ($("#start_date").val() == "null" || $("#start_date").val() == "") {

											bootbox
											.alert("Select Only Year");
											return false;
										}
										
										/*if ($("#end_date").val() == "null" || $("#end_date").val() == "") {

											bootbox
											.alert("Select End Date");
											return false;
										}*/
										
										
										var data1 = {}
										data1["communityID"] = $(
												"#selectcommunityName").val();
										data1["blockID"] = $("#selectBlockBasedonCommunity").val() == "Select Block" ? 0 : $("#selectBlockBasedonCommunity").val();
										/*data1["CRNNumber"] = $(
												"#selectHouseBasedonBlock")
												.val();
										data1["meterID"] = $("#AMR_topup")
												.val();*/
										data1["year"] = $("#start_date")
												.val();
										data1["month"] =  $("#end_date").val() == "" ? 0 : $("#end_date").val();

										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/financialreports/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(d) {
														
														//document.querySelector("#totalcount").innerText = ;
														
														$("#form").hide();
														$("#tablereport").show();

														document.querySelector("#totalcount").innerHTML = "Total Amount: "+d.totalAmountForSelectedPeriod+ " Total Units: "+d.totalUnitsForSelectedPeriod
														
														 table = $('#financialTable').DataTable(
																	{
																		"dom": "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f<br/><'span'>>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-12'p<br/>i>>",
																		"responsive" : true,
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
																		"scrollX" : true,
																		"data" : d.data,
																		"columns" : [ {
																			"data" : "communityName"
																		}, {
																			"data" : "blockName"
																		}, {
																			"data" : "houseNumber"
																		}, {
																			"data" : "meterID"
																		}, {
																			"data" : "totalAmount"
																		}, {
																			"data" : "totalUnits"
																		}],
																		"columnDefs" : [ {
																			orderable : false
																		//	targets : 11, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
																			,"className": "dt-center", "targets": "_all"
																		},
																		{
																			orderable : false,
																			targets : [ 1 ]
																		}], "buttons": [
																			   /* 'csvHtml5',
																			'excelHtml5',
																		'pdfHtml5'*/
																			
																			{extend: 'excel',
																		        footer: 'true',
																		        text: 'Excel',
																		        title:'Financial Report'  },
																		         
																		        {extend: 'pdf',
																		        footer: 'true',
																		        exportOptions: {
																		            columns: [1,2,3,4,5,6,7]
																		        },
																		        text: 'pdf',
																		        orientation: 'landscape',
																		        title:'Financial Report'  }
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