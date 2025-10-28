package Controller;

import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import Model.Curso;
import Model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class CursoController {

    private static final List<Curso> cursos = new ArrayList<>();
    CursoDAO  cursoDAO = new CursoDAO();
    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();


    public void cadastrarCurso(int codcurso, String nomecurso, String turno,List<Disciplina> disciplinas) {

        atualizarLista();

        if (validaCod(codcurso)) {
            System.out.println("Curso ja foi cadastrado");
            return;
        }
        if (validaNome(nomecurso)) {
            System.out.println("Nome invalido");
            return;
        }
        for (Disciplina d : disciplinas) {
            if(disciplinaDAO.consultarDisciplina(d.getcoddisciplina()) == null){
                System.out.println("Disciplina ja foi cadastrado");
            }
        }

        Curso novocurso = new Curso(codcurso, nomecurso, turno);
        novocurso.setDisciplinas(disciplinas);
        cursoDAO.salvar(novocurso);
    }

    public void consultarCursos(){

       atualizarLista();

        if(!cursos.isEmpty()){

            int cursoExibido = -1;
            for (Curso c : cursos) {
                if(c.getCodcurso() != cursoExibido){

                    cursoExibido  = c.getCodcurso();

                    System.out.println("===========");
                    System.out.println("Cod Curso: " + c.getCodcurso() );
                    System.out.println("Nome Curso: " + c.getNomecurso() );
                    System.out.println("Turno: " + c.getTurno() );
                    System.out.print("Disciplinas: ");
                    for(Disciplina d : c.getDisciplinas()) {
                        System.out.print("[" + d.getcoddisciplina() + "," + d.getnome() + "]");
                    }
                    System.out.println(" ");
                    System.out.println("===========");
                }
            }
        }
        else {
           System.out.println("Nao ha cursos disponiveis!");
        }
    }

    public void consultarDisciplinasCurso(Curso cursod){
        atualizarLista();

        int cursoExibido = -1;
        for(Curso c : cursos ){
            if(c.getCodcurso() == cursod.getCodcurso()){
                if(c.getCodcurso() != cursoExibido){
                    cursoExibido  = c.getCodcurso();
                    for(Disciplina d : c.getDisciplinas()) {
                        System.out.print("[" + d.getcoddisciplina() + "," + d.getnome() + "] ");
                    }
                }
            }
        }
    }

    public void atualizarCurso(int codcurso,String nome,String turno) {

        atualizarLista();

        if (validaCod(codcurso)) {
            System.out.println("Curso nao existe");
        }
        if (validaNome(nome)) {
            System.out.println("Nome invalido");
        }
        Curso cursoatualizar = null;

        for(Curso c : cursos ){
            if (c.getCodcurso() == codcurso){
                cursoatualizar = c;
            }
        }

        if (cursoatualizar == null) {
            System.out.println("Curso nao existe");
            return;
        }

        cursoatualizar.setNomecurso(nome);
        cursoatualizar.setTurno(turno);
        cursoDAO.atualizar(cursoatualizar);

    }
    public void atualizarDisciplina(int codcurso,int coddisciplina){

    }

    public void removerCurso(int codcurso){

        atualizarLista();

        Curso cursoremover = null;

        for(Curso c : cursos){
            if (c.getCodcurso() == codcurso){
                cursoremover = c;
            }
        }

        if(cursoremover == null){
            System.out.println("Nao existe o Curso com esse Codigo");
            return;
        }

        cursos.remove(cursoremover);
        cursoDAO.deletar(cursoremover);
        System.out.println("Curso removido com sucesso");
    }

    public void exibecoddisponiveis(){
        if(!cursos.isEmpty()){
            for(Curso c : cursos){
                System.out.println(c.getCodcurso());
            }
        }
        else {
            System.out.println(" ");
        }
    }

    public Curso consultarcodigocurso(int codigo){
        atualizarLista();

        for(Curso c : cursos){
            if (c.getCodcurso() == codigo){
                return c;
            }
        }
        return null;
    }

//    public void verDisciplinasPorAluno(int matricula){
//
//        List<Disciplina> listadisciplina = disciplinaDAO.consultar();
//
//        for (Disciplina d : listadisciplina) {
//            if (){
//                System.out.println(d.getnome());
//            }
//        }
//    }


    // ATUALIZA LISTA

    public void atualizarLista(){
        List<Curso> listaCursos = cursoDAO.consultar();
        cursos.clear();
        cursos.addAll(listaCursos);
    }


    // VALIDATES

    public boolean validaCod(int codcurso){
            for(Curso c : cursos){
                if(c.getCodcurso() == codcurso){
                    return true;
                }
            }
        return false;
    }
    public boolean validaNome(String nome){ return nome == null || nome.isEmpty();}

}
