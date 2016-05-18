<%@page import="com.unika.hibernatemapping.FixedDeposits"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.unika.accounts.fixed.FixedDepositsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Fixed Deposit</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\fixeddeposits.css" rel="stylesheet" type="text/css" />
<link href="css\submenu.css" rel="stylesheet" type="text/css" />
<script src="jquery.js"></script>
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
			String customername = (String) httpsession
					.getAttribute("customername");
			@SuppressWarnings("rawtypes")
			Class cls = Class
					.forName("com.unika.accounts.fixed.FixedDepositsDAOImpl");
			FixedDepositsDAO fdao = (FixedDepositsDAO) cls.newInstance();
			ArrayList<FixedDeposits> al = fdao.getDepositPolicies();
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
					<li class="active"><a id="links" href="">Add</a>
					<li><a id="links" href="ViewFixedDeposits.jsp">View</a>
					<li style="border-bottom: 1px solid #008d00;"><a id="links"
						href="DeleteFixedDeposits.jsp">Delete</a>
				</ul>
			</div>
			<div class="data">
				<form action="AddFixedDepositServlet" method="post">
					<table>
						<tr>
							<td>Select Fixed Deposit Code</td>
							<td><select name="option" width="150px;">
									<option value="">--Select Deposit Code--</option>
									<%
										int index = 0;
											while (index < al.size()) {
									%><option value="<%=al.get(index).getFixedtype()%>"><%=al.get(index).getFixedtype()%></option>
									<%
										index++;
											}
									%>
							</select></td>
						</tr>
						<tr>
							<td>Amount(INR)</td>
							<td><input type=text name="amount" value=""
								placeholder="Enter Amount" autocomplete="off"></td>
						</tr>
						<tr>
							<td align="center"><input type="submit" value="Add"></td>
							<td align="center"><input type="reset" value="Reset"></td>
						</tr>
					</table>
				</form>

				<div class="info">
					<h4>Deposit Code Information</h4>
					<table>
						<tr>
							<th>Deposit Code</th>
							<th>Number of Days</th>
							<th>Interest Rate(%)</th>
						</tr>
						<%
							index = 0;
								while (index < al.size()) {
						%>
						<tr>
							<td><%=al.get(index).getFixedtype()%></td>
							<td><%=al.get(index).getDays()%></td>
							<td><%=al.get(index).getIntrest()%></td>
						</tr>

						<%
							index++;
								}
						%>
					</table>
				</div>
				<div class="instructions">
					<p>* Verify Code Details in the above table before adding Fixed
						Deposit</p>
				</div>
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