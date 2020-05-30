/**
 * 
 */


$(document)
		.ready(
				function() {
					$(
					"#CRNNumber")
					.val(sessionStorage.getItem("ID"))
					
					
					
					$('#feedbackTable')
					.DataTable(
							{
								"dom" : "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
										
										"language": {
										      "emptyTable": "No data available in table"
										    },
										 
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
											"scrollX" : true,
										
								"ajax" : {
									"url" : "/PAYGTL_LORA_BLE/feedback/"
											+ sessionStorage
													.getItem("ID"),
									"type" : "GET",
									"data" : function(search) {
									},
									"complete" : function(json) {
										console.log(JSON.stringify(json));
										return json.data;
									},
								},
								"columns" : [
									
										{
											"data" : "CRNNumber",
											"defaultContent": ""
										},
										{
											"data" : "houseNumber",
											"defaultContent": ""
											
										},
										 {
												"data" : "name",
												"defaultContent": ""
										},
										 {
											"data" : "feedback",
											"defaultContent": ""
										},{
												"data" : "description",
												"defaultContent": ""
										},
										 {
											"data" : "date",
											"defaultContent": ""
										},{
											"mData" : "action",
											"render" : function(data, type, row) {
												
												return "<a href=# onclick='getFeedback("
												+ row.feedbackID+","+'"1"'
												+ ")'>"
												+ "<img src=common/images/accept1.png /></a>"
											}
											}],
											"columnDefs" : [ {
												orderable : false,
												//targets: 5, visible: !(sessionStorage.getItem("roleID") == 4)
												"className": "dt-center", "targets": "_all"
											},
											{
												targets: 5, 
												visible: !(sessionStorage.getItem("roleID") == 5)
											}],

								"buttons" : [
										]
							})  
			
			$("div.headname").html('<h3>Feedback Details</h3>');
					
					$("#feedback")
							.click(
									function() {

										if ($("#selectFeedback").val() == "-1") {
											
											bootbox
											.alert("Select Feedback");
											return false;
										}

										var data1 = {}
										data1["CRNNumber"] = $(
										"#CRNNumber")
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