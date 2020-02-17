package br.ufs.dcomp.alocador.AlocadorGUI;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import br.ufs.dcomp.alocador.modelo.AulaSequencia;
import br.ufs.dcomp.alocador.modelo.AulaTurno;
import br.ufs.dcomp.alocador.modelo.DiaDaSemana;
import br.ufs.dcomp.alocador.modelo.Disciplina;
import br.ufs.dcomp.alocador.modelo.HorarioMateria;
import br.ufs.dcomp.alocador.modelo.Turma;
import br.ufs.dcomp.alocador.modelo.Turno;

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
	
	private String[] diasSelecionados;

	private int tempCreditos;

	private String tempCodDisc, nomeDisc;

	private Validar validador;
	
	private String tempTurno;

	private List<Turma> turmas = new ArrayList<>();

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
		validador = Validar.getValidador();
	}
	public void removerTurma(Turma t) {
		if(!turmas.isEmpty())
			turmas.remove(t);

	}
	public boolean isCheio() {
		return turmas.size()>5;
	}
	
	public String[] getDiasSelecionados() {
		return diasSelecionados;
	}

	public void setDiasSelecionados(String[] diasSelecionados) {
		this.diasSelecionados = diasSelecionados;
	}

	public String getTempTurno() {
		return tempTurno;
	}

	public void setTempTurno(String tempTurno) {
		this.tempTurno = tempTurno;
	}

	public boolean isTurmaInserida() {
		return !turmas.isEmpty();
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
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

	private void preencherHorarios(String turno, String[] dias) {
		String turnSimbol;
		turnSimbol = turno.equals("MANHÃ") ? "M" : "T";
		if (turno.equals("NOITE"))
			turnSimbol = "N";
		for (String dia : dias) {
			int control = diasSemana.indexOf(dia) + 2;
			horarios.add(control + turnSimbol + "12");
			horarios.add(control + turnSimbol + "34");
			if (!turnSimbol.equals("N"))
				horarios.add(control + turnSimbol + "56");
		}

	}

	public void validarTH() {
		SelectOneMenu turno = (SelectOneMenu) validador.getAtributo("form:turno");
		SelectCheckboxMenu checkDias = (SelectCheckboxMenu) validador.getAtributo("form:menu");
		String nomeTurno = turno.getSubmittedValue().toString();
		String[] dias = ((String[]) checkDias.getSubmittedValue());
		if (nomeTurno.isEmpty() || (dias.length == 0))
			horarios.clear();
		else {
			horarios.clear();
			preencherHorarios(nomeTurno, dias);
		}

	}

//	public boolean existeNome(String nomeTurma) {
//		for (Disciplina aux : disciplinas) {
//			if (aux.getNome().equals(nomeTurma))
//				return true;
//		}
//		return false;
//	}
//
//	public boolean existeCodigo(String nomeCodigo) {
//		for (Disciplina aux : disciplinas) {
//			if (aux.getCodigo().equals(nomeCodigo))
//				return true;
//		}
//		return false;
//	}
//	
	private boolean horarioSeguido(String h1,String h2) {
		if(h1.equals("12") && h2.equals("34"))
			return true;
		if(h1.equals("34") && h2.equals("56"))
			return true;
		return false;
	}

	private List<String> unificaHorario() {
		List<String> listaHorarios = new ArrayList<>();
		List<String> listaHorarioFinal = new ArrayList<>();
		for (String s : horarioSelecionados) {
			listaHorarios.add(s);
		}

		for (int i = 0;i<listaHorarios.size();i++) {
			String h = listaHorarios.get(i);
			boolean isSeguido = false;
			for (int j =i+1 ;j<listaHorarios.size();j++) {
				String s = listaHorarios.get(j);
				if (h.subSequence(0, 2).equals(s.subSequence(0, 2))) {
					if(horarioSeguido(h.substring(2,4), s.substring(2,4))) {
					String temp = h.substring(0, 2) + h.substring(2, 4) + s.substring(2, 4);
					i++;
					listaHorarioFinal.add(temp);
					isSeguido = true;
					break;
					}
				}
			}
			if(!isSeguido) {
				if(!listaHorarioFinal.contains(h))
					listaHorarioFinal.add(h);
			}
			if(listaHorarios.isEmpty())
				break;
		}
		return listaHorarioFinal;
	}

	private List<String> unificarDiasHorario(List<String> horarios) {
		List<String> resp = new ArrayList<>();
		String parcial = "";
		String fim = "";

		for (int i = 0; i < horarios.size(); i++) {
			parcial = horarios.get(i).substring(0,1);
			boolean match = false;
			for (int j = i + 1; j < horarios.size(); j++) {
				
				if (horarios.get(i).length() == horarios.get(j).length()) {
					System.out.println(parcial);
					if (horarios.get(i).substring(1).equals(horarios.get(j).substring(1))) {
						parcial = parcial + horarios.get(j).substring(0, 1);
						fim = horarios.get(i).substring(1, horarios.get(i).length());
						i=j;
						match = true;
					}
				}
			}

			if (!match) {
				resp.add(horarios.get(i));
				parcial = "";
			}
		}

		if (!(parcial + fim).isEmpty())
			resp.add(parcial + fim);
		return resp;

	}
	private List<HorarioMateria>  montarHorarioMateria() {
		List<String> horariosIndividuais = unificaHorario(); // HORARIOS UNIFICADOS POR SEQUENCIA
															// EX: 2T1234
		List<String> horariosJuntos = unificarDiasHorario(horariosIndividuais);// horarios junto ex: 246T12
		List<HorarioMateria> resp = new ArrayList<>();		
		for(String s: horariosIndividuais) {
			HorarioMateria aux = new HorarioMateria();
			AulaSequencia aula = s.length() <5 ? AulaSequencia.DOIS : AulaSequencia.QUATRO;
			List<AulaTurno> auxAT = new ArrayList<>();
			if(s.contains("12")) {
				auxAT.add(AulaTurno.PRIMEIRO);
				auxAT.add(AulaTurno.SEGUNDO);
			}
			if(s.contains("34")) {
				auxAT.add(AulaTurno.TERCEIRO);
				auxAT.add(AulaTurno.QUARTO);
			}			
			if(s.contains("56")) {
				auxAT.add(AulaTurno.QUINTO);
				auxAT.add(AulaTurno.SEXTO);
			}
			Turno t ;
			t = s.contains("T") ? Turno.TARDE : Turno.MANHA;
			if(s.contains("N"))
				t = Turno.NOITE;
			DiaDaSemana dia = null;
			if(s.substring(0,1).equals("2"))
				dia = DiaDaSemana.SEGUNDA;
			if(s.substring(0,1).equals("3"))
				dia = DiaDaSemana.TERCA;
			if(s.substring(0,1).equals("4"))
				dia = DiaDaSemana.QUINTA;
			if(s.substring(0,1).equals("5"))
				dia = DiaDaSemana.QUINTA;
			if(s.substring(0,1).equals("6"))
				dia = DiaDaSemana.SEXTA;
			aux.setAulaSequencia(aula);
			aux.setAulaTurno(auxAT);
			aux.setCodigo(horariosJuntos.toString());
			aux.setDia(dia);
			aux.setTurno(t);
			resp.add(aux);
									
		}
		return resp;
	}
	public boolean existeNome(String nome) {
		for(Turma t: turmas) {
			if(t.getDisciplina().getNome().equals(nome))
				return true;
		}
		return false;
	}
	public boolean existeCodigo(String cod) {
		for(Turma t: turmas) {
			if(t.getDisciplina().getNome().equals(cod))
				return true;
		}
		return false;
	}
	private void limparVariaveis() {
		nomeDisc = tempCodDisc =  "";
		tempCreditos=2;
		horarioSelecionados = diasSelecionados = new String[0];

		
	}
	public void adicionarDisciplina(){
		if(existeNome(nomeDisc) || existeCodigo(tempCodDisc)) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Já existe uma disciplina com esses dados.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
			
		}else {
		Disciplina aux = new Disciplina();
		aux.setCargaHoraria(tempCreditos);
		aux.setCodigo(tempCodDisc);
		aux.setNome(nomeDisc);
		Turma tempt = new Turma();
		tempt.setDisciplina(aux);
		if(isDiscHorFixo) 
			tempt.setHorario(montarHorarioMateria());
		turmas.add(tempt);
		limparVariaveis();
		}
		
	}
	public void mostrarUni() {
		adicionarDisciplina();

	}
	public void limparPaginas() {
		renderedP1 = renderedP2 = renderedP3 = false;
	}
	
	public void visuP2() {
		limparPaginas();
		renderedP2 = true;
	}
	public void visuP1() {
		limparPaginas();
		renderedP1 = true;
	}
	public List<Disciplina> getDiscs(){
		List<Disciplina> aux = new ArrayList<>();
		for(Turma t : turmas)
			aux.add(t.getDisciplina());
		return aux;
	}
}
