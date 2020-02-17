package br.ufs.dcomp.alocador.modelo;

import java.io.Serializable;
import java.util.List;

import javax.swing.ListModel;

public class HorarioMateria implements Serializable, Comparable {

	private String codigo;
	private DiaDaSemana dia;
	private Turno turno;
	private AulaSequencia aulaSequencia;
	private List<AulaTurno> aulaTurno;

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public DiaDaSemana getDia() {
		return dia;
	}

	public void setDia(DiaDaSemana dia) {
		this.dia = dia;
	}

	public AulaSequencia getAulaSequencia() {
		return aulaSequencia;
	}

	public void setAulaSequencia(AulaSequencia aulaSequencia) {
		this.aulaSequencia = aulaSequencia;
	}

	public List<AulaTurno> getAulaTurno() {
		return aulaTurno;
	}

	public void setAulaTurno(List<AulaTurno> aulaTurno) {
		this.aulaTurno = aulaTurno;
	}

	@Override
	public String toString() {
		int d = dia.value();
		String t = turno.value();
		String a = "";
		for (AulaTurno aula : aulaTurno) {
			a += String.valueOf(aula.value());
		}
		return d + t + a;
	}

	@Override
	public int compareTo(Object object) {
		HorarioMateria horario = (HorarioMateria) object;
		if (this.dia.value() < horario.getDia().value()) {
			return -1;
		}
		if (this.dia.value() > horario.getDia().value()) {
			return 1;
		}

		List<AulaTurno> aulasTurno = horario.getAulaTurno();
		int max = aulasTurno.get(aulasTurno.size() - 1).value();
		int maxThis = aulaTurno.get(aulasTurno.size() - 1).value();
		if (maxThis < max)
			return -1;
		if (max > maxThis)
			return 1;
		return 0;
	}

	public int compareDia(HorarioMateria horario) {
		if (this.dia.value() < horario.getDia().value()) {
			return -1;
		}
		if (this.dia.value() > horario.getDia().value()) {
			return 1;
		}

		return 0;
	}
}
