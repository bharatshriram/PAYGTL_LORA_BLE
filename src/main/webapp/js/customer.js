/**
 * 
 */


$(document).ready(function() {
table = $('#customerTable')
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
"url":"/PAYGTL_LORA_BLE/customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
"type" : "GET",
"data" : function(search) {
},
"complete" : function(json) {
	console.log(json);
return json.data;
},
},
"columns" : [
 /*{
    	"data" : "communityName"
 },*/{
"data" : "communityName"
},{
"data" : "blockName"
},{
"data" : "firstName"
},{
"data" : "lastName"
},{
"data" : "houseNumber"
},{
"data" : "mobileNumber"
},{
"data" : "email"
},{
"data" : "meterSerialNumber"
},{
"data" : "meterID"
},{
"data" : "defaultReading"
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
		
		/*<button type="button"
			class="btn btn-raised btn-primary float-right"
			data-toggle="modal" data-target="#exampleModal">
			<i class="fa fa-user"></i>
		</button>*/
	//return "<a href='#communityEditModal' class='teal modal-trigger' data-toggle='modal' data-target='#communityEditModal' id='communityEditModal' onclick='getSocietyFormEdit("+row.communityID+")'><i class='material-icons' style='color:#17e9e9'>edit</i></a>"
		
		return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit("
																	+ row.customerID
																	+ ")'>"
																	+ "ABC"
																	+ "</a>"
	}
	}



]
});
});






$(document)
				.ready(
						function() {
							$('#customerDetails').bootstrapValidator(
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
													
													firstNameAdd : {
														message : 'The Customer Name is not valid',
														validators : {
															notEmpty : {
																message : 'The Customer Name is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The Customer Name must be more than 2 and less than 30 characters long'
															}
														}
													},
													lastNameAdd : {
														message : 'The Last Name is not valid',
														validators : {
															notEmpty : {
																message : 'The Last Name is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The Last Name must be more than 2 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Last Name can only consist of alphabetical and number'
															}
														}
													},
													houseNoAdd : {
														message : 'The House No. is not valid',
														validators : {
															notEmpty : {
																message : 'The House No is required and cannot be empty'
															}
														}
													},
													mobileNoAdd : {
														message : 'The Mobile No. is not valid',
														validators : {
															notEmpty : {
																message : 'The Mobile No. is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													},
													emailAdd : {
														message : 'The Email is not valid',
														validators : {
															notEmpty : {
																message : 'The Email is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													},
													meterSerialAdd : {
														message : 'The Meter sr. No. is not valid',
														validators : {
															notEmpty : {
																message : 'The Meter sr. No. is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													},
													amrAdd : {
														message : 'The AMR No. is not valid',
														validators : {
															notEmpty : {
																message : 'The AMR No. is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													},
													
													selectTariffName: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select your Tariff language.'
									                        }
									                    }
									                },
													
													defaultReadingAdd : {
														message : 'The Default Reading is not valid',
														validators : {
															notEmpty : {
																message : 'The Default Reading is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													}
												}
											});
							
							
							
							
							$('#customerEdit')
							.bootstrapValidator(
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
							                blockNameEdit: {
							                    validators: {
							                        notEmpty: {
							                            message: 'Please select your native language.'
							                        }
							                    }
							                },
											
											firstNameEdit : {
												message : 'The Customer Name is not valid',
												validators : {
													notEmpty : {
														message : 'The Customer Name is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'The Customer Name must be more than 2 and less than 30 characters long'
													}
												}
											},
											lastNameEdit : {
												message : 'The Last Name is not valid',
												validators : {
													notEmpty : {
														message : 'The Last Name is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'The Last Name must be more than 2 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'The Last Name can only consist of alphabetical and number'
													}
												}
											},
											houseNoEdit : {
												message : 'The House No. is not valid',
												validators : {
													notEmpty : {
														message : 'The House No is required and cannot be empty'
													}
												}
											},
											mobileNoEdit: {
												message : 'The Mobile No. is not valid',
												validators : {
													notEmpty : {
														message : 'The Mobile No. is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'The Community Address can only consist of alphabetical and number'
													}*/
												}
											},
											emailEdit : {
												message : 'The Email is not valid',
												validators : {
													notEmpty : {
														message : 'The Email is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'The Community Address can only consist of alphabetical and number'
													}*/
												}
											},
											meterSerialEdit : {
												message : 'The Meter sr. No. is not valid',
												validators : {
													notEmpty : {
														message : 'The Meter sr. No. is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'The Community Address can only consist of alphabetical and number'
													}*/
												}
											},
											amrEdit : {
												message : 'The AMR No. is not valid',
												validators : {
													notEmpty : {
														message : 'The AMR No. is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'The Community Address can only consist of alphabetical and number'
													}*/
												}
											},
											
											selectTariffNameEdit: {
							                    validators: {
							                        notEmpty: {
							                            message: 'Please select your Tariff language.'
							                        }
							                    }
							                },
											
											defaultReadingEdit : {
												message : 'The Default Reading is not valid',
												validators : {
													notEmpty : {
														message : 'The Default Reading is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'The Community Address can only consist of alphabetical and number'
													}*/
												}
											}
										}
									});
							
							
							
							

							$('#customerDetails')
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

							
							
							
							$('#customerEdit').on(
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
											$('#customerEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#customerEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							$("#customerAdd")
									.click(
											function() {

												var data1 = {}
												data1["communityID"] = $("#selectcommunityName").val();
												data1["blockID"] = $("#selectBlockBasedonCommunity").val();
												data1["firstName"] = $("#firstNameAdd").val();
												data1["lastName"] = $("#lastNameAdd").val();
												data1["houseNumber"] = $("#houseNoAdd").val();
												data1["mobileNumber"] = $("#mobileNoAdd").val();
												data1["email"] = $("#emailAdd").val();
												data1["meterSerialNumber"] = $("#meterSerialAdd").val();
												data1["meterID"] = $("#amrAdd").val();
												data1["tariffID"] = $("#selectTariffName").val();
												data1["defaultReading"] = $("#defaultReadingAdd").val();
												data1["CRNNumber"] = $("#CRNAdd").val();
												data1["createdByID"] = sessionStorage.getItem("createdByID");
												data1["loggedInUserID"] = sessionStorage.getItem("userID");
												data1["roleID"] = sessionStorage.getItem("roleID");
												
												alert("===>"
														+ JSON.stringify(data1));
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "/PAYGTL_LORA_BLE/customer/add",
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
																		window.location = "customerDetails.jsp";
																		return false
																	});
																	
																	

																} else if(data.result == "Customer Registered Successfully but due to internal server Error Credentials have not been sent to your registered Mail ID. Please Contact Administrator"){
									
																	bootbox.alert(data.result,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "customerDetails.jsp";
																		return false
																				});
																	
																	
																}else if(data.result == "Failure"){
																	
																	bootbox.alert(data.result,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "customerDetails.jsp";
																		return false
																				});
																}
															}
														});
												return false;
											});
							
							
							
							$("#customerEditsave")
							.click(
									function() {

										var data1 = {}
										
										data1["firstName"] = $("#firstNameEdit").val();
										data1["houseNumber"] = $("#houseNoEdit").val();
										data1["mobileNumber"] = $("#mobileNoEdit").val();
										data1["email"] = $("#emailEdit").val();
										data1["meterID"] = $("#amrEdit").val();
										data1["tariffID"] = $("#selectTariffNameEdit").val();
										data1["defaultReading"] = $("#defaultReadingEdit").val();
										data1["createdByID"] = sessionStorage.getItem("createdByID");
										data1["loggedInUserID"] = sessionStorage.getItem("userID");
										data1["roleID"] = sessionStorage.getItem("roleID");
								
										alert("===>"
												+ JSON.stringify(data1));
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/customer/edit/"+$("#customerIdhidden").val(),
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
																window.location = "customerDetails.jsp";
																return false
															});
														} else if(data.result == "Failure"){
															
															bootbox.alert(data.result,
																	function(
																			result) {
																			
																//alert();
																window.location = "customerDetails.jsp";
																return false
																
																		});
															
														}
													}
												});
										return false;
									});
							
							
						});




function getCustomerFormEdit(id) {

//	 alert(id);

	$.getJSON("/PAYGTL_LORA_BLE/customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.customerID) {
				$('#communityNameEdit').val(item.communityName).trigger("change");
				$("#formcommunityNameEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#blockNameEdit').val(item.blockName).trigger("change");
				$("#formblockNameEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#firstNameEdit').val(item.firstName).trigger("change");
				$("#formfirstNameEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#lastNameEdit').val(item.lastName).trigger("change");
				$("#formlastNameEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#houseNoEdit').val(item.houseNumber).trigger("change");
				$("#formhouseNoEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$('#mobileNoEdit').val(item.mobileNumber).trigger("change");
				$("#formmobileNoEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$('#emailEdit').val(item.email).trigger("change");
				$("#formemailEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$('#meterSerialEdit').val(item.meterSerialNumber).trigger("change");
				$("#formmeterSerialEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$('#amrEdit').val(item.meterID).trigger("change");
				$("#formamrEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")

				$('#defaultReadingEdit').val(item.defaultReading).trigger("change");
				$("#formdefaultReadingEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#CRNEdit').val(item.mobileNumber).trigger("change");
				$("#formCRNEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			    
				$("#customerIdhidden").val(item.customerID);
			
				$('#customerEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myCustomerEdit').modal('show');
	});
}