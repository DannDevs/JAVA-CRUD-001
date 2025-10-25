package Model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

    private int coddisciplina;
    private  String nome;
    private  int cargahoraria;
    private List<Curso> cursos;


    public Disciplina(int coddisciplina, String nome, int cargahoraria) {
        this.coddisciplina = coddisciplina;
        this.nome = nome;
        this.cargahoraria = cargahoraria;
        this.cursos = new ArrayList<>();

    }

    public int getcoddisciplina() { return coddisciplina; }
    public String getnome(){
        return nome;
    }
    public int getcargahorario(){
        return cargahoraria;
    }
    public void setcargahorario(int cargahoraria){
        this.cargahoraria = cargahoraria;
    }
    public void setnome(String nome){
        this.nome = nome;
    }
    public List<Curso> getcursos(){ return cursos; }

    @Override
    public String toString() {
        return nome;
    }
}




