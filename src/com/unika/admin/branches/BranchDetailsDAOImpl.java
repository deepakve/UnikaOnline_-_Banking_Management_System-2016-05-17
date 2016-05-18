package com.unika.admin.branches;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.BranchDetails;

public class BranchDetailsDAOImpl implements BranchDetailsDAO {

	@Override
	public void saveOrUpdate(BranchDetails branchdb) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.save(branchdb);
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

}
