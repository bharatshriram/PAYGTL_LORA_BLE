<!DOCTYPE html 
PUBLIC "-//W3C//DTD HTML 4.01//EN"
      "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
	
<title>Login</title>

<link rel="icon" type="image/png" sizes="16x16" href="common/images/1-hanbit.png">


	
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link id="avast_os_ext_custom_font"
	href="chrome-extension://eofcbnmajmjmplflapaojjnihcjkigck/common/ui/fonts/fonts.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://mdbootstrap.com/api/snippets/static/download/MDB-Pro_4.8.1/css/mdb.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
  <link rel="stylesheet" href="common/css/style.css">

        
        
</head>
<body class="login" data-gr-c-s-loaded="true">
	<div class="container">
		<div class="d-flex justify-content-end h-100">
		
		 <!-- Rotating card -->
         <div class="card-wrapper col-md-6 col-lg-4 login-card">
          <div id="my-card" class="card-rotating">
             <!-- Front Side -->
            <div class="face front login_bg">
              <div class="card-body">
			<form id="test">
                <!-- Header -->
                <div class="card-header">
                  <h3>Sign In</h3>
                  <div class="d-flex justify-content-end social_icon">
                    <span><i class="fab fa-facebook-square"></i></span>
                    <span><i class="fab fa-google-plus-square"></i></span>
                    <span><i class="fab fa-twitter-square"></i></span>
                  </div>
                </div>

                <!-- Login Form -->
                <div class="form-group md-form">
                  <i class="fas fa-envelope prefix icon_colors d-flex"></i>
                  <input type="text" id="username" name="username" class="form-control" style = "color : #f0e3e3">
                  <label for="username" class="bmd-label-floating text-white">User ID</label>
                </div>

                <div class="form-group md-form">
                  <i class="fas fa-lock prefix icon_colors d-flex"></i>
                  <input type="password" name="password" id="password" class="form-control" style = "color : #f0e3e3">
                  <label for="password" class="bmd-label-floating text-white">Your password</label>
                  <span toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                </div>

                

                  <!--  <div class="custom-control custom-checkbox">
                  <input type="checkbox" class="custom-control-input" id="checkbox">
                  <label class="custom-control-label text-white" for="checkbox">Remember Me</label>
                </div> -->

                
                
                <div class="form-group text-right">
                  <input type="button" value="Login" id="login"
							class="btn float-right login_btn btn btn-lg btn-success submit-button"
							disabled>
                </div>
                <div class="card-footer mt-2">
                  <div class="d-flex justify-content-center">
                    <!-- Triggering button -->
                    <a class="rotate-btn text-white" tabindex="-1" data-card="my-card">Forgot Password?</a>
                  </div>
                </div>
               
               </form>
                <!-- Login Form -->
	<div class="errorMessage" style = "color : Red"></div>
              </div>
            </div>
            <!-- Front Side -->

            <!-- Back Side -->
            <div class="face back login_bg">
              <div class="card-body">
                <div class="forgot">
                <!-- Header -->
                <div class="form-header login_btn">
                  <h3 class="font-weight-500 my-2 py-1"><i class="fas fa-plus"></i> Forgot Password</h3>
                </div>
								<!-- forgot  password-->
                  <div class="md-form">
                    <i class="fas fa-envelope prefix icon_colors d-flex"></i>
                    <input type="text" name="forgetusername" id = "forgetusername" class="form-control">
                    <label for="email" class="bmd-label-floating">User ID</label>
                  </div>
                  <div class="d-flex justify-content-end">
                    <!-- Triggering button -->
                    <a class="rotate-btn text-white" data-card="my-card" tabindex="-3">back to login</a>
                  </div>
                  <div class="text-center">
                    <button type="button" class="btn login_btn newpwd_btn" id="forgotButton">Submit</button>
                  </div>
                </div>
                  <!--forgot  password-->
                  <!--new Password-->
                  <div class="newpwd">
                    <!-- Header -->
                    <div class="form-header login_btn">
                      <h3 class="font-weight-500 my-2 py-1"><i class="fas fa-plus"></i> New Password</h3>
                    </div>
                    <!-- forgot  password-->
                    <div class="md-form">
                      <i class="fas fa-lock prefix icon_colors d-flex"></i>
                      <input type="password" name="password" id="password-field1" class="form-control">
                      <label for="password" class="bmd-label-floating">Your password</label>
                      <span toggle="#password-field1" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                    </div>
                    <div class="md-form">
                      <i class="fas fa-lock prefix icon_colors d-flex"></i>
                      <input type="password" name="password" id="password-field2" class="form-control">
                      <label for="password" class="bmd-label-floating">Your password</label>
                      <span toggle="#password-field2" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                    </div>
                    <div class="md-form">
                      <i class="fas fa-lock prefix icon_colors d-flex"></i>
                      <input type="password" name="password" id="password-field3" class="form-control">
                      <label for="password" class="bmd-label-floating">Your password</label>
                      <span toggle="#password-field3" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                    </div>
                   <div class="d-flex justify-content-end">
                        <!-- Triggering button -->
                        <a class="rotate-btn text-white" data-card="my-card" tabindex="-3">back to login</a>
                      </div>
                      <div class="text-center">
                        <button type="sumbit" class="btn login_btn">Submit</button>
                      </div>
                    </div>
                  <!--new Password-->
							
              </div>
            </div>
            <!-- Back Side -->

          </div>
        </div>
        <!-- Rotating card -->
		
			<!-- <div class="card login-card">
				<div class="card-header">
					<h3>Sign In</h3>
					<div class="d-flex justify-content-end social_icon">
						<span><i class="fab fa-facebook-square"></i></span> <span><i
							class="fab fa-google-plus-square"></i></span> <span><i
							class="fab fa-twitter-square"></i></span>
					</div>
				</div>
				<div class="card-body">
					<form id="test">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" id="userName" name="username"
								class="form-control" placeholder="username">

						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" id="password" name="password" type="password"
								class="form-control" placeholder="password">
						</div>
						<div class="row align-items-center remember">
							<input type="checkbox">Remember Me
						</div>
						<div class="form-group">
							<input type="button" value="Login" id="login"
							class="btn float-right login_btn btn btn-lg btn-success submit-button"
							disabled>
						</div>
					</form>

					<div class="errorMessage"></div>


				</div>
				<div class="card-footer">
					<div class="d-flex justify-content-center">
						<a href="#">Forgot your password?</a>
					</div>
				</div>
			</div> -->
		</div>
	</div>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="https://mdbootstrap.com/api/snippets/static/download/MDB-Pro_4.8.1/js/mdb.min.js"></script>

<script type="text/javascript"
	src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
	
	
	<script src="js/login.js"></script>
<script>
 /*  $(".newpwd_btn").click(function(){
    $(".newpwd").show();
    $(".forgot").hide();
}); */
/* $(".toggle-password").click(function() {

var x = document.getElementById("password");
if (x.type === "password") {
  x.type = "text";
} else {
  x.type = "password";
}
}); */

$('.toggle-password').on('click', function() {
	  $(this).toggleClass('fa-eye fa-eye-slash');
	  let input = $($(this).attr('toggle'));
	  if (input.attr('type') == 'password') {
	    input.attr('type', 'text');
	  }
	  else {
	    input.attr('type', 'password');
	  }
	});
	
</script>  

</body>
</html>