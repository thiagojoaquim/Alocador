package br.ufs.dcomp.alocador.AlocadorGUI;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 * Hello world!
 *
 */

@ManagedBean(name = "guiController")
@SessionScoped
public class GUIcontroller {
	private boolean renderedP1, renderedP2, renderedP3;

	private boolean isDiscHorFixo;

	private List<String> diasSemana = new ArrayList<>();

	private List<String> turnos = new ArrayList<String>();

	private List<String> horarios = new ArrayList<String>();

	private String[] horarioSelecionados;
	
	private int tempCreditos;
	
	private List<Object> discInseridas = new ArrayList();

	
	private String tempCodDisc,nomeDisc;
	
	private Validar validador;

	{
		renderedP1 = true;
		diasSemana.add("SEGUNDA");
		diasSemana.add("TERÇA");
		diasSemana.add("QUARTA");
		diasSemana.add("QUINTA");
		diasSemana.add("SEXTA");
		turnos.add("MANHÃ");
		turnos.add("TARDE");
		turnos.add("NOITE");
		validador = validador.getValidador();
	}
	public boolean isTurmaInserida() {
		return ! discInseridas.isEmpty();
	}
	public List<Object> getDiscInseridas() {
		return discInseridas;
	}


	public void setDiscInseridas(List<Object> discInseridas) {
		this.discInseridas = discInseridas;
	}


	public int getTempCreditos() {
		return tempCreditos;
	}


	public void setTempCreditos(int tempCreditos) {
		this.tempCreditos = tempCreditos;
	}


	public List<String> getHorarios() {
		return horarios;
	}
	

	public String getTempCodDisc() {
		return tempCodDisc;
	}


	public void setTempCodDisc(String tempCodDisc) {
		this.tempCodDisc = tempCodDisc;
	}
	

	public String getNomeDisc() {
		return nomeDisc;
	}


	public void setNomeDisc(String nomeDisc) {
		this.nomeDisc = nomeDisc;
	}




	public List<String> getTurnos() {
		return turnos;
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

	
	public String[] getHorarioSelecionados() {
		return horarioSelecionados;
	}


	public void setHorarioSelecionados(String[] horarioSelecionados) {
		this.horarioSelecionados = horarioSelecionados;
	}


	private void preencherHorarios(String turno,String[] dias) {
		String turnSimbol;
		turnSimbol = turno.equals("MANHÃ")? "M" : "T";
		if(turno.equals("NOITE"))
			turnSimbol = "N";
		for(String dia: dias) {
			int control  = diasSemana.indexOf(dia)+2;
			horarios.add(control+turnSimbol+"12");
			horarios.add(control+turnSimbol+"34");
			if(!turnSimbol.equals("N"))
				horarios.add(control+turnSimbol+"56");
		}
		
	}

	public void validarTH() {
		SelectOneMenu turno = (SelectOneMenu) validador.getAtributo("form:turno");
		SelectCheckboxMenu checkDias = (SelectCheckboxMenu) validador.getAtributo("form:menu");
		String nomeTurno = turno.getSubmittedValue().toString();
		String[] dias = ((String[])checkDias.getSubmittedValue());
		System.out.println(nomeTurno);
		if(nomeTurno.isEmpty()||(dias.length == 0))
			horarios.clear();
		else {
			horarios.clear();
			preencherHorarios(nomeTurno,dias);
		}

	}

}
