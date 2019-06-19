var app = angular.module("shiftApp", [ 'notifications' ]);
app
		.controller(
				"create-wsr-controller",
				function($scope, $http, $notification) {
					// $notification.info("hello title", "hello content", "User
					// data");
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
					/*
					 * check WSR Details
					 */
					$http({
						method : "GET",
						url : "/wsrDetails"
					})
							.then(
									function(response) {
										var status = true;
										for (var i = 0; i < response.data.length; i++) {
											if (response.data[i].cognizantId == $scope.cognizantId) {
												status = false;
											}
										}
										if (!status) {
											$()
													.toastmessage(
															'showToast',
															{
																text : 'You have not updated your WSR yet',
																stayTime : 5000,
																inEffectDuration : 1000,
																sticky : false,
																type : 'warning'
															});
										}
									}, function(response) {
										console.log(response);
									})

					$("#create-wsr-button")
							.click(
									function() {
										var formData = $("#create-wsr-form")
												.serializeArray();
										// console.log(formData);
										var obj = [];
										var resp = {};
										for (var i = 1; i < formData.length; i++) {
											if (formData[i].value < 1) {
												$()
														.toastmessage(
																'showErrorToast',
																"Remove the fields that are not required and then press submit");
												return false;
											} else {
												obj.push(formData[i].value);
											}
										}
										var curDate = new Date();
										var dd = curDate.getDate();
										var mm = curDate.getMonth() + 1;
										var yr = curDate.getFullYear();
										var wsrDate = dd + "-" + mm + "-" + yr;
										resp['userName'] = $scope.name;
										resp['cognizantId'] = $scope.cognizantId;
										resp['listOfJiras'] = obj;
										var JSONFormData = JSON.stringify(resp);
										$http({
											method : "POST",
											url : "/wsrDetails",
											data : JSONFormData
										})
												.then(
														function(response) {
															var respMessage = "";
															for (var i = 0; i < response.data.listOfJiras.length; i++) {
																respMessage = respMessage
																		+ response.data.listOfJiras[i]
																		+ " ";
															}
															$()
																	.toastmessage(
																			'showSuccessToast',
																			respMessage
																					+ " have been added successfully");
															document
																	.getElementById(
																			'create-wsr-form')
																	.reset();
														},
														function(response) {
															console
																	.log(response)
														})
									})

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

/*
 * List WSR Details
 */
app
		.controller(
				"list-wsr-controller",
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

					$scope.getAllWSRDetails = function() {
						$http({
							method : "GET",
							url : "/wsrDetails"
						}).then(function(response) {
							console.log(response);
							$scope.wsrResponse = response.data;
						}, function(response) {
							console.log(response);
						})
					}

					$scope.sort = function(keyname) {
						$scope.sortKey = keyname; // set the sortKey to the
													// param passed
						$scope.reverse = !$scope.reverse; // if true make it
															// false and vice
															// versa
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
getMonthName = function(v) {
	var n = [ "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" ];
	return n[v];
}