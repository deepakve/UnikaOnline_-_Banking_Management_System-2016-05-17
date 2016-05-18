package com.unika.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.billpay.BillPayDAO;

/**
 * Servlet implementation class TelephoneBillServlet
 */
@WebServlet("/TelephoneBillServlet")
public class TelephoneBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TelephoneBillServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Class cls = Class.forName("com.unika.billpay.BillPayDAOImpl");
			BillPayDAO bpdao = (BillPayDAO) cls.newInstance();
			PrintWriter out = response.getWriter();
			String operator = request.getParameter("operator");
			// String type = request.getParameter("type");
			String numberStr = request.getParameter("number");
			// System.out.println("Type="+type);
			HttpSession httpsession = request.getSession();
			long phnumber = Long.parseLong(numberStr);
			httpsession.setAttribute("phnumber", phnumber);
			out.println(bpdao.moblieBillAmount(phnumber));
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
