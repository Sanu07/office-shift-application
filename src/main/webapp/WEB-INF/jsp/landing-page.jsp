<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>Application | Shift Plan App</title>

<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!-- full calendar css-->
<link href="assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css"
	rel="stylesheet" />
<link href="assets/fullcalendar/fullcalendar/fullcalendar.css"
	rel="stylesheet" />
<!-- easy pie chart-->
<link href="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<!-- owl carousel -->
<link rel="stylesheet" href="css/owl.carousel.css" type="text/css">
<link href="css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
<!-- Custom styles -->
<link rel="stylesheet" href="css/fullcalendar.css">
<link href="css/widgets.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<link href="css/xcharts.min.css" rel=" stylesheet">
<link href="css/jquery-ui-1.10.4.min.css" rel="stylesheet">
<!-- =======================================================
        Theme Name: NiceAdmin
        Theme URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
        Author: BootstrapMade
        Author URL: https://bootstrapmade.com
    ======================================================= -->
<style type="text/css">
#coming-soon-div {
	background: rgba(200, 54, 54, 0.5);
}
</style>
<script type="text/javascript">
function preventBack() {
 window.history.forward();
}
setTimeout("preventBack()", 0);
window.onunload = function() {
 null
};
</script>
</head>

<body ng-app="shiftApp" ng-controller="landing-page-controller">
	<!-- container section start -->
	<section id="container" class="">


		<header class="header dark-bg">
			<div class="toggle-nav">
				<div class="icon-reorder tooltips"
					data-original-title="Toggle Navigation" data-placement="bottom">
					<i class="icon_menu"></i>
				</div>
			</div>

			<!--logo start-->
			<a href="index.html" class="logo">Shift <span class="lite">Application</span></a>
			<!--logo end-->

			<div class="top-nav notification-row">
				<!-- notificatoin dropdown start-->
				<ul class="nav pull-right top-menu">

					<!-- task notificatoin start -->
					<li id="task_notificatoin_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
							class="icon-task-l"></i> <span class="badge bg-important">shift
								{{totalCount}}</span>
					</a>
						<ul class="dropdown-menu extended tasks-bar">
							<div class="notify-arrow notify-arrow-blue"></div>
							<li>
								<p class="blue">Today's Shift Coverage</p>
							</li>
							<li><a href="#">
									<div class="task-info">
										<div class="desc">Morning</div>
										<div class="percent">{{mCount}}
											[{{(mCount*100)/totalCount}}]%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="90" aria-valuemin="0"
											aria-valuemax="100" style="width: {{(mCount*100)/totalCount">
											<span class="sr-only">90% Complete (success)</span>
										</div>
									</div>
							</a></li>
							<li><a href="#">
									<div class="task-info">
										<div class="desc">Night</div>
										<div class="percent">{{nCount}}
											[{{(nCount*100)/totalCount}}]%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-info" role="progressbar"
											aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"
											style="width: {{(nCount*100)/totalCount">
											<span class="sr-only">30% Complete (warning)</span>
										</div>
									</div>
							</a></li>
							<li><a href="#">
									<div class="task-info">
										<div class="desc">Afternoon</div>
										<div class="percent">{{aCount}}
											[{{(aCount*100)/totalCount}}]%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-warning"
											role="progressbar" aria-valuenow="80" aria-valuemin="0"
											aria-valuemax="100" style="width: {{(aCount*100)/totalCount">
											<span class="sr-only">80% Complete</span>
										</div>
									</div>
							</a></li>
							<li><a href="#">
									<div class="task-info">
										<div class="desc">Weekly Off</div>
										<div class="percent">{{wCount}}
											[{{(wCount*100)/totalCount}}]%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="78" aria-valuemin="0"
											aria-valuemax="100" style="width: {{(wCount*100)/totalCount">
											<span class="sr-only">78% Complete (danger)</span>
										</div>
									</div>
							</a></li>
							<li class="external"><a href="/list-all-shifts">See All
									Shifts</a></li>
						</ul></li>
					<!-- task notificatoin end -->
					<!-- inbox notificatoin start-->
					<li id="mail_notificatoin_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
							class="icon-envelope-l"></i> <span class="badge bg-important">{{incidentsResponse.length+jirasResponse.length+leavesResponse.length}}</span>
					</a>
						<ul class="dropdown-menu extended inbox">
							<div class="notify-arrow notify-arrow-blue"></div>
							<li>
								<p class="blue">You have
									{{incidentsResponse.length+jirasResponse.length+leavesResponse.length}}
									new messages</p>
							</li>
							<li><a href="/list-all-jiras"> <span class="photo"><img
										alt="avatar" src="./img/avatar-mini.jpg"></span> <span
									class="subject"> <span class="from">Jiras</span> <span
										class="time">{{jirasResponse.length == 0 ? 'N/A' :
											jirasResponse.length}}</span>
								</span> <span class="message"> I really like this admin panel. </span>
							</a></li>
							<li><a href="/list-all-incidents"> <span class="photo"><img
										alt="avatar" src="./img/avatar-mini2.jpg"></span> <span
									class="subject"> <span class="from">Incidents</span> <span
										class="time">{{incidentsResponse.length == 0 ? 'N/A' :
											incidentsResponse.length}}</span>
								</span> <span class="message"> Hi, What is next project plan? </span>
							</a></li>
							<li><a href="/list-all-leaves"> <span class="photo"><img
										alt="avatar" src="./img/avatar-mini3.jpg"></span> <span
									class="subject"> <span class="from">Leaves</span> <span
										class="time">{{leavesResponse.length == 0 ? 'N/A' :
											leavesResponse.length}}</span>
								</span> <span class="message"> I am like to buy this Admin
										Template. </span>
							</a></li>
							<li><a href="/create-wsr"> <span class="photo"><img
										alt="avatar" src="./img/avatar-mini4.jpg"></span> <span
									class="subject"> <span class="from">WSR</span> <span
										class="time">{{wsrResponse.length == 0 ? 'Not updated'
											: 'Updated'}}</span>
								</span> <span class="message"> Icon fonts are great. </span>
							</a></li>
							<li><a href="#">See all messages</a></li>
						</ul></li>
					<!-- inbox notificatoin end -->
					<!-- alert notification start-->
					<li id="alert_notificatoin_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
							class="icon-bell-l"></i> <span class="badge bg-important">7</span>
					</a>
						<ul class="dropdown-menu extended notification">
							<div class="notify-arrow notify-arrow-blue"></div>
							<li>
								<p class="blue">You have 4 new notifications</p>
							</li>
							<li><a href="#"> <span class="label label-primary"><i
										class="icon_profile"></i></span> Friend Request <span
									class="small italic pull-right">5 mins</span>
							</a></li>
							<li><a href="#"> <span class="label label-warning"><i
										class="icon_pin"></i></span> John location. <span
									class="small italic pull-right">50 mins</span>
							</a></li>
							<li><a href="#"> <span class="label label-danger"><i
										class="icon_book_alt"></i></span> Project 3 Completed. <span
									class="small italic pull-right">1 hr</span>
							</a></li>
							<li><a href="#"> <span class="label label-success"><i
										class="icon_like"></i></span> Mick appreciated your work. <span
									class="small italic pull-right"> Today</span>
							</a></li>
							<li><a href="#">See all notifications</a></li>
						</ul></li>
					<!-- alert notification end-->
					<!-- user login dropdown start-->
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <span class="profile-ava">
								<img alt="" src="{{imageURL}}" style="width: 40px; height: 40px">
						</span> <span class="username">{{name}}</span> <b class="caret"></b>
					</a>
						<ul class="dropdown-menu extended logout">
							<div class="log-arrow-up"></div>
							<li class="eborder-top"><a href="/profile"><i
									class="icon_profile"></i> My Profile</a></li>
							<li><a href="#"><i class="icon_mail_alt"></i> My Inbox</a></li>
							<li><a href="#"><i class="icon_clock_alt"></i> Timeline</a>
							</li>
							<li><a href="#"><i class="icon_chat_alt"></i> Chats</a></li>
							<li><a href="/logout"><i class="icon_key_alt"></i> Log
									Out</a></li>
							<li><a href="documentation.html"><i class="icon_key_alt"></i>
									Documentation</a></li>
							<li><a href="documentation.html"><i class="icon_key_alt"></i>
									Documentation</a></li>
						</ul></li>
					<!-- user login dropdown end -->
				</ul>
				<!-- notificatoin dropdown end-->
			</div>
		</header>
		<!--header end-->

		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu">
					<li class=""><a class="" href="/landing-page"> <i
							class="icon_house_alt"></i> <span>Dashboard</span>
					</a></li>
					<li class=""><a class="" href="/profile"> <i
							class="icon_document_alt"></i> <span>Profile</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_document_alt"></i> <span>Jiras</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">
							<li><a class="" href="/list-all-jiras">List All Jiras</a></li>
							<li><a class="" href="/create-jiras">Create Jiras</a></li>
						</ul></li>
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_desktop"></i> <span>Incidents</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">
							<li><a class="" href="/list-all-incidents">List All
									Incidents</a></li>
							<li><a class="" href="/create-incidents">Create New
									Incident</a></li>
						</ul></li>
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_document_alt"></i> <span>Leaves</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">
							<li><a class="" href="/list-all-leaves">Leave Approval</a></li>
							<li><a class="" href="/create-leaves">Apply Leave</a></li>
							<li><a class="" href="/list-my-leaves">My Leaves</a></li>
						</ul></li>
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_document_alt"></i> <span>Shifts</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">
							<li><a class="" href="/list-all-shifts">Show Monthly
									Shifts</a></li>
							<li><a class="" href="/shift-plan-generator">Generate
									Shifts</a></li>
						</ul></li>
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_document_alt"></i> <span>Status Report</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">
							<li><a class="" href="/create-wsr">Update my WSR</a></li>
							<li><a class="" href="/list-wsr">List WSR</a></li>
						</ul></li>
				</ul>
				<!-- End -->
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->

		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<!--overview start-->
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa-laptop"></i> Dashboard
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
							<li><i class="fa fa-laptop"></i>Dashboard</li>
						</ol>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<div class="info-box blue-bg">
							<i class="fa fa-cloud-download"></i>
							<div class="title">Shift for {{shiftDetailsMonth}}
								{{shiftDetailsYear}}</div>
							<div class="">Morning | {{countMorningShift == 0 ? 'N/A' :
								countMorningShift}}</div>
							<div class="">Afternoon | {{countAfternoonShift == 0 ?
								'N/A' : countAfternoonShift}}</div>
							<div class="">Night | {{countNightShift == 0 ? 'N/A' :
								countNightShift}}</div>
							<div class="">WeeklyOff | {{countWeeklyOff == 0 ? 'N/A' :
								countWeeklyOff}}</div>
						</div>
						<!--/.info-box-->
					</div>
					<!--/.col-->

					<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<div class="info-box brown-bg">
							<i class="fa fa-shopping-cart"></i>
							<div class="title">Last month updates</div>
							<div>{{jirasResponse.length > 0 ? jirasResponse.length :
								"Recently no" }} Jiras are updated</div>
							<div>{{incidentsResponse.length > 0 ?
								incidentsResponse.length : "Recently no" }} Incidents are
								updated</div>
							<div>{{leavesResponse.length > 0 ? leavesResponse.length :
								"Recently no" }} Leaves are updated</div>
						</div>
						<!--/.info-box-->
					</div>
					<!--/.col-->

					<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<div class="info-box dark-bg">
							<i class="fa fa-thumbs-o-up"></i>
							<div class="title">WSR for {{nextThursday}}</div>
							<div class="">{{wsrResponse.length > 0 ? 'has been updated'
								: 'has not been updated' }}</div>
						</div>
						<!--/.info-box-->
					</div>
					<!--/.col-->

					<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<div class="info-box green-bg">
							<i class="fa fa-cubes"></i>
							<div class="title">Last Login</div>
							<div class="">{{lastLoginTime == null ? 'This is your first
								time login' : lastLoginTime}}</div>
						</div>
						<!--/.info-box-->
					</div>
					<!--/.col-->

				</div>
				<!--/.row-->


				<div class="row">
					<div class="col-lg-9 col-md-12">

						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<i class="fa fa-map-marker red"></i><strong>Countries</strong>
								</h2>
								<div class="panel-actions">
									<a href="index.html#" class="btn-setting"><i
										class="fa fa-rotate-right"></i></a> <a href="index.html#"
										class="btn-minimize"><i class="fa fa-chevron-up"></i></a> <a
										href="index.html#" class="btn-close"><i
										class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body-map">
								<div id="map" style="height: 380px; width: 100%"></div>
							</div>

						</div>
					</div>
					<div class="col-md-3">
						<!-- List starts -->
						<ul class="today-datas">
							<!-- List #1 -->
							<li>
								<!-- Graph -->
								<div>
									<span id="todayspark1" class="spark"></span>
								</div> <!-- Text -->
								<div class="datas-text">11,500 visitors/day</div>
							</li>
							<li>
								<div>
									<span id="todayspark2" class="spark"></span>
								</div>
								<div class="datas-text">15,000 Pageviews</div>
							</li>
							<li>
								<div>
									<span id="todayspark3" class="spark"></span>
								</div>
								<div class="datas-text">30.55% Bounce Rate</div>
							</li>
							<li>
								<div>
									<span id="todayspark4" class="spark"></span>
								</div>
								<div class="datas-text">$16,00 Revenue/Day</div>
							</li>
							<li>
								<div>
									<span id="todayspark5" class="spark"></span>
								</div>
								<div class="datas-text">12,000000 visitors every Month</div>
							</li>
						</ul>
					</div>


				</div>


				<!-- Today status end -->



				<div class="row">

					<div class="col-lg-9 col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<i class="fa fa-flag-o red"></i><strong>Weather
										Forecast</strong>
								</h2>
								<div class="panel-actions">
									<a href="" class="btn-setting"><i
										class="fa fa-rotate-right"></i></a> <a href=""
										class="btn-minimize"><i class="fa fa-chevron-up"></i></a> <a
										href="" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<div class="jumbotron">
									<h2>{{weatherData.location.city}}{{weatherData.location.region}},{{weatherData.location.country}}</h2>
									<p>Data as of {{weatherData.item.condition.date}}</p>
									<table class='table borderless'>
										<tbody>
											<tr>
												<td><p>
														sunrise - <i class="fa fa-sun-o" aria-hidden="true"></i>
														{{weatherData.astronomy.sunrise}}
													</p>
													<p>
														sunset - <i class="fa fa-moon-o" aria-hidden="true"></i>
														{{weatherData.astronomy.sunset}}
													</p></td>
												<td><p>
														Temperature - <i class="fa fa-thermometer-half"
															aria-hidden="true"></i>
														{{weatherData.item.condition.temp}}&#8457;
													</p>
													<p>Highest -
														{{weatherData.item.forecast[0].high}}&#8457;</p>
													<p>Lowest - {{weatherData.item.forecast[0].low}}&#8457;</p></td>
												<td>
													<p>Wind speed - {{weatherData.wind.speed}} mph</p>
													<p>Humidity - {{weatherData.atmosphere.humidity}}</p>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Day</th>
											<th>Date</th>
											<th>Highest temperature(approx)</th>
											<th>Lowest temperature(approx)</th>
										</tr>
									</thead>
									<tbody
										ng-repeat="temp in weatherData.item.forecast.slice(1,weatherData.item.forecast.length)">
										<tr>
											<th scope="row">{{temp.day}}</th>
											<td>{{temp.date}}</td>
											<td>{{temp.high}}&#8457;</td>
											<td>{{temp.low}}&#8457;</td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>

					</div>
					<!--/col-->
					<div class="col-md-3">

						<div class="social-box facebook">
							<i class="fa fa-facebook"></i>
							<ul>
								<li><strong>256k</strong> <span>friends</span></li>
								<li><strong>359</strong> <span>feeds</span></li>
							</ul>
						</div>
						<!--/social-box-->
					</div>
					<div class="col-md-3">

						<div class="social-box google-plus">
							<i class="fa fa-google-plus"></i>
							<ul>
								<li><strong>962</strong> <span>followers</span></li>
								<li><strong>256</strong> <span>circles</span></li>
							</ul>
						</div>
						<!--/social-box-->

					</div>
					<!--/col-->
					<div class="col-md-3">

						<div class="social-box twitter">
							<i class="fa fa-twitter"></i>
							<ul>
								<li><strong>1562k</strong> <span>followers</span></li>
								<li><strong>2562</strong> <span>tweets</span></li>
							</ul>
						</div>
						<!--/social-box-->

					</div>
					<!--/col-->

				</div>



				<!-- statics end -->




				<!-- project team & activity start -->
				<!-- <div class="row">
            <div class="col-md-4 portlets">
              Widget
              <div class="panel panel-default">
   <div class="panel-heading">
                  <div class="pull-left">Message</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                <div class="panel-body">
                  Widget content
                  <div class="padd sscroll">
                    
                    <ul class="chats">

                      Chat by us. Use the class "by-me".
                      <li class="by-me">
                        Use the class "pull-left" in avatar
                        <div class="avatar pull-left">
                          <img src="img/user.jpg" alt=""/>
                        </div>

                        <div class="chat-content">
                          In meta area, first include "name" and then "time"
                          <div class="chat-meta">John Smith <span class="pull-right">3 hours ago</span></div>
                          Vivamus diam elit diam, consectetur dapibus adipiscing elit.
                          <div class="clearfix"></div>
                        </div>
                      </li> 

                      Chat by other. Use the class "by-other".
                      <li class="by-other">
                        Use the class "pull-right" in avatar
                        <div class="avatar pull-right">
                          <img src="img/user22.png" alt=""/>
                        </div>

                        <div class="chat-content">
                          In the chat meta, first include "time" then "name"
                          <div class="chat-meta">3 hours ago <span class="pull-right">Jenifer Smith</span></div>
                          Vivamus diam elit diam, consectetur fconsectetur dapibus adipiscing elit.
                          <div class="clearfix"></div>
                        </div>
                      </li>   

                      <li class="by-me">
                        <div class="avatar pull-left">
                          <img src="img/user.jpg" alt=""/>
                        </div>

                        <div class="chat-content">
                          <div class="chat-meta">John Smith <span class="pull-right">4 hours ago</span></div>
                          Vivamus diam elit diam, consectetur fermentum sed dapibus eget, Vivamus consectetur dapibus adipiscing elit.
                          <div class="clearfix"></div>
                        </div>
                      </li>  

                      <li class="by-other">
                        Use the class "pull-right" in avatar
                        <div class="avatar pull-right">
                          <img src="img/user22.png" alt=""/>
                        </div>

                        <div class="chat-content">
                          In the chat meta, first include "time" then "name"
                          <div class="chat-meta">3 hours ago <span class="pull-right">Jenifer Smith</span></div>
                          Vivamus diam elit diam, consectetur fermentum sed dapibus eget, Vivamus consectetur dapibus adipiscing elit.
                          <div class="clearfix"></div>
                        </div>
                      </li>                                                                                  

                    </ul>

                  </div>
                  Widget footer
                  <div class="widget-foot">
                      
                      <form class="form-inline">
     <div class="form-group">
      <input type="text" class="form-control" placeholder="Type your message here...">
     </div>
                        <button type="submit" class="btn btn-info">Send</button>
                      </form>


                  </div>
                </div>


              </div> 
            </div> -->

				<!-- <div class="col-lg-8">
                      Project Activity start
                      <section class="panel">
                          <div class="panel-body progress-panel">
                            <div class="row">
                              <div class="col-lg-8 task-progress pull-left">
                                  <h1>To Do Everyday</h1>                                  
                              </div>
                              <div class="col-lg-4">
                                <span class="profile-ava pull-right">
                                        <img alt="" class="simple" src="img/avatar1_small.jpg">
                                        *****
                                </span>                                
                              </div>
                            </div>
                          </div>
                          <table class="table table-hover personal-task">
                              <tbody>
                              <tr>
                                  <td>Today</td>
                                  <td>
                                      web design
                                  </td>
                                  <td>
                                      <span class="badge bg-important">Upload</span>
                                  </td>
                                  <td>
                                    <span class="profile-ava">
                                        <img alt="" class="simple" src="img/avatar1_small.jpg">
                                    </span>
                                  </td>
                              </tr>
                              <tr>
                                  <td>Yesterday</td>
                                  <td>
                                      Project Design Task
                                  </td>
                                  <td>
                                      <span class="badge bg-success">Task</span>
                                  </td>
                                  <td>
                                      <div id="work-progress2"></div>
                                  </td>
                              </tr>
                              <tr>
                                  <td>21-10-14</td>
                                  <td>
                                      Generate Invoice
                                  </td>
                                  <td>
                                      <span class="badge bg-success">Task</span>
                                  </td>
                                  <td>
                                      <div id="work-progress3"></div>
                                  </td>
                              </tr>                              
                              <tr>
                                  <td>22-10-14</td>
                                  <td>
                                      Project Testing
                                  </td>
                                  <td>
                                      <span class="badge bg-primary">To-Do</span>
                                  </td>
                                  <td>
                                      <span class="profile-ava">
                                        <img alt="" class="simple" src="img/avatar1_small.jpg">
                                      </span>
                                  </td>
                              </tr>
                              <tr>
                                  <td>24-10-14</td>
                                  <td>
                                      Project Release Date
                                  </td>
                                  <td>
                                      <span class="badge bg-info">Milestone</span>
                                  </td>
                                  <td>
                                      <div id="work-progress4"></div>
                                  </td>
                              </tr>                              
                              <tr>
                                  <td>28-10-14</td>
                                  <td>
                                      Project Release Date
                                  </td>
                                  <td>
                                      <span class="badge bg-primary">To-Do</span>
                                  </td>
                                  <td>
                                      <div id="work-progress5"></div>
                                  </td>
                              </tr>
        <tr>
                                  <td>Last week</td>
                                  <td>
                                      Project Release Date
                                  </td>
                                  <td>
                                      <span class="badge bg-primary">To-Do</span>
                                  </td>
                                  <td>
                                      <div id="work-progress1"></div>
                                  </td>
                              </tr>
        <tr>
                                  <td>last month</td>
                                  <td>
                                      Project Release Date
                                  </td>
                                  <td>
                                      <span class="badge bg-success">To-Do</span>
                                  </td>
                                  <td>
                                      <span class="profile-ava">
                                        <img alt="" class="simple" src="img/avatar1_small.jpg">
                                      </span>
                                  </td>
                              </tr>
                              </tbody>
                          </table>
                      </section>
                      Project Activity end
                  </div>
              </div><br><br> -->

				<div class="row">
					<div class="col-md-6 portlets">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<strong>Calendar</strong>
								</h2>
								<div class="panel-actions">
									<a href="" class="wminimize"><i class="fa fa-chevron-up"></i></a>
									<a href="" class="wclose"><i class="fa fa-times"></i></a>
								</div>

							</div>
							<br>
							<br>
							<br>
							<div class="panel-body">
								<!-- Widget content -->

								<!-- Below line produces calendar. I am using FullCalendar plugin. -->
								<div id="calendar"></div>

							</div>
						</div>

					</div>

					<div class="col-md-6 portlets" id="coming-sonn-div">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="pull-left">Quick Post</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="fa fa-times"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="panel-body">
								<div class="padd">

									<div class="form quick-post">
										<!-- Edit profile form (not working)-->
										<form class="form-horizontal">
											<!-- Title -->
											<div class="form-group">
												<label class="control-label col-lg-2" for="title">Title</label>
												<div class="col-lg-10">
													<input type="text" class="form-control" id="title"
														disabled="disabled">
												</div>
											</div>
											<!-- Content -->
											<div class="form-group">
												<label class="control-label col-lg-2" for="content">Content</label>
												<div class="col-lg-10">
													<textarea class="form-control" id="content"
														disabled="disabled"></textarea>
												</div>
											</div>
											<!-- Cateogry -->
											<div class="form-group">
												<label class="control-label col-lg-2">Category</label>
												<div class="col-lg-10">
													<select class="form-control" disabled="disabled">
														<option value="">- Choose Cateogry -</option>
														<option value="1">General</option>
														<option value="2">News</option>
														<option value="3">Media</option>
														<option value="4">Funny</option>
													</select>
												</div>
											</div>
											<!-- Tags -->
											<div class="form-group">
												<label class="control-label col-lg-2" for="tags">Tags</label>
												<div class="col-lg-10">
													<input type="text" class="form-control" id="tags"
														disabled="disabled">
												</div>
											</div>

											<!-- Buttons -->
											<div class="form-group">
												<!-- Buttons -->
												<div class="col-lg-offset-2 col-lg-9">
													<button type="submit" class="btn btn-primary"
														disabled="disabled">Publish</button>
													<button type="submit" class="btn btn-danger"
														disabled="disabled">Save Draft</button>
													<button type="reset" class="btn btn-default"
														disabled="disabled">Reset</button>
												</div>
											</div>
										</form>
									</div>
								</div>
								<div class="widget-foot">
									<!-- Footer goes here -->
								</div>
							</div>
						</div>

					</div>

				</div>
				<!-- project team & activity end -->

			</section>
			<div class="text-right">
				<div class="credits">
					<!-- 
                    All the links in the footer should remain intact. 
                    You can delete the links only if you purchased the pro version.
                    Licensing information: https://bootstrapmade.com/license/
                    Purchase the pro version form: https://bootstrapmade.com/buy/?theme=NiceAdmin
                -->
					<a
						href="https://bootstrapmade.com/free-business-bootstrap-themes-website-templates/">Business
						Bootstrap Themes</a> by <a href="https://bootstrapmade.com/">BootstrapMade</a>
				</div>
			</div>
		</section>
		<!--main content end-->
	</section>
	<!-- container section start -->

	<!-- javascripts -->
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.10.4.min.js"></script>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
	<!-- bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- charts scripts -->
	<script src="assets/jquery-knob/js/jquery.knob.js"></script>
	<script src="js/jquery.sparkline.js" type="text/javascript"></script>
	<script src="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
	<script src="js/owl.carousel.js"></script>
	<!-- jQuery full calendar -->
	<
	<script src="js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<script src="assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
	<!--script for this page only-->
	<script src="js/calendar-custom.js"></script>
	<script src="js/jquery.rateit.min.js"></script>
	<!-- custom select -->
	<script src="js/jquery.customSelect.min.js"></script>
	<script src="assets/chart-master/Chart.js"></script>

	<!--custome script for all page-->
	<script src="js/scripts.js"></script>
	<!-- custom script for this page-->
	<script src="js/sparkline-chart.js"></script>
	<script src="js/easy-pie-chart.js"></script>
	<script src="js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="js/xcharts.min.js"></script>
	<script src="js/jquery.autosize.min.js"></script>
	<script src="js/jquery.placeholder.min.js"></script>
	<script src="js/gdp-data.js"></script>
	<script src="js/morris.min.js"></script>
	<script src="js/sparklines.js"></script>
	<script src="js/charts.js"></script>
	<script src="js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script src="custom-js/landing-page.js"></script>
	<script>
  var curLatitude;
var curLongitude;
      //knob
      $(function() {
        $(".knob").knob({
          'draw' : function () { 
            $(this.i).val(this.cv + '%')
          }
        })
      });

      //carousel
      $(document).ready(function() {
          $("#owl-slider").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true

          });
      });

      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });
  
  /* ---------- Map ---------- */
/*$(function(){
  $('#map').vectorMap({
    map: 'world_mill_en',
    series: {
      regions: [{
        values: gdpData,
        scale: ['#000', '#000'],
        normalizeFunction: 'polynomial'
      }]
    },
 backgroundColor: '#eef3f7',
    onLabelShow: function(e, el, code){
      el.html(el.html()+' (GDP - '+gdpData[code]+')');
    }
  });
});*/

  function initMap() {
   if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
   } else {
    alert("Geolocation is not supported by this browser");
   }
  }
  function showPosition(position) {
   curLatitude = position.coords.latitude;
   curLongitude = position.coords.longitude;
   var mapCanvas = document.getElementById("map");
   var myCenter = new google.maps.LatLng(curLatitude, curLongitude);
   var mapOptions = {
    center : myCenter,
    zoom : 10,
    styles: [
                {elementType: 'geometry', stylers: [{color: '#242f3e'}]},
                {elementType: 'labels.text.stroke', stylers: [{color: '#242f3e'}]},
                {elementType: 'labels.text.fill', stylers: [{color: '#746855'}]},
                {
                  featureType: 'administrative.locality',
                  elementType: 'labels.text.fill',
                  stylers: [{color: '#d59563'}]
                },
                {
                  featureType: 'poi',
                  elementType: 'labels.text.fill',
                  stylers: [{color: '#d59563'}]
                },
                {
                  featureType: 'poi.park',
                  elementType: 'geometry',
                  stylers: [{color: '#263c3f'}]
                },
                {
                  featureType: 'poi.park',
                  elementType: 'labels.text.fill',
                  stylers: [{color: '#6b9a76'}]
                },
                {
                  featureType: 'road',
                  elementType: 'geometry',
                  stylers: [{color: '#38414e'}]
                },
                {
                  featureType: 'road',
                  elementType: 'geometry.stroke',
                  stylers: [{color: '#212a37'}]
                },
                {
                  featureType: 'road',
                  elementType: 'labels.text.fill',
                  stylers: [{color: '#9ca5b3'}]
                },
                {
                  featureType: 'road.highway',
                  elementType: 'geometry',
                  stylers: [{color: '#746855'}]
                },
                {
                  featureType: 'road.highway',
                  elementType: 'geometry.stroke',
                  stylers: [{color: '#1f2835'}]
                },
                {
                  featureType: 'road.highway',
                  elementType: 'labels.text.fill',
                  stylers: [{color: '#f3d19c'}]
                },
                {
                  featureType: 'transit',
                  elementType: 'geometry',
                  stylers: [{color: '#2f3948'}]
                },
                {
                  featureType: 'transit.station',
                  elementType: 'labels.text.fill',
                  stylers: [{color: '#d59563'}]
                },
                {
                  featureType: 'water',
                  elementType: 'geometry',
                  stylers: [{color: '#17263c'}]
                },
                {
                  featureType: 'water',
                  elementType: 'labels.text.fill',
                  stylers: [{color: '#515c6d'}]
                },
                {
                  featureType: 'water',
                  elementType: 'labels.text.stroke',
                  stylers: [{color: '#17263c'}]
                }
              ]
   };
   var map = new google.maps.Map(mapCanvas, mapOptions);
   var marker = new google.maps.Marker({
    position : myCenter,
    animation : google.maps.Animation.BOUNCE
   });
   marker.setMap(map);
  }
 </script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBDyo5j1o1y-deISZoPGK4ltDTwOIdxwt8&callback=initMap"></script>
</body>
</html>