<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Profile</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\profile.css" rel="stylesheet" type="text/css" />
<script src="jquery.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
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
				<li class="active"><a href="manageProfile.jsp">Manage
						Profile</a></li>
				<li><a href="transferFunds.jsp">Transfer Funds</a></li>
				<li><a href="PayBills.jsp">Pay Bills</a></li>
			</ul>
		</div>

		<div class="info">
			<h4 style="margin-left: 30px;">You are here: <a href="manageProfile.jsp"> Manage Profile</a></h4>
			<br><br>
			<p>The profile tab enables you to change your password, view
				personal details and manage beneficiary accounts.</p>
			<table>
				<tr>
					<td><a href="ChangePassword.jsp">> Change Password</a></td>
					<td><a href="addAccount.jsp">> Manage Beneficiary</a></td>
				</tr>
				<tr>
					<td><a href="ViewPersonalDetails.jsp">> View Personal
							Details</a></td>
					<td><a href="AddFixedDeposits.jsp">> Manage Fixed Deposits</a></td>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>