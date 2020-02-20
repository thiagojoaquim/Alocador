package br.ufs.dcomp.alocador.AlocadorGUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.ufs.dcomp.alocador.modelo.Disciplina;
import br.ufs.dcomp.alocador.modelo.Professor;
import br.ufs.dcomp.alocador.modelo.Turma;
import br.ufs.dcomp.alocador.modelo.Turno;
import sun.misc.Queue;

public class Serializador {
	private static final Serializador serializador = new Serializador();
	
	
	
	
	public static Serializador getSerializador() {
		return serializador;
	}
	
	public void salvarTurma(List<Turma> turma,Turno t) {
		try {
			int cont = contarSalvos();
			File arq = new File("Disciplinas" + cont + ".txt");
			File arq2 = new File("TurnoDisciplinas"+cont+".txt");
			FileOutputStream out = new FileOutputStream(arq);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(turma);
			oos.flush();
			out = new FileOutputStream(arq2);
			oos = new ObjectOutputStream(out);
			oos.writeObject(t);
			oos.flush();
			oos.close();
			out.close();
		} catch (Exception e) {
			System.out.println("Problem serializing: " + e);
		}
	}
	public void salvarProfessor(List<Professor> professor) {
		try {
			List<List<Disciplina>> preferencias = new ArrayList<List<Disciplina>>();
			for(Professor p: professor) {
				preferencias.add(p.getDisciplinasDePreferencia());
				p.setDisciplinasDePreferencia(new ArrayList<>());
			}
			File arq = new File("Professores.txt");
			FileOutputStream out = new FileOutputStream(arq);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(professor);
			oos.flush();
			oos.close();
			out.close();
			for(Professor p: professor) {
				p.setDisciplinasDePreferencia(preferencias.get(0));
				preferencias.remove(0);
			}
		} catch (Exception e) {
			System.out.println("Problem serializing: " + e);
		}
	}
	

	private int contarSalvos() {
		int a =  0;
		for ( File f : new File(".").listFiles()) {
			if(f.getName().startsWith("Disciplina") && f.getName().endsWith(".txt")) 
				a++;
		}
		return a+1;
	}
	public List<String> getArquivosNome(){
		List<String> aux = new ArrayList<>();
		for ( File f : new File(".").listFiles())
			if(f.getName().startsWith("Disciplina") && f.getName().endsWith(".txt")) 
				aux.add(f.getName());
		return aux;
	}

	@SuppressWarnings("unchecked")
	public List<Turma> carregarLista(String nome) {
		try {
			List<Turma> lista;
			FileInputStream in = new FileInputStream(nome);
			ObjectInputStream ois = new ObjectInputStream(in);
			lista = (List<Turma>) (ois.readObject());
			ois.close();
			in.close();
			return lista;
		} catch (Exception e) {
			System.out.println("Problem serializing: " + e);
		}
		return null;
	}
	public Turno carregarTurno(String nome) {
		try {
			FileInputStream in = new FileInputStream("Turno"+nome);
			ObjectInputStream ois = new ObjectInputStream(in);
			Turno t = (Turno) (ois.readObject());
			ois.close();
			in.close();
			return t;
		} catch (Exception e) {
			System.out.println("Problem serializing: " + e);
		}
		return null;
	}
	public List<Professor> carregarProfessores() {
		try {
			List<Professor> lista;
			FileInputStream in = new FileInputStream("Professores.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			lista = (List<Professor>) (ois.readObject());
			ois.close();
			in.close();
			return lista;
		} catch (Exception e) {
			System.out.println("Problem serializing: " + e);
		}
		return null;
	}
	

}
