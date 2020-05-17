/**
 * 
 */


$(document).ready(function() {
	
	if(sessionStorage.getItem("roleID") == 3){
		$("#holidayAddd").show();
		$("#CRNNumberAdd").val(sessionStorage.getItem("ID"));
		$("#formCRNNumber").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
	}else{
		$("#holidayAddd").remove();
		
	}
	
	
table = $('#holidayTable')
.DataTable(
{//'Pfrtip'
	"dom": "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-white'i><'col-sm-6 text-white'p>>",
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
"url":"/PAYGTL_LORA_BLE/vacation/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
"type" : "GET",
"data" : function(search) {
},
"complete" : function(json) {
	console.log(json);
return json.data;
},
},
"columns" : [
{
"data" : "communityName"
},{
"data" : "blockName"
},{
"data" : "firstName"
},{
"data" : "lastName"
},{
"data" : "houseNumber"
},{
"data" : "CRNNumber"
},{
"data" : "vacationName"
},{
"data" : "meterID"
},{
"data" : "startDate"
},{
"data" : "endDate"
},{
"data" : "registeredDate"
},{
"data" : "status"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<a href=# id=HolidayEdit data-toggle=modal data-target=#myHolidayEdit onclick='getHolidayFormEdit("
																	+ row.vacationID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getVacationrFormDelete("
																	+ row.vacationID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																	+ "</a>"
																	
																	
	}
	}



],
"columnDefs" : [ {
	//orderable : false,
	targets : 12, visible:  (sessionStorage.getItem("roleID") == 3)
},
{
	
	"className": "dt-center", "targets": "_all"}
],
	"buttons" : [
	{
		//extend : 'excel',
		footer : 'true',
		//text : 'Excel',
		title : 'Vacation',
		className: 'custom-btn fa fa-file-excel-o'
			
	},

	{
		//extend : 'pdf',
		footer : 'true',
		exportOptions : {
			columns : [ 0,1, 2, 3, 4,
					5, 6, 7, 8, 9,
					10]
		},
		//text : 'pdf',
		className: 'custom-btn fa fa-file-pdf-o',
		orientation : 'landscape',
		title : 'Vacation'
	}
	]
});
});

 




$(document)
				.ready(
						function() {
							$("#holidayAdd")
									.click(
											function() {

												if($("#vacationAdd").val() == -1 || $("#vacationAdd").val() == ""){
													bootbox
													.alert("Enter Vacation Name");
													return false;
												} 
												
												if($("#start_date").val() == "" ){
													bootbox
													.alert("Enter Start Date and Time");
													return false;
												}

												if($("#end_date").val() == "" ){
													bootbox
													.alert("Enter End    Date and Time");
													return false;
												}
												
												var startDay = new Date($("#start_date").val());
												var endDay = new Date($("#end_date").val())
												
												
												var startDay = startDay.getDay();
												var endDay = endDay.getDay();
											//	alert(new Date($("#start_date_edit").val()).getDay());
												var data1 = {}
												data1["CRNNumber"] = $("#CRNNumberAdd").val();
												data1["vacationName"] = $("#vacationAdd").val();
												data1["startDateTime"] = $("#start_date").val();
												data1["endDateTime"] = $("#end_date").val();
												data1["startDay"] = startDay;
												data1["endDay"] = endDay;
												data1["source"] = "web";
											//	data1["mode"] = "add";
												
												
												
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "/PAYGTL_LORA_BLE/vacation/add",
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
																		window.location = "holiday.jsp";
																		return false
																	});
																	
																	

																} else if(data.result == "Failure"){
																	
																	bootbox.alert(data.Message,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "holiday.jsp";
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
							
							
							
							$("#holidayEditsave")
							.click(
									function() {

										var data1 = {}
										
										if($("#vacationEdit").val() == -1 || $("#vacationEdit").val() == ""){
											bootbox
											.alert("Enter Vacation Name");
											return false;
										} 
										
										if($("#start_date_edit").val() == "" ){
											bootbox
											.alert("Enter Start Date and Time");
											return false;
										}

										if($("#end_date_edit").val() == "" ){
											bootbox
											.alert("Enter End Date and Time");
											return false;
										}
										
										var startDay = new Date($("#start_date_edit").val());
										var endDay = new Date($("#end_date_edit").val())
										
										
										var startDay = startDay.getDay();
										var endDay = endDay.getDay();
										
										
										var data1 = {}
										data1["CRNNumber"] = $("#CRNNumberEdit").val();
										data1["vacationName"] = $("#vacationEdit").val();
										data1["startDateTime"] = $("#start_date_edit").val();
										data1["endDateTime"] = $("#end_date_edit").val();
								
										data1["startDay"] = startDay;
										data1["endDay"] = endDay;
										data1["source"] = "web";
										//data1["mode"] = "edit";
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/vacation/edit/"+$("#vacationID").val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														alert("data"
																+ JSON
																		.stringify(data));
														if (data.result == "Success") {

															/*alert( "data"
																	+ data.result);*/
															
															bootbox.alert(data.Message,
																	function(
																			result) {
																			
																//alert();
																window.location = "holiday.jsp";
																return false
															});
														} else {
															
															bootbox.alert(data.Message,
																	function(
																			result) {
																			
																//alert();
																window.location = "holiday.jsp";
																return false
																
																		});
															//return false;
														}
													}
												});
										return false;
									});
							
							
						});




function getHolidayFormEdit(id) {

//	 alert(id);

	$.getJSON("/PAYGTL_LORA_BLE/vacation/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.vacationID) {
				$('#CRNNumberEdit').val(item.CRNNumber).trigger("change");
				
				$('#vacationEdit').val(item.vacationName).trigger("change");
				
				$('#start_date_edit').val(item.startDate).trigger("change");
				
				$('#end_date_edit').val(item.endDate).trigger("change");
			    
				$("#vacationID").val(item.vacationID);
			
			} else {
			}
		});
		$('#myHolidayEdit').modal('show');
	});
}


function getVacationrFormDelete(vacationID){
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELEE VACATION",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/PAYGTL_LORA_BLE/vacation/delete/web/" + vacationID,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "holiday.jsp";
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
