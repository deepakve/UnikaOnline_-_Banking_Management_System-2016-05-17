<%@page import="com.unika.hibernatemapping.RegisteredUserDetails"%>
<%@page import="com.unika.login.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Security Questions to reset password</title>
<link href="css\home_style.css" rel="stylesheet" type="text/css" />
<link href="css\home.css" rel="stylesheet" type="text/css" />
<link href="css\firstTimeAccess.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/disableRightClick.js"></script>
<script type="text/javascript" src="js/noback.js"></script>
<script type="text/javascript" src="js/forgotpwd2.js"></script>
</head>
<body oncontextmenu="return false" onload="noBack();">
	<%
		try {
			HttpSession httpsession = request.getSession();
			int customerid = (Integer) httpsession
					.getAttribute("customerid");
			@SuppressWarnings("rawtypes")
			Class cls = Class.forName("com.unika.login.LoginDAOImpl");
			LoginDAO ldao = (LoginDAO) cls.newInstance();
			RegisteredUserDetails rud = ldao
					.getSecutityQuestions(customerid);
	%>
	<div class="container">
		<div class="image">
			<img src="images\logo.png" height="70" width="150" />
		</div>
	</div>
	<div id="page">
		<div class="container">
			<div class="panes">
				<div id="content_1" class="content">
					<div class="login_info">
						<div class="first_time">
							<h1>Everyone forgets password!</h1>
							<h6>Please fill in the following details.</h6>
							<div class="login_details">
								<form action="ForgotPasswordProcessorServlet2" method="post">
									<div>
										<%
											if (request.getAttribute("errmsg") != null) {
										%>
										<p style="color: red; font-style: italic;"><%=request.getAttribute("errmsg")%></p>
										<%
											} else {
												}
										%>
									</div>
									<div class="pwd">
										<label for="answer1"></label> <a><%=rud.getQuestion1()%></a> <br>
										<a>Answer:</a> <input type="text" name="answer1" id="answer1"
											value="" style="height: 15px; width: 200px"
											placeholder="Enter the answer" autocomplete="off" /> <label
											flag="0" id="ans1_msg"></label>
									</div>
									<br> <br>
									<div class="pwd">
										<label for="answer2"></label> <a><%=rud.getQuestion2()%></a> <br>
										<a>Answer:</a> <input type="text" name="answer2" id="answer2"
											value="" style="height: 15px; width: 200px"
											placeholder="Enter the answer" autocomplete="off" /> <label
											flag="0" id="ans2_msg"></label>
									</div>


									<input type="submit" value="Continue"
										onclick="return validate()" />
								</form>
							</div>
						</div>
					</div>
					<div>
						<br> <br> <br> <br> <img
							src="images\answer.png" height="300" width="300" />
					</div>
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