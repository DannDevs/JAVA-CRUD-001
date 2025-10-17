package View;

import Controller.CursoController;
import Controller.DisciplinaController;
import Model.Disciplina;

import java.util.Scanner;

public class CursoView {

    public void menuCurso() {

        CursoController cursoController = new CursoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        Scanner input = new Scanner(System.in);
        int opcao;

        do{
            System.out.println("=============");
            System.out.println("1 Cadastrar Curso");
            System.out.println("2 Consultar Curso ");
            System.out.println("3 Remover Curso");
            System.out.println("4 Atualizar Curso ");
            System.out.println("5 Voltar");
            System.out.println("=============");
            System.out.print("Opcao : ");
            opcao = input.nextInt();

            switch(opcao){
                case 1 ->{
                    System.out.println("Digite o CodCurso: ");
                    int idcurso = input.nextInt();
                    input.nextLine();
                    System.out.println("Digite o nome do curso");
                    String nome = input.nextLine();
                    System.out.println("Digite o Turno");
                    String turno = input.nextLine();
                    cursoController.cadastrarCurso(idcurso,nome,turno);
                }
                case 2 -> cursoController.consultarCursos();
                case 3 ->{}
                case 4 -> {
                    System.out.println("Digite 1 para Atualizar as disciplinas e 2 para Atualizar os Dados curso");
                    int opcaoa = input.nextInt();
                    switch(opcaoa){

                        case 1 -> {

                            System.out.println("Digite o CodCurso a ser Atualizado:");
                            int codcurso = input.nextInt();
                            System.out.println("Digite o codigo da disciplina a atualizar");
                            int coddisciplina = input.nextInt();
                            System.out.println("Disciplinas disponiveis: ");
                            disciplinaController.exibirDisciplinasDisponiveis();
                            cursoController.atualizarDisciplina(codcurso,coddisciplina);
                        }
                        case 2 -> {

                            System.out.println("Digite o CodCurso a ser Atualizado:");
                            int codcurso = input.nextInt();
                            System.out.println("Digite o novo nome do curso");
                            String nome = input.nextLine();
                            System.out.println("Digite o novo turno:");
                            String turno = input.nextLine();
                            cursoController.atualizarCurso(codcurso,nome,turno);
                        }
                    }
                }
                case 5 -> MenuView.menuExibir();
                default ->  System.out.println("Opcao invalida");
            }
        } while (opcao != 0);




    }
}
