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
	
	<link rel="stylesheet" href="common/css/bootstrap-material-datetimepicker.css" />
	
	<link href='http://fonts.googleapis.com/css?family=Roboto:400,500' rel='stylesheet' type='text/css'>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" 
	integrity="sha256-yMjaV542P+q1RnH6XByCPDfUFhmOafWbeLPmqKh11zo=" crossorigin="anonymous" />

<title>Feedback/Complaints</title>
</head>


<body class="innerbody">
<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (user_id == null) {
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
        <div class="row mb-4" id="form">
          <div class="col-md-10 m-auto">
            <div class="card">
                <div class="card-header bg-primary cardHeading">Feedback / Complaints</div>
                <div class="card-body scroll right-block">
                <form id="topupDetails">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="input-group form-group">
                              <div class="input-group form-group">
                              <label class="bmd-label-floating">Select CRN<sup class="imp">*</sup></label>
                              <input type="text" class="form-control" id="CRNNumber" name="CRNNumber" disabled>
                              </select>
                            </div>
                            </div>
                          </div>
                          <div class="col-md-4">
                            <div class="input-group form-group">
                              <label class="bmd-label-floating select-label">FeedBack<sup class="imp">*</sup></label>
                              <select class="form-control" id="selectFeedback" name="selectFeedback">
                              <option value="-1">Select Feedback</option>
                              <option value="Bill in Time Not Updated">Bill in Time Not Updated</option>
                              <option value="InSuffient Pressure">InSuffient Pressure</option>
                              <option value="Leakages">Leakages</option>
                              <option value="Meter Not Working">Meter Not Working</option>
                              <option value="Meter Run Fast">Meter Run Fast</option>
                              <option value="Mismatch B/W Mechanical & Digital Reading">Mismatch B/W Mechanical & Digital Reading</option>
                              <option value="New Connection Request">New Connection Request</option>
                              <option value="Others">Others</option>
                              <option value="Failure">Failure</option>
                              
                              </select>
                            </div>
                          </div>
                          <div class="col-md-4">
                            <div id="formAMR_topup" class="input-group form-group">
                              <label class="bmd-label-floating">Description</label>
                              <textarea id="textarea1" class="form-control" id="description" name="description"></textarea>
                            </div>
                          </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <button type="button" id="feedback" class="btn btn-primary submit-button btn-raised float-right mr-4">Submit<div class="ripple-container"></div></button>
                        </div>
                    </div>
                    </form>
                </div>
              </div>
          </div>
        </div>
        <!--Right end-->
			</div>
		</div>
	</div>
	
	
	
	<div class="modal fade" id="myFeedback" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Community</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="communityEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formcomunityName" class="input-group form-group">
									<label class="bmd-label-floating">Name</label> <input
										type="text" class="form-control" name="communityNameEdit"
										id="communityNameEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formcomunityAddress" class="input-group form-group">
									<label class="bmd-label-floating">Address</label> <input
										type="text" class="form-control" name="communityAddressEdit"
										id="communityAddressEdit">
								</div>
							</div>

							<div class="col-md-6">
								<div id="formcomunityMobile" class="input-group form-group">
									<label class="bmd-label-floating">Mobile</label> <input
										type="text" class="form-control" name="communityMobileEdit"
										id="communityMobileEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formcomunityEmail" class="input-group form-group">
									<label class="bmd-label-floating">Email</label> <input
										type="email" class="form-control" name="communityEmailEdit"
										id="communityEmailEdit">
										<input type = "hidden" id="communityIdhidden">
								</div>
							</div>

							<div class="col-md-6">
									<button class="btn btn-secondary submit-button"
									 value="Save!" id="communityEditsave"
									type="button" disabled>Update</button>
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
	
	
	<jsp:include page="footer.jsp" />

<%} %>

	<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script> -->

	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	
	        <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/0.5.10/js/material.min.js"></script> -->
		
		<script type="text/javascript" src="http://momentjs.com/downloads/moment-with-locales.min.js"></script>
		<script type="text/javascript" src="common/js/bootstrap-material-datetimepicker.js"></script>
	

	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>



	<script src="js/dropdown.js"></script>
	<script src="js/common.js"></script>
	<script src="js/feedback.js"></script>
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
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> -->
	
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
	<script type="text/javascript">
		$(document).ready(function()
		{
			var date = new Date();
            var currentMonth = date.getMonth();
            var currentDate = date.getDate();
            var currentYear = date.getFullYear();

			$('#end_date').bootstrapMaterialDatePicker({ format: 'YYYY-MM-DD HH:mm',
				clearButton: true,
				 maxDate: new Date(currentYear, currentMonth, currentDate)
				  });
			$('#start_date').bootstrapMaterialDatePicker({ format: 'YYYY-MM-DD HH:mm',
				clearButton: true,
				 maxDate: new Date(currentYear, currentMonth, currentDate)
				  });
			$.material.init()
		});
		</script>
		
</body>
</html>