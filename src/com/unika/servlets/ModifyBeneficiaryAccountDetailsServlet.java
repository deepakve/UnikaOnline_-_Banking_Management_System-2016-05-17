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
import com.unika.hibernatemapping.BeneficiaryDetails;

/**
 * Servlet implementation class ModifyAccountDetailsServlet
 */
@WebServlet("/ModifyAccountDetailsServlet")
public class ModifyBeneficiaryAccountDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyBeneficiaryAccountDetailsServlet() {
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
			String SelectedOption = request.getParameter("account");
			HttpSession session = request.getSession();
			int customerid = (int) session.getAttribute("customerid");
			Class cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
			AccountsDAO adao = (AccountsDAO) cls.newInstance();
			ArrayList<BeneficiaryDetails> al = adao.getSelectedBeneficiary(
					customerid, SelectedOption);
			out.println("<table class=\"modify\">"
					+ "<tr>"
					+ "<td>Name</td>"
					+ "<td align=\"center\"><input type=text name=name value=\""
					+ al.get(0).getNickName()
					+ "\" autocomplete=\"off\"></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Account Number</td>"
					+ "<td align=\"center\"><input type=text name=accno value=\""
					+ al.get(0).getBeneficiaryNumber()
					+ "\" readonly></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Limit (INR)</td>"
					+ "<td align=\"center\"><input type=text name=limit value=\""
					+ al.get(0).getLimit()
					+ "\" autocomplete=\"off\"></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td align=\"center\"><input type=submit value=\"Modify\"	id=\"add\"></td>"
					+ "<td align=\"center\"><input type=reset value=\"Reset\"></td>"
					+ "</tr>" + "</table>");
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
