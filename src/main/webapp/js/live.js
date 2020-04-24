/**
 * 
 */

/*
 * $(document).ready(function() { table = $('#liveTable') .DataTable( {
 * "processing" : false, dom: 'Bfrtip', buttons: [ 'copy', 'csv', 'excel',
 * 'pdf', 'print' ], "serverSide" : false, "bDestroy" : true, "pagging" : true,
 * "bProcessing" : false, "ordering" : true, "order" : [ 0, "desc" ],
 * "lengthMenu" : [ 5, 10, 25, 30, 50, 75 ], "pageLength" : "5", "scrollY" :
 * 324, "scrollX" : true, "ajax" : {
 * "url":"/PAYGTL_LORA_BLE/dashboard/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
 * "type" : "GET", "data" : function(search) { }, "complete" : function(json) {
 * console.log(json); return json.data; }, }, "columns" : [ { "data" :
 * "communityName" },{ "data" : "blockName" },{ "mData" : "action", "render" :
 * function(data, type, row) {
 * 
 * return '<div class=btn btn-secondary data-toggle="tooltip"
 * data-placement=top ' + 'title='+row.blockName+'>' +row.HouseNumber + '</div>';
 * 
 * return "<a href=# id=CustomerEdit data-toggle=modal
 * data-target=#myCustomerEdit onclick='getCustomerFormEdit(" + row.customerID +
 * ")'>" + "ABC" + "</a>" } } ,{ "data" : "HouseNumber" },{ "data" : "meterID"
 * },{ "data" : "meterID" },{ "data" : "reading" },{ "data" : "balance" },{
 * "data" : "emergencyCredit" },{ "data" : "battery" },{ "data" : "valveStatus"
 * },{ "data" : "tariff" },{ "data" : "tamperStatus" },{ "data" : "timeStamp" } ]
 * }); });
 */

$(document)
		.ready(
				function() {
					// Only needed for the filename of export files.
					// Normally set in the title tag of your page.document.title
					// = 'Simple DataTable';
					// Define hidden columns
					var hCols = [ 3, 4 ];
					// DataTable initialisation
					$('#liveTable')
							.DataTable(
									{
										responsive: {
								            details: {
								                display: $.fn.dataTable.Responsive.display.modal( {
								                    header: function ( row ) {
								                        var data = row.data();
								                        return 'Details for '+data[0]+' '+data[1];
								                    }
								                } ),
								                renderer: $.fn.dataTable.Responsive.renderer.tableAll( {
								                    tableClass: 'table'
								                } )
								            }
								        },
										"dom" : "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>"
												+ "<'row'<'col-sm-12'tr>>"
												+ "<'row'<'col-sm-12'p<br/>i>>",
												/*"pagingType" : 'full_numbers',*/
												"responsive" : true,
												"processing" : true,
												"serverSide" : true,
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
												/*"processing" : true,
												"serverSide" : true,
												"bDestroy" : true,
												"pagging" : true,
												"bProcessing" : true,
												"ordering" : true,
												"order" : [ 0, "desc" ],
												"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
												"pageLength" : "5",
												"scrollY" : 324,
												"scrollX" : true,*/
												
										"ajax" : {
											"url" : "/PAYGTL_LORA_BLE/dashboard/"
													+ sessionStorage
															.getItem("roleID")
													+ "/"
													+ sessionStorage
															.getItem("ID"),
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												/*alert("===>"
														+ JSON.stringify(json));*/
												console.log(JSON.stringify(json));
												return json.data;
											},
										},
										"columns" : [
												{
													"data" : "communityName"
												},
												{
													"data" : "blockName"
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {

														return '<div class=btn btn-secondary data-toggle="tooltip" data-placement=top '
																+ 'title='
																+ row.blockName
																+ '>'
																+ row.HouseNumber
																+ '</div>';

														/*
														 * return "<a href=#
														 * id=CustomerEdit
														 * data-toggle=modal
														 * data-target=#myCustomerEdit
														 * onclick='getCustomerFormEdit(" +
														 * row.customerID +
														 * ")'>" + "ABC" + "</a>"
														 */
													}
												}
												/*
												 * ,{ "data" : "HouseNumber" }
												 */,
												{
													"data" : "meterID"
												},
												{
													"data" : "meterID"
												},
												{
													"data" : "reading"
												},
												{
													"data" : "balance"
												},
												{
													"data" : "emergencyCredit"
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {
														return "<span id=color style = color:"
																+ row.batteryColor
																+ ">"
																+ row.battery
																+ "</span>"
													}
												},
												{
													"data" : "valveStatus"
												},
												{
													"data" : "tariff"
												},
												{
													"data" : "tamperStatus"
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {
														return "<span id=color style = color:"
																+ row.dateColor
																+ ">"
																+ row.timeStamp
																+ "</span>"
													}
												} ],
												"columnDefs" : [ {
													orderable : false,
													targets : [ 0 ]
												},
												{
													orderable : false,
													targets : [ 1 ]
												}],

										/*
										 * "columnDefs": [{ "visible": false,
										 * "targets": hCols }],
										 */
										"buttons" : [
												{
													extend : 'excel',
													footer : 'true',
													text : 'Excel',
													title : 'Dashboard'
												},

												{
													extend : 'pdf',
													footer : 'true',
													exportOptions : {
														columns : [ 1, 2, 3, 4,
																5, 6, 7, 8, 9,
																10, 11, 12 ]
													},
													text : 'pdf',
													orientation : 'landscape',
													title : 'Dashboard'
												} ]
									}).columns.adjust().draw();
								      ;
				});