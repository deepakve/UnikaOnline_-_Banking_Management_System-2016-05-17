package com.unika.login;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.RegisteredUserDetails;

public class FirstLoginDAOImpl implements FirstLoginDAO {

	@Override
	public boolean saveNecessaryDetails(int customerid, String newPassword,
			String secQues1, String answer1, String secQues2, String answer2) {
		Session session = null;
		RegisteredUserDetails rud;
		PasswordConversion pc = new PasswordConversion();
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			rud = (RegisteredUserDetails) session.get(
					RegisteredUserDetails.class, customerid);
			rud.setCustomerid(customerid);
			rud.setPassword(pc.makeSHA1Hash(newPassword));
			rud.setQuestion1(secQues1);
			rud.setAnswer1(answer1);
			rud.setQuestion2(secQues2);
			rud.setAnswer2(answer2);
			rud.setStatus("0");
			session.update(rud);
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

	public void saveRegAcc(RegisteredUserDetails rud,
			HttpServletRequest request, String cont) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.saveOrUpdate(rud);
			txn.commit();
			int cid = getCustomerId(cont);
			String customerid = "" + cid;
			HttpSession ses = request.getSession(true);
			ses.setAttribute("Regcustomerid", new String(customerid));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}

	public int getCustomerId(String cont) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "from RegisteredUserDetails where contact= '" + cont
					+ "'";
			Query que = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			ArrayList<RegisteredUserDetails> al = (ArrayList<RegisteredUserDetails>) que
					.list();
			int id = al.get(0).getCustomerid();
			return id;
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
	public String randomPasswordGenerator() {
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(7);
		for (int i = 0; i < 7; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}
