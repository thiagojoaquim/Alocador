package br.ufs.dcomp.alocador.modelo;

public enum DiaDaSemana {
	SEGUNDA(2),
	TERCA(3),
	QUARTA(4),
	QUINTA(5),
	SEXTA(6);
	
	private int dia;
	
	private DiaDaSemana(int dia) {
		this.dia = dia;
	}

	public int value() {
		return dia;
	}	
}
