package br.ufs.dcomp.alocador.modelo;

import java.io.Serializable;
import java.util.List;

public class Turma implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4019872052620682398L;
	private Disciplina disciplina;
	private List<DiaDaSemana> diaDaSemana;
	private List<Horario> horario;
	private Professor professor;
	private Turno turno;
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public List<DiaDaSemana> getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(List<DiaDaSemana> diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	public List<Horario> getHorario() {
		return horario;
	}
	public void setHorario(List<Horario> horario) {
		this.horario = horario;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	
}

