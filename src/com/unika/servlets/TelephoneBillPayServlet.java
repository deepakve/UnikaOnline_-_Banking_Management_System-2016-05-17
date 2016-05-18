package com.unika.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.billpay.BillPayDAO;

/**
 * Servlet implementation class TelephoneBillPayServlet
 */
@WebServlet("/TelephoneBillPayServlet")
public class TelephoneBillPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TelephoneBillPayServlet() {
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
			Class cls = Class.forName("com.unika.billpay.BillPayDAOImpl");
			BillPayDAO bpdao = (BillPayDAO) cls.newInstance();
			long numberStr = (long) session.getAttribute("phnumber");
			System.out.println(numberStr);
			if (bpdao.mobileBillPay(customerid, numberStr)) {
				response.sendRedirect("PayBills.jsp");
			} else {
				RequestDispatcher rd = request
						.getRequestDispatcher("PayBills.jsp");
				request.setAttribute("errmsg",
						"Some thing went wrong, please try later");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
