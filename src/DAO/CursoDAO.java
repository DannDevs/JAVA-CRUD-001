package DAO;

import Model.Curso;
import Model.CursoDisciplina;
import Model.Disciplina;
import libs.Conexao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {


    public void salvar(Curso curso) {
        String sql = "INSERT INTO curso(codcurso,nome,turno) VALUES (?,?,?)";
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, curso.getCodcurso());
            stmt.setString(2, curso.getNomecurso());
            stmt.setString(3, curso.getTurno());
            stmt.executeUpdate();

            CursoDisciplinaDAO cursoDisciplinaDAO = new CursoDisciplinaDAO();
            for (Disciplina d : curso.getDisciplinas()){
                cursoDisciplinaDAO.adicionarCursoDisciplina(curso.getCodcurso(),d.getcoddisciplina());
            }
            System.out.println("Curso cadastrado com sucesso");

        } catch(SQLException ex){
            throw new RuntimeException("Erro ao inserir: " + ex.getMessage());
        }
    }



    public List<Curso> consultar(){
        String sql =  "select c.codcurso,c.nome as nomecurso," +
                "turno,d.coddisciplina,d.nome as nomedisciplina," +
                "d.cargahoraria from curso c " +
                "join cursodisciplina e on c.codcurso = e.codcurso " +
                "join disciplina d on d.coddisciplina = e.coddisciplina";
        List<Curso> cursos = new ArrayList<>();

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
        {
            while(rs.next()){
                Curso curso = new Curso(
                        rs.getInt("codcurso"),
                        rs.getString("nomecurso"),
                        rs.getString("turno")
                );
                Disciplina disciplina = new Disciplina(
                        rs.getInt("coddisciplina"),
                        rs.getString("nomedisciplina"),
                        rs.getInt("cargahoraria")
                );
                curso.adicionardisciplina(disciplina);
                cursos.add(curso);
            }

        }
        catch (Exception ex){
            throw new RuntimeException("Erro ao consultar: " + ex.getMessage());
        }
        return cursos;
    }
    public void atualizar(Curso curso) {

    }
    public void deletar(Curso curso) {

    };
}

