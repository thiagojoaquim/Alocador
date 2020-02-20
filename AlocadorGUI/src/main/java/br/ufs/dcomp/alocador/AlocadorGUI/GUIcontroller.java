package br.ufs.dcomp.alocador.AlocadorGUI;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import br.ufs.dcomp.alocador.csp.Alocador;
import br.ufs.dcomp.alocador.modelo.AulaSequencia;
import br.ufs.dcomp.alocador.modelo.AulaTurno;
import br.ufs.dcomp.alocador.modelo.Credito;
import br.ufs.dcomp.alocador.modelo.DiaDaSemana;
import br.ufs.dcomp.alocador.modelo.Disciplina;
import br.ufs.dcomp.alocador.modelo.Grade;
import br.ufs.dcomp.alocador.modelo.HorarioMateria;
import br.ufs.dcomp.alocador.modelo.Professor;
import br.ufs.dcomp.alocador.modelo.Turma;
import br.ufs.dcomp.alocador.modelo.Turno;

/**
 * Hello world!
 *
 */

@ManagedBean(name = "guiController")
@SessionScoped
public class GUIcontroller {
	
	private Serializador serializador ;
	private boolean renderedP1, renderedP2, renderedP3,renderedCD,renderedPF;

	private boolean isDiscHorFixo;

	private List<String> diasSemana = new ArrayList<>();

	private List<String> turnos = new ArrayList<String>();

	private List<String> horarios = new ArrayList<String>();

	private String[] horarioSelecionados;

	private String[] diasSelecionados;

	private String[] preferencias;

	private int tempCreditos;

	private String tempCodDisc, nomeDisc;

	private Validar validador;

	private String tempTurno;

	private String nomeProf;

	private String matricula;

	private List<Turma> turmas = new ArrayList<>();

	private List<Professor> professores = new ArrayList<>();
	
	private List<Turma> turmaNormal = new ArrayList<Turma>();
	
	private List<Turma> turmaFixa = new ArrayList<Turma>();
	
	private Turno turno;
	
	private List<Turma> resposta = new ArrayList<Turma>();
	
	private List<Professor> professoresAUX = new ArrayList<>();

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
		serializador = Serializador.getSerializador();
	}

	public void removerTurma(Turma t) {
		if (!turmas.isEmpty())
			turmas.remove(t);

	}
	

	public List<Professor> getProfessoresAUX() {
		return professoresAUX;
	}


	public void setProfessoresAUX(List<Professor> professoresAUX) {
		this.professoresAUX = professoresAUX;
	}


	public boolean isRenderedCD() {
		return renderedCD;
	}

		
	public boolean isRenderedPF() {
		return renderedPF;
	}


	public void setRenderedPF(boolean renderedPF) {
		this.renderedPF = renderedPF;
	}


	public void setRenderedCD(boolean renderedCD) {
		this.renderedCD = renderedCD;
	}


	public List<Turma> getTurmaNormal() {
		return turmaNormal;
	}


	public void setTurmaNormal(List<Turma> turmaNormal) {
		this.turmaNormal = turmaNormal;
	}
	
	
	public List<Turma> getResposta() {
		return resposta;
	}


	public void setResposta(List<Turma> resposta) {
		this.resposta = resposta;
	}


	public List<Turma> getTurmaFixa() {
		return turmaFixa;
	}


	public void setTurmaFixa(List<Turma> turmaFixa) {
		this.turmaFixa = turmaFixa;
	}


	public String[] getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(String[] preferencias) {
		this.preferencias = preferencias;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public boolean temProf() {
		return !professores.isEmpty();
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}
	public boolean isCheio() {
		int sum=0;
		int Tcredito = 15;
		if(turno != null)
			if(turno.equals(turno.NOITE))
				Tcredito = 10;
		for(Turma t: turmas) {
			sum += t.getDisciplina().getCredito().getCredito();
		}

		if((sum/2)+1 >Tcredito || (sum/2)+2>Tcredito || (sum/2)+3>Tcredito) {
			return true;
		}
		return false;
	}
	public boolean podeCarregar() {
		return turmas.isEmpty();
	}

	public String[] getDiasSelecionados() {
		return diasSelecionados;
	}

	public void setDiasSelecionados(String[] diasSelecionados) {
		this.diasSelecionados = diasSelecionados;
	}

	public String getNomeProf() {
		return nomeProf;
	}

	public void setNomeProf(String nomeProf) {
		this.nomeProf = nomeProf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTempTurno() {
		return tempTurno;
	}

	public void setTempTurno(String tempTurno) {
		turno = tempTurno.equals("MANHÃ") ? Turno.MANHA : Turno.TARDE;
		if(tempTurno.equals("NOITE"))
			turno = Turno.NOITE;
		System.out.println(turno);
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
	private boolean horarioSeguido(String h1, String h2) {
		if (h1.equals("12") && h2.equals("34"))
			return true;
		if (h1.equals("34") && h2.equals("56"))
			return true;
		return false;
	}

	private List<String> unificaHorario() {
		List<String> listaHorarios = new ArrayList<>();
		List<String> listaHorarioFinal = new ArrayList<>();
		for (String s : horarioSelecionados) {
			listaHorarios.add(s);
		}

		for (int i = 0; i < listaHorarios.size(); i++) {
			String h = listaHorarios.get(i);
			boolean isSeguido = false;
			for (int j = i + 1; j < listaHorarios.size(); j++) {
				String s = listaHorarios.get(j);
				if (h.subSequence(0, 2).equals(s.subSequence(0, 2))) {
					if (horarioSeguido(h.substring(2, 4), s.substring(2, 4))) {
						String temp = h.substring(0, 2) + h.substring(2, 4) + s.substring(2, 4);
						i++;
						listaHorarioFinal.add(temp);
						isSeguido = true;
						break;
					}
				}
			}
			if (!isSeguido) {
				if (!listaHorarioFinal.contains(h))
					listaHorarioFinal.add(h);
			}
			if (listaHorarios.isEmpty())
				break;
		}
		return listaHorarioFinal;
	}

	private List<String> unificarDiasHorario(List<String> horarios) {
		List<String> resp = new ArrayList<>();
		String parcial = "";
		String fim = "";

		for (int i = 0; i < horarios.size(); i++) {
			parcial = horarios.get(i).substring(0, 1);
			boolean match = false;
			for (int j = i + 1; j < horarios.size(); j++) {

				if (horarios.get(i).length() == horarios.get(j).length()) {
					System.out.println(parcial);
					if (horarios.get(i).substring(1).equals(horarios.get(j).substring(1))) {
						parcial = parcial + horarios.get(j).substring(0, 1);
						fim = horarios.get(i).substring(1, horarios.get(i).length());
						i = j;
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

	private List<HorarioMateria> montarHorarioMateria() {
		List<String> horariosIndividuais = unificaHorario(); // HORARIOS UNIFICADOS POR SEQUENCIA
																// EX: 2T1234
		List<String> horariosJuntos = unificarDiasHorario(horariosIndividuais);// horarios junto ex: 246T12
		List<HorarioMateria> resp = new ArrayList<>();
		for (String s : horariosIndividuais) {
			HorarioMateria aux = new HorarioMateria();
			AulaSequencia aula = s.length() < 5 ? AulaSequencia.DOIS : AulaSequencia.QUATRO;
			List<AulaTurno> auxAT = new ArrayList<>();
			if (s.contains("12")) {
				auxAT.add(AulaTurno.PRIMEIRO);
				auxAT.add(AulaTurno.SEGUNDO);
			}
			if (s.contains("34")) {
				auxAT.add(AulaTurno.TERCEIRO);
				auxAT.add(AulaTurno.QUARTO);
			}
			if (s.contains("56")) {
				auxAT.add(AulaTurno.QUINTO);
				auxAT.add(AulaTurno.SEXTO);
			}
			Turno t;
			t = s.contains("T") ? Turno.TARDE : Turno.MANHA;
			if (s.contains("N"))
				t = Turno.NOITE;
			DiaDaSemana dia = null;
			if (s.substring(0, 1).equals("2"))
				dia = DiaDaSemana.SEGUNDA;
			if (s.substring(0, 1).equals("3"))
				dia = DiaDaSemana.TERCA;
			if (s.substring(0, 1).equals("4"))
				dia = DiaDaSemana.QUINTA;
			if (s.substring(0, 1).equals("5"))
				dia = DiaDaSemana.QUINTA;
			if (s.substring(0, 1).equals("6"))
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
		for (Turma t : turmas) {
			if (t.getDisciplina().getNome().equals(nome))
				return true;
		}
		return false;
	}

	public boolean existeCodigo(String cod) {
		for (Turma t : turmas) {
			if (t.getDisciplina().getCodigo().equals(cod))
				return true;
		}
		return false;
	}

	private void limparVariaveis() {
		nomeDisc = tempCodDisc = "";
		tempCreditos = 2;
		horarioSelecionados = diasSelecionados = new String[0];

	}

	public void adicionarDisciplina() {
		if (existeNome(nomeDisc) || existeCodigo(tempCodDisc)) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Já existe uma disciplina com esses dados.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);

		} else {
			Disciplina aux = new Disciplina();
			Credito cr = tempCreditos == 2 ? Credito.DOIS : Credito.QUATRO;
			if (tempCreditos == 6)
				cr = Credito.SEIS;
			aux.setCredito(cr);
			aux.setCodigo(tempCodDisc);
			aux.setNome(nomeDisc);
			Turma tempt = new Turma();
			tempt.setDisciplina(aux);
			if (isDiscHorFixo) {
				tempt.setHorario(montarHorarioMateria());
				turmaFixa.add(tempt);
				
			}else {
				turmaNormal.add(tempt);
			}
			turmas.add(tempt);
			limparVariaveis();
		}

	}

	public void mostrarUni() {
		adicionarDisciplina();

	}

	public void limparPaginas() {
		renderedP1 = renderedP2 = renderedP3=
				 renderedCD= renderedPF = false;
	}

	public void visuP2() {
		limparPaginas();
		renderedP2 = true;
	}

	public void visuP1() {
		limparPaginas();
		renderedP1 = true;
	}

	public List<Disciplina> getDiscs() {
		List<Disciplina> aux = new ArrayList<>();
		for (Turma t : turmas)
			aux.add(t.getDisciplina());
		return aux;
	}

	private Disciplina procuraDisc(String nome) {
		for (Turma t : turmas)
			if (t.getDisciplina().getNome().equals(nome))
				return t.getDisciplina();
		return null;
	}

	private boolean existeMatriculaNome(String nome, String matricula) {
		for (Professor p : professores)
			if (p.getMatricula().equals(matricula) || p.getNome().equals(nome))
				return true;
		return false;
	}

	public void adicionarProf() {
		if (preferencias.length > 5 || preferencias.length < 3) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("O professor deve ter de 3 a 5 preferências.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);

		} else if (existeMatriculaNome(nomeProf, matricula)) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Já existe um professor cadastrado com esses dados.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		} else {
			Professor p = new Professor();
			p.setMatricula(matricula);
			p.setNome(nomeProf);
			List<Disciplina> disc = new ArrayList<>();
			for (String n : preferencias) {
				disc.add(procuraDisc(n));
			}
			p.setDisciplinasDePreferencia(disc);
			professores.add(p);
			nomeProf = matricula = "";
			preferencias = new String[0];
		}

	}

	public void removerProfessor(Professor p) {
		professores.remove(p);
	}
	public boolean podeCalcular() {
		return professores.size() > 4;
	}
	
	public Grade montarGrade() {
		Grade grade = new Grade();
		grade.setProfessores(professores);
		if(!turmaNormal.isEmpty())
			grade.setTurmas(turmaNormal);
		if(!turmaFixa.isEmpty())
			grade.setTurmasDefinidas(turmaFixa);
		grade.setTurno(turno);
		return grade;
	}
	private void visuP3() {
		limparPaginas();
		renderedP3 = true;
	}
	public void  obterResp() {
		visuP3();
		Alocador a = new Alocador(montarGrade());
		resposta = a.alocar();
		System.out.println(resposta);

	}
	public List<String> listaArquivos(){
		return serializador.getArquivosNome();
	}
	public void carregarDisciplinas(String nome) {
		turmas = serializador.carregarLista(nome);
		turno = serializador.carregarTurno(nome);
		System.out.println(turmas);
		System.out.println(turno);
		limparPaginas();
		renderedPF = true;
	}
	public void visuCarregar() {
		limparPaginas();
		renderedCD = true;
	}
	public void salvarDisciplinas() {
		serializador.salvarTurma(turmas,turno);	
		System.out.println(" I GO T TE DAAAAAAAAAAAAAAA");
	}
	public void salvarProfs() {
		serializador.salvarProfessor(professores);
		System.out.println("salvo");
	}
	
	public List<Professor> carregarProfs(){
		professoresAUX = serializador.carregarProfessores();
		return professoresAUX;
	}
	public void inserirPref(Professor p) {
		if(preferencias.length > 2 || preferencias.length <6) {
		System.out.println(p.getDisciplinasDePreferencia());
		List<Disciplina> temp = new ArrayList<>();
		for(String s:preferencias)
			temp.add(procuraDisc(s));
		p.setDisciplinasDePreferencia(temp);
		professores.add(p);
		professoresAUX.remove(p);
		System.out.println(p.getDisciplinasDePreferencia());
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("O professor deve ter de 3 a 5 preferências.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
			
		}
	}

}
