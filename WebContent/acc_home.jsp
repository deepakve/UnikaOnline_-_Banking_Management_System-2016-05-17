<%@page import="com.unika.accounts.fixed.FixedDepositsDAO"%>
<%@page import="com.unika.accounts.AccountsService"%>
<%@page import="com.unika.hibernatemapping.CustomerAccounts"%>
<%@page import="com.unika.hibernatemapping.CustomerDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.unika.accounts.AccountsDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account Home</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\acc_info.css" rel="stylesheet" type="text/css" />
<script src="jquery.js"></script>
<script type="text/javascript" src="js\AccountHomeScript.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
</head>
<body oncontextmenu="return false" onload="noBack();">
	<%
		HttpSession httpsession = request.getSession();
		try {
			int customerid = (Integer) httpsession
					.getAttribute("customerid");
			String customername = (String) httpsession
					.getAttribute("customername");
			@SuppressWarnings("rawtypes")
			Class cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
			AccountsDAO as = (AccountsDAO) cls.newInstance();
			ArrayList<CustomerAccounts> al = as
					.getAccountsInformation(customerid);
			@SuppressWarnings("rawtypes")
			Class cls1 = Class
					.forName("com.unika.accounts.AccountsServiceImpl");
			AccountsService aser = (AccountsService) cls1.newInstance();
			@SuppressWarnings("rawtypes")
			Class cls2 = Class
					.forName("com.unika.accounts.fixed.FixedDepositsDAOImpl");
			FixedDepositsDAO fddao = (FixedDepositsDAO) cls2.newInstance();
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
				<li class="active"><a href="acc_home.jsp">Account Info</a></li>
				<li><a href="manageProfile.jsp">Manage Profile</a></li>
				<li><a href="transferFunds.jsp">Transfer Funds</a></li>
				<li><a href="PayBills.jsp">Pay Bills</a></li>
			</ul>
		</div>

		<div class="info">
			<h4 style="margin-left: 30px;">You are here: <a href="acc_home.jsp">Account Info</a></h4>
			<div class="type">
				<table class="view_acc">
					<tr>
						<td>Select Account Type :</td>
						<td><select name="option" id="AccountSelector"
							onchange="getSelectedAccountDetails()">
								<option value="All">All Accounts</option>
								<%
									int x = 0, i = 0;
										while (i < al.size()) {
											if (al.get(i).getAccountcode() == 101) {
								%><option value="savings">Savings Account</option>
								<%
									}
											if (al.get(i).getAccountcode() == 102) {
												if (x == 0) {
								%><option value="fixed">Fixed Deposits</option>
								<%
									x = 1;
												}
											}
											i++;
										}
								%>
						</select></td>
					</tr>
				</table>
			</div>
			<div id="accounts">
				<%
					i = 0;
						while (i < al.size()) {
							if (al.get(i).getAccountcode() == 101) {
				%>
				<div class="savings">
					<div class="acc_img">
						<br> <img src="images/savings.jpg" height="100" width="130" />
					</div>
					<div class="acc_type">
						<h3>Savings Account</h3>
						<h3>
							Account Number :
							<%=aser.convertAccountNumber(al.get(i)
								.getAccountnumber())%>
						</h3>
					</div>
					<div class="acc_details">
						<h3>
							Available Balance: Rs.<%=al.get(i).getBalance()%></h3>
						<br> <a href="miniStatement.jsp">Click here to view mini
							statement</a>
					</div>
				</div>
				<%
					}
							if (al.get(i).getAccountcode() == 102) {
				%>
				<div class="current">
					<div class="acc_img">
						<br> <img src="images/current.jpg" height="100" width="130" />
					</div>
					<div class="acc_type">
						<h3>Fixed Deposits</h3>
						<h3>
							Account Number :
							<%=aser.convertAccountNumber(al.get(i)
								.getAccountnumber())%></h3>
					</div>
					<div class="acc_details">
						<h3>
							Available Balance: Rs.<%=al.get(i).getBalance()%></h3>
						<%
							if (fddao.getDaysToClose(customerid, al.get(i)
												.getAccountnumber()) <= 0) {
						%><br>Account is Expired<br> <a
							href="DeleteFixedDeposits.jsp">Click Here</a> to close this
						account
						<%
 	} else {
 %>
						<br>Remaining days to close :
						<%=fddao.getDaysToClose(customerid, al
									.get(i).getAccountnumber())%>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							i++;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>