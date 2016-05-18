<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>First Time Login</title>
<link href="css\home_style.css" rel="stylesheet" type="text/css" />
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
			<div class="first_time">
				<h1>We are happy to see you here</h1>
				<h6>Please fill in the following details for future reference.</h6>
				<form action="FirstTimeAccessServlet" method="post">
					<table>
						<tr>
							<td><a>Email-Id</a></td>
							<td><input type="text" name="emailid" id="emailid" value=""
								placeholder="Enter Email-ID" autocomplete="off"></td>
						</tr>
						<tr>
							<td><a>New Password</a></td>
							<td><input type="password" name="newpassword"
								id="newpassword" value="" placeholder="Enter New Password"
								autocomplete="off"></td>
						</tr>
						<tr>
							<td><a>Retype Password</a></td>
							<td><input type="password" name="repassword" id="repassword"
								value="" placeholder="Retype Password" autocomplete="off"></td>
						</tr>
						<tr>
							<td><a>Contact No</a></td>
							<td><input type="text" name="contact" id="contact" value=""
								placeholder="Enter Contact Number" autocomplete="off"></td>
						</tr>
						<tr>
							<td><a>Secret Question1</a></td>
							<td><select name="question1">
									<option value="default">---Select---</option>
									<option value="What was your childhood nickname?">What
										was your childhood nickname?</option>
									<option
										value="What is the name of your	favorite childhood friend?">What
										is the name of your favorite childhood friend?</option>
									<option value="What school did you attend for sixth grade?">What
										school did you attend for sixth grade?</option>
									<option value="What was the name of your first stuffed animal?">What
										was the name of your first stuffed animal?</option>
									<option value="Who was your childhood hero?">Who was
										your childhood hero?</option>
									<option value="What is your grandmother's first name?">What
										is your grandmother's first name?</option>
							</select></td>
						</tr>
						<tr>
							<td><a>Your Answer</a></td>
							<td><input type="text" name="answer1" id="answer1" value=""
								placeholder="Enter Answer" autocomplete="off"></td>
						</tr>
						<tr>
							<td><a>Secret Question2</a></td>
							<td><select name="question1">
									<option value="default">---Select---</option>
									<option value="What was your childhood nickname?">What
										was your childhood nickname?</option>
									<option
										value="What is the name of your	favorite childhood friend?">What
										is the name of your favorite childhood friend?</option>
									<option value="What school did you attend for sixth grade?">What
										school did you attend for sixth grade?</option>
									<option value="What was the name of your first stuffed animal?">What
										was the name of your first stuffed animal?</option>
									<option value="Who was your childhood hero?">Who was
										your childhood hero?</option>
									<option value="What is your grandmother's first name?">What
										is your grandmother's first name?</option>
							</select></td>
						</tr>
						<tr>
							<td><a>Your Answer</a></td>
							<td><input type="text" name="answer2" id="answer2" value=""
								placeholder="Enter Answer" autocomplete="off"></td>
						</tr>
						<tr>
							<td><input type="submit" value="Continue" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>