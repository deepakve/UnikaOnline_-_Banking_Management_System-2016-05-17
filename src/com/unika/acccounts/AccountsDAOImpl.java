package com.unika.accounts;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.CustomerAccounts;
import com.unika.hibernatemapping.CustomerDetails;
import com.unika.hibernatemapping.BeneficiaryDetails;
import com.unika.hibernatemapping.RegisteredUserDetails;

public class AccountsDAOImpl implements AccountsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CustomerAccounts> getAccountsInformation(int customerid) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM CustomerAccounts WHERE customerid ="
					+ customerid + " ORDER BY accountcode";
			Query query = session.createQuery(hql);
			ArrayList<CustomerAccounts> al = (ArrayList<CustomerAccounts>) query
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
	public boolean addBeneficiary(int customerid, long beneficiary,
			String nickName, double limit) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			BeneficiaryDetails mb = new BeneficiaryDetails();
			mb.setCustomerid(customerid);
			mb.setBeneficiaryNumber(beneficiary);
			mb.setNickName(nickName);
			mb.setLimit(limit);
			session.save(mb);
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
	public boolean deleteBeneficiary(int customerid, long beneficiaryNumber) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			String hql = "DELETE FROM BeneficiaryDetails WHERE customerid = "
					+ customerid + " AND beneficiarynumber = "
					+ beneficiaryNumber;
			Query query = session.createQuery(hql);
			int row = query.executeUpdate();
			txn.commit();
			if (row == 0) {
				return false;
			} else {
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<BeneficiaryDetails> viewBeneficiary(int customerid) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM BeneficiaryDetails WHERE customerid ="
					+ customerid;
			Query query = session.createQuery(hql);
			ArrayList<BeneficiaryDetails> al = (ArrayList<BeneficiaryDetails>) query
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
	public CustomerDetails getCustomerDetails(int customerid) {
		Session session = null;
		CustomerDetails cd = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			cd = (CustomerDetails) session.get(CustomerDetails.class,
					customerid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return cd;
	}

	@Override
	public RegisteredUserDetails getContactDetails(int customerid) {
		Session session = null;
		RegisteredUserDetails rud = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return rud;
	}

	@Override
	public boolean editContactDetails(int customerid, String phoneNumber,
			String emailid) {
		Session session = null;
		RegisteredUserDetails rud;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
			rud.setContact(phoneNumber);
			rud.setEmail(emailid);
			session.save(rud);
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
	public boolean modifyBeneficiary(int customerid, long beneficiaryNumber,
			double limit, String name) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			String hql = "FROM BeneficiaryDetails WHERE customerid = "
					+ customerid + " AND beneficiaryNumber = "
					+ beneficiaryNumber;
			Query query = session.createQuery(hql);
			BeneficiaryDetails mb = (BeneficiaryDetails) query.list().get(0);
			mb.setLimit(limit);
			mb.setNickName(name);
			session.update(mb);
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
	public boolean checkBeneficiaryAvaliability(long beneficiary) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM CustomerAccounts WHERE accountnumber ="
					+ beneficiary;
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			ArrayList<CustomerAccounts> al = (ArrayList<CustomerAccounts>) query
					.list();
			if (al.size() == 0)
				return false;
			else
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CustomerAccounts> getSelectedAccountsInformation(
			int customerid, String selected) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql;
			if (selected.equalsIgnoreCase("All")) {
				hql = "FROM CustomerAccounts WHERE customerid =" + customerid;
			} else if (selected.equalsIgnoreCase("savings")) {
				hql = "FROM CustomerAccounts WHERE customerid =" + customerid
						+ "AND accountcode = 101";
			} else {
				hql = "FROM CustomerAccounts WHERE customerid =" + customerid
						+ "AND accountcode = 102";
			}
			Query query = session.createQuery(hql);
			ArrayList<CustomerAccounts> al = (ArrayList<CustomerAccounts>) query
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<BeneficiaryDetails> getSelectedBeneficiary(int customerid,
			String selected) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			long beneficiaryNumber = Long.parseLong(selected);
			String hql = "FROM BeneficiaryDetails WHERE customerid ="
					+ customerid + " AND beneficiaryNumber = "
					+ beneficiaryNumber;
			Query query = session.createQuery(hql);
			ArrayList<BeneficiaryDetails> al = (ArrayList<BeneficiaryDetails>) query
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
	public boolean editPersonalDetails(int customerid, String fname,
			String email, String lname, String mobile) {
		Session session = null;
		RegisteredUserDetails rud;
		CustomerDetails cd;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
			cd = (CustomerDetails) session.get(CustomerDetails.class,
					customerid);
			rud.setEmail(email);
			rud.setContact(mobile);
			cd.setFirstname(fname);
			cd.setLastname(lname);
			session.update(rud);
			session.update(cd);
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
	public CustomerAccounts getCustomerAccounts(int customerid) {
		Session session = null;
		CustomerAccounts ca;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM CustomerAccounts WHERE customerid ="
					+ customerid + " AND accountcode = 101";
			Query query = session.createQuery(hql);
			ca = (CustomerAccounts) query.list().get(0);
			return ca;
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
	public long getSavingsAccountNumber(int customerid) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM CustomerAccounts WHERE customerid ="
					+ customerid + " AND accountcode = '101'";
			Query query = session.createQuery(hql);
			CustomerAccounts ca = (CustomerAccounts) query.list().get(0);
			return ca.getAccountnumber();
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
}
