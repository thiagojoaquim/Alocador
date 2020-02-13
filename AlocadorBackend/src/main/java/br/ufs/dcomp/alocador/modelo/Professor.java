package br.ufs.dcomp.alocador.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private List<Disciplina> disciplinasDePreferencia = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Disciplina> getDisciplinasDePreferencia() {
		return disciplinasDePreferencia;
	}

	public void setDisciplinasDePreferencia(List<Disciplina> disciplinasDePreferencia) {
		this.disciplinasDePreferencia = disciplinasDePreferencia;
	}
}
