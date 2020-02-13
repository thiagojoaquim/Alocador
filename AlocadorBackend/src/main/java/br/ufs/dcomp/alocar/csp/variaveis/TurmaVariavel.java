package br.ufs.dcomp.alocar.csp.variaveis;

import aima.core.search.csp.Variable;
import br.ufs.dcomp.alocador.modelo.Turma;

public class TurmaVariavel extends Variable{
	
	private String codigo;
	private Turma turma;

	public TurmaVariavel(String codigo, Turma turma) {
		super(codigo);
		this.turma = turma;			
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}		

}
