<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
  <link rel="stylesheet" href="common/css/style.css">
	
<style type="text/css">

</style>


<title>Login</title>
<link id="avast_os_ext_custom_font"
	href="chrome-extension://eofcbnmajmjmplflapaojjnihcjkigck/common/ui/fonts/fonts.css"
	rel="stylesheet" type="text/css">
</head>
<body class="login" data-gr-c-s-loaded="true">
	<div class="container">
		<div class="d-flex justify-content-end h-100">
			<div class="card">
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
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>


	<script src="common/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>
	
	<script src="js/login.js"></script>


</body>
</html>