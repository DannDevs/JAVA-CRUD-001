package View;

import java.util.Scanner;

public class DisciplinaView {

    public void menuDisciplina(){
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=============");
            System.out.println("1 Cadastrar Disciplina");
            System.out.println("2 Consultar Disciplina ");
            System.out.println("3 Remover Diciplina");
            System.out.println("4 Atualizar Disciplina ");
            System.out.println("5 Voltar ao Menu Inicial");
            System.out.println("=============");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();

            switch(opcao){
                case 1 ->{}
                case 2 ->{}
                case 3 ->{}
                case 4 ->{}
                case 5 -> MenuView.menuExibir();
                default ->  System.out.println("Opcao invalida");
            }



        } while (opcao != 0);






    }


}
