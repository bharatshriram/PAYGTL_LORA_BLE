/**
 * 
 */


$(document)
		.ready(
				function() {
					$(
					"#CRNNumber")
					.val(sessionStorage.getItem("ID"))
					
					$("#feedback")
							.on(
									function() {

										if ($("#selectfeedback").val() == "-1") {
											
											bootbox
											.alert("Select Feedback");
											return false;
										}

										var data1 = {}
										data1["CRNNumber"] = $(
										"#CRN")
										.val();
										data1["feedback"] = $(
												"#selectFeedback")
												.val();
										data1["description"] = $(
												"#description")
												.val();
										
										$('#feedback').prop('disabled', true).addClass('disabled').off( "click" );
										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/feedback/add",
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
																window.location = "feedback.jsp";
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
				});