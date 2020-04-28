/**
 * 
 */


$(document)
				.ready(
						function() {
							$('#profile').bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													
													oldpassword : {
														message : 'The Old Password is not valid',
														validators : {
															notEmpty : {
																message : 'The Old Password is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'The Old Password must be more than 4 and less than 30 characters long'
															}
														}
													},
													newpassword : {
														message : 'The Old Password is not valid',
														validators : {
															notEmpty : {
																message : 'The Old Password is required and cannot be empty'
															},
															stringLength : {
																min : 6,
																max : 30,
																message : 'The Old Password must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,30}$/,
																message : 'The New Password can only consist which contain at least one numeric digit, one Special Symbol, one uppercase and one lowercase letter'
															},
															 identical: {
											                        field: 'confirmpassword',
											                        message: 'The password and its confirm are not the same'
											                    }
														}
													},
													confirmpassword : {
														message : 'The Old Password is not valid',
														validators : {
															notEmpty : {
																message : 'The Old Password is required and cannot be empty'
															},
															stringLength : {
																min : 6,
																max : 30,
																message : 'The Old Password must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,30}$/,
																message : 'The Confirm Password can only consist which contain at least one numeric digit, one Special Symbol, one uppercase and one lowercase letter'
															}, identical: {
										                        field: 'newpassword',
										                        message: 'The password and its confirm are not the same'
										                    }
														}
													}
												}
											});
							
							
							
							$('#profile')
											.on(
													'status.field.bv',
													function(e, data) {
														formIsValid = true;
														$('.form-group', $(this))
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
							
							
							
							
							$("#profilebutton")
							.click(
									function() {

										var data1 = {}
										data1["newPassword"] = $("#confirmpassword").val();
										data1["oldPassword"] = $("#oldpassword").val();
										
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/changepassword/"+sessionStorage.getItem("userID"),
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
																window.location = "myprofile.jsp";
																return false
															});
															
															

														}else if(data.result == "Failure"){
															
															bootbox.alert(data.result,
																	function(
																			result) {
																			
																//alert();
																window.location = "myprofile.jsp";
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