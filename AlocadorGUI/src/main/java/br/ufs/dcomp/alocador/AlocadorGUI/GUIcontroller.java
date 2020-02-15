package br.ufs.dcomp.alocador.AlocadorGUI;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.component.spinner.Spinner;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
@FacesValidator("validador")
@ManagedBean(name = "guiController")
@SessionScoped
public class GUIcontroller implements Validator {
	private boolean renderedP1, renderedP2, renderedP3;

	private boolean isDiscHorFixo;

	private List<String> diasSemana = new ArrayList<>();

	private String[] diasSelecionados;

	private int maxSelects;
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

	public String[] getDiasSelecionados() {
		return diasSelecionados;
	}

	public void setDiasSelecionados(String[] diasSelecionados) {
		this.diasSelecionados = diasSelecionados;
	}

	public void Printar() {
		System.out.println(diasSelecionados);
		System.out.println(diasSemana);
	}
	public UIComponent getAtributo(String atributo) {
		return FacesContext.getCurrentInstance().getViewRoot().findComponent(atributo);		
	}

	@Override
	   public void validate(FacesContext context, UIComponent component,
	           Object value) throws ValidatorException { 
	       //get limit
		   UIComponent cHorariaComp = (Spinner) getAtributo("form:spiner");
	       SelectCheckboxMenu myComponent = (SelectCheckboxMenu)component;
	       int cargaHoraria = Integer.parseInt(cHorariaComp.getAttributes().get("value").toString());
	      System.out.println( cHorariaComp.getAttributes().get("value"));
	       if (((String[])myComponent.getSubmittedValue()).length*2 > cargaHoraria  ) {
	           FacesMessage msg
	                   = new FacesMessage("Limite de dias excedido.",
	                           "A quantidade de dias selecionados deve ser menor ou igual que 2 vezes a carga horaria.\n"
	                           + "Dias selecionados " + ((String[])myComponent.getSubmittedValue()).length+""
	                           		+ "\n Dias suportados: "+cargaHoraria/2);
	           msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	           throw new ValidatorException(msg);
	       } 
	   }
	//2T1234
	//3T1234
	

}
