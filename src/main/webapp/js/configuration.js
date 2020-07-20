/**
 * 
 */

$(document)
		.ready(
				function() {
					
					if(sessionStorage.getItem("roleID") == 2){
						$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
						$("#formcommunityNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
						$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
						$("#formblockNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
					}
										$(document).on('click', '#configuration', function () {
									    
										var data1 = {}										
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


										if ($("#selectcommandType").val() == "null" || $("#selectcommandType").val() == -1 || $("#selectcommandType").val() == "Select Command Type") {

											bootbox
											.alert("Select Command Type");
											return false;
										}
										
										if($("#selectcommandType").val() == "6"){
											var reg =/[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/;
											if($("#defaultReading").val() == ""){
											
												bootbox
												.alert("Enter Default Reading");
												return false;
											}	
											
												else if(!reg.test($("#defaultReading").val())){
													bootbox
													.alert("The Default Reading can only consist of number");
													return false;
												}	
											data1["defaultReading"] = $("#defaultReading").val();
											} else if($("#selectcommandType").val() == "10"){
												if($("#selectTariffName").val() == "Select Tariff" || $("#selectTariffName").val() == -1){
												
													bootbox
													.alert("Select Tariff");
													return false;
												}	
												data1["tariffID"] = $("#selectTariffName").val();
											}
										 
										data1["commandType"] = $("#selectcommandType").val();
										data1["CRNNumber"] = $("#selectHouseBasedonBlock").val();
										data1["meterID"] = $("#AMR_topup").val();
										data1["source"] = "web";

										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/configuration/add",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(data) {
														
														if (data.result == "Success") {

															/*
															 * alert( "data" +
															 * data.result);
															 */

															bootbox
																	.alert(
																			data.Message,
																			function(
																					result) {

																				// alert();
																				window.location = "configurationStatus.jsp";
																				return false
																			});

														} else if (data.result == "Failure") {

															bootbox
																	.alert(
																			data.Message,
																			function(
																					result) {

																				// alert();
																				window.location = "configuration.jsp";
																				return false
																			});
														}
													}
												});
										return false;
									});
				});

$(document)
		.ready(
				function() {
					table = $('#configurationstatusTable1').hide();
					table = $('#configurationstatusTable')
							.DataTable(
									{
										"dom" : "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
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
												"ajax" : {
											"url" : "/PAYGTL_LORA_BLE/configuration/"+ sessionStorage.getItem("roleID")+ "/"+ sessionStorage.getItem("ID")+"/-1",
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												return json.data;
											},
										},
										"columns" : [
												{
													"data" : "meterID",
													"defaultContent": ""
												},
												{
													"data" : "commandType",
													"defaultContent": ""
												},{
													"data" : "value",
													"defaultContent": ""
												},
												{
													"data" : "modifiedDate",
													"defaultContent": ""
												},
												{
													"data" : "status",
													"defaultContent": ""
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {

														if(row.status == "Passed"){
														
															return "---";
															
														}else if(row.status == "Pending" || row.status == "Pending...waiting for acknowledge"){
															
															return "---";
															
														}
														
														else if(row.status == "Failed"){
															return "<a onclick='getDeleteTransactionID("
															+ row.transactionID
															+ ")'>"
															+ "<i class='material-icons' style='color:#17e9e9; cursor:pointer;'>delete</i>"
															+ "</a>"
															
														}
														
														
													}
												} ],
										"columnDefs" : [ {
										//	orderable : false,
											targets :5, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
										},
										{
											"className": "dt-center", "targets": "_all"
										}],
										"buttons" : [
										{
											extend : 'excel',
											footer : 'true',
											//text : 'Excel',
											exportOptions : {
												columns : [ 0, 1, 2, 3, 4 ]
											},
											//className: 'custom-btn fa fa-file-excel-o',
											title : 'Configuration Status'
										},

										{
											extend : 'pdf',
											//className: 'custom-btn fa fa-file-pdf-o',
											footer : 'true',
											exportOptions : {
												columns : [ 0, 1, 2, 3,4 ]
											},
											//text : 'pdf',
											orientation : 'landscape',
											title : 'Configuration Status'
										},
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
									        } ],
											 initComplete: function() {
												   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
												   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
												  }

									});
					if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
						table.buttons( $('a.customButton') ).remove();	
					}
					
					$("div.headname").html('<h3>Configuration Status</h3>');
					
					
					

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
											url : "/PAYGTL_LORA_BLE/configuration/"+url,
											dataType : "JSON",

											success : function(d) {
												
													$('#configurationstatusTable').dataTable()._fnAjaxUpdate();
													$("#configurationstatusTable_wrapper").hide();
													$("#filter").modal("hide");
													$("#configurationstatusTable1").show();
													
													var hCols = [ 3, 4 ];
													table = $('#configurationstatusTable1')
													.DataTable(
															{
																
																	"dom": "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
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
																	"scrollX" : false,
																	"data" : d.data,
																	"columns" : [
																		{
																			"data" : "meterID",
																			"defaultContent": ""
																		},
																		{
																			"data" : "commandType",
																			"defaultContent": ""
																		},{
																			"data" : "value",
																			"defaultContent": ""
																		},
																		{
																			"data" : "modifiedDate",
																			"defaultContent": ""
																		},
																		{
																			"data" : "status",
																			"defaultContent": ""
																		},
																		{
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {

																				if(row.status == "Passed"){
																				
																					return "---";
																					
																				}else if(row.status == "Pending" || row.Status == "Pending...waiting for acknowledge"){
																				
																					return "---";
																					
																				}
																				
																				else if(row.status == "Failed"){
																					return "<a onclick='getDeleteTransactionID("
																					+ row.transactionID
																					+ ")'>"
																					+ "<i class='material-icons' style='color:#17e9e9; cursor:pointer;'>delete</i>"
																					+ "</a>"
																					
																				}
																				
																				
																			}
																		} ],
																"columnDefs" : [ {
																//	orderable : false,
																	targets : 5, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
																},
																{
																	"className": "dt-center", "targets": "_all"
																}],
																"buttons" : [
																{
																	extend : 'excel',
																	footer : 'true',
																	//text : 'Excel',
																	exportOptions : {
																		columns : [ 0, 1, 2, 3,4 ]
																	},
																	//className: 'custom-btn fa fa-file-excel-o',
																	title : 'Configuration Status'
																},

																{
																	extend : 'pdf',
																	footer : 'true',
																	exportOptions : {
																		columns : [ 0, 1, 2, 3,4 ]
																	},
																	//className: 'custom-btn fa fa-file-pdf-o',
																	//text : 'pdf',
																	orientation : 'landscape',
																	title : 'Configuration Status'
																},
																{
													                text: 'Reset',
													                action: function ( e, dt, node, config ) {
													                    alert( 'Button activated' );
													                },
													                className: 'customButton',
													               
													                action: function ( e, dt, button, config ) {
													                   
													                	window.location = "configurationStatus.jsp"
													                }
													            } ],
																 initComplete: function() {
																	   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																	   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																	  }

															});
													if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
														table.buttons( $('a.customButton') ).remove();	
													}
											
											$("div.headname").html('<h3>Configuration</h3>');
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





function getDeleteTransactionID(transID){
	
	//alert(transID);
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELETE RECORD",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/PAYGTL_LORA_BLE/configuration/delete/" + transID,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "configurationStatus.jsp";
								});

						} else {
							bootbox
							.alert(data.Message);
							return false;
						}
					}
				});
			}else if(result==false){
				//alert("@"+false)
				
			}
		});
}

