package br.ufs.dcomp.alocador.csp.dominio;
import java.util.List;
import br.ufs.dcomp.alocador.modelo.HorarioMateria;
import br.ufs.dcomp.alocador.modelo.Professor;

public class Dominio {

	private HorarioMateria horario;
	private Professor professor;

	public Dominio() {

	}

	public Dominio(HorarioMateria horario, Professor professor) {
		this.horario = horario;
		this.professor = professor;
	}

	public HorarioMateria getHorario() {
		return horario;
	}

	public void setHorario(HorarioMateria horario) {
		this.horario = horario;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}