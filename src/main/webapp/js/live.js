

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
										"dom" : "<'row'<'col-sm-4 custombutton'B><'col-sm-2'l><'col-sm-6'f>>"
												+ "<'row'<'col-sm-12'tr>>"
												+ "<'row'<'col-sm-6 text-white'i><'col-sm-6 text-white'p>>",
												
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
												 
												/* "responsive" : true,
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
													"scrollX" : true,*/
												    
/*												    destroy: true,
*/												    processing: true,
												    serverSide: false,
												    fixedColumns    : true
												    ,autoWidth  : true
												    ,responsive : true
												    ,deferRender    : true
												    ,processing : true
												    ,paging     : true
												    ,pageLength : 5
												    ,searching  : true
												    ,info       : true,
												    "ordering" : true,
													"order" : [ 0, "desc" ],
													"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ]
												    ,bPaginate  : false,
												    "scrollY" : 300,
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
													  //  "data":"battery"
													"mData" : "action",
													"render" : function(data,
															type, row) {
														if(!row.battery == undefined){
														}	
														console.log(data+"!!"+type+"@@"+JSON.stringify(row));
														if ( type === 'display' ) {
															return "<span id=color style = color:"
															+ row.batteryColor
															+ ">"
															+ row.battery
															+ "</span>"
													    }
														
													},
													"defaultContent": ""
												},
												{
													"data" : "valveStatus"
												},
												{
													"data" : "tariff",
													"defaultContent": ""
												},
												{
													"data" : "tamperStatus",
														"defaultContent": ""
												},
												{
													"data": "timeStamp",
													"defaultContent": ""
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
													"orderable" : true,
													"targets":  [0,1,2,3,4,5,6,7,8,9,10,11,12,13],
													"className": "dt-center", "targets": "_all"
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
													//extend : 'excel',
													footer : 'true',
													//text : 'Excel',
													title : 'Dashboard',
													className: 'custom-btn fa fa-file-excel-o'
														
												},

												{
													//extend : 'pdf',
													footer : 'true',
													exportOptions : {
														columns : [ 0,1, 2, 3, 4,
																5, 6, 7, 8, 9,
																10, 11, 12,13 ]
													},
													//text : 'pdf',
													className: 'custom-btn fa fa-file-pdf-o',
													orientation : 'landscape',
													title : 'Dashboard'
												},
												{
									                //text: 'AdvSerach',
									                action: function ( e, dt, node, config ) {
									                    alert( 'Button activated' );
									                },
									                className: 'customButton fa fa-search-plus ',
									               
									                action: function ( e, dt, button, config ) {
									                    $('.custombutton').attr(
									                        {
									                            "data-toggle": "modal",
									                            "data-target": "#exampleModal"
									                        }
									                    );
									                //    var selected = dt.row( { selected: true } ).data(); 
									                	$('#exampleModal').modal('show');
									                }
									            }
												]

									}).columns.adjust();
					
					
					$("#dashboardFilter")
					.click(
							function() {

								var data1 = {}
								
								data1["communityID"] = $(
										"#start_date").val();
								data1["blockID"] = $(
										"#end_date")
										.val();
								data1["CRNNumber"] = $(
										"#reading_from")
										.val();
								data1["meterID"] = $("#reading_to")
										.val();
								data1["fromDate"] = $("#battery_from")
										.val();
								data1["toDate"] = $("#battery_to").val();
								
								
								data1["fromDate"] = $("#tamper")
								.val();
						data1["toDate"] = $("#mode").val();

								
								$
										.ajax({
											type : "POST",
											contentType : "application/json",
											url : "/PAYGTL_LORA_BLE/userconsumptionreports",
											data : JSON
													.stringify(data1),
											dataType : "JSON",

											success : function(d) {
												
												//if (data.result == "Success") {
												
												$("#form").hide();
												$("#tablereport").show();

												 table = $('#liveTable')
													.DataTable(
															{
																"dom" : "<'row'<'col-sm-4 custombutton'B><'col-sm-2'l><'col-sm-6'f>>"
																		+ "<'row'<'col-sm-12'tr>>"
																		+ "<'row'<'col-sm-6 text-white'i><'col-sm-6 text-white'p>>",
																		
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
																	
																		"language": {
																		      "emptyTable": "No data available in table"
																		    },
																		 
																		
																		    
						/*												    destroy: true,
						*/												    processing: true,
																		    serverSide: false,
																		    fixedColumns    : true
																		    ,autoWidth  : true
																		    ,responsive : true
																		    ,deferRender    : true
																		    ,processing : true
																		    ,paging     : true
																		    ,pageLength : 5
																		    ,searching  : true
																		    ,info       : true,
																		    "ordering" : true,
																			"order" : [ 0, "desc" ],
																			"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ]
																		    ,bPaginate  : false,
																		    "scrollY" : 300,
																			"scrollX" : true,
																			"data" : d.data,
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
																			  //  "data":"battery"
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				if(!row.battery == undefined){
																				}	
																				console.log(data+"!!"+type+"@@"+JSON.stringify(row));
																				if ( type === 'display' ) {
																					return "<span id=color style = color:"
																					+ row.batteryColor
																					+ ">"
																					+ row.battery
																					+ "</span>"
																			    }
																				
																			},
																			"defaultContent": ""
																		},
																		{
																			"data" : "valveStatus"
																		},
																		{
																			"data" : "tariff",
																			"defaultContent": ""
																		},
																		{
																			"data" : "tamperStatus",
																				"defaultContent": ""
																		},
																		{
																			"data": "timeStamp",
																			"defaultContent": ""
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
																			"orderable" : true,
																			"targets":  [0,1,2,3,4,5,6,7,8,9,10,11,12,13],
																			"className": "dt-center", "targets": "_all"
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
																				columns : [ 0,1, 2, 3, 4,
																						5, 6, 7, 8, 9,
																						10, 11, 12,13 ]
																			},
																			text : 'pdf',
																			orientation : 'landscape',
																			title : 'Dashboard'
																		},
																		{
															                text: 'Adv Serach',
															                action: function ( e, dt, node, config ) {
															                    alert( 'Button activated' );
															                },
															                className: 'customButton',
															               
															                action: function ( e, dt, button, config ) {
															                    $('.custombutton').attr(
															                        {
															                            "data-toggle": "modal",
															                            "data-target": "#exampleModal"
															                        }
															                    );
															                //    var selected = dt.row( { selected: true } ).data(); 
															                	$('#exampleModal').modal('show');
															                }
															            }
																		]

															}).columns.adjust();
													//table.ajax.reload()
											//	}
											}
										});
								return false;
							});
					
					
				});




