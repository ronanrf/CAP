package br.com.sicpa.cap.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.sicpa.cap.model.Usuario;

public class UsuarioDao<T> extends GenericDao<T> {

	public UsuarioDao(Class<T> classe, Session hibernateSession) {
		super(classe, hibernateSession);

	}

	public boolean efetuaLogin(Usuario usuario) {

		Criteria criteria = session.createCriteria(classe);
		criteria.add(Restrictions.eq("usuLogin", usuario.getUsuLogin()));
		criteria.add(Restrictions.eq("usuSenha", usuario.getUsuSenha()));

		if (criteria.uniqueResult() != null) {
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByLogin(Usuario usuario) {

		Criteria criteria = session.createCriteria(classe);
		criteria.add(Restrictions.eq("usuLogin", usuario.getUsuLogin()));

		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByMatricula(Usuario usuario) {

		Criteria criteria = session.createCriteria(classe);
		criteria.add(Restrictions.eq("usuMatricula", usuario.getUsuMatricula()));

		return criteria.list();

	}
}
