/**
 * 
 */


$(document).ready(function() {
	
	if(sessionStorage.getItem("roleID") == 3){
		$("#holidayAddd").show();
		$("#CRNNumberAdd").val(sessionStorage.getItem("ID"));
		$("#formCRNNumber").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-3 totalcount'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-5 total'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}else{
		$("#holidayAddd").remove();
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-3'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
		
	}
	
	//alert(sessionStorage.getItem("ID"));
	
	
	$("#holidayTable1").hide();
table = $('#holidayTable')
.DataTable(
{
	"dom": dom1,
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
"url":"/PAYGTL_LORA_BLE/vacation/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1",
"type" : "GET",
"data" : function(search) {
},
"complete" : function(json) {
	console.log(json);
	$("div.total").html('<b>MIU ID:</b> '+json.responseJSON.data[0].meterID+ '  <b>CRN Number:</b> '+sessionStorage.getItem("ID"));
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
"data" : "mode"
},{
"data" : "registeredDate"
},{
"data" : "status"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		if((row.mode == "add" || row.mode == "edit") && row.status == "Passed"){
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
		} else if(row.mode == "delete" || row.status == "Passed"){
			return "---";
		}else if(row.mode == "delete" || row.status == "Passed"){
			return "---"
																	
	}else{
		return "---"
	}
		
	}}

],
"columnDefs" : [ {
	//orderable : false,
	targets : 12, visible:  (sessionStorage.getItem("roleID") == 3)
},
{
	//orderable : false,
	targets :  [0,1,2,3,4,5,7], visible: !(sessionStorage.getItem("roleID") == 3) 
	
},
{
	
	"className": "dt-center", "targets": "_all"}
],
	"buttons" : [
	{
		extend : 'excel',
		footer : 'true',
		exportOptions : {
			columns : [ 0,1, 2, 3, 4,
					5, 6, 7, 8, 9,
					10,11,12]
		},
		//text : 'Excel',
		title : 'Vacation',
	//className: 'custom-btn fa fa-file-excel-o'
			
	},

	{
		extend : 'pdf',
		footer : 'true',
		exportOptions : {
			columns : [ 0,1, 2, 3, 4,
					5, 6, 7, 8, 9,
					10,11,12]
		},
		//text : 'pdf',
		//className: 'custom-btn fa fa-file-pdf-o',
		orientation : 'landscape',
		title : 'Vacation'
	},
	{
        className: 'customButton',
        text : "Filter",
         action: function ( e, dt, button, config ) {
         	$('.customButton').attr(
                 {
                     "data-toggle": "modal",
                     "data-target": "#filter"
                 }
             );
         }
     }
	],
	 initComplete: function() {
		   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
		   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
		  }
});
if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
	table.buttons( $('a.customButton') ).remove();	
}

$("div.headname").html('<h3>Vacation Details</h3>');
$("div.addevent").html('<button type="button" id="holidayAddd" class="btn btn-raised btn-primary float-right" data-toggle="modal" data-target="#exampleModal">	<i class="fa fa-user"></i></button>');


$("#customerFilter")
.click(
		function() {

			var url = $("#filterselectcommunityName").val() == "-1" ? sessionStorage.getItem("roleID")+"/0/-1" : $("#filterselectBlockBasedonCommunity").val() == "Select Block" ? 
					$("#filterselectcommunityName").val() == "-1" ? 
					sessionStorage.getItem("roleID")+"/0/-1":sessionStorage.getItem("roleID")+"/0/"+$("#filterselectcommunityName").val():
				"2/"+$("#filterselectBlockBasedonCommunity").val()+"/-1"
			
				
				//alert(url);
				
			$
					.ajax({
						type : "GET",
						contentType : "application/json",
						url : "/PAYGTL_LORA_BLE/vacation/"+url,
						dataType : "JSON",

						success : function(d) {
							
								$('#holidayTable').dataTable()._fnAjaxUpdate();
								$("#holidayTable_wrapper").hide();
								$("#filter").modal("hide");
								$("#holidayTable1").show();
								var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-3 totalcount'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
								var hCols = [ 3, 4 ];
								table = $('#holidayTable1')
								.DataTable(
										{
											
												"dom": dom1,
											   "responsive" : true,
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
												"scrollX" : false,
												"data" : d.data,
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
														"data" : "mode"
														},{
														"data" : "registeredDate"
														},{
														"data" : "status"
														}
														,{
															"mData" : "action",
															"render" : function(data, type, row) {
																
																if(row.mode == "add" || row.mode == "edit" && row.status == "Passed"){
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
																} else if(row.mode == "delete" || row.status == "Passed"){
																	return "---";
																}else if(row.mode == "delete" || row.status == "Passed"){
																	return "---"
																															
															}
															}}

														],
													"columnDefs" : [ {
														//orderable : false,
														targets : 12, visible:  (sessionStorage.getItem("roleID") == 3)
													},
													{
														//orderable : false,
														targets :  [0,1,2,3,4,5,7], visible: !(sessionStorage.getItem("roleID") == 3) 
														
													},
													{
														
														"className": "dt-center", "targets": "_all"}
													],
														"buttons" : [
														{
															extend : 'excel',
															footer : 'true',
															//text : 'Excel',
															title : 'Vacation',
															//className: 'custom-btn fa fa-file-excel-o'
																
														},

														{
															extend : 'pdf',
															footer : 'true',
															exportOptions : {
																columns : [ 0,1, 2, 3, 4,
																		5, 6, 7, 8, 9,
																		10,11,12,13]
															},
															//text : 'pdf',
															//className: 'custom-btn fa fa-file-pdf-o',
															orientation : 'landscape',
															title : 'Vacation'
														},
														{
											                text: 'Reset',
											                action: function ( e, dt, node, config ) {
											                    alert( 'Button activated' );
											                },
											                className: 'customButton',
											               
											                action: function ( e, dt, button, config ) {
											                   
											                	window.location = "holiday.jsp"
											                }
											            }
														],
														 initComplete: function() {
															   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
															   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
															  }
													});
										if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
									table.buttons( $('a.customButton') ).remove();	
								}

													$("div.headname").html('<h3>Vacation Details</h3>');
						}
					});
			return false;
		});

$("#resetFilter")
.on(
		function() {
			 $("input:text").val("");
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

	$.getJSON("/PAYGTL_LORA_BLE/vacation/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.vacationID) {
				$('#CRNNumberEdit').val(item.CRNNumber).trigger("change");
				
				$('#vacationEdit').val(item.vacationName).trigger("change");
				var myDate = new Date();
				var startDate = myDate.getFullYear() + "-" + (myDate.getMonth()+1) + "-" + myDate.getDate();
				$('#start_date_edit').val(startDate).trigger("change");
				
				$('#end_date_edit').val(startDate).trigger("change");
			    
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
			"ARE YOU SURE TO DELETE VACATION",
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
