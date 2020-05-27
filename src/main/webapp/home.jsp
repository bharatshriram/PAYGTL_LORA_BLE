<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
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

<title>Home</title>
</head>

<body class="innerbody">
	<%
		String user_id = (String) session.getAttribute("roleID");
	%>

	<%
		if (user_id == null) {
			System.out.println("response.sendRedirect=>" + user_id);
			response.sendRedirect("login.jsp");
		} else {
	%>

	<jsp:include page="header.jsp" />
	<div
		class="container-fluid topspacing bottomspacing pl-0 pr-0 mr-0 ml-0">
		<div class="row mr-0 ml-0">
			<div class="col-md-2 pl-0 pr-0">
				<jsp:include page="menu.jsp" />
			</div>
			<!-- <div id="container" style="height: 400px; width: 500"></div> -->

			<div class="right_data col-md-10 mt-4 mb-4">
				<!--Right start-->
				<div class="rightblock">
					<%
						if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("4")
									|| user_id.equalsIgnoreCase("5")) {
					%>
					<div class="row admin">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-2">
									<div class="row">
										<div class="col-md-12">
											<div class="card card-stats borderRadius">
												<div class="card-body cardSpacing">
													<div class="row">
														<div class="col-5 col-md-4">
															<div class="icon-big text-center icon-warning">
																<img src=common/images/icons/icons8-sports-mode-802.png
																	style="width: 50px; height: 50px;" />
															</div>
														</div>
														<div class="col-7 col-md-8">
															<div class="numbers text-right">
																<p class="card-category">Active</p>
																<p class="card-title" id="adminActive"></p>
															</div>
														</div>
													</div>
												</div>
												<div class="card-footer borderRadius">

													<div class="stats" onClick="redirection(1)">
														View Details<i class="fa fa-chevron-right view_details"
															aria-hidden="true"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row mt-4">
										<div class="col-md-12">
											<div class="card card-stats borderRadius">
												<div class="card-body cardSpacing">
													<div class="row">
														<div class="col-5 col-md-4">
															<div class="icon-big text-center icon-warning">
																<img src=common/images/icons/gas3.png />
															</div>
														</div>
														<div class="col-7 col-md-8">
															<div class="numbers text-right">
																<p class="card-category">Consumption</p>
																<p class="card-title" id="admincomption"></p>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row mt-4">
										<div class="col-md-12">
											<div class="card card-stats borderRadius">
												<div class="card-body cardSpacing">
													<div class="row">
														<div class="col-5 col-md-4">
															<div class="icon-big text-center icon-warning">
																<img src=common/images/icons/gas3.png />
															</div>
														</div>
														<div class="col-7 col-md-8">
															<div class="numbers text-right">
																<p class="card-category">Non-Live</p>
																<p class="card-title" id="adminNonLive"></p>
															</div>
														</div>
													</div>
												</div>
												<div class="card-footer borderRadius">

													<div class="stats" onClick="redirection(4)">
														View Details<i class="fa fa-chevron-right view_details"
															aria-hidden="true"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row mt-4">
										<div class="col-md-12">
									<div class="card card-stats borderRadius">
												<div class="card-body cardSpacing">
													<div class="row">
														<div class="col-5 col-md-4">
															<div class="icon-big text-center icon-warning">
																<img src=common/images/icons/gas3.png />
															</div>
														</div>
														<div class="col-7 col-md-8">
															<div class="numbers text-right">
																<p class="card-category">EC</p>
																<p class="card-title" id="adminEmergency"></p>
															</div>
														</div>
													</div>
												</div>
												<div class="card-footer borderRadius">

													<div class="stats" onClick="redirection(6)">
														View Details<i class="fa fa-chevron-right view_details"
															aria-hidden="true"></i>
													</div>
												</div>
											</div>
											</div>
									</div>
									
									
									
									
								</div>

								<div class="col-md-8">
									<div class="row billingAmount">
										<div class="col-md-3">
											<div class="card card-stats borderRadius">
												<div class="card-body cardSpacing">
													<div class="row">
														<div class="col-5 col-md-4">
															<div class="icon-big text-center icon-warning">
																<img src=common/images/icons/gas3.png />
															</div>
														</div>
														<div class="col-7 col-md-8">
															<div class="numbers text-right">
																<p class="card-category">Total MUI</p>
																<p class="card-title" id="adminAMR"></p>
															</div>
														</div>
													</div>
												</div>
												<div class="card-footer borderRadius">

													<div class="stats" onClick="redirection(5)">
														View Details<i class="fa fa-chevron-right view_details"
															aria-hidden="true"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row mt-4">
										<div class="col-md-12">
											<div class="card shadow mb-4 bg-transparent">
												<!-- Card Header - Dropdown -->
												<div
													class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
													<h6 class="m-0 font-weight-bold text-white">Live
														Status</h6>
												</div>
												<!-- Card Body -->
												<div class="card-body">
													<!-- <div id="container" class="container"
														style="height: 250px; width: 500"></div> -->

													<!-- <div id="chartContainer" style="height: 300px; width: 100%;"></div> -->
													<div id="highchart_container"
														style="width: 100%; max-width: 800px; margin: 0 auto"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="row">
										<div class="col-md-12">
										<div class="col-md-12">
											<div class="card card-stats borderRadius">
												<div class="card-body cardSpacing">
													<div class="row">
														<div class="col-5 col-md-4">
															<div class="icon-big text-center icon-warning">
																<img src=common/images/icons/gas3.png />
															</div>
														</div>
														<div class="col-7 col-md-8">
															<div class="numbers text-right">
																<p class="card-category">Live</p>
																<p class="card-title" id="adminLive"></p>
															</div>
														</div>
													</div>
												</div>
												<div class="card-footer borderRadius">

													<div class="stats" onClick="redirection(3)">
														View Details<i class="fa fa-chevron-right view_details"
															aria-hidden="true"></i>
													</div>
												</div>
											</div>
										</div>
											
										</div>
									</div>
									<div class="row mt-4 ">
										<div class="col-md-12">
											<div class="card card-stats borderRadius">
												<div class="card-body cardSpacing">
													<div class="row">
														<div class="col-5 col-md-4">
															<div class="icon-big text-center icon-warning">
																<img src=common/images/icons/gas3.png />
															</div>
														</div>
														<div class="col-7 col-md-8">
															<div class="numbers text-right">
																<p class="card-category">Day Topup</p>
																<p class="card-title" id="admindayTopup"></p>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									
									<div class="row mt-4">
										<div class="col-md-12">
											<div class="card card-stats borderRadius">
												<div class="card-body cardSpacing">
													<div class="row">
														<div class="col-5 col-md-4">
															<div class="icon-big text-center icon-warning">
																<img src=common/images/icons/gas3.png />
															</div>
														</div>
														<div class="col-7 col-md-8">
															<div class="numbers text-right">
																<p class="card-category">In-Active</p>
																<p class="card-title" id="adminInActive"></p>
															</div>
														</div>
													</div>
												</div>
												<div class="card-footer borderRadius">

													<div class="stats" onClick="redirection(2)">
														View Details<i class="fa fa-chevron-right view_details"
															aria-hidden="true"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row mt-4 ">
										<div class="col-md-12">
											<div class="card card-stats borderRadius">
												<div class="card-body cardSpacing">
													<div class="row">
														<div class="col-5 col-md-4">
															<div class="icon-big text-center icon-warning">
																<img src=common/images/icons/gas3.png />
															</div>
														</div>
														<div class="col-7 col-md-8">
															<div class="numbers text-right">
																<p class="card-category">Low Battery</p>
																<p class="card-title" id="adminBattery"></p>
															</div>
														</div>
													</div>
												</div>
												<div class="card-footer borderRadius">

													<div class="stats">
														View Details<i class="fa fa-chevron-right view_details"
															aria-hidden="true"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
					<%
						} else if (user_id.equalsIgnoreCase("3")) {
					%>>
					<div class="row customer">
						<div class="col-md-3">
							<div class="row billingAmount">
								<div class="col-md-12 bg-success text-center">
									<p class="text-white">
										Last Bill Amount:<span>0000</span>
									</p>
									<p class="text-white">
										Last Bill Amount:<span>0000</span>
									</p>
								</div>
							</div>
							<div class="row mt-4 billingAmount">
								<div class="col-md-12 text-center">
									<div class="row mt-4">
										<div class="col col1">
											<img src=common/images/icon/23-consumptionn.png />
											<p class="mt-4">Consumption</p>
										</div>
										<div class="col col2">
											<img src=common/images/icon/24-PaymentInfo.png />
											<p class="mt-4">Payment Info</p>
										</div>
									</div>
								</div>
							</div>
							<div class="row mt-4 billingAmount">
								<div class="col-md-12 text-center">
									<div class="row mt-4">
										<div class="col col1">
											<img src=common/images/icon/25-billpayment.png />
											<p class="mt-4">Bill Payment</p>
										</div>
										<div class="col col2">
											<img src=common/images/icon/26-feedback.png />
											<p class="mt-4">Feedback</p>
										</div>
									</div>
								</div>
							</div>
							<div class="row mt-4 billingAmount">
								<div class="col-md-12 text-center">
									<div class="row mt-4">
										<div class="col col1">
											<img src=common/images/icon/27-profiifle.png />
											<p class="mt-4">Profile</p>
										</div>
										<div class="col col2">
											<img src=common/images/icon/28-complaints.png />
											<p class="mt-4">Compliants</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-9">
							<div class="row">
								<div class="col col1">
									<div class="card card-stats borderRadius">
										<div class="card-body cardSpacing">
											<div class="row">
												<div class="col-5 col-md-4">
													<div class="icon-big text-center icon-warning">
														<img src=common/images/icons/gas3.png />
													</div>
												</div>
												<div class="col-7 col-md-8">
													<div class="numbers text-right">
														<p class="card-category">Community</p>
														<p class="card-title" id="communicating">21</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col col2">
									<div class="card card-stats borderRadius">
										<div class="card-body cardSpacing">
											<div class="row">
												<div class="col-5 col-md-4">
													<div class="icon-big text-center icon-warning">
														<img src=common/images/icons/gas3.png />
													</div>
												</div>
												<div class="col-7 col-md-8">
													<div class="numbers text-right">
														<p class="card-category">Block</p>
														<p class="card-title" id="communicating">21</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col col3">
									<div class="card card-stats borderRadius">
										<div class="card-body cardSpacing">
											<div class="row">
												<div class="col-5 col-md-4">
													<div class="icon-big text-center icon-warning">
														<img src=common/images/icons/gas3.png />
													</div>
												</div>
												<div class="col-7 col-md-8">
													<div class="numbers text-right">
														<p class="card-category">CRN Number</p>
														<p class="card-title" id="communicating">21</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col col4">
									<div class="card card-stats borderRadius">
										<div class="card-body cardSpacing">
											<div class="row">
												<div class="col-5 col-md-4">
													<div class="icon-big text-center icon-warning">
														<img src=common/images/icons/gas3.png />
													</div>
												</div>
												<div class="col-7 col-md-8">
													<div class="numbers text-right">
														<p class="card-category">Available Status</p>
														<p class="card-title" id="communicating">21</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>








							<div class="row">
								<div class="col col1">
									<div class="card card-stats borderRadius">
										<div class="card-body cardSpacing">
											<div class="row">
												<div class="col-5 col-md-4">
													<div class="icon-big text-center icon-warning">
														<img src=common/images/icons/gas3.png />
													</div>
												</div>
												<div class="col-7 col-md-8">
													<div class="numbers text-right">
														<p class="card-category">Valve Status</p>
														<p class="card-title" id="communicating">21</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col col2">
									<div class="card card-stats borderRadius">
										<div class="card-body cardSpacing">
											<div class="row">
												<div class="col-5 col-md-4">
													<div class="icon-big text-center icon-warning">
														<img src=common/images/icons/gas3.png />
													</div>
												</div>
												<div class="col-7 col-md-8">
													<div class="numbers text-right">
														<p class="card-category">Meter Status</p>
														<p class="card-title" id="communicating">21</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col col3">
									<div class="card card-stats borderRadius">
										<div class="card-body cardSpacing">
											<div class="row">
												<div class="col-5 col-md-4">
													<div class="icon-big text-center icon-warning">
														<img src=common/images/icons/gas3.png />
													</div>
												</div>
												<div class="col-7 col-md-8">
													<div class="numbers text-right">
														<p class="card-category">Battery Status</p>
														<p class="card-title" id="communicating">21</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>



							<div class="row">
								<div class="col-md-12">
									<div class="card shadow mb-4 bg-transparent">
										<!-- Card Header - Dropdown -->
										<div
											class="card-header">
										<div class="row">
											<div class="col-md-4"><h6 class="m-0 font-weight-bold text-grey lineHeight">Live Status</h6></div>
											<div class="col-md-4">
											<div class="row">
												<div class="col-md-6">
													 <select class="form-control end_date" id="end_date">
                             <option value="">Select Month</option>
                             <option value="01">January</option>
                             <option value="02">February</option>
                             <option value="03">March</option>
                             <option value="04">April</option>
                             <option value="05">May</option>
                             <option value="06">June</option>
                             <option value="07">July</option>
                             <option value="08">August</option>
                             <option value="09">September</option>
                             <option value="10">October</option>
                             <option value="11">November</option>
                             <option value="12">December</option>
                             </select>
												</div>
												<div class="col-md-6">
												 
                             <select class="yrselectdesc form-control start_date" id="start_date">
                             <option value="">Select Year</option>
                             </select>
												</div>
											</div>
													
                             
                             
                            
											</div>
											<div class="col-md-4">
												<button type="button" id="view" class="btn btn-primary submit-button btn-raised float-right mr-4">View<div class="ripple-container"></div></button> 
											</div>
										</div>
											
											
										
                             
                                                         
										</div>
										<!-- Card Body -->
										<div class="card-body">
											<!-- <div id="container" class="container"
														style="height: 250px; width: 500"></div> -->

											<!-- <div id="chartContainer" style="height: 300px; width: 100%;"></div> -->
											<div id="highchart_container1"
												style="height: 250px; width: 100%; max-width: 800px; margin: 0 auto"></div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<%
		}
	%>

	<!-- Modal -->
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

	<script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
		integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
		crossorigin="anonymous"></script>

	<script src="js/home.js"></script>
	<script src="js/common.js"></script>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script type="text/javascript"
		src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>


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
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
	<script>
		$(document).ready(function() {
			var flag = true;
			$('.button-left').click(function() {
				$('.left ').toggleClass('fliph');
			});

		});
		
	</script>
	
	<script src="common/js/year-select.js"></script>
        <script type="text/javascript">
            $(document).ready(function(e) {
                $('.yearselect').yearselect();

                $('.yrselectdesc').yearselect({step: 5, order: 'desc'});
                $('.yrselectasc').yearselect({order: 'asc'});
            });
        </script>

</body>

</html>