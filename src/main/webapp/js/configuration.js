/**
 * 
 */

$(document)
		.ready(
				function() {
					$('#configurationDetails').bootstrapValidator(
{
feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {

											selectcommunityName : {
												validators : {
													notEmpty : {
														message : 'Please select your native language.'
													}
												}
											},
											selectBlockBasedonCommunity : {
												validators : {
													notEmpty : {
														message : 'Please select your native language.'
													}
												}
											},

											selectHouseBasedonBlock : {
												validators : {
													notEmpty : {
														message : 'Please select your native language.'
													}
												}
											},

											AMR_topup : {
												message : 'The AMR is not valid',
												validators : {
													notEmpty : {
														message : 'The AMR is required and cannot be empty'
													}
												}
											},
											selectcommandType : {
												validators : {
													notEmpty : {
														message : 'Please select your native language.'
													}
												}
											}
										}
									});

					$('#configurationDetails').on(
							'status.field.bv',
							function(e, data) {
								formIsValid = true;
								$('.input-group.form-group', $(this)).each(
										function() {
											// alert(this+"@@=>"+formIsValid);
											formIsValid = formIsValid
													&& $(this).hasClass(
															'has-success');

											// alert("!!@@=>"+formIsValid);

										});

								if (formIsValid) {
									$('.submit-button', $(this)).attr(
											'disabled', false);
								} else {
									$('.submit-button', $(this)).attr(
											'disabled', true);
								}
							});

					$("#configuration")
							.click(
									function() {

										var data1 = {}

										data1["communityID"] = $(
												"#selectcommunityName").val();
										data1["blockID"] = $(
												"#selectBlockBasedonCommunity")
												.val();
										data1["customerID"] = $(
												"#selectHouseBasedonBlock")
												.val();
										data1["meterID"] = $("#AMR_topup")
												.val();
										data1["commandType"] = $("#selectcommandType").val();
										data1["source"] = "web"

alert("===>"
+ JSON.stringify(data1));
$
.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/configuration/add",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(data) {
														alert("data"
																+ JSON
																		.stringify(data));
														if (data.result == "Success") {

															/*
															 * alert( "data" +
															 * data.result);
															 */

															bootbox
																	.alert(
																			"Added Succesfully!",
																			function(
																					result) {

																				// alert();
																				window.location = "configuration.jsp";
																				return false
																			});

														} else if (data.result == "Block Registered Successfully but due to internal server Error Credentials have not been sent to your registered Mail ID. Please Contact Administrator") {

															bootbox
																	.alert(
																			data.result,
																			function(
																					result) {

																				// alert();
																				window.location = "configuration.jsp";
																				return false
																			});

														} else if (data.result == "Failure") {

															bootbox
																	.alert(
																			data.result,
																			function(
																					result) {

																				//alert();
																				window.location = "configuration.jsp";
																				return false
																			});
														}
													}
												});
return false;
});
});





$(document).ready(function() {
table = $('#configurationstatusTable')
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
							"url" : "/PAYGTL_LORA_BLE/configuration/"
									+ sessionStorage.getItem("roleID") + "/"
									+ sessionStorage.getItem("ID"),
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
						 },{
						 "data" : "blockName"
						 },{
						 "data" : "houseNumber"
						 },*/{
							"data" : "meterID"
						}, {
							"data" : "commandType"
						}, {
							"data" : "modifiedDate"
						}, {
							"data" : "status"
						} ]
					});
		});