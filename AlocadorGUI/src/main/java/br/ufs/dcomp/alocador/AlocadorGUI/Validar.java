package br.ufs.dcomp.alocador.AlocadorGUI;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.component.spinner.Spinner;

@FacesValidator("validador")
public class Validar implements Validator {

	public static  Validar validador;
	
	public static Validar getValidador() {
		if(validador == null)
			validador = new Validar();
		return validador;
		
	}
	
	public UIComponent getAtributo(String atributo) {
		return FacesContext.getCurrentInstance().getViewRoot().findComponent(atributo);
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// get limit
		Spinner cHorariaComp = (Spinner) getAtributo("form:spiner");
		int cargaHoraria = Integer.parseInt(cHorariaComp.getAttributes().get("value").toString());
		SelectCheckboxMenu aux = (SelectCheckboxMenu) getAtributo("form:menuHorario");
		
		String[] qtHorarios = ((String[])aux.getSubmittedValue());
		
		if(qtHorarios.length*2 != cargaHoraria ) {
			FacesMessage msg = new FacesMessage("Quantidade de hórarios incorreta.",
					"A disciplina tem "+cargaHoraria+" créditos de carga horaria, "
							+ "você deve selecionar "+cargaHoraria/2+" horários.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

		
		
	}
}
