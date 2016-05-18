package com.unika.admin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unika.admin.branches.BranchDetailsDAOImpl;
import com.unika.hibernatemapping.BranchDetails;

/**
 * Servlet implementation class BranchDetailsInsertionServlet
 */
@WebServlet("/BranchDetailsInsertionServlet")
public class BranchDetailsInsertionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BranchDetailsInsertionServlet() {
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
		BranchDetails bd = new BranchDetails();
		bd.setBranchCode(request.getParameter("branchcode"));
		bd.setBranchAddr1(request.getParameter("branchaddr1"));
		bd.setBranchAddr2(request.getParameter("branchaddr2"));
		bd.setBranchCity(request.getParameter("branchcity"));
		bd.setBranchState(request.getParameter("branchstate"));
		bd.setBranchZipCode(Integer.parseInt(request
				.getParameter("branchzipcode")));
		BranchDetailsDAOImpl li = new BranchDetailsDAOImpl();
		li.saveOrUpdate(bd);
		response.sendRedirect("cust_reg.jsp");
	}

}
