/**
 * 
 */
									
									$(document).ready(function() {
										table = $('#alarmTable')
										.DataTable(
										{
											"dom": "<'row'<'col-sm-4 headname'><'col-sm-3'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
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
											{extend: 'excel',
										        footer: 'true',
										        text: 'Excel',
										        title:'Statistics'  },
										         
										        {extend: 'pdf',
										        footer: 'true',
										        exportOptions: {
										            columns: [0,1,2,3,4,5,6]
										        },
										        text: 'pdf',
										        orientation: 'landscape',
										        title:'Statistics'  },
										        {
										            className: 'customButton',
										            text : "Filter",
										             action: function ( e, dt, button, config ) {
										             	$('.customButton').attr(
										                     {
										                         "data-toggle": "modal",
										                         "data-target": "#filter"
										                     }
										                 );
										             }
										         }
										]
										});
										 $("div.headname").html('<h3>Alarms Details</h3>');
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
																						url : "/PAYGTL_LORA_BLE/alarmreports",
																						data : JSON
																								.stringify(data1),
																						dataType : "JSON",

																						success : function(d) {
																							
																							//if (data.result == "Success") {
																							
																							$("#form").hide();
																							$("#tablereport").show();

																							 table = $('#alarmReportTable').DataTable(
																										{
																											"dom": "<'row'<'col-sm-4 headname'><'col-sm-3 totalcount'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
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
																											"scrollX" : true,
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
																											            columns: [0,1,2,3,4,5,6]
																											        },
																											        text: 'pdf',
																											        orientation: 'landscape',
																											        title:'Alarm Report'  }
																											]
																										});

																							 $("div.headname").html('<h3>Alarms Reports</h3>');
																								//table.ajax.reload()
																							 
																							 $("div.addevent").html('<button id="back" onClick="returnBack()"'
																									 +'class="btn btn-raised btn-primary float-right"'
																										+'>'
																									+'	<span>Back</span>'
																								+'</button>');
																						
																						}
																					});
																			return false;
																		});
														
														
														
														
														$("#customerFilter")
														.click(
																function() {

																	var url = $("#filterselectcommunityName").val() == "-1" ? sessionStorage.getItem("roleID")+"/0/-1" : $("#filterselectBlockBasedonCommunity").val() == "Select Block" ? 
																			$("#filterselectcommunityName").val() == "-1" ? 
																			sessionStorage.getItem("roleID")+"/0/-1":sessionStorage.getItem("roleID")+"/0/"+$("#filterselectcommunityName").val():
																		"2/"+$("#filterselectBlockBasedonCommunity").val()+"/-1"
																			
																	$
																			.ajax({
																				type : "GET",
																				contentType : "application/json",
																				url : "/PAYGTL_LORA_BLE/alarmreports/"+url,
																				dataType : "JSON",

																				success : function(d) {
																					
																					//if (data.result == "Success") {
																					$('#customerTable').dataTable()._fnAjaxUpdate();
																					//$("#form").hide();
																					//$("#tablereport").show();
																						console.log(JSON.stringify(d));
																						$("#customerTable_wrapper").hide();
																						$("#filter").modal("hide");
																						$("#customerTable1").show();
																						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
																						var hCols = [ 3, 4 ];
																						table = $('#customerTable1')
																						.DataTable(
																								{
																									
																									"dom": dom1,
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
																										"columns" : [
																								{
																								"data" : "communityName"
																								},{
																								"data" : "blockName"
																								},{
																								"data" : "CRNNumber"
																								},{
																								"data" : "firstName"
																								},{
																								"data" : "lastName"
																								},{
																								"data" : "houseNumber"
																								},{
																								"data" : "meterSerialNumber"
																								},{
																								"data" : "meterID"
																								},{
																								"data" : "mobileNumber"
																								},{
																								"data" : "email"
																								},{
																								"data" : "createdByUserName"
																								},{
																								"data" : "createdByRoleDescription"
																								},{
																								"data" : "date"
																								}
																								,{
																									"mData" : "action",
																									"render" : function(data, type, row) {
																										
																										return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit(\""
																									}
																									},{
																										"mData" : "action",
																										"render" : function(data, type, row) {
																											
																											return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit(\""
																																										+ row.CRNNumber
																																										+ "\")'>"
																																										+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																																										+ "</a>"
																										}
																										}



																								],
																								"columnDefs" : [ {
																									//orderable : false,
																									targets : 13, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4))),
																									//targets : 14, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4))),
																								},{
																									//orderable : false,
																									//targets : 13, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4))),
																									targets : 14, visible: ( !(sessionStorage.getItem("roleID") == 1) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4))))
																								},
																								{
																									"className": "dt-center", "targets": "_all"
																								}], "buttons": [
																									{
																						                text: 'Reset',
																						                action: function ( e, dt, node, config ) {
																						                    alert( 'Button activated' );
																						                },
																						                className: 'customButton',
																						               
																						                action: function ( e, dt, button, config ) {
																						                   
																						                	window.location = "customerDetails.jsp"
																						                }
																						            }
																								]
																								})
																								$("div.headname").html('<h3>Customer Managemnent</h3>');
																				}
																			});
																	return false;
																});
														
														$("#resetFilter")
														.on(
																function() {
																	
																	 $("input:text").val("");
														
																});	
														
													});