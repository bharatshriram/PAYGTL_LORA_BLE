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

<title>Profile</title>
</head>


<body>
	<%
		String user_id = (String) session.getAttribute("roleID");

		System.out.println("======>" + user_id);
	%>

	<%
		if (null == user_id) {
			response.sendRedirect("login.jsp");
		}
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
                      <div class="row border-bottom p-2">
                        <div class="col-md-3">Password:</div>
                        <div class="col-md-5">xxxxxxx</div>
                        <div class="col-md-2 change_pwd text-primary"><b style="cursor: pointer;">Change Password</b></div>
                       
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
                 
                   
                </div>
              </div>
          </div>
        </div>
       

        <!--Right end-->
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />



	<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script> -->

	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

	<script src="common/js/bootstrap.min.js"></script>


	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/profile.js"></script>
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