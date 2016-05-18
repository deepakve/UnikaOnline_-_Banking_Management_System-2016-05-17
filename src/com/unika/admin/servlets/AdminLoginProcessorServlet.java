package com.unika.admin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.admin.login.AdminLoginDAO;
import com.unika.admin.login.AdminLoginDAOImpl;

/**
 * Servlet implementation class AdminLoginProcessorServlet
 */
@WebServlet("/AdminLoginProcessorServlet")
public class AdminLoginProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginProcessorServlet() {
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
		String adminpassword = request.getParameter("adminpassword");
		AdminLoginDAO ali = new AdminLoginDAOImpl();
		HttpSession session = request.getSession();
		session.setAttribute("adminid", adminid);
		int flag = ali.adminloginAuthentication(adminid, adminpassword);
		if (flag == 0)
			response.sendRedirect("cust_reg.jsp");
		if (flag == 1)
			response.sendRedirect("admin_index.jsp");

	}

}
