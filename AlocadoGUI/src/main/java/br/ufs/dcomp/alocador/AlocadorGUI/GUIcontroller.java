package br.ufs.dcomp.alocador.AlocadorGUI;

import java.util.List;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Hello world!
 *
 */
@ManagedBean(name = "GUIcontroller")
@SessionScoped
public class GUIcontroller 
{
	private boolean renderedP1,renderedP2,renderedP3;
	
	private boolean isDiscHorFixo;
	
	private List<String> diasSemana = new ArrayList<>();
	{
		renderedP1 = true;
		diasSemana.add("SEGUNDA");
		diasSemana.add("TERÇA");
		diasSemana.add("QUARTA");
		diasSemana.add("QUINTA");
		diasSemana.add("SEXTA");
	}
	
	
	
	public List<String> getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(List<String> diasSemana) {
		this.diasSemana = diasSemana;
	}

	public boolean isDiscHorFixo() {
		return isDiscHorFixo;
	}

	public void setDiscHorFixo(boolean isDiscHorFixo) {
		this.isDiscHorFixo = isDiscHorFixo;
	}

	public boolean isRenderedP1() {
		return renderedP1;
	}

	public void setRenderedP1(boolean renderedP1) {
		this.renderedP1 = renderedP1;
	}

	public boolean isRenderedP2() {
		return renderedP2;
	}

	public void setRenderedP2(boolean renderedP2) {
		this.renderedP2 = renderedP2;
	}

	public boolean isRenderedP3() {
		return renderedP3;
	}

	public void setRenderedP3(boolean renderedP3) {
		this.renderedP3 = renderedP3;
	}
    
	
	
}
