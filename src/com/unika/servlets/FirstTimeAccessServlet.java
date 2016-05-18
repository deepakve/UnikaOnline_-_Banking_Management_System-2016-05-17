package com.unika.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.login.FirstLoginDAO;

/**
 * Servlet implementation class firstTimeAccessServlet
 */
@WebServlet("/FirstTimeAccessServlet")
public class FirstTimeAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstTimeAccessServlet() {
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
			String newPassword = request.getParameter("newpassword");
			String secQues1 = request.getParameter("question1");
			String answer1 = request.getParameter("answer1");
			String secQues2 = request.getParameter("question2");
			String answer2 = request.getParameter("answer2");
			Class cls = Class.forName("com.unika.login.FirstLoginDAOImpl");
			FirstLoginDAO fldao = (FirstLoginDAO) cls.newInstance();
			fldao.saveNecessaryDetails(customerid, newPassword, secQues1,
					answer1, secQues2, answer2);
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
