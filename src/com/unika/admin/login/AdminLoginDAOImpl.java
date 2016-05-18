package com.unika.admin.login;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.AdminDetails;

public class AdminLoginDAOImpl implements AdminLoginDAO {

	@Override
	public int adminloginAuthentication(String adminid, String adminpassword) {
		Session session = null;
		AdminDetails ad;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			ad = (AdminDetails) session.get(AdminDetails.class, adminid);
			if (ad.getAdminpassword().toString().equals(adminpassword)) {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return 1;
	}
}
