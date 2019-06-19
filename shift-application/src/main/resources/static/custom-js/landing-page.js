var app = angular
		.module("shiftApp", [])
		.controller(
				'landing-page-controller',
				function($scope, $http) {
					var loggedInUserId = "";
					$http({
						method : "GET",
						url : "getLoggedInUser"
					})
							.then(
									function(response) {
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
										/*
										 * start of Last Month shift details
										 */

										$http(
												{
													method : "GET",
													url : "/shifts/month/"
															+ month
															+ "/"
															+ year
															+ "/"
															+ $scope.cognizantId
												})
												.then(
														function(response) {
															console
																	.log(response);
															if (response.data.length < 1) {
																$scope.ShiftDetailsResponseMessage = "No shifts to display"
																$scope.countMorningShift = $scope.countWeeklyOff = $scope.countNightShift = $scope.countAfternoonShift = 0;
															} else {
																var shiftDetails = JSON
																		.parse(response.data[0].individualShiftModel);
																var countMorningShift = countNightShift = countWeeklyOff = countAfternoonShift = 0;
																for ( var key in shiftDetails) {
																	if (shiftDetails
																			.hasOwnProperty(key)) {
																		if (shiftDetails[key].shift == "M") {
																			++countMorningShift;
																		} else if (shiftDetails[key].shift == "W") {
																			++countWeeklyOff;
																		} else if (shiftDetails[key].shift == "N") {
																			++countNightShift;
																		} else if (shiftDetails[key].shift == "A") {
																			++countAfternoonShift;
																		}
																	}
																}
																$scope.countMorningShift = countMorningShift;
																$scope.countWeeklyOff = countWeeklyOff;
																$scope.countNightShift = countNightShift;
																$scope.countAfternoonShift = countAfternoonShift;
															}
														},
														function(response) {
															console
																	.log("error :: landing-page.js"
																			+ response);
														})
										/*
										 * End of last month shift details
										 */

										/*
										 * Start of Last Logged In
										 */
										$http(
												{
													url : "lastLoggedIn/"
															+ $scope.cognizantId,
													method : "GET"
												})
												.then(
														function(response) {
															console
																	.log(response);
															$scope.lastLoggedIn = response.data.lastLoginTime;
														},
														function(response) {
															console
																	.log("error :: landing-page.js")
														})
									}, function(response) {
										console.log('Error');
									});

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

					/*
					 * Get the next thursday
					 */
					var tempDate = new Date();
					tempDate.setDate(tempDate.getDate()
							+ (4 + 7 - tempDate.getDay()) % 7);
					$scope.nextThursday = tempDate.getDate() + "-"
							+ getMonthName(tempDate.getMonth()) + "-"
							+ tempDate.getFullYear();

					/*
					 * start of getting WSR after a particular date
					 */
					$http(
							{
								url : "/wsrDetails/afterDate/"
										+ (tempDate.getMonth() + 1) + "-"
										+ tempDate.getDate() + "-"
										+ tempDate.getFullYear() + "/"
										+ $scope.cognizantId,
								method : "GET"
							}).then(function(response) {
						$scope.wsrResponse = response.data;
					}, function(response) {
						console.log(response);
					})
					/*
					 * end of getting WSR after a particular date
					 */

					/*
					 * weather application
					 */
					if (navigator.geolocation) {
						navigator.geolocation.getCurrentPosition(
								successFunction, errorFunction);
					}
					// Get the latitude and the longitude;
					function successFunction(position) {
						var cityName;
						var lat = position.coords.latitude;
						var lng = position.coords.longitude;
						var geocoder = new google.maps.Geocoder();
						var latlng = new google.maps.LatLng(lat, lng);
						geocoder
								.geocode(
										{
											'latLng' : latlng
										},
										function(results, status) {
											if (status == google.maps.GeocoderStatus.OK) {
												console.log(results)
												if (results[1]) {
													// formatted address
													// alert(results[0].formatted_address)
													// find country name
													for (var i = 0; i < results[0].address_components.length; i++) {
														for (var b = 0; b < results[0].address_components[i].types.length; b++) {

															// there are
															// different types
															// that might hold a
															// city
															// admin_area_lvl_1
															// usually does in
															// come cases
															// looking for
															// sublocality type
															// will be more
															// appropriate
															if (results[0].address_components[i].types[b] == "locality"
																	|| results[0].address_components[i].types[b] == "administrative_area_level_2") {
																// this is the
																// object you
																// are looking
																// for
																city = results[0].address_components[i];
																cityName = city.long_name;
																break;
															}
														}
													}
													// city data
													// alert(city.short_name + "
													// " + city.long_name)
													console.log(cityName);
													$http(
															{
																url : "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text='"
																		+ cityName
																		+ "')&format=json",
																method : "GET"
															})
															.then(
																	function(
																			response) {
																		console
																				.log("weather response");
																		console
																				.log(response);
																		$scope.weatherData = response.data.query.results.channel;
																	},
																	function(
																			response) {
																		console
																				.log('error weather app :: '
																						+ response)
																	});

												} else {
													alert("No results found");
												}
											} else {
												alert("Geocoder failed due to: "
														+ status);
											}
										});
					}
					function errorFunction() {
						alert("Geocoder failed");
					}
				});
getMonthName = function(v) {
	var n = [ "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" ];
	return n[v];
}