<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pay Bills</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\Bills.css" rel="stylesheet" type="text/css" />
<script src="jquery.js"></script>
<script type="text/javascript" src="js/Bills.js"></script>
</head>
<body>
	<%
		try {
			HttpSession httpsession = request.getSession();
			int customerid = (Integer) httpsession
					.getAttribute("customerid");
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
				<li><a href="manageProfile.jsp">Manage Profile</a></li>
				<li><a href="transferFunds.jsp">Transfer Funds</a></li>
				<li class="active"><a href="PayBills.jsp">Pay Bills</a></li>
			</ul>
		</div>

		<div class="info">
		<br>
		<h5 style="margin-left: 35px;">You are here: <a href="PayBills.jsp"> Pay Bills</a></h5>
			<br>
			<h4>Select Bill</h4>
			<ul>
				<li><input type="radio" name="Bill"
					onchange="getSelectedBillDetails()" value="electricity">
					Electricity Bill</li>
				<li><input type="radio" name="Bill"
					onchange="getSelectedBillDetails()" value="telephone">
					Mobile/Landline Bill</li>
			</ul>
			<div id="Bill"></div>
		</div>
	</div>
	<%
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>