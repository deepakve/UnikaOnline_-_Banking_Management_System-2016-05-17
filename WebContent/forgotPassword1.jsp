<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
<link href="css\home_style.css" rel="stylesheet" type="text/css" />
<link href="css\home.css" rel="stylesheet" type="text/css" />
<link href="css\firstTimeAccess.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/forgotpwd1.js">

</script>
</head>
<body>
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
						<h1>No Problem, Everyone forgets!</h1>
						<h6>Please fill in the following details.</h6>
						<div class="login_details">
							<form action="ForgotPasswordProcessorServlet1" method="post">

								<div class="pwd">
									<a>Customer Id:</a><br> <input type="text"
										name="customerid" id="customerid" value=""
										style="height: 15px; width: 200px"
										placeholder="Enter the customer ID" autocomplete="off"
										class="required" /> <label flag="0" id="custid_msg"></label>
								</div>

								<div class="pwd">
									<a>Email Id:</a><br> <input type="text" name="emailid"
										id="emailid" value="" style="height: 15px; width: 200px"
										placeholder="Enter your Email ID" autocomplete="off"
										class="required" /> <label flag="0" id="mail_msg"></label>
								</div>

								<div class="pwd">
									<a>Contact No:</a><br> <input type="text" name="contactNo"
										id="contactNo" value="" style="height: 15px; width: 200px"
										placeholder="Enter your Mobile No" autocomplete="off"
										class="required" /> <label flag="0" id="ctct_msg"></label>
								</div>

								<input type="submit" value="Continue"
									onclick="return validate()" />
							</form>
						</div>
					</div>
					<br> <br> <br> <br> <br> <br>
					<div>
						<img src="images\oops!.png" height="200" width="200" />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>