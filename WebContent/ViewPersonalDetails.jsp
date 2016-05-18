<%@page import="com.unika.hibernatemapping.RegisteredUserDetails"%>
<%@page import="com.unika.hibernatemapping.CustomerDetails"%>
<%@page import="com.unika.accounts.AccountsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Personal Details</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\ViewPersonalDetails.css" rel="stylesheet"
	type="text/css" />
<script src="jquery.js"></script>
<script type="text/javascript" src="js\ViewPersonalDetailsScript.js"></script>
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
			AccountsDAO adao = (AccountsDAO) cls.newInstance();
			CustomerDetails cd = adao.getCustomerDetails(customerid);
			RegisteredUserDetails rud = adao.getContactDetails(customerid);
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
		<div class="details">
		<br>
		<h5 style="margin-left: 35px;">You are here: <a href="manageProfile.jsp"> Manage Profile</a> > <a href="ViewPersonalDetails.jsp"> Personal Details</a></h5>
			<br>
			<h4>Personal Details</h4>
			<form action="ModifyPersonalDetailsServlet" method="post">
				<table>
					<tr>
						<td>First Name :</td>
						<td><input id="fname" type="text" name="fname"
							value="<%=cd.getFirstname()%>" readonly="readonly" />&nbsp&nbsp&nbsp<a
							href="javascript:activateTextbox1()">Edit...</a></td>
					</tr>
					<tr>
						<td>Last Name :</td>
						<td><input id="lname" type="text" name="lname"
							value="<%=cd.getLastname()%>" readonly="readonly" />&nbsp&nbsp&nbsp<a
							href="javascript:activateTextbox2()">Edit...</a></td>
					</tr>
					<tr>
						<td>Email-Id :</td>
						<td><input id="email" type="text" name="email"
							value="<%=rud.getEmail()%>" readonly="readonly" />&nbsp&nbsp&nbsp<a
							href="javascript:activateTextbox3()">Edit...</a></td>
					</tr>
					<tr>
						<td>Contact No :</td>
						<td><input id="mobile" type="text" name="mobile"
							value="<%=rud.getContact()%>" readonly="readonly" />&nbsp&nbsp&nbsp<a
							href="javascript:activateTextbox4()">Edit...</a></td>
					</tr>
					<tr>
						<td>Date Of Birth :</td>
						<td><%=cd.getDob()%></td>
					</tr>
					<tr>
						<td>Address :</td>
						<td><%=cd.getAddress1()%></td>
					</tr>
					<tr>
						<td></td>
						<td><%=cd.getAddress2()%></td>
					</tr>
					<tr>
						<td>City :</td>
						<td><%=cd.getCity()%></td>
					</tr>
					<tr>
						<td>State :</td>
						<td><%=cd.getState()%></td>
					</tr>
					<tr>
						<td>Zipcode :</td>
						<td><%=cd.getZipcode()%></td>
					</tr>
					<tr>
						<td align="center"><input type=submit value="Save"></td>
						<td align="center"><input type=reset value="Cancel"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>