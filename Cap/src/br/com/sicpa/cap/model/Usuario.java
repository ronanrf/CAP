package br.com.sicpa.cap.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario")
	private int idUsuario;

	@Column(name="usu_area")
	private String usuArea;

	private String usu_gestorDireto;

	private String usu_grupoAcesso;

	@Column(name="usu_login")
	private String usuLogin;

	@Column(name="usu_matricula")
	private String usuMatricula;

	@Column(name="usu_nome")
	private String usuNome;

	@Column(name="usu_senha")
	private String usuSenha;

	//bi-directional many-to-one association to Gestor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_gestor")
	private Gestor gestor;

    public Usuario() {
    }

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuArea() {
		return this.usuArea;
	}

	public void setUsuArea(String usuArea) {
		this.usuArea = usuArea;
	}

	public String getUsu_gestorDireto() {
		return this.usu_gestorDireto;
	}

	public void setUsu_gestorDireto(String usu_gestorDireto) {
		this.usu_gestorDireto = usu_gestorDireto;
	}

	public String getUsu_grupoAcesso() {
		return this.usu_grupoAcesso;
	}

	public void setUsu_grupoAcesso(String usu_grupoAcesso) {
		this.usu_grupoAcesso = usu_grupoAcesso;
	}

	public String getUsuLogin() {
		return this.usuLogin;
	}

	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	public String getUsuMatricula() {
		return this.usuMatricula;
	}

	public void setUsuMatricula(String usuMatricula) {
		this.usuMatricula = usuMatricula;
	}

	public String getUsuNome() {
		return this.usuNome;
	}

	public void setUsuNome(String usuNome) {
		this.usuNome = usuNome;
	}

	public String getUsuSenha() {
		return this.usuSenha;
	}

	public void setUsuSenha(String usuSenha) {
		this.usuSenha = usuSenha;
	}

	public Gestor getGestor() {
		return this.gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}
	
}