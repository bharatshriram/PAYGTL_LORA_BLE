/**
 * 
 */


$(document).ready(function() {
table = $('#communityTable')
.DataTable(
{
	
	/*"initComplete": function(settings, json) {            
	    if (sessionStorage.getItem("roleID") == 1){
	        oTable.columns([0,1,3,4]).visible(false);
	        oTable.columns([]).visible(true);
	    }
	    else if (sessionStorage.getItem("roleID") == 4){
	        oTable.columns([0,1,3]).visible(false);
	        oTable.columns([4]).visible(true);
	    }
	},*/

	"dom": "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f<br/>i>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-12'p<br/>i>>",
	"responsive" : true,
	"processing" : true,
	"serverSide" : true,
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
"url":"/PAYGTL_LORA_BLE/community/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
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
"data" : "address"
},{
"data" : "email"
},{
"data" : "mobileNumber"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<div id=tdfiled><a href=# id=CommunityEdit data-toggle=modal data-target=#myCommunityEdit onclick='getCommunityFormEdit("
																	+ row.communityID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a> <a href=# id=CommunityEdit data-toggle=modal data-target=#myCommunityEdit onclick='getCommunityFormEdit("
																	+ row.communityID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>pageview</i>"
																	+ "</a></div>"
	}
	}



],
"columnDefs" : [ {
	orderable : false,
	//targets : [ 0 ],
	targets: 4, visible: (!(sessionStorage.getItem("roleID") == 4) || !(sessionStorage.getItem("roleID") == 5))
	
},
{
	orderable : false,
	targets : [ 1 ]
}], "buttons": [
   
]
});

});






$(document)
				.ready(
						function() {							/*alert(sessionStorage.getItem("roleID"));
							if(sessionStorage.getItem("roleID") == 1){
								$("#thfiled,#tdfiled").show();
							}else if(sessionStorage.getItem("roleID") == 4){
								$("#thfiled").hide();
								$("#tdfiled").hide();
								
							}*/
								$('#communityDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													communityNameAdd : {
														message : 'The Community Name is not valid',
														validators : {
															notEmpty : {
																message : 'The Community Name is required and cannot be empty'
															},
															stringLength : {
																min : 6,
																max : 30,
																message : 'The Community Name must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]*$/,
																message : 'The Tariff Name can only consist of alphabet'
															}
														}
													},
													communityAddressAdd : {
														message : 'The Community Address is not valid',
														validators : {
															notEmpty : {
																message : 'The Community Address is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The Community Address must be more than 6 and less than 30 characters long'
															}
														}
													},
													communityMobileAdd : {
														message : 'The Community Mobile is not valid',
														validators : {
															notEmpty : {
																message : 'The Community Mobile is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'The Community Mobile can only consist of number'
															}
														}
													},
													communityEmailAdd : {
														message : 'The Community Email is not valid',
														validators : {
															notEmpty : {
																message : 'The Community Email is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													}
												}
											});
							
							
							
							
							$('#communityEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											communityNameEdit : {
												message : 'The Community Name is not valid',
												validators : {
													notEmpty : {
														message : 'The Community Name is required and cannot be empty'
													},
													stringLength : {
														min : 6,
														max : 30,
														message : 'The Community Name must be more than 6 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z ]*$/,
														message : 'The Community Name can only consist of alphabet'
													}
												}
											},
											communityAddressEdit : {
												message : 'The Community Address is not valid',
												validators : {
													notEmpty : {
														message : 'The Community Address is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'The Community Address must be more than 6 and less than 30 characters long'
													}
												}
											},
											communityMobileEdit : {
												message : 'The Community Mobile is not valid',
												validators : {
													notEmpty : {
														message : 'The Community Mobile is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]{10}$/,
														message : 'The Community Mobile can only consist of number'
													}
												}
											},
											communityEmailEdit : {
												message : 'The Community Email is not valid',
												validators : {
													notEmpty : {
														message : 'The Community Email is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'The Community Address can only consist of alphabetical and number'
													}*/
												}
											}
										}
									});
							
							
							
							

							$('#communityDetails')
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

							
							
							
							$('#communityEdit').on(
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
											$('#communityEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#communityEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							$("#communityAdd")
									.click(
											function() {

												var data1 = {}
												data1["communityName"] = $("#communityNameAdd")
														.val();
												data1["email"] = $("#communityEmailAdd").val();
												data1["mobileNumber"] = $("#communityMobileAdd")
												.val();
												data1["address"] = $("#communityAddressAdd").val();
										
												alert("===>"
														+ JSON.stringify(data1));
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "/PAYGTL_LORA_BLE/community/add",
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
																		window.location = "communityDetails.jsp";
																				});
																	return false
																	

																} else if(data.result == "Failure"){
																	
																	bootbox.alert(data.result,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "communityDetails.jsp";
																		
																				});
																	return false
																}
															}
														});
												return false;
											});
							
							
							
							$("#communityEditsave")
							.click(
									function() {

										var data1 = {}
										
										var data1 = {}
										data1["communityName"] = $("#communityNameEdit")
												.val();
										data1["email"] = $("#communityEmailEdit").val();
										data1["mobileNumber"] = $("#communityMobileEdit")
										.val();
										data1["address"] = $("#communityAddressEdit").val();
								
										alert("===>"
												+ JSON.stringify(data1));
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/community/edit/"+$("#communityIdhidden").val(),
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
																window.location = "communityDetails.jsp";
																		});
															return false
															

														} else if(data.result == "Failure"){
															
															bootbox.alert(data.result,
																	function(
																			result) {
																			
																//alert();
																window.location = "communityDetails.jsp";
																
																		});
															return false
														}
													}
												});
										return false;
									});
							
							
						});




function getCommunityFormEdit(id) {

//	 alert(id);

	$.getJSON("/PAYGTL_LORA_BLE/community/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.communityID) {
				$('#communityNameEdit').val(item.communityName).trigger("change");
				$("#formcomunityName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#communityEmailEdit').val(item.email).trigger("change");
				$("#formcomunityEmail").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#communityMobileEdit').val(item.mobileNumber).trigger("change");
				$("#formcomunityMobile").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#communityAddressEdit').val(item.address).trigger("change");
				$("#formcomunityAddress").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$("#communityIdhidden").val(item.communityID);
			
				$('#communityEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myCommunityEdit').modal('show');
	});
}