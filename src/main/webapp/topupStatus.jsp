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

<title>ReCharge Details</title>
</head>


<body class="innerbody">
<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (user_id == null) {
			System.out.println("response.sendRedirect=>"+user_id);
			response.sendRedirect("login.jsp");
		}else {
	%>
<div id="preloader">
  <div id="status">&nbsp;</div>
</div>
	<jsp:include page="header.jsp" />
	<div
		class="container-fluid topspacing bottomspacing pl-0 pr-0 mr-0 ml-0">
		<div class="row mr-0 ml-0">
			<div class="left_nav col-md-2 pl-0 pr-0">

				<jsp:include page="menu.jsp" />
			</div>
			<div class="right_data col-md-10 mt-4 mb-4">
				<!--Right start-->
				<!-- <div class="row mb-4">
					<div class="col-md-6">
						<h3>Top up Status</h3>
					</div>
				</div> -->
				<div class="row">
					<div class="col-md-12">
						<table id="topstatusTable"
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead>
								<tr>
									<th>Community</th>
									<th>Block</th>
									<th>House No</th>
									<th>MIU ID</th>
									<th>Amount</th>
									<th>EC</th>
									<th>Alarm Credit</th>
									<th>Date</th>
									<th>By UserName</th>
									<th>By Role Description</th>
									<th>Status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						
						
						<table id="topstatusTable1"
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead>
								<tr>
									<th>Community</th>
									<th>Block</th>
									<th>House No</th>
									<th>MIU ID</th>
									<th>Amount</th>
									<th>EC</th>
									<th>Alarm Credit</th>
									<th>Date</th>
									<th>By UserName</th>
									<th>By Role Description</th>
									<th>Status</th>
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
	
	
	<div class="modal fade" id="filter" tabindex="-1" role="dialog"
		aria-labelledby="filterModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Topup Filter</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
          <div class="col-md-6">
            <div class="input-group form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating">Select Community</label> 
									<select
										class="form-control" id="filterselectcommunityName" name="filterselectcommunityName" onchange="showBlockbyCommunity(this.value);">
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating">Select Block</label> <select
										class="form-control" id="filterselectBlockBasedonCommunity" name="filterselectBlockBasedonCommunity">
									</select>
								</div>
							</div>
        </div>
        <div class="modal-footer m-auto">
          <button type="button" class="btn btn-primary btn-raised mr-4" id="customerFilter">Filter</button>
          <button type="button" class="btn btn-danger btn-raised mr-4" data-dismiss="modal">Close<div class="ripple-container"></div></button>
          <button type="button" class="btn btn-secondary btn-raised mr-4" id="resetFilter">Reset</button>
          
        </div>
      </div>
    </div>
    </div>
  </div>
  
	<jsp:include page="footer.jsp" />
	
	<%} %>

	<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script> -->

	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

	<!-- <script src="common/js/bootstrap.min.js"></script> -->


	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/topup.js"></script>
	<script src="js/common.js"></script>
	<script src="js/dropdown.js"></script>
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
$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();   
	});
</script>	

</body>

</html>