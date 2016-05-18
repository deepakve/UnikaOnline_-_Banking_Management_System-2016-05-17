package com.unika.admin.login;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.AdminDetails;

public class ChangePasswordDAOImpl implements ChangePasswordDAO {

	@Override
	public boolean changeAdminPassword(String adminid, String oldpassword,
			String newpassword) {
		Session session = null;
		AdminDetails ad;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			ad = (AdminDetails) session.get(AdminDetails.class, adminid);
			if (ad.getAdminpassword().toString().equals(oldpassword)) {
				ad.setAdminid(adminid);
				ad.setAdminpassword(newpassword);
			}
			session.update(ad);
			txn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}
}
