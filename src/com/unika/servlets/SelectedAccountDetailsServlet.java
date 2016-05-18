package com.unika.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.accounts.AccountsDAO;
import com.unika.accounts.AccountsService;
import com.unika.accounts.fixed.FixedDepositsDAO;
import com.unika.hibernatemapping.CustomerAccounts;

/**
 * Servlet implementation class SelectedAccountDetailsServlet
 */
@WebServlet("/SelectedAccountDetailsServlet")
public class SelectedAccountDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectedAccountDetailsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String SelectedOption = request.getParameter("option");
		HttpSession session = request.getSession();
		int customerid = (int) session.getAttribute("customerid");
		try {
			Class cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
			AccountsDAO as = (AccountsDAO) cls.newInstance();
			ArrayList<CustomerAccounts> al = as.getSelectedAccountsInformation(
					customerid, SelectedOption);
			Class cls2 = Class
					.forName("com.unika.accounts.fixed.FixedDepositsDAOImpl");
			FixedDepositsDAO fddao = (FixedDepositsDAO) cls2.newInstance();
			
			Class cls1 = Class
					.forName("com.unika.accounts.AccountsServiceImpl");
			AccountsService aser = (AccountsService) cls1.newInstance();
			
			int i = 0;
			while (i < al.size()) {
				if (al.get(i).getAccountcode() == 101) {
					out.write("<div class=\"savings\">"
							+ "<div class=\"acc_img\">"
							+ "<br> <img src=\"images/savings.jpg\" height=\"100\" width=\"130\" />"
							+ "</div>"
							+ "<div class=\"acc_type\">"
							+ "<h3>Savings Account</h3>"
							+ "<h3>Account Number: "
							+ aser.convertAccountNumber(al.get(i).getAccountnumber())
							+ "</h3>"
							+ "</div>"
							+ "<div class=\"acc_details\">"
							+ "<h3>Available Balance: Rs."
							+ al.get(i).getBalance()
							+ "</h3>"
							+ "<br>"
							+ "<a href=\"miniStatement.jsp\">Click here to view mini statement</a>"
							+ "<br>" + "</div>" + "</div>");
				}
				if (al.get(i).getAccountcode() == 102) {
					out.write("<div class=\"current\">"
							+ "<div class=\"acc_img\">"
							+ "<br> <img src=\"images/current.jpg\" height=\"100\" width=\"130\" />"
							+ "</div>" + "<div class=\"acc_type\">"
							+ "<h3>Fixed Deposits</h3>"
							+ "<h3>Account Number: "
							+ aser.convertAccountNumber(al.get(i).getAccountnumber()) + "</h3>" + "</div>"
							+ "<div class=\"acc_details\">"
							+ "<h3>Available Balance: Rs."
							+ al.get(i).getBalance() + "</h3>");
					if (fddao.getDaysToClose(customerid, al.get(i)
							.getAccountnumber()) <= 0) {
						out.write("<br>Account is Expired<br> <a href=\"DeleteFixedDeposits.jsp\">Click Here</a> to close this account");
					} else {
						out.write("<br>Remaining days to close : "
								+ fddao.getDaysToClose(customerid, al.get(i)
										.getAccountnumber()));
					}
					out.write("</div>" + "</div>");
				}
				i++;
			}
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
