package br.ufs.dcomp.alocador.csp.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufs.dcomp.alocador.modelo.*;

public class DominioCSP {

	private List<HorarioMateria> gerarHorarios (AulaSequencia aulaSequencia) {
		List<AulaTurno> aulasTurno = Arrays.asList(AulaTurno.values());
		List<HorarioMateria> horarios = new ArrayList<>();
		for (DiaDaSemana dia : DiaDaSemana.values()) {
			for (Turno turno : Turno.values()) {
				int maxAulaTurno = turno.NOITE.getTurno().equals("N") ? 4 : 6;
				for(int aulaTurno = AulaTurno.PRIMEIRO.getHorario(); aulaTurno <= maxAulaTurno; aulaTurno += AulaSequencia.DOIS.getAulaSequencia()) {
					HorarioMateria horario = new HorarioMateria();
					horario.setAulaSequencia(aulaSequencia);
					horario.setDia(dia);
					List<AulaTurno> aulasTurnoHorario = new ArrayList<>();
					aulasTurnoHorario.add(aulasTurno.get(aulaTurno));
					aulasTurnoHorario.add(aulasTurno.get(aulaTurno + 1));
					horario.setTurno(turno);
					horarios.add(horario);
					
				}
			}
		}
		return horarios;
	}		
}
