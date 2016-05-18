package com.unika.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.accounts.fixed.FixedDepositsDAO;

/**
 * Servlet implementation class DeleteFixedDepositServlet
 */
@WebServlet("/DeleteFixedDepositServlet")
public class DeleteFixedDepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteFixedDepositServlet() {
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
		long depositNumber = Long.parseLong(request.getParameter("account"));
		try {
			Class cls = Class
					.forName("com.unika.accounts.fixed.FixedDepositsDAOImpl");
			FixedDepositsDAO fddao = (FixedDepositsDAO) cls.newInstance();
			if (fddao.cancelFixedDeposit(customerid, depositNumber))
				response.sendRedirect("DeleteFixedDeposits.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
