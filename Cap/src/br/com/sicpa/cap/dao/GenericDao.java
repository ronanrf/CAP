package br.com.sicpa.cap.dao;

import java.util.List;

import org.hibernate.Session;

public class GenericDao<T> implements IDao<T> {

	public Class<T> classe;
	public Session session;

	public GenericDao(Class<T> classe, Session session) {
		super();
		this.classe = classe;
		this.session = session;
	}

	@Override
	public void salvar(T obj) {

		session.getTransaction().begin();

		session.saveOrUpdate(obj);

		session.getTransaction().commit();

	}

	@Override
	public void upadte(T obj) {

		session.getTransaction().begin();

		session.saveOrUpdate(obj);

		session.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {

		return session.createCriteria(classe).list();

	}

	@Override
	public Object findById(Integer id) {

		return session.get(classe, id);
	}

}