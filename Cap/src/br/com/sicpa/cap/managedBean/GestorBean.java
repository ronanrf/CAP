package br.com.sicpa.cap.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sicpa.cap.dao.GenericDao;
import br.com.sicpa.cap.model.Gestor;
import br.com.sicpa.cap.util.Util;

@ManagedBean
@SessionScoped
public class GestorBean {

	List<Gestor> gestores = new ArrayList<Gestor>();

	@PostConstruct
	public void carregaGestores() {

		GenericDao<Gestor> dao = new GenericDao<Gestor>(Gestor.class,
				Util.getHibernateSession());
		this.gestores = dao.findAll();

	}

	public List<Gestor> getGestores() {
		return gestores;
	}

	public void setGestores(List<Gestor> gestores) {
		this.gestores = gestores;
	}

}
