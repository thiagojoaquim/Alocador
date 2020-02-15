package br.ufs.dcomp.alocador.modelo;

import java.util.ArrayList;
import java.util.List;

public enum Credito {
	
	DOIS(2, 30),
	QUATRO(4, 60),
	SEIS(6, 90);
	
	private int creditos;
	private int cargaHoraria;
	
	private Credito(int credito, int cargaHoraria) {
		this.creditos = credito;
		this.cargaHoraria = cargaHoraria;
	}

	public int getCreditos() {
		return creditos;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}
	
	public static List<Credito> toList() {
		List<Credito> creditos = new ArrayList<>();
		creditos.add(DOIS);
		creditos.add(QUATRO);
		creditos.add(SEIS);
		return creditos;
	}
}
