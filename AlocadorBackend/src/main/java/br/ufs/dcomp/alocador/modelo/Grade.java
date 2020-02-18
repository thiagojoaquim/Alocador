package br.ufs.dcomp.alocador.modelo;

import java.util.List;

public class Grade {

	private List<Turma> turmas;
	private List<Professor> professores;
	private List<Turma> turmasDefinidas;
	private Turno turno;
	
	public Grade() {
	
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Turma> getTurmasDefinidas() {
		return turmasDefinidas;
	}

	public void setTurmasDefinidas(List<Turma> turmasDefinidas) {
		this.turmasDefinidas = turmasDefinidas;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	
	
	
}

