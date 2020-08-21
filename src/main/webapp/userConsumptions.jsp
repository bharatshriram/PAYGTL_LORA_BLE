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

<title>User Consumptions</title>
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
                <div class="card-header bg-primary cardHeading">User Consumptions</div>
                <div class="card-body scroll right-block">
                <form id="topupDetails">
                    <div class="row">
                      <%if(user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("4")){ %>
                        <div class="col-md-4">
                            <div class="input-group form-group">
                              <label class="bmd-label-floating select-label">Community<sup class="imp">*</sup></label>
                              <select class="form-control" id="selectcommunityName" name="selectcommunityName" onchange="showBlockbyCommunity(this.value);">
                              </select>
                            </div>
                          </div>
                          <div class="col-md-4">
                            <div class="input-group form-group">
                              <label class="bmd-label-floating select-label">Select Block<sup class="imp">*</sup></label>
                              <select class="form-control" id="selectBlockBasedonCommunity" name="selectBlockBasedonCommunity" onchange="showCustomerbyBlock(this.value);">
                              
                              </select>
                            </div>
                          </div>
                          <div class="col-md-4">
                            <div class="input-group form-group">
                              <label class="bmd-label-floating select-label">Select CRN<sup class="imp">*</sup></label>
                              <select class="form-control" id="selectHouseBasedonBlock" name="selectHouseBasedonBlock" onchange="showTopupDetails(this.value);">
                              </select>
                            </div>
                          </div>
                          <%} else if(user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("5")){%>
                          <div class="col-md-4">
								<div id = "formcommunityNameAdd" class="input-group form-group">
									<label class="bmd-label-floating">Community Name</label> <input
										type="text" class="form-control" name="communityNameAdd"
										id="communityNameAdd" disabled>
								</div>
							</div>
							<div class="col-md-4">
								<div id = "formblockNameAdd" class="input-group form-group">
									<label class="bmd-label-floating">Block Name</label> <input
										type="text" class="form-control" name="blockNameAdd"
										id="blockNameAdd"  disabled>
								</div>
							</div>
							
							<div class="col-md-4">
                            <div class="input-group form-group">
                              <label class="bmd-label-floating select-label">Select CRN<sup class="imp">*</sup></label>
                              <select class="form-control" id="selectHouseBasedonBlock" name="selectHouseBasedonBlock">
                              </select>
                            </div>
                          </div>
                          
                          <%} if(user_id.equalsIgnoreCase("3")){%>
                          
                           <div class="col-md-4">
                            <div id="formCRNNumber" class="input-group form-group">
                              <label class="bmd-label-floating">CRN Number</label>
                              <input type="text" class="form-control" id="selectHouseBasedonBlock" name="selectHouseBasedonBlock" disabled>
                            </div>
                          </div>
                          
                          <%} %>
                         
                          <div class="col-md-4">
                            <div id="formcurrentBalance_topup" class="input-group form-group">
                            <label class="bmd-label-floating">Start Date Time<sup class="imp">*</sup></label> 
                             <!--  <input type="text" class="form-control datepicker" id="start_date" name="start_date"> -->
                             <input type="text" id="start_date" name="start_date" class="form-control" >
                            </div>
                          </div>
                          <div class="col-md-4">
                            <div id="formdateTime_topup" class="input-group form-group">
                              <label class="bmd-label-floating">End Date Time<sup class="imp">*</sup></label>
                              <input type="text" class="form-control" id="end_date" name="end_date">
                            </div>
                          </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-11">
                            <button type="button" id="userconsumption" class="btn btn-primary submit-button btn-raised float-right mr-4">Submit<div class="ripple-container"></div></button>
                        </div>
                        
                         <div class="col-md-1">
								<button type="button" class="btn btn-secondary btn-raised mr-3 resetFilter" id="resetFilter">Reset</button>
							</div>
							
                    </div>
                    </form>
                </div>
              </div>
          </div>
        </div>
       
			<div id="tablereport"  style = "display:none">
			<div class="row mb-4">
					<!-- <div class="col-md-6">
						<h3>User Consumption Details</h3>
					</div>
					<div class="col-md-6">
						<button id="back"
							class="btn btn-raised btn-primary float-right"
							>
							<span>Back</span>
						</button>
					</div> -->
				</div>
				<div class="row">
					<div class="col-md-12">
						<table id="userConsumptionsTable"
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead>
								<tr>
									<th>CRN</th>
									<th>MIU ID</th>
									<th>Reading</th>
									<th>Balance</th>
									<th>Battery (%)</th>
									<th>Tariff</th>
									<th>Alarm Credit</th>
									<th>Emergency Credit</th>
									<th>Date Time</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				</div>
			

        <!--Right end-->
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
	

	<!-- <script src="common/js/bootstrap.min.js"></script> -->


	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>



	<script src="js/dropdown.js"></script>
	<script src="js/common.js"></script>
	<script src="js/userConsumptions.js"></script>
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
			/* $('#start_date').bootstrapMaterialDatePicker
			({
				time: true,
				clearButton: true,
				format: 'YYYY-MM-DD HH:mm',
				 maxDate: new Date(currentYear, currentMonth, currentDate)
			}); */

			$('#end_date').bootstrapMaterialDatePicker({ format: 'YYYY-MM-DD HH:mm',
				clearButton: true,
				 maxDate: new Date(currentYear, currentMonth, currentDate)
				  });
			$('#start_date').bootstrapMaterialDatePicker({ format: 'YYYY-MM-DD HH:mm',
				clearButton: true,
				 maxDate: new Date(currentYear, currentMonth, currentDate)
				  });
			/* $('#time').bootstrapMaterialDatePicker
			({
				date: false,
				shortTime: false,
				format: 'HH:mm'
			});

			$('#date-format').bootstrapMaterialDatePicker
			({
				format: 'dddd DD MMMM YYYY - HH:mm'
			});
			$('#date-fr').bootstrapMaterialDatePicker
			({
				format: 'DD/MM/YYYY HH:mm',
				lang: 'en',
				weekStart: 1, 
				cancelText : 'ANNULER',
				nowButton : true,
				switchOnClick : true
			});

			$('#date-end').bootstrapMaterialDatePicker
			({
				weekStart: 0, format: 'DD/MM/YYYY HH:mm'
			});
			$('#date-start').bootstrapMaterialDatePicker
			({
				weekStart: 0, format: 'DD/MM/YYYY HH:mm', shortTime : true
			}).on('change', function(e, date)
			{
				$('#date-end').bootstrapMaterialDatePicker('setMinDate', date);
			});

			$('#min-date').bootstrapMaterialDatePicker({ format : 'DD/MM/YYYY HH:mm', minDate : new Date() }); */

			$.material.init()
		});
		</script>
		
</body>
</html>