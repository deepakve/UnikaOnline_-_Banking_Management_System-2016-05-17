package com.unika.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unika.billpay.BillPayDAO;

/**
 * Servlet implementation class ElectricityBillServlet
 */
@WebServlet("/ElectricityBillServlet")
public class ElectricityBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ElectricityBillServlet() {
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
			String areaCodeStr = request.getParameter("area");
			String billNumberStr = request.getParameter("billnumber");
			int areaCode = Integer.parseInt(areaCodeStr);
			int billNumber = Integer.parseInt(billNumberStr);
			Class cls = Class.forName("com.unika.billpay.BillPayDAOImpl");
			BillPayDAO bpdao = (BillPayDAO) cls.newInstance();
			out.println(bpdao.electricityBillAmount(areaCode, billNumber));
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
