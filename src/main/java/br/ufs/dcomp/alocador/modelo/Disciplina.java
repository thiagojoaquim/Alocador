package br.ufs.dcomp.alocador.modelo;

import java.io.Serializable;

public class Disciplina implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2120979652764778026L;
	private String nome;
	private int cargaHoraria;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}
