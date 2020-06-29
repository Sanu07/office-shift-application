var app = angular
    .module("shiftApp", ["googlechart"])
    .controller(
        'landing-page-controller',
        function($scope, $http, $q, $timeout) {
            var loggedInUserId = "";
            var promises = [];
            $scope.loading = true;
            var qLoginReq = $http({
                    method: "GET",
                    url: "getLoggedInUser"
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

                        var qShiftsReq = $http({
                                method: "GET",
                                url: "/shifts/month/" +
                                    month +
                                    "/" +
                                    year +
                                    "/" +
                                    $scope.cognizantId
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
                                        for (var key in shiftDetails) {
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
                                    promises.push(qShiftsReq);
                                },
                                function(response) {
                                    console
                                        .log("error :: landing-page.js" +
                                            response);
                                })
                        /*
                         * End of last month shift details
                         */

                        /*
                         * Start of Last Logged In
                         */
                        var qLastLogin = $http({
                                url: "lastLoggedIn/" +
                                    $scope.cognizantId,
                                method: "GET"
                            })
                            .then(
                                function(response) {
                                    // console.log(response);
                                    $scope.lastLoggedIn = response.data.lastLoginTime;
                                    promises.push(qLastLogin);
                                },
                                function(response) {
                                    console.log("error :: landing-page.js")
                                })
                                promises.push(qLoginReq);
                    },
                    function(response) {
                        console.log('Error');
                    });
            
            var date = new Date();
            var month = getMonthName(date.getMonth());
            var year = date.getFullYear();
            var today = date.getDate();
            var qShiftReq = $http({
                    url: "/shifts/month/" + month + "/" + year
                })
                .then(
                    function(response) {
                        // console.log(response);
                        var morningCount = weeklyOffCount = nightCount = afternoonCount = totalCount = 0;
                        $scope.individualMemberShifts = [];
                        for (var i = 0; i < response.data.length; i++) {
                        	var individualMorning = individualAfternoon = individualWeeklyOff = individualNight = 0;
                            var shift = JSON.parse(response.data[i].individualShiftModel)[today].shift;
                            var individualShifts = JSON.parse(response.data[i].individualShiftModel);
                            if (shift === 'W') {
                                ++weeklyOffCount;
                            } else if (shift === 'M') {
                                ++morningCount;
                            } else if (shift === 'N') {
                                ++nightCount;
                            } else if (shift === 'A') {
                                ++afternoonCount;
                            }
                            angular.forEach(individualShifts, function(value, key) {
                            	switch (value.shift) {
                                case 'M':
                                	individualMorning += 1;
                                    break;
                                case 'A':
                                	individualAfternoon += 1;
                                    break;
                                case 'N':
                                	individualNight += 1;
                                	break;
                                case 'W':
                                	individualWeeklyOff += 1;
                                    break;
                                default:
                            	}
                            });
                            $scope.individualMemberShifts.push({
                        			name: response.data[i].name,
                            		m: individualMorning,
                            		a: individualAfternoon,
                            		n: individualNight,
                            		w: individualWeeklyOff
                            });
                        }
                        $scope.myChartObject = {};
                        $scope.rows = [];
                        $scope.myChartObject.type = "ColumnChart";
                        for(var i = 0; i < $scope.individualMemberShifts.length; i++) {
                        	$scope.rows.push({c: [
                                {v: $scope.individualMemberShifts[i].name},
                                {v: $scope.individualMemberShifts[i].m},
                                {v: $scope.individualMemberShifts[i].a},
                                {v: $scope.individualMemberShifts[i].n},
                                {v: $scope.individualMemberShifts[i].w}
                            ]})
                        }
                        
                        $scope.myChartObject.data = {"cols": [
                            {id: "t", label: "Users", type: "string"},
                            {id: "m", label: "Morning", type: "number"},
                            {id: "a", label: "Afternoon", type: "number"},
                            {id: "n", label: "Night", type: "number"},
                            {id: "w", label: "Week-Off", type: "number"}
                        ], "rows": $scope.rows };
                        $scope.myChartObject.options = {
                                'title': 'Shifts',
                                'height': '100%',
                                'width': '100%',
                                'isStacked': true,
                                'legend': { position: 'top' },
                                orientation: 'vertical',
                                colors: ["#00b33c", "#e6b800", "#66b3ff", "#ff3300"],
                                hAxis: {
                                	viewWindow: {
                                        max: 31,
                                        min: 0,
                                      },
                                      gridlines: {
                                        count: 10,
                                      }
                                }
                            };
                        $scope.shiftDetailsMonth = month;
                        $scope.shiftDetailsYear = year;
                        $scope.mCount = morningCount;
                        $scope.nCount = nightCount;
                        $scope.aCount = afternoonCount;
                        $scope.wCount = weeklyOffCount;
                        $scope.totalCount = response.data.length;
                        promises.push(qShiftReq);
                    },
                    function(response) {
                        console.log(response);
                    })
            /*
             * get the list of incidents after a particular date
             */
            var qIncidentsReq = $http({
                url: "/incidents/afterDate/" +
                    (date.getMonth()) + "-" + today + "-" +
                    year,
                method: "GET"
            }).then(function(response) {
                $scope.incidentsResponse = response.data;
                promises.push(qIncidentsReq);
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
            var qJirasReq = $http({
                url: "/jiras/afterDate/" +
                    date.getMonth() + "-" + today +
                    "-" + year,
                method: "GET"
            }).then(function(response) {
                $scope.jirasResponse = response.data;
                promises.push(qJirasReq);
            }, function(response) {
                console.log(response);
            });
            /*
             * end of getting the list of jiras after a particular date
             */
            var qJiraIncPast12MonthsReq = $http({
            	url: "/jiras/" +
                (date.getMonth() + 1) + "-" + today +
                "-" + year + "/past12",
            method: "GET"
            }).then(function(response) {
                $scope.pastJirasResponse = response.data[0];
                $scope.pastJirasStatus = response.data[1];
                $http({
                	url: "/incidents/" +
                    (date.getMonth()) + "-" + today + "-" +
                    year + "/past12",
                method: "GET"
                }).then(function(response) {
                    $scope.pastIncidentsResponse = response.data[0];
                    $scope.pastIncidentsStatus = response.data[1];
                    $scope.incJiraPrevMonthsChart = {};
                    $scope.incJiraRows = [];
                    for(var key in $scope.pastIncidentsResponse) {
                    	$scope.incJiraRows.push({c: [
                            {v: key},
                            {v: $scope.pastIncidentsResponse[key]},
                            {v: $scope.pastJirasResponse[key]}
                        ]})
                    }
                    $scope.incJiraPrevMonthsChart.type = "AreaChart";
                    $scope.incJiraPrevMonthsChart.data = {"cols": [
                        {id: "t", label: "Month", type: "string"},
                        {id: "i", label: "Incidents", type: "number"},
                        {id: "j", label: "Jiras", type: "number"},
                    ], "rows": $scope.incJiraRows };
                    $scope.incJiraPrevMonthsChart.options = {
                            'title': 'Incidents and Jiras created since last 12 months',
                            'height': '400',
                            'width': '100%',
                            hAxis: {
                                slantedText: true,
                                slantedTextAngle: 90,
                                minorGridlines: { count: 5 }
                            },
                            vAxis: { 
                            	format: '#',
                                 viewWindow: {
                                	 min: 0
                                 }
                               }
                    }
                    
                    $scope.jiraIncStatusChart = {};
                    $scope.jiraIncStatusChart.type = "BarChart";
                    $scope.jiraIncStatusChart.data = {"cols": [
                        {id: "t", label: "Tasks", type: "string"},
                        {id: "i", label: "Open", type: "number"},
                        {id: "j", label: "Close", type: "number"},
                    ], "rows": [
                    	{
                    		c: [{v: "Jiras"},
                    			{v: $scope.pastJirasStatus["Open"]},
                    			{v: $scope.pastJirasStatus["Closed"]}]
                    	},
                    	{
                    		c: [{v: "Incidents"},
                    			{v: $scope.pastIncidentsStatus["Open"]},
                    			{v: $scope.pastIncidentsStatus["Closed"]}]
                    	}
                    ] };
                    $scope.jiraIncStatusChart.options = {
                            'title': '12 Months Status',
                            'height': '100%',
                            'width': '100%',
                            orientation: 'horizontal',
                            legend: { position: 'top' },
                            vAxis: { 
                            	format: '#',
                                 viewWindow:{
                                	 min: 0
                                 }
                            }
                    };
                    
                }, function(response) {
                    console.log(response);
                });
                promises.push(qJiraIncPast12MonthsReq);
            }, function(response) {
                console.log(response);
            });
            /*
             * start of getting leaves after a particular date
             */
            var qLeavesReq = $http({
                url: "/leaves/afterDate/" +
                    (date.getMonth() + 1) + "-" + today +
                    "-" + year,
                method: "GET"
            }).then(function(response) {
                $scope.leavesResponse = response.data;
                promises.push(qLeavesReq);
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
            tempDate.setDate(tempDate.getDate() +
                (4 + 7 - tempDate.getDay()) % 7);
            $scope.nextThursday = tempDate.getDate() + "-" +
                getMonthName(tempDate.getMonth()) + "-" +
                tempDate.getFullYear();

            /*
             * start of getting WSR after a particular date
             */
            var qWSRReq = $http({
                url: "/wsrDetails/afterDate/" +
                    (tempDate.getMonth() + 1) + "-" +
                    tempDate.getDate() + "-" +
                    tempDate.getFullYear() + "/" +
                    $scope.cognizantId,
                method: "GET"
            }).then(function(response) {
                $scope.wsrResponse = response.data;
                promises.push(qWSRReq);
            }, function(response) {
                console.log(response);
            });
            $q.all(promises).then(function () {
            	$timeout(function() {
            		$scope.loading = false;
            	}, 4000);
            });
        });
getMonthName = function(v) {
    var n = ["January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"
    ];
    return n[v];
}