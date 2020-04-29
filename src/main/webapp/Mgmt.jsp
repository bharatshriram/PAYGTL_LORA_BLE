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
	
<title>User Details</title>
</head>


<body class="innerbody">
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
						<h3>User Details</h3>
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
						<table id="mgmtTable"
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead>
								<tr>
									<th>User ID</th>
									<th>User Name</th>
									<th>Role</th>
									<th>Community Name</th>
									<th>Block Name</th>
									<th>Created By User Name</th>
									<th>Created By Role Description</th>
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
					<h5 class="modal-title" id="exampleModalLabel">User Add
						Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="userDetails">
						<div class="row">

								<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Type Of user</label> 
									<select
										class="form-control" id="selecttypeofuser" name="selecttypeofuser" onchange="showCommunitybyTypeuser(this.value);">
							 			<option value = "-1">Select Type</option>
							 			<option value = "Super">Super Admin Supervisor</option>
							  			<option value = "Admin">Admin Supervisor</option>
									</select>
								</div>
							</div>

							<div class="col-md-6" id = "usercommunityId">
								<div id ="formcomunityName" class="input-group form-group">
									<label class="bmd-label-floating">Community Name</label> 
									<select
										class="form-control" id="selectcommunityName" name="selectcommunityName" onchange="showBlockbyCommunity(this.value);">
						<!--  <option>Select Community</option> --> 
									</select>
									
								</div>
							</div>
							<div class="col-md-6" id = "userblockId">
								<div id ="formblockName" class="input-group form-group">
									<label class="bmd-label-floating">Select Block</label> <select
										class="form-control" id="selectBlockBasedonCommunity" name="selectBlockBasedonCommunity">
									</select>
								</div>
							</div>

							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">User ID</label> <input
										type="text" class="form-control" name="userIDAdd"
										id="userIDAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">User Name</label> <input
										type="text" class="form-control" name="userNameAdd"
										id="userNameAdd">
								</div>
							</div>
							
							
							
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">User Password</label> <input
										type="text" class="form-control" name="userPasswordAdd"
										id="userPasswordAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group form-group">
									<label class="bmd-label-floating">Confirm Password</label> <input
										type="text" class="form-control" name="confirmPasswordAdd"
										id="confirmPasswordAdd">
								</div>
							</div>
							
							<div class="col-md-6">
							
							</div>
							
							<div class="col-md-6">
									<button class="btn btn-secondary submit-button"
									 value="Save!" id="userAdd"
									type="button" disabled>Save</button>
							</div>

							<div class="col-md-6">
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


	<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script> -->

	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

	<script src="common/js/bootstrap.min.js"></script>


	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/dropdown.js"></script>
	<script src="js/common.js"></script>
	<script src="js/mgmt.js"></script>
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

</body>

</html>