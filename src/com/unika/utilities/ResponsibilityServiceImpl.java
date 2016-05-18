package com.unika.utilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.unika.hibernatemapping.PhoneCompany;
import com.unika.hibernatemapping.PhoneDetails;
import com.unika.hibernatemapping.PhoneType;

public class ResponsibilityServiceImpl implements ResponsibilityService {

	@Override
	public String phoneDetails(long phNumber) {
		try {
//			int len = ("" + phNumber).length();
//			if (len > 10) {
//				phNumber = (int) (phNumber%Math.pow(10, 10));
//				System.out.println(phNumber);
//			}
			Session session = null;
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM PhoneDetails WHERE phoneNumber = " + phNumber;
			Query query = session.createQuery(hql);
			PhoneDetails pd = (PhoneDetails) query.list();
			String Company = phoneCompany(pd.getCompanyCode());
			String phType = phoneType(pd.getModelCode());
			return phType + " " + Company;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String phoneCompany(int code) {
		try {
			Session session = null;
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM PhoneCompany WHERE companyCode = " + code;
			Query query = session.createQuery(hql);
			PhoneCompany pd = (PhoneCompany) query.list();
			return pd.getCompanyName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String phoneType(int code) {
		try {
			Session session = null;
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			String hql = "FROM PhoneType WHERE phoneCode = " + code;
			Query query = session.createQuery(hql);
			PhoneType pd = (PhoneType) query.list();
			return pd.getPhoneType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
