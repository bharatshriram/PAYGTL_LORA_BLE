

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
										"dom" : "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>"
												+ "<'row'<'col-sm-12'tr>>"
												+ "<'row'<'col-sm-12'p<br/>i>>",
												
												responsive: {
											        details: {
											            renderer: function ( api, rowIdx ) {
											            var data = api.cells( rowIdx, ':hidden' ).eq(0).map( function ( cell ) {
											                var header = $( api.column( cell.column ).header() );
											                return  '<p style="color:#00A">'+header.text()+' : '+api.cell( cell ).data()+'</p>';  // changing details mark up.
											            } ).toArray().join('');
											 
											            return data ?    $('<table/>').append( data ) :    false;
											            }
											        }
											        },
												/*"pagingType" : 'full_numbers',*/
												/*"responsive" : true,
												"processing" : true,
												"serverSide" : true,
												"bDestroy" : true,
												"bPaginate": true,
												"pagging" : true,
												"bProcessing" : true,
												"ordering" : true,*/
												/*"processing" : true,
												"serverSide" : true,
												"bDestroy" : true,
												"pagging" : true,
												"bProcessing" : true,
												"ordering" : true,
												"order" : [ 0, "desc" ],
												"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
												"pageLength" : 5,
												"scrollY" : 324,
												"scrollX" : true,	
												 "deferRender": true,*/
												"language": {
												      "emptyTable": "No data available in table"
												    },
												 
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
													"data" : "HouseNumber"
													
												},
												 {
														"data" : "CRNNumber"
												},
												 {
													"data" : "meterSerialNumber"
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
													    //"data":"battery"
													"mData" : "action",
													"render" : function(data,
															type, row) {
														if(!row.battery == undefined){
															
														console.log(data+"!!"+type+"@@"+JSON.stringify(row));
														if ( type === 'display' ) {
															return "<span id=color style = color:"
															+ row.batteryColor
															+ ">"
															+ row.battery
															+ "</span>"
													    }
														}
													},
													"defaultContent": "",
													orderable: false 
												},
												{
													"data" : "valveStatus"
												},
												{
													"data" : "tariffName",
													"defaultContent": "",
													orderable: false 
												},
												{
													"data" : "tamperStatus",
														"defaultContent": "",
														orderable: false 
												},
												{
													"data": "timeStamp",
													"defaultContent": "",
													orderable: false 
													/*"mData" : "action",
													"render" : function(data,
															type, row) {
														return "<span id=color style = color:"
																+ row.dateColor
																+ ">"
																+ row.timeStamp
																+ "</span>"
													}*/
												} ],
												"columnDefs" : [ {
													"orderable" : false,
													"targets":  9 
												},
												{
													orderable : false,
													targets : [ 12 ]
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

									}).columns.adjust().draw().clear().draw().rows.add().draw();
				});