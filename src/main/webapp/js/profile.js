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
															
															bootbox.alert(data.Message,
																	function(
																			result) {
																			
																//alert();
																window.location = "myprofile.jsp";
																return false
															});
															
															

														}else if(data.result == "Failure"){
															
															bootbox.alert(data.Message,
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
							
							
							
							
							$.getJSON("/PAYGTL_LORA_BLE/community/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
								$.each(data.data, function(i, item) {
									//if (session.Storage("") == item.communityID) {
									document.querySelector('#communityName').innerText = item.communityName;
									document.querySelector('#communityEmail').innerText = item.email;
									document.querySelector('#communityMobile').innerText = item.mobileNumber;
									document.querySelector('#communityAddress').innerText = item.address;
								});
							});
							
							
							$.getJSON("/PAYGTL_LORA_BLE/block/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
								$.each(data.data, function(i, item) {
									document.querySelector('.blockNameEdit').innerText = item.blockName;
									document.querySelector('.blockLocationEdit').innerText = item.Location;
									document.querySelector('.blockMobileEdit').innerText = item.mobile;
									document.querySelector('.blockEmailEdit').innerText = item.email;
									document.querySelector(".blockIdhidden").innerText = item.blockID;
									
								});
							});
						});





function getBlock(){
	$.getJSON("/PAYGTL_LORA_BLE/block/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
		$.each(data.data, function(i, item) {
			if (sessionStorage.getItem("ID") == item.blockID) {
				
				$('#communityNameEdit').val(item.communityName).trigger("change");
				$("#formcomunityName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockNameEdit').val(item.blockName).trigger("change");
				$("#formblockName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockLocationEdit').val(item.Location).trigger("change");
				$("#formblocklocation").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#blockMobileEdit').val(item.mobile).trigger("change");
				$("#formblockMobile").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockEmailEdit').val(item.email).trigger("change");
				$("#formblockEmail").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$("#blockIdhidden").val(item.blockID);
			
				$('#blockEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myBlockEdit').modal('show');
	});
}