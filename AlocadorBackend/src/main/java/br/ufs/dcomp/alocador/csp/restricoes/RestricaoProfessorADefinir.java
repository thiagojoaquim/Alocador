package br.ufs.dcomp.alocador.csp.restricoes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import br.ufs.dcomp.alocador.csp.constantes.Constantes;
import br.ufs.dcomp.alocador.csp.dominio.Dominio;
import br.ufs.dcomp.alocador.csp.variaveis.TurmaVariavel;
import br.ufs.dcomp.alocador.modelo.AulaSequencia;
import br.ufs.dcomp.alocador.modelo.Professor;

public class RestricaoProfessorADefinir implements Constraint<TurmaVariavel, Dominio> {

	private TurmaVariavel variavel;
	List<TurmaVariavel> variaveis;

	public RestricaoProfessorADefinir(TurmaVariavel variavel, List<TurmaVariavel> variaveis) {
		this.variavel = variavel;
		this.variaveis = variaveis;
	}

	@Override
	public List<TurmaVariavel> getScope() {
		return Arrays.asList(variavel);
	}

	@Override
	public boolean isSatisfiedWith(Assignment<TurmaVariavel, Dominio> assignment) {
		Map<Professor, Integer> professorCargaHoraria = new HashMap<>();
		Dominio valorV = assignment.getValue(variavel);
		if (valorV.getProfessor().getNome().equals(Constantes.PROFESSOR_A_DEFINIR)) {
			for (TurmaVariavel turmaVariavel : this.variaveis) {
				Dominio valor = assignment.getValue(turmaVariavel);
				if (valor != null && valor.getProfessor() != null) {
					Integer profCargaSum = professorCargaHoraria.get(valor.getProfessor());
					if (profCargaSum == null) {
						professorCargaHoraria.put(valor.getProfessor(), valor.getHorario().size() * AulaSequencia.DOIS.getAulaSequencia());
					} else {
						professorCargaHoraria.put(valor.getProfessor(),
								profCargaSum +  valor.getHorario().size() * 2);
					}
				}
			}
			for (Professor professor : professorCargaHoraria.keySet()) {
				if (professor.getDisciplinasDePreferencia().contains(variavel.getTurma().getDisciplina())
						&& !professor.getNome().equals(Constantes.PROFESSOR_A_DEFINIR)
						&& (professorCargaHoraria.get(professor) + variavel.getTurma().getDisciplina().getCredito().getCredito()) <= Constantes.CARGA_HORARIA) {
					return false;
				}
			}
		}
		return true;
	}
}
