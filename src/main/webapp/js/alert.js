/**
 * 
 */


$(document).ready(function() {
	
	if(sessionStorage.getItem("roleID") == 1){
		//$("#alertAddbutton").show();
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}else{
	/*	$("#alertAddbutton").remove();*/
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
		
	}
	
table = $('#alertTable')
.DataTable(
{
/*"processing" : false,*/
	"dom":dom1,
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
	console.log(JSON.stringify(json.responseText)+"json.data"+json);
	var json_data = JSON.parse(JSON.stringify(json.responseText));
	//Object.keys(json).length >0 ? $("")
	console.log(JSON.stringify(json_data.data));
	//console.log(Object.keys(json_data.data[0]).length);
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
"data" : "reconnectionCharges"
},{
"data" : "perUnitValue"
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
	//orderable : false,
	targets : 6, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
},
{
	"className": "dt-center", "targets": "_all"
}

],
initComplete: function() {
	   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
	   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
	   if($('#alertTable').DataTable().rows( 'tr' ).count() >= 1){
		   $("#alertAddbutton").remove();
	   }
}


});





$("div.headname").html('<h3>Alert Details</h3>');

$("div.addevent").html('<button type="button" id="alertAddbutton"'
		+'class="btn btn-raised btn-primary float-right"'
			+'data-toggle="modal" data-target="#exampleModal">'
		+'	<i class="fa fa-user"></i>'
		+'</button>');

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
														message : 'No AMR Interval is not valid',
														validators : {
															notEmpty : {
																message : 'No AMR Interval is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'No AMR Interval be more than 2 and less than 30 characters long'
															}
														}
													},
													lowbatteryvoltageAdd : {
														message : 'Low Battery Voltage is not valid',
														validators : {
															notEmpty : {
																message : 'Low Battery Voltage is required and cannot be empty'
															},
															stringLength : {
																min : 1,
																max : 3,
																message : 'Low Battery Voltage must be more than 1 and less than 3 characters long'
															},
															regexp : {
																regexp : /^[0-9]+$/,
																message : 'Low Battery Voltage can only consist of alphabetical and number'
															}
														}
													},
													rechargetimeoutAdd : {
														message : 'Recharge Timeout is not valid',
														validators : {
															notEmpty : {
																message : 'Recharge Timeout is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]+$/,
																message : 'Recharge Timeout can only consist of number'
															}
														}
													},
													reconnectionAdd : {
														message : 'ReConnection Charge is not valid',
														validators : {
															notEmpty : {
																message : 'ReConnection Charge is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]+$/,
																message : 'ReConnection Charge can only consist of number'
															}
														}
													},
													perUnitAdd : {
														message : 'Per Unit Charge is not valid',
														validators : {
															notEmpty : {
																message : 'Per Unit Charge is required and cannot be empty'
															},
															regexp : {
																regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
																message : 'Per Unit Charge can only consist of number'
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
												message : 'No AMR Interval is not valid',
												validators : {
													notEmpty : {
														message : 'No AMR Interval is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'No AMR Interval be more than 6 and less than 30 characters long'
													}
												}
											},
											lowbatteryvoltageEdit1 : {
												message : 'Low Battery Voltage is not valid',
												validators : {
													notEmpty : {
														message : 'Low Battery Voltage is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'Low Battery Voltage must be more than 6 and less than 30 characters long'
													}
													
												}
											},
											rechargetimeoutEdit1 : {
												message : 'Recharge Timeout is not valid',
												validators : {
													notEmpty : {
														message : 'Recharge Timeout is required and cannot be empty'
													}
												}
											},
											connectionEdit1 : {
												message : 'ReConnection Charge is not valid',
												validators : {
													notEmpty : {
														message : 'ReConnection Charge is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]+$/,
														message : 'ReConnection Charge can only consist of number'
													}
												}
											},
											perUnitEdit1 : {
												message : 'Per Unit Charge is not valid',
												validators : {
													notEmpty : {
														message : 'Per Unit Charge is required and cannot be empty'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'Per Unit Charge can only consist of number'
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
												data1["timeOut"] = $("#rechargetimeoutAdd").val();

												data1["reconnectionCharges"] = $("#reconnectionAdd").val();
												data1["perUnitValue"] = $("#perUnitAdd").val();
												
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
																
																if (data.result == "Success") {

																	/*alert( "data"
																			+ data.result);*/
																	
																	bootbox.alert(data.Message,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "alert.jsp";
																				});
																	return false
																	

																} else if(data.result == "Failure"){
																	
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
										data1["lowBatteryVoltage"] = $("#lowbatteryvoltageEdit1").val();
										data1["timeOut"] = $("#rechargetimeoutEdit1")
										.val();
								
										data1["reconnectionCharges"] = $("#connectionEdit1").val();
										data1["perUnitValue"] = $("#perUnitEdit1").val();
										
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
														
														if (data.result == "Success") {

															/*alert( "data"
																	+ data.result);*/
															
															bootbox.alert(data.Message,
																	function(
																			result) {
																			
																//alert();
																window.location = "alert.jsp";
																		});
															return false
															

														} else if(data.result == "Failure"){
															
															bootbox.alert(data.Message,
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
			//	alert(item.lowBatteryVoltage);
				$('#noamrintervalEdit').val(item.noAMRInterval).trigger("change");
				$("#formnoamrintervalEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#lowbatteryvoltageEdit1').val(item.lowBatteryVoltage).trigger("change");
				$("#formlowbatteryvoltageEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#rechargetimeoutEdit1').val(item.timeOut).trigger("change");
				$("#formrechargetimeoutEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			
				$('#connectionEdit1').val(item.reconnectionCharges).trigger("change");
				$("#formreconnectionEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#perUnitEdit1').val(item.perUnitValue).trigger("change");
				$("#formperUnitEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
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