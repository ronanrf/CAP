package br.com.sicpa.cap.util;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util implements Serializable {

	private static final long serialVersionUID = 1L;

	static Session session = null;

	private static SessionFactory factory;

	@SuppressWarnings("deprecation")
	public static Session getHibernateSession() {

		if (session.isOpen()) {
			return session;
		} else {
			factory = new Configuration().configure().buildSessionFactory();
			return factory.openSession();
		}

	}

	public void close(Session session) {

		session.close();
		factory.close();
	}

}
