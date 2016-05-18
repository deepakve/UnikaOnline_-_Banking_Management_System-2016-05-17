package com.unika.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.login.ForgotPasswordDAO;

/**
 * Servlet implementation class ForgotPasswordProcessorServlet3
 */
@WebServlet("/ForgotPasswordProcessorServlet3")
public class ForgotPasswordProcessorServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPasswordProcessorServlet3() {
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
			HttpSession session = request.getSession();
			int customerid = (int) session.getAttribute("customerid");
			String newPassword = request.getParameter("newPassword");
			Class cls = Class.forName("com.unika.login.ForgotPasswordDAOImpl");
			ForgotPasswordDAO fpdao = (ForgotPasswordDAO) cls.newInstance();
			if (fpdao.resetPassword(customerid, newPassword))
				response.sendRedirect("index.jsp");
			else
				response.sendRedirect("error.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
