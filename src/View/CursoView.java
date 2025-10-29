package View;

import Controller.CursoController;
import Controller.DisciplinaController;
import Model.Curso;
import Model.Disciplina;

import java.util.ArrayList;
import java.util.List;
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
                    System.out.print("-> ");
                    int idcurso = input.nextInt();
                    input.nextLine();

                    System.out.println("Digite o nome do curso");
                    System.out.print("-> ");
                    String nome = input.nextLine();

                    System.out.println("Digite o Turno");
                    System.out.print("-> ");
                    String turno = input.nextLine();

                    List<Disciplina> disciplinas = new ArrayList<>();
                    int coddisciplina;

                    do{
                        System.out.println("Digite o codigo das Disciplina, caso nao tenha mais digite 0 ");
                        coddisciplina = input.nextInt();

                        if (!disciplinaController.codExiste(coddisciplina)){
                            if(coddisciplina == 0){
                                System.out.println(" ");
                            }
                            else {
                                System.out.println("Codigo: " + coddisciplina +" não existe no sistema");
                            }
                        }
                        if (coddisciplina != 0 ) {
                            Disciplina d = new Disciplina(coddisciplina,null,0);
                            disciplinas.add(d);
                        }
                    } while (coddisciplina != 0);

                    cursoController.cadastrarCurso(idcurso,nome,turno,disciplinas);
                }
                case 2 -> cursoController.consultarCursos();
                case 3 ->{
                    System.out.println("Digite o Cod Curso a Remover: ");
                    System.out.print("-> ");
                    int codremover = input.nextInt();
                    if (!cursoController.validaCod(codremover)){
                        System.out.println("Curso nao está no sistema");
                        break;
                    }
                    cursoController.removerCurso(codremover);
                }
                case 4 -> {
                    System.out.println("Digite o CodCurso a Atualizar: ");
                    System.out.print("-> ");
                    int codcurso = input.nextInt();
                    input.nextLine();

                    if(!cursoController.validaCod(codcurso)){
                        System.out.println("O Codigo curso nao está no sistema!");
                        break;
                    }

                    System.out.println("Deseja Atualizar os Dados do Curso ou da Disciplina?: C/D");
                    System.out.print("-> ");
                    String escolhaAT = input.nextLine();

                        if(escolhaAT.equals("D") || escolhaAT.equals("d")){

                            Curso cursoinsert = new Curso(codcurso,null,null);
                            System.out.print("Disciplinas do Curso: ");
                            cursoController.consultarDisciplinasCurso(cursoinsert);
                            System.out.println(" ");

                            System.out.println("Deseja Remover ou Adicionar uma disciplina? A/R");
                            System.out.print("-> ");
                            String escolhaAD = input.nextLine();

                            if(escolhaAD.equals("A") || escolhaAD.equals("a")){
                                System.out.println("Digite o codigo da disciplina a adicionar: ");
                                System.out.print("-> ");
                                int coddisciplina = input.nextInt();
                                input.nextLine();

                                if(!disciplinaController.codExiste(coddisciplina)){
                                    System.out.println("Cod da Disciplina nao existe no sistema");
                                    break;
                                }
                                cursoController.atualizarDisciplina(codcurso,coddisciplina);
                            }
                            else if(escolhaAD.equals("r") || escolhaAD.equals("R")){
                                System.out.println("Digite o codigo da disciplina a remover: ");
                                System.out.print("-> ");
                                int coddisciplina = input.nextInt();

                                if(!disciplinaController.codExiste(coddisciplina)){
                                    System.out.println("Cod da Disciplina nao existe no sistema");
                                    break;
                                }

                                cursoController.removerDisciplina(codcurso,coddisciplina);
                            }
                        }
                        else if (escolhaAT.equals("C") || escolhaAT.equals("c")) {
                            System.out.println("Digite o novo nome do curso");
                            System.out.print("-> ");
                            String nome = input.nextLine();
                            System.out.println("Digite o novo turno:");
                            System.out.print("-> ");
                            String turno = input.nextLine();
                            cursoController.atualizarCurso(codcurso,nome,turno);
                        }
                        else{
                            System.out.println("Opcao invalida");
                        }
                }
                case 5 -> MenuView.menuExibir();
                default ->  System.out.println("Opcao invalida");
            }
        } while (opcao != 0);




    }
}
