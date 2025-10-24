package main.java.Controller;

import main.java.DAO.AlunoDAO;
import main.java.Model.Aluno;
import main.java.Model.Curso;

import java.util.ArrayList;
import java.util.List;

public class AlunoController {

    private static final List<Aluno> alunos = new ArrayList<>();
    CursoController cursoController = new CursoController();
    AlunoDAO  alunoDAO = new AlunoDAO();


    public  void cadastrarAluno(int matricula,String nome, int idade,int codcurso) {

        if (!validaMatricula(matricula))  {
            System.out.println("Matricula Invalida");
            return;
        }
        if (validaNome(nome))  {
            System.out.println("Nome Invalido");
            return;
        }
        if (idadeValida(idade)) {
            System.out.println("Idade Invalida");
            return;
        }
        Curso curso = cursoController.consultarcodigocurso(codcurso);
        if (curso == null) {
            System.out.println("Curso nao foi encontrado");
            System.out.print("Disponiveis: ");
            cursoController.exibecoddisponiveis();
            return;
        }
            Aluno novoaluno = new Aluno(matricula,nome,idade,curso);
            alunos.add(novoaluno);
            alunoDAO.salvar(novoaluno);

    }

    public void consultarAluno(){
     if(!alunos.isEmpty()){
         for (Aluno aluno : alunos) {
             System.out.println("==============");
             System.out.println("Matricula: " + aluno.getMatricula());
             System.out.println("Nome: " + aluno.getNomeAluno());
             System.out.println("Idade: " + aluno.getIdade());
             System.out.println("Curso: " + aluno.getCurso());
             System.out.println("Disciplinas Matriculada: " + " ");
             System.out.println("==============");
         }
     }
     else {
         System.out.println("Nao ha Alunos Cadastrados ");
     }
    }


    public void deletarAluno(int matricula){
        Aluno alunoremover = null;

        for (Aluno a : alunos) {
            if (a.getMatricula() == matricula) {
                alunoremover = a;
                break;
            }
        }

        if(alunoremover != null){
            alunos.remove(alunoremover);
            alunoDAO.deletar(alunoremover);
        }
        else{
            System.out.println("Matricula nao está no sistema");
        }
    }

    public void atualizarAlunoc(int matricula,int codcurso){
        Aluno alunoatualizar =  null;
        Curso cursoexistente = cursoController.consultarcodigocurso(codcurso);

        for(Aluno a : alunos){
            if(a.getMatricula() == matricula){
                alunoatualizar = a;
                break;
            }
        }

        if (alunoatualizar == null || alunoatualizar.getCurso() ==  null || cursoexistente == null ){
            System.out.println("Curso ou aluno inexistente");
            return;
        }

        if(alunoatualizar.getCurso().getCodcurso() == cursoexistente.getCodcurso()){
            System.out.println("Não e possivel atualizar o curso para seu proprio curso");
            return;
        }
            alunoatualizar.setCurso(cursoexistente);
            alunoDAO.atualizar(alunoatualizar);

    }


    public void atualizarAluno(int matricula,String nome, int idade){
        Aluno alunoatualizar =  null;

        for(Aluno a : alunos){
            if(a.getMatricula() == matricula){
                alunoatualizar = a;
                break;
            }
        }

        if(idadeValida(idade)){
            System.out.println("Idade Invalida");
            return;
        }
        if (validaNome(nome)) {
            System.out.println("Nome Invalido");
            return;
        }

        if(alunoatualizar != null){
            alunoatualizar.setIdade(idade);
            alunoatualizar.setNomeAluno(nome);
            alunoDAO.atualizar(alunoatualizar);
        }
        else {
            System.out.println("Nao ha Alunos Cadastrados ");
        }
    }

    //VALIDAÇOES

    public static boolean verificamatricula(int matricula){
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

    public boolean validaMatricula(int matricula){ return matricula > 0; }

    public boolean idadeValida(int idade) { return idade <= 8 || idade >= 100; }

    public boolean validaNome(String nome){ return nome == null || nome.isEmpty(); }
}
