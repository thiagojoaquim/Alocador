package br.ufs.dcomp.alocador.AlocadorGUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufs.dcomp.alocador.modelo.AulaSequencia;
import br.ufs.dcomp.alocador.modelo.AulaTurno;
import br.ufs.dcomp.alocador.modelo.Credito;
import br.ufs.dcomp.alocador.modelo.DiaDaSemana;
import br.ufs.dcomp.alocador.modelo.HorarioMateria;
import br.ufs.dcomp.alocador.modelo.Turno;

public class Teste {
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
							creditos += horarios.get(k).getAulaSequencia().getAulaSequencia();
							listaHorarioMateria.add(h);
							h = new ArrayList<HorarioMateria>();
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
	public static void main(String[] args) {
		System.out.println(gerarDominioHorarios(Credito.DOIS, Turno.TARDE).size());
		System.out.println(gerarDominioHorarios(Credito.SEIS,Turno.TARDE).size());
	}
}
