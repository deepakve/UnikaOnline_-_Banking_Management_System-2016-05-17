package com.unika.admin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unika.admin.fixeddeposits.FixedDepositsDAO;
import com.unika.admin.fixeddeposits.FixedDepositsDAOImpl;
import com.unika.hibernatemapping.FixedDeposits;

/**
 * Servlet implementation class FixedDepositeProcessorServlet
 */
@WebServlet("/FixedDepositeProcessorServlet")
public class FixedDepositeProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FixedDepositeProcessorServlet() {
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
		FixedDeposits fd = new FixedDeposits();
		fd.setFixedtype(request.getParameter("type"));
		fd.setIntrest(Float.parseFloat(request.getParameter("intrest")));
		fd.setDays(Integer.parseInt(request.getParameter("days")));
		FixedDepositsDAO fddi = new FixedDepositsDAOImpl();
		int flag = fddi.fixedDepositeType(fd);
		if (flag == 1)
			response.sendRedirect("cust_reg.jsp");
		else
			response.sendRedirect("error.jsp");
	}

}
