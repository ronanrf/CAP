package br.com.sicpa.cap.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gestores database table.
 * 
 */
@Entity
@Table(name="gestor")
public class Gestor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_gestor")
	private int idGestor;

	@Column(name="gest_departamento")
	private String gestDepartamento;

	@Column(name="gest_nome")
	private String gestNome;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="gestor")
	private List<Usuario> usuarios;

    public Gestor() {
    }

	public int getIdGestor() {
		return this.idGestor;
	}

	public void setIdGestor(int idGestor) {
		this.idGestor = idGestor;
	}

	public String getGestDepartamento() {
		return this.gestDepartamento;
	}

	public void setGestDepartamento(String gestDepartamento) {
		this.gestDepartamento = gestDepartamento;
	}

	public String getGestNome() {
		return this.gestNome;
	}

	public void setGestNome(String gestNome) {
		this.gestNome = gestNome;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}