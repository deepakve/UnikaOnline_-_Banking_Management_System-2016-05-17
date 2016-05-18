package com.unika.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.accounts.AccountsDAO;

/**
 * Servlet implementation class DeleteBeneficiaryServlet
 */
@WebServlet("/DeleteBeneficiaryServlet")
public class DeleteBeneficiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBeneficiaryServlet() {
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
		HttpSession session = request.getSession();
		int customerid = (int) session.getAttribute("customerid");
		long beneficiaryNumber = Long.parseLong(request
				.getParameter("beneficiaryNumber"));
		Class cls;
		try {
			cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
			AccountsDAO as = (AccountsDAO) cls.newInstance();
			if (as.deleteBeneficiary(customerid, beneficiaryNumber)) {
				response.sendRedirect("viewDetails.jsp");
			} else
				response.sendRedirect("error.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
