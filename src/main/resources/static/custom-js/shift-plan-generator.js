/*
 * Shift plan generator.
 * create shift plan.
 */
var app = angular.module("shiftApp", []);
app
		.controller(
				"shift-plan-controller",
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
					$('#shift-plan-table').hide();
					$scope.generateShiftPlan = function() {
						checkUserLoginStatus();
						var monthSelected = $('#month').val();
						if (monthSelected == undefined
								|| monthSelected.length < 1) {
							alertify.alert("Date field cannot be blank");
							return false;
						}
						var getFullDate = new Date(monthSelected);
						var monthString = getFullDate.getMonth();
						var month = getMonthName(monthString);
						var year = getFullDate.getFullYear();
						var day = getFullDate.getDay();
						var getLastDayOfMonth = new Date(getFullDate
								.getFullYear(), getFullDate.getMonth() + 1, 0,
								23, 59, 59);
						getLastDayOfMonth = getLastDayOfMonth.getDate();
						var dayOfTheWeek = getDateAndDayName(year,
								monthSelected, day, getLastDayOfMonth);
						console.log(dayOfTheWeek);
						$http({
							method : "GET",
							url : "users",
						})
								.then(
										function(response) {
											console.log(response);
											$('#shift-plan-table').show();
											$scope.getLastDayOfMonth = getLastDayOfMonth;
											$scope.getDaysInMonth = dayOfTheWeek;
											$scope.month = month;
											$scope.year = year;
											$scope.response = response.data;

											/*
											 * get the cell color on page load
											 */
											setTimeout(
													function() {
														$(
																"#shift-plan-generator-table-row tr")
																.each(
																		function(
																				rowIndex) {
																			var $tds = $(
																					this)
																					.find(
																							'td');
																			for (var i = 0; i < $tds.length - 1;) {
																				var shiftType = $tds
																						.eq(
																								++i)
																						.find(
																								'select')
																						.val();
																				if (shiftType == 'W') {
																					$tds
																							.eq(
																									i)
																							.find(
																									'select')
																							.addClass(
																									'weeklyOff');
																				} else if (shiftType == 'M') {
																					$tds
																							.eq(
																									i)
																							.find(
																									'select')
																							.addClass(
																									'morning');
																				} else if (shiftType == 'N') {
																					$tds
																							.eq(
																									i)
																							.find(
																									'select')
																							.addClass(
																									'night');
																				} else {
																					$tds
																							.eq(
																									i)
																							.find(
																									'select')
																							.addClass(
																									'afternoon');
																				}
																			}
																		})
													}, 200);
										}, function(response) {
											// console.log(response);
										});
					}
					$scope.saveShiftPlan = function(data) {
						checkUserLoginStatus();
						$http(
								{
									method : "GET",
									url : "shifts/month/" + data.month + "/"
											+ data.year,
								})
								.then(
										function(response) {
											if (/*response.data.length > 0*/ false) {
												$scope.shiftResponseMessage = "You have already created the shift plan for the month of "
														+ data.month
														+ "  "
														+ data.year;
												$(
														'#create-shift-response-modal')
														.modal('toggle');
											} else {
												var shiftData = [];
												$(
														"#shift-plan-generator-table-row tr")
														.each(
																function(
																		rowIndex) {
																	var tempObj = {};
																	var userData = {};
																	var $tds = $(
																			this)
																			.find(
																					'td');
																	var userName = $tds
																			.eq(
																					0)
																			.text();
																	// var
																	// userId =
																	// data.response[rowIndex].userId;
																	var userId = data.response[rowIndex].cognizantId;
																	for (var i = 0; i < $tds.length - 1;) {
																		var shiftType = $tds
																				.eq(
																						++i)
																				.find(
																						'select')
																				.val();
																		tempObj[i] = {
																			date : i,
																			dayOfTheWeek : (data.getDaysInMonth[i].day)
																					.substring(
																							0,
																							3),
																			name : userName,
																			userId : userId,
																			shift : shiftType
																		};
																		// console.log(JSON.stringify(tempObj))
																	}
																	userData = {
																		name : userName,
																		userId : userId,
																		month : data.month,
																		year : data.year,
																		individualShiftModel : JSON
																				.stringify(tempObj)
																	};
																	shiftData
																			.push(userData);
																});
												var stringifiedJSON = JSON
														.stringify(shiftData);
												$http({
													method : "POST",
													url : "shifts",
													data : stringifiedJSON
												})
														.then(
																function(
																		response) {
																	$scope.saveShiftResponse = response.data;
																	$scope.shiftResponseMessage = 'Shift Plan has been saved successfully for the month of '
																			+ data.month
																			+ ' '
																			+ data.year
																			+ ' for the following users.';
																	$(
																			'#create-shift-response-modal')
																			.modal(
																					'toggle');
																},
																function(
																		response) {
																	console
																			.log("Error");
																	$scope.shiftResponseMessage = 'Failed to generate the Shift Plan for the month of '
																			+ data.month
																			+ ' '
																			+ data.year;
																	$(
																			'#create-shift-response-modal')
																			.modal(
																					'toggle');
																});
											}
										},
										function(response) {
											alert('shift plan generator error');
										});

					}

					/*
					 * This method is used to change the 'select' background
					 * color based on the changed value
					 */
					$scope.changedValue = function(selectedShift, colIndex,
							rowIndex) {
						console.log(selectedShift + ' : ' + colIndex + ' : '
								+ rowIndex);
						var $position = $("#shift-plan-generator-table-row tr")
								.eq(rowIndex).find('td').eq(colIndex + 1).find(
										'select');
						if (selectedShift == 'A') {
							$position.attr('class', 'form-control afternoon');
						} else if (selectedShift == 'M') {
							$position.attr('class', 'form-control morning');
						} else if (selectedShift == 'N') {
							$position.attr('class', 'form-control night');
						} else if (selectedShift == 'W') {
							$position.attr('class', 'form-control weeklyOff');
						}
					}

					getMonthName = function(v) {
						var n = [ "January", "February", "March", "April",
								"May", "June", "July", "August", "September",
								"October", "November", "December" ];
						return n[v]
					}
					getDayName = function(v) {
						var n = [ "Sun", "Mon", "Tue", "Wed", "Thu", "Fri",
								"Sat" ];
						return n[v]
					}
					getDateAndDayName = function(year, month, day,
							lastDayOfMonth) {
						var shiftDateAndTime = {};
						var getFullDate = new Date(month);
						for (var i = 1; i <= lastDayOfMonth; i++) {
							var x = getDayName((getFullDate.getDay()));
							shiftDateAndTime[i] = {
								date : i,
								day : x
							};
							getFullDate.setDate(getFullDate.getDate() + 1);
						}
						return shiftDateAndTime;
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
 * ---------------------------- List all Shifts ----------------------------
 */
app
		.controller(
				"list-shift-plan-controller",
				function($http, $scope) {
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
					$('#list-shift-plan-table').hide();
					$scope.listAllShiftData = function() {
						checkUserLoginStatus();
						if ($('#monthAndYear').val() == undefined
								|| $('#monthAndYear').val().length < 1) {
							alertify.alert("Date field cannot be blank");
							return false;
						}
						var monthAndYear = $('#monthAndYear').val().split("-");
						var monthName = getMonthName(monthAndYear[1] - 1);
						var year = monthAndYear[0];
						$http({
							method : "GET",
							url : "shifts/month/" + monthName + "/" + year,
						})
								.then(
										function(response) {
											// console.log(response)
											if (response.data.length < 1) {
												alertify
														.alert("Sorry! No shift plan for "
																+ monthName
																+ " "
																+ year
																+ " has been updated yet");
												return false;
											}
											var shiftData = new Array(
													response.data.length);
											for (var i = 0; i < response.data.length; i++) {
												shiftData[i] = JSON
														.parse(response.data[i].individualShiftModel);
											}
											$scope.responseData = response.data;
											$scope.individualShiftData = shiftData;
											$scope.month = response.data[0].month;
											$scope.year = response.data[0].year;
											$('#list-shift-plan-table').show();
											/*
											 * Add colour to the cells based on
											 * the Shift type
											 */
											getCellColour();
										}, function(response) {

										});
					}

					/*
					 * This method is used to change the 'select' background
					 * color based on the changed value
					 */
					$scope.changedValue = function(selectedShift, colIndex,
							rowIndex) {
						console.log(selectedShift + ' : ' + colIndex + ' : '
								+ rowIndex);
						var $position = $(
								'#list-shift-plan-generator-table-row tr').eq(
								rowIndex).find('td').eq(colIndex + 1).find(
								'select');
						if (selectedShift == 'A') {
							$position.attr('class', 'form-control afternoon');
						} else if (selectedShift == 'M') {
							$position.attr('class', 'form-control morning');
						} else if (selectedShift == 'N') {
							$position.attr('class', 'form-control night');
						} else if (selectedShift == 'W') {
							$position.attr('class', 'form-control weeklyOff');
						}
					}

					$scope.saveEditedShiftPlan = function(editedShiftData) {
						checkUserLoginStatus();
						var shiftData = [];
						console.log(editedShiftData);
						$("#list-shift-plan-generator-table-row tr")
								.each(
										function(rowIndex) {
											var tempObj = {};
											var userDate = {};
											var $tds = $(this).find('td');
											// console.log($tds.length);
											var userName = editedShiftData.responseData[rowIndex].name;
											var userId = editedShiftData.responseData[rowIndex].userId;
											var shiftId = editedShiftData.responseData[rowIndex].shiftId;
											var month = editedShiftData.responseData[rowIndex].month;
											var year = editedShiftData.responseData[rowIndex].year;
											// console.log(userName +" :
											// "+userId+" : "+shiftId);
											for (var i = 0; i < $tds.length - 1;) {
												var dayOfTheWeek = editedShiftData.individualShiftData[rowIndex][++i].dayOfTheWeek;
												var date = editedShiftData.individualShiftData[rowIndex][i].date;
												var shiftType = $tds.eq(i)
														.find('select').val();
												tempObj[i] = {
													date : date,
													dayOfTheWeek : dayOfTheWeek,
													shift : shiftType,
													name : userName,
													userId : userId
												};
											}
											userDate = {
												shiftId : shiftId,
												name : userName,
												userId : userId,
												month : month,
												year : year,
												individualShiftModel : JSON
														.stringify(tempObj)
											};
											shiftData.push(userDate);
										});
						var stringifiedJSON = JSON.stringify(shiftData);
						$http({
							method : "PUT",
							url : "shifts",
							data : stringifiedJSON
						})
								.then(
										function(response) {
											console.log(response);
											$scope.saveShiftResponse = response.data;
											$scope.shiftResponseMessage = 'Shift Plan has been updated successfully for the month of '
													+ response.data[0].month
													+ '  '
													+ response.data[0].year
													+ ' for the following users';
											$('#list-shifts-response-modal')
													.modal('toggle');
										},
										function(response) {
											console.log("Error");
											$scope.shiftResponseMessage = 'Failed to generate the Shift Plan for the month of '
													+ response.data[0].month
													+ '  '
													+ response.data[0].year;
											$('#list-shifts-response-modal')
													.modal('toggle');
										});
					}

					$scope.generateExcelSheet = function(getDetails) {
						checkUserLoginStatus();
						var month = getDetails.month;
						var year = getDetails.year;

						$http(
								{
									url : "/shifts/generateExcel/" + month
											+ "/" + year,
									method : "GET"
								}).then(function(response) {
							console.log(response);
							// $().toastmessage('showSuccessToast', 'Excel File
							// Generated');
						}, function(response) {
							console.log(response);
							// $().toastmessage('showSuccessToast', 'Excel File
							// Generated');
						})
					}

					getMonthName = function(v) {
						var n = [ "January", "February", "March", "April",
								"May", "June", "July", "August", "September",
								"October", "November", "December" ];
						return n[v]
					}

					/*
					 * Add colour to the cells based on the Shift type
					 */
					function getCellColour() {
						setTimeout(function() {
							$("#list-shift-plan-generator-table-row tr").each(
									function(rowIndex) {
										var $tds = $(this).find('td');
										for (var i = 0; i < $tds.length - 1;) {
											var shiftType = $tds.eq(++i).find(
													'select').val();
											if (shiftType == 'W') {
												$tds.eq(i).find('select')
														.addClass('weeklyOff');
											} else if (shiftType == 'M') {
												$tds.eq(i).find('select')
														.addClass('morning');
											} else if (shiftType == 'N') {
												$tds.eq(i).find('select')
														.addClass('night');
											} else {
												$tds.eq(i).find('select')
														.addClass('afternoon');
											}
										}
									})
						}, 200);
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