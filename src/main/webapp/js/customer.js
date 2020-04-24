/**
 * 
 */


$(document).ready(function() {
table = $('#customerTable')
.DataTable(
{//'Pfrtip'
	"dom": "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f<br/>i>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-12'p<br/>i>>",
	"responsive" : true,
	"processing" : true,
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
{
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
"data" : "CRNNumber"
},{
"data" : "mobileNumber"
},{
"data" : "email"
},{
"data" : "meterSerialNumber"
},{
"data" : "meterID"
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
		
		return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit("
																	+ row.customerID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getCustomerFormDelete("
																	+ row.customerID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>delete</i>"
																	+ "</a>"
																	
																	
	}
	}



],
"columnDefs" : [ {
	orderable : false,
	targets : [ 0 ]
},
{
	orderable : false,
	targets : [ 1 ]
}], "buttons": [
   /* 'csvHtml5',
	'excelHtml5',
'pdfHtml5'*/
	
	/*{extend: 'excel',
        footer: 'true',
        text: 'Excel',
        title:'Statistics'  },
         
        {extend: 'pdf',
        footer: 'true',
        exportOptions: {
            columns: [1,2,3,4,5,6,7,8,9,10,11,12]
        },
        text: 'pdf',
        orientation: 'landscape',
        title:'Statistics'  }*/
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
													
													firstNameAdd : {
														message : 'The First Name is not valid',
														validators : {
															notEmpty : {
																message : 'The First Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'The First Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'The First Name can only consist of Alphanumaric'
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
																min : 4,
																max : 30,
																message : 'The Last Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'The Last Name can only consist of Alphanumaric'
															}
														}
													},
													houseNoAdd : {
														message : 'The House No. is not valid',
														validators : {
															notEmpty : {
																message : 'The House No is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'The House No must be more than 4 and less than 30 characters long'
															}
														}
													},
													mobileNoAdd : {
														message : 'The Mobile No. is not valid',
														validators : {
															notEmpty : {
																message : 'The Mobile No. is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'The Customer Mobile can only consist of number'
															}
														}
													},
													emailAdd : {
														message : 'The Email is not valid',
														validators : {
															notEmpty : {
																message : 'The Email is required and cannot be empty'
															}
														}
													},
													meterSerialAdd : {
														message : 'The Meter sr. No. is not valid',
														validators : {
															notEmpty : {
																message : 'The Meter sr. No. is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 15,
																message : 'The Meter Serial Number must be more than 4 and less than 15 characters long'
															},
															regexp : {
																regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
																message : 'The Meter Serial Number can only consist of Alphanumaric and Could not start with zero'
															}
														}
													},
													amrAdd : {
														message : 'The AMR No. is not valid',
														validators : {
															notEmpty : {
																message : 'The AMR No. is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 15,
																message : 'The AMR ID must be more than 4 and less than 15 characters long'
															},
															regexp : {
																regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
																message : 'The AMR ID can only consist of Alphanumaric and Could not start with zero'
															}
														}
													},
													
													selectTariffName: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select your Tariff language.'
									                        }
									                    }
									                },
									                CRNAdd : {
														message : 'The CRN is not valid',
														validators : {
															notEmpty : {
																message : 'The CRN is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'The CRN must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'The CRN can only consist of Alphanumaric'
															}
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
											
											firstNameEdit : {
												message : 'The First Name is not valid',
												validators : {
													notEmpty : {
														message : 'The First Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'The First Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'The First Name can only consist of Alphanumaric'
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
														min : 4,
														max : 30,
														message : 'The Last Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'The First Name can only consist of Alphanumaric'
													}
												}
											},
											houseNoEdit : {
												message : 'The House No. is not valid',
												validators : {
													notEmpty : {
														message : 'The House No is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'The House Number must be more than 4 and less than 30 characters long'
													}
												}
											},
											mobileNoEdit: {
												message : 'The Mobile No. is not valid',
												validators : {
													notEmpty : {
														message : 'The Mobile No. is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]{10}$/,
														message : 'The Customer Mobile can only consist of number'
													}
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
													},
													stringLength : {
														min : 4,
														max : 15,
														message : 'The Meter Serial Number must be more than 4 and less than 15 characters long'
													},
													regexp : {
														regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
														message : 'The Meter Serial Number can only consist of Alphanumaric and Could not start with zero'
													}
												}
											},
											amrEdit : {
												message : 'The AMR No. is not valid',
												validators : {
													notEmpty : {
														message : 'The AMR No. is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 15,
														message : 'The AMR ID must be more than 4 and less than 15 characters long'
													},
													regexp : {
														regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
														message : 'The AMR ID can only consist of Alphanumaric and Could not start with zero'
													}
												}
											},
											
											selectTariffNameEdit: {
							                    validators: {
							                        notEmpty: {
							                            message: 'Please select your Tariff language.'
							                        }
							                    }
							                },
							                CRNEdit : {
												message : 'The CRN is not valid',
												validators : {
													notEmpty : {
														message : 'The CRN is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'The CRN must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'The CRN can only consist of Alphanumaric'
													}
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

												if($("#selectcommunityName").val() == -1 || $("#selectcommunityName").val() == null || $("#selectcommunityName").val() == "Select Community"){
													bootbox
													.alert("Select Community Id");
													return false;
												} 
												
												if($("#selectBlockBasedonCommunity").val() == -1 || $("#selectBlockBasedonCommunity").val() == null || $("#selectBlockBasedonCommunity").val() == "Select Block"){
													bootbox
													.alert("Select Block Id");
													return false;
												}
												
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
												data1["loggedInRoleID"] = sessionStorage.getItem("roleID");
												
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
																}else {
																	
																	bootbox.alert(data.Message);
																	return false;
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
										data1["loggedInRoleID"] = sessionStorage.getItem("roleID");
								
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
															
														}else {
															
															bootbox.alert(data.Message);
															return false;
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


function getCustomerFormDelete(cust_id){
	
	
	
}
