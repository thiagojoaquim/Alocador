package br.ufs.dcomp.alocador.csp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CspSolver;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import br.ufs.dcomp.alocador.csp.variaveis.TurmaVariavel;
import br.ufs.dcomp.alocador.modelo.Disciplina;
import br.ufs.dcomp.alocador.modelo.Professor;
import br.ufs.dcomp.alocador.modelo.Turma;

public class Rodar {
	public static void main(String[] args) {
		Turma turma = new Turma();
		Turma turma2 = new Turma();
		Turma turma3 = new Turma();
		Disciplina disciplina = new Disciplina();
		Disciplina disciplina2 = new Disciplina();
		Disciplina disciplina3 = new Disciplina();
		disciplina.setCodigo("COMP1");
		disciplina2.setCodigo("COMP2");
		disciplina3.setCodigo("COMP3");
		turma.setDisciplina(disciplina);
		turma2.setDisciplina(disciplina2);
		turma3.setDisciplina(disciplina3);
		List<Turma> disciplinas = new ArrayList<>();
		disciplinas.add(turma3);
		disciplinas.add(turma2);
		disciplinas.add(turma);
		List<Professor> professores = new ArrayList<>();
		Professor professor = new Professor();
		professor.setMatricula("tt");
		professores.add(professor);
		professor = new Professor();
		professor.setMatricula("tttt");
		professores.add(professor);
		CSPProfessorToDisciplina cspProfessorToDisciplina = new CSPProfessorToDisciplina(disciplinas, professores);
		CspSolver<TurmaVariavel, List<Professor>> solver = new FlexibleBacktrackingSolver<>();
		Optional result = solver.solve(cspProfessorToDisciplina);
		Assignment<TurmaVariavel, Professor> assignment = (Assignment<TurmaVariavel, Professor>) result.get();
		assignment.getVariables();
		for(TurmaVariavel turmaV : assignment.getVariables()) {
			System.out.print("turma " + turma.getDisciplina().getCodigo() + " ");
			
		}
	}
}
