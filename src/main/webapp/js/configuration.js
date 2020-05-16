/**
 * 
 */

$(document)
		.ready(
				function() {
				/*	$('#configurationDetails')
							.bootstrapValidator(
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
											},
											selectTariffName : {
												validators : {
													notEmpty : {
														message : 'Please select your Tariff language.'
													}
												}
											},
											defaultReading : {
												message : 'The Default Reading is not valid',
												validators : {
													notEmpty : {
														message : 'The Default Reading is required and cannot be empty'
													},stringLength : {
														min : 2,
														max : 30,
														message : 'The Default Reading must be more than 2 and less than 30 characters long'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'The Default Reading can only consist of number'
													}
												}
											}
										}
									});*/

					/*$('#configurationDetails').on(
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
							});*/

										$(document).on('click', '#configuration', function () {
									    
										var data1 = {}										
										if ($("#selectcommunityName").val() == "-1") {
											
											bootbox
											.alert("Select Community Id");
											return false;
										}

										if ($("#selectBlockBasedonCommunity").val() == "null" || $("#selectBlockBasedonCommunity").val() == "Select Block") {

											bootbox
											.alert("Select Block Name");
											return false;
										}
										
										if ($("#selectHouseBasedonBlock").val() == "null" || $("#selectHouseBasedonBlock").val() == "Select CRN") {

											bootbox
											.alert("Select CRN Number");
											return false;
										}


										if ($("#selectcommandType").val() == "null" || $("#selectcommandType").val() == -1 || $("#selectcommandType").val() == "Select Command Type") {

											bootbox
											.alert("Select Command Type");
											return false;
										}
										
										if($("#selectcommandType").val() == "6"){
											var reg =/[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/;
											if($("#defaultReading").val() == ""){
											
												bootbox
												.alert("Enter Default Reading");
												return false;
											}	
											
												else if(!reg.test($("#defaultReading").val())){
													bootbox
													.alert("The Default Reading can only consist of number");
													return false;
												}	
											data1["defaultReading"] = $("#defaultReading").val();
											} else if($("#selectcommandType").val() == "10"){
												if($("#selectTariffName").val() == "Select Tariff" || $("#selectTariffName").val() == -1){
												
													bootbox
													.alert("Select Tariff");
													return false;
												}	
												data1["tariffID"] = $("#selectTariffName").val();
											}
										 
										data1["commandType"] = $("#selectcommandType").val();
										data1["CRNNumber"] = $("#selectHouseBasedonBlock").val();
										data1["meterID"] = $("#AMR_topup").val();
										data1["source"] = "web";

										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/configuration/add",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(data) {
														
														if (data.result == "Success") {

															/*
															 * alert( "data" +
															 * data.result);
															 */

															bootbox
																	.alert(
																			data.Message,
																			function(
																					result) {

																				// alert();
																				window.location = "configurationStatus.jsp";
																				return false
																			});

														} else if (data.result == "Failure") {

															bootbox
																	.alert(
																			data.Message,
																			function(
																					result) {

																				// alert();
																				window.location = "configuration.jsp";
																				return false
																			});
														}
													}
												});
										return false;
									});
				});

$(document)
		.ready(
				function() {
					table = $('#configurationstatusTable')
							.DataTable(
									{
										"dom" : "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>"
												+ "<'row'<'col-sm-12'tr>>"
												+ "<'row'<'col-sm-6 text-white'i><'col-sm-6 text-white'p>>",
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
											"url" : "/PAYGTL_LORA_BLE/configuration/"+ sessionStorage.getItem("roleID")+ "/"+ sessionStorage.getItem("ID"),
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												return json.data;
											},
										},
										"columns" : [
												{
													"data" : "meterID"
												},
												{
													"data" : "commandType"
												},
												{
													"data" : "modifiedDate"
												},
												{
													"data" : "status"
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {

														if(row.status == "Passed"){
														
															return "---";
															
														}else if(row.status == "Pending...waiting for acknowledge"){
														
															return "---";
															
														}
														
														else if(row.status == "Failed"){
															return "<a onclick='getDeleteTransactionID("
															+ row.transactionID
															+ ")'>"
															+ "<i class='material-icons' style='color:#17e9e9; cursor:pointer;'>delete</i>"
															+ "</a>"
															
														}
														
														
													}
												} ],
										"columnDefs" : [ {
										//	orderable : false,
											targets : 4, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
										},
										{
											"className": "dt-center", "targets": "_all"
										}],
										"buttons" : [
										{
											extend : 'excel',
											footer : 'true',
											text : 'Excel',
											exportOptions : {
												columns : [ 0, 1, 2, 3 ]
											},
											title : 'Configuration Status'
										},

										{
											extend : 'pdf',
											footer : 'true',
											exportOptions : {
												columns : [ 0, 1, 2, 3 ]
											},
											text : 'pdf',
											orientation : 'landscape',
											title : 'Configuration Status'
										} ]

									});
				});





function getDeleteTransactionID(transID){
	
	alert(transID);
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELEE RECORD",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/PAYGTL_LORA_BLE/configuration/delete/" + transID,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "configurationStatus.jsp";
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

