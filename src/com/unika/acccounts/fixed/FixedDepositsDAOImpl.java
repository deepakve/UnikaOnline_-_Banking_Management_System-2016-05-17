package com.unika.accounts.fixed;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.accounts.AccountsDAO;
import com.unika.hibernatemapping.CustomerAccounts;
import com.unika.hibernatemapping.FixedDepositAccounts;
import com.unika.hibernatemapping.FixedDeposits;
import com.unika.hibernatemapping.TransactionHistory;

public class FixedDepositsDAOImpl implements FixedDepositsDAO {

	@Override
	public boolean addFixedDeposit(int customerid, long SavingAccountNumber,
			String depositCode, double amount) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			CustomerAccounts ca;
			FixedDepositAccounts fda = new FixedDepositAccounts();
			String hql = "FROM CustomerAccounts WHERE customerid ="
					+ customerid + " AND accountcode = 101";
			Query query = session.createQuery(hql);
			ca = (CustomerAccounts) query.list().get(0);
			if (ca.getBalance() > amount) {
				fda.setAmount(amount);
				fda.setCreatedon(toDate());
				fda.setCustomerid(customerid);
				fda.setDepositTypeCode(depositCode);
				ca.setBalance(ca.getBalance() - amount);
				session.save(fda);
				session.saveOrUpdate(ca);
				txn.commit();
				setFixedDeposit(customerid);
				setTransactionFixed(customerid, amount);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return false;
	}

	private void setTransactionFixed(int customerid, double amount) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			String hql = "FROM FixedDepositAccounts WHERE customerid = "
					+ customerid + " ORDER BY accountNumber DESC";
			Query query = session.createQuery(hql);
			FixedDepositAccounts fda = (FixedDepositAccounts) query.list().get(
					0);
			@SuppressWarnings("rawtypes")
			Class cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
			AccountsDAO adao = (AccountsDAO) cls.newInstance();
			TransactionHistory th = new TransactionHistory();
			th.setAmount(amount);
			th.setTimestamp(toDate());
			th.setToaccountnumber(fda.getAccountNumber());
			th.setWithtransactioncode("003");
			th.setWithaccountnumber(adao.getSavingsAccountNumber(customerid));
			th.setTotransactioncode("001");
			session.saveOrUpdate(th);
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}

	@Override
	public boolean cancelFixedDeposit(int customerid, long depositNumber) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Class cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
			AccountsDAO adao = (AccountsDAO) cls.newInstance();
			long SavingsAccountNumber = adao
					.getSavingsAccountNumber(customerid);
			String hql = "FROM CustomerAccounts WHERE customerid = "
					+ customerid + " AND accountnumber = "
					+ SavingsAccountNumber;
			Query query = session.createQuery(hql);
			CustomerAccounts ca = (CustomerAccounts) query.list().get(0);
			double amount = releaseAmountFixed(customerid, depositNumber);
			ca.setBalance(ca.getBalance() + amount);
			session.saveOrUpdate(ca);
			txn.commit();
			unsetFixedDeposit(customerid, depositNumber);
			setTransactionUnFixed(customerid, amount, depositNumber);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return false;
	}

	private boolean unsetFixedDeposit(int customerid, long depositNumber) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			String hql1, hql2;
			hql1 = "DELETE FROM FixedDepositAccounts WHERE customerid = "
					+ customerid + " AND accountNumber = " + depositNumber;
			hql2 = "DELETE FROM CustomerAccounts WHERE customerid = "
					+ customerid + " AND accountnumber = " + depositNumber;
			Query query1, query2;
			query1 = session.createQuery(hql1);
			query2 = session.createQuery(hql2);
			query1.executeUpdate();
			query2.executeUpdate();
			txn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return false;
	}

	private void setTransactionUnFixed(int customerid, double amount,
			long depositNumber) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Class cls = Class.forName("com.unika.accounts.AccountsDAOImpl");
			AccountsDAO adao = (AccountsDAO) cls.newInstance();
			TransactionHistory th = new TransactionHistory();
			th.setAmount(amount);
			th.setTimestamp(toDate());
			th.setWithaccountnumber(depositNumber);
			th.setWithtransactioncode("004");
			th.setToaccountnumber(adao.getSavingsAccountNumber(customerid));
			th.setTotransactioncode("002");
			session.saveOrUpdate(th);
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}

	private double releaseAmountFixed(int customerid, long depositNumber) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			String hql = "FROM FixedDepositAccounts WHERE customerid = "
					+ customerid + " AND accountNumber = " + depositNumber;
			Query query = session.createQuery(hql);
			FixedDepositAccounts fda = (FixedDepositAccounts) query.list().get(
					0);
			String hql1 = "FROM FixedDeposits WHERE fixedtype = '"
					+ fda.getDepositTypeCode() + "'";
			Query query1 = session.createQuery(hql1);
			FixedDeposits fd = (FixedDeposits) query1.list().get(0);
			txn.commit();
			if (getDaysToClose(customerid, depositNumber) == 0)
				return fda.getAmount() * fd.getIntrest();
			else
				return fda.getAmount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<FixedDeposits> getDepositPolicies() {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM FixedDeposits";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			ArrayList<FixedDeposits> al = (ArrayList<FixedDeposits>) query
					.list();
			return al;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return null;
	}

	private String toDate() {
		String currDate;
		Calendar cal = Calendar.getInstance();

		currDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1)
				+ "/" + cal.get(Calendar.YEAR);
		return currDate;
	}

	private boolean setFixedDeposit(int customerid) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			CustomerAccounts ca = new CustomerAccounts();
			FixedDepositAccounts fda;
			String hql = "FROM FixedDepositAccounts WHERE customerid ="
					+ customerid + " ORDER BY accountNumber DESC";
			Query query = session.createQuery(hql);
			fda = (FixedDepositAccounts) query.list().get(0);
			ca.setAccountcode(102);
			ca.setAccountnumber(fda.getAccountNumber());
			ca.setBalance(fda.getAmount());
			ca.setBranchcode("INB");
			ca.setCustomerid(customerid);
			session.saveOrUpdate(ca);
			txn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return false;
	}

	@Override
	public ArrayList<FixedDepositAccounts> viewFixedDeposits(int customerid) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM FixedDepositAccounts WHERE customerid = "
					+ customerid + " ORDER BY accountnumber";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			ArrayList<FixedDepositAccounts> al = (ArrayList<FixedDepositAccounts>) query
					.list();
			return al;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return null;
	}

	@Override
	public int getDaysToClose(int customerid, long accno) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM FixedDepositAccounts WHERE customerid = "
					+ customerid + " AND accountNumber = " + accno;
			Query query = session.createQuery(hql);
			FixedDepositAccounts fda = (FixedDepositAccounts) query.list().get(
					0);
			hql = "FROM FixedDeposits WHERE fixedtype = '"
					+ fda.getDepositTypeCode() + "'";
			query = session.createQuery(hql);
			FixedDeposits fd = (FixedDeposits) query.list().get(0);
			return (fd.getDays() - differenceDays(fda.getCreatedon()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return 0;
	}

	private int differenceDays(String createdDate) {
		String currDate = toDate();
		long diffInMillisec = 0;
		long diffInDays = 0;
		Calendar firstDate, secondDate;
		firstDate = Calendar.getInstance();
		secondDate = Calendar.getInstance();
		firstDate.set(getYear(createdDate), getMonth(createdDate),
				getDays(createdDate));
		secondDate
				.set(getYear(currDate), getMonth(currDate), getDays(currDate));
		diffInMillisec = secondDate.getTimeInMillis()
				- firstDate.getTimeInMillis();
		diffInDays = diffInMillisec / (24 * 60 * 60 * 1000);
		return (int) diffInDays;
	}

	private int getDays(String Date) {
		int day = 0;
		if (Date.charAt(1) == '/')
			day = Date.charAt(0);
		if (Date.charAt(2) == '/')
			day = (Date.charAt(0) * 10) + Date.charAt(1);
		return day;
	}

	private int getMonth(String Date) {
		int month = 0;
		if ((Date.charAt(1) == '/') && (Date.charAt(3) == '/'))
			month = Date.charAt(2);
		if ((Date.charAt(2) == '/') && (Date.charAt(4) == '/'))
			month = Date.charAt(3);
		if ((Date.charAt(1) == '/') && (Date.charAt(4) == '/'))
			month = (Date.charAt(2) * 10) + Date.charAt(3);
		if ((Date.charAt(2) == '/') && (Date.charAt(5) == '/'))
			month = (Date.charAt(3) * 10) + Date.charAt(4);
		return month;
	}

	private int getYear(String Date) {
		int year = 0;
		int len = Date.length();
		year = (Date.charAt(len - 4) * 1000) + (Date.charAt(len - 3) * 100)
				+ (Date.charAt(len - 2) * 10) + Date.charAt(len - 1);
		return year;
	}

	@Override
	public String maturityDateCalculator(int days) {
		String Current = "";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Current = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1)
				+ "/" + cal.get(Calendar.YEAR);
		return Current;
	}

	@Override
	public FixedDepositAccounts getSelectedDepositDetails(int customerid,
			long depositNumber) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM FixedDepositAccounts WHERE customerid = "
					+ customerid + " AND accountnumber = " + depositNumber;
			Query query = session.createQuery(hql);
			FixedDepositAccounts fda = (FixedDepositAccounts) query.list().get(0);
			return fda;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return null;
	}
}
