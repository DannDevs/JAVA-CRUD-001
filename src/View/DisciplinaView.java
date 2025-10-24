package View;

import Controller.DisciplinaController;
import DAO.DisciplinaDAO;
import Model.Disciplina;

import java.util.Scanner;

public class DisciplinaView {

    public void menuDisciplina(){
        DisciplinaController disciplinaController = new DisciplinaController();
        int opcao;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("1 Cadastrar Disciplina");
            System.out.println("2 Consultar Disciplinas");
            System.out.println("3 Remover Disciplina");
            System.out.println("4 Atualizar Disciplina");
            System.out.println("5 Voltar");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();

            switch(opcao){
                case 1 -> {
                    System.out.println(" ========= Cadastro de Disciplinas: ========= ");
                    System.out.println("Digite o Codigo");
                    int codigo = sc.nextInt();
                    System.out.println("Digite o Nome");
                    String nome = sc.next();
                    System.out.println("Digite a Carga Horaria");
                    int cargaHoraria = sc.nextInt();
                    disciplinaController.cadastrardisciplina(codigo, nome, cargaHoraria);
                }
                case 2 -> disciplinaController.consultarDisciplina();
                case 3 -> {
                    System.out.println(" ========== Remover Disciplina: ========= ");
                    System.out.println("Digite o Codigo da disciplina a ser excluida");
                    System.out.print("Opçao: ");
                    int codigo = sc.nextInt();
                    disciplinaController.deletarDisciplina(codigo);
                }
                case 4 ->{
                    System.out.println("========== Atualizar Disciplina: ========== ");
                    System.out.print("Disponiveis: ");
                    disciplinaController.consultarCodigos();
                    System.out.println();
                    System.out.println("Digite o Codigo da disciplina :");
                    int codigo = sc.nextInt();
                    System.out.println("Digite o Nome a alterar");
                    String nome = sc.next();
                    System.out.println("Digite a Carga Horaria a alterar");
                    int cargaHoraria = sc.nextInt();

                    disciplinaController.atualizarDisciplina(codigo, nome, cargaHoraria);
                }
                case 5 ->  MenuView.menuExibir();
                default -> System.out.println("Opçao invalida");
            }


        } while(opcao != 0);




    }
}
