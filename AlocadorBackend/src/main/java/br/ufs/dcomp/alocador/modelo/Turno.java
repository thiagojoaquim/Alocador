package br.ufs.dcomp.alocador.modelo;

public enum Turno {
	
	MANHA("M"),
	TARDE("T"),
	NOITE("N");
	
	private String turno;
	
	private Turno (String turno) {
		this.turno = turno;
	}
	
	public String value() {
		return this.turno;
	}
}
