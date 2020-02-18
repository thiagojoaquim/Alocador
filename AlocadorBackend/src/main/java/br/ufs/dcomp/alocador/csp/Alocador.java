package br.ufs.dcomp.alocador.csp;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CspSolver;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import br.ufs.dcomp.alocador.csp.dominio.Dominio;
import br.ufs.dcomp.alocador.csp.variaveis.TurmaVariavel;
import br.ufs.dcomp.alocador.modelo.Grade;
import br.ufs.dcomp.alocador.modelo.Turma;

public class Alocador {
	
	private AlocadorCSP alocadorCSP;
	private Grade grade;
	
	
	public Alocador(Grade grade) {
	this.grade = grade;	
	}
	
	public List<Turma> alocar() {
		AlocadorCSP alocadorCSP =
				new AlocadorCSP(grade.getTurmas(), grade.getProfessores(), grade.getTurmasDefinidas(), grade.getTurno());
		CspSolver<TurmaVariavel, Dominio> cspSolver = new FlexibleBacktrackingSolver<>();
		Assignment<TurmaVariavel, Dominio> atribucoes =  cspSolver.solve(alocadorCSP).get();
		List<TurmaVariavel> variaveis = atribucoes.getVariables();
		List<Turma> turmasFinais = new ArrayList<>();
		for(TurmaVariavel var : variaveis) {
			Turma turma = var.getTurma();
			turma.setHorario(atribucoes.getValue(var).getHorario());
			turma.setProfessor(atribucoes.getValue(var).getProfessor());
			turmasFinais.add(turma);
		}
		return turmasFinais;
	
	}
	
}
