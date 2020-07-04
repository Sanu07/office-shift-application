/*
 * Controller for #create-jira-form. This is used to create a jira along with
 * response message
 */
var app = angular.module("shiftApp",
		[ 'angularUtils.directives.dirPagination' ]);
app
		.controller(
				"create-jira-controller",
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
					$scope.saveJira = function() {
						var formData = $('#create-jira-form').serializeArray();
						checkUserLoginStatus();
						console.log(formData);
						if (formData[0].value.length < 1) {
							alertify.alert("Jira Number cannot be blank");
							return false;
						} else if (formData[0].value.length < 1) {
							alertify.alert("Jira Number cannot be blank");
							return false;
						} else if (formData[2].value.length < 1) {
							alertify.alert("Reporter Name cannot be blank");
							return false;
						} else if (formData[3].value.length < 1) {
							alertify.alert("Assignee Name cannot be blank");
							return false;
						} else if (formData[4].value.length < 1) {
							alertify.alert("Start Date cannot be blank");
							return false;
						} else if (formData[5].value.length < 1) {
							alertify.alert("End Date cannot be blank");
							return false;
						} else if (new Date(formData[4].value).getTime() > new Date(
								formData[5].value).getTime()) {
							alertify
									.alert("End date cannot be after start date");
							return false;
						} else if (formData[6].value.length < 1) {
							alertify.alert("Deployment Date cannot be blank");
							return false;
						} else if (new Date(formData[5].value).getTime() > new Date(
								formData[6].value).getTime()) {
							alertify
									.alert("End date cannot be after Deployment date");
							return false;
						} else if (formData[8].value.length < 1) {
							alertify.alert("Issue Summary cannot be blank");
							return false;
						} else if (formData[9].value.length < 1) {
							alertify.alert("Comments cannot be blank");
							return false;
						} else if (formData[10].value.length < 1) {
							alertify.alert("Time Spent cannot be blank");
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
							url : "jiras",
							data : JSONFormData
						})
								.then(
										function(response) {
											console.log(response);
											$scope.responseMessage = "Jira has been created successfully";
											$scope.applicationName = response.data.applicationName;
											$scope.issueSummary = response.data.issueSummary;
											$scope.jiraNumber = response.data.jiraNumber;
											$scope.currentStatus = response.data.currentStatus;
											$scope.createdBy = response.data.createdBy;
											$scope.responseColor = "green";
											$('#create-jira-response-modal')
													.modal('toggle');
											document.getElementById(
													'create-jira-form').reset();
										},
										function(response) {
											console.log(response);
											$scope.responseMessage = "Failed to create the Jira.Please contact the admin for further details.";
											$scope.responseColor = "red";
											$(
													'#create-jira-response-modal .modal-body')
													.hide();
											$('#create-jira-response-modal')
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

/*
 * ------------------List-All-Jiras------------------
 */

app
		.controller(
				"list-jira-controller",
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
					$('#edit-jira-modal').hide();
					$scope.getAllJiras = function() {
						$http({
							method : "GET",
							url : "jiras",
						}).then(function(response) {
							console.log(response)
							$scope.jiraListResponse = response;
							$('.se-pre-con').hide();
						}, function(response) {
							console.log("Error");
							console.log(response);
						});
					}

					$scope.sort = function(keyname) {
						$scope.sortKey = keyname; // set the sortKey to the
													// param passed
						$scope.reverse = !$scope.reverse; // if true make it
															// false and vice
															// versa
					}

					$scope.deleteJira = function(jiraDetails) {
						checkUserLoginStatus();
						var jiraId = jiraDetails.data.jiraId;

						// var elem = $(this).closest('.item');

						$
								.confirm({
									'title' : 'Delete   '
											+ jiraDetails.data.jiraNumber,
									'message' : 'You are about to delete. <br />It cannot be restored at a later time! Continue?',
									'buttons' : {
										'Yes' : {
											'class' : 'blue',
											'action' : function() {
												$http({
													method : "DELETE",
													url : "jiras/" + jiraId,
												}).then(function(response) {
													$scope.getAllJiras();
												}, function(response) {
													alert("error occured");
												});
											}
										},
										'No' : {
											'class' : 'gray',
											'action' : function() {
											} // Nothing to do in this case.
												// You can as well omit the
												// action property.
										}
									}
								});
					}

					/*---------------------
					 * Edit Jira 
					 * --------------------
					 */
					$scope.editJira = function(jiraDetails) {
						console.log(jiraDetails);
						checkUserLoginStatus();
						$scope.editResponseMessage = "";
						$scope.jiraId = jiraDetails.data.jiraId;
						$scope.applicationName = jiraDetails.data.applicationName;
						$scope.jiraNumber = jiraDetails.data.jiraNumber;
						$scope.assigneeName = jiraDetails.data.assigneeName;
						$scope.createdAt = jiraDetails.data.createdAt;
						$scope.createdBy = jiraDetails.data.createdBy;
						$scope.updatedAt = jiraDetails.data.updatedAt;
						$scope.deploymentDate = jiraDetails.data.deploymentDate;
						$scope.startDate = jiraDetails.data.startDate;
						$scope.endDate = jiraDetails.data.endDate;
						$scope.noOfDaysSpent = jiraDetails.data.noOfDaysSpent;
						$scope.reporterName = jiraDetails.data.reporterName;
						$scope.timeSpent = jiraDetails.data.timeSpent;
						$scope.currentStatus = jiraDetails.data.currentStatus;
						$scope.issueSummary = jiraDetails.data.issueSummary;
						$scope.updatedBy = jiraDetails.data.updatedBy;
						$scope.comments = jiraDetails.data.comments;
						$('#edit-jira-modal').modal('toggle');
					}

					$scope.saveEditedJira = function(jiraDetails) {
						checkUserLoginStatus();
						var jira = {};
						jira = {
							jiraId : jiraDetails.jiraId,
							applicationName : jiraDetails.applicationName,
							jiraNumber : jiraDetails.jiraNumber,
							assigneeName : jiraDetails.assigneeName,
							createdAt : jiraDetails.createdAt,
							createdBy : jiraDetails.createdBy,
							deploymentDate : jiraDetails.deploymentDate,
							startDate : jiraDetails.startDate,
							endDate : jiraDetails.endDate,
							noOfDaysSpent : jiraDetails.noOfDaysSpent,
							reporterName : jiraDetails.reporterName,
							timeSpent : jiraDetails.timeSpent,
							currentStatus : jiraDetails.currentStatus,
							issueSummary : jiraDetails.issueSummary,
							comments : jiraDetails.comments
						};
						console.log(jiraDetails);
						$http({
							method : "PUT",
							url : "jiras",
							data : jira
						})
								.then(
										function(response) {
											$scope.editResponseMessage = "Jira Edited Successfully";
											$scope.responseColor = "green";
											setTimeout(function() {
												$('#edit-jira-modal').modal(
														'hide');
											}, 1000);
											$scope.getAllJiras();
										},
										function(response) {
											$scope.editResponseMessage = "Failed to Edit the Jira"
											$scope.responseColor = "red";
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

/*
 * Search filter
 */
app.filter('searchFor', function() {
	return function(arr, searchString) {
		if (!searchString) {
			return arr;
		}
		var result = [];
		searchString = searchString.toLowerCase();
		angular
				.forEach(arr,
						function(item) {
							if (item.jiraNumber.toLowerCase().indexOf(
									searchString) !== -1
									|| item.applicationName.toLowerCase()
											.indexOf(searchString) !== -1
									|| item.assigneeName.toLowerCase().indexOf(
											searchString) !== -1
									|| item.currentStatus.toLowerCase()
											.indexOf(searchString) !== -1
									|| item.comments.toLowerCase().indexOf(
											searchString) !== -1
									|| item.issueSummary.toLowerCase().indexOf(
											searchString) !== -1
									|| item.updatedBy.toLowerCase().indexOf(
											searchString) !== -1) {
								result.push(item);
							}
						});
		return result;
	};
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