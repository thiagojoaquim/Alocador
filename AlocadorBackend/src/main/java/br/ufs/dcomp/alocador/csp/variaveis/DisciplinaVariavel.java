package br.ufs.dcomp.alocador.csp.variaveis;

import aima.core.search.csp.Variable;
import br.ufs.dcomp.alocador.modelo.Disciplina;

public class DisciplinaVariavel extends Variable{

	private Disciplina disciplina;
	public DisciplinaVariavel(Disciplina disciplina) {
		super(disciplina.getCodigo());
		this.disciplina = disciplina;				
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	

}
