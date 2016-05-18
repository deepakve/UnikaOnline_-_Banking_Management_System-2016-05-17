<%@page import="com.unika.utilities.ResponsibilityService"%>
<%@page import="com.unika.hibernatemapping.TransactionHistory"%>
<%@page import="com.unika.hibernatemapping.CustomerAccounts"%>
<%@page import="com.unika.accounts.AccountsService"%>
<%@page import="com.unika.transactions.TransactionDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.unika.accounts.AccountsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mini Statement</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\miniStatement.css" rel="stylesheet" type="text/css" />
<script src="jquery.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
</head>
<body oncontextmenu="return false" onload="noBack();">
	<%
		try {
			HttpSession httpsession = request.getSession();
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
			Class cls2 = Class
					.forName("com.unika.transactions.TransactionDAOImpl");
			TransactionDAO tdao = (TransactionDAO) cls2.newInstance();
			@SuppressWarnings("rawtypes")
			Class cls1 = Class
					.forName("com.unika.accounts.AccountsServiceImpl");
			AccountsService aser = (AccountsService) cls1.newInstance();
			long accountnumber = al.get(0).getAccountnumber();
			ArrayList<TransactionHistory> al_tdao = tdao
					.miniStatement(accountnumber);
			@SuppressWarnings("rawtypes")
			Class cls3 = Class
					.forName("com.unika.utilities.ResponsibilityServiceImpl");
			ResponsibilityService rs = (ResponsibilityService) cls3
					.newInstance();
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
			<table>
				<tr>
					<td>Name:</td>
					<td><%=customername%></td>
				</tr>
				<tr>
					<td>Account Number:</td>
					<td><%=aser.convertAccountNumber(accountnumber)%></td>
				</tr>
				<tr>
					<td>Available Balance (INR):</td>
					<td><%=al.get(0).getBalance()%></td>
				</tr>
			</table>
			<div class="transaction">
				<h4>Account Mini Statement</h4>
				<table>
					<tr>
						<th>Date</th>
						<th>Narration</th>
						<th>Transaction ID</th>
						<th>Debit</th>
						<th>Credit</th>
					</tr>
					<%
						int index = 0;
							if (al_tdao.size() == 0) {
					%><tr>
						<td colspan="5"><center>
								No transactions were made till now. <a href="transferFunds.jsp">Click
									Here</a> to make Transactions
							</center></td>
					<tr>
						<%
							} else {
									while (index < al_tdao.size() && index < 10) {
										if (al_tdao.get(index).getWithtransactioncode()
												.equals("002")
												&& al_tdao.get(index).getWithaccountnumber() == accountnumber) {
						%>
					
					<tr>
						<td><center><%=al_tdao.get(index).getTimestamp()%></center></td>
						<td><center>
								Transfer to
								<%=aser.convertAccountNumber(al_tdao.get(
									index).getToaccountnumber())%>,
								<%=tdao.getBenficiaryName(customerid,
									al_tdao.get(index).getToaccountnumber())%></center></td>
						<td><center><%=al_tdao.get(index).getTxnid()%></center></td>
						<td><center><%=al_tdao.get(index).getAmount()%></center></td>
						<td></td>
					</tr>
					<%
						}
									if (al_tdao.get(index).getTotransactioncode()
											.equals("001")
											&& al_tdao.get(index).getToaccountnumber() == accountnumber) {
					%><tr>
						<td><center><%=al_tdao.get(index).getTimestamp()%></center></td>
						<td><center>
								Transfer from
								<%=aser.convertAccountNumber(al_tdao.get(
									index).getWithaccountnumber())%>
								<%
									if (tdao.getBenficiaryName(customerid,
															al_tdao.get(index).getWithaccountnumber()) == null) {
													} else {
								%>
								<%=tdao.getBenficiaryName(customerid,
										al_tdao.get(index)
												.getWithaccountnumber())%>
								<%
									}
								%>
							</center></td>
						<td><center><%=al_tdao.get(index).getTxnid()%></center></td>
						<td><center></center></td>
						<td><center><%=al_tdao.get(index).getAmount()%></center></td>
					</tr>
					<%
						}
									if (al_tdao.get(index).getWithtransactioncode()
											.equals("003")
											&& al_tdao.get(index).getWithaccountnumber() == accountnumber) {
					%><tr>
						<td><center><%=al_tdao.get(index).getTimestamp()%></center></td>
						<td><center>
								Fixed Deposited
								<%=aser.convertAccountNumber(al_tdao.get(
									index).getToaccountnumber())%>
								opened
							</center></td>
						<td><center><%=al_tdao.get(index).getTxnid()%></center></td>
						<td><center><%=al_tdao.get(index).getAmount()%></center></td>
						<td><center></center></td>
					</tr>
					<%
						}
									if (al_tdao.get(index).getWithtransactioncode()
											.equals("004")
											&& al_tdao.get(index).getToaccountnumber() == accountnumber) {
					%><tr>
						<td><center><%=al_tdao.get(index).getTimestamp()%></center></td>
						<td><center>
								Fixed Deposit
								<%=aser.convertAccountNumber(al_tdao.get(
									index).getWithaccountnumber())%>
								is closed/expired
							</center></td>
						<td><center><%=al_tdao.get(index).getTxnid()%></center></td>
						<td><center></center></td>
						<td><center><%=al_tdao.get(index).getAmount()%></center></td>
					</tr>
					<%
						}
									if (al_tdao.get(index).getWithtransactioncode()
											.equals("005")) {
					%>
					<tr>
						<td><center><%=al_tdao.get(index).getTimestamp()%></center></td>
						<td><center>
								Paid bill of phone number
								<%=al_tdao.get(index).getToaccountnumber()%>
							</center></td>
						<td><center><%=al_tdao.get(index).getTxnid()%></center></td>
						<td><center><%=al_tdao.get(index).getAmount()%></center></td>
						<td><center></center></td>
					</tr>
					<%
						}
									if (al_tdao.get(index).getWithtransactioncode()
											.equals("006")) {
					%>
					<tr>
						<td><center><%=al_tdao.get(index).getTimestamp()%></center></td>
						<td><center>
								Paid Electricity Bill Number
								<%=al_tdao.get(index).getToaccountnumber()%>
							</center></td>
						<td><center><%=al_tdao.get(index).getTxnid()%></center></td>
						<td><center><%=al_tdao.get(index).getAmount()%></center></td>
						<td><center></center></td>
					</tr>
					<%
						}
									index++;
								}
							}
					%>
				</table>
			</div>
		</div>
	</div>
	<%
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>