package br.ufs.dcomp.alocador.modelo;

import java.io.Serializable;

public class Turma implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4019872052620682398L;
	private Disciplina disciplina;
	private String horario;
	private Professor professor;
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}		
}
