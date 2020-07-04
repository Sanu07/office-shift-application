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

<title>Application | List all incidents</title>

<!-- Bootstrap CSS -->
<!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
<!-- bootstrap theme -->
<!-- <link href="css/bootstrap-theme.css" rel="stylesheet"> -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!-- Custom styles -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.0/jquery-confirm.min.css">

<style type="text/css">
/*loader CSS*/
.no-js #loader {
	display: none;
}

.js #loader {
	display: block;
	position: absolute;
	left: 100px;
	top: 0;
}

.se-pre-con {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	/* z-index: 9999; */
	background: url(img/Preloader_2.gif) center no-repeat #fff;
}
</style>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
</head>

<body ng-app="shiftApp" ng-controller="list-incident-controller"
	ng-init="getAllIncidents()" ng-cloak>

	<!-- Paste this code after body tag -->
	<div class="se-pre-con"></div>
	<!-- Ends -->
	<!-- container section start -->
	<section id="container" class="">
		<!--header start-->

		<header class="header dark-bg">
			<div class="toggle-nav">
				<div class="icon-reorder tooltips"
					data-original-title="Toggle Navigation" data-placement="bottom">
					<i class="icon_menu"></i>
				</div>
			</div>

			<!--logo start-->
			<a href="#" class="logo">Shift <span class="lite">Application</span></a>
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
											aria-valuemax="100" style="width: {{(mCount*100)/totalCount}}%">
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
											style="width: {{(nCount*100)/totalCount}}%">
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
											aria-valuemax="100" style="width: {{(aCount*100)/totalCount}}%">
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
											aria-valuemax="100" style="width: {{(wCount*100)/totalCount}}%">
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
				<div class="row" id="not-movable">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa fa-bars"></i> Incidents
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
							<li><i class="fa fa-bars"></i>Incidents</li>
							<li><i class="fa fa-square-o"></i>List All Incidents</li>
						</ol>
					</div>
				</div>
				<!-- page start-->
				<div class="container">
					<h3>Incidents</h3>

					<!-- search -->
					<div class="form-group" style="width: 60%; margin-bottom: 5%;">
						<div class="col-sm-5">
							<input type="search" ng-model="searchString"
								class="form-control round-input"
								placeholder="Search for any string">
						</div>
					</div>
					<!-- search ends -->

					<!-- Linked Jira Modal -->
					<div class="modal fade" id="edit-jira-modal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									Jira
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>
								<form class="form-horizontal" id="edit-jira-form" method="post">
									<div class="modal-body">
										<table class="table">
											<tbody>
												<tr>
													<td>Application Name :</td>
													<td><input ng-model="applicationName"
														disabled="disabled" class="form-control round-input" /></td>
												</tr>
												<tr>
													<td>Jira Number :</td>
													<td><input ng-model="jiraNumber"
														class="form-control round-input" disabled="disabled" /></td>
												</tr>
												<tr>
													<td>Assignee Name :</td>
													<td><input ng-model="assigneeName" disabled="disabled"
														class="form-control round-input" /></td>
												</tr>
												<tr>
													<td>Status :</td>
													<td><select class="form-control round-input"
														disabled="disabled" name="currentStatus"
														ng-model="currentStatus">
															<option value="Open">Open</option>
															<option value="Inprogress">In Progress</option>
															<option value="Onsite Review">Onsite Review</option>
															<option value="Closed">Closed</option>
													</select></td>
												</tr>
												<tr>
													<td>Issue Summary :</td>
													<td><input ng-model="issueSummary" disabled="disabled"
														class="form-control round-input" /></td>
												</tr>
												<tr>
													<td>Comments :</td>
													<td><input ng-model="comments" disabled="disabled"
														class="form-control round-input" /></td>
												</tr>
											</tbody>
										</table>
										<button type="submit" class="btn btn-primary"
											disabled="disabled">Save</button>
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Cancel</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- Edit Jira Modal ends -->
					<!-- Edit Incident Modal starts -->

					<div class="modal fade" id="edit-incident-modal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									Incidents
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>
								<form class="form-horizontal" id="edit-incident-form"
									method="post" ng-submit="saveEditedIncident(this)">
									<div class="modal-body">
										<table class="table">
											<tbody>
												<tr>
													<td>Application Name :</td>
													<td><select class="form-control round-input"
														name="currentStatus" ng-model="applicationName">
															<option value="DIGITAL-529">DIGITAL-529</option>
															<option value="Oppenheimer Funds">Oppenheimer
																Funds</option>
													</select></td>
												</tr>
												<tr>
													<td>Incident Number :</td>
													<td><input ng-model="incidentNumber"
														class="form-control round-input" disabled="disabled" /></td>
												</tr>
												<tr>
													<td>Assignee Name :</td>
													<td><input ng-model="assigneeName"
														class="form-control round-input" /></td>
												</tr>
												<tr>
													<td>Status :</td>
													<td><select class="form-control round-input"
														name="currentStatus" ng-model="currentStatus">
															<option value="Open">Open</option>
															<option value="Closed">Closed</option>
													</select></td>
												</tr>
												<tr>
													<td>Acknowledged By :</td>
													<td><input ng-model="acknowledgedBy"
														class="form-control round-input" /></td>
												</tr>
												<tr>
													<td>Comments :</td>
													<td><input ng-model="commentsOnResolution"
														class="form-control round-input" /></td>
												</tr>
												<tr>
													<td>Issue Description :</td>
													<td><input ng-model="issueDescription"
														class="form-control round-input" /></td>
												</tr>
												<tr>
													<td>Time Spent :</td>
													<td><input ng-model="timeSpent"
														class="form-control round-input" /></td>
												</tr>
												<tr>
													<td>Linked Jira :</td>
													<td><input ng-model="linkedJira"
														class="form-control round-input" /></td>
												</tr>
											</tbody>
										</table>
										<button type="submit" class="btn btn-primary">Save</button>
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Cancel</button>
										<span style="margin-left: 25px; color: {{responseColor">{{editResponseMessage}}</span>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- Edit Incident Modal ends -->
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Application Name</th>
							<th ng-click="sort('incidentNumber')">Incident Number <span
								class="fa fa-sort"></span>
							</th>
							<th ng-click="sort('assigneeName')">Assignee Name <span
								class="fa fa-sort"></span>
							</th>
							<th>Current Status</th>
							<th>Incident Description</th>
							<th>Time Spent(Hr)</th>
							<th>Comments</th>
							<th>Linked Jira</th>
						</tr>
					</thead>
					<tbody>
						<tr
							dir-paginate="data in incidentListResponse.data | orderBy:sortKey:reverse | searchFor:searchString | itemsPerPage:5">
							<td>{{data.applicationName}}</td>
							<td>{{data.incidentNumber}}</td>
							<td>{{data.assigneeName}}</td>
							<td>{{data.currentStatus}}</td>
							<td style="width: 500px">{{data.issueDescription}}</td>
							<td>{{data.timeSpent}}</td>
							<td style="width: 500px">{{data.commentsOnResolution}}</td>
							<td><button class="btn btn-primary" id="edit-jira"
									ng-click="listJira(this)" ng-if="data.linkedJira">{{data.linkedJira}}</button></td>
							<td><button class="btn btn-default" id="edit-jira"
									ng-click="editIncident(this)">Edit</button></td>
							<td><button class="btn btn-default" id="delete-jira"
									ng-click="deleteIncident(this)">Delete</button></td>
						</tr>
					</tbody>
				</table>
				<dir-pagination-controls max-size="5" direction-links="true"
					boundary-links="true"> </dir-pagination-controls>
				<!-- page end-->
			</section>
		</section>
		<!--main content end-->
		<div class="text-right">
			<div class="credits">
				<!-- 
                    All the links in the footer should remain intact. 
                    You can delete the links only if you purchased the pro version.
                    Licensing information: https://bootstrapmade.com/license/
                    Purchase the pro version form: https://bootstrapmade.com/buy/?theme=NiceAdmin
                -->
				<!-- <a
					href="https://bootstrapmade.com/free-business-bootstrap-themes-website-templates/">Business
					Bootstrap Themes</a> by <a href="https://bootstrapmade.com/">BootstrapMade</a> -->
			</div>
		</div>
	</section>
	<!-- container section end -->
	<!-- javascripts -->
	<script src="js/jquery.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<!--custome script for all page-->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script src="js/scripts.js"></script>
	<script src="custom-js/create-incidents.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.0/jquery-confirm.min.js"></script>
	<script
		src="https://rawgit.com/michaelbromley/angularUtils/master/src/directives/pagination/dirPagination.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
</body>
</html>