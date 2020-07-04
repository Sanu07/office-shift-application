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
         .info-box i {
         margin-right: 0px;
         }
         .loader {
  			border: 16px solid #f3f3f3;
  			border-radius: 50%;
  			border-top: 16px solid #394a59;
  			border-bottom: 16px solid #394a59;
  			width: 120px;
  			height: 120px;
  			position: fixed;
  			top: 50%;
  			left: 50%;
  			-webkit-animation: spin 2s linear infinite;
  			animation: spin 2s linear infinite;
		}

		@-webkit-keyframes spin {
  			0% { -webkit-transform: rotate(0deg); }
  			100% { -webkit-transform: rotate(360deg); }
		}

		@keyframes spin {
  			0% { transform: rotate(0deg); }
  			100% { transform: rotate(360deg); }
		}
		[ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
 			display: none !important;
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
   <body ng-app="shiftApp" ng-controller="landing-page-controller" ng-cloak>
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
            <a href="#" class="logo">Shift <span class="lite">Application</span></a>
            <!--logo end-->
            <div class="top-nav notification-row">
               <!-- notificatoin dropdown start-->
               <ul class="nav pull-right top-menu">
                  <!-- task notificatoin start -->
                  <li id="task_notificatoin_bar" class="dropdown">
                     <a
                        data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
                        class="icon-task-l"></i> <span class="badge bg-important" >shift
                     {{totalCount}}</span>
                     </a>
                     <ul class="dropdown-menu extended tasks-bar">
                        <div class="notify-arrow notify-arrow-blue"></div>
                        <li>
                           <p class="blue">Today's Shift Coverage</p>
                        </li>
                        <li>
                           <a href="#">
                              <div class="task-info">
                                 <div class="desc">Morning</div>
                                 <div class="percent">{{mCount}}
                                    [{{(mCount*100)/totalCount || 0}}]%
                                 </div>
                              </div>
                              <div class="progress progress-striped">
                                 <div class="progress-bar progress-bar-success"
                                    role="progressbar" aria-valuenow="90" aria-valuemin="0"
                                    aria-valuemax="100" style="width: {{(mCount*100)/totalCount || 0}}%;">
                                    <span class="sr-only">90% Complete (success)</span>
                                 </div>
                              </div>
                           </a>
                        </li>
                        <li>
                           <a href="#">
                              <div class="task-info">
                                 <div class="desc">Night</div>
                                 <div class="percent">{{nCount}}
                                    [{{(nCount*100)/totalCount || 0}}]%
                                 </div>
                              </div>
                              <div class="progress progress-striped">
                                 <div class="progress-bar progress-bar-info" role="progressbar"
                                    aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"
                                    style="width: {{(nCount*100)/totalCount || 0}}%">
                                    <span class="sr-only">30% Complete (warning)</span>
                                 </div>
                              </div>
                           </a>
                        </li>
                        <li>
                           <a href="#">
                              <div class="task-info">
                                 <div class="desc">Afternoon</div>
                                 <div class="percent">{{aCount}}
                                    [{{(aCount*100)/totalCount || 0}}]%
                                 </div>
                              </div>
                              <div class="progress progress-striped">
                                 <div class="progress-bar progress-bar-warning"
                                    role="progressbar" aria-valuenow="80" aria-valuemin="0"
                                    aria-valuemax="100" style="width: {{(aCount*100)/totalCount || 0}}%">
                                    <span class="sr-only">80% Complete</span>
                                 </div>
                              </div>
                           </a>
                        </li>
                        <li>
                           <a href="#">
                              <div class="task-info">
                                 <div class="desc">Weekly Off</div>
                                 <div class="percent">{{wCount}}
                                    [{{(wCount*100)/totalCount || 0}}]%
                                 </div>
                              </div>
                              <div class="progress progress-striped">
                                 <div class="progress-bar progress-bar-danger"
                                    role="progressbar" aria-valuenow="78" aria-valuemin="0"
                                    aria-valuemax="100" style="width: {{(wCount*100)/totalCount || 0}}%">
                                    <span class="sr-only">78% Complete (danger)</span>
                                 </div>
                              </div>
                           </a>
                        </li>
                        <li class="external"><a href="/list-all-shifts">See All
                           Shifts</a>
                        </li>
                     </ul>
                  </li>
                  <!-- task notificatoin end -->
                  <!-- inbox notificatoin start-->
                  <li id="mail_notificatoin_bar" class="dropdown">
                     <a
                        data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
                        class="icon-envelope-l"></i> <span class="badge bg-important" >{{incidentsResponse.length+jirasResponse.length+leavesResponse.length}}</span>
                     </a>
                     <ul class="dropdown-menu extended inbox">
                        <div class="notify-arrow notify-arrow-blue"></div>
                        <li>
                           <p class="blue" >You have
                              {{incidentsResponse.length+jirasResponse.length+leavesResponse.length}}
                              new messages
                           </p>
                        </li>
                        <li><a href="/list-all-jiras"> <span class="photo"><img
                           alt="avatar" src="./img/avatar-mini.jpg"></span> <span
                           class="subject"> <span class="from">Jiras</span> <span
                           class="time" >{{jirasResponse.length == 0 ? 'N/A' :
                           jirasResponse.length}}</span>
                           </span> <span class="message"> I really like this admin panel. </span>
                           </a>
                        </li>
                        <li><a href="/list-all-incidents"> <span class="photo"><img
                           alt="avatar" src="./img/avatar-mini2.jpg"></span> <span
                           class="subject"> <span class="from">Incidents</span> <span
                           class="time">{{incidentsResponse.length == 0 ? 'N/A' :
                           incidentsResponse.length}}</span>
                           </span> <span class="message"> Hi, What is next project plan? </span>
                           </a>
                        </li>
                        <li><a href="/list-all-leaves"> <span class="photo"><img
                           alt="avatar" src="./img/avatar-mini3.jpg"></span> <span
                           class="subject"> <span class="from">Leaves</span> <span
                           class="time">{{leavesResponse.length == 0 ? 'N/A' :
                           leavesResponse.length}}</span>
                           </span> <span class="message"> I am like to buy this Admin
                           Template. </span>
                           </a>
                        </li>
                        <li><a href="/create-wsr"> <span class="photo"><img
                           alt="avatar" src="./img/avatar-mini4.jpg"></span> <span
                           class="subject"> <span class="from">WSR</span> <span
                           class="time">{{wsrResponse.length == 0 ? 'Not updated'
                           : 'Updated'}}</span>
                           </span> <span class="message"> Icon fonts are great. </span>
                           </a>
                        </li>
                        <li><a href="#">See all messages</a></li>
                     </ul>
                  </li>
                  <!-- inbox notificatoin end -->
                  <!-- alert notification start-->
                  <li id="alert_notificatoin_bar" class="dropdown">
                     <a
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
                           </a>
                        </li>
                        <li><a href="#"> <span class="label label-warning"><i
                           class="icon_pin"></i></span> John location. <span
                           class="small italic pull-right">50 mins</span>
                           </a>
                        </li>
                        <li><a href="#"> <span class="label label-danger"><i
                           class="icon_book_alt"></i></span> Project 3 Completed. <span
                           class="small italic pull-right">1 hr</span>
                           </a>
                        </li>
                        <li><a href="#"> <span class="label label-success"><i
                           class="icon_like"></i></span> Mick appreciated your work. <span
                           class="small italic pull-right"> Today</span>
                           </a>
                        </li>
                        <li><a href="#">See all notifications</a></li>
                     </ul>
                  </li>
                  <!-- alert notification end-->
                  <!-- user login dropdown start-->
                  <li class="dropdown">
                     <a data-toggle="dropdown"
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
                           Out</a>
                        </li>
                        <li><a href="documentation.html"><i class="icon_key_alt"></i>
                           Documentation</a>
                        </li>
                        <li><a href="documentation.html"><i class="icon_key_alt"></i>
                           Documentation</a>
                        </li>
                     </ul>
                  </li>
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
                     </a>
                  </li>
                  <li class=""><a class="" href="/profile"> <i
                     class="icon_document_alt"></i> <span>Profile</span>
                     </a>
                  </li>
                  <li class="sub-menu">
                     <a href="javascript:;" class=""> <i
                        class="icon_document_alt"></i> <span>Jiras</span> <span
                        class="menu-arrow arrow_carrot-right"></span>
                     </a>
                     <ul class="sub">
                        <li><a class="" href="/list-all-jiras">List All Jiras</a></li>
                        <li><a class="" href="/create-jiras">Create Jiras</a></li>
                     </ul>
                  </li>
                  <li class="sub-menu">
                     <a href="javascript:;" class=""> <i
                        class="icon_desktop"></i> <span>Incidents</span> <span
                        class="menu-arrow arrow_carrot-right"></span>
                     </a>
                     <ul class="sub">
                        <li><a class="" href="/list-all-incidents">List All
                           Incidents</a>
                        </li>
                        <li><a class="" href="/create-incidents">Create New
                           Incident</a>
                        </li>
                     </ul>
                  </li>
                  <li class="sub-menu">
                     <a href="javascript:;" class=""> <i
                        class="icon_document_alt"></i> <span>Leaves</span> <span
                        class="menu-arrow arrow_carrot-right"></span>
                     </a>
                     <ul class="sub">
                        <li><a class="" href="/list-all-leaves">Leave Approval</a></li>
                        <li><a class="" href="/create-leaves">Apply Leave</a></li>
                        <li><a class="" href="/list-my-leaves">My Leaves</a></li>
                     </ul>
                  </li>
                  <li class="sub-menu">
                     <a href="javascript:;" class=""> <i
                        class="icon_document_alt"></i> <span>Shifts</span> <span
                        class="menu-arrow arrow_carrot-right"></span>
                     </a>
                     <ul class="sub">
                        <li><a class="" href="/list-all-shifts">Show Monthly
                           Shifts</a>
                        </li>
                        <li><a class="" href="/shift-plan-generator">Generate
                           Shifts</a>
                        </li>
                     </ul>
                  </li>
                  <li class="sub-menu">
                     <a href="javascript:;" class=""> <i
                        class="icon_document_alt"></i> <span>Status Report</span> <span
                        class="menu-arrow arrow_carrot-right"></span>
                     </a>
                     <ul class="sub">
                        <li><a class="" href="/create-wsr">Update my WSR</a></li>
                        <li><a class="" href="/list-wsr">List WSR</a></li>
                     </ul>
                  </li>
               </ul>
               <!-- End -->
               <!-- sidebar menu end-->
            </div>
         </aside>
         <!--sidebar end-->
         <!--main content start-->
         <div class="loader" ng-if="loading"></div>
         <section id="main-content" ng-if="!loading">
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
               <div class="row" ng-if="shiftDetailsMonth">
                  <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                     <div class="info-box blue-bg">
                        <i class="fa fa-users"></i>
                        <div class="title" >{{shiftDetailsMonth}}
                           {{shiftDetailsYear}}
                        </div>
                        <div class="" >Morning | {{countMorningShift || 0}}</div>
                        <div class="" >Afternoon | {{countAfternoonShift || 0}}</div>
                        <div class="" >Night | {{countNightShift || 0}}</div>
                        <div class="" >WeeklyOff | {{countWeeklyOff|| 0}}</div>
                     </div>
                     <!--/.info-box-->
                  </div>
                  <!--/.col-->
                  <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                     <div class="info-box brown-bg">
                        <i class="fa fa-pencil-square-o"></i>
                        <div class="title">Past 30 days</div>
                        <div >Jiras | {{jirasResponse.length}}</div>
                        <div >Incidents | {{incidentsResponse.length}}</div>
                        <div >Leaves | {{leavesResponse.length}}</div>
                     </div>
                     <!--/.info-box-->
                  </div>
                  <!--/.col-->
                  <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                     <div class="info-box dark-bg">
                        <i class="fa fa-file-word-o"></i>
                        <div class="title">WSR for {{nextThursday}}</div>
                        <div class="" >{{wsrResponse.length > 0 ? 'has been updated'
                           : 'has not been updated' }}
                        </div>
                     </div>
                     <!--/.info-box-->
                  </div>
                  <!--/.col-->
                  <div  class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                     <div class="info-box green-bg">
                        <i class="fa fa-sign-in"></i>
                        <div class="title">Last Login</div>
                        <div class="">{{lastLoginTime == null ? 'This is your first
                           time login' : lastLoginTime}}
                        </div>
                     </div>
                     <!--/.info-box-->
                  </div>
                  <!--/.col-->
               </div>
               <!--/.row-->
               <div class="row">
                  <div class="col-lg-9 col-md-12">
                           <!-- <h2>
                              <i class="fa fa-map-marker red"></i><strong>Countries</strong>
                           </h2>
                           <div class="panel-actions">
                              <a href="index.html#" class="btn-setting"><i
                                 class="fa fa-rotate-right"></i></a> <a href="index.html#"
                                 class="btn-minimize"><i class="fa fa-chevron-up"></i></a> <a
                                 href="index.html#" class="btn-close"><i
                                 class="fa fa-times"></i></a>
                           </div> -->
                        <div>
                           <div google-chart chart="incJiraPrevMonthsChart" style="height:410px; width:100%;"></div>
                        </div>
                  </div>
                  <div class="col-md-3">
                  	<div google-chart chart="myChartObject" style="height:400px; width:100%;"></div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-lg-9 col-md-12">
                     <div class="panel panel-default">
                        <div class="panel-heading">
                           <h2>
                              <i class="fa fa-map-marker red"></i><strong>Logged In From</strong>
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
                           <div id="map" style="height: 100%; width: 100%"></div>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-3">
                  	<div google-chart chart="jiraIncStatusChart" style="height:400px; width:100%;"></div>
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
                  <!-- <a
                     href="https://bootstrapmade.com/free-business-bootstrap-themes-website-templates/">Business
                     Bootstrap Themes</a> by <a href="https://bootstrapmade.com/">BootstrapMade</a> -->
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
      <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-google-chart/1.0.0-beta.1/ng-google-chart.min.js" type="text/javascript"></script>
      <script
         src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDIViJw6I2CW86FLXbZGzQpg29MmUH-LXw&callback=initMap"></script>
   </body>
</html>