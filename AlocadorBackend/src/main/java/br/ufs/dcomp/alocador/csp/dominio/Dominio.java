package br.ufs.dcomp.alocador.csp.dominio;
import java.util.List;
import br.ufs.dcomp.alocador.modelo.HorarioMateria;
import br.ufs.dcomp.alocador.modelo.Professor;

public class Dominio {

	private List<HorarioMateria> horario;
	private Professor professor;

	public Dominio() {

	}

	public List<HorarioMateria> getHorario() {
		return horario;
	}

	public void setHorario(List<HorarioMateria> horario) {
		this.horario = horario;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	
}