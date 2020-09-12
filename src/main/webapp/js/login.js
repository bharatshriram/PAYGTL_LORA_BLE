/**
 * 
 */


$(document)
				.ready(
						function() {
							$('#test')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													/*  email: {
													     validators: {
													         notEmpty: {
													             message: 'email address is required.'
													         },
													         emailAddress: {
													             message: 'email address is not valid.'
													         }
													     }
													 }, */
													username : {
														message : 'Username is not valid',
														validators : {
															notEmpty : {
																message : 'username is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'username must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'username can only consist of alphabetical and number'
															},
															different : {
																field : 'password',
																message : 'username and password cannot be same as each other'
															}
														}
													},
													password : {
														validators : {
															notEmpty : {
																message : 'password is required.'
															},
															stringLength : {
																min : 5,
																message : 'Your password must be at least 5 characters.'
															}
														}
													}
												}
											});

							$('#test').on('status.field.bv', function(e, data) {
						        formIsValid = true;
						        $('.form-group.md-form',$(this)).each( function() {
						            formIsValid = formIsValid && $(this).hasClass('has-success');
						            console.log("@@"+formIsValid);
						        });
						        
						        if(formIsValid) {
						            $('.submit-button', $(this)).attr('disabled', false);
						        } else {
						            $('.submit-button', $(this)).attr('disabled', true);
						        }
						    });

							$("#login")
									.click(
											function() {

												var data1 = {}
												data1["userID"] = $("#username")
														.val();
												data1["password"] = $(
														"#password").val();
												
												data1["source"] = "web";
												
												alert("@@");
												
												
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "/PAYGTL_LORA_BLE/login",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {
																if (data.result == "Success") {

																	sessionStorage.setItem("userID",$("#username").val());
																	
																	if (data.userDetails.roleID == 1) {

																		sessionStorage
																				.setItem(
																						"type",
																						"SuperAdmin");
																		
																		sessionStorage
																		.setItem(
																				"email",
																				data.userDetails.email);
																		
																		sessionStorage
																		.setItem(
																				"mobileNumber",
																				data.userDetails.mobileNumber);		
																		
																		sessionStorage
																				.setItem(
																						"createdByID",
																						data.userDetails.ID);
																		
																		sessionStorage
																		.setItem(
																				"ID",
																				0);
																		
																		sessionStorage
																		.setItem(
																				"userName",
																				data.userDetails.userName);
																		
																		sessionStorage
																		.setItem(
																				"roleID",
																				data.userDetails.roleID);
																		
																		var Role = data.userDetails.roleID;

																		window.location = "LoginAction.jsp?RoleID="
																				+ Role;
																	} else if (data.userDetails.roleID == 2) {
																		sessionStorage
																		.setItem(
																		"communityID",
																		data.userDetails.communityID);
																		sessionStorage
																		.setItem(
																				"type",
																				"Admin");
																sessionStorage
																		.setItem(
																				"createdByID",
																				data.userDetails.ID);
																
																sessionStorage
																.setItem(
																		"roleID",
																		data.userDetails.roleID);
																
																sessionStorage
																.setItem(
																		"ID",
																		data.userDetails.blockID);
																
																sessionStorage
																.setItem(
																		"userName",
																		data.userDetails.userName);
																
																sessionStorage
																.setItem(
																		"mobileNumber",
																		data.userDetails.mobileNumber);
																
																sessionStorage
																.setItem(
																		"communityName",
																		data.userDetails.communityName);
																
																
																sessionStorage
																.setItem(
																		"blockName",
																		data.userDetails.blockName);
																
																sessionStorage
																.setItem(
																		"email",
																		data.userDetails.email);
																
																var Role = data.userDetails.roleID;
																		window.location = "LoginAction.jsp?RoleID="
																				+ Role;

																	} else if (data.userDetails.roleID == 3) {
																		sessionStorage
																		.setItem(
																				"type",
																				"Admin");
																sessionStorage
																		.setItem(
																				"createdByID",
																				data.userDetails.ID);
																
																sessionStorage
																.setItem(
																		"roleID",
																		data.userDetails.roleID);
																
																sessionStorage
																.setItem(
																		"blockID",
																		data.userDetails.blockID);
																
																sessionStorage
																.setItem(
																		"communnityID",
																		data.userDetails.communnityID);
																
																sessionStorage
																.setItem(
																		"ID",
																		data.userDetails.CRNNumber);
																
																sessionStorage
																.setItem(
																		"userName",
																		data.userDetails.userName);
																
																sessionStorage
																.setItem(
																		"mobileNumber",
																		data.userDetails.mobileNumber);
																
																sessionStorage
																.setItem(
																		"email",
																		data.userDetails.email);
																
																var Role = data.userDetails.roleID;
																		window.location = "LoginAction.jsp?RoleID="
																				+ Role;

																	} else if (data.userDetails.roleID == 4 ) {

																		sessionStorage
																				.setItem(
																						"type",
																						"SuperSupervisor");
																		
																		sessionStorage
																				.setItem(
																						"createdByID",
																						data.userDetails.ID);
																		
																		sessionStorage
																		.setItem(
																				"ID",
																				0);
																		
																		sessionStorage
																		.setItem(
																				"userName",
																				data.userDetails.userName);
																		
																		sessionStorage
																		.setItem(
																				"roleID",
																				data.userDetails.roleID);
																		
																		var Role = data.userDetails.roleID;

																		window.location = "LoginAction.jsp?RoleID="
																				+ Role;
																	}else if (data.userDetails.roleID == 5) {
																		sessionStorage
																		.setItem(
																		"communityID",
																		data.userDetails.communityID);
																		sessionStorage
																		.setItem(
																				"type",
																				"AdminSuperviour");
																sessionStorage
																		.setItem(
																				"createdByID",
																				data.userDetails.ID);
																
																sessionStorage
																.setItem(
																		"roleID",
																		data.userDetails.roleID);
																
																sessionStorage
																.setItem(
																		"ID",
																		data.userDetails.blockID);
																
																sessionStorage
																.setItem(
																		"userName",
																		data.userDetails.userName);
																
																var Role = data.userDetails.roleID;
																		window.location = "LoginAction.jsp?RoleID="
																				+ Role;

																	}

																} else if(data.result == "Failure"){

																	document.querySelector(".errorMessage").innerText="";
																	var error = document.createElement("h6");
																	error.class = "error";
																	error.innerText = data.Message;
																	document.querySelector(".errorMessage").appendChild(error);
																	
																}
															}
														});
												return false;
											});
							
							
							
							
							
							$("#forgotButton")
							.click(
									function() {

										if($("#forgetusername")
												.val() == null || $("#forgetusername")
												.val() == ""){
											bootbox
											.alert("Enter User ID");
											return false;
										}
										
										
										/*var data1 = {}
										data1["userID"] = $("#forgetusername")
												.val();
										data1["password"] = $(
												"#password").val();*/
										$.ajax({
											type : "POST",
											contentType : "application/json",
											url : "/PAYGTL_LORA_BLE/forgotpassword/" + $("#forgetusername")
											.val(),
											dataType : "JSON",
											success : function(data) {
//												alert("===>" + JSON.stringify(data));
												if (data.result == "Success") {
													bootbox.alert(data.Message,
															function(
																	result) {
																	
														//alert();
														window.location = "login.jsp";
														return false
														
																});

												} else {
													bootbox
													.alert(data.Message);
													return false;
												}
											}
										});
										return false;
									});
							
							
							
						});