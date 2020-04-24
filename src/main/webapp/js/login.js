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
													             message: 'The email address is required.'
													         },
													         emailAddress: {
													             message: 'The email address is not valid.'
													         }
													     }
													 }, */
													username : {
														message : 'The username is not valid',
														validators : {
															notEmpty : {
																message : 'The username is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'The username must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The username can only consist of alphabetical and number'
															},
															different : {
																field : 'password',
																message : 'The username and password cannot be the same as each other'
															}
														}
													},
													password : {
														validators : {
															notEmpty : {
																message : 'The password is required.'
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
						        $('.input-group.form-group',$(this)).each( function() {
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
												data1["userID"] = $("#userName")
														.val();
												data1["password"] = $(
														"#password").val();
												alert("===>"
														+ JSON.stringify(data1));
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
																/*alert("data"
																		+ JSON
																				.stringify(data));*/
																if (data.result == "Success") {

																	sessionStorage.setItem("userID",$("#userName").val());
																	
																	if (data.userDetails.roleID == 1) {

																		sessionStorage
																				.setItem(
																						"type",
																						"SuperAdmin");
																		
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
																				"type",
																				"Admin");
																sessionStorage
																		.setItem(
																				"createdByID",
																				data.userDetails.ID);
																
																sessionStorage
																.setItem(
																		"communityID",
																		data.userDetails.communityID);
																
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
																
																var Role = data.userDetails.roleID;
																		window.location = "LoginAction.jsp?RoleID="
																				+ Role;

																	}

																} else if(data.result == "Failure"){

																	document.querySelector(".errorMessage").innerText="";
																	var error = document.createElement("h1");
																	error.innerText = data.Message;
																	document.querySelector(".errorMessage").appendChild(error);
																	
																}
															}
														});
												return false;
											});
						});