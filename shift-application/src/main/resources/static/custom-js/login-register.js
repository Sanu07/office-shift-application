var app = angular.module("shiftApp", []);
app.controller("login-register-controller", function($scope, $http) {
	$scope.validateLoginDetails = function(loginDetails) {
		console.log(loginDetails);
		var loginEmail = $('#form-email').val().trim();
		var loginPassword = $('#form-password').val().trim();
		if ($('#form-email').val().trim().length < 1
				|| $('#form-password').val().trim().length < 1) {
			$().toastmessage('showErrorToast',
					"Fill out all the fields and then click on Sign in");
			return false;
		}
	}

	$scope.validateRegistrationDetails = function(event) {
		var firstName = $('#form-first-name').val().trim();
		var cognizantId = $('#form-cognizant-id').val().trim();
		var email = $('#form-email-reg').val().trim();
		var phone = $('#form-phone').val().trim();
		var pswd = $('#form-password-reg').val().trim();
		var conpswd = $('#form-conpassword-reg').val().trim();
		if (firstName.length < 1 || cognizantId.length < 1 || email.length < 1
				|| phone.length < 1 || pswd.length < 1 || conpswd.length < 1) {
			event.preventDefault();
			$().toastmessage('showErrorToast',
					"Fill out all the fields and then click on Sign me up!");
			return false;
		} else if (!validateForm(email)) {
			event.preventDefault();
			$().toastmessage('showErrorToast', "Invalid email ID");
			return false;
		} else if (pswd != conpswd) {
			event.preventDefault();
			$().toastmessage('showErrorToast',
					"Password and Confirm password should match");
			return false;
		}
	}
})
function validateForm(email) {
	var x = email;
	var atpos = x.indexOf("@");
	var dotpos = x.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length) {
		return false;
	} else {
		return true;
	}
}