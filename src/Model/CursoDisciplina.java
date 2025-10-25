package Model;

public class CursoDisciplina {
    private int codDisciplina;
    private int codcurso;

    public CursoDisciplina(int codDisciplina, int codcurso) {
        this.codDisciplina = codDisciplina;
        this.codcurso = codcurso;
    }
    // GETTERS
    public int getCodDisciplina() { return codDisciplina; }
    public int getCodCurso() { return codcurso; }

    // SETTERS
    public void setCodDisciplina(int codDisciplina) {this.codDisciplina = codDisciplina; }
    public void setcodCurso(int codcurso){ this.codcurso = codcurso; }
}
