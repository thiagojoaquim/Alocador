package br.ufs.dcomp.alocador.csp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import br.ufs.dcomp.alocador.csp.dominio.Dominio;
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
	private Map<Turma, List<TurmaVariavel>> referencias;
	private List<Dominio> dominioProblema;

	public AlocadorCSP(List<Turma> turmas, List<Professor> professores, List<Turma> turmasDefinidas, Turno turno) {
		this.setTurmas(turmas);
		this.setProfessores(professores);
		this.setTurmasDefinidas(turmasDefinidas);
	}

	public AlocadorCSP(List<Turma> turmas, List<Professor> professores,
			List<Turma> turmasDefinidas) {
		
		horarios = gerarHorarios(Turno.TARDE);
		this.professores = professores;
		this.turmas = turmas;
		this.turmasDefinidas = turmasDefinidas;
		referencias = new HashMap<Turma, List<TurmaVariavel>>();
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

	private int compararAulasSeguidas(List<HorarioMateria> horarios) {
		if (horarios.size() <= 2)
			return 1;
		for (int i = 1; i < horarios.size(); i++) {
			if (horarios.get(i).compareDia(horarios.get(i - 1)) != 0) {
				return 1;
			}
		}
		return 0;
	}

	public List<Dominio> gerarDominio(List<HorarioMateria> horarios, List<Professor> professores) {
		List<Dominio> resultado = new ArrayList<Dominio>();
		for (HorarioMateria horario : horarios) {
			for (Professor professor : professores) {
				Dominio dominio = new Dominio();
				dominio.setHorario(horario);
				dominio.setProfessor(professor);
				resultado.add(dominio);
			}
		}
		return resultado;
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

	public static void main(String[] args) {
		List<HorarioMateria> lista = gerarHorarios(Turno.TARDE);
		for (HorarioMateria horario : lista) {
			System.out.print(horario.toString() + " ");
		}
		Turma turma = new Turma();
		Disciplina disciplina = new Disciplina();
		disciplina.setCodigo("COMP001");
		disciplina.setCredito(Credito.SEIS);
		turma.setDisciplina(disciplina);
	}
}
