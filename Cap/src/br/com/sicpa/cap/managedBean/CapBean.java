package br.com.sicpa.cap.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import br.com.sicpa.cap.dao.UsuarioDao;
import br.com.sicpa.cap.model.Usuario;
import br.com.sicpa.cap.util.Util;

@ManagedBean
@SessionScoped
public class CapBean {

	public String motivo;
	public boolean pool = false;
	private boolean servicoExterno;
	private boolean consultaMedica;
	private boolean particular;
	private String outroMotivo = "_________________";
	private boolean check_outroMotivo = false;

	@PostConstruct
	public void growl() {

		Session hibernateSession = Util.getHibernateSession();
		UsuarioDao<Usuario> dao = new UsuarioDao<Usuario>(Usuario.class,
				hibernateSession);

		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) contexto.getExternalContext()
				.getSession(false);

		List<Usuario> usuarios = dao.findByLogin(((Usuario) session
				.getAttribute("usuario")));

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ol�, "
				+ usuarios.get(0).getUsuNome() + "!",
				"Bem vindo ao CAP online!");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		this.pool = true;

	}

	public boolean isCheck_outroMotivo() {
		return check_outroMotivo;
	}

	public void setCheck_outroMotivo(boolean check_outroMotivo) {
		this.check_outroMotivo = check_outroMotivo;
	}

	public String getOutroMotivo() {
		return outroMotivo;
	}

	public void setOutroMotivo(String outroMotivo) {
		this.outroMotivo = outroMotivo;
	}

	public boolean isConsultaMedica() {
		return consultaMedica;
	}

	public void setConsultaMedica(boolean consultaMedica) {
		this.consultaMedica = consultaMedica;
	}

	public boolean isParticular() {
		return particular;
	}

	public void setParticular(boolean particular) {
		this.particular = particular;
	}

	public boolean isServicoExterno() {
		return servicoExterno;
	}

	public void setServicoExterno(boolean servicoExterno) {
		this.servicoExterno = servicoExterno;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isPool() {
		return pool;
	}

	public void setPool(boolean pool) {
		this.pool = pool;
	}

}
