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
 * Servlet implementation class ForgotPasswordProcessorServlet
 */
@WebServlet("/ForgotPasswordProcessorServlet1")
public class ForgotPasswordProcessorServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPasswordProcessorServlet1() {
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
			String contact = request.getParameter("contactNo");
			String email = request.getParameter("emailid");
			HttpSession session = request.getSession();
			session.setAttribute("customerid", customerid);
			Class cls = Class.forName("com.unika.login.ForgotPasswordDAOImpl");
			ForgotPasswordDAO fpdao = (ForgotPasswordDAO) cls.newInstance();
			if (fpdao.getSecurityQuestions(customerid, contact, email))
				response.sendRedirect("forgotPassword2.jsp");
			else
				response.sendRedirect("forgotPassword1.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
