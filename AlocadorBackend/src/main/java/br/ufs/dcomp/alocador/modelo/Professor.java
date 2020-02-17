package br.ufs.dcomp.alocador.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String matricula;
	private List<Disciplina> disciplinasDePreferencia = new ArrayList<>();
	private List<Turma> turmas = new ArrayList<>();

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

}
