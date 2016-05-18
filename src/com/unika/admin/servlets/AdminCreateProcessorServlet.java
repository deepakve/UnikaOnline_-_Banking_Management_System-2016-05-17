package com.unika.admin.servlets;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.sql.Date;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.unika.admin.create.CustomerDetailsDAOImpl;
import com.unika.hibernatemapping.CustomerDetails;

/**
 * Servlet implementation class AdminCreateProcessorServlet
 */
@WebServlet("/AdminCreateProcessorServlet")
public class AdminCreateProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCreateProcessorServlet() {
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
		CustomerDetails create = new CustomerDetails();
		String cid = request.getParameter("customerid");
		create.setCustomerid(Integer.parseInt(cid));
		create.setFirstname(request.getParameter("first_name"));
		create.setLastname(request.getParameter("last_name"));
		String startDateString = request.getParameter("dob");
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Date startDate;
		try {
			startDate = (Date) df.parse(startDateString);
			create.setDob(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		// create.setDob(request.getParameter("dob"));
		create.setAddress1(request.getParameter("address_1"));
		create.setAddress2(request.getParameter("address_2"));
		create.setCity(request.getParameter("city"));
		create.setState(request.getParameter("state"));
		String zp = request.getParameter("zipcode");
		create.setZipcode(Integer.parseInt(zp));
		/*
		 * SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy"); Date
		 * now = new Date(0); String strDate = sdfDate.format(now); try {
		 * curDate = (Date) df.parse(strDate); create.setCreatedon(curDate); }
		 * catch (java.text.ParseException e) {
		 * 
		 * e.printStackTrace(); }
		 */
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date(0);
		create.setCreatedon(dateFormat.format(date));
		CustomerDetailsDAOImpl cdi = new CustomerDetailsDAOImpl();
		cdi.createCustomerDetails(create);
		response.sendRedirect("cust_acc.jsp");
	}

}
