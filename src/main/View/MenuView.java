package View;

import java.util.Scanner;

public class MenuView {
    public static void menuExibir() {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("==========");
            System.out.println("1. Gerenciar ALUNOS ");
            System.out.println("2. Gerenciar CURSOS ");
            System.out.println("3. Gerenciar DISCIPLINAS ");
            System.out.println("4. Sair");
            System.out.println("==========");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1 -> new AlunoView().menuAluno();
                case 2 -> new CursoView().menuCurso() ;
                case 3 -> new DisciplinaView().menuDisciplina() ;
                case 4 -> {
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                }

                default -> System.out.println("Opçao invalida");

            }

        } while (opcao != 0);



    }
}
