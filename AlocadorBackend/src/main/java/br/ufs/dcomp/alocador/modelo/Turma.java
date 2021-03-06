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
	
	public String getCodigosToString() {
		String resp ="";
		if(horario != null)
			for(HorarioMateria h: horario)
				resp = resp+h.getCodigo();
		return resp;
	}
	public int colide(Turma t) {
		for(HorarioMateria h : t.getHorario()) {
			for(HorarioMateria h2 : this.getHorario()) {
				if(h.colide(h2) == 1)
					return 1;
			}
		}
		return 0;
	}
}

