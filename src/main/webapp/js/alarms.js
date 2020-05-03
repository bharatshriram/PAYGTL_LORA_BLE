/**
 * 
 */
									
									$(document).ready(function() {
										table = $('#alarmTable')
										.DataTable(
										{
											"dom": "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f<br/>i>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-white'i><'col-sm-6 text-white'p>>",
											/*"processing" : false,*/
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
										"ajax" : {
										"url":"/PAYGTL_LORA_BLE/alarm/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
										"type" : "GET",
										"data" : function(search) {
										},
										"complete" : function(json) {
											console.log(json);
										return json.data;
										},
										},
										"columns" : [
										 {
										    	"data" : "communityName"
										 },{
										"data" : "blockName"
										},{
											"mData" : "action",
											"render" : function(data, type, row) {
												
												return '<div class=btn btn-secondary data-toggle="tooltip" data-placement=top ' +
														'title='+row.blockName+'>' 
												+row.houseNumber +
												 '</div>';
											}
											},{
										"data" : "meterID"
										},{
										"data" : "batteryVoltage"
										},{
										"data" : "tamper"
										},{
										"data" : "dateTime"
										},{
										"data" : "difference"
										}
										],
										"columnDefs" : [ {
											//targets : 11, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
											"className": "dt-center", "targets": "_all"
										}], "buttons": [
											   /* 'csvHtml5',
											'excelHtml5',
										'pdfHtml5'*/
											
											{extend: 'excel',
										        footer: 'true',
										        text: 'Excel',
										        title:'Statistics'  },
										         
										        {extend: 'pdf',
										        footer: 'true',
										        exportOptions: {
										            columns: [1,2,3,4,5,6,7]
										        },
										        text: 'pdf',
										        orientation: 'landscape',
										        title:'Statistics'  }
										]
										});
										});
									
									
									
									
									
									
									
									
									
									

									$(document)
											.ready(
													function() {
														$("#alarmReport")
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
																						url : "/PAYGTL_LORA_BLE/alarmreport",
																						data : JSON
																								.stringify(data1),
																						dataType : "JSON",

																						success : function(d) {
																							
																							//if (data.result == "Success") {
																							
																							$("#form").hide();
																							$("#tablereport").show();

																							 table = $('#alarmReportTable').DataTable(
																										{
																											"dom": "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-white'i><'col-sm-6 text-white'p>>",
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
																										    	"data" : "communityName"
																											 },{
																											"data" : "blockName"
																											},{
																												"mData" : "action",
																												"render" : function(data, type, row) {
																													
																													return '<div class=btn btn-secondary data-toggle="tooltip" data-placement=top ' +
																															'title='+row.blockName+'>' 
																													+row.houseNumber +
																													 '</div>';
																												}
																												},{
																											"data" : "meterID"
																											},{
																											"data" : "batteryVoltage"
																											},{
																											"data" : "tamper"
																											},{
																											"data" : "dateTime"
																											}
																											],
																											"columnDefs" : [ {
																												//targets : 11, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
																												"className": "dt-center", "targets": "_all"
																											}], "buttons": [
																												   /* 'csvHtml5',
																												'excelHtml5',
																											'pdfHtml5'*/
																												
																												{extend: 'excel',
																											        footer: 'true',
																											        text: 'Excel',
																											        title:'Alarm Report'  },
																											         
																											        {extend: 'pdf',
																											        footer: 'true',
																											        exportOptions: {
																											            columns: [1,2,3,4,5,6]
																											        },
																											        text: 'pdf',
																											        orientation: 'landscape',
																											        title:'Alarm Report'  }
																											]
																										});
																								//table.ajax.reload()
																						//	}
																						}
																					});
																			return false;
																		});
													});