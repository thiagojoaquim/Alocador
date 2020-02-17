package br.ufs.dcomp.alocador.modelo;

import java.io.Serializable;

public class Disciplina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2120979652764778026L;
	private String nome;
	private Credito credito;
	private String codigo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean Equals(Disciplina outraDisciplina) {
		if (outraDisciplina == null)
			return false;
		return outraDisciplina.getCodigo().equals(this.codigo);		
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}
	@Override
	public String toString() {
		return nome;
	}
}
