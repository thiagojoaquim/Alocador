package br.ufs.dcomp.alocador.dao;

import br.ufs.dcomp.alocador.modelo.Disciplina;
import br.ufs.dcomp.alocador.modelo.Professor;
import br.ufs.dcomp.alocador.modelo.Turma;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Lucas Tiago
 */
public class SalvadorSolucao {
    
    public static void salvarSolucao(List<Pair<Professor, Turma>> solucao) throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream("solucao.txt");
        for (Pair par : solucao) {
            Professor p = (Professor) par.getKey();
            Turma t = (Turma) par.getValue();
            String nomeProfessor = p.getNome();
            String nomeTurma = t.getDisciplina().getNome();
            System.out.println(
                "Professor " + nomeProfessor +
                " alocado para " + nomeTurma
            );
            String info = nomeProfessor + " -> " + nomeTurma + "\n";
            file.write(info.getBytes());
        }
        file.close();
    }
    
    public static void main(String[] args) throws IOException {
        List<Pair<Professor, Turma>> demo = new ArrayList<>();
        Professor p = new Professor();
        p.setNome("teste");
        Turma t = new Turma();
        t.setDisciplina(new Disciplina());
        t.getDisciplina().setNome("nome teste");
        demo.add(new Pair<>(p, t));
        salvarSolucao(demo);
    }
}
