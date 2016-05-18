package com.unika.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.login.ForgotPasswordDAO;

/**
 * Servlet implementation class ForgotPasswordProcessorServlet2
 */
@WebServlet("/ForgotPasswordProcessorServlet2")
public class ForgotPasswordProcessorServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPasswordProcessorServlet2() {
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
			String answer1 = request.getParameter("answer1");
			String answer2 = request.getParameter("answer2");
			Class cls = Class.forName("com.unika.login.ForgotPasswordDAOImpl");
			ForgotPasswordDAO fpdao = (ForgotPasswordDAO) cls.newInstance();
			if (fpdao
					.securityAnswerAuthentication(customerid, answer1, answer2))
				response.sendRedirect("resetPassword.jsp");
			else {
				RequestDispatcher rd = request
						.getRequestDispatcher("forgotPassword2.jsp");
				request.setAttribute("errmsg", "Please check your answer");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
