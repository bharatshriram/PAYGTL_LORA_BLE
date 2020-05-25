/**
 * 
 */

$(document)
				.ready(
						function() {
							
							if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 2){
								
								
							
							
							$('#topupDetails').bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													
													selectcommunityName: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select your native language.'
									                        }
									                    }
									                },
									                selectBlockBasedonCommunity: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select your native language.'
									                        }
									                    }
									                },
									                
									                selectHouseBasedonBlock: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select your native language.'
									                        }
									                    }
									                },
													
									                AMR_topup : {
														message : 'The MIU ID is not valid',
														validators : {
															notEmpty : {
																message : 'The MIU ID is required and cannot be empty'
															}
														}
													},
													currentBalance_topup : {
														message : 'The Current Balance is not valid',
														validators : {
															notEmpty : {
																message : 'The Current Balance is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The Last Name must be more than 2 and less than 30 characters long'
															}
														}
													},
													dateTime_topup : {
														message : 'The Date Time is not valid',
														validators : {
															notEmpty : {
																message : 'The Date Time is required and cannot be empty'
															}
														}
													},
													unit_topup : {
														message : 'The Unit Rate is not valid',
														validators : {
															notEmpty : {
																message : 'The Unit Rate is required and cannot be empty'
															}
														}
													},
													emergency_topup : {
														message : 'The Emergency Credit is not valid',
														validators : {
															notEmpty : {
																message : 'The Emergency Credit is required and cannot be empty'
															}
														}
													},
													alarm_topup : {
														message : 'The Alarm Topup No. is not valid',
														validators : {
															notEmpty : {
																message : 'The Alarm Topup No. is required and cannot be empty'
															}
														}
													},
													recharge_topup : {
														message : 'The Recharge Amount is not valid',
														validators : {
															notEmpty : {
																message : 'The Recharge Amount is required and cannot be empty'
															},stringLength : {
																min : 2,
																max : 10,
																message : 'The Recharge Amount must be more than 2 and less than 10 characters long'
															},
															regexp : {
																regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
																message : 'The Recharge Amount can only consist of number'
															}
														}
													}
												}
											});
							}else if(sessionStorage.getItem("roleID") == 3){
								
								$.getJSON("/PAYGTL_LORA_BLE/topupdetails/" + sessionStorage.getItem("ID"), function(data) {
									//var Options = "";
									$("#CustomerCRNNumber").val(sessionStorage.getItem("ID")).trigger("change");
									$("#formCRNNumber").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
									$("#AMR_topup").val(data.topupdetails.meterID).trigger("change");
									$("#formAMR_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
									$("#currentBalance_topup").val(data.topupdetails.currentBalance).trigger("change");
									$("#formcurrentBalance_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
									$("#dateTime_topup").val(data.topupdetails.IoTTimeStamp).trigger("change");
									$("#formdateTime_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
									$("#unit_topup").val(data.topupdetails.tariff).trigger("change");
									$("#formunit_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
									$("#emergency_topup").val(data.topupdetails.emergencyCredit).trigger("change");
									$("#formemergency_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
									$("#alarm_topup").val(data.topupdetails.alarmCredit).trigger("change");
									$("#formalarm_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
									$("#reconnection_topup").val(data.topupdetails.reconnectionCharges).trigger("change");
									$("#formreconnection_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
									$("#fixed_topup").val(data.topupdetails.fixedCharges).trigger("change");
									$("#formfixed_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
									$("#month_topup").val(data.topupdetails.noOfMonths).trigger("change");
									$("#formmonth_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
									
								});
								
							}
							
							
							$('#topupDetails')
							.on(
									'status.field.bv',
									function(e, data) {
										formIsValid = true;
										$('.input-group.form-group', $(this))
												.each(
														function() {
														//	alert(this+"@@=>"+formIsValid);
															formIsValid = formIsValid
																	&& $(
																			this)
																			.hasClass(
																					'has-success');
															
															//alert("!!@@=>"+formIsValid);
															
														});

										if (formIsValid) {
											$('.submit-button', $(this))
													.attr('disabled',
															false);
										} else {
											$('.submit-button', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							
										$(document).on('click', '#topup', function () {

										var data1 = {}
										
										if(sessionStorage.getItem("roleID") == 3){
											data1["CRNNumber"] = $("#selectHouseBasedonBlock").val();	
										}else{
											data1["communityID"] = $("#selectcommunityName").val();
											data1["blockID"] = $("#selectBlockBasedonCommunity").val();
											data1["CRNNumber"] = $("#selectHouseBasedonBlock").val();
										}

										data1["meterID"] = $("#AMR_topup").val();
										data1["currentBalance"] = $("#currentBalance_topup").val();
										data1["tariffID"] = $("#tariffID").val();
										data1["amount"] = $("#recharge_topup").val();
										data1["modeOfPayment"] = "Cash"	
										data1["source"] = "web"
										data1["transactedByID"] = sessionStorage.getItem("createdByID");
										data1["transactedByRoleID"] = sessionStorage.getItem("roleID");
										
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/topup",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														/*alert("data"
																+ JSON
																		.stringify(data));*/
														if (data.result == "Success") {

															/*alert( "data"
																	+ data.result);*/
															
															bootbox.alert(data.Message,
																	function(
																			result) {
																			
																//alert();
																window.location = "topupStatus.jsp";
																return false
															});
															
															

														} else if(data.result == "Failure"){
															
															bootbox.alert(data.Message)
																	
																return false
														}
													}
												});
										return false;
									});
						});





$(document).ready(function() {
	
	if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 2){
		$("#blockAddButton").show();
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"; 
	}else if(sessionStorage.getItem("roleID") == 3){
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-5 total'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}
	else{
		$("#customerAddd").remove();
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}
	
	//alert((sessionStorage.getItem("roleID") == 3) ? [0,1,2,11]:11);
	//alert(((sessionStorage.getItem("roleID") == 3)) ? (sessionStorage.getItem("roleID") == 3) :(((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4))));
	
	$('#topstatusTable1').hide();
	table = $('#topstatusTable')
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
		"scrollX" : true,
	"ajax" : {
	"url":"/PAYGTL_LORA_BLE/status/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1",
	"type" : "GET",
	"data" : function(search) {
	},
	"complete" : function(json) {
		console.log(json);
		$("div.total").html('MUI ID: '+json.responseJSON.data[0].meterID+ ' CRN Number: '+sessionStorage.getItem("ID"));
	return json.data;
	},
	},
	"columns" : [
	 {
	    	"data" : "communityName"
	 },{
	"data" : "blockName"
	},{
	"data" : "houseNumber"
	},{
	"data" : "meterID"
	},{
	"data" : "amount"
	},{
	"data" : "emergencyCredit"
	},{
	"data" : "alarmCredit"
	},{
	"data" : "transactionDate"
	},{
	"data" : "transactedByUserName"
	},{
	"data" : "transactedByRoleDescription"
	},{
	"data" : "Status"
	},{
		"mData" : "action",
		"render" : function(data, type, row) {
			if(row.Status == "Failed"){
				return "<a onclick='getDeleteTransactionID("
				+ row.transactionID
				+ ")'>"
				+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>delete</i>"
				+ "</a>";
				
			}else if(row.Status == "Passed" || row.Status == "Pending...waiting for acknowledge"){
				return "<a onclick='getReceiptTransactionID("
				+ row.transactionID
				+ ")'>"
				+"<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
				+ "</a>"
			}
																
		}
		}
	],
	"columnDefs" : [ {
		//orderable : false,
		targets : (sessionStorage.getItem("roleID") == 3) ? [0,1,2,3]:11, visible: ((sessionStorage.getItem("roleID") == 3)) ? false :(((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
		
	},
	{
		"className": "dt-center", "targets": "_all"
	}], "buttons": [
		{extend: 'excel',
	        footer: 'true',
	        text: 'Excel',
	        exportOptions: {
	            columns: [0,1,2,3,4,5,6,7,8,9,10]
	        },
	        title:'ReCharge Status' 
	        },
	         
	        {extend: 'pdf',
	        footer: 'true',
	        exportOptions: {
	            columns: [0,1,2,3,4,5,6,7,8,9,10]
	        },
	        text: 'pdf',
	        orientation: 'landscape',
	        title:'ReCharge Status',
	        pageSize: 'LEGAL'
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
	        }
	]
	});
	
	
	if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
		table.buttons( $('a.customButton') ).remove();	
	}
	$("div.headname").html('<h3>ReCharge Status</h3>');
	//$("div.total").html('MUI ID: '+data.meterID+ ' Total Units: '+data.meterID);
	
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
							url : "/PAYGTL_LORA_BLE/status/"+url,
							dataType : "JSON",

							success : function(d) {
								
									$('#topstatusTable').dataTable()._fnAjaxUpdate();
									$("#topstatusTable_wrapper").hide();
									$("#filter").modal("hide");
									$("#topstatusTable1").show();
									var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
									var hCols = [ 3, 4 ];
									table = $('#topstatusTable1')
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
														"data" : "houseNumber"
														},{
														"data" : "meterID"
														},{
														"data" : "amount"
														},{
														"data" : "emergencyCredit"
														},{
														"data" : "alarmCredit"
														},{
														"data" : "transactionDate"
														},{
														"data" : "transactedByUserName"
														},{
														"data" : "transactedByRoleDescription"
														},{
														"data" : "Status"
														},{
															"mData" : "action",
															"render" : function(data, type, row) {
																if(row.Status == "Failed"){
																	return "<a onclick='getDeleteTransactionID("
																	+ row.transactionID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>delete</i>"
																	+ "</a>";
																	
																}else if(row.Status == "Passed" || row.Status == "Pending...waiting for acknowledge"){
																	return "<a onclick='getReceiptTransactionID("
																	+ row.transactionID
																	+ ")'>"
																	+"<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																	+ "</a>"
																}
																													
															}
															}
														],
														"columnDefs" : [ {
															//orderable : false,
															targets : 11, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
														},
														{
															"className": "dt-center", "targets": "_all"
														}], "buttons": [
															{extend: 'excel',
														        footer: 'true',
														        text: 'Excel',
														        exportOptions: {
														            columns: [0,1,2,3,4,5,6,7,8,9,10]
														        },
														        title:'ReCharge Status' 
														        },
														         
														        {extend: 'pdf',
														        footer: 'true',
														        exportOptions: {
														            columns: [0,1,2,3,4,5,6,7,8,9,10]
														        },
														        text: 'pdf',
														        orientation: 'landscape',
														        title:'ReCharge Status',
														        pageSize: 'LEGAL'
														       },
														       {
													                text: 'Reset',
													                action: function ( e, dt, node, config ) {
													                    alert( 'Button activated' );
													                },
													                className: 'customButton',
													               
													                action: function ( e, dt, button, config ) {
													                   
													                	window.location = "topupStatus.jsp"
													                }
													            }
														]
														});
														if(sessionStorage.getItem("roleID") == 3){
															table.buttons( $('a.customButton') ).remove();	
														}
											$("div.headname").html('<h3>ReCharge Status</h3>');
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
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELEE RECORD",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/PAYGTL_LORA_BLE/status/delete/" + transID,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "topupStatus.jsp";
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

function getReceiptTransactionID(transID){
	bootbox
	.confirm(
			"ARE YOU SURE TO DOWNLOAD RECEIPT",
		function(
			result) {
			//	alert(result);
				window.open("/PAYGTL_LORA_BLE/status/print/" + transID);
			/*if(result == true){
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "/PAYGTL_LORA_BLE/status/print/" + transID,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "topupStatus.jsp";
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
				
			}*/
		});
}

