<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="common/css/bootstrap.min.css">
  <!-- Material Design for Bootstrap CSS -->
  <link rel="stylesheet"
    href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
    integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX" crossorigin="anonymous">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
    integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
  <link rel="stylesheet" href="common/css/style.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
 
  <title>Home</title>
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

<jsp:include page="header.jsp" />
  <div class="container-fluid topspacing bottomspacing pl-0 pr-0 mr-0 ml-0">
    <div class="row mr-0 ml-0">
      <div class="col-md-2 pl-0 pr-0">
       <jsp:include page="menu.jsp" />
      </div>
      <!-- <div id="container" style="height: 400px; width: 500"></div> -->
      
      <div class="right_data col-md-10 mt-4 mb-4">
				 <!--Right start-->
				 <div class="rightblock">
            <div class="row">
              <div class="col-md-4 mb-4">
                <div class="card border-left-grey shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-gray text-uppercase mb-1">Total MIU ID</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800" id="totalmiu"></div>
                      </div>
                      <div class="col-auto">
                       <img src=common/images/icons/GAS2.jpg /> 
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-4 mb-4">
                <div class="card border-left-success shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Communicating</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800" id="communicating"></div>
                      </div>
                      <div class="col-auto">
                       <img src=common/images/icons/6-COMMUNICATION.png /> 
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-4 mb-4">
                <div class="card border-left-danger shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">Non-Communicating</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800" id="Non-Communicating"></div>
                      </div>
                      <div class="col-auto">
                       <img src=common/images/icons/7-NONCOMMUNICATION.png />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>  
            <div class="row">
              <div class="col-md-3">
              </div>
              <div class="col-md-6">
                <div class="card shadow mb-4 bg-transparent">
                  <!-- Card Header - Dropdown -->
                  <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-dark">Device Status</h6>
                  </div>
                  <!-- Card Body -->
                  <div class="card-body">
                    <div id="container" class="container" style="height: 300px; width: 500"></div>
                  </div>
                </div>
              </div>
              <div class="col-md-3">
              </div>
            </div>
			</div>
        <!--Right end-->

			</div>
    </div>
  </div>

  <jsp:include page="footer.jsp" />
  <%} %>
  
  <!-- Modal -->
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
		integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
		crossorigin="anonymous"></script>

	<!-- <script src="common/js/bootstrap.min.js"></script> -->
<script src="https://code.highcharts.com/highcharts.js"></script>

	
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
    $(document).ready(function () {
    	var flag =true;
      $('.button-left').click(function () {
        $('.left ').toggleClass('fliph');
      });

    });
  </script>
  <script>
    $(document).ready(function () {
    	var data1;
    	$
		.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/PAYGTL_LORA_BLE/dashboard/"+ sessionStorage.getItem("roleID")+ "/"+ sessionStorage.getItem("ID"),
			dataType : "JSON",

			success : function(d) {
			
				//alert(JSON.stringify(d));
				
				document.querySelector("#totalmiu").innerText = d.total;
				document.querySelector("#communicating").innerText = d.communicating;
				document.querySelector("#Non-Communicating").innerText = d.nonCommunicating;
				
				
				data1 = [
    	            ["non-Communicating", ((d.nonCommunicating/d.total)*100) ],
    	            ['communicating',     ((d.communicating/d.total)*100)]
    	        ];
				
				Highcharts.chart('container', {
		    	    chart: {
		    	        type: 'pie'
		    	    },

		    	    title: {
		    	        text: 'Device Status'
		    	    },

		    	    plotOptions: {
		    	        pie: {
		    	        	colors: [
		    	        	     '#f54747', 
		    	        	     '#33c354'
		    	        	   ]
		    	        }
		    	    },

		    	    series: [{
		    	        data: data1
		    	    }]
		    	});
				
			//	document.querySelector(".highcharts-series-group .highcharts-series .highcharts-color-0").setAttribute("fill","red");
			  //  document.querySelector(".highcharts-series-group .highcharts-series .highcharts-color-1").setAttribute("fill","green");
			
		}	
});
    });
    
    
    
    
  </script>
  
</body>

</html>