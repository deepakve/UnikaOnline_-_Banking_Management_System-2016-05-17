package com.unika.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unika.admin.create.CustomerDetailsDAOImpl;

/**
 * Servlet implementation class ManageProcessorServlet
 */
@WebServlet("/ManageProcessorServlet")
public class ManageProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageProcessorServlet() {
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
		int cid = Integer.parseInt(request.getParameter("customerid"));

		CustomerDetailsDAOImpl cdd = new CustomerDetailsDAOImpl();
		cdd.getCustomerDetails(cid);

	}

}
