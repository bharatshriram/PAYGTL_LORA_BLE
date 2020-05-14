
/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		$("#blockAddButton").show();
	}else{
		$("#blockAddButton").remove();
		
	}
table = $('#blockTable')
.DataTable(
{
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
"url":"/PAYGTL_LORA_BLE/block/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
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
"data" : "Location"
},{
"data" : "email"
}
,{
"data" : "mobile"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		/*<button type="button"
			class="btn btn-raised btn-primary float-right"
			data-toggle="modal" data-target="#exampleModal">
			<i class="fa fa-user"></i>
		</button>*/
	//return "<a href='#communityEditModal' class='teal modal-trigger' data-toggle='modal' data-target='#communityEditModal' id='communityEditModal' onclick='getSocietyFormEdit("+row.communityID+")'><i class='material-icons' style='color:#17e9e9'>edit</i></a>"
		
		return "<div id = actionfield> <a href=# id=BlockEdit data-toggle=modal data-target=#myBlockEdit onclick='getBlockFormEdit("
																	+ row.blockID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getBlockFormDelete("
																	+ row.blockID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																	+ "</a></div>"
	}
	}



],
"columnDefs" : [ {
//	orderable : false,
	targets: 5, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
},
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
	   
	]
});
});






$(document)
				.ready(
						function() {
							$('#blockDetails')
							
							/*.find('[name="selectcommunityName"]')
            .selectpicker()
            .change(function(e) {
                $('#blockDetails').bootstrapValidator('revalidateField', 'selectcommunityName');
            })
            .end()*/
							
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													/*selectcommunityName: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select your native language.'
									                        }
									                    }
									                },*/
													blockNameAdd : {
														message : 'The Block Name is not valid',
														validators : {
															notEmpty : {
																message : 'The Block Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'The Block Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'The Block Name can only consist of Alphanumaric'
															}
														}
													},
													blockLocationAdd : {
														message : 'The Location is not valid',
														validators : {
															notEmpty : {
																message : 'The Location is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'The Locaton must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]+$/,
																message : 'The Location can only consist of alphabetical'
															}
														}
													},
													blockMobileAdd : {
														message : 'The Mobile is not valid',
														validators : {
															notEmpty : {
																message : 'The Mobile is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'The Mobile can only consist of number'
															}
														}
													},
													blockEmailAdd : {
														message : 'The Email is not valid',
														validators : {
															notEmpty : {
																message : 'The Email is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													}
												}
											});
							
							
							
							
							$('#blockEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											blockNameEdit : {
												message : 'The Block Name is not valid',
												validators : {
													notEmpty : {
														message : 'The Block Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'The Block Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'The Block Name can only consist of Alphanumaric'
													}
												}
											},
											blockLocationEdit : {
												message : 'The Location is not valid',
												validators : {
													notEmpty : {
														message : 'The Location is required and cannot be empty'
													},
													stringLength : {
														min : 6,
														max : 30,
														message : 'The Location must be more than 6 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z ]+$/,
														message : 'The Location can only consist of alphabetical'
													}
												}
											},
											blockMobileEdit : {
												message : 'The Mobile is not valid',
												validators : {
													notEmpty : {
														message : 'The Mobile is required and cannot be empty'
													},
													regexp : {
														regexp : /^\d{10}$/,
														message : 'The Mobile can only consist of number'
													}
												}
											},
											blockEmailEdit : {
												message : 'The Email is not valid',
												validators : {
													notEmpty : {
														message : 'The Email is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'The Community Address can only consist of alphabetical and number'
													}*/
												}
											}
										}
									});
							
							
							
							

							$('#blockDetails')
									.on(
											'status.field.bv',
											function(e, data) {
												formIsValid = true;
												$('.input-group.form-group', $(this))
														.each(
																function() {
																//	alert(this+"@@=>"+formIsValid);
																	formIsValid = formIsValid
																			&& $(
																					this)
																					.hasClass(
																							'has-success');
																	
																	//alert("!!@@=>"+formIsValid);
																	
																});

												if (formIsValid) {
													$('.submit-button', $(this))
															.attr('disabled',
																	false).attr('class', 'btn btn-success submit-button');;
												} else {
													$('.submit-button', $(this))
															.attr('disabled',
																	true);
												}
											});

							
							
							
							$('#blockEdit').on(
									'status.field.bv',
									function(e, data) {
										formIsValid = true;
										$('.input-group.form-group', $(this))
												.each(
														function() {
														//	alert(this+"@@=>"+formIsValid);
															formIsValid = formIsValid
																	&& $(
																			this)
																			.hasClass(
																					'has-success');
															
															//alert("!!@@=>"+formIsValid);
															
														});

										if (formIsValid) {
											$('#blockEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#blockEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							
												$(document).on('click', '#blockAdd', function () {
													 
												
												//alert(""+$("#selectcommunityName").val());

												if($("#selectcommunityName").val() == -1 || $("#selectcommunityName").val() == null || $("#selectcommunityName").val() == "Select Community"){
													bootbox
													.alert("Select Community Id");
													return false;
												}
												
												var data1 = {}
												data1["communityID"] = $("#selectcommunityName").val();
												data1["blockName"] = $("#blockNameAdd").val();
												data1["location"] = $("#blockLocationAdd").val();
												data1["mobileNumber"] = $("#blockMobileAdd").val();
												data1["email"] = $("#blockEmailAdd").val();
												data1["createdByID"] = sessionStorage.getItem("createdByID");
												data1["loggedInUserID"] = sessionStorage.getItem("userID");
												data1["roleID"] = sessionStorage.getItem("roleID");

												$('#blockAdd').prop('disabled', true).addClass('disabled').off( "click" );
												
												/*console.log("===>"
														+ JSON.stringify(data1));*/
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "/PAYGTL_LORA_BLE/block/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {

																if (data.result == "Success") {

																	bootbox.alert(data.Message,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "blockDetails.jsp";
																		return false;
																	});
																
																	

																} else if(data.result == "Failure"){
									
																	/*bootbox.alert(data.Message,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "blockDetails.jsp";
																		return false
																				});*/
																	
																	bootbox.alert(data.Message);
																	return false;
																	
																	
																}else {
																	
																	bootbox.alert("SomeThing went Wrong",
																			function(
																					result) {
																					
																		//alert();
																		window.location = "blockDetails.jsp";
																		return false;
																	});
																	
																}
															}
														});
												return false;
											});
							
							
							
							$("#blockEditsave")
							.click(
									function() {

										var data1 = {}
										
										var data1 = {}
										data1["blockName"] = $("#blockNameEdit").val();
										data1["location"] = $("#blockLocationEdit").val();
										data1["mobileNumber"] = $("#blockMobileEdit").val();
										data1["email"] = $("#blockEmailEdit").val();
										data1["createdByID"] = sessionStorage.getItem("createdByID");
										data1["loggedInUserID"] = sessionStorage.getItem("userID");
										data1["roleID"] = sessionStorage.getItem("roleID");
								
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$('#blockEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/PAYGTL_LORA_BLE/block/edit/"+$("#blockIdhidden").val(),
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
																window.location = "blockDetails.jsp";
																return false;
															});
															

														} else if(data.result == "Failure"){
															
															bootbox.alert(data.Message);
															return false;
															//});
														}
													}
												});
										return false;
									});
							
							
						});




function getBlockFormEdit(id) {

 // alert(id);

	$.getJSON("/PAYGTL_LORA_BLE/block/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.blockID) {
				
				$('#communityNameEdit').val(item.communityName).trigger("change");
				$("#formcomunityName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockNameEdit').val(item.blockName).trigger("change");
				$("#formblockName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockLocationEdit').val(item.Location).trigger("change");
				$("#formblocklocation").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#blockMobileEdit').val(item.mobile).trigger("change");
				$("#formblockMobile").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockEmailEdit').val(item.email).trigger("change");
				$("#formblockEmail").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$("#blockIdhidden").val(item.blockID);
			
				$('#blockEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myBlockEdit').modal('show');
	});
}



function getBlockFormDelete(blockId){
	
	//alert(""+blockId);
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELEE BLOCK",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/PAYGTL_LORA_BLE/block/delete/" + blockId,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "blockDetails.jsp";
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