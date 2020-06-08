/**
 * 
 */


$(document).ready(function() {
	
	if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 2){
		if(sessionStorage.getItem("roleID") == 2){
			$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
			$("#formcommunityNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
			$("#formblockNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		}
		$("#blockAddButton").show();
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}else{
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}
	$("#customerTable1").hide();
table = $('#customerTable')
.DataTable(
{
	"dom": dom1,
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
	"scrollX" : true,
"ajax" : {
"url":"/PAYGTL_LORA_BLE/customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1",
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
																	+ row.CRNNumber
																	+ "\")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getCustomerFormDelete(\""
																	+ row.CRNNumber
																	+ "\")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																	+ "</a>"
																	
																	
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
	targets : 13, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4))),
},{
	targets : 14, visible: (sessionStorage.getItem("roleID") == 3)
//(!(sessionStorage.getItem("roleID") == 1) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4))))
},
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
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

$("div.headname").html('<h3>Customer Managemnent</h3>');

if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
	table.buttons( $('a.customButton') ).remove();	
}

$("div.addevent").html('<button type="button" id="customerAddd"'
		+'class="btn btn-raised btn-primary float-right"'
			+'data-toggle="modal" data-target="#exampleModal">'
		+'<i class="fa fa-user-plus"></i>'
		+'</button>');

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
																message : 'The Mobile No. can only consist of number'
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
														message : 'The MSN is not valid',
														validators : {
															notEmpty : {
																message : 'The MSN is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 15,
																message : 'The MSN must be more than 4 and less than 15 characters long'
															},
															regexp : {
																regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
																message : 'The MSN can only consist of Alphanumaric and Could not start with zero'
															}
														}
													},
													amrAdd : {
														message : 'The MIU ID. is not valid',
														validators : {
															notEmpty : {
																message : 'The MIU ID is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 16,
																message : 'The MIU ID must be more than 4 and less than 16 characters long'
															},
															regexp : {
																regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
																message : 'The MIU ID can only consist of Alphanumaric and Could not start with zero'
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
														message : 'The CRN No. is not valid',
														validators : {
															notEmpty : {
																message : 'The CRN No. is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'The CRN No. must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$;]+$/,
																message : 'The CRN No. can only consist of Alphanumaric'
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
														message : 'The House No. must be more than 4 and less than 30 characters long'
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
														message : 'The Mobile No. can only consist of number'
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
												message : 'The MSN is not valid',
												validators : {
													notEmpty : {
														message : 'The MSN is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 15,
														message : 'The MSN must be more than 4 and less than 15 characters long'
													},
													regexp : {
														regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
														message : 'The MSN can only consist of Alphanumaric and Could not start with zero'
													}
												}
											},
											amrEdit : {
												message : 'The MIU ID is not valid',
												validators : {
													notEmpty : {
														message : 'The MIU ID is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 16,
														message : 'The MIU ID must be more than 4 and less than 16 characters long'
													},
													regexp : {
														regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
														message : 'The MIU ID can only consist of Alphanumaric and Could not start with zero'
													}
												}
											},
											
											/*selectTariffNameEdit: {
							                    validators: {
							                        notEmpty: {
							                            message: 'Please select your Tariff language.'
							                        }
							                    }
							                },*/
							                CRNEdit : {
												message : 'The CRN No. is not valid',
												validators : {
													notEmpty : {
														message : 'The CRN No. is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'The CRN No. must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$;]+$/,
														message : 'The CRN No. can only consist of Alphanumaric'
													}
												}
											}
										}
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
													url : "/PAYGTL_LORA_BLE/customer/"+url,
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
							
												$(document).on('click', '#customerAdd', function () {
													if(sessionStorage.getItem("roleID") != 2){
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
}			
												let communityId = sessionStorage.getItem("roleID") == 2 ? sessionStorage.getItem("communityID") : $("#selectcommunityName").val();
												let blockId = sessionStorage.getItem("roleID") == 2 ? sessionStorage.getItem("ID") : $("#selectBlockBasedonCommunity").val();
												
												var data1 = {}
												data1["communityID"] = communityId;
												data1["blockID"] = blockId;
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
												
												/*alert("===>"
														+ JSON.stringify(data1));*/
												
												$('#customerAdd').prop('disabled', true).addClass('disabled').off( "click" );
												
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
																		window.location = "customerDetails.jsp";
																		return false
																	});
																	
																	

																} else if(data.result == "Failure"){
																	
																	bootbox.alert(data.Message);
																	return false;
																				//});
																}else {
																	
																	bootbox.alert(data.Message);
																	return false;
																}
															}
														});
												return false;
											});
							
							
										$(document).on('click', '#customerEditsave', function () {
											
										var data1 = {}
										
										data1["firstName"] = $("#firstNameEdit").val();
										//data1["houseNumber"] = $("#houseNoEdit").val();
										data1["mobileNumber"] = $("#mobileNoEdit").val();
										data1["email"] = $("#emailEdit").val();
										data1["CRNNumber"] = $("#CRNEdit").val();
										data1["houseNumber"] = $("#houseNoEdit").val();
										if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 2 ){
											data1["meterID"] = $("#amrEdit").val();
											
											//data1["tariffID"] = $("#selectTariffNameEdit").val();	
										}
										data1["createdByID"] = sessionStorage.getItem("createdByID");
										data1["loggedInUserID"] = sessionStorage.getItem("userID");
										data1["loggedInRoleID"] = sessionStorage.getItem("roleID");
										$('#customerEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/customer/edit/"+$("#CRNEdit").val(),
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
																location.reload();		
																//alert();
																//window.location = "customerDetails.jsp";
																return false
															});
														} else if(data.result == "Failure" && data.Message == undefined){
															
															bootbox.alert(data.Message);
															return false;
															
														}else {
															
															bootbox.alert(data.Message);
															return false;
															//return false;
														}
													}
												});
										return false;
									});
							
							
						});




function getCustomerFormEdit(id) {


	$.getJSON("/PAYGTL_LORA_BLE/customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.CRNNumber) {
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
				
				if(sessionStorage.getItem("roleID") == 3){
					$('#amrEdit')
					.attr('disabled',
							true);
				}
				$('#CRNEdit').val(item.CRNNumber).trigger("change");
				$("#formCRNEdit").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			    
				$("#customerIdhidden").val(item.CRNNumber);
			
				$('#customerEditsave')
				.attr('disabled',
						false);
				if(sessionStorage.getItem("roleID") == 3){
				
					$('#amrEdit', '#amrEdit')
					.attr('disabled',
							true);
					$('#houseNoEdit')
					.attr('disabled',
							true);
					
				}
				
				
			} else {
			}
		});
		$('#myCustomerEdit').modal('show');
	});
}


function getCustomerFormDelete(CRNNumber){
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELETE CUSTOMER",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/PAYGTL_LORA_BLE/customer/delete/" + CRNNumber,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "customerDetails.jsp";
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
