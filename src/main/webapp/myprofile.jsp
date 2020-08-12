<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16" href="common/images/1-hanbit.png">
<!-- Material Design for Bootstrap CSS -->
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
	integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" href="common/css/style.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">

<title>Profile</title>
</head>


<body  class="innerbody">
	<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (null == user_id) {
			response.sendRedirect("login.jsp");
		}else{
	%>
	
	<jsp:include page="header.jsp" />
	<div
		class="container-fluid topspacing bottomspacing pl-0 pr-0 mr-0 ml-0">
		<div class="row mr-0 ml-0">
			<div class="col-md-2 pl-0 pr-0">

				<jsp:include page="menu.jsp" /> 
			</div>
			<div class="col-md-10 mt-4 mb-4">
			 <!--Right start-->
        <div class="row mb-4">
          <div class="col-md-10 m-auto">
            <div class="card">
                <div class="card-header bg-primary cardHeading">Profile</div>
                <div class="card-body scroll right-block">
                <%if(user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("4")){ %>
                   <div class="row border-bottom p-2">
                       <div class="col-md-3">Name:</div>
                        <div class="col-md-9" id="profileName"></div>
                        
                     </div>
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Email:</div>
                        <div class="col-md-9" id="email"></div>
                        
                      </div>
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Mobile:</div>
                        <div class="col-md-9" id="mobile"></div>
                      </div>
                      <%} %>
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Password:</div>
                        <div class="col-md-5">xxxxxxx</div>
                        <div class="col-md-2 change_pwd text-danger"><b style="cursor: pointer;">Change Password</b></div>
                       
                      </div>
                      
                      <div class="row p-2 pwd_block">
                        <div class="col-md-3">Password:</div>
                        <div class="col-md-9">
                        <form id="profile">
                          <div class="col-md-12">
                            <div class="form-group">
                              <label class="bmd-label-floating">Old Password<sup class="imp">*</sup></label>
                              <input type="password" class="form-control" id="oldpassword" name="oldpassword">
                            </div>
                          </div>
                          <div class="col-md-12">
                            <div class="form-group">
                              <label class="bmd-label-floating">New Password<sup class="imp">*</sup></label>
                              <input type="password" class="form-control" id="newpassword" name="newpassword">
                            </div>
                          </div>
                          <div class="col-md-12">
                            <div class="form-group">
                              <label class="bmd-label-floating">Confirm  Password<sup class="imp">*</sup></label>
                              <input type="password" class="form-control" id="confirmpassword" name="confirmpassword">
                            </div>
                          </div>
                          <div class="col-md-12 text-right">
                            <button type="button" class="btn btn-primary btn-raised submit-button" id="profilebutton" disabled>Save</button>
                          </div>
                          </form>
                        </div>
                       </div>
                       
                       <%if(user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("5")){ %>
                    	   
                       
                       
					<div class="row border-bottom p-2">
                        <div class="col-md-10 text-center text-primary">Community Details</div>
                      </div>
                       <div class="row border-bottom p-2">
                        <div class="col-md-3">Name:</div>
                        <div class="col-md-5" id= "communityName"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Address:</div>
                        <div class="col-md-5" id= "communityAddress"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Email:</div>
                        <div class="col-md-5" id= "communityEmail"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Mobile:</div>
                        <div class="col-md-5" id= "communityMobile"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-10 text-center text-primary">Block Details</div>
                         <% if(user_id.equalsIgnoreCase("2")){%>
                          <div class="col-md-2 text-primary" onClick= "getBlock()"><b style="cursor: pointer;">Edit</b></div>
                          <%}%>
                      </div>
                      
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Name:</div>
                        <div class="col-md-5 blockNameEdit"></div>
                      </div>
                      
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Location:</div>
                        <div class="col-md-5 blockLocationEdit"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Email:</div>
                        <div class="col-md-5 blockEmailEdit"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Mobile:</div>
                        <div class="col-md-5 blockMobileEdit"></div>
                      </div>
                      <%} else if(user_id.equalsIgnoreCase("3")){%>
                      
					<div class="row border-bottom p-2">
                        <div class="col-md-10 text-center text-primary">Customer Details</div>
					<% if(user_id.equalsIgnoreCase("3")){%>
                          <div class="col-md-2 text-primary" onClick= "getCustomer()"><b style="cursor: pointer;">Edit</b></div>
                          <%}%>
                      </div>
                      
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Community Name:</div>
                        <div class="col-md-5 communityNameEdit"></div>
                      </div>
                      
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Block Name:</div>
                        <div class="col-md-5 blockNameEdit"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">First Name:</div>
                        <div class="col-md-5 firstNameEdit"></div>
                      </div>
                      
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Last Name:</div>
                        <div class="col-md-5 lastNameEdit"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">CRN Number:</div>
                        <div class="col-md-5 CRNEdit"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">House No:</div>
                        <div class="col-md-5 houseNoEdit"></div>
                      </div>
                      
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">MIU:</div>
                        <div class="col-md-5 amrEdit"></div>
                      </div>
                      
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">MSN:</div>
                        <div class="col-md-5 meterSerialEdit"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Mobile:</div>
                        <div class="col-md-5 mobileNoEdit"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Email:</div>
                        <div class="col-md-5 emailEdit"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Created By UserName:</div>
                        <div class="col-md-5 createdUserNameEdit"></div>
                      </div>
                      
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Created By Role:</div>
                        <div class="col-md-5 createdRoleEdit"></div>
                      </div>
                      
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Registration Date:</div>
                        <div class="col-md-5 registrationDateEdit"></div>
                      </div>
                      <% }%>
                </div>
              </div>
          </div>
        </div>
       

        <!--Right end-->
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<%if(user_id.equalsIgnoreCase("2")){ %>
	<div class="modal fade" id="myBlockEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Block</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="blockEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formcomunityName" class="input-group form-group">
									<label class="bmd-label-floating">Community</label> <input
										type="text" class="communityNameEdit form-control " name="communityNameEdit"
										id="communityNameEdit" disabled>
								</div>
							</div>
							<div class="col-md-6">
								<div id="formblockName" class="input-group form-group">
									<label class="bmd-label-floating">Block Name</label> <input
										type="text" class="blockNameEdit form-control" name="blockNameEdit"
										id="blockNameEdit">
								</div>
							</div>

							<div class="col-md-6">
								<div id="formblocklocation" class="input-group form-group">
									<label class="bmd-label-floating">Location</label> <input
										type="text" class="blockLocationEdit form-control" name="blockLocationEdit"
										id="blockLocationEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formblockMobile" class="input-group form-group">
									<label class="bmd-label-floating">Mobile Number</label> <input
										type="text" class="blockMobileEdit form-control" name="blockMobileEdit"
										id="blockMobileEdit">
								</div>
							</div>

							<div class="col-md-6">
								<div id="formblockEmail" class="input-group form-group">
									<label class="bmd-label-floating">Email</label> <input
										type="email" class="blockEmailEdit form-control" name="blockEmailEdit"
										id="blockEmailEdit">
										
										 <input
										type="hidden" id="blockIdhidden">
										
								</div>
							</div>
							<div class="col-md-6">
							</div>

							<div class="col-md-4">
									<button class="btn btn-secondary submit-button"
									 value="Save!" id="blockEditsave"
									type="button" disabled>Update</button>
							</div>

							<div class="col-md-3">
								<button type="button" class="btn btn-secondary btn-raised mr-3 resetFilter" id="resetFilter">Reset</button>
							</div>


							<div class="col-md-4">
								<button type="button" class="btn btn-danger btn-raised mr-4"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


<%} else if(user_id.equalsIgnoreCase("3")){ %>

<div class="modal fade" id="myCustomerEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Customer</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="customerEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formcommunityNameEdit" class="input-group form-group">
									<label class="bmd-label-floating">Community</label> 
									 <input
										type="text" class="form-control" name="communityNameEdit"
										id="communityNameEdit" disabled>
								</div>
							</div>
							<div class="col-md-6">
								<div id="formblockNameEdit" class="input-group form-group">
									<label class="bmd-label-floating">Block</label> <input
										type="text" class="form-control" name="blockNameEdit"
										id="blockNameEdit" disabled>
								</div>
							</div>

							<div class="col-md-6">
								<div id="formfirstNameEdit" class="input-group form-group">
									<label class="bmd-label-floating">First Name</label> <input
										type="text" class="form-control" name="firstNameEdit"
										id="firstNameEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formlastNameEdit" class="input-group form-group">
									<label class="bmd-label-floating">Last Name</label> <input
										type="text" class="form-control" name="lastNameEdit"
										id="lastNameEdit" disabled>
								</div>
							</div>
							
							
							
							<div class="col-md-6">
								<div id="formhouseNoEdit" class="input-group form-group">
									<label class="bmd-label-floating">House No.</label> <input
										type="text" class="form-control" name="houseNoEdit"
										id="houseNoEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formmobileNoEdit" class="input-group form-group">
									<label class="bmd-label-floating">Mobile No</label> <input
										type="text" class="form-control" name="mobileNoEdit"
										id="mobileNoEdit">
								</div>
							</div>
							
							
							
							
							<div class="col-md-6">
								<div id="formemailEdit" class="input-group form-group">
									<label class="bmd-label-floating">Email</label> <input
										type="email" class="form-control" name="emailEdit"
										id="emailEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formmeterSerialEdit" class="input-group form-group">
									<label class="bmd-label-floating">MSN</label> <input
										type="text" class="form-control" name="meterSerialEdit"
										id="meterSerialEdit" disabled>
								</div>
							</div>
							
							
							
							
							<div class="col-md-6">
								<div id="formamrEdit" class="input-group form-group">
									<label class="bmd-label-floating">MIU ID</label> <input
										type="text" class="form-control" name="amrEdit"
										id="amrEdit">
								</div>
							</div>
							<!-- <div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Tariff Name</label>  <select
										class="form-control" id="selectTariffNameEdit" name="selectTariffNameEdit">
										<option style = "color: Red" value="" disabled selected>Select Tariff</option> --><!--  <option>Select Community</option> 
									</select>
								</div>
							</div> -->
							
							
							<div class="col-md-6">
								<div id="formCRNEdit" class="input-group form-group">
									<label class="bmd-label-floating">CRN Number</label> <input
										type="text" class="form-control" name="CRNEdit"
										id="CRNEdit" disabled>
										<input type = "hidden" id="customerIdhidden" />
								</div>
							</div>

							<div class="col-md-4">
								<input class="btn btn-success submit-button"
									 value="Update" id="customerEditsave"
									type="button" disabled />
							</div>

							<div class="col-md-3">
								<button type="button" class="btn btn-secondary btn-raised mr-3 resetFilter" id="resetFilter">Reset</button>
							</div>


							<div class="col-md-4">
								<button type="button" class="btn btn-danger btn-raised mr-4"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
<%} }%>

	<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script> -->

	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

	<script src="common/js/bootstrap.min.js"></script>


	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

<script src="js/common.js"></script>
	<script src="js/profile.js"></script>
	<script src="js/block.js"></script>
	<script src="js/customer.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
		integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
		crossorigin="anonymous"></script>
	<script
		src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js"
		integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9"
		crossorigin="anonymous"></script>
	<script>
		$(document).ready(function() {
			$('body').bootstrapMaterialDesign();
		});
	</script>
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.js"></script> -->
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script>
		$(document).ready(function() {
			$('.button-left').click(function() {
				$('.left ').toggleClass('fliph');

			});
			
			document.querySelector("#profileName").innerText = "  "+sessionStorage.getItem("userName");
			document.querySelector("#mobile").innerText = "  "+sessionStorage.getItem("mobileNumber");
			document.querySelector("#email").innerText = "  "+sessionStorage.getItem("email");


		});
	</script>
	<script>
    $(function(){
    $(".pwd_block").hide();
    $(".change_pwd").on("click", function(){
        $(".pwd_block").toggle();
    });
});
  </script>

</body>
</html>