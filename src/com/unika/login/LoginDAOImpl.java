package com.unika.login;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.CustomerDetails;
import com.unika.hibernatemapping.RegisteredUserDetails;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public void saveOrUpdate(RegisteredUserDetails logindb) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.save(logindb);
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
	public int loginAuthentication(int customerid, String password) {
		Session session = null;
		RegisteredUserDetails rud;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			PasswordConversion pc = new PasswordConversion();
			session = sessionFactory.openSession();
			session.beginTransaction();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
			if (rud.getPassword().toString().equals(pc.makeSHA1Hash(password))) {
				if (rud.getStatus().toString().equals("0")) {
					return 0;
				} else {
					return 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return 2;
	}

	@Override
	public String getName(int customerid) {
		Session session = null;
		CustomerDetails cd;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			cd = (CustomerDetails) session.get(CustomerDetails.class,
					customerid);
			String name = "" + cd.getFirstname() + " " + cd.getLastname();
			return name;
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
	public RegisteredUserDetails getSecutityQuestions(int customerid) {
		Session session = null;
		RegisteredUserDetails rud = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
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
}
