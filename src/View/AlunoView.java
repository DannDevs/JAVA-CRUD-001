package View;

import Controller.AlunoController;

import java.util.Scanner;

public class AlunoView {

   public void menuAluno(){

       AlunoController alunoController = new AlunoController();
       Scanner input = new Scanner(System.in);
       int opcao;

       do{
           System.out.println("=============");
           System.out.println("1 Cadastrar Aluno");
           System.out.println("2 Consultar Aluno ");
           System.out.println("3 Remover Aluno");
           System.out.println("4 Atualizar Aluno ");
           System.out.println("5 Voltar ao Menu Inicial");
           System.out.println("=============");
           System.out.print("Opcao: ");
           opcao = input.nextInt();

           switch(opcao){
               case 1 ->{

                   System.out.println("Digite a Matricula: ");
                   int matricula = input.nextInt();
                   input.nextLine();

                   System.out.println("Digite o nome do Aluno: ");
                   String nomeAluno = input.nextLine();

                   System.out.println("Digite a idade do Aluno: ");
                   int idade  = input.nextInt();

                   System.out.println("Digite o Codigo do Curso do Aluno");
                   int codcurso = input.nextInt();

                   alunoController.cadastrarAluno(matricula,nomeAluno,idade,codcurso);
               }
               case 2 -> alunoController.consultarAluno();
               case 3 ->{
                   System.out.println("Digite a Matricula do Aluno a remover");
                   int matricula =  input.nextInt();

                   alunoController.deletarAluno(matricula);
               }
               case 4 ->{
                   System.out.println("Digite a Matricula do Aluno a Atualizar");
                   int matricula =  input.nextInt();
                   input.nextLine();

                   if(AlunoController.verificamatricula(matricula)){
                       System.out.println("Digite o nome do Aluno a Atualizar: ");
                       String nome = input.nextLine();
                       System.out.println("Digite a idade do Aluno a Atualizar: ");
                       int idade = input.nextInt();
                       input.nextLine();
                       System.out.println("Deseja alterar o Curso ? Digite S/N");
                       String escolha = input.nextLine();
                       if ( escolha.equals("S")){

                           System.out.println("Digite o Codigo do Curso: ");
                           int codcurso = input.nextInt();
                           alunoController.atualizarAlunoc(matricula,nome,idade,codcurso);
                           return;
                       }
                       else {
                           System.out.println("Escolha invalida ou Nao desejou atualizar!");
                       }
                       alunoController.atualizarAluno(matricula,nome,idade);

                   }

                   else {
                       System.out.println("Essa matricula nao está no sistema!");
                   }
               }
               case 5 -> MenuView.menuExibir();

               default ->  System.out.println("Opcao invalida");
           }

       }while(opcao != 0 );
   }
}
