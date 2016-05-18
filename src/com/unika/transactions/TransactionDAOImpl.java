package com.unika.transactions;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.BranchDetails;
import com.unika.hibernatemapping.CustomerAccounts;
import com.unika.hibernatemapping.BeneficiaryDetails;
import com.unika.hibernatemapping.RegisteredUserDetails;
import com.unika.hibernatemapping.TransactionHistory;
import com.unika.login.PasswordConversion;

public class TransactionDAOImpl implements TransactionDAO {
	Session session = null;
	RegisteredUserDetails rud;
	TransactionHistory th;
	CustomerAccounts ca1;
	CustomerAccounts ca2;
	BeneficiaryDetails mb;

	@SuppressWarnings("unchecked")
	@Override
	public boolean transferFunds(int customerid, long withAccountNumber,
			long toAccountNumber, double amount) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		th = new TransactionHistory();
		Transaction txn = session.beginTransaction();
		String ca1querystr = "FROM CustomerAccounts WHERE accountnumber = "
				+ withAccountNumber;
		String ca2querystr = "FROM CustomerAccounts WHERE accountnumber = "
				+ toAccountNumber;
		Query ca1query = session.createQuery(ca1querystr);
		Query ca2query = session.createQuery(ca2querystr);
		ArrayList<CustomerAccounts> ca1 = (ArrayList<CustomerAccounts>) ca1query
				.list();
		ArrayList<CustomerAccounts> ca2 = (ArrayList<CustomerAccounts>) ca2query
				.list();
		System.out.println("withAccountNumber11" + withAccountNumber);
		String hql = "FROM BeneficiaryDetails WHERE customerid = " + customerid
				+ " and beneficiaryNumber = " + toAccountNumber;
		System.out.println("withAccountNumber12" + withAccountNumber);
		Query query = session.createQuery(hql);
		ArrayList<BeneficiaryDetails> al = (ArrayList<BeneficiaryDetails>) query
				.list();
		double limit = al.get(0).getLimit();
		double senderBalance = ca1.get(0).getBalance();
		double recieverBalanace = ca2.get(0).getBalance();

		if (senderBalance > amount + 500 && amount <= limit) {
			recieverBalanace = recieverBalanace + amount;
			senderBalance = senderBalance - amount;
			ca1.get(0).setBalance(senderBalance);
			ca2.get(0).setBalance(recieverBalanace);
			th.setWithaccountnumber(withAccountNumber);
			th.setToaccountnumber(toAccountNumber);
			th.setWithtransactioncode("002");
			th.setTotransactioncode("001");
			th.setAmount(amount);
			th.setTimestamp(toDate());
			session.update(ca1.get(0));
			session.update(ca2.get(0));
			session.save(th);
			txn.commit();
			return true;
		} else
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TransactionHistory> miniStatement(long accountnumber) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		String hql = "FROM TransactionHistory WHERE withaccountnumber = "
				+ accountnumber + "OR toaccountnumber = " + accountnumber
				+ " ORDER BY txnid DESC";
		Query query = session.createQuery(hql);
		ArrayList<TransactionHistory> al = (ArrayList<TransactionHistory>) query
				.list();
		return al;
	}

	@Override
	public String getBranchDetails(int branchCode) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		BranchDetails bd = (BranchDetails) session.get(BranchDetails.class,
				branchCode);
		String branchName = bd.getBranchAddr2() + ", " + bd.getBranchCity();
		return branchName;
	}

	@Override
	public String getBenficiaryName(int customerid, long beneficiaryNumber) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM BeneficiaryDetails where customerid = " + customerid
				+ " AND beneficiaryNumber = " + beneficiaryNumber;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<BeneficiaryDetails> al = (ArrayList<BeneficiaryDetails>) query
				.list();
		if (al.size() == 0) {
			return "";
		} else
			return al.get(0).getNickName();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean passwordAuthentication(int customerid,
			String passwordConformation) {
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
			Class cls = Class.forName("com.unika.login.PasswordConversion");
			PasswordConversion pc = (PasswordConversion) cls.newInstance();
			try {
				if (rud.getPassword().toString()
						.equals(pc.makeSHA1Hash(passwordConformation)))
					return true;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long getAccountNumber(int customerid) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM CustomerAccounts WHERE customerid = " + customerid
				+ " AND accountcode = 101";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<CustomerAccounts> al = (ArrayList<CustomerAccounts>) query
				.list();
		return al.get(0).getAccountnumber();
	}

	private String toDate() {
		Calendar cal = null;
		String currDate;
		cal = Calendar.getInstance();

		currDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1)
				+ "/" + cal.get(Calendar.YEAR);
		return currDate;
	}
}
