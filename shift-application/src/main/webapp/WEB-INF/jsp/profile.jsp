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

<title>Application | Profile</title>

<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!-- Custom styles -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<link href="custom-css/jquery.toastmessage.css" rel="stylesheet">

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
<body ng-app="shiftApp" ng-controller="user-profile-controller">
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
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa-user-md"></i> Profile
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
							<li><i class="icon_documents_alt"></i>Pages</li>
							<li><i class="fa fa-user-md"></i>Profile</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<!-- profile-widget -->
					<div class="col-lg-12">
						<div class="profile-widget profile-widget-info">
							<div class="panel-body">
								<div class="col-lg-2 col-sm-2">
									<h4>{{name}}</h4>
									<div class="follow-ava">
										<img src="" alt="">
									</div>
									<h6>User</h6>
								</div>
								<div class="col-lg-4 col-sm-4 follow-info">
									<p>Hello {{name}}</p>
									<p>
										<i class="fa fa-google-plus"> {{email | lowercase}}</i>
									</p>
									<p>
										<i class="fa fa-facebook"> {{email | lowercase}}</i>
									</p>
									<p>
										<i class="fa fa-android"> {{phone}}</i>
									</p>
								</div>
								<div class="col-lg-2 col-sm-6 follow-info weather-category">
									<ul>
										<li class="active"><i class="fa fa-comments fa-2x"> </i><br>

											Contrary to popular belief, Lorem Ipsum is not simply</li>

									</ul>
								</div>
								<div class="col-lg-2 col-sm-6 follow-info weather-category">
									<ul>
										<li class="active"><i class="fa fa-bell fa-2x"> </i><br>

											Contrary to popular belief, Lorem Ipsum is not simply</li>

									</ul>
								</div>
								<div class="col-lg-2 col-sm-6 follow-info weather-category">
									<ul>
										<li class="active"><i class="fa fa-tachometer fa-2x">
										</i><br> Contrary to popular belief, Lorem Ipsum is not
											simply</li>

									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- page start-->
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading tab-bg-info">
								<ul class="nav nav-tabs">
									<li class="active" ng-click="loadStatus()"><a
										data-toggle="tab" href="#recent-activity"> <i
											class="icon-home"></i> Daily News
									</a></li>
									<li><a data-toggle="tab" href="#profile"> <i
											class="icon-user"></i> Profile
									</a></li>
									<!-- <li>
                                      <a data-toggle="tab" href="#status">
                                          <i class="icon-user"></i>
                                          Add Status
                                      </a>
                                  </li>-->
									<li class=""><a data-toggle="tab" href="#edit-profile">
											<i class="icon-envelope"></i> Edit Profile
									</a></li>
									<li class=""><a data-toggle="tab" href="#change-password">
											<i class="icon-user"></i> Change Password
									</a></li>
								</ul>
							</header>
							<div class="panel-body">
								<div class="tab-content">
									<div id="recent-activity" class="tab-pane active">
										<div class="profile-activity">
											<div class="act-time">
												<div class="activity-body act-in">
													<span class="arrow"></span>
													<!-- made changes -->

													<div class="container">
														<h2>Daily News Update</h2>
														<div id="myCarousel" class="carousel slide"
															data-ride="carousel">
															<!-- Indicators -->
															<ol class="carousel-indicators">
																<li data-target="#myCarousel" data-slide-to="0"
																	class="active"></li>
																<li data-target="#myCarousel" data-slide-to="1"></li>
																<li data-target="#myCarousel" data-slide-to="2"></li>
																<li data-target="#myCarousel" data-slide-to="3"></li>
																<li data-target="#myCarousel" data-slide-to="4"></li>
																<li data-target="#myCarousel" data-slide-to="5"></li>
																<li data-target="#myCarousel" data-slide-to="6"></li>
																<li data-target="#myCarousel" data-slide-to="7"></li>
																<li data-target="#myCarousel" data-slide-to="8"></li>
																<li data-target="#myCarousel" data-slide-to="9"></li>
															</ol>

															<!-- Wrapper for slides -->
															<div class="carousel-inner">
																<div class="item active">
																	<img src="{{response.data.articles[5].urlToImage}}"
																		alt="loading..." style="width: 100%; height: 50%">
																	<div class="carousel-caption">
																		<h3>{{response.data.articles[5].title}}</h3>
																		<p>{{response.data.articles[5].description}}</p>
																		<span><a
																			href="{{response.data.articles[5].url}}">Click
																				here to know more</a></span>
																		<p>Published At :
																			{{response.data.articles[5].publishedAt}}</p>
																	</div>
																</div>

																<div class="item"
																	ng-repeat="data in response.data.articles">
																	<img src="{{data.urlToImage}}" alt="{{data.title}}"
																		style="width: 100%; height: 50%">
																	<div class="carousel-caption">
																		<h3>{{data.title}}</h3>
																		<p>{{data.description}}</p>
																		<span><a href="{{data.url}}">Click here to
																				know more</a></span>
																		<p>Published At : {{data.publishedAt}}</p>
																	</div>
																</div>
															</div>

															<!-- Left and right controls -->
															<a class="left carousel-control" href="#myCarousel"
																data-slide="prev"> <i
																class="fa fa-chevron-circle-left" aria-hidden="true"
																style="font-size: 48px;"></i> <!--       <span class="glyphicon glyphicon-chevron-left"></span> -->
																<span class="sr-only">Previous</span>
															</a> <a class="right carousel-control" href="#myCarousel"
																data-slide="next"> <i
																class="fa fa-chevron-circle-right" aria-hidden="true"
																style="font-size: 48px;"></i> <!--  <span class="glyphicon glyphicon-chevron-right"></span> -->
																<span class="sr-only">Next</span>
															</a>
														</div>
													</div>

													<!-- End of changes -->
												</div>
											</div>
										</div>
									</div>
									<!-- profile -->
									<div id="profile" class="tab-pane">
										<section class="panel">
											<div class="bio-graph-heading">Hello {{name}}, below is
												your personal details</div>
											<div class="panel-body bio-graph-info">
												<div class="row">
													<div class="bio-row">
														<p>
															<span>Name</span><span>{{name}}</span>
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Cognizant ID</span><span>{{cognizantId}}</span>
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Occupation</span><span>Employee</span>
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Email</span><span>{{email}}</span>
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Country</span><span>India</span>
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Phone</span><span>(+91)-{{phone}}</span>
														</p>
													</div>
												</div>
											</div>
										</section>
										<section>
											<div class="row"></div>
										</section>
									</div>

									<!-- Add Status -->
									<!-- <div id="status" class="tab-pane">
         <section class="panel">
          <div class="panel-body bio-graph-info">
           <h1>Add Status</h1>
           <div class="row">
            <form class="form-horizontal" role="form" id="status-form" ng-submit="submitStatus()">
             <div class="form-group">
              <label class="col-lg-2 control-label">Status</label>
              <div class="col-lg-6">
               <input type="text" class="form-control" id="status-input" name="statusInput"
                placeholder="Update Status ..." />
              </div>
             </div>
             <div class="form-group">
              <div class="col-lg-offset-2 col-lg-10">
               <button type="submit" class="btn btn-default">Update Status</button>
               <button type="reset" class="btn btn-default">Reset</button>
              </div>
             </div>
            </form>
           </div>
          </div>
         </section>
         <section>
          <div class="row"></div>
         </section>
        </div> -->
									<!-- End Add Status -->

									<!-- Start Change Password -->
									<div id="change-password" class="tab-pane">
										<section class="panel">
											<div class="panel-body bio-graph-info">
												<h1>Change Password</h1>
												<form class="form-horizontal" role="form" method="post"
													ng-submit="changePassword()" id="change-password-form">
													<div class="form-group">
														<label class="col-lg-2 control-label">Old Password</label>
														<div class="col-lg-6">
															<input type="password" name="oldPassword"
																class="form-control" placeholder=" ">
														</div>
													</div>
													<div class="form-group">
														<label class="col-lg-2 control-label">New Password</label>
														<div class="col-lg-6">
															<input type="password" name="newPassword"
																class="form-control" placeholder=" ">
														</div>
													</div>
													<div class="form-group">
														<label class="col-lg-2 control-label">Confirm
															Password</label>
														<div class="col-lg-6">
															<input type="password" name="confirmPassword"
																class="form-control" placeholder=" ">
														</div>
													</div>
													<div class="form-group">
														<div class="col-lg-offset-2 col-lg-10">
															<button type="submit" class="btn btn-default">Change
																Password</button>
															<button type="reset" class="btn btn-default">Cancel</button>
														</div>
													</div>
												</form>
											</div>
										</section>
									</div>
									<!-- End Change Password -->

									<!-- edit-profile -->
									<div id="edit-profile" class="tab-pane">
										<section class="panel">
											<div class="panel-body bio-graph-info">
												<h1>Profile Info</h1>
												<form class="form-horizontal" role="form"
													id="edit-profile-form" ng-submit="editProfile()">
													<div class="form-group">
														<label class="col-lg-2 control-label">Name</label>
														<div class="col-lg-6">
															<input type="text" class="form-control" name="name"
																ng-model="name" placeholder=" ">
														</div>
													</div>
													<div class="form-group">
														<label class="col-lg-2 control-label">Cognizant ID</label>
														<div class="col-lg-6">
															<input type="text" class="form-control"
																name="cognizantId" ng-model="cognizantId"
																disabled="disabled" placeholder=" ">
														</div>
													</div>
													<div class="form-group">
														<label class="col-lg-2 control-label">Email</label>
														<div class="col-lg-6">
															<input type="email" class="form-control" id="url"
																name="email" ng-model="email"
																placeholder="example@gmail.com">
														</div>
													</div>
													<div class="form-group">
														<label class="col-lg-2 control-label">Phone</label>
														<div class="col-lg-6">
															<input type="text" class="form-control" id="phone"
																name="phone" ng-model="phone" placeholder=" ">
														</div>
													</div>
													<div class="form-group">
														<div class="col-lg-offset-2 col-lg-10">
															<button type="submit" class="btn btn-default">Save</button>
															<button type="reset" class="btn btn-default">Cancel</button>
														</div>
													</div>
												</form>
											</div>
										</section>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>

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
				<a
					href="https://bootstrapmade.com/free-business-bootstrap-themes-website-templates/">Business
					Bootstrap Themes</a> by <a href="https://bootstrapmade.com/">BootstrapMade</a>
			</div>
		</div>
	</section>
	<!-- container section end -->
	<!-- javascripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- jquery knob -->
	<script src="assets/jquery-knob/js/jquery.knob.js"></script>
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<!--custome script for all page-->
	<script src="js/scripts.js"></script>
	<script src="custom-js/user-profile.js"></script>
	<script src="custom-js/jquery.toastmessage.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
	<script>

      //knob
      $(".knob").knob();

  </script>


</body>
</html>
