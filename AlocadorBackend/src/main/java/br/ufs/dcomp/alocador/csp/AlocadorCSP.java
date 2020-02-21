package br.ufs.dcomp.alocador.csp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aima.core.search.csp.CSP;
import aima.core.search.csp.CspSolver;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;
import br.ufs.dcomp.alocador.csp.constantes.Constantes;
import br.ufs.dcomp.alocador.csp.dominio.Dominio;
import br.ufs.dcomp.alocador.csp.restricoes.RestricaoAulaParalela;
import br.ufs.dcomp.alocador.csp.restricoes.RestricaoCargaHoraria;
import br.ufs.dcomp.alocador.csp.restricoes.RestricaoPreferenciaProfessor;
import br.ufs.dcomp.alocador.csp.restricoes.RestricaoProfessorADefinir;
import br.ufs.dcomp.alocador.csp.variaveis.TurmaVariavel;
import br.ufs.dcomp.alocador.modelo.AulaSequencia;
import br.ufs.dcomp.alocador.modelo.AulaTurno;
import br.ufs.dcomp.alocador.modelo.Credito;
import br.ufs.dcomp.alocador.modelo.DiaDaSemana;
import br.ufs.dcomp.alocador.modelo.Disciplina;
import br.ufs.dcomp.alocador.modelo.HorarioMateria;
import br.ufs.dcomp.alocador.modelo.Professor;
import br.ufs.dcomp.alocador.modelo.Turma;
import br.ufs.dcomp.alocador.modelo.Turno;

public class AlocadorCSP extends CSP<TurmaVariavel, Dominio> {

	private List<Turma> turmas;
	private List<Professor> professores;
	private List<HorarioMateria> horarios;
	private List<Turma> turmasDefinidas;
	private Turno turno;

	public AlocadorCSP(List<Turma> turmas, List<Professor> professores, List<Turma> turmasDefinidas, Turno turno) {
		
		horarios = gerarHorarios(turno);
		this.professores = professores;
		this.turmas = turmas;
		this.turmasDefinidas = turmasDefinidas;
		if (turmasDefinidas != null)
			turmas.addAll(turmasDefinidas);
		addProfessorADefinir();
		for (Turma turma : turmas) {
			TurmaVariavel turmaVariavel = new TurmaVariavel(turma);
			addVariable(turmaVariavel);
		}
		List<TurmaVariavel> variaveis = getVariables();
		for (TurmaVariavel variavel : variaveis) {
			Domain<Dominio> dominio = null;
			if (variavel.getTurma().getHorario() != null && (!variavel.getTurma().getHorario().isEmpty())) {
				dominio = new Domain<>(gerarDominio(Arrays.asList(variavel.getTurma().getHorario()), professores));
			}else {
				List<List<HorarioMateria>> horariosPossiveis = gerarDominioHorarios(
						variavel.getTurma().getDisciplina().getCredito(), turno);
				dominio = new Domain<>(gerarDominio(horariosPossiveis, professores));
			}
			setDomain(variavel, dominio);
		}
		addRestricao();
	}

	private static List<HorarioMateria> gerarHorarios(Turno turno) {
		List<AulaTurno> aulasTurno = Arrays.asList(AulaTurno.values());
		List<HorarioMateria> horarios = new ArrayList<>();
		for (DiaDaSemana dia : DiaDaSemana.values()) {
			int maxAulaTurno = turno.value().equals("N") ? 4 : 6;
			for (int aulaTurno = AulaTurno.PRIMEIRO.value(); aulaTurno <= maxAulaTurno; aulaTurno += AulaSequencia.DOIS
					.getAulaSequencia()) {
				HorarioMateria horario = new HorarioMateria();
				horario.setAulaSequencia(AulaSequencia.DOIS);
				horario.setDia(dia);
				List<AulaTurno> aulasTurnoHorario = new ArrayList<>();
				for (int i = aulaTurno; i <= (aulaTurno + AulaSequencia.DOIS.getAulaSequencia() - 1); i++) {
					aulasTurnoHorario.add(aulasTurno.get(i - 1));
				}
				horario.setTurno(turno);
				horario.setAulaTurno(aulasTurnoHorario);
				horarios.add(horario);
			}
		}
		return horarios;
	}

	private static List<List<HorarioMateria>> gerarDominioHorarios(Credito credito, Turno turno) {
		List<List<HorarioMateria>> listaHorarioMateria = new ArrayList<List<HorarioMateria>>();
		List<HorarioMateria> horarios = gerarHorarios(turno);
		if (credito == Credito.DOIS) {
			for (HorarioMateria horario : horarios) {
				List<HorarioMateria> h = new ArrayList<HorarioMateria>();
				h.add(horario);
				listaHorarioMateria.add(h);
			}
			return listaHorarioMateria;
		}
		if (credito == Credito.QUATRO) {
			for (int i = 0; i < horarios.size(); i++) {
				List<HorarioMateria> h = new ArrayList<HorarioMateria>();
				h.add(horarios.get(i));
				int creditos = horarios.get(i).getAulaSequencia().getAulaSequencia();
				for (int j = i + 1; j < horarios.size(); j++) {
					if (horarios.get(i).getDia() == horarios.get(j).getDia() && j != (i + 1))
						continue;
					if ((horarios.get(j).getAulaSequencia().getAulaSequencia() + creditos) <= credito.getCredito()) {
						h.add(horarios.get(j));
						listaHorarioMateria.add(h);
						h = new ArrayList<HorarioMateria>();
						h.add(horarios.get(i));
						creditos = horarios.get(i).getAulaSequencia().getAulaSequencia();
					}
				}
			}
		}
		if (credito == Credito.SEIS) {
			for (int i = 0; i < horarios.size(); i++) {
				List<HorarioMateria> h = new ArrayList<HorarioMateria>();
				h.add(horarios.get(i));
				int creditos = horarios.get(i).getAulaSequencia().getAulaSequencia();
				for (int j = i + 1; j < horarios.size(); j++) {
					h.add(horarios.get(j));
					creditos += horarios.get(i).getAulaSequencia().getAulaSequencia();
					for (int k = j + 1; k < horarios.size(); k++) {
						if (horarios.get(j).getDia() == horarios.get(k).getDia())
							continue;
						if ((horarios.get(k).getAulaSequencia().getAulaSequencia() + creditos) <= credito
								.getCredito()) {
							h.add(horarios.get(k));
							listaHorarioMateria.add(h);
							h = new ArrayList<>();
							h.add(horarios.get(i));
							h.add(horarios.get(j));
							creditos = horarios.get(i).getAulaSequencia().getAulaSequencia();
							creditos += horarios.get(j).getAulaSequencia().getAulaSequencia();
						}
					}
				}
			}
		}
		return listaHorarioMateria;
	}

	private List<Dominio> gerarDominio(List<List<HorarioMateria>> horarios, List<Professor> professores) {
		List<Dominio> resultado = new ArrayList<>();
		for (List<HorarioMateria> horario : horarios) {
			for (Professor professor : professores) {
				Dominio dominio = new Dominio();
				dominio.setHorario(horario);
				dominio.setProfessor(professor);
				resultado.add(dominio);
			}
		}
		return resultado;
	}

	private void addRestricao() {
		for (int i = 0; i < getVariables().size(); i++) {
			for (int j = i + 1; j < getVariables().size(); j++) {
				addConstraint(new RestricaoAulaParalela(getVariables().get(i), getVariables().get(j)));
			}
		}
		for (TurmaVariavel var : getVariables()) {
			addConstraint(new RestricaoPreferenciaProfessor(var));
			addConstraint(new RestricaoProfessorADefinir(var, getVariables()));
		}
		addConstraint(new RestricaoCargaHoraria(getVariables()));
	}

	private void addProfessorADefinir() {
		Professor professor = new Professor();
		professor.setMatricula(null);
		professor.setNome(Constantes.PROFESSOR_A_DEFINIR);
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		for (Turma turma : turmas) {
			disciplinas.add(turma.getDisciplina());
		}
		professor.setDisciplinasDePreferencia(disciplinas);
		professores.add(professor);
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<HorarioMateria> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioMateria> horarios) {
		this.horarios = horarios;
	}

	public List<Turma> getTurmasDefinidas() {
		return turmasDefinidas;
	}

	public void setTurmasDefinidas(List<Turma> turmasDefinidas) {
		this.turmasDefinidas = turmasDefinidas;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}
