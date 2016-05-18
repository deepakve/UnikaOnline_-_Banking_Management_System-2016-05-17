package com.unika.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.transactions.TransactionDAO;

/**
 * Servlet implementation class MiniStatementProcesserServlet
 */
@WebServlet("/MiniStatementProcesserServlet")
public class MiniStatementProcesserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MiniStatementProcesserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			long customerid = (long) session.getAttribute("customerid");
			long accountnumber = (long) Long.parseLong(request
					.getParameter("accountnumber"));
			Class cls = Class.forName("com.unika.transactions.TransactionDAOImpl");
			TransactionDAO tdao = (TransactionDAO) cls.newInstance();
			tdao.miniStatement(accountnumber);
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
