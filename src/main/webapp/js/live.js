/**
 * 
 */
									
									$(document).ready(function() {
										table = $('#liveTable')
										.DataTable(
										{
										"processing" : false,
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
										]
										});
										});