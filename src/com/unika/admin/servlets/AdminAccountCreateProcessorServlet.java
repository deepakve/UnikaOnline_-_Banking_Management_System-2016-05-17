package com.unika.admin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.admin.accounts.CustomerAccountsDAOImpl;
import com.unika.hibernatemapping.CustomerAccounts;

/**
 * Servlet implementation class AdminAccountCreateProcessorServlet
 */
@WebServlet("/AdminAccountCreateProcessorServlet")
public class AdminAccountCreateProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAccountCreateProcessorServlet() {
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
		CustomerAccounts ca = new CustomerAccounts();
		HttpSession session = request.getSession();
		int customerid = Integer.parseInt((String) session
				.getAttribute("Regcustomerid"));
		ca.setCustomerid(customerid);
		ca.setAccountnumber(Long.parseLong(request
				.getParameter("accountnumber")));
		ca.setAccountcode(Integer.parseInt(request.getParameter("acc_type")));
		ca.setBranchcode(request.getParameter("branch_code"));
		ca.setBalance(Double.parseDouble(request.getParameter("amount")));
		CustomerAccountsDAOImpl cad = new CustomerAccountsDAOImpl();
		cad.saveCustomerAccounts(ca);
		response.sendRedirect("AccountCreationSuccess.jsp");
	}

}
