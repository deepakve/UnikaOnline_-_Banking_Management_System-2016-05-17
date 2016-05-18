package com.unika.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.accounts.fixed.FixedDepositsDAO;
import com.unika.hibernatemapping.FixedDepositAccounts;

/**
 * Servlet implementation class ViewFixedDepositDetailsServlet
 */
@WebServlet("/ViewFixedDepositDetailsServlet")
public class ViewFixedDepositDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewFixedDepositDetailsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			int customerid = (int) session.getAttribute("customerid");
			long SelectedOption = Long.parseLong(request
					.getParameter("account"));
			Class cls = Class
					.forName("com.unika.accounts.fixed.FixedDepositsDAOImpl");
			FixedDepositsDAO fddao = (FixedDepositsDAO) cls.newInstance();
			FixedDepositAccounts fda = fddao.getSelectedDepositDetails(
					customerid, SelectedOption);
			int days = fddao.getDaysToClose(customerid, SelectedOption);
			System.out.println("hello");
			fddao.maturityDateCalculator(days);
			double intrest = 0;
			for (int i = 0; i < fddao.getDepositPolicies().size(); i++) {
				if (fda.getDepositTypeCode().equals(
						fddao.getDepositPolicies().get(i).getFixedtype()))
					intrest = fddao.getDepositPolicies().get(i).getIntrest();
			}
			out.println("<h4>Details</h4>"
					+ "<table>"
					+ "<tr>"
					+ "<td>Account Number :</td>" 
					+ "<td>"
					+ fda.getAccountNumber()
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Deposit Amount(INR):</td>"
					+ "<td>"
					+ fda.getAmount()
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Interest Rate (%) :</td>"
					+ "<td>"
					+ intrest
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Created On:</td>"
					+ "<td>"
					+ fda.getCreatedon()
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Matured On:</td>"
					+ "<td>"
					+ fddao.maturityDateCalculator(days)
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Days Remaining:</td>"
					+ "<td>"
					+ fddao.getDaysToClose(customerid, SelectedOption)
					+ "</td>" + "</tr>" + "</table>" + "</div>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
