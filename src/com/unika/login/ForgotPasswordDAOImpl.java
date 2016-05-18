package com.unika.login;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.RegisteredUserDetails;

public class ForgotPasswordDAOImpl implements ForgotPasswordDAO {

	@Override
	public boolean getSecurityQuestions(int customerid, String contact,
			String email) {
		Session session = null;
		RegisteredUserDetails rud;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
			String ContactNo = rud.getContact();
			String EmailID = rud.getEmail();
			if (ContactNo.equals(contact) && EmailID.equals(email))
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
	public boolean securityAnswerAuthentication(int customerid, String answer1,
			String answer2) {
		Session session = null;
		RegisteredUserDetails rud;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
			String SecAns1 = rud.getAnswer1();
			String SecAns2 = rud.getAnswer2();
			if (SecAns1.equals(answer1) && SecAns2.equals(answer2))
				return true;
			else
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

	@Override
	public boolean resetPassword(int customerid, String newPassword) {
		Session session = null;
		RegisteredUserDetails rud;
		PasswordConversion pc = new PasswordConversion();
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.beginTransaction();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
			rud.setPassword(pc.makeSHA1Hash(newPassword));
			session.update(rud);
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

}
