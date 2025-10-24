package DAO;

import Model.Curso;


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
            System.out.println("Curso cadastrado com sucesso");

        } catch(SQLException ex){
            throw new RuntimeException("Erro ao inserir: " + ex.getMessage());
        }
    }
    public List<Curso> consultar(){
        String sql =  "SELECT codcurso,nome,turno FROM curso";
        List<Curso> cursos = new ArrayList<>();

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
        {
            while(rs.next()){
                Curso curso = new Curso(
                        rs.getInt("codcurso"),
                        rs.getString("nome"),
                        rs.getString("turno")
                );
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

