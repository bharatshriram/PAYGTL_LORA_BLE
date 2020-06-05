/**
 * 
 */

$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
		
		if(sessionStorage.getItem("roleID") == 2){
			$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
			$("#formcommunityNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
			$("#formblockNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		}
		
		
		document.querySelector(".blockimp").innerText ="*";
	}
	});
$(document)
		.ready(
				function() {
					$("#topupSummary")
							.click(
									function() {

										var data1 = {}
										
										var selectcommunityName = $(
												"#selectcommunityName")
												.val();

//										alert($("#selectBlockBasedonCommunity").val());
										
										if ($("#selectcommunityName").val() == "-1") {
											
											bootbox
											.alert("Select Community Id");
											return false;
										}

										if(sessionStorage.getItem("roleID") == 1){
										
										if ($("#selectBlockBasedonCommunity").val() == "null" || $("#selectBlockBasedonCommunity").val() != "Select Block") {

											data1["blockID"] = $(
											"#selectBlockBasedonCommunity").val();
										}else {
											data1["blockID"] = "-1";
										}
										} else if(sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
											if ($("#selectBlockBasedonCommunity").val() == "null" || $("#selectBlockBasedonCommunity").val() == "Select Block") {

												bootbox
												.alert("Select Block Id");
												return false;
												
											}else {
												data1["blockID"] = $(
												"#selectBlockBasedonCommunity").val();
											}
											
										}
										
										if ($("#selectHouseBasedonBlock").val() == "null" || $("#selectHouseBasedonBlock").val() != "Select CRN") {

											data1["CRNNumber"] = $(
											"#selectHouseBasedonBlock")
											.val();
										}else {
											data1["CRNNumber"] = "";
										}


										if ($("#start_date").val() == "null" || $("#start_date").val() == "") {

											bootbox
											.alert("Select Start Date");
											return false;
										}
										
										if ($("#end_date").val() == "null" || $("#end_date").val() == "") {

											bootbox
											.alert("Select End Date");
											return false;
										}
										
										
									
										data1["communityID"] = $(
												"#selectcommunityName").val();
										
										
										/*data1["meterID"] = $("#AMR_topup")
												.val();*/
										data1["fromDate"] = $("#start_date")
												.val();
										data1["toDate"] = $("#end_date").val();

										alert("===>" + JSON.stringify(data1));
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/topupsummary",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(d) {
														/*alert("data"
																+ JSON
																		.stringify(d));*/
														//if (data.result == "Success") {
														
														$("#form").hide();
														$("#tablereport").show();

														 table = $('#topupsummaryTable').DataTable(
																	{
																		"dom": "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
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
																		"data" : d.data,
																		"columns" : [ {
																			
																			"data" : "firstName"
																		},{
																			
																			"data" : "lastName"
																		},{
																			
																			"data" : "houseNumber"
																		}, {
																			"data" : "meterID"
																		}, {
																			"data" : "rechargeAmount"
																		}, {
																			"data" : "modeOfPayment"
																		}, {
																			"data" : "status"
																		}, {
																			"data" : "transactedByUserName"
																		}, {
																			"data" : "transactedByRoleDescription"
																		}, {
																			"data" : "dateTime"
																		}, {
																			"mData" : "action",
																			"render" : function(data, type, row) {
																				
																				/*<button type="button"
																					class="btn btn-raised btn-primary float-right"
																					data-toggle="modal" data-target="#exampleModal">
																					<i class="fa fa-user"></i>
																				</button>*/
																			//return "<a href='#communityEditModal' class='teal modal-trigger' data-toggle='modal' data-target='#communityEditModal' id='communityEditModal' onclick='getSocietyFormEdit("+row.communityID+")'><i class='material-icons' style='color:#17e9e9'>edit</i></a>"
																				
																				return "<a onclick='getReceiptTransactionID("
																																			+ row.transactionID
																																			+ ")'>"
																																			+"<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																																			+ "</a>"
																			}
																		}],
																		"columnDefs" : [ {
																		"className": "dt-center", "targets": "_all"
																		}], "buttons": [
																			   /* 'csvHtml5',
																			'excelHtml5',
																		'pdfHtml5'*/
																			
																			{extend: 'excel',
																		        footer: 'true',
																		        text: 'Excel',
																		        title:'ReCharge Summary'  },
																		         
																		        {extend: 'pdf',
																		        footer: 'true',
																		        exportOptions: {
																		            columns: [1,2,3,4,5,6,7,8,9]
																		        },
																		        text: 'pdf',
																		        orientation: 'landscape',
																		        title:'ReCharge Summary'  }
																		]
																	});

														 $("div.headname").html('<h3>ReCharge Summary</h3>');
															//table.ajax.reload()
														 
														 $("div.addevent").html('<button id="back" onClick="returnBack()"'
																 +'class="btn btn-raised btn-primary float-right"'
																	+'>'
																+'	<span>Back</span>'
															+'</button>');
														 
													}
												});
										return false;
									});
				});

$(document).ready(
		function() {
			$("#back")
			.click(
					function() {
		
						window.location = "topupSummary.jsp";
						return false
						
					});
		});





function getReceiptTransactionID(transID){
	bootbox
	.confirm(
			"ARE YOU SURE TO DOWNLOAD RECEIPT",
		function(
			result) {
			//	alert(result);
				window.open("/PAYGTL_LORA_BLE/status/print/" + transID);
				/*if(result == true){
					$.ajax({
						type : "GET",
						contentType : "application/json",
						url : "/PAYGTL_LORA_BLE/status/print/" + transID,
						dataType : "JSON",
						success : function(data) {
							//alert("Success====" + data.result);
							if (data.result == "Success") {
								bootbox
								.confirm(
										data.Message,
									function(
										result) {
										window.location = "topupStatus.jsp";
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
					
				}*/
		});
}