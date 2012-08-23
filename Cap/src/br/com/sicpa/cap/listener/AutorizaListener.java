package br.com.sicpa.cap.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.sicpa.cap.managedBean.LoginBean;

public class AutorizaListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// Obt�m o contexto atual
		FacesContext contexto = event.getFacesContext();
		// Obt�m a p�gina que atualmente est� interagindo com o ciclo
		String paginaAtual = contexto.getViewRoot().getViewId();
		// Se for a p�gina 'login.jsp' seta a vari�vel como true
		boolean isLoginPage = false;
		if (paginaAtual.lastIndexOf("cadastroUsuario") > -1 == true) {
			isLoginPage = paginaAtual.lastIndexOf("cadastroUsuario") > -1;
		}
		if (paginaAtual.lastIndexOf("createCap.xhtml") > -1 == true) {
			isLoginPage = paginaAtual.lastIndexOf("createCap") > -1;
		}
		// Obt�m a sess�o atual
		HttpSession sessao = (HttpSession) contexto.getExternalContext()
				.getSession(true);
		// Resgata o nome do usu�rio logado
		LoginBean loginBean = (LoginBean) sessao.getAttribute("usuarioBean");

		if (loginBean == null) {
			loginBean = null;
		} else {
			loginBean = ((LoginBean) sessao.getAttribute("usuarioBean"));
		}
		// Verifica se o usu�rio est� logado e se n�o est� na p�gina de login
		if (!isLoginPage && loginBean == null) {
			// Redireciona o fluxo para a p�gina de login
			NavigationHandler nh = contexto.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(contexto, null, "cadastroUsuario");

			contexto.renderResponse();

		} else if (isLoginPage && loginBean != null) {
			// Se o usu�rio logado tentar acessar a p�gina de login ele �
			// redirecionado para a p�gina inicial
			NavigationHandler nh = contexto.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(contexto, null, "createCap");

			contexto.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}