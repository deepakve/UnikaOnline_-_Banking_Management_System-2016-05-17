<%@page import="com.unika.accounts.AccountsService"%>
<%@page import="com.unika.hibernatemapping.BeneficiaryDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.unika.accounts.AccountsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Account</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\modifyDetails.css" rel="stylesheet" type="text/css" />
<link href="css\submenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js\ModifyDetailsScript.js"></script>
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
			ArrayList<BeneficiaryDetails> al = as
					.viewBeneficiary(customerid);
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
					<li><a id="links" href="addAccount.jsp">Add</a>
					<li><a id="links" href="viewDetails.jsp">View</a>
					<li class="active"><a id="links" href="modifyDetails.jsp">Modify</a>
					<li style="border-bottom: 1px solid #008d00;"><a id="links"
						href="deleteAccount.jsp">Delete</a>
				</ul>
			</div>
			<div class="data">
				<h4>Select Account Number</h4>
				<form action="ModifyBeneficiaryServlet" method="post">
					<div class="accounts">
						<table>
							<tr>
								<th>Account Number</th>
								<th>Name</th>
								<th>Transfer Limit</th>
							</tr>
							<%
								int index = 0;
									if (al.size() == 0) {
							%><tr>
								<td colspan="3"><center>
										There are no beneficiary members listed in your account.<br>
										<a href="addAccount.jsp">Click Here</a> to add beneficiary
										members
									</center></td>
							<tr>
								<%
									} else {
											while (index < al.size()) {
								%>
							
							<tr>
								<td><input type="radio" name="account"
									onchange="getSelectedOneDetails()"
									value="<%=al.get(index).getBeneficiaryNumber()%>"> <%=aser.convertAccountNumber(al.get(index)
								.getBeneficiaryNumber())%></td>
								<td><%=al.get(index).getNickName()%></td>
								<td><%=al.get(index).getLimit()%></td>
							</tr>
							<%
								index++;
										}
							%>
						</table>
					</div>
					<div id="Selected">
						<table class="modify">
							<tr>
								<td>Name</td>
								<td align="center"><input type=text name=name value=""
									autocomplete="off"></td>
							</tr>
							<tr>
								<td>Account Number</td>
								<td align="center"><input type=text name=accno value=""
									readonly></td>
							</tr>
							<tr>
								<td>Limit (INR)</td>
								<td align="center"><input type=text name=limit value=""
									autocomplete="off"></td>
							</tr>
							<tr>
								<td align="center"><input type=submit value="Modify"
									id="add" onclick="return validate()"></td>
								<td align="center"><input type=button value="Cancel"></td>
							</tr>
							<%
								}
							%>
						</table>
					</div>
				</form>
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