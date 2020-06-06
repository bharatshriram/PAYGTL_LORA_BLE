/*  Author     : Bharat
   
 */

$(document).ready(function() {
	$("#login").click(function() {
		var user_id = $("#user_id").val();
		var user_password = $("#user_password").val();
		if (user_id == " ") {
			Materialize.toast('Enter User Name', 4000);
			return false;
		}
		
		if (user_password == "") {
			Materialize.toast('Enter Password', 4000);
			return false;
		}
		
		
		var data1 = {}
		data1["username"] = user_id;
		data1["password"] = user_password;

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/BGLWalkByAMR_Gas/login",
			data : JSON.stringify(data1),
			dataType : "JSON",

			success : function(data) {
				
				if (data.result == "Success") {
					//alert(data.userDetails.communityId);
					if (data.userDetails.userRole == 1) {
						sessionStorage.setItem("userRole", data.userDetails.userRole);
						sessionStorage.setItem("AMRID", data.userDetails.userRole);
						sessionStorage.setItem("ID", data.userDetails.communityId);
						sessionStorage.setItem("customerId", data.userDetails.customerId);
						sessionStorage.setItem("blockId", data.userDetails.blockId);
						sessionStorage.setItem("type", "SuperAdmin");

						var Role = data.userDetails.userRole;
					} else if (data.userDetails.userRole == 2) {

						sessionStorage.setItem("userRole", data.userDetails.userRole);
						sessionStorage.setItem("AMRID", data.userDetails.userRole);
						sessionStorage.setItem("ID", data.userDetails.communityId);
						sessionStorage.setItem("customerId", data.userDetails.customerId);
						sessionStorage.setItem("blockId", data.userDetails.blockId);
						sessionStorage.setItem("type", "Community");

						var Role = data.userDetails.userRole;
					} else if (data.userDetails.userRole == 3) {

						sessionStorage.setItem("userRole", data.userDetails.userRole);
						sessionStorage.setItem("AMRID", data.userDetails.AMRId);
						sessionStorage.setItem("communityID", data.userDetails.communityId);
						sessionStorage.setItem("ID", data.userDetails.customerId);
						sessionStorage.setItem("blockId", data.userDetails.blockId);
						sessionStorage.setItem("type", "Customer");

						var Role = data.userDetails.userRole;
					}
					window.location = "LoginAction.jsp?RoleID=" + Role;
				} else {
					let errorNode = document.createElement("div");
					errorNode.innerText = data.result;
					let node = document.querySelector("errordiv");
					errorNode.appendChild(node);
					Materialize.toast("Invalid Username and Password", 4000);
					return false;
				}
			}
		});
		return false;
	});
});

/* Community Page */
$(document)
.ready(
		function() {
			$("#communityAdd")
					.click(
							function() {
								
								var communityNameAdd = $("#communityNameAdd")
										.val();

								var mode = "add";

								var communityAddressAdd = $(
										"#communityAddressAdd").val();

								var communityEmailAdd = $(
										"#communityEmailAdd").val();

								
								  var communityMobileAdd = $(
								  "#communityMobileAdd").val();

								var society_Idpattern = /^[a-zA-Z]{2,20}$/;

								if (communityNameAdd == "") {

									toastr.info('Are you the 6 fingered man?')


									//Materialize.toast('Enter Community Name!', 4000);
									return false;
									
								}
								if (!society_Idpattern
										.test(society_add_name)) {
									Materialize.toast('Enter Valid Society!', 4000);
									return false;
								}

								if (society_add_email == "") {

									Materialize.toast('Enter Email!', 4000);
									return false;
								} else {
									var society_emailpattern = /^[a-zA-Z0-9._-]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/;
									if (!society_emailpattern
											.test(society_add_email)) {
										Materialize.toast('Email Id is not valid!' , 4000);
										return false;
									}
								}
								
								if (society_add_mobile == "") {

									Materialize.toast('Enter Mobile!', 4000);
									
									return false;
								}
								var mobilePattern = /^[6789]\d{9}$/;

								if (!mobilePattern
										.test(society_add_mobile)) {
									// alert("in mobile");
									Materialize.toast('Invalid Mobile Number' , 4000);
									return false;
								}
								
								
								if (society_add_tariff == "") {

									Materialize.toast('Select Charges!', 4000);
									
									return false;
								}
								if (society_add_address == "") {

									Materialize.toast('Select Address!', 4000);
									
									return false;
								}

								

								var data1 = {}

								data1["socName"] = society_add_name;
								data1["socEmail"] = society_add_email;
								data1["socMobile"] = society_add_mobile;
								data1["socCharge"] = society_add_tariff;
								data1["socAddress"] = society_add_address;
								

								console.log("77=>"
										+ JSON.stringify(data1));

								$
										.ajax({
											type : "POST",
											contentType : "application/json",
											url : "/Billing_AMR/registration/Society/0",

											data : JSON
													.stringify(data1),
											dataType : "JSON",
											success : function(data) {
												
												if (data.result == "Success") {
													alert(data.result);
													$('#societyAdd').prop('disabled', true).addClass('disabled').off( "click" );
													Materialize.toast('Added Successfully', 3000,'green',function(){
														
														window.location="community.jsp";
														
													})
												}

												else if(data.result == "Failure"){
													Materialize.toast(data.errorMessage, 4000);
													return false;
												}
											}
										});

							});
			return false;

		});


$(document)
.ready(
		function() {
			// alert("block_add_Id==>" + block_add_Id);

			$("#buildingAdd")
					.click(
							function() {
								var building_add_society = $(
										"#building_add_society").val();

								var mode = "add";

								var building_add_name = $(
										"#building_add_name")
										.val();

								var Community_Idpattern = /^[a-zA-Z]{2,20}$/;

								if (building_add_society == "" || building_add_society == null) {

									Materialize.toast(
											'Select Society Name', 4000);

									return false;
								}

								if (building_add_name == "") {

									Materialize.toast(
											'Enter Building Name', 4000);

									return false;
								}

								if (!Community_Idpattern
										.test(building_add_name)) {
									Materialize
											.toast(
													'Enter a valid Building name',
													4000);
									return false;
								}

								var data1 = {}

								data1["socId"] = building_add_society;
								data1["buildingName"] = building_add_name;

								console.log("77=>"
										+ JSON.stringify(data1));

								$
										.ajax({
											type : "POST",
											contentType : "application/json",
											url : "/Billing_AMR/registration/Building/0",

											data : JSON
													.stringify(data1),
											dataType : "JSON",
											success : function(data) {

												if (data.result == "Success") {
													$('#buildingAdd')
															.prop(
																	'disabled',
																	true)
															.addClass(
																	'disabled')
															.off(
																	"click");
													Materialize
															.toast(
																	'Added Successfully',
																	3000,
																	'green',
																	function() {

																		window.location = "Building.jsp";

																	})
												}

												else if (data.result == "Failure") {
													Materialize
															.toast(
																	data.errorMessage,
																	4000);
													return false;
												}

											}
										});
							});
			return false;
		});



