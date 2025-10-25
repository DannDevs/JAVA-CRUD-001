package Controller;


import DAO.DisciplinaDAO;
import Model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaController {

    private final List<Disciplina> disciplina = new ArrayList<>();
    DisciplinaDAO dao = new DisciplinaDAO();

    public void cadastrardisciplina(int codDisciplina, String nomeDisciplina, int cargahoraria){

        atualizaLista();

        if(validaNome(nomeDisciplina)){
            System.out.println("O nome  e invalido");
            return;
        }
        if(codExiste(codDisciplina)){
            System.out.println("Codigo da disciplina ja foi usado");
            return;
        }
        if(validaCargaHorario(cargahoraria)){
            System.out.println("A carga e invalida");
            return;
        }
        Disciplina novadisciplina = new Disciplina(codDisciplina,nomeDisciplina,cargahoraria);
        dao.salvar(novadisciplina);

    }

    public void consultarDisciplina() {
        List<Disciplina> listaDisciplinas = dao.consultar();

        if (!listaDisciplinas.isEmpty()) {
            try {
                for(Disciplina d : listaDisciplinas){
                    System.out.println("============");
                    System.out.println("Codigo: " + d.getcoddisciplina());
                    System.out.println("Nome: " + d.getnome());
                    System.out.println("Horario: " + d.getcargahorario());
                    System.out.println("============");
                }

                disciplina.clear();
                disciplina.addAll(listaDisciplinas);
            }
            catch (Exception e) {
                System.out.println("Erro ao consultar disciplina");
            }
        }
        else{
            System.out.println("Nenhum disciplina encontrada");
        }
    }


    public void consultarCodigos(){

        List<Disciplina> listaDisciplinas = dao.consultar();

        if (!listaDisciplinas.isEmpty()) {
            try {
                for(Disciplina d : listaDisciplinas ){
                    System.out.print(d.getcoddisciplina() + " ");
                    disciplina.clear();
                    disciplina.addAll(listaDisciplinas);
                }
            }
            catch (Exception e) {
                System.out.println("Erro ao consultar disciplina");
            }
        }
    }


    public void atualizarDisciplina(int codigo,String nomeDisciplina, int cargahoraria) {

        List<Disciplina> listaDisciplinas = dao.consultar();

        if(validaNome(nomeDisciplina)){
            System.out.println("O nome Atualizado e invalido");
            return;
        }
        if(validaCargaHorario(cargahoraria)){
            System.out.println("A carga Atualizada e invalida");
            return;
        }

        Disciplina disciplinaexistente = null;

        for(Disciplina d : listaDisciplinas ){
            if(d.getcoddisciplina() == codigo ){
                disciplinaexistente = d;
                break;
            }
        }
        if(disciplinaexistente == null){
            System.out.println("Nenhum disciplina encontrada");
        }
        else {
            disciplinaexistente.setnome(nomeDisciplina);
            disciplinaexistente.setcargahorario(cargahoraria);
            dao.atualizar(disciplinaexistente);
        }

    }


    public void deletarDisciplina(int codigo){
        atualizaLista();
        List<Disciplina> listaDisciplina = dao.consultar();

        if(!codExiste(codigo)){

            for(Disciplina d : listaDisciplina ){
                if(d.getcoddisciplina() == codigo){
                    dao.excluir(codigo);
                    disciplina.remove(codigo);
                }
            }
        }
        else {
            System.out.println("Nenhum disciplina encontrada");
        }
    }

    public void atualizaLista(){
        List<Disciplina> disciplinas =  dao.consultar();
        disciplina.clear();
        disciplina.addAll(disciplinas);
    }


    public boolean validaNome(String nome) {
        return nome == null || nome.isEmpty();
    }

    public boolean validaCod(int codDisciplina) { return codDisciplina >= 0; }

    public boolean codExiste(int codigodisciplina) {
        for (Disciplina d : disciplina) {
            return d.getcoddisciplina() == codigodisciplina;
        }
        return false;
    }



    public boolean validaCargaHorario(int carga) {return carga <= 0;  }

}
