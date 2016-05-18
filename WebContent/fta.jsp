<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>First Time Login</title>
<link href="css\home_style.css" rel="stylesheet" type="text/css" />
<link href="css\firstTimeAccess.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
<script type="text/javascript" src="js/firstaccess.js"></script>
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
							<td>
								<ul class="field">
									<li><h4>New Password :</h4></li>
									<li><input type="password" name="newpassword"
										id="newpassword" value="" placeholder="Enter New Password"
										autocomplete="off"></li>
									<br>
									<label flag="0" id="pwd_msg"></label>
									<li><h4>Secret Question1 :</h4></li>
									<li><select name="question1">
											<option value="default">---Select---</option>
											<option value="What was your childhood nickname?">What
												was your childhood nickname?</option>
											<option
												value="What is the name of your	favorite childhood friend?">What
												is the name of your favorite childhood friend?</option>
											<option value="What school did you attend for sixth grade?">What
												school did you attend for sixth grade?</option>
											<option
												value="What was the name of your first stuffed animal?">What
												was the name of your first stuffed animal?</option>
											<option value="Who was your childhood hero?">Who was
												your childhood hero?</option>
											<option value="What is your grandmother's first name?">What
												is your grandmother's first name?</option>
									</select></li>
									<br>
									<label flag="0" id="sec1_msg"></label>

									<li><h4>Your Answer :</h4></li>
									<li><input type="text" name="answer1" id="answer1"
										value="" placeholder="Enter Answer" autocomplete="off"></li>
									<label flag="0" id="ans1_msg"></label>

									<li><input type="submit" value="Continue"
										onclick="return validate()" style="margin-left: 130px;" /></li>
								</ul>
							</td>
							<td>
								<ul class="field">
									<li><h4>Retype Password :</h4></li>
									<li><input type="password" name="repassword"
										id="repassword" value="" placeholder="Retype Password"
										autocomplete="off"></li>
									<br>
									<label flag="0" id="pwd2_msg"></label>

									<li><h4>Secret Question2 :</h4></li>
									<li><select name="question2">
											<option value="default">---Select---</option>
											<option value="What was your childhood nickname?">What
												was your childhood nickname?</option>
											<option
												value="What is the name of your	favorite childhood friend?">What
												is the name of your favorite childhood friend?</option>
											<option value="What school did you attend for sixth grade?">What
												school did you attend for sixth grade?</option>
											<option
												value="What was the name of your first stuffed animal?">What
												was the name of your first stuffed animal?</option>
											<option value="Who was your childhood hero?">Who was
												your childhood hero?</option>
											<option value="What is your grandmother's first name?">What
												is your grandmother's first name?</option>
									</select></li>
									<br>
									<label flag="0" id="sec2_msg"></label>

									<li><h4>Your Answer :</h4></li>
									<li><input type="text" name="answer2" id="answer2"
										value="" placeholder="Enter Answer" autocomplete="off"></li>
									<label flag="0" id="ans2_msg"></label>

									<li><input type="button" value="Reset" /></li>
								</ul>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>