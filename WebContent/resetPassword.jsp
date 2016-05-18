<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Security Questions to reset password</title>
<link href="css\home_style.css" rel="stylesheet" type="text/css" />
<link href="css\home.css" rel="stylesheet" type="text/css" />
<link href="css\firstTimeAccess.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Unika/jquery.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
</head>
<body oncontextmenu="return false" onload="noBack();">
	<div class="container">
		<div class="image">
			<img src="images\logo.png" height="70" width="150" />
		</div>
		</div>
	<div id="page">
		<div class="container">
			<div class="panes">
				<div id="content_1" class="content">
					<div class="first_time">
						<h1>You are almost done!</h1>
						<h6>Reset your password and continue using your account.</h6>
						<div class="login_details">
							<form action="ForgotPasswordProcessorServlet3" method="post">
								
								<div class="pwd">
									<a>New Password:</a><br> <input type="password"
										name="newPassword" id="newPassword" value="" style="height:15px; width:200px" placeholder="Enter your new password"/>
								</div>
								<div class="pwd">
									<a>Confirm Password:</a><br> <input type="password"
										name="cfmPassword" id="cfmPassword" value="" style="height:15px; width:200px" placeholder="Confirm the password" />
								</div>
								<input type="submit" value="Done" />
							</form>
						</div>
					</div>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<div>
						<img src="images\recovery.png" height="200" width="200" />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>