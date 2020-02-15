package br.ufs.dcomp.alocador.modelo;

public enum Horario {
	
	PRIMEIRO(1),
	SEGUNDO(2),
	TERCEIRO(3),
	QUARTO(4),
	QUNTO(5),
	SEXTO(6);
	
	private int horario;
	
	private Horario(int horario) {
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
