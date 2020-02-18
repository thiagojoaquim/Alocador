package br.ufs.dcomp.alocador.csp.restricoes;

import java.util.Arrays;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import br.ufs.dcomp.alocador.csp.AlocadorCSP;
import br.ufs.dcomp.alocador.csp.dominio.Dominio;
import br.ufs.dcomp.alocador.csp.variaveis.TurmaVariavel;
import br.ufs.dcomp.alocador.modelo.HorarioMateria;

public class RestricaoAulaParalela implements Constraint<TurmaVariavel, Dominio>{
	private TurmaVariavel turma;
	private TurmaVariavel turma2;
	
	public RestricaoAulaParalela( TurmaVariavel turma, TurmaVariavel turma2) {
		this.turma = turma;
		this.turma2 = turma2;
	}

	@Override
	public List<TurmaVariavel> getScope() {
		return Arrays.asList(turma, turma2);
	}

	@Override
	public boolean isSatisfiedWith(Assignment<TurmaVariavel, Dominio> assignment) {
		Dominio dominio1 = assignment.getValue(turma);
		Dominio dominio2 = assignment.getValue(turma2);
		if(dominio1 == null || dominio2 == null)
			return true;		
		 for(HorarioMateria h : dominio1.getHorario()) {
			 for(HorarioMateria h2 : dominio2.getHorario()) {
				 if(h.colide(h2) == 1)
					 return false;
			 } 
		 }
		 return true;
	}
}
