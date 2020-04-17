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
										 /*{
										    	"data" : "communityName"
										 },*/{
										"data" : "block"
										},{
										"data" : "HouseNumber"
										},{
										"data" : "1212"
										},{
										"data" : "54545"
										},{
										"data" : "reading"
										},{
										"data" : "balance"
										},{
										"data" : "emergencyCredit"
										},{
										"data" : "battery"
										},{
										"data" : "valve"
										},{
										"data" : "tariff"
										},{
										"data" : "tamper"
										},{
										"data" : "timeStamp"
										}
										]
										});
										});