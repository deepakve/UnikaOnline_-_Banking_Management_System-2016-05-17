package com.unika.admin.fixeddeposits;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.FixedDeposits;

public class FixedDepositsDAOImpl implements FixedDepositsDAO {

	@Override
	public int fixedDepositeType(FixedDeposits fd) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.save(fd);
			txn.commit();
			return 1;
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
