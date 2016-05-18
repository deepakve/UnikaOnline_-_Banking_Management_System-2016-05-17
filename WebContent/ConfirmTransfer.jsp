<%@page import="com.unika.accounts.AccountsService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>High Security Password</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\confirmTransfer.css" rel="stylesheet" type="text/css" />
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
		long toAccountNumber = (Long) httpsession
				.getAttribute("toAccountNubmer");
		double amount = (Double) httpsession.getAttribute("amount");
		String beneficiaryName = (String) httpsession
				.getAttribute("beneficiaryName");
		@SuppressWarnings("rawtypes")
		Class cls1 = Class
				.forName("com.unika.accounts.AccountsServiceImpl");
		AccountsService aser = (AccountsService) cls1.newInstance();
	%>
	<div class="container">
		<img src="images\logo.png" height="50" width="100"
			style="width: 152px;" />
		<h4>
			Hello
			<%=customername%></h4>
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
			<form action="SecurePassword.jsp">
				<table style="color: #A4A4A4;">
					<tr>
						<td>Name:</td>
						<td><%=beneficiaryName%></td>
					</tr>
					<tr>
						<td>Account Number:</td>
						<td><%=aser.convertAccountNumber(toAccountNumber)%></td>
					</tr>
					<tr>
						<td>Amount to be transferred:</td>
						<td><%=amount%></td>
					</tr>
					<tr>
						<td align="center"><input type=submit value="Confirm"></td>
						<td align="center"><a href="transferFunds.jsp"><input
								type=button value="Cancel"></a></td>
					</tr>
				</table>
			</form>
			<div class="instructions">
				<p>* Please check the details once before Confirm Transfer</p>
				<p>* Bank does not accept responsibility for funds transferred
					inadvertently to a wrong account number input by the customer</p>
			</div>
		</div>
	</div>
</body>
</html>