package com.unika.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.login.LoginDAO;

@WebServlet("/LoginProcessorServlet")
public class LoginProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginProcessorServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int customerid = Integer.parseInt(request
					.getParameter("customerid"));
			String password = request.getParameter("password");
			Class cls = Class.forName("com.unika.login.LoginDAOImpl");
			LoginDAO li = (LoginDAO) cls.newInstance();
			HttpSession session = request.getSession();
			int flag = li.loginAuthentication(customerid, password);
			if (flag == 0) {
				session.setAttribute("customerid", customerid);
				String customername = li.getName(customerid);
				session.setAttribute("customername", customername);
				response.sendRedirect("acc_home.jsp");
			}
			if (flag == 1) {
				session.setAttribute("customerid", customerid);
				response.sendRedirect("fta.jsp");
			}
			if (flag == 2) {
				RequestDispatcher rd = request
						.getRequestDispatcher("index.jsp");
				request.setAttribute("errmsglogin",
						"Sorry entered login details doesn't match");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
