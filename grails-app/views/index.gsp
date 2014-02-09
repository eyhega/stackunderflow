<!DOCTYPE html>
<html>
  <head>
    <title>Stack Underflow</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/site.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="js/libs/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="text/javascript" src="js/libs/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="js/libs/prototype-1.7.1.min.js"></script>

    <script type="text/javascript" src="js/libs/jquery-ui-1.10.3.min.js"></script>

	<script type="text/javascript" src="js/libs/i18next-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/libs/moment-langs.min.js"></script>

	<script type="text/javascript" src="js/libs/markup.min.js"></script>
	<script type="text/javascript" src="js/libs/markup-extras.numbers.min.js"></script>
	
	<script type="text/javascript" src="js/libs/modernizr-2.6.2.min.js"></script>

	<script type="text/javascript" src="js/libs/crypto-js-3.1.2-sha2-512.js"></script>
	<script type="text/javascript" src="js/libs/intro.min.js"></script>
    
    <script src="js/site.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<!-- Navigation bar -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" id="zz-navbar">
		<ul class="nav navbar-nav" style="width:100%">
			<!-- Brand and toggle get grouped for better mobile display -->
		    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		      <span class="sr-only">Toggle navigation</span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		    </button>
		    <a class="navbar-brand" href="#">Stack Underflow</a>
			<!-- Set active in function -->
			<li id="nav_topics" class="active"><a href="#" onClick="UI.Question.showList();"><span class="glyphicon glyphicon-list-alt zz-icon-navbar-margin"></span> Topics</a></li>
			<li id="nav_myquestions" style="display:none" onClick="UI.Question.showMyQuestions()"><a href="#"><span class="glyphicon glyphicon-question-sign zz-icon-navbar-margin"></span> My questions</a></li>
			<li id="nav_myanswers" style="display:none"><a href="#"><span class="glyphicon glyphicon-book zz-icon-navbar-margin"></span> My answers</a></li>
			<li id="nav_mycomments" style="display:none"><a href="#"><span class="glyphicon glyphicon-chevron-right zz-icon-navbar-margin"></span> My comments</a></li>
			<li id="nav_messages" style="display:none"><a href="#"><span class="glyphicon glyphicon-envelope zz-icon-navbar-margin"></span> Messages</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-stats zz-icon-navbar-margin"></span> Statistics</a></li>

			<li id="nav_dropdown_options" class="dropdown pull-right" style="display:none">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog zz-icon-navbar-margin"></span><b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="#"><span class="glyphicon glyphicon-pencil zz-icon-navbar-margin"></span> Modify profile</a></li>
				<li><a href="mailto:kabogtob@gmail.com"><span class="glyphicon glyphicon-warning-sign zz-icon-navbar-margin"></span> Support</a></li>
				<li class="divider"></li>
				<li><a href="#" onClick="UI.Login.LogOut();"><span class="glyphicon glyphicon-off zz-icon-navbar-margin"></span> Log out</a></li>
				</ul>
			</li>
			<li id="nav_login" class="pull-right"><a href="#" onClick="UI.Login.ShowLogIn();"><span class="glyphicon glyphicon-record zz-icon-navbar-margin"></span> Login !</a></li>
			<li id="nav_register" class="pull-right"><a href="#" onClick="UI.Login.ShowRegister();"><span class="glyphicon glyphicon-plus-sign zz-icon-navbar-margin"></span> Register</a></li>
			<li class="pull-right"><a href="#"><span class="glyphicon glyphicon-info-sign zz-icon-navbar-margin"></span> Help</a></li>

			<form class="navbar-form navbar-left" role="search">
				<span class="glyphicon glyphicon-search zz-icon-navbar-margin"></span>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
			</form>
		</ul>
    </nav>

	<!-- Login Modal -->
	<div class="modal fade" id="zz-modal-login" tabindex="-1" role="dialog" aria-labelledby="zz-modal-login-label" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="zz-modal-login-label">Sign In...</h4>
	      </div>
	      <div class="modal-body">
	      	<div id="zz-modal-login-error" class="alert alert-danger" style="display:none">Bad Password or Username.</div>
	        <form class="form-signin" role="form">
				<input id="zz-modal-login-username" type="text" class="form-control" placeholder="Username" required="" autofocus="">
				<input id="zz-modal-login-password" type="password" class="form-control" placeholder="Password" required="">
				<button class="btn btn-lg btn-primary btn-block" onClick="UI.Login.LogIn()">Sign in</button>
			</form>
			<h5>Press <em>Enter </em> &nbsp;to submit.</h5>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- Registering Modal -->
	<div class="modal fade" id="zz-modal-register" tabindex="-1" role="dialog" aria-labelledby="zz-modal-register-label" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="zz-modal-register-label">Join the smartest community</h4>
	      </div>
	      <div class="modal-body">
	      	<div id="zz-modal-register-error" class="alert alert-danger" style="display:none"></div>
	        <form class="form-signin" role="form">
				<input id="zz-modal-register-username" type="text" class="form-control" placeholder="Username" required="" autofocus="">
				<input id="zz-modal-register-password" type="password" class="form-control" placeholder="Password" required="">
				<input id="zz-modal-register-email" type="email" class="form-control" placeholder="Email" required="">
				<button class="btn btn-lg btn-primary btn-block" onClick="UI.Login.Register()">Register</button>
			</form>
			<h5>Press <em>Enter </em> &nbsp;to submit.</h5>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>

	<div class="row" id="zz-content">
		<!-- Sidebar -->
		<div class="col-md-2 panel panel-default" id="zz-sidebar" hidden>

		</div>

		<!-- Main content -->
  		<div class="col-md-12 panel panel-default" id="zz-main-content">
  			<div class="panel-body">
  			</div>
  		</div>
	</div>

    <script type="text/javascript">
    Site.initialize(function() {
    	UI.resizeMainContent();
    	$j(window).resize(function() { UI.resizeMainContent(); });
    	UI.Question.showList();
   	});
    </script>
  </body>
</html>