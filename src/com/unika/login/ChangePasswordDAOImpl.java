package com.unika.login;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.RegisteredUserDetails;

public class ChangePasswordDAOImpl implements ChangePasswordDAO {

	@Override
	public boolean changePassword(int customerid, String oldPassword,
			String newPassword) {
		Session session = null;
		PasswordConversion pc = new PasswordConversion();
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			RegisteredUserDetails rud = new RegisteredUserDetails();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
			if (rud.getPassword().equals(pc.makeSHA1Hash(oldPassword))) {
				rud.setPassword(pc.makeSHA1Hash(newPassword));
				session.update(rud);
				txn.commit();
				return true;
			} else
				return false;
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

}
