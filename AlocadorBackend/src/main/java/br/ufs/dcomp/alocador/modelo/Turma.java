package br.ufs.dcomp.alocador.modelo;

import java.io.Serializable;
import java.util.List;

public class Turma implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4019872052620682398L;
	private Disciplina disciplina;
	private List<HorarioMateria> horario;
	private Professor professor;
	
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

	public List<HorarioMateria> getHorario() {
		return horario;
	}

	public void setHorario(List<HorarioMateria> horario) {
		this.horario = horario;
	}
	
	
}

