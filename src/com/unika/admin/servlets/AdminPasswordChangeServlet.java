package com.unika.admin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unika.admin.login.ChangePasswordDAO;
import com.unika.admin.login.ChangePasswordDAOImpl;

/**
 * Servlet implementation class AdminPasswordChangeServlet
 */
@WebServlet("/AdminPasswordChangeServlet")
public class AdminPasswordChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPasswordChangeServlet() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String adminid = request.getParameter("adminid");
		String currentpassword = request.getParameter("currentpassword");
		String newpassword = request.getParameter("newpassword");
		ChangePasswordDAO cpdi = new ChangePasswordDAOImpl();
		cpdi.changeAdminPassword(adminid, currentpassword, newpassword);
		response.sendRedirect("cust_reg.jsp");
	}
}
