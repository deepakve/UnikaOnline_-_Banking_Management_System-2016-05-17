<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Account</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\addAccount.css" rel="stylesheet" type="text/css" />
<link href="css\submenu.css" rel="stylesheet" type="text/css" />
<script src="jquery.js"></script>
<script type="text/javascript" src="js/addaccount.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
</head>
<body oncontextmenu="return false" onload="noBack();">
	<%
		HttpSession httpsession = request.getSession();
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

		<div class="content">
		<br>
		<h5 style="margin-left: 35px;">You are here: <a href="manageProfile.jsp"> Manage Profile</a> > <a href="addAccount.jsp"> Manage Beneficiary</a></h5>
			<br>
			<div class="submenu">
				<ul>
					<li class="active"><a id="links" href="">Add</a></li>
					<li><a id="links" href="viewDetails.jsp">View</a></li>
					<li><a id="links" href="modifyDetails.jsp">Modify</a></li>
					<li style="border-bottom: 1px solid #008d00;"><a id="links"
						href="deleteAccount.jsp">Delete</a></li>
				</ul>
			</div>
			<div class="data">
				<form id="addBeneficiary" action="AddBeneficiaryServlet"
					method="post">
					<table>
						<tr>
							<td>Name</td>
							<td align="center"><input type=text name="name" id="name"
								value="" placeholder="Enter Name" autocomplete="off"></td>
							<td><label flag="0" id="name_msg"></label></td>
						</tr>
						<tr>
							<td>Account Number</td>
							<td align="center"><input type=text name="accno" id="accno"
								value="" placeholder="Enter Account Number" autocomplete="off"></td>
							<td><label flag="0" id="acc_msg"></label></td>
						</tr>
						<tr>
							<td>Confirm Account Number</td>
							<td align="center"><input type=text name=confirmacc
								id="confirmacc" value="" placeholder="Retype Account Number"
								autocomplete="off"></td>
							<td><label flag="0" id="cacc_msg"></label></td>
						</tr>
						<tr>
							<td>Limit (INR)</td>
							<td align="center"><input type=text name=limit id="limit"
								value="" placeholder="Enter Limit" autocomplete="off"></td>
							<td><label flag="0" id="limit_msg"></label></td>
						</tr>
						<tr>
							<td align="center"><input type=submit value="Add" id="add"
								onclick="return validate()"></td>
							<td align="center"><input type="reset" value="Reset"></td>
						</tr>
					</table>
				</form>
				<div class="instructions">
					<p>* Ensure that you enter the correct account number</p>
					<p>* Bank does not accept responsibility for funds transferred
						inadvertently to a wrong account number input by the customer</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>