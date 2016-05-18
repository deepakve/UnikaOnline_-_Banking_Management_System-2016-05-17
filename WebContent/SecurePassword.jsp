<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>High Security Password</title>
<link href="css\gen_style.css" rel="stylesheet" type="text/css" />
<link href="css\password.css" rel="stylesheet" type="text/css" />
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

		<div>
			<form action="TransferFundsProcessorServlet" method="post">
				<%
					if (request.getAttribute("errmsgsecure") != null) {
				%>
				<p style="color: red; font-style: italic;"><%=request.getAttribute("errmsgsecure")%></p>
				<%
					}
				%>
				<table>
					<tr>
						<td>Enter High Security Password:</td>
						<td><input type="password" name="password" value="" /></td>
					</tr>
					<tr>
						<td align="center"><input type=submit value="Submit"></td>
						<td align="center"><input type=reset value="Reset"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>