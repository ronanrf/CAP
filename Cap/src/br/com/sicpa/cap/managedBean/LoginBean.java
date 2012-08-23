package br.com.sicpa.cap.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;

import br.com.sicpa.cap.dao.GenericDao;
import br.com.sicpa.cap.dao.IDao;
import br.com.sicpa.cap.dao.UsuarioDao;
import br.com.sicpa.cap.model.Gestor;
import br.com.sicpa.cap.model.Usuario;
import br.com.sicpa.cap.util.Util;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	Usuario usuario = new Usuario();
	Gestor gestor = new Gestor();
	Util util = new Util();

	public void efetuaLogin() throws IOException {

		Session hibernateSession = Util.getHibernateSession();
		UsuarioDao<Usuario> dao = new UsuarioDao<Usuario>(Usuario.class,
				hibernateSession);

		try {
			if (dao.efetuaLogin(usuario)) {
				FacesContext contexto = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) contexto
						.getExternalContext().getSession(false);

				session.invalidate();
				session = (HttpSession) contexto.getExternalContext()
						.getSession(true);
				session.setAttribute("usuario", this.usuario);

				contexto.getExternalContext().redirect("createCap.xhtml");

			} else {
				FacesContext.getCurrentInstance().addMessage(
						"growl",
						new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"Usu�rio n�o encontrado.", ""));

			}
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(
					"growl",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aten��o",
							"Ocorreu um erro no sistema. Tente novamente."));
		} finally {
			util.close(hibernateSession);
			this.usuario = new Usuario();
		}

	}

	public void salvar() {

		Session hibernateSession = Util.getHibernateSession();

		if (!validaLogin()) {
			if (!validaMatricula()) {

				try {
					IDao<Usuario> dao = new GenericDao<Usuario>(Usuario.class,
							hibernateSession);
					dao.salvar(this.usuario);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, " ",
									"Cadastro realizado com sucesso!"));
				} catch (PersistentObjectException e) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL,
									"Erro no sistema.",
									"Por favor, contacte o adminstrador."));
				} finally {
					util.close(hibernateSession);
					this.usuario = new Usuario();
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Esta matricula j� existe.",
								"Por favor, digite novamente."));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Este login j� existe.", "Por favor tente outro."));
		}

	}

	public boolean validaLogin() {

		boolean uniqueLogin = false;

		Session hibernateSession = Util.getHibernateSession();
		UsuarioDao<Usuario> dao = new UsuarioDao<Usuario>(Usuario.class,
				hibernateSession);

		List<Usuario> usuarios = dao.findByLogin(usuario);

		for (Usuario u : usuarios) {

			if (u.getUsuLogin().equals(this.usuario.getUsuLogin())) {
				uniqueLogin = true;
			}

		}

		return uniqueLogin;

	}

	public boolean validaMatricula() {

		boolean uniqueLogin = false;

		Session hibernateSession = Util.getHibernateSession();
		UsuarioDao<Usuario> dao = new UsuarioDao<Usuario>(Usuario.class,
				hibernateSession);

		List<Usuario> usuarios = dao.findByMatricula(usuario);

		for (Usuario u : usuarios) {

			if (u.getUsuMatricula().equals(this.usuario.getUsuMatricula())) {
				uniqueLogin = true;
			}

		}

		return uniqueLogin;

	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
