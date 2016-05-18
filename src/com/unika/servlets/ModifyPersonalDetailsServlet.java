package com.unika.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.accounts.AccountsDAO;

/**
 * Servlet implementation class ModifyPersonalDetailsServlet
 */
@WebServlet("/ModifyPersonalDetailsServlet")
public class ModifyPersonalDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyPersonalDetailsServlet() {
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
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			Class cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
			AccountsDAO adao = (AccountsDAO) cls.newInstance();
			if (adao.editPersonalDetails(customerid, fname, email, lname,
					mobile)) {
				session.setAttribute("customername", fname + " " + lname);
				response.sendRedirect("ViewPersonalDetails.jsp");
			} else
				response.sendRedirect("error.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
