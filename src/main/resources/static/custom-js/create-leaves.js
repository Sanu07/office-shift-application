/**
 * Controller for #create-jira-form. This is used to create a jira along with
 * response message
 */
var app = angular.module("shiftApp",
		[ 'angularUtils.directives.dirPagination' ]);
app
		.controller(
				"create-leave-controller",
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
					$scope.saveLeave = function() {
						checkUserLoginStatus();
						var formData = $('#create-leave-form').serializeArray();
						console.log(formData);
						/**
						 * checking validity of the start and end date
						 */
						if (formData[1].value.length < 1) {
							alertify.alert("Leave start date cannot be blank");
							$('[name=leaveStartDate]').focus();
							return false;
						} else if (formData[2].value.length < 1) {
							alertify.alert("Leave end date cannot be blank");
							$('[name=leaveEndDate]').focus();
							return false;
						} else if (new Date(formData[1].value).getTime() > new Date(
								formData[2].value).getTime()) {
							alertify
									.alert("Leave start date cannot be after leave end date");
							$('[name=leaveStartDate]').focus();
							return false;
						}
						var resp = {};

						for (var i = 0; i < formData.length; i++) {
							var obj = formData[i];
							resp[obj.name] = obj.value;
						}
						var JSONFormData = JSON.stringify(resp);
						console.log("result : " + JSONFormData);
						$http({
							method : "POST",
							url : "leaves",
							data : JSONFormData
						})
								.then(
										function(response) {
											$scope.responseMessage = "Leave has been added successfully";
											$scope.name = response.data.name;
											$scope.leaveType = response.data.leaveType;
											$scope.leaveStartDate = response.data.leaveStartDate;
											$scope.leaveEndDate = response.data.leaveEndDate;
											$scope.noOfLeaveDays = response.data.noOfLeaveDays;
											$('#create-leave-response-modal')
													.modal('toggle');
											setTimeout(
													function() {
														$scope.responseMessage = "Your Leave approval is now pending with your admin";
													}, 3000);
											setTimeout(
													function() {
														$(
																'#create-leave-response-modal')
																.modal('hide');
													}, 5000);
											document.getElementById(
													'create-leave-form')
													.reset();
										},
										function(response) {
											console.log(response);
											$scope.responseMessage = "Failed to create the applied Leave.Please contact the admin for further details.";
											$(
													'#create-leave-response-modal .modal-body')
													.hide();
											$('#create-leave-response-modal')
													.modal('toggle');
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

app
		.controller(
				'list-leaves-controller',
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
					$scope.getAllLeaves = function() {
						$http({
							method : "GET",
							url : "leaves",
						}).then(function(response) {
							console.log(response);
							$scope.leavesListResponse = response;
							$('.se-pre-con').hide();
						}, function(response) {
							alert('leave error');
						});
					}

					$scope.approveLeave = function(leaveDetails) {
						checkUserLoginStatus();
						leaveDetails.data.approvalStatus = true;
						$http({
							method : "PUT",
							url : "leaves",
							data : JSON.stringify(leaveDetails.data)
						}).then(function(response) {
							// console.log(response);
							$scope.getAllLeaves();
							// $scope.leavesListResponse = response;
						}, function(response) {
							alert('leave error');
						});
					}
					$scope.rejectLeave = function(leaveDetails) {
						checkUserLoginStatus();
						leaveDetails.data.approvalStatus = false;
						$http({
							method : "PUT",
							url : "leaves",
							data : JSON.stringify(leaveDetails.data)
						}).then(function(response) {
							// console.log(response);
							$scope.getAllLeaves();
							// $scope.leavesListResponse = response;
						}, function(response) {
							alert('leave error');
						});
					}
					$scope.getMyLeaves = function() {
						$http({
							method : "GET",
							url : "getLoggedInUser",
						})
								.then(
										function(response) {
											console.log(response);
											$http(
													{
														method : "GET",
														url : "/leaves/cognizantId/"
																+ response.data.cognizantId,
													})
													.then(
															function(response) {
																console
																		.log(response);
																$scope.leavesListResponse = response;
																$('.se-pre-con')
																		.hide();
															},
															function(response) {
																alert('leave error');
															});
										}, function(response) {
											console.log('Error');
											$('.se-pre-con').hide();
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
				})
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