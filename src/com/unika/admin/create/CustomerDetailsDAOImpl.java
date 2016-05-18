package com.unika.admin.create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.CustomerDetails;

public class CustomerDetailsDAOImpl implements CustomerDetailsDAO {

	@Override
	public void createCustomerDetails(CustomerDetails cd) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.save(cd);
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
	public CustomerDetails getCustomerDetails(int customerid) {
		Session session = null;
		CustomerDetails ad = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			ad = (CustomerDetails) session.get(CustomerDetails.class,
					customerid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return ad;
	}
}
