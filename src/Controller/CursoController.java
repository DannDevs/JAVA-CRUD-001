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
            for (Curso c : cursos) {
                System.out.println("===========");
                System.out.println("Cod Curso: " + c.getCodcurso() );
                System.out.println("Nome Curso: " + c.getNomecurso() );
                System.out.println("Turno:" + c.getTurno() );
                System.out.println("Disciplina: ");

                for(Disciplina d : c.getDisciplinas()) {
                    System.out.print("  -" + d.getcoddisciplina());
                }
                System.out.println("===========");
            }
        }
        else {
           System.out.println("Nao ha cursos disponiveis!");
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
        for(Curso c : cursos){
            if (c.getCodcurso() == codigo){
                return c;
            }
        }
        return null;
    }

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
