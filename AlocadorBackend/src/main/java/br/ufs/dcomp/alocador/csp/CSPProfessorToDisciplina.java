package br.ufs.dcomp.alocador.csp;

import java.util.List;


import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;
import br.ufs.dcomp.alocador.csp.variaveis.TurmaVariavel;
import br.ufs.dcomp.alocador.modelo.Professor;
import br.ufs.dcomp.alocador.modelo.Turma;

public class CSPProfessorToDisciplina extends CSP{
	
	public List<Turma> disciplinas;
	public List<Professor> professores;

	public CSPProfessorToDisciplina(List<Turma> disciplinas, List<Professor> professores) {
		this.disciplinas = disciplinas;
		this.professores = professores;
		
		for(Turma turma : disciplinas) {
			TurmaVariavel turmaVariavel = new TurmaVariavel(turma);
			addVariable(turmaVariavel);
		}
		List<Variable> variaveis = getVariables();
		Domain<Professor> dominio = new Domain<>(professores);
		for (Variable variavel : variaveis) {
			setDomain(variavel, dominio);
		}
	}
}
