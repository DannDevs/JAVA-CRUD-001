package main.Model;

public class Disciplina {
    private int coddisciplina;
    private String nomedisciplina;
    private int cargahorario;

    public Disciplina(int coddisciplina, String nomedisciplina, int cargahorario) {
        this.coddisciplina = coddisciplina;
        this.cargahorario = cargahorario;
        this.nomedisciplina = nomedisciplina;
    }

    public int getCoddisciplina() {
        return coddisciplina;
    }
    public void setCoddisciplina(int coddisciplina) {
        this.coddisciplina = coddisciplina;
    }
    public String getNomedisciplina() {
        return nomedisciplina;
    }
    public void setNomedisciplina(String nomedisciplina) {
        this.nomedisciplina = nomedisciplina;
    }
    public int getCargahorario() {
        return cargahorario;
    }
    public void setCargahorario(int cargahorario) {
        this.cargahorario = cargahorario;
    }

    @Override
    public String toString() {
        return  coddisciplina + " " + nomedisciplina;
    }
}
