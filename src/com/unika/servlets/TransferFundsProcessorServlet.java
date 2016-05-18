package com.unika.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.transactions.TransactionDAO;

/**
 * Servlet implementation class TransferFundsProcessorServlet
 */
@WebServlet("/TransferFundsProcessorServlet")
public class TransferFundsProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferFundsProcessorServlet() {
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
			RequestDispatcher rd;
			HttpSession session = request.getSession();
			int customerid = (Integer) session.getAttribute("customerid");
			long toAccountNumber = (Long) session
					.getAttribute("toAccountNubmer");
			double amount = (Double) session.getAttribute("amount");
			String passwordConformation = request.getParameter("password");
			Class cls = Class
					.forName("com.unika.transactions.TransactionDAOImpl");
			TransactionDAO tdao = (TransactionDAO) cls.newInstance();
			if (tdao.passwordAuthentication(customerid, passwordConformation)) {
				long withAccountNumber = tdao.getAccountNumber(customerid);
				if (tdao.transferFunds(customerid, withAccountNumber,
						toAccountNumber, amount))
					response.sendRedirect("acc_home.jsp");
				else {
					rd = request.getRequestDispatcher("transferFunds.jsp");
					request.setAttribute("errmsgbalance",
							"Sorry you have insufficient balance");
					rd.forward(request, response);
				}

			} else {
				rd = request.getRequestDispatcher("SecurePassword.jsp");
				request.setAttribute("errmsgsecure", "Wrong Password");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
