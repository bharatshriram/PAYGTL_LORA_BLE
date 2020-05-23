/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		$("#tariffAddd").show();
	}else{
		$("#tariffAddd").remove();
		
	}
	
	//alert(!(sessionStorage.getItem("roleID") == 4))
	
table = $('#tariffTable')
.DataTable(
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
	"scrollX" : true,
"ajax" : {
"url":"/PAYGTL_LORA_BLE/tariff",
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
"data" : "tariffName"
},{
"data" : "tariff"
},{
"data" : "emergencyCredit"
},{
"data" : "alarmCredit"
},{
"data" : "fixedCharges"
},{
"data" : "modifiedDate"
},{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<a href=# id=TariffEdit data-toggle=modal data-target=#myTariffEdit onclick='getTariffFormEdit("
																	+ row.tariffID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a> "
																	+"<a onclick='getDeleteTraiff("
																	+ row.tariffID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9; cursor:pointer;'>delete</i>"
																	+ "</a></div>"
	}
	}
],
"columnDefs" : [ {
//	orderable : false,
	targets: 6, visible: !(sessionStorage.getItem("roleID") == 4)
},
{
	"className": "dt-center", "targets": "_all"
}
], "buttons": [
   
]
});

$("div.headname").html('<h3>Tariff Details</h3>');

$("div.addevent").html('<button type="button" id="tariffAddd" class="btn btn-raised btn-primary" data-toggle="modal" data-target="#exampleModal"> <i class="fa fa-user-plus"></i></button>');

});






$(document)
				.ready(
						function() {
							$('#tariffDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													tariffNameAdd : {
														message : 'The Name is not valid',
														validators : {
															notEmpty : {
																message : 'The Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'The Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]*$/,
																message : 'The Name can only consist of alphabet'
															}
														}
													},
													tariffRateAdd : {
														message : 'The Rate is not valid',
														validators : {
															notEmpty : {
																message : 'The Rate is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The Rate must be more than 2 and less than 30 characters long'
															},
															regexp : {
																regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
																message : 'The Rate can only consist of alphabetical and number'
															}
														}
													},
													emergencyCreditAdd : {
														message : 'The Emergency Credit is not valid',
														validators : {
															notEmpty : {
																message : 'The Emergency Credit is required and cannot be empty'
															},stringLength : {
																min : 2,
																max : 30,
																message : 'The Emergency Credit must be more than 2 and less than 30 characters long'
															},
															regexp : {
																regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
																message : 'The Emergency Credit can only consist of number'
															}
														}
													},
													alarmCreditAdd : {
														message : 'The Alaram Credit is not valid',
														validators : {
															notEmpty : {
																message : 'The Alaram Credit is required and cannot be empty'
															},stringLength : {
																min : 2,
																max : 30,
																message : 'The Alaram Credit must be more than 2 and less than 30 characters long'
															},
															regexp : {
																regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
																message : 'The Alarm Credit can only consist of number'
															}
														}
													},
													fixedChargeAdd : {
														message : 'The Fixed Charge is not valid',
														validators : {
															notEmpty : {
																message : 'The Fixed Charge is required and cannot be empty'
															},stringLength : {
																min : 2,
																max : 30,
																message : 'The Fixed Charge must be more than 2 and less than 30 characters long'
															},
															regexp : {
																regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
																message : 'The Fixed Charge can only consist of number'
															}
														}
													}
												}
											});
							
							
							
							
							$('#tariffEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											tariffNameEdit : {
												message : 'The Name is not valid',
												validators : {
													notEmpty : {
														message : 'The Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'The Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z ]*$/,
														message : 'The Name can only consist of alphabet'
													}
												}
											},
											tariffRateEdit : {
												message : 'The Rate is not valid',
												validators : {
													notEmpty : {
														message : 'The Rate is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'The Rate must be more than 2 and less than 30 characters long'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'The Rate can only consist of alphabetical and number'
													}
												}
											},
											emergencyCreditEdit : {
												message : 'The Emergency Credit is not valid',
												validators : {
													notEmpty : {
														message : 'The Emergency Credit is required and cannot be empty'
													},stringLength : {
														min : 2,
														max : 30,
														message : 'The Emergency Credit must be more than 2 and less than 30 characters long'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'The Emergency Credit can only consist of number'
													}
												}
											},
											alarmCreditEdit : {
												message : 'The Alaram Credit is not valid',
												validators : {
													notEmpty : {
														message : 'The Alaram Credit is required and cannot be empty'
													},stringLength : {
														min : 2,
														max : 30,
														message : 'The Alaram Credit must be more than 2 and less than 30 characters long'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'The Alarm Credit can only consist of number'
													}
												}
											},
											fixedChargeEdit : {
												message : 'The Fixed Charge is not valid',
												validators : {
													notEmpty : {
														message : 'The Fixed Charge is required and cannot be empty'
													},stringLength : {
														min : 2,
														max : 30,
														message : 'The Fixed Charge must be more than 2 and less than 30 characters long'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'The Fixed Charge can only consist of number'
													}
												}
											}
										}
									});
							
							
							
							
							
							
							
							$('#tariffDetails')
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
							
							
							
							
							$('#tariffEdit').on(
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
											$('#tariffEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#tariffEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							

												$(document).on('click', '#tariffAdd', function () {

												var data1 = {}
												data1["tariffName"] = $("#tariffNameAdd").val();
												data1["tariff"] = $("#tariffRateAdd").val();
												data1["emergencyCredit"] = $("#emergencyCreditAdd").val();
												data1["alarmCredit"] = $("#alarmCreditAdd").val();
												data1["fixedCharges"] = $("#fixedChargeAdd").val();
										
											/*	alert("===>"
														+ JSON.stringify(data1));*/
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "/PAYGTL_LORA_BLE/tariff/add",
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
																	//	window.location = "tariff.jsp";
																		return false
																	});
																	

																} else {
																	bootbox.alert(data.Message);
																	return false;

																}
															}
														});
												return false;
											});
							
							
							
							
										$(document).on('click', '#tariffEditsave', function () {

										var data1 = {}
										
										data1["tariffName"] = $("#tariffNameEdit").val();
										data1["tariff"] = $("#tariffRateEdit").val();
										data1["emergencyCredit"] = $("#emergencyCreditEdit").val();
										data1["alarmCredit"] = $("#alarmCreditEdit").val();
										data1["fixedCharges"] = $("#fixedChargeEdit").val();
								
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/tariff/edit/"+$("#tariffIdhidden").val(),
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
															
															bootbox.alert("Updated Succesfully!",
																	function(
																			result) {
																			
																//alert();
																window.location = "tariff.jsp";
																		});
															return false
															

														} else if(data.result == "Failure"){
															
															bootbox.alert(data.Message,
																	function(
																			result) {
																			
																//alert();
																window.location = "tariff.jsp";
																
																		});
															return false
														}else {
															bootbox.alert(data.Message);
															return false;

														}
													}
												});
										return false;
									});
							
							
							
						});


function getTariffFormEdit(id) {

//	 alert(id);

	$.getJSON("/PAYGTL_LORA_BLE/tariff", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.tariffID) {
				$('#tariffNameEdit').val(item.tariffName).trigger("change");
				$("#formtariffName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#tariffRateEdit').val(item.tariff).trigger("change");
				$("#formtariffRate").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#emergencyCreditEdit').val(item.emergencyCredit).trigger("change");
				$("#formemergencyCredit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#alarmCreditEdit').val(item.alarmCredit).trigger("change");
				$("#formalarmCredit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#fixedChargeEdit').val(item.fixedCharges).trigger("change");
				$("#formfixedCharge").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				$("#tariffIdhidden").val(item.tariffID);
			
				$('#tariffEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myTariffEdit').modal('show');
	});
}


function  getDeleteTraiff(id){
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELEE TARIFF",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/PAYGTL_LORA_BLE/tariff/delete/" + id,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "tariff.jsp";
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
	/*
	
*/	
	
}