<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id"
	content="346004683826-ok14os0738dm39g348lnqvjncn7mei3d.apps.googleusercontent.com">
<title>Shift App &amp; Incidents Tracker</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/form-elements.css">
<link rel="stylesheet" href="assets/css/style.css">
<link href="custom-css/jquery.toastmessage.css" rel="stylesheet">
<!-- alert messages css -->
<link href="plugins-css/alertify.core.css" rel="stylesheet">
<link href="plugins-css/alertify.default.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>

	<!-- Top content -->
	<div class="top-content" ng-app="shiftApp"
		ng-controller="login-register-controller">

		<div class="inner-bg">
			<div class="container">

				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>Internal Project</strong> Login &amp; Register 
						</h1>
						<div class="description">
							<p>
								<strong>Click</strong> <a href="https://github.com/Sanu07/" target="_blank"><strong>to see my GitHub Link</strong></a>,
							</p>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-5">

						<div class="form-box">
							<div class="form-top">
								<div class="form-top-left">
									<h3>Login</h3>
									<p>Enter user email and password to log on:</p>
								</div>
								<div class="form-top-right">
									<i class="fa fa-lock"></i>
								</div>
							</div>
							<div class="form-bottom">
								<form role="form" action="/loginUser" method="post"
									class="login-form">
									<div class="form-group">
										<label class="sr-only" for="form-email">Email</label> <input
											type="text" name="form-email" placeholder="Email..."
											class="form-email form-control" id="form-email">
									</div>
									<div class="form-group">
										<label class="sr-only" for="form-password">Password</label> <input
											type="password" name="form-password"
											placeholder="Password..." class="form-password form-control"
											id="form-password">
									</div>
									<!-- reCAPTCHA -->
									<!-- <div class="form-group"><div class="g-recaptcha"
         data-sitekey="6LffagoUAAAAAIKHXwv1j9aFm6rnBw7glvGjOdNi"></div></div> -->
									<button type="submit" class="btn"
										ng-click="validateLoginDetails(this)">Sign in!</button>
								</form>
								<span style="font-family: none; color: #ff5233">
									${errorMessage}</span>
							</div>
						</div>

						<div class="social-login">
							<h3>...or login with:</h3>
							<div class="social-login-buttons">
								<a class="btn btn-link-2" onclick="logIn()"> <i
									class="fa fa-facebook"></i> Facebook
								</a> <a class="btn btn-link-2"> <i class="fa fa-twitter"></i>
									Twitter
								</a> <a class="btn btn-link-2" id="googleSignIn"> <i
									class="fa fa-google-plus"></i> Google Plus
								</a>
							</div>
						</div>

					</div>

					<div class="col-sm-1 middle-border"></div>
					<div class="col-sm-1"></div>

					<div class="col-sm-5">

						<div class="form-box">
							<div class="form-top">
								<div class="form-top-left">
									<h3>Sign up now</h3>
									<p>Fill in the form below to get instant access:</p>
								</div>
								<div class="form-top-right">
									<i class="fa fa-pencil"></i>
								</div>
							</div>
							<div class="form-bottom">
								<form role="form" id="register-form" action="/saveUser"
									method="post" class="registration-form">
									<div class="form-group">
										<label class="sr-only" for="form-first-name">First
											name</label> <input type="text" name="form-first-name"
											placeholder="First name..." autocomplete="off"
											class="form-first-name form-control" id="form-first-name">
									</div>
									<div class="form-group">
										<label class="sr-only" for="form-cognizant-id">Cognizant
											ID</label> <input type="text" name="form-cognizant-id"
											onkeypress="return (event.charCode == 8 || event.charCode == 0 || event.charCode == 13) ? null : event.charCode >= 48 && event.charCode <= 57"
											autocomplete="off" pattern="\d+" placeholder="Cognizant ID..."
											class="form-first-name form-control" id="form-cognizant-id">
									</div>
									<div class="form-group">
										<label class="sr-only" for="form-email">Email</label> <input
											type="email" autocomplete="off" name="form-email" placeholder="Email..."
											class="form-email form-control" id="form-email-reg">
									</div>
									<div class="form-group">
										<label class="sr-only" for="phone">Phone</label> <input
											type="text" name="phone" placeholder="Phone..."
											onkeypress="return (event.charCode == 8 || event.charCode == 0 || event.charCode == 13) ? null : event.charCode >= 48 && event.charCode <= 57"
											maxlength="10" autocomplete="off"
											min="10"
											class="form-phone form-control" id="form-phone">
									</div>
									<div class="form-group">
										<label class="sr-only" for="password">Password</label> <input
											type="password" name="password" autocomplete="off" placeholder="Password..."
											class="form-password form-control" id="form-password-reg">
									</div>
									<div class="form-group">
										<label class="sr-only" for="conpassword">Confirm
											Password</label> <input type="password" name="conpassword"
											placeholder="Confirm Password..." autocomplete="off"
											class="form-conpassword form-control"
											id="form-conpassword-reg">
									</div>
									<button type="submit" class="btn">Sign me up!</button>
								</form>
								<span style="font-family: none; color: #ff5233">
									${registerErrorMessage}</span>
							</div>
						</div>

					</div>
				</div>

			</div>
		</div>

	</div>

	<!-- Footer -->
	<footer>
		<div class="container">
			<div class="row">

				<!-- <div class="col-sm-8 col-sm-offset-2">
					<div class="footer-border"></div>
					<p>
						<a href="http://azmind.com" target="_blank"><strong></strong></a>
						<i class="fa fa-smile-o"></i>
					</p>
				</div> -->
			</div>
		</div>
	</footer>
	<!-- modal to update data when user login through FB or Google -->
	<div class="modal fade" id="register-user-modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					This is your first time Login
					<p>
						You have to provide the following details
						<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form class="form-horizontal" id="register-user-form" method="post">
					<div class="modal-body">
						<table class="table">
							<tbody>
								<tr>
									<td>User Name :</td>
									<td><input ng-model="userName" name="userName"
										class="form-control round-input" disabled="disabled" /></td>
								</tr>
								<tr>
									<td>Email :</td>
									<td><input ng-model="email" name="email"
										class="form-control round-input" disabled="disabled" /></td>
								</tr>
								<tr>
									<td>Cognizant ID :</td>
									<td><input ng-model="cognizantId" name="cognizantId"
										class="form-control round-input" required /></td>
								</tr>
								<tr>
									<td>Phone :</td>
									<td><input ng-model="phone" name="phone"
										class="form-control round-input" required /></td>
								</tr>
								<tr>
									<td><input type="hidden" ng-model="imageURL"
										name="imageURL" class="form-control round-input" /></td>
								</tr>
							</tbody>
						</table>
						<button type="submit" class="btn btn-primary"
							onclick="return saveUserDetails(this)">Save</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal body ends here -->
	<!-- Javascript -->
	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/scripts.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular.min.js"></script>
	<script src="https://code.angularjs.org/1.4.5/angular-route.js"></script>
	<script src="https://connect.facebook.net/en_US/all.js"></script>
	<script
		src="https://apis.google.com/js/platform.js?onload=renderButton" async
		defer></script>
	<script type="text/javascript" src="js/facebook-login.js"></script>
	<script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
	<script type="text/javascript" src="custom-js/login-register.js"></script>
	<script src="custom-js/jquery.toastmessage.js"></script>
	<script src="plugins-js/alertify.min.js"></script>
	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
</body>

</html>