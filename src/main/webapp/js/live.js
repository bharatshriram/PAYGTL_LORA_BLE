

$(document)
		.ready(
				function() {
					
					var dom1 ="<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>"
						+ "<'row'<'col-sm-12'tr>>"
						+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					
					var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					
					var filterId =(sessionStorage.getItem("filterId") == null) ? 0 :(sessionStorage.getItem("filterId"));
					//sessionStorage.remove("filterId");
					$("#liveTable1").hide();
					
					var hCols = [ 3, 4 ];
					// DataTable initialisation
					$('#liveTable')
							.DataTable(
									{
										"dom" : dom1,
												
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
												 
												    processing: true,
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
										"ajax" : {
											"url" : "/PAYGTL_LORA_BLE/dashboard/"
													+ sessionStorage
															.getItem("roleID")
													+ "/"
													+ sessionStorage
															.getItem("ID") + "/"+filterId,
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												return json.data;
											},
										},
										
										
										"columns" : [
											
												{
													"data" : "communityName",
													"defaultContent": ""
												},
												{
													"data" : "blockName",
													"defaultContent": ""
												},
												{
													"data" : "HouseNumber",
													"defaultContent": ""
													
												},
												{
													//"data": "timeStamp",
													
													"mData" : "action",
													"render" : function(data,
															type, row) {
														return "<span id=color style = color:"
																+ row.vacationColor
																+ ">"
																+ row.CRNNumber
																+ "</span>"
													},
													"defaultContent": ""
												},
												 {
													"data" : "meterSerialNumber",
													"defaultContent": ""
												},
												{
													"data" : "meterID",
													"defaultContent": ""
												},
												{
													"data" : "reading",
													"defaultContent": ""
												},
												{
													"data" : "consumption",
													"defaultContent": ""
												},
												{
													"data" : "balance",
													"defaultContent": ""
												},
												{
													"data" : "emergencyCredit",
													"defaultContent": ""
												},
												{
													"data" : "tariff",
													"defaultContent": ""
												},
												{
														"mData" : "action",
														"render" : function(data,
																type, row) {
															return "<span id=color style = color:"
																	+ row.valveStatusColor
																	+ ">"
																	+ row.valveStatus
																	+ "</span>"
														},
														"defaultContent": ""
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {
														return "<span id=color title="+row.tamperTimeStamp +" style = color:"
																+ row.tamperColor
																+ ">"
																+ row.tamperStatus
																+ "</span>"
													},
													"defaultContent": ""
												},
												{
													  //  "data":"battery"
													"mData" : "action",
													"render" : function(data,
															type, row) {
														if(!row.battery == undefined){
														}	
						//								console.log(data+"!!"+type+"@@"+JSON.stringify(row));
														if ( type === 'display' ) {
															return '<svg width="28pt" height="18pt" viewBox="0 0 338 168" version="1.1" xmlns="http://www.w3.org/2000/svg">'
															+'<g id="#000000ff">'
															+'<path fill="#000000" opacity="1.00" d=" M 10.50 0.00 L 73.50 0.00 C 73.51 6.99 73.48 13.97 73.52 20.96 C 56.02 21.05 38.51 20.97 21.01 21.00 C 20.94 40.85 21.11 60.70 20.92 80.54 C 13.95 80.46 6.97 80.52 0.00 80.50 L 0.00 17.49 C 3.50 17.51 6.99 17.50 10.49 17.49 C 10.52 11.66 10.49 5.83 10.50 0.00 Z" />'
																+'<path fill="#000000" opacity="1.00" d=" M 94.50 0.00 L 157.50 0.00 C 157.51 6.98 157.47 13.95 157.54 20.93 C 136.52 21.06 115.49 21.02 94.47 20.95 C 94.52 13.97 94.49 6.98 94.50 0.00 Z" />'
																+'<path fill="#000000" opacity="1.00" d=" M 178.50 0.00 L 241.50 0.00 C 241.54 6.95 241.41 13.91 241.59 20.86 C 220.56 21.16 199.51 20.96 178.48 20.96 C 178.52 13.97 178.49 6.99 178.50 0.00 Z" />'
																+'<path fill="#000000" opacity="1.00" d=" M 262.50 0.00 L 287.00 0.00 C 287.00 16.00 286.99 32.00 287.00 48.00 C 304.00 48.01 321.00 48.00 338.00 48.00 L 338.00 119.00 C 321.00 119.00 304.00 118.99 287.00 119.01 C 286.89 127.21 287.21 135.41 286.84 143.61 C 279.91 143.38 272.97 143.54 266.03 143.52 C 265.94 123.67 266.05 103.82 265.97 83.98 C 265.94 81.27 267.36 78.76 267.04 76.02 C 266.71 69.70 267.76 63.26 266.02 57.09 C 265.97 45.08 266.02 33.07 266.00 21.07 C 264.81 21.01 263.62 20.94 262.44 20.85 C 262.57 13.90 262.47 6.95 262.50 0.00 Z" />'
																+'<path fill="#000000" opacity="1.00" d=" M 0.00 101.50 C 6.99 101.50 13.98 101.51 20.97 101.49 C 21.07 120.12 20.92 138.76 21.05 157.39 C 19.87 157.45 18.69 157.51 17.51 157.57 C 17.50 161.04 17.49 164.52 17.50 168.00 L 0.00 168.00 L 0.00 101.50 Z" />'
																+'<path fill="#000000" opacity="1.00" d=" M 38.48 147.03 C 59.49 146.97 80.50 146.99 101.51 147.02 C 101.49 154.01 101.50 161.01 101.50 168.00 L 38.50 168.00 C 38.50 161.01 38.51 154.02 38.48 147.03 Z" />'
																+'<path fill="#000000" opacity="1.00" d=" M 122.45 147.10 C 143.47 146.91 164.50 146.99 185.53 147.05 C 185.47 154.04 185.51 161.02 185.50 168.00 L 122.50 168.00 C 122.48 161.03 122.55 154.06 122.45 147.10 Z" />'
																+'<path fill="#000000" opacity="1.00" d=" M 206.48 147.04 C 227.49 146.97 248.50 146.98 269.52 147.03 C 269.48 154.02 269.51 161.01 269.50 168.00 L 206.50 168.00 C 206.49 161.01 206.52 154.03 206.48 147.04 Z" />'
																+'</g>'
															+'<svg id="1f6e43ff" x="37" y="37" width="214" height="95" class = "batteryText">'
															+'<rect width="'+row.battery+'%" height="100%" fill="'+row.batteryColor+'"/>'
																  +'  <text x="0" y="50" font-family="Verdana" fill="white">'+row.battery+'</text>'
															+'</svg></svg>';
															
													    }
														
													},
													"defaultContent": ""
												},
												{
													//"data": "timeStamp",
													
													"mData" : "action",
													"render" : function(data,
															type, row) {
														return "<span id=color style = color:"
																+ row.vacationColor
																+ ">"
																+ row.vacationStatus
																+ "</span>"
													},
													"defaultContent": ""
												},
												{
													//"data": "timeStamp",
													
													"mData" : "action",
													"render" : function(data,
															type, row) {
														return "<span id=color style = color:"
																+ row.dateColor
																+ ">"
																+ row.timeStamp
																+ "</span>"
													},
													"defaultContent": ""
												} ],
												"columnDefs" : [ {
													"orderable" : true,
													"targets":  [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],
													"className": "dt-center", "targets": "_all"
												},
												{
													orderable : false,
													targets : [ 12 ]
												}],

										"buttons" : [
												{
													extend : 'excel',
													footer : 'true',
													//text : 'Excel',
													title : 'Dashboard',
													//className: 'custom-btn fa fa-file-excel-o'
												},

												{
													extend : 'pdf',
													footer : 'true',
													exportOptions : {
														columns : [ 0,1, 2, 3, 4,
																5, 6, 7, 8, 9,
																10, 11, 12,13,14,15 ]
													},
													orientation : 'landscape',
													title : 'Dashboard',
													//className: 'custom-btn fa fa-file-pdf-o',
													pageSize: 'LEGAL'
												},
												{
									               className: 'customButton',
									               text : "Adv Serach",
									              // extend : 'ADv',
									                action: function ( e, dt, button, config ) {
									                	$('.customButton').attr(
									                        {
									                            "data-toggle": "modal",
									                            "data-target": "#exampleModal"
									                        }
									                    );
									                }
									            }
												],
										 initComplete: function() {
											   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
											   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
											//   alert( 'Rows '+$('#liveTable').DataTable().rows( 'tr' ).count()+' are selected' );
										 }

									}).columns.adjust();
					
					
					
					
					$("div.headname").html('<h3>Customer Details</h3>');
					
					if(sessionStorage.getItem("filterId") != null ){
						$(".customButton").remove();
						
					}
					
					sessionStorage.removeItem("filterId")
					
					$("#dashboardFilter")
					.click(
							function() {

								if($("#end_date").val() != ""){
									if($("#start_date").val() == ""){
										bootbox.alert("Enter Date From")
										return false;
										}
									}
								
								
								var reading =/^^\d{7}$/;
								if($("#reading_from").val() != ""){
									if (!reading.test($("#reading_from").val())) {
										bootbox.alert("Invalid Reading From ");
										return false;
										}
									}
								
								if($("#reading_to").val() != ""){
									if (!reading.test($("#reading_to").val())) {
										bootbox.alert("Invalid Reading To")
										return false;
										}
									}
								
								
								
								var battery =/^[0-9]{0,3}$/;
								if($("#battery_from").val() != ""){
									if (!battery.test($("#battery_from").val())) {
										bootbox.alert("Invalid Battery (%) From ");
										return false;
										}
									}
								
								if($("#battery_to").val() != ""){
									if (!battery.test($("#battery_to").val())) {
										bootbox.alert("Invalid Battery (%) To")
										return false;
										}
									}
								
								var data1 = {}
								
								data1["dateFrom"] = $("#start_date").val() == "" ? "null":$("#start_date").val();
								data1["dateTo"] = $("#end_date").val() == "" ? "null":$("#end_date").val();
								data1["readingFrom"] = $("#reading_from").val() == "" ? "0":$("#reading_from").val();
								data1["readingTo"] = $("#reading_to").val() == "" ? "0":$("#reading_to").val()
								data1["batteryVoltageFrom"] = $("#battery_from").val() == "" ? "0":$("#battery_from").val()
								data1["batteryVoltageTo"] = $("#battery_to").val() == "" ? "0":$("#battery_to").val()
								data1["tamperType"] = $("#tamper").val();

								//alert(JSON.stringify(data1));
								
								$
										.ajax({
											type : "POST",
											contentType : "application/json",
											url : "/PAYGTL_LORA_BLE/filterdashboard/"+ sessionStorage
											.getItem("roleID")
											+ "/"
											+ sessionStorage
													.getItem("ID"),
											data : JSON
													.stringify(data1),
											dataType : "JSON",

											success : function(d) {
												
												//if (data.result == "Success") {
												$('#liveTable').dataTable()._fnAjaxUpdate();
												//$("#form").hide();
												//$("#tablereport").show();
													console.log(JSON.stringify(d));
													$("#liveTable_wrapper").hide();
													$("#exampleModal").modal("hide");
													$("#liveTable1").show();
													
													var hCols = [ 3, 4 ];
												table = $('#liveTable1')
													.DataTable(
															{
																
																"dom" : "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>"
																		+ "<'row'<'col-sm-12'tr>>"
																		+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
																		
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
																			"scrollX" : false,
																			"data" : d.data,
																			"columns" : [
																	
																		{
																			"data" : "communityName",
																			"defaultContent": ""
																		},
																		{
																			"data" : "blockName",
																			"defaultContent": ""
																		},
																		{
																			"data" : "HouseNumber",
																			"defaultContent": ""
																			
																		},{
																			//"data": "timeStamp",
																			
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				return "<span id=color style = color:"
																						+ row.vacationColor
																						+ ">"
																						+ row.CRNNumber
																						+ "</span>"
																			},
																			"defaultContent": ""
																		},
																		 {
																			"data" : "meterSerialNumber",
																			"defaultContent": ""
																		},
																		{
																			"data" : "meterID",
																			"defaultContent": ""
																		},
																		{
																			"data" : "reading",
																			"defaultContent": ""
																		},
																		{
																			"data" : "consumption",
																			"defaultContent": ""
																		},
																		{
																			"data" : "balance",
																			"defaultContent": ""
																		},
																		{
																			"data" : "emergencyCredit",
																			"defaultContent": ""
																		},
																		{
																			  //  "data":"battery"
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				if(!row.battery == undefined){
																				}	
																				//console.log(data+"!!"+type+"@@"+JSON.stringify(row));
																				/*if ( type === 'display' ) {
																					return "<span id=color style = color:"
																					+ row.batteryColor
																					+ ">"
																					+ row.battery
																					+ "</span>"
																			    }*/
																				
																				return '<svg width="28pt" height="18pt" viewBox="0 0 338 168" version="1.1" xmlns="http://www.w3.org/2000/svg">'
																				+'<g id="#000000ff">'
																				+'<path fill="#000000" opacity="1.00" d=" M 10.50 0.00 L 73.50 0.00 C 73.51 6.99 73.48 13.97 73.52 20.96 C 56.02 21.05 38.51 20.97 21.01 21.00 C 20.94 40.85 21.11 60.70 20.92 80.54 C 13.95 80.46 6.97 80.52 0.00 80.50 L 0.00 17.49 C 3.50 17.51 6.99 17.50 10.49 17.49 C 10.52 11.66 10.49 5.83 10.50 0.00 Z" />'
																					+'<path fill="#000000" opacity="1.00" d=" M 94.50 0.00 L 157.50 0.00 C 157.51 6.98 157.47 13.95 157.54 20.93 C 136.52 21.06 115.49 21.02 94.47 20.95 C 94.52 13.97 94.49 6.98 94.50 0.00 Z" />'
																					+'<path fill="#000000" opacity="1.00" d=" M 178.50 0.00 L 241.50 0.00 C 241.54 6.95 241.41 13.91 241.59 20.86 C 220.56 21.16 199.51 20.96 178.48 20.96 C 178.52 13.97 178.49 6.99 178.50 0.00 Z" />'
																					+'<path fill="#000000" opacity="1.00" d=" M 262.50 0.00 L 287.00 0.00 C 287.00 16.00 286.99 32.00 287.00 48.00 C 304.00 48.01 321.00 48.00 338.00 48.00 L 338.00 119.00 C 321.00 119.00 304.00 118.99 287.00 119.01 C 286.89 127.21 287.21 135.41 286.84 143.61 C 279.91 143.38 272.97 143.54 266.03 143.52 C 265.94 123.67 266.05 103.82 265.97 83.98 C 265.94 81.27 267.36 78.76 267.04 76.02 C 266.71 69.70 267.76 63.26 266.02 57.09 C 265.97 45.08 266.02 33.07 266.00 21.07 C 264.81 21.01 263.62 20.94 262.44 20.85 C 262.57 13.90 262.47 6.95 262.50 0.00 Z" />'
																					+'<path fill="#000000" opacity="1.00" d=" M 0.00 101.50 C 6.99 101.50 13.98 101.51 20.97 101.49 C 21.07 120.12 20.92 138.76 21.05 157.39 C 19.87 157.45 18.69 157.51 17.51 157.57 C 17.50 161.04 17.49 164.52 17.50 168.00 L 0.00 168.00 L 0.00 101.50 Z" />'
																					+'<path fill="#000000" opacity="1.00" d=" M 38.48 147.03 C 59.49 146.97 80.50 146.99 101.51 147.02 C 101.49 154.01 101.50 161.01 101.50 168.00 L 38.50 168.00 C 38.50 161.01 38.51 154.02 38.48 147.03 Z" />'
																					+'<path fill="#000000" opacity="1.00" d=" M 122.45 147.10 C 143.47 146.91 164.50 146.99 185.53 147.05 C 185.47 154.04 185.51 161.02 185.50 168.00 L 122.50 168.00 C 122.48 161.03 122.55 154.06 122.45 147.10 Z" />'
																					+'<path fill="#000000" opacity="1.00" d=" M 206.48 147.04 C 227.49 146.97 248.50 146.98 269.52 147.03 C 269.48 154.02 269.51 161.01 269.50 168.00 L 206.50 168.00 C 206.49 161.01 206.52 154.03 206.48 147.04 Z" />'
																					+'</g>'
																				+'<svg id="1f6e43ff" x="37" y="37" width="214" height="95" class = "batteryText">'
																				+'<rect width="'+row.battery+'%" height="100%" fill="'+row.batteryColor+'"/>'
																					  +'  <text x="0" y="50" font-family="Verdana" fill="white">'+row.battery+'</text>'
																				+'</svg></svg>';
																				
																			},
																			"defaultContent": ""
																		},
																		{
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				return "<span id=color style = color:"
																						+ row.valveStatusColor
																						+ ">"
																						+ row.valveStatus
																						+ "</span>"
																			},
																			"defaultContent": ""
																	},
																		{
																			"data" : "tariff",
																			"defaultContent": ""
																		},
																		{
																			//"data": "timeStamp",
																			
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				return "<span id=color title="+row.tamperTimeStamp +"style = color:"
																						+ row.tamperColor
																						+ ">"
																						+ row.tamperStatus
																						+ "</span>"
																			},
																			"defaultContent": ""
																		},
																		{
																			//"data": "timeStamp",
																			
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				return "<span id=color style = color:"
																						+ row.vacationColor
																						+ ">"
																						+ row.vacationStatus
																						+ "</span>"
																			},
																			"defaultContent": ""
																		},
																		
																		{
																			"data": "timeStamp",
																			"defaultContent": "",
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
																			"orderable" : true,
																			"targets":  [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],
																			"className": "dt-center", "targets": "_all"
																		},
																		{
																			orderable : false,
																			targets : [ 12 ]
																		}],

																
																  "columnDefs": [{ "visible": false }],
																 
																"buttons" : [
																	{
																		extend : 'excel',
																		footer : 'true',
																		//text : 'Excel',
																		title : 'Dashboard',
																	//	className: 'custom-btn fa fa-file-excel-o'
																			
																	},

																	{
																		extend : 'pdf',
																		footer : 'true',
																		exportOptions : {
																			columns : [ 0,1, 2, 3, 4,
																					5, 6, 7, 8, 9,
																					10, 11, 12,13,14 ]
																		},
																		orientation : 'landscape',
																		title : 'Dashboard',
																		pageSize: 'LEGAL',
																		//	className: 'custom-btn fa fa-file-pdf-o'
																	},
																	
																	{
															               className: 'customButton',
															               text : "Adv Serach",
															              // extend : 'ADv',
															                action: function ( e, dt, button, config ) {
															                	$('.customButton').attr(
															                        {
															                            "data-toggle": "modal",
															                            "data-target": "#exampleModal"
															                        }
															                    );
															                }
															            },
																		{
															                text: 'Reset',
															                action: function ( e, dt, node, config ) {
															                    alert( 'Button activated' );
															                },
															                className: 'customButton',
															               
															                action: function ( e, dt, button, config ) {
															                   
															                	window.location = "LiveDashBoard.jsp"
															                }
															            }
																		],
																		 initComplete: function() {
																			   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																			   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																			  }

															})
											}
										});
								return false;
							});

					$("#resetFilter")
					.click(
							function() {
								
								 $("input:text").val("");
					
							});	
				});




