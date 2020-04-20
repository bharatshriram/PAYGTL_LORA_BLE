/**
 * 
 */
									
									/*$(document).ready(function() {
										table = $('#liveTable')
										.DataTable(
										{
										"processing" : false,
										dom: 'Bfrtip',
								        buttons: [
								            'copy', 'csv', 'excel', 'pdf', 'print'
								        ],
										"serverSide" : false,
										"bDestroy" : true,
										"pagging" : true,
										"bProcessing" : false,
										"ordering" : true,
										"order" : [ 0, "desc" ],
										"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
										"pageLength" : "5",
										"scrollY" : 324,
										"scrollX" : true,
										"ajax" : {
										"url":"/PAYGTL_LORA_BLE/dashboard/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
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
											"mData" : "action",
											"render" : function(data, type, row) {
												
												return '<div class=btn btn-secondary data-toggle="tooltip" data-placement=top ' +
														'title='+row.blockName+'>' 
												+row.HouseNumber +
												 '</div>';
												
												return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit("
																											+ row.customerID
																											+ ")'>"
																											+ "ABC"
																											+ "</a>"
											}
											}
										 ,{
										"data" : "HouseNumber"
										},{
										"data" : "meterID"
										},{
										"data" : "meterID"
										},{
										"data" : "reading"
										},{
										"data" : "balance"
										},{
										"data" : "emergencyCredit"
										},{
										"data" : "battery"
										},{
										"data" : "valveStatus"
										},{
										"data" : "tariff"
										},{
										"data" : "tamperStatus"
										},{
										"data" : "timeStamp"
										}
										]
										});
										});*/
									
									
									
									
									
									
									
									$(document).ready(function() {
										//Only needed for the filename of export files.
										//Normally set in the title tag of your page.document.title = 'Simple DataTable';
										//Define hidden columns
										var hCols = [3, 4];
										// DataTable initialisation
										$('#liveTable').DataTable({
											"dom": "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f<br/>i>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-12'p<br/>i>>",
											"serverSide" : false,
											"bDestroy" : true,
											"pagging" : true,
											"bProcessing" : false,
											"bPaginate": true,
											"ordering" : true,
											"order" : [ 0, "desc" ],
											"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
											"pageLength" : 5,
											"scrollY" : 324,
											"scrollX" : true,
											"ajax" : {
												"url":"/PAYGTL_LORA_BLE/dashboard/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
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
													"mData" : "action",
													"render" : function(data, type, row) {
														
														return '<div class=btn btn-secondary data-toggle="tooltip" data-placement=top ' +
																'title='+row.blockName+'>' 
														+row.HouseNumber +
														 '</div>';
														
														/*return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit("
																													+ row.customerID
																													+ ")'>"
																													+ "ABC"
																													+ "</a>"*/
													}
													}
												 /*,{
												"data" : "HouseNumber"
												}*/,{
												"data" : "meterID"
												},{
												"data" : "meterID"
												},{
												"data" : "reading"
												},{
												"data" : "balance"
												},{
												"data" : "emergencyCredit"
												},{
												"data" : "battery"
												},{
												"data" : "valveStatus"
												},{
												"data" : "tariff"
												},{
												"data" : "tamperStatus"
												},{
												"data" : "timeStamp"
												}
												],
											
											
											
											/*"columnDefs": [{
												"visible": false,
												"targets": hCols
											}],*/
											 "buttons": [
												 {extend: 'excel',
												        footer: 'true',
												        text: 'Excel',
												        title:'Dashboard'  },
												         
												        {extend: 'pdf',
												        footer: 'true',
												        exportOptions: {
												            columns: [1,2,3,4,5,6,7,8,9,10,11,12]
												        },
												        text: 'pdf',
												        orientation: 'landscape',
												        title:'Dashboard'  }
												]
											/*"buttons": [{
												extend: 'colvis',
												collectionLayout: 'three-column',
												text: function() {
													var totCols = $('#liveTable thead th').length;
													var hiddenCols = hCols.length;
													var shownCols = totCols - hiddenCols;
													return 'Columns (' + shownCols + ' of ' + totCols + ')';
												},
												prefixButtons: [{
													extend: 'colvisGroup',
													text: 'Show all',
													show: ':hidden'
												}, {
													extend: 'colvisRestore',
													text: 'Restore'
												}]
											}, {
												extend: 'collection',
												text: 'Export',
												buttons: [{
														text: 'Excel',
														extend: 'excelHtml5',
														footer: false,
														exportOptions: {
															columns: ':visible'
														}
													}, {
														text: 'CSV',
														extend: 'csvHtml5',
														fieldSeparator: ';',
														exportOptions: {
															columns: ':visible'
														}
													}, {
														text: 'PDF Portrait',
														extend: 'pdfHtml5',
														message: '',
														exportOptions: {
															columns: ':visible'
														}
													}, {
														text: 'PDF Landscape',
														extend: 'pdfHtml5',
														message: '',
														orientation: 'landscape',
														exportOptions: {
															columns: ':visible'
														}
													}]
												}]
											,oLanguage: {
							            oPaginate: {
							                sNext: '<span class="pagination-default">&#x276f;</span>',
							                sPrevious: '<span class="pagination-default">&#x276e;</span>'
							            }
							        }
												,"initComplete": function(settings, json) {
													// Adjust hidden columns counter text in button -->
													$('#liveTable').on('column-visibility.dt', function(e, settings, column, state) {
														var visCols = $('#liveTable thead tr:first th').length;
														//Below: The minus 2 because of the 2 extra buttons Show all and Restore
														var tblCols = $('.dt-button-collection li[aria-controls=liveTable] a').length - 2;
														$('.buttons-colvis[aria-controls=liveTable] span').html('Columns (' + visCols + ' of ' + tblCols + ')');
														e.stopPropagation();
													});
												}*/
											});
										});