package br.ufs.dcomp.alocador.modelo;

import java.io.Serializable;
import java.util.List;

import javax.swing.ListModel;

public class HorarioMateria implements Serializable {

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

}
