package com.unika.admin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unika.hibernatemapping.RegisteredUserDetails;
import com.unika.login.FirstLoginDAO;
import com.unika.login.FirstLoginDAOImpl;
import com.unika.login.PasswordConversion;

/**
 * Servlet implementation class AdminAccountRegProcessorServlet
 */
@WebServlet("/AdminAccountRegProcessorServlet")
public class AdminAccountRegProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAccountRegProcessorServlet() {
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
		try {
			HttpSession session = request.getSession();
			RegisteredUserDetails rud = new RegisteredUserDetails();
			PasswordConversion pc = new PasswordConversion();
			FirstLoginDAO fldi = new FirstLoginDAOImpl();
			String c = request.getParameter("contact");
			String randomPassword = fldi.randomPasswordGenerator();
			session.setAttribute("randomPassword", randomPassword);
			rud.setPassword(pc.makeSHA1Hash(randomPassword));
			rud.setStatus("1");
			rud.setContact(c);
			rud.setEmail(request.getParameter("email"));
			fldi.saveRegAcc(rud, request, c);
			response.sendRedirect("admin_acc_home.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
