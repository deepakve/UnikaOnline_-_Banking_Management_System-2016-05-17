package com.unika.billpay;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.accounts.AccountsDAO;
import com.unika.accounts.AccountsDAOImpl;
import com.unika.hibernatemapping.CustomerAccounts;
import com.unika.hibernatemapping.ElectricityDetails;
import com.unika.hibernatemapping.PhoneDetails;
import com.unika.hibernatemapping.TransactionHistory;

public class BillPayDAOImpl implements BillPayDAO {

	@SuppressWarnings("unchecked")
	@Override
	public String electricityBillAmount(int areaCode, int serviceNumber) {
		try {
			Session session = null;
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM ElectricityDetails WHERE areaCode = " + areaCode
					+ " AND serviceCode = " + serviceNumber;
			Query query = session.createQuery(hql);
			ArrayList<ElectricityDetails> al = (ArrayList<ElectricityDetails>) query
					.list();
			return "" + al.get(0).getAmount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Unidentified Details Given";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean electricityBillPay(int customerid, int areaCode,
			int serviceNumber) {
		String amountStr = electricityBillAmount(areaCode, serviceNumber);
		if (amountStr.length() != 26) {
			float amount = Float.parseFloat(amountStr);
			if (amount != 0) {
				try {
					Session session = null;
					SessionFactory sessionFactory = new Configuration()
							.configure().buildSessionFactory();
					session = sessionFactory.openSession();
					Transaction txn = session.beginTransaction();
					String hql = "FROM CustomerAccounts WHERE customerid ="
							+ customerid + " AND accountcode = 101";
					Query query = session.createQuery(hql);
					ArrayList<CustomerAccounts> ca = (ArrayList<CustomerAccounts>) query.list();
					if (ca.get(0).getBalance() > amount) {
						ca.get(0).setBalance(ca.get(0).getBalance() - amount);
						if (setElectricityBillTransaction(amount, customerid,
								areaCode, serviceNumber)) {
							session.save(ca.get(0));
							txn.commit();
						}
					} else
						return false;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else
				return true;
		}
		return false;
	}

	private boolean setElectricityBillTransaction(float amount, int customerid,
			int areaCode, int serviceNumber) {
		try {
			String code = areaCode + "" + serviceNumber;
			int codeNumber = Integer.parseInt(code);
			Session session = null;
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			AccountsDAO adao = new AccountsDAOImpl();
			TransactionHistory th = new TransactionHistory();
			th.setAmount(amount);
			th.setTimestamp(toDate());
			th.setToaccountnumber(codeNumber);
			th.setTotransactioncode("001");
			th.setWithaccountnumber(adao.getSavingsAccountNumber(customerid));
			th.setWithtransactioncode("006");
			session.saveOrUpdate(th);
			txn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private String toDate() {
		Calendar cal = null;
		String currDate;
		cal = Calendar.getInstance();

		currDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1)
				+ "/" + cal.get(Calendar.YEAR);
		return currDate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String moblieBillAmount(long phNumber) {
		System.out.println(phNumber);
		try {
			Session session = null;
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM PhoneDetails WHERE phoneNumber = " + phNumber;
			Query query = session.createQuery(hql);
			ArrayList<PhoneDetails> al = (ArrayList<PhoneDetails>) query.list();
			return "" + al.get(0).getAmount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Unidentified Details Given";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean mobileBillPay(int customerid, long phNumber) {
		String amountStr = moblieBillAmount(phNumber);
		if (amountStr.length() != 26) {
			float amount = Float.parseFloat(amountStr);
			if (amount != 0) {
				try {
					Session session = null;
					SessionFactory sessionFactory = new Configuration()
							.configure().buildSessionFactory();
					session = sessionFactory.openSession();
					Transaction txn = session.beginTransaction();
					String hql = "FROM CustomerAccounts WHERE customerid ="
							+ customerid + " AND accountcode = 101";
					Query query = session.createQuery(hql);
					ArrayList<CustomerAccounts> ca = (ArrayList<CustomerAccounts>) query.list();
					if (ca.get(0).getBalance() > amount) {
						ca.get(0).setBalance(ca.get(0).getBalance() - amount);
						if (setPhoneBillTransaction(amount, customerid,
								phNumber)) {
							session.save(ca.get(0));
							txn.commit();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	private boolean setPhoneBillTransaction(float amount, int customerid,
			long phNumber) {
		try {
			Session session = null;
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			AccountsDAO adao = new AccountsDAOImpl();
			TransactionHistory th = new TransactionHistory();
			th.setAmount(amount);
			th.setTimestamp(toDate());
			th.setToaccountnumber(phNumber);
			th.setTotransactioncode("001");
			th.setWithaccountnumber(adao.getSavingsAccountNumber(customerid));
			th.setWithtransactioncode("005");
			session.saveOrUpdate(th);
			txn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
