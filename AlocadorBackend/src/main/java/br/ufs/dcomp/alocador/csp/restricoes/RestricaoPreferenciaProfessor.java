package br.ufs.dcomp.alocador.csp.restricoes;

import java.util.Arrays;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import br.ufs.dcomp.alocador.csp.dominio.Dominio;
import br.ufs.dcomp.alocador.csp.variaveis.TurmaVariavel;

public class RestricaoPreferenciaProfessor implements Constraint<TurmaVariavel, Dominio>{
	
	private TurmaVariavel variavel;
	
	public RestricaoPreferenciaProfessor(TurmaVariavel variavel) {
		this.variavel = variavel;
	}
	
	@Override
	public List<TurmaVariavel> getScope() {
		return Arrays.asList(variavel);		
	}

	@Override
	public boolean isSatisfiedWith(Assignment<TurmaVariavel, Dominio> assignment) {
		Dominio dominio = assignment.getValue(variavel);
		if(dominio != null) {
			return dominio.getProfessor().getDisciplinasDePreferencia().contains(variavel.getTurma().getDisciplina());
		}
		return false;		
	}
}
