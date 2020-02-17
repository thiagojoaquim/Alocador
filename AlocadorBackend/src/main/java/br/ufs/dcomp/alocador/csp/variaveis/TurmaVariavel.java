package br.ufs.dcomp.alocador.csp.variaveis;

import aima.core.search.csp.Variable;
import br.ufs.dcomp.alocador.modelo.Turma;

public class TurmaVariavel extends Variable {

	public Turma turma;
	public int idTurma;
	public int QtdHorarios;

	public TurmaVariavel(Turma turma, int id, int horario) {
		super(turma.getDisciplina().getCodigo() + "." + id);
		this.turma = turma;
		this.idTurma = id;
		this.QtdHorarios = horario;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public int getQtdHorarios() {
		return QtdHorarios;
	}

	public void setQtdHorarios(int qtdHorarios) {
		QtdHorarios = qtdHorarios;
	}

}
