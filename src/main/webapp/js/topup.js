/**
 * 
 */

$(document)
		.ready(
				function() {

					if (sessionStorage.getItem("roleID") == 1
							|| sessionStorage.getItem("roleID") == 2) {

						$('#topupDetails')
								.bootstrapValidator(
										{
											feedbackIcons : {
												valid : 'glyphicon glyphicon-ok',
												invalid : 'glyphicon glyphicon-remove',
												validating : 'glyphicon glyphicon-refresh'
											},
											fields : {

												selectcommunityName : {
													validators : {
														notEmpty : {
															message : 'Please select your native language.'
														}
													}
												},
												selectBlockBasedonCommunity : {
													validators : {
														notEmpty : {
															message : 'Please select your native language.'
														}
													}
												},

												selectHouseBasedonBlock : {
													validators : {
														notEmpty : {
															message : 'Please select your native language.'
														}
													}
												},

												AMR_topup : {
													message : 'MIU ID is not valid',
													validators : {
														notEmpty : {
															message : 'MIU ID is required and cannot be empty'
														}
													}
												},
												currentBalance_topup : {
													message : 'Current Balance is not valid',
													validators : {
														notEmpty : {
															message : 'Current Balance is required and cannot be empty'
														},
														stringLength : {
															min : 2,
															max : 30,
															message : 'Last Name must be more than 2 and less than 30 characters long'
														}
													}
												},
												dateTime_topup : {
													message : 'Date Time is not valid',
													validators : {
														notEmpty : {
															message : 'Date Time is required and cannot be empty'
														}
													}
												},
												unit_topup : {
													message : 'Unit Rate is not valid',
													validators : {
														notEmpty : {
															message : 'Unit Rate is required and cannot be empty'
														}
													}
												},
												emergency_topup : {
													message : 'Emergency Credit is not valid',
													validators : {
														notEmpty : {
															message : 'Emergency Credit is required and cannot be empty'
														}
													}
												},
												alarm_topup : {
													message : 'Alarm Topup No. is not valid',
													validators : {
														notEmpty : {
															message : 'Alarm Topup No. is required and cannot be empty'
														}
													}
												},
												recharge_topup : {
													message : 'Recharge Amount is not valid',
													validators : {
														notEmpty : {
															message : 'Recharge Amount is required and cannot be empty'
														},
														stringLength : {
															min : 2,
															max : 10,
															message : 'Recharge Amount must be more than 2 and less than 10 characters long'
														},
														regexp : {
															regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
															message : 'Recharge Amount can only consist of number'
														}
													}
												},
												paymentMode : {
													validators : {
														notEmpty : {
															message : 'Please select your native language.'
														}
													}
												},
											}
										});
					} else if (sessionStorage.getItem("roleID") == 3) {

						$
								.getJSON(
										"/PAYGTL_LORA_BLE/topupdetails/"
												+ sessionStorage.getItem("ID"),
										function(data) {
											// var Options = "";
											$("#CustomerCRNNumber").val(
													sessionStorage
															.getItem("ID"))
													.trigger("change");
											$("#formCRNNumber")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$("#AMR_topup").val(
													data.topupdetails.meterID)
													.trigger("change");
											$("#formAMR_topup")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$("#currentBalance_topup")
													.val(
															data.topupdetails.currentBalance)
													.trigger("change");
											$("#formcurrentBalance_topup")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$("#dateTime_topup")
													.val(
															data.topupdetails.IoTTimeStamp)
													.trigger("change");
											$("#formdateTime_topup")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$("#unit_topup").val(
													data.topupdetails.tariff)
													.trigger("change");
											$("#formunit_topup")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$("#emergency_topup")
													.val(
															data.topupdetails.emergencyCredit)
													.trigger("change");
											$("#formemergency_topup")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$("#alarm_topup")
													.val(
															data.topupdetails.alarmCredit)
													.trigger("change");
											$("#formalarm_topup")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$("#reconnection_topup")
													.val(
															data.topupdetails.reconnectionCharges)
													.trigger("change");
											$("#formreconnection_topup")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$("#fixed_topup")
													.val(
															data.topupdetails.fixedCharges)
													.trigger("change");
											$("#formfixed_topup")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$("#month_topup")
													.val(
															data.topupdetails.noOfMonths)
													.trigger("change");
											$("#formmonth_topup")
													.addClass(
															"input-group form-group has-feedback has-success bmd-form-group is-filled")

											$('#topup').attr('disabled', false);

										});

					}

					$('#topupDetails').on(
							'status.field.bv',
							function(e, data) {
								formIsValid = true;
								$('.input-group.form-group', $(this)).each(
										function() {
											// alert(this+"@@=>"+formIsValid);
											formIsValid = formIsValid
													&& $(this).hasClass(
															'has-success');

											// alert("!!@@=>"+formIsValid);

										});

								if (formIsValid) {
									$('.submit-button', $(this)).attr(
											'disabled', false);
								} else {
									$('.submit-button', $(this)).attr(
											'disabled', true);
								}
							});

					$(document)
							.on(
									'click',
									'#topup',
									function() {

										var data1 = {}

										var regTopup = /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/

										if ($("#selectHouseBasedonBlock").val() == "Select CRN") {

											bootbox
													.alert("Please Select CRN Number");
											return false;
										}

										if ($("#recharge_topup").val() == "") {

											bootbox
													.alert("Please Enter Amount");
											return false;
										} else {
											if (!regTopup.test($(
													"#recharge_topup").val())) {
												bootbox
														.alert('Enter Valid Amount');
												return false;
											}
										}

										if ($("#paymentMode").val() == "Select Mode") {

											bootbox.alert("Please Select Mode");
											return false;
										}

										if (sessionStorage.getItem("roleID") == 3) {
											data1["CRNNumber"] = $(
													"#CustomerCRNNumber")
													.val();
										} else {
											data1["communityID"] = $(
													"#selectcommunityName")
													.val();
											data1["blockID"] = $(
													"#selectBlockBasedonCommunity")
													.val();
											data1["CRNNumber"] = $(
													"#selectHouseBasedonBlock")
													.val();
										}

										data1["meterID"] = $("#AMR_topup")
												.val();
										data1["currentBalance"] = $(
												"#currentBalance_topup").val();
										data1["tariffID"] = $("#tariffID")
												.val();
										data1["amount"] = $("#recharge_topup")
												.val();
										if(sessionStorage.getItem("roleID") == 3){
											data1["modeOfPayment"] = "Online";
										}
										else if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 2){
										data1["modeOfPayment"] = $(
												"#paymentMode").val()
										}
										data1["source"] = "web"
										data1["transactedByID"] = sessionStorage
												.getItem("createdByID");
										data1["transactedByRoleID"] = sessionStorage
												.getItem("roleID");

										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/topup",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(data) {
														if (data.result == "Success") {

															if (data.paymentMode == "Cash") {

																bootbox
																		.alert(
																				data.Message,
																				function(
																						result) {
																					window.location = "topupStatus.jsp";
																					return false
																				});

															} else if (data.paymentMode == "Online") {

																sessionStorage
																		.setItem(
																				"transactionID",
																				data.checkoutDetails.transactionID);

																data.checkoutDetails.handler = function processRazorpayResponse(
																		response) {

																	data2 = {}
																	data2["razorpay_order_id"] = response.razorpay_order_id
																	data2["razorpay_payment_id"] = response.razorpay_payment_id
																	data2["razorpay_signature"] = response.razorpay_signature
																	data2["transactionID"] = sessionStorage
																			.getItem("transactionID");

																	$
																			.ajax({
																				type : "POST",
																				contentType : "application/json",
																				url : "/PAYGTL_LORA_BLE/checkout",
																				data : JSON
																						.stringify(data2),
																				dataType : "JSON",

																				success : function(
																						data) {
																					if (data.result == "Success") {

																						bootbox
																								.alert(
																										data.Message,
																										function(
																												result) {
																											window.location = "topupStatus.jsp";
																											return false
																										});

																					} else {
																						bootbox
																								.alert(
																										data.Message,
																										function(
																												result) {
																											window.location = "topupStatus.jsp";
																											return false
																										});
																					}
																				}
																			});
																	return false;
																}
															}
															;

															var rzp1 = new Razorpay(
																	data.checkoutDetails);
															rzp1.open();
														} else if (data.result == "Failure") {

															bootbox
																	.alert(data.Message)

															return false
														}
													}
												});
										return false;
									});
				});

$(document)
		.ready(
				function() {

					if (sessionStorage.getItem("roleID") == 2
							|| sessionStorage.getItem("roleID") == 5) {
						$("#communityNameAdd").val(
								sessionStorage.getItem("communityName"));
						$("#formcommunityNameAdd")
								.addClass(
										"input-group form-group has-feedback has-success bmd-form-group is-filled")
						$("#blockNameAdd").val(
								sessionStorage.getItem("blockName"));
						$("#formblockNameAdd")
								.addClass(
										"input-group form-group has-feedback has-success bmd-form-group is-filled")
					}

					if (sessionStorage.getItem("roleID") == 1
							|| sessionStorage.getItem("roleID") == 2) {
						$("#blockAddButton").show();
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					} else if (sessionStorage.getItem("roleID") == 3) {
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-5 total'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					} else {
						$("#customerAddd").remove();
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					}

					// alert((sessionStorage.getItem("roleID") == 3) ?
					// [0,1,2,11]:11);
					// alert(((sessionStorage.getItem("roleID") == 3)) ?
					// (sessionStorage.getItem("roleID") == 3)
					// :(((sessionStorage.getItem("roleID") == 1) ||
					// (sessionStorage.getItem("roleID") == 2) ||
					// (sessionStorage.getItem("roleID") == 3)) &&
					// (!(sessionStorage.getItem("roleID") == 5) ||
					// !(sessionStorage.getItem("roleID") == 4))));

					var filterId = sessionStorage.getItem("day") == "day" ? "1"
							: 0;
					sessionStorage.removeItem("day")

					$('#topstatusTable1').hide();
					table = $('#topstatusTable')
							.DataTable(
									{
										"dom" : dom1,
										"responsive" : true,
										/* "processing" : true, */
										"serverSide" : false,
										"bDestroy" : true,
										"bPaginate" : true,
										"pagging" : true,
										"bProcessing" : true,
										"ordering" : true,
										"order" : [ 0, "desc" ],
										"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
										"pageLength" : 5,
										"scrollY" : 324,
										"scrollX" : true,
										"ajax" : {
											"url" : "/PAYGTL_LORA_BLE/status/"
													+ sessionStorage
															.getItem("roleID")
													+ "/"
													+ sessionStorage
															.getItem("ID")
													+ "/-1/" + filterId,
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												console.log(json);

												if (sessionStorage
														.getItem("roleID") == 3) {
													$("div.total")
															.html(
																	'<b>MIU ID:</b> '
																			+ json.responseJSON.data[0].meterID
																			+ ' <b>CRN Number:</b> '
																			+ sessionStorage
																					.getItem("ID"));
												}
												return json.data;
											},
										},
										"columns" : [
												{
													"data" : "communityName",
													"defaultContent" : ""
												},
												{
													"data" : "blockName",
													"defaultContent" : ""
												},
												{
													"data" : "houseNumber",
													"defaultContent" : ""
												},
												{
													"data" : "meterID",
													"defaultContent" : ""
												},
												{
													"data" : "amount",
													"defaultContent" : ""
												},
												{
													"data" : "emergencyCredit",
													"defaultContent" : ""
												},
												{
													"data" : "alarmCredit",
													"defaultContent" : ""
												},
												{
													"data" : "modeOfPayment",
													"defaultContent" : ""
												},
												{
													"data" : "razorPayOrderID",
													"defaultContent" : ""
												},
												{
													"data" : "razorPayPaymentID",
													"defaultContent" : ""
												},
												{
													"data" : "razorPayRefundID",
													"defaultContent" : ""
												},
												{
													"data" : "RazorPayRefundStatus",
													"defaultContent" : ""
												},
												{
													"data" : "transactionDate",
													"defaultContent" : ""
												},
												{
													"data" : "transactedByUserName",
													"defaultContent" : ""
												},
												{
													"data" : "transactedByRoleDescription",
													"defaultContent" : ""
												},
												{
													"data" : "Status",
													"defaultContent" : ""
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {
														if (row.Status == "Failed") {
															return "<a onclick='getDeleteTransactionID("
																	+ row.transactionID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>delete</i>"
																	+ "</a>";

														} else if (row.Status == "Passed"
																|| row.Status == "Pending") {
															return "<a onclick='getReceiptTransactionID("
																	+ row.transactionID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																	+ "</a>"
														} else if (row.Status == "Pending...waiting for acknowledge") {
															return "---"
														}

													}
												} ],
										"columnDefs" : [
												{
													// orderable : false,
													targets : (sessionStorage
															.getItem("roleID") == 3) ? [
															0, 1, 2, 3 ]
															: 11,
													visible : ((sessionStorage
															.getItem("roleID") == 3)) ? false
															: (((sessionStorage
																	.getItem("roleID") == 1)
																	|| (sessionStorage
																			.getItem("roleID") == 2) || (sessionStorage
																	.getItem("roleID") == 3)) && (!(sessionStorage
																	.getItem("roleID") == 5) || !(sessionStorage
																	.getItem("roleID") == 4)))

												}, {
													"className" : "dt-center",
													"targets" : "_all"
												} ],
										"buttons" : [
												{
													extend : 'excel',
													footer : 'true',
													//text : 'Excel',
													exportOptions : {
														columns : [ 0, 1, 2, 3,
																4, 5, 6, 7, 8,
																9, 10, 11, 12,
																13, 14, 15 ]
													},
													//className: 'custom-btn fa fa-file-excel-o',
													title : 'ReCharge Status'
												},

												{
													extend : 'pdf',
													footer : 'true',
													exportOptions : {
														columns : [ 0, 1, 2, 3,
																4, 5, 6, 7, 8,
																9, 10, 11, 12,
																13, 14, 15 ]
													},
													//className: 'custom-btn fa fa-file-pdf-o',
													//text : 'pdf',
													orientation : 'landscape',
													title : 'ReCharge Status',
													pageSize : 'LEGAL'
												},
												{

													className : 'customButton',
													text : "Filter",
													action : function(e, dt,
															button, config) {
														$('.customButton')
																.attr(
																		{
																			"data-toggle" : "modal",
																			"data-target" : "#filter"
																		});
													}
												} ],
												 initComplete: function() {
													   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
													   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
													  }
									});

					if (sessionStorage.getItem("roleID") == 3
							|| sessionStorage.getItem("roleID") == 2
							|| sessionStorage.getItem("roleID") == 5) {
						if(sessionStorage.getItem("roleID") == 3){
							
						}else{
						table.buttons($('a.customButton')).remove();
					}}
					$("div.headname").html('<h3>ReCharge Status</h3>');
					// $("div.total").html('MUI ID: '+data.meterID+ ' Total
					// Units: '+data.meterID);

					$("#customerFilter")
							.click(
									function() {

										var url = $(
												"#filterselectcommunityName")
												.val() == "-1" ? sessionStorage
												.getItem("roleID")
												+ "/0/-1/0"
												: $(
														"#filterselectBlockBasedonCommunity")
														.val() == "Select Block" ? $(
														"#filterselectcommunityName")
														.val() == "-1" ? sessionStorage
														.getItem("roleID")
														+ "/0/-1/-1"
														: sessionStorage
																.getItem("roleID")
																+ "/0/"
																+ $(
																		"#filterselectcommunityName")
																		.val()
																+ "/0"
														: "2/"
																+ $(
																		"#filterselectBlockBasedonCommunity")
																		.val()
																+ "/-1/0"

										$
												.ajax({
													type : "GET",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/status/"
															+ url,
													dataType : "JSON",

													success : function(d) {

														$('#topstatusTable')
																.dataTable()
																._fnAjaxUpdate();
														$(
																"#topstatusTable_wrapper")
																.hide();
														$("#filter").modal(
																"hide");
														$("#topstatusTable1")
																.show();
														var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
																+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>"
																+ "<'row'<'col-sm-12'tr>>"
																+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
														var hCols = [ 3, 4 ];
														table = $(
																'#topstatusTable1')
																.DataTable(
																		{

																			"dom" : dom1,
																			"responsive" : true,
																			/*
																			 * "processing" :
																			 * true,
																			 */
																			"serverSide" : false,
																			"bDestroy" : true,
																			"bPaginate" : true,
																			"pagging" : true,
																			"bProcessing" : true,
																			"ordering" : true,
																			"order" : [
																					0,
																					"desc" ],
																			"lengthMenu" : [
																					5,
																					10,
																					25,
																					30,
																					50,
																					75 ],
																			"pageLength" : 5,
																			"scrollY" : 324,
																			"scrollX" : false,
																			"data" : d.data,
																			"columns" : [
																					{
																						"data" : "communityName",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "blockName",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "houseNumber",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "meterID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "amount",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "emergencyCredit",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "alarmCredit",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "modeOfPayment",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "razorPayOrderID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "razorPayPaymentID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "razorPayRefundID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "RazorPayRefundStatus",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "transactionDate",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "transactedByUserName",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "transactedByRoleDescription",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "RazorPayPaymentStatus",
																						"defaultContent" : ""
																					},
																					{
																						"mData" : "action",
																						"render" : function(
																								data,
																								type,
																								row) {
																							if (row.Status == "Failed") {
																								return "<a onclick='getDeleteTransactionID("
																										+ row.transactionID
																										+ ")'>"
																										+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>delete</i>"
																										+ "</a>";

																							} else if (row.Status == "Passed"
																									|| row.Status == "Pending") {
																								return "<a onclick='getReceiptTransactionID("
																										+ row.transactionID
																										+ ")'>"
																										+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																										+ "</a>"
																							} else if (row.Status == "Pending...waiting for acknowledge") {
																								return "---"
																							}

																						}
																					} ],
																			"columnDefs" : [
																					{
																						// orderable
																						// :
																						// false,
																						targets : 11,
																						visible : (((sessionStorage
																								.getItem("roleID") == 1)
																								|| (sessionStorage
																										.getItem("roleID") == 2) || (sessionStorage
																								.getItem("roleID") == 3)) && (!(sessionStorage
																								.getItem("roleID") == 5) || !(sessionStorage
																								.getItem("roleID") == 4)))
																					},
																					{
																						"className" : "dt-center",
																						"targets" : "_all"
																					} ],
																			"buttons" : [
																					{
																						extend : 'excel',
																						footer : 'true',
																						//text : 'Excel',
																						//className: 'custom-btn fa fa-file-excel-o',
																						exportOptions : {
																							columns : [
																									0,
																									1,
																									2,
																									3,
																									4,
																									5,
																									6,
																									7,
																									8,
																									9,
																									10,
																									11,
																									12,
																									13,
																									14,
																									15 ]
																						},
																						title : 'ReCharge Status'
																					},

																					{
																						extend : 'pdf',
																						footer : 'true',
																						//className: 'custom-btn fa fa-file-excel-o',
																						exportOptions : {
																							columns : [
																									0,
																									1,
																									2,
																									3,
																									4,
																									5,
																									6,
																									7,
																									8,
																									9,
																									10,
																									11,
																									12,
																									13,
																									14,
																									15 ]
																						},
																						//text : 'pdf',
																						orientation : 'landscape',
																						title : 'ReCharge Status',
																						pageSize : 'LEGAL'
																					},
																					{
																						text : 'Reset',
																						action : function(
																								e,
																								dt,
																								node,
																								config) {
																							alert('Button activated');
																						},
																						className : 'customButton',

																						action : function(
																								e,
																								dt,
																								button,
																								config) {

																							window.location = "topupStatus.jsp"
																						}
																					} ],
																					 initComplete: function() {
																						   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																						   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																						  }
																		});
														if (sessionStorage
																.getItem("roleID") == 3) {
															table
																	.buttons(
																			$('a.customButton'))
																	.remove();
														}
														$("div.headname")
																.html(
																		'<h3>ReCharge Status</h3>');
													}
												});
										return false;
									});

					$("#resetFilter").on(function() {
						$("input:text").val("");
					});
				});

function getDeleteTransactionID(transID) {

	bootbox.confirm("ARE YOU SURE TO DELETE RECORD", function(result) {
		// alert(result);
		if (result == true) {
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "/PAYGTL_LORA_BLE/status/delete/" + transID,
				dataType : "JSON",
				success : function(data) {
					// alert("Success====" + data.result);
					if (data.result == "Success") {
						bootbox.confirm(data.Message, function(result) {
							window.location = "topupStatus.jsp";
						});

					} else {
						bootbox.alert(data.Message);
						return false;
					}
				}
			});
		} else if (result == false) {
			// alert("@"+false)

		}
	});
}

function getReceiptTransactionID(transID) {
	bootbox.confirm("ARE YOU SURE TO DOWNLOAD RECEIPT", function(result) {
		// alert(result);
		window.open("/PAYGTL_LORA_BLE/status/print/" + transID);
		/*
		 * if(result == true){ $.ajax({ type : "GET", contentType :
		 * "application/json", url : "/PAYGTL_LORA_BLE/status/print/" + transID,
		 * dataType : "JSON", success : function(data) { //alert("Success====" +
		 * data.result); if (data.result == "Success") { bootbox .confirm(
		 * data.Message, function( result) { window.location =
		 * "topupStatus.jsp"; });
		 *  } else { bootbox .alert(data.Message); return false; } } }); }else
		 * if(result==false){ //alert("@"+false)
		 *  }
		 */
	});
}
