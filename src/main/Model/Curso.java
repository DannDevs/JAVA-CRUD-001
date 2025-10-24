package main.Model;

import java.util.List;

public class Curso {

    private int codcurso;
    private String nomecurso;
    private String turno;
    private List<Disciplina> disciplinas;

    public Curso(int codcurso,String nomecurso,String turno){
        this.codcurso= codcurso;
        this.nomecurso = nomecurso;
        this.turno = turno;
    }

    public int getCodcurso() {
        return codcurso;
    }

    public void setCodcurso(int codcurso) {
        this.codcurso = codcurso;
    }

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public List<Disciplina> getDisciplinas() { return disciplinas; }

    public void setDisciplinas(List<Disciplina> disciplinas) { this.disciplinas = disciplinas; }

    @Override
    public String toString() {
        return nomecurso;
    }

}
