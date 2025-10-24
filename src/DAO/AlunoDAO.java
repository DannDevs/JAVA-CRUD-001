package main.java.DAO;

import main.java.Model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private static List<Aluno> alunos =  new ArrayList<>();

    public  void salvar(Aluno aluno) {
        System.out.println("Aluno cadastrado com sucesso!");
    }
    public void listar(){}
    public void deletar(Aluno aluno) {
        System.out.println("Deletado com sucesso!");
    }
    public void atualizar(Aluno aluno) { System.out.println("Atualizado com sucesso!"); }

}
