package View;

import Controller.DisciplinaController;

import java.util.Scanner;

public class DisciplinaView {

    public void menuDisciplina(){
        DisciplinaController disciplinaController = new DisciplinaController();
        int opcao;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("==========");
            System.out.println("1 Cadastrar Disciplina");
            System.out.println("2 Consultar Disciplinas");
            System.out.println("3 Remover Disciplina");
            System.out.println("4 Atualizar Disciplina");
            System.out.println("5 Voltar");
            System.out.println("==========");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();

            switch(opcao){
                case 1 -> {
                    System.out.println("Digite o Codigo");
                    System.out.print("-> ");
                    int codigo = sc.nextInt();
                    if (!disciplinaController.validaCod(codigo)){
                        System.out.println("Cod Invalido");
                        break;
                    }
                    sc.nextLine();

                    System.out.println("Digite o Nome");
                    System.out.print("-> ");
                    String nome = sc.nextLine();
                    if(disciplinaController.validaNome(nome)){
                        System.out.println("Nome Invalido");
                        break;
                    }

                    System.out.println("Digite a Carga Horaria");
                    System.out.print("-> ");
                    int cargaHoraria = sc.nextInt();
                    if(disciplinaController.validaCargaHorario(cargaHoraria)){
                        System.out.println("Carga Horaria Invalido");
                        break;
                    }
                    disciplinaController.cadastrardisciplina(codigo, nome, cargaHoraria);
                }
                case 2 -> disciplinaController.consultarDisciplina();
                case 3 -> {
                    System.out.println("Digite o Codigo da disciplina a ser excluida");
                    System.out.print("-> ");
                    int codigo = sc.nextInt();
                    if(!disciplinaController.codExiste(codigo)){
                        System.out.println("Cod da disciplina nao existe");
                        break;
                    }
                    disciplinaController.deletarDisciplina(codigo);
                }
                case 4 ->{
                    System.out.print("Disciplinas Disponiveis: ");
                    disciplinaController.consultarCodigos();
                    System.out.println();

                    System.out.println("Digite o Codigo da disciplina:");
                    System.out.print("-> ");
                    int codigo = sc.nextInt();
                    if(!disciplinaController.codExiste(codigo)){
                        System.out.println("Cod Disciplina nao existe");
                        break;
                    }
                    sc.nextLine();

                    System.out.println("Digite o Nome a alterar");
                    System.out.print("-> ");
                    String nome = sc.nextLine();
                    if(disciplinaController.validaNome(nome)){
                        System.out.println("Nome Invalido");
                        break;
                    }

                    System.out.println("Digite a Carga Horaria a alterar");
                    System.out.print("-> ");
                    int cargaHoraria = sc.nextInt();
                    if (disciplinaController.validaCargaHorario(cargaHoraria)){
                        System.out.println("Carga Horaria Invalida");
                        break;
                    }
                    disciplinaController.atualizarDisciplina(codigo, nome, cargaHoraria);
                }
                case 5 ->  MenuView.menuExibir();
                default -> System.out.println("Opçao invalida");
            }
        } while(opcao != 0);
    }
}
