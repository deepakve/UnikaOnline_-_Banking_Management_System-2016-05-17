package com.unika.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BillDetailsServlet
 */
@WebServlet("/BillDetailsServlet")
public class BillDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BillDetailsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String SelectedOption = request.getParameter("Bill");
		if (SelectedOption.equalsIgnoreCase("electricity")) {
			out.println("<form action=\"ElectricityBillPayServlet\" method=\"post\">"
					+ "<table>"
					+ "<tr>"
					+ "<td>Area Code</td>"
					+ "<td><input type = text id=\"area\" name = area onblur=\"getElectricityBill()\" value = \"\" placeholder=\"Enter Locality Code\" autocomplete=\"off\"></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Service Number</td>"
					+ "<td><input type = text id=\"billnumber\" name = billnumber onblur=\"getElectricityBill()\" value = \"\" placeholder=\"Enter Bill Number\" autocomplete=\"off\"></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Bill Amount(INR)</td>"
					+ "<td><input type = text id = \"BillValue\" name = number value = \"\" readonly></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td align = \"center\"><input type = submit id = \"Submit\" value = \"Pay\" disabled></td>"
					+ "<td align = \"center\"><input type = reset value = \"Reset\"></td>"
					+ "</tr>" 
					+ "</table>"
					+ "</form>");
		} else if (SelectedOption.equalsIgnoreCase("telephone")) {
			out.println("<form action=\"TelephoneBillPayServlet\" method=\"post\">"
					+ "<table>"
					+ "<tr>"
					+ "<td>Subscriber</td>"
					+ "<td><select id=\"operator\" name=\"operator\" onchange=\"getNumberDetails()\">" 
					+ "<option>---Choose---</option>"
					+ "<option value=\"bsnl\">BSNL</option>"
					+ "<option value=\"airtel\">Airtel</option>"
					+ "<option value=\"vodafone\">Vodafone</option>"
					+ "<option value=\"tata\">Tata</option>"
					+ "<option value=\"reliance\">Reliance</option>"
					+ "<option value=\"idea\">Idea</option>"
					+ "<option value=\"aircel\">Aircel</option>"
					+ "</select>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Type</td>"
					+ "<td>"
					+ "<div id=\"type\">"
					+ "</div>"
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Telephone Number</td>"
					+ "<td><input type = text id=\"number\" name = number onblur=\"getTelephoneBill()\" value = \"\" placeholder=\"Enter Phone Number\" autocomplete=\"off\"></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Bill Amount(INR)</td>"
					+ "<td><input type = text id=\"amount\" name = amount value = \"\" readonly></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td align = \"center\"><input type = submit id = \"Submit\" value = \"Pay\" disabled></td>"
					+ "<td align = \"center\"><input type = reset value = \"Reset\"></td>"
					+ "</tr>" 
					+ "</table>"
					+ "</form>");
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
