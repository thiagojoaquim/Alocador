package br.ufs.dcomp.alocador.csp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CspSolver;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import br.ufs.dcomp.alocador.csp.dominio.Dominio;
import br.ufs.dcomp.alocador.csp.listener.AlocadorListener;
import br.ufs.dcomp.alocador.csp.variaveis.TurmaVariavel;
import br.ufs.dcomp.alocador.modelo.Grade;
import br.ufs.dcomp.alocador.modelo.Turma;

public class Alocador {
	
	private Grade grade;
	
	
	public Alocador(Grade grade) {
	this.grade = grade;	
	}
	
	public List<Turma> alocar() {
		AlocadorCSP alocadorCSP =
				new AlocadorCSP(grade.getTurmas(), grade.getProfessores(), grade.getTurmasDefinidas(), grade.getTurno());
		CspSolver<TurmaVariavel, Dominio> cspSolver = new FlexibleBacktrackingSolver<>();
		Assignment<TurmaVariavel, Dominio> atribuicoes = null;
		Optional<Assignment<TurmaVariavel, Dominio>> container = cspSolver.solve(alocadorCSP);
		if(container.isPresent()) {
			atribuicoes =  cspSolver.solve(alocadorCSP).get();	
		}
		List<TurmaVariavel> variaveis = atribuicoes.getVariables();
		List<Turma> turmasFinais = new ArrayList<>();
		for(TurmaVariavel var : variaveis) {
			Turma turma = var.getTurma();
			turma.setHorario(atribuicoes.getValue(var).getHorario());
			turma.setProfessor(atribuicoes.getValue(var).getProfessor());
			turmasFinais.add(turma);
		}
		return turmasFinais;	
	}
	
}
