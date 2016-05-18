<%@page import="com.unika.accounts.AccountsService"%>
<%@page import="com.unika.hibernatemapping.FixedDepositAccounts"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.unika.accounts.fixed.FixedDepositsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view Fixed Deposit</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\fixeddeposits.css" rel="stylesheet" type="text/css" />
<link href="css\submenu.css" rel="stylesheet" type="text/css" />
<script src="jquery.js"></script>
<script type="text/javascript" src="js\ViewDepositDetails.js"></script>
<script type="text/javascript">
	window.history.forward(1);
	function noBack() {
		window.history.forward();
	}
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<%
		try {
			HttpSession httpsession = request.getSession();
			int customerid = (Integer) httpsession
					.getAttribute("customerid");
			String customername = (String) httpsession
					.getAttribute("customername");
			@SuppressWarnings("rawtypes")
			Class cls = Class
					.forName("com.unika.accounts.fixed.FixedDepositsDAOImpl");
			FixedDepositsDAO fdao = (FixedDepositsDAO) cls.newInstance();
			ArrayList<FixedDepositAccounts> al = fdao
					.viewFixedDeposits(customerid);
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

		<div class="content">
		<br>
		<h5 style="margin-left: 35px;">You are here: <a href="manageProfile.jsp"> Manage Profile</a> > <a href="AddFixedDeposits.jsp"> Manage Fixed Deposits</a></h5>
			<br>
			<div class="submenu">
				<ul>
					<li><a id="links" href="AddFixedDeposits.jsp">Add</a>
					<li class="active"><a id="links" href="">View</a>
					<li style="border-bottom: 1px solid #008d00;"><a id="links"
						href="DeleteFixedDeposits.jsp">Delete</a>
				</ul>
			</div>
			<div class="data">
				<div class="AccountNumber">
					<%
						if (al.size() == 0) {
					%>
					<h4>
						<center>There are no fixed deposits</center>
					</h4>
					<%
						} else {
					%>
					<h4>Select Account Number</h4>
					<table>
						<%
							int index = 0;
									while (index < al.size()) {
						%>
						<tr>
							<td><input type="radio" name="account"
								onchange="getSelectedOneDetails()"
								value="<%=al.get(index).getAccountNumber()%>"><%=aser.convertAccountNumber(al.get(index)
								.getAccountNumber())%></td>
						</tr>
						<%
							index++;
									}
						%>
					</table>
				</div>
				<div id="details"></div>
			</div>
		</div>
	</div>
	<%
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>