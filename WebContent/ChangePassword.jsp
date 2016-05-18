<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\ChangePassword.css" rel="stylesheet" type="text/css" />
<script src="jquery.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
<script type="text/javascript" src="js/resetpwd.js"></script>
</head>
<body oncontextmenu="return false" onload="noBack();">
	<%
		HttpSession httpsession = request.getSession();
		int customerid = (Integer) httpsession.getAttribute("customerid");
		String customername = (String) httpsession
				.getAttribute("customername");
	%>
	<div class="container">
		<img src="images\logo.png" height="50" width="100"
			style="width: 152px;" />
		<h4>
			Hello
			<%=customername%></h4>
		<h6>
			<a href="logout.jsp">Logout</a>
		</h6>
	</div>

	<div class="page">
		<div class="menu">
			<ul class="tabs">
				<li><a href="acc_home.jsp">Account Info</a></li>
				<li><a href="manageProfile.jsp" class="active">Manage
						Profile</a></li>
				<li><a href="transferFunds.jsp">Transfer Funds</a></li>
				<li><a href="PayBills.jsp">Pay Bills</a></li>
			</ul>
		</div>

		<div class="info">
		<br>
		<h5 style="margin-left: 35px;">You are here: <a href="manageProfile.jsp"> Manage Profile</a> > <a href="ChangePassword.jsp"> Change Password</a></h5>
			<br>
			<form action="ChangePasswordServlet" method="post">
				<h4>Fill the following details</h4>
				<table>
					<tr>
						<td>Current Password</td>
						<td><input type="password" name="oldpassword"
							id="curPassword" value="" placeholder="Enter Current Password"
							/ autocomplete="off"></td>
						<td><label flag="0" id="pwd_msg"></label></td>
					</tr>
					<tr>
						<td>New Password</td>
						<td><input type="password" name="newpassword"
							id="newPassword" value="" placeholder="Enter New Password"
							/ autocomplete="off"></td>
						<td><label flag="0" id="pwd1_msg"></label></td>
					</tr>
					<tr>
						<td>Confirm Password</td>
						<td><input type="password" name="confirmpassword"
							id="cfmPassword" value="" placeholder="Retype New Password"
							/ autocomplete="off"></td>
						<td><label flag="0" id="pwd2_msg"></label></td>
					</tr>
					<tr>
						<td align="center"><input type=submit value="Change" onclick="return validate()"></td>
						<td align="center"><input type=reset value="Reset"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>