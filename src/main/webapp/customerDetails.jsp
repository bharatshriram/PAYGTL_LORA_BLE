<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link href="common/css/materialize.fontawsome.css"
	rel="stylesheet">
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
	
<title>Customer Details</title>
</head>


<body>
	<jsp:include page="header.jsp" />
	<div
		class="container-fluid topspacing bottomspacing pl-0 pr-0 mr-0 ml-0">
		<div class="row mr-0 ml-0">
			<div class="left_nav col-md-2 pl-0 pr-0">

				<jsp:include page="menu.jsp" />
			</div>
			<div class="right_data col-md-10 mt-4 mb-4">
				<!--Right start-->
				<div class="row mb-4">
					<div class="col-md-6">
						<h3>Customer Details</h3>
					</div>
					<div class="col-md-6">
						<button type="button"
							class="btn btn-raised btn-primary float-right"
							data-toggle="modal" data-target="#exampleModal">
							<i class="fa fa-user"></i>
						</button>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table id="customerTable"
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead>
								<tr>
									<th>Community Name</th>
									<th>Block Name</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>House No</th>
									<th>CRN Number</th>
									<th>Mobile</th>
									<th>Email</th>
									<th>Meter Sr. No.</th>
									<th>AMR ID</th>
									<th>Default Reading</th>
									<th>Created UserName</th>
									<th>Created Role</th>
									<th>Registration Date</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>

				<!--Right end-->
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Customer Add
						Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="customerDetails">
						<div class="row">
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Community Name</label> 
									<select
										class="form-control" id="selectcommunityName" name="selectcommunityName" onchange="showBlockbyCommunity(this.value);">
						<!--  <option>Select Community</option> --> 
									</select>
									
										<!-- <input
										type="text" class="form-control" name="communityAdmin"
										id="communityAdmin"> -->
										
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Select Block</label> <select
										class="form-control" id="selectBlockBasedonCommunity" name="selectBlockBasedonCommunity">
						<!-- <option style = "color: Red" value="" disabled selected>Select Block</option> --><!--  <option>Select Community</option> --> 
									</select>
									
									<!-- <input
										type="text" class="form-control" name="blockAdmin"
										id="blockAdmin"> -->
									
								</div>
							</div>

							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">First Name</label> <input
										type="text" class="form-control" name="firstNameAdd"
										id="firstNameAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Last Name</label> <input
										type="text" class="form-control" name="lastNameAdd"
										id="lastNameAdd">
								</div>
							</div>
							
							
							
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">House No.</label> <input
										type="text" class="form-control" name="houseNoAdd"
										id="houseNoAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Mobile No</label> <input
										type="text" class="form-control" name="mobileNoAdd"
										id="mobileNoAdd">
								</div>
							</div>
							
							
							
							
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Email</label> <input
										type="email" class="form-control" name="emailAdd"
										id="emailAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Meter Serial</label> <input
										type="text" class="form-control" name="meterSerialAdd"
										id="meterSerialAdd">
								</div>
							</div>
							
							
							
							
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">AMR</label> <input
										type="text" class="form-control" name="amrAdd"
										id="amrAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Tariff Name</label>  <select
										class="form-control" id="selectTariffName" name="selectTariffName">
										<!-- <option style = "color: Red" value="" disabled selected>Select Tariff</option> --><!--  <option>Select Community</option> --> 
									</select>
								</div>
							</div>
							
							
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Default Reading</label> <input
										type="text" class="form-control" name="defaultReadingAdd"
										id="defaultReadingAdd">
								</div>
							</div>
							
							
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">CRN</label> <input
										type="email" class="form-control" name="CRNAdd"
										id="CRNAdd">
								</div>
							</div>

							<div class="col-md-6">
								<input class="btn btn-lg btn-success submit-button"
									style="width: 100%;" value="Save!" id="customerAdd"
									type="button" disabled></input>
							</div>

							<div class="col-md-6">
								<button type="button" class="btn btn-secondary btn-raised mr-4"
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
									<label class="bmd-label-floating">Community Name</label> 
									 <input
										type="text" class="form-control" name="communityNameEdit"
										id="communityNameEdit" disabled>
								</div>
							</div>
							<div class="col-md-6">
								<div id="formblockNameEdit" class="input-group form-group">
									<label class="bmd-label-floating">Block Name</label> <input
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
									<label class="bmd-label-floating">Meter Serial</label> <input
										type="text" class="form-control" name="meterSerialEdit"
										id="meterSerialEdit" disabled>
								</div>
							</div>
							
							
							
							
							<div class="col-md-6">
								<div id="formamrEdit" class="input-group form-group">
									<label class="bmd-label-floating">AMR</label> <input
										type="text" class="form-control" name="amrEdit"
										id="amrEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Tariff Name</label>  <select
										class="form-control" id="selectTariffNameEdit" name="selectTariffNameEdit">
										<!-- <option style = "color: Red" value="" disabled selected>Select Tariff</option> --><!--  <option>Select Community</option> --> 
									</select>
								</div>
							</div>
							
							
							<div class="col-md-6">
								<div id="formdefaultReadingEdit" class="input-group form-group">
									<label class="bmd-label-floating">Default Reading</label> <input
										type="text" class="form-control" name="defaultReadingEdit"
										id="defaultReadingEdit">
								</div>
							</div>
							
							
							<div class="col-md-6">
								<div id="formCRNEdit" class="input-group form-group">
									<label class="bmd-label-floating">CRN</label> <input
										type="email" class="form-control" name="CRNEdit"
										id="CRNEdit" disabled>
										<input type = "hidden" id="customerIdhidden" />
								</div>
							</div>

							<div class="col-md-6">
								<input class="btn btn-lg btn-success submit-button"
									style="width: 100%;" value="Save!" id="customerEditsave"
									type="button" disabled />
							</div>

							<div class="col-md-6">
								<button type="button" class="btn btn-secondary btn-raised mr-4"
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


	<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script> -->

	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

	<script src="common/js/bootstrap.min.js"></script>


	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/dropdown.js"></script>
	<script src="js/common.js"></script>
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
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> -->  -->
	
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
		
	<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.colVis.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
		
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
		
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.bootstrap.min.js"></script>	
		
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
		
		<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>	
		
		<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
		
		
		
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
			$('#communityTable').DataTable();
		});
	</script>

</body>

</html>