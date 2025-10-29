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
           System.out.println("2 Consultar Aluno");
           System.out.println("3 Remover Aluno");
           System.out.println("4 Atualizar Aluno ");
           System.out.println("5 Voltar ao Menu Inicial");
           System.out.println("=============");
           System.out.print("Opcao: ");
           opcao = input.nextInt();

           switch(opcao){
               case 1 ->{

                   System.out.println("Digite a Matricula: ");
                   System.out.print("-> ");
                   int matricula = input.nextInt();

                   if(!alunoController.validaMatricula(matricula)){
                       System.out.println("Matricula invalida");
                       break;
                   }

                   input.nextLine();

                   System.out.println("Digite o nome do Aluno: ");
                   System.out.print("-> ");
                   String nomeAluno = input.nextLine();
                   if(alunoController.validaNome(nomeAluno)){
                       System.out.println("Nome invalido");
                       break;
                   }

                   System.out.println("Digite a idade do Aluno: ");
                   System.out.print("-> ");
                   int idade  = input.nextInt();
                   if(alunoController.idadeValida(idade)){
                       System.out.println("Idade invalida, Deve ser maior que 8");
                       break;
                   }

                   System.out.println("Digite o Codigo do Curso do Aluno");
                   System.out.print("-> ");
                   int codcurso = input.nextInt();

                   alunoController.cadastrarAluno(matricula,nomeAluno,idade,codcurso);
               }
               case 2 -> alunoController.consultarAluno();

               case 3 ->{
                   System.out.println("Digite a Matricula do Aluno a remover");
                   System.out.print("-> ");
                   int matricula =  input.nextInt();
                   if(!alunoController.validaMatricula(matricula)){
                       System.out.println("Matricula invalida");
                       break;
                   }

                   alunoController.deletarAluno(matricula);
               }
               case 4 ->{
                   System.out.println("Digite a Matricula do Aluno a Atualizar");
                   System.out.print("-> ");
                   int matricula =  input.nextInt();
                   input.nextLine();

                   if(alunoController.verificamatricula(matricula)){
                       System.out.println("Deseja alterar o Curso ? Digite S/N");
                       System.out.print("-> ");
                       String escolha = input.nextLine();

                       if ( escolha.equals("S") || escolha.equals("s")){
                           System.out.println("Digite o Codigo do Novo Curso: ");
                           System.out.print("-> ");
                           int codcurso = input.nextInt();
                           alunoController.atualizarAlunoc(matricula,codcurso);
                       }
                       else if (escolha.equals("N") || escolha.equals("n") ){

                           System.out.println("Digite o nome do Aluno a Atualizar: ");
                           System.out.print("-> ");
                           String nome = input.nextLine();
                           if (alunoController.validaNome(nome)){
                               System.out.println("Nome Invalido");
                               break; }

                           System.out.println("Digite a idade do Aluno a Atualizar: ");
                           System.out.print("-> ");
                           int idade = input.nextInt();
                           if (alunoController.idadeValida(idade)){
                               System.out.println("Idade Invalida, Deve ser maior que 8");
                           }

                           alunoController.atualizarAluno(matricula,nome,idade);
                       }
                       else {
                           System.out.println("Escolha invalida ou Nao desejou atualizar!");
                       }
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
