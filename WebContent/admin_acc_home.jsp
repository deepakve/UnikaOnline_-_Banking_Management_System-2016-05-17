<%@ page language="java" session="true" %>
<%
String custid=(String)session.getAttribute("Regcustomerid");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>ADMIN HOME</title>
<link href="css\home_style.css" rel="stylesheet" type="text/css" />
<link href="css\home.css" rel="stylesheet" type="text/css" />
<link href="css\firstTimeAccess.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
</head>
<body oncontextmenu="return false" onload="noBack();">

	<div class="container">
		<div class="image">
			<img src="images\logo.png" height="70" width="150" />
			<h5 style="margin-left:1100px;">
				<a href="logout.jsp">Logout</a>
			</h5>
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
					<div class="login_info" style="margin-top: 20px;">						
						<div class="login_details" style="margin-top: 0px;">
							<form action="AdminCreateProcessorServlet" method="post">
								<table>
								<tr>
									<td>
										<ul class="field">
											<li><h4>Customer Id :</h4></li>
											<li><input type="text" name="customerid" id="customerid" <%out.println("value="+"'"+custid+"'"); %> readonly/></li>
										
											<li><h4>First Name :</h4></li>
											<li><input type="text" name="first_name" id="first_name" value="" /></li>
											
											<li><h4>Last Name :</h4></li>
											<li><input type="text" name="last_name" id="last_name" value="" /><li>
											
											<li><h4>Date of Birth :</h4></li>
											<li><input type="date" name="dob" id="dob" value="" /></li>
											
											<br><br><br><br>
										
											<li><input type="submit" value="Continue" style="margin-left:130px;"/></li>
										</ul>										
									</td>
									<td>
										<ul class="field">
											<li><h4>Address1 :</h4></li>
											<li><input type="text" name="address_1" id="address_1" value="" /></li>
											
											<li><h4>Address2 :</h4></li>
											<li><input type="text" name="address_2" id="address_2" value="" /></li>
											
											<li><h4>City :</h4></li>
											<li><input type="text" name="city" id="city" value="" /></li>
											
											<li><h4>State :</h4></li>
											<li><input type="text" name="state" id="state" value="" /></li>
										
											<li><h4>ZipCode :</h4></li>
											<li><input type="text" name="zipcode" id="zipcode" value="" /></li>
										
											<li><input type="reset" value="Reset" /></li>
										</ul>										
									</td>
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
			<a>Type:</a><br><input type="text" name="type" id="type" value="" /></div>
			<div class="id">
			<a>Interest:</a><br><input type="text" name="intrest" id="intrest" value="" /></div>
			<div class="id">
			<a>Days:</a><br><input type="text" name="days" id="days" value="" /></div>
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
									<a>Admin ID:</a><br> <input type="text"
										name="adminid" id="adminid" value="" />
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
</body>
</html>
