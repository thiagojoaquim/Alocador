package br.ufs.dcomp.alocador.modelo;

public enum AulaSequencia {

	DOIS(2), QUATRO(4);

	private int aulaSequencia;

	private AulaSequencia(int aulas) {
		this.aulaSequencia = aulas;
	}

	public int getAulaSequencia() {
		return this.aulaSequencia;
	}

}
