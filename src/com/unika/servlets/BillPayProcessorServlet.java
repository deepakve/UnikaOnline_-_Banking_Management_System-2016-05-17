package com.unika.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unika.billpay.BillPayServiceImpl;

/**
 * Servlet implementation class BillPayProcessorServlet
 */
@WebServlet("/BillPayProcessorServlet")
public class BillPayProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillPayProcessorServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("coming to bill pay servlet");
		//BillPayService bps = (BillPayService) Class.forName("com.unika.billpay.BillPayServiceImpl");
		BillPayServiceImpl bps = new BillPayServiceImpl();
		bps.payCurrentBill();
		bps.payMobileBill();
	}

}
