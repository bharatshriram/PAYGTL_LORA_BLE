/**
 * 
 */
									
									$(document).ready(function() {
										table = $('#alarmTable')
										.DataTable(
										{
											"dom": "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f<br/>i>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-12'p<br/>i>>",
											/*"processing" : false,*/
											"serverSide" : false,
											"bDestroy" : true,
											"pagging" : true,
											"bPaginate": true,
											"bProcessing" : false,
											"ordering" : true,
											"order" : [ 0, "desc" ],
											"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
											"pageLength" : 5,
										"scrollY" : 324,
										"scrollX" : true,
										"ajax" : {
										"url":"/PAYGTL_LORA_BLE/alarm/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
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
												+row.houseNumber +
												 '</div>';
											}
											},{
										"data" : "meterID"
										},{
										"data" : "batteryVoltage"
										},{
										"data" : "tamper"
										},{
										"data" : "dateTime"
										},{
										"data" : "difference"
										}
										], "buttons": [
											   /* 'csvHtml5',
											'excelHtml5',
										'pdfHtml5'*/
											
											{extend: 'excel',
										        footer: 'true',
										        text: 'Excel',
										        title:'Statistics'  },
										         
										        {extend: 'pdf',
										        footer: 'true',
										        exportOptions: {
										            columns: [1,2,3,4,5,6,7]
										        },
										        text: 'pdf',
										        orientation: 'landscape',
										        title:'Statistics'  }
										]
										});
										});