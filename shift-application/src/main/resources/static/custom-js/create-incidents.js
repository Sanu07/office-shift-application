/*
 * Controller for #create-incident-form. This is used to create a jira along with
 * response message
 */
var app = angular.module("shiftApp",
		[ 'angularUtils.directives.dirPagination' ]);
app
		.controller(
				"create-incident-controller",
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
					$scope.saveIncident = function() {
						checkUserLoginStatus();
						var formData = $('#create-incident-form')
								.serializeArray();
						// console.log(formData);
						if (formData[0].value.length < 1) {
							alertify.alert("Incident Number cannot be blank");
							return false;
						} else if (formData[2].value.length < 1) {
							alertify.alert("Acknowledged By cannot be blank");
							return false;
						} else if (formData[3].value.length < 1) {
							alertify.alert("Assignee Name cannot be blank");
							return false;
						} else if (formData[6].value.length < 1) {
							alertify.alert("Issue Summary cannot be blank");
							return false;
						} else if (formData[7].value.length < 1) {
							alertify.alert("Issue Description cannot be blank");
							return false;
						} else if (formData[8].value.length < 1) {
							alertify
									.alert("Comments On Resolution cannot be blank");
							return false;
						} else if (formData[9].value.length < 1) {
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
							url : "incidents",
							data : JSONFormData
						})
								.then(
										function(response) {
											console
													.log(response.data.applicationName);
											$scope.responseMessage = "Incident has been created successfully";
											$scope.applicationName = response.data.applicationName;
											$scope.issueSummary = response.data.issueSummary;
											$scope.issueDescription = response.data.issueDescription;
											$scope.currentStatus = response.data.currentStatus;
											$scope.commentsOnResolution = response.data.commentsOnResolution;
											$scope.linkedJira = response.data.linkedJira;
											$('#create-incident-response-modal')
													.modal('toggle');
											// $scope.create-incident-form.$setPristine();
											document.getElementById(
													'create-incident-form')
													.reset();
										},
										function(response) {
											console.log(response);
											$scope.responseMessage = "Failed to create the Incident.Please contact the admin for further details.";
											$(
													'#create-incident-response-modal .modal-body')
													.hide();
											$('#create-incident-response-modal')
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
 * List all incidents
 */
app
		.controller(
				'list-incident-controller',
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

					$scope.getAllIncidents = function() {
						$http({
							method : "GET",
							url : "incidents",
						}).then(function(response) {
							console.log(response)
							$scope.incidentListResponse = response;
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

					$scope.listJira = function(jiraDetails) {
						console.log(jiraDetails.data.linkedJira);
						checkUserLoginStatus();
						$http(
								{
									method : "GET",
									url : "jiras/jiraNumber/"
											+ jiraDetails.data.linkedJira
								})
								.then(
										function(response) {
											console.log(response)
											$scope.applicationName = response.data.applicationName;
											$scope.jiraNumber = response.data.jiraNumber;
											$scope.assigneeName = response.data.assigneeName;
											$scope.currentStatus = response.data.currentStatus;
											$scope.issueSummary = response.data.issueSummary;
											$scope.comments = response.data.comments;
											$('#edit-jira-modal').modal(
													'toggle');
										}, function(response) {
											console.log("Error");
											console.log(response);
										});
					}

					/*
					 * Open edit incident modal on clicking edit button
					 */
					$scope.editIncident = function(incidentDetails) {
						console.log(incidentDetails);
						checkUserLoginStatus();
						$scope.incidentId = incidentDetails.data.incidentId;
						$scope.applicationName = incidentDetails.data.applicationName;
						$scope.incidentNumber = incidentDetails.data.incidentNumber;
						$scope.assigneeName = incidentDetails.data.assigneeName;
						$scope.currentStatus = incidentDetails.data.currentStatus;
						$scope.acknowledgedBy = incidentDetails.data.acknowledgedBy;
						$scope.commentsOnResolution = incidentDetails.data.commentsOnResolution;
						$scope.issueDescription = incidentDetails.data.issueDescription;
						$scope.issueDate = incidentDetails.data.issueData;
						$scope.issueSummary = incidentDetails.data.issueSummary;
						$scope.timeSpent = incidentDetails.data.timeSpent;
						$scope.linkedJira = incidentDetails.data.linkedJira;
						$('#edit-incident-modal').modal('toggle');
					}

					$scope.saveEditedIncident = function(incidentDetails) {
						console.log(incidentDetails);
						checkUserLoginStatus();
						$scope.editResponseMessage = "";
						var incident = {};
						incident = {
							incidentId : incidentDetails.incidentId,
							acknowledgedBy : incidentDetails.acknowledgedBy,
							applicationName : incidentDetails.applicationName,
							assigneeName : incidentDetails.assigneeName,
							commentsOnResolution : incidentDetails.commentsOnResolution,
							currentStatus : incidentDetails.currentStatus,
							incidentNumber : incidentDetails.incidentNumber,
							issueDate : incidentDetails.issueDate,
							issueDescription : incidentDetails.issueDescription,
							issueSummary : incidentDetails.issueSummary,
							timeSpent : incidentDetails.timeSpent,
							linkedJira : incidentDetails.linkedJira
						};
						$http({
							method : "PUT",
							url : "incidents",
							data : incident
						})
								.then(
										function(response) {
											// console.log(response)
											$scope.editResponseMessage = "Incident Edited Successfully";
											$scope.responseColor = "green";
											setTimeout(function() {
												$('#edit-incident-modal')
														.modal('hide');
											}, 1000);
											$scope.getAllIncidents();
										}, function(response) {
											alert('working');
										});
					}

					/*
					 * Deleting incidents
					 */
					$scope.deleteIncident = function(incidentDetails) {
						checkUserLoginStatus();
						var incidentId = incidentDetails.data.incidentId;
						$
								.confirm({
									'title' : 'Delete   '
											+ incidentDetails.data.incidentNumber,
									'message' : 'You are about to delete. <br />It cannot be restored at a later time! Continue?',
									'buttons' : {
										'Yes' : {
											'class' : 'blue',
											'action' : function() {
												$http(
														{
															method : "DELETE",
															url : "incidents/"
																	+ incidentId,
														})
														.then(
																function(
																		response) {
																	console
																			.log('incident deleted successfully');
																	$scope
																			.getAllIncidents();
																},
																function(
																		response) {
																	console
																			.log("error occured while deleting incident");
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
							if (item.incidentNumber.toLowerCase().indexOf(
									searchString) !== -1
									|| item.applicationName.toLowerCase()
											.indexOf(searchString) !== -1
									|| item.assigneeName.toLowerCase().indexOf(
											searchString) !== -1
									|| item.issueDescription.toLowerCase()
											.indexOf(searchString) !== -1
									|| item.commentsOnResolution.toLowerCase()
											.indexOf(searchString) !== -1
									|| item.linkedJira.toLowerCase().indexOf(
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