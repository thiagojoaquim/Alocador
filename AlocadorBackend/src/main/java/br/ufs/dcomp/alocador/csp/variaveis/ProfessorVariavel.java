package br.ufs.dcomp.alocador.csp.variaveis;

import aima.core.search.csp.Variable;
import br.ufs.dcomp.alocador.modelo.Professor;

public class ProfessorVariavel extends Variable {

	private Professor professor;

	public ProfessorVariavel(String name, Professor professor) {
		super(professor.getMatricula());
		this.professor = professor;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
