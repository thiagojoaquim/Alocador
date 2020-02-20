package br.ufs.dcomp.alocador.csp.restricoes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import br.ufs.dcomp.alocador.csp.constantes.Constantes;
import br.ufs.dcomp.alocador.csp.dominio.Dominio;
import br.ufs.dcomp.alocador.csp.variaveis.TurmaVariavel;
import br.ufs.dcomp.alocador.modelo.Professor;

public class RestricaoCargaHoraria implements Constraint<TurmaVariavel, Dominio> {

	private List<TurmaVariavel> variaveis;
	

	public RestricaoCargaHoraria(List<TurmaVariavel> variaveis) {
		this.variaveis = variaveis;
	}

	@Override
	public List<TurmaVariavel> getScope() {
		return variaveis;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<TurmaVariavel, Dominio> assignment) {
		Map<Professor, Integer> professorCargaHoraria = new HashMap<>();
		for (TurmaVariavel turmaVariavel : this.variaveis) {
			Dominio valor = assignment.getValue(turmaVariavel);
			if (valor != null && valor.getProfessor() != null) {
				Integer profCargaSum = professorCargaHoraria.get(valor.getProfessor());

				if (profCargaSum == null) {
					professorCargaHoraria.put(valor.getProfessor(),
							(int) valor.getHorario().size() * 2);
				} else {
					professorCargaHoraria.put(valor.getProfessor(),
							profCargaSum + (int) valor.getHorario().size() * 2);
				}
				if (professorCargaHoraria.get(valor.getProfessor()) > Constantes.CARGA_HORARIA) {
					return false;
				}
			}
		}
		return true;
	}

}
