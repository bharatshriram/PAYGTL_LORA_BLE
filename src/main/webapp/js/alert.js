/**
 * 
 */


$(document).ready(function() {
table = $('#alertTable')
.DataTable(
{
/*"processing" : false,*/
"serverSide" : false,
"bDestroy" : true,
"pagging" : true,
"bProcessing" : false,
"ordering" : true,
"order" : [ 0, "desc" ],
"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
"pageLength" : "5",
"scrollY" : 324,
"scrollX" : true,
"ajax" : {
"url":"/PAYGTL_LORA_BLE/alert",
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
"data" : "noAMRInterval"
},{
"data" : "lowBatteryVoltage"
},{
"data" : "timeOut"
},{
"data" : "registeredDate"
},{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		/*return "<a href=# id=alertEdit data-toggle=modal data-target=#myAlertEdit onclick='getAlertFormEdit("
																	+ row.alertID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"*/
																	
																	return "<a href=# id=alertEdit data-toggle=modal data-target=#myAlertEdit onclick='getAlertFormEdit("
																	+ row.alertID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
	}
	}



],
"columnDefs" : [ {
	orderable : false,
	targets : 4, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
},
{
	orderable : false,
	targets : [ 1 ]
}]
});
});






$(document)
				.ready(
						function() {
							$('#alertDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													noamrintervalAdd : {
														message : 'The No AMR Interval is not valid',
														validators : {
															notEmpty : {
																message : 'The No AMR Interval is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The No AMR Interval be more than 6 and less than 30 characters long'
															}
														}
													},
													lowbatteryvoltageAdd : {
														message : 'The Low Battery Voltage is not valid',
														validators : {
															notEmpty : {
																message : 'The Low Battery Voltage is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The Low Battery Voltage must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Low Battery Voltage can only consist of alphabetical and number'
															}
														}
													},
													rechargetimeoutAdd : {
														message : 'The Recharge Timeout is not valid',
														validators : {
															notEmpty : {
																message : 'The Recharge Timeout is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]+$/,
																message : 'The Recharge Timeout can only consist of alphabetical and number'
															}
														}
													}
												}
											});
							
							
							
							
							$('#alertEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											noamrintervalEdit : {
												message : 'The No AMR Interval is not valid',
												validators : {
													notEmpty : {
														message : 'The No AMR Interval is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'The No AMR Interval be more than 6 and less than 30 characters long'
													}
												}
											},
											lowbatteryvoltageEdit : {
												message : 'The Low Battery Voltage is not valid',
												validators : {
													notEmpty : {
														message : 'The Low Battery Voltage is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'The Low Battery Voltage must be more than 6 and less than 30 characters long'
													}
													
												}
											},
											rechargetimeoutEdit : {
												message : 'The Recharge Timeout is not valid',
												validators : {
													notEmpty : {
														message : 'The Recharge Timeout is required and cannot be empty'
													}
												}
											}
										}
									});
							
							
							
							

							$('#alertDetails')
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

							
							
							
							$('#alertEdit').on(
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
											$('#alertEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#alertEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							$("#alertAdd")
									.click(
											function() {

												var data1 = {}
												data1["noAMRInterval"] = $("#noamrintervalAdd")
														.val();
												data1["lowBatteryVoltage"] = $("#lowbatteryvoltageAdd").val();
												data1["timeOut"] = $("#rechargetimeoutAdd")
												.val();

												alert("===>"
														+ JSON.stringify(data1));
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "/PAYGTL_LORA_BLE/alert/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {
																alert("data"
																		+ JSON
																				.stringify(data));
																if (data.result == "Success") {

																	/*alert( "data"
																			+ data.result);*/
																	
																	bootbox.alert("Added Succesfully!",
																			function(
																					result) {
																					
																		//alert();
																		window.location = "alert.jsp";
																				});
																	return false
																	

																} else if(data.Message == "SETTINGS ARE ALREADY ADDED"){
																	
																	bootbox.alert(data.Message,
																			function(
																					result) {
																					
																		//alert();
																		//window.location = "alert.jsp";
																		
																				});
																	//return false
																}
															}
														});
												return false;
											});
							
							
							
							$("#alertEditsave")
							.click(
									function() {

										var data1 = {}
										
										data1["noAMRInterval"] = $("#noamrintervalEdit")
										.val();
										data1["lowBatteryVoltage"] = $("#lowbatteryvoltageEdit").val();
										data1["timeOut"] = $("#rechargetimeoutEdit")
										.val();
								
										alert("===>"
												+ JSON.stringify(data1));
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/alert/edit/"+$("#alertIdhidden").val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														alert("data"
																+ JSON
																		.stringify(data));
														if (data.result == "Success") {

															/*alert( "data"
																	+ data.result);*/
															
															bootbox.alert("Updated Succesfully!",
																	function(
																			result) {
																			
																//alert();
																window.location = "alert.jsp";
																		});
															return false
															

														} else if(data.result == "Failure"){
															
															bootbox.alert(data.result,
																	function(
																			result) {
																			
																//alert();
																window.location = "alert.jsp";
																
																		});
															return false
														}
													}
												});
										return false;
									});
							
							
						});




function getAlertFormEdit(id) {

//	 alert(id);

	$.getJSON("/PAYGTL_LORA_BLE/alert", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.alertID) {
				alert(item.lowBatteryVoltage);
				$('#noamrintervalEdit').val(item.noAMRInterval).trigger("change");
				$("#formnoamrintervalEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#lowbatteryvoltageEdit').val(item.lowBatteryVoltage).trigger("change");
				$("#formlowbatteryvoltageEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#rechargetimeoutEdit').val(item.timeOut).trigger("change");
				$("#formrechargetimeoutEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$("#alertIdhidden").val(item.alertID);
			
				$('#alertEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myAlertEdit').modal('show');
	});
}