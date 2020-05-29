

$(document)
		.ready(
				function() {
					var hCols = [ 3, 4 ];
					$('#approvalTable')
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
											"url" : "/PAYGTL_LORA_BLE/customerupdatesrequest/"
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
													"data" : "firstName"
												},
												{
													"data" : "email"
													
												},
												 {
														"data" : "mobileNumber"
												},
												 {
													"data" : "userID"
												},{
													"mData" : "action",
													"render" : function(data, type, row) {
														
														return "<a href=# onclick='getApprovalORRejected("
														+ row.requestID+","+'"1"'
														+ ")'>"
														+ "<img src=common/images/accept1.png /></a>"
														+"<a href=# onclick='getApprovalORRejected("
														+ row.requestID+","+'"0"'
														+ ")'>"
														+ "<img src=common/images/reject.png /></a>"
																													
																													
													}
													}],
													"columnDefs" : [ {
														orderable : false,
														//targets: 5, visible: !(sessionStorage.getItem("roleID") == 4)
														"className": "dt-center", "targets": "_all"
													},
													{
														targets: 4, 
														visible: !(sessionStorage.getItem("roleID") == 5)
													}],

										"buttons" : [
												]
									})  
					
					$("div.headname").html('<h3>Approval Details</h3>');
				});






function getApprovalORRejected (requestId,Id){
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/PAYGTL_LORA_BLE/approverequest/" + requestId +"/"+Id,
		dataType : "JSON",
		success : function(data) {
			if (data.result == "Success") {
				bootbox.alert(data.Message,
						function(
								result) {
								
					//alert();
					window.location = "approval.jsp";
					return false
					
							});

			} else {
				bootbox.alert(data.Message,
						function(
								result) {
					window.location = "approval.jsp";
					return false
					
							});
			}
		}
	});
} 