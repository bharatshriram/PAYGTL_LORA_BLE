/**
 * 
 */


$(document).ready(function() {
table = $('#tariffTable')
.DataTable(
{
"processing" : false,
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
"data" : "RegisteredDate"
}
]
});
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
														message : 'The Tariff Name is not valid',
														validators : {
															notEmpty : {
																message : 'The Tariff Name is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The Tariff Name must be more than 6 and less than 30 characters long'
															}
														}
													},
													tariffRateAdd : {
														message : 'The Tariff Rate is not valid',
														validators : {
															notEmpty : {
																message : 'The Tariff Rate is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The Tariff Rate must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Tariff Rate can only consist of alphabetical and number'
															}
														}
													},
													emergencyCreditAdd : {
														message : 'The Emergency Credit is not valid',
														validators : {
															notEmpty : {
																message : 'The Emergency Credit is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]+$/,
																message : 'The Emergency Credit can only consist of alphabetical and number'
															}
														}
													},
													alarmCreditAdd : {
														message : 'The Alaram Credit is not valid',
														validators : {
															notEmpty : {
																message : 'The Alaram Credit is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													},
													fixedChargeAdd : {
														message : 'The Fixed Charge is not valid',
														validators : {
															notEmpty : {
																message : 'The Fixed Charge is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
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

							$("#tariffAdd")
									.click(
											function() {

												var data1 = {}
												data1["tariffName"] = $("#tariffNameAdd").val();
												data1["tariff"] = $("#tariffRateAdd").val();
												data1["emergencyCredit"] = $("#emergencyCreditAdd").val();
												data1["alarmCredit"] = $("#alarmCreditAdd").val();
												data1["fixedCharges"] = $("#fixedChargeAdd").val();
										
												alert("===>"
														+ JSON.stringify(data1));
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
																		window.location = "tariff.jsp";
																		return false
																	});
																	

																} else if(data.Message == "TARIFF AMOUNT ALREADY EXISTS"){
																	
																	bootbox.alert(data.Message,
																			function(
																					result) {
																					
																				});
																	
																}
															}
														});
												return false;
											});
							
						});



