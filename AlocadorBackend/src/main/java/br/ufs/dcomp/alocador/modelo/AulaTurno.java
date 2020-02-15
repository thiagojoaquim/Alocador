package br.ufs.dcomp.alocador.modelo;

import java.util.ArrayList;
import java.util.List;

public enum AulaTurno {
	
	PRIMEIRO(1),
	SEGUNDO(2),
	TERCEIRO(3),
	QUARTO(4),
	QUINTO(5),
	SEXTO(6);
	
	private int horario;
	
	private AulaTurno(int horario) {
		this.horario = horario;
	}
	
	public int getHorario() {
		return horario;
	}
	
	@Override
	public String toString() {
		return String.valueOf(horario);
	}
	
	
}
