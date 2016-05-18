package com.unika.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.accounts.AccountsDAO;
import com.unika.accounts.fixed.FixedDepositsDAO;
import com.unika.hibernatemapping.CustomerAccounts;

/**
 * Servlet implementation class AddFixedDepositServlet
 */
@WebServlet("/AddFixedDepositServlet")
public class AddFixedDepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFixedDepositServlet() {
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
			Class cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
			AccountsDAO adao = (AccountsDAO) cls.newInstance();
			CustomerAccounts ca = adao.getCustomerAccounts(customerid);
			long savingsNumber = ca.getAccountnumber();
			Class cls1 = Class
					.forName("com.unika.accounts.fixed.FixedDepositsDAOImpl");
			FixedDepositsDAO fdao = (FixedDepositsDAO) cls1.newInstance();
			String depositCode = request.getParameter("option");
			double amount = Double.parseDouble(request.getParameter("amount"));
			if (fdao.addFixedDeposit(customerid, savingsNumber, depositCode,
					amount)) {
				response.sendRedirect("ViewFixedDeposits.jsp");
			} else
				response.sendRedirect("error.jsp");
		} catch (Exception e) {
		}
	}
}
