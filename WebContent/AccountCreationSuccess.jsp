<%@page import="com.unika.hibernatemapping.RegisteredUserDetails"%>
<%@page import="com.unika.accounts.AccountsDAO"%>
<%@page import="com.unika.hibernatemapping.CustomerAccounts"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Successfully Created</title>
<link href="css\home_style.css" rel="stylesheet" type="text/css" />
<link href="css\home.css" rel="stylesheet" type="text/css" />
<link href="css\table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
</head>
<body oncontextmenu="return false" onload="noBack();">
	<%
		try {
		String custid = (String) session.getAttribute("Regcustomerid");
		@SuppressWarnings("rawtypes")
		Class cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
		AccountsDAO adao = (AccountsDAO)cls.newInstance();
		int customerid = Integer.parseInt(custid);
		CustomerAccounts ca = adao.getCustomerAccounts(customerid);
		RegisteredUserDetails rud = adao.getContactDetails(customerid);
		HttpSession sessionHttp = request.getSession();
	%>
	<div class="container">
		<div class="image">
			<img src="images\logo.png" height="70" width="150" />
		</div>

		<div class="tabbed_area">
			<ul class="tabs">
				<li><a href="" id="tab_1">Create Account</a></li>
				<li><a href="" id="tab_2">Fixed Deposites</a></li>
				<li><a href="" id="tab_3">Change Password</a></li>
				<li><a href="" id="tab_4">Add Branches</a></li>
			</ul>
		</div>
	</div>
	<div id="page">
		<div class="container">

			<!-- tab "panes" -->
			<div class="panes">
				<div id="content_1" class="content">
					<div class="login_info">

						<div class="login_details">
							<h4>New Account Successfully Created</h4>
							<form action="cust_reg.jsp" method="post">
								<table>
									<tr>
										<td>Account Number:</td>
										<td><%=ca.getAccountnumber()%></td>
									</tr>
									<tr>
										<td>Customer Id:</td>
										<td><%=ca.getCustomerid()%></td>
									</tr>
									<tr>
										<td>Password:</td>
										<td><%=session.getAttribute("randomPassword")%></td>
									</tr>
									<tr>
										<td align="center"><input type=submit value="Home"></td>
										<td></td>
									</tr>
								</table>
							</form>
						</div>

					</div>

				</div>
				<div id="content_3" class="content">
					<div class="login_info">
						<div class="login_details">
							<form action="FixedDepositeProcessorServlet" method="post">
								<div class="id">
									<a>Type:</a><br> <input type="text" name="type" id="type"
										value=" " />
								</div>
								<div class="id">
									<a>Interest:</a><br> <input type="text" name="intrest"
										id="intrest" value="" />
								</div>
								<div class="id">
									<a>Days:</a><br> <input type="text" name="days" id="days"
										value="" />
								</div>
								<input type="submit" value="continue" />
							</form>
						</div>
					</div>
				</div>
				<div id="content_4" class="content">
					<div class="login_info">
						<div class="login_details">
							<form action="AdminPasswordChangeServlet" method="post">
								<div class="id">
									<a>Admin ID:</a><br> <input type="text" name="adminid"
										id="adminid" value="" />
								</div>
								<div class="id">
									<a>Current Password:</a><br> <input type="password"
										name="currentpassword" id="currentpassword" value="" />
								</div>
								<div class="id">
									<a>New Password:</a><br> <input type="password"
										name="newpassword" id="newpassword" value="" />
								</div>
								<div class="id">
									<a>Retype Password:</a><br> <input type="password"
										name="retypepassword" id="retypepassword" value="" />
								</div>
								<input type="submit" value="Continue" />
							</form>
						</div>
					</div>
				</div>
				<div id="content_5" class="content">
					<div class="login_info">
						<div class="login_details">
							<form action="BranchDetailsInsertionServlet" method="post">
								<div class="id">
									<a>Branch Code:</a><br> <input type="text"
										name="branchcode" id="branchcode" value="" />
								</div>
								<div class="id">
									<a>Branch Address 1:</a><br> <input type="text"
										name="branchaddr1" id="branchaddr1" value="" />
								</div>
								<div class="id">
									<a>Branch Address 1:</a><br> <input type="text"
										name="branchaddr2" id="branchaddr2" value="" />
								</div>
								<div class="id">
									<a>Branch City:</a><br> <input type="text"
										name="branchcity" id="branchcity" value="" />
								</div>
								<div class="id">
									<a>Branch State:</a><br> <input type="text"
										name="branchstate" id="branchstate" value="" />
								</div>
								<div class="id">
									<a>Zip Code:</a><br> <input type="text"
										name="branchzipcode" id="branchzipcode" value="" />
								</div>
								<input type="submit" value="Continue" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		// perform JavaScript after the document is scriptable.
		$(function() {
			// setup ul.tabs to work as tabs for each div directly under div.panes
			$("ul.tabs").tabs("div.panes > div");
		});
	</script>
	<%
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>