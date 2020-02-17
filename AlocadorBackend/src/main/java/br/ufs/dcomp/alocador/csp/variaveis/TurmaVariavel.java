package br.ufs.dcomp.alocador.csp.variaveis;

import aima.core.search.csp.Variable;
import br.ufs.dcomp.alocador.modelo.Turma;

public class TurmaVariavel extends Variable {

	public Turma turma;

	public TurmaVariavel(Turma turma) {
		super(turma.getDisciplina().getCodigo());
		this.turma = turma;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
