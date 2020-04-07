var app = angular.module("shiftApp", []);
app
		.controller(
				'user-profile-controller',
				function($scope, $http) {
					$http({
						method : "GET",
						url : "getLoggedInUser",
					}).then(function(response) {
						console.log(response);
						if (response.data.length < 1) {
							window.location.href = "/";
						}
						$scope.name = response.data.name;
						$scope.email = response.data.email;
						$scope.phone = response.data.phone;
						$scope.cognizantId = response.data.cognizantId;
						$scope.userId = response.data.userId;
						$scope.lastLoginTime = response.data.lastloginTime;
						$scope.imageURL = response.data.imageURL;
					}, function(response) {
						console.log('Error');
					});

					$('.item active').hide();
					$http(
							{
								method : "GET",
								url : "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=f8083b8df9024fc2bffeab62316e6596"
							}).then(function(response) {
						console.log(response);
						$scope.response = response;
						$('.se-pre-con').hide();
						$('.item active').show();
					}, function(response) {
						console.log(response)
					});

					$http({
						method : "GET",
						url : "status",
					}).then(function(response) {
						console.log(response);
						$scope.statusData = response.data;
					}, function(response) {
						console.log('Error');
					});
					$('.carousel').carousel({
						interval : 1000 * 5
					});
					$scope.changePassword = function() {
						checkUserLoginStatus();
						var formData = $('#change-password-form')
								.serializeArray();
						console.log(formData);
						var cognizantId = $scope.cognizantId;
						$http({
							method : "GET",
							url : "/user/cognizantId/" + cognizantId,
						})
								.then(
										function(response) {
											if (response.data.password == formData[0].value
													&& formData[1].value == formData[2].value) {
												var data = {
													userId : response.data.userId,
													name : response.data.name,
													cognizantId : response.data.cognizantId,
													email : response.data.email,
													phone : response.data.phone,
													password : formData[1].value
												};
												console.log(JSON
														.stringify(data));
												$http({
													method : "PUT",
													dataType : "json",
													url : "saveUser",
													data : JSON.stringify(data)
												})
														.then(
																function(
																		response) {
																	console
																			.log(response)
																	document
																			.getElementById(
																					'change-password-form')
																			.reset();
																	$()
																			.toastmessage(
																					'showSuccessToast',
																					'You have successfully changed your password');
																},
																function(
																		response) {
																	$()
																			.toastmessage(
																					'showErrorToast',
																					'Some problem occured while updating the password');
																})
											} else {
												alert("error occured");
											}

										}, function(response) {
											console.log('Error');
										});
					}

					$scope.editProfile = function() {
						checkUserLoginStatus();
						var formData = $('#edit-profile-form').serializeArray();
						console.log(formData);
						var cognizantId = $scope.cognizantId;
						$http({
							method : "GET",
							url : "/user/cognizantId/" + cognizantId,
						})
								.then(
										function(response) {
											var data = {
												userId : response.data.userId,
												name : formData[0].value,
												cognizantId : cognizantId,
												email : formData[1].value,
												phone : formData[2].value,
												password : response.data.password
											};
											$http({
												method : "PUT",
												dataType : "json",
												url : "saveUser",
												data : JSON.stringify(data)
											})
													.then(
															function(response) {
																console
																		.log(response)
																$scope.name = response.data.name;
																$scope.email = response.data.email;
																$scope.phone = response.data.phone;
																$scope.cognizantId = response.data.cognizantId;
																$scope.userId = response.data.userId;
																document
																		.getElementById(
																				'edit-profile-form')
																		.reset();
																$()
																		.toastmessage(
																				'showSuccessToast',
																				'You have successfully edited your profile');
															},
															function(response) {
																console
																		.log('edit profile error')
															})
										}, function(response) {
											console.log('edit profile error')
										});
					}

					$scope.submitStatus = function() {
						checkUserLoginStatus();
						var formData = $('#status-form').serializeArray();
						var status = formData[0].value;
						var cognizantId = $scope.cognizantId;
						var name = $scope.name;
						var data = {
							name : name,
							cognizantId : cognizantId,
							status : status
						}
						$http({
							method : "POST",
							url : "status",
							data : {
								name : name,
								cognizantId : cognizantId,
								status : status
							}
						})
								.then(
										function(response) {
											console.log(response)
											document.getElementById(
													'status-form').reset();
											$()
													.toastmessage(
															'showSuccessToast',
															'You have successfully added your status');
										}, function(response) {
											console.log('Status Error');
											console.log(response);
										})
					}

					$scope.loadStatus = function() {
						checkUserLoginStatus();
						$http({
							method : "GET",
							url : "status",
						}).then(function(response) {
							console.log(response);
							$scope.statusData = response.data;
						}, function(response) {
							console.log('Error');
						});
					}
					var date = new Date();
					var month = getMonthName(date.getMonth());
					var year = date.getFullYear();
					var today = date.getDate();
					$http({
						url : "/shifts/month/" + month + "/" + year
					})
							.then(
									function(response) {
										console.log(response);
										var individualShiftArray = new Array();
										var morningCount = 0, weeklyOffCount = 0, nightCount = 0, afternoonCount = 0;
										for (var i = 0; i < response.data.length; i++) {
											var shift = JSON
													.parse(response.data[i].individualShiftModel)[today].shift;
											if (shift == 'W') {
												++weeklyOffCount;
											} else if (shift == 'M') {
												++morningCount;
											} else if (shift == 'N') {
												++nightCount;
											} else if (shift == 'A') {
												++afternoonCount;
											}
										}
										$scope.shiftDetailsMonth = month;
										$scope.shiftDetailsYear = year;
										$scope.mCount = morningCount;
										$scope.nCount = nightCount;
										$scope.aCount = afternoonCount;
										$scope.wCount = weeklyOffCount;
										$scope.totalCount = response.data.length;
										// JSON.parse(response.data[0].individualShiftModel)["1"].shift
									}, function(response) {
										console.log(response);
									})
					/*
					 * get the list of incidents after a particular date
					 */
					$http(
							{
								url : "/incidents/afterDate/"
										+ (date.getMonth()) + "-" + today + "-"
										+ year,
								method : "GET"
							}).then(function(response) {
						$scope.incidentsResponse = response.data;
					}, function(response) {
						console.log(response);
					});
					/*
					 * end of getting the list of incidents after a particular
					 * date
					 */

					/*
					 * get the list of jiras after a particular date
					 */
					$http(
							{
								url : "/jiras/afterDate/"
										+ (date.getMonth() + 1) + "-" + today
										+ "-" + year,
								method : "GET"
							}).then(function(response) {
						$scope.jirasResponse = response.data;
					}, function(response) {
						console.log(response);
					});
					/*
					 * end of getting the list of jiras after a particular date
					 */
					/*
					 * start of getting leaves after a particular date
					 */
					$http(
							{
								url : "/leaves/afterDate/"
										+ (date.getMonth() + 1) + "-" + today
										+ "-" + year,
								method : "GET"
							}).then(function(response) {
						$scope.leavesResponse = response.data;
					}, function(response) {
						console.log(response);
					});
					/*
					 * end of getting leaves after a particular date
					 */
				});
function checkUserLoginStatus() {
	$
			.ajax({
				method : "GET",
				url : "getLoggedInUser",
			})
			.then(
					function(response) {
						console.log(response);
						if (response.cognizantId.length < 1) {
							window.location.href = "/";
						}
					},
					function(response) {
						console
								.log("problem occured :: checkUserLoginStatus :: in retrieving user logged in status");
						window.location.href = "/";
					});
}
getMonthName = function(v) {
	var n = [ "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" ];
	return n[v];
}