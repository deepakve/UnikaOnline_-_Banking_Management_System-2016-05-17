package com.unika.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.transactions.TransactionDAO;

/**
 * Servlet implementation class TransactionProcessorServlet
 */
@WebServlet("/TransactionProcessorServlet")
public class TransactionProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransactionProcessorServlet() {
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
			long toAccountNumber = Long.parseLong(request
					.getParameter("toAccountNumber"));
			double amount = Double.parseDouble(request.getParameter("amount"));
			session.setAttribute("toAccountNubmer", toAccountNumber);
			session.setAttribute("amount", amount);
			Class cls = Class.forName("com.unika.transactions.TransactionDAOImpl");
			TransactionDAO tdao = (TransactionDAO) cls.newInstance();
			String beneficiaryName = tdao.getBenficiaryName(customerid,
					toAccountNumber);
			session.setAttribute("beneficiaryName", beneficiaryName);
			response.sendRedirect("ConfirmTransfer.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
