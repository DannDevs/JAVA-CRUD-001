package Model;

public class Aluno {

    private int matricula;
    private String nomeAluno;
    private int idade;
    private Curso curso;

    public Aluno(int matricula, String nomeAluno, int idade,Curso curso) {
        this.matricula = matricula;
        this.nomeAluno = nomeAluno;
        this.idade = idade;
        this.curso = curso;

    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Curso getCurso() { return curso; }
    public void setCurso (Curso curso) { this.curso = curso; }


    @Override
    public String toString() {
        return nomeAluno + " " + matricula ;
    }

}
