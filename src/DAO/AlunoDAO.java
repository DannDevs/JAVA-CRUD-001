package DAO;

import Model.Aluno;
import Model.Curso;
import libs.Conexao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno(matricula,nome,idade,fkcurso) VALUES (?,?,?,?)";
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, aluno.getMatricula());
            stmt.setString(2,aluno.getNomeAluno());
            stmt.setInt(3,aluno.getIdade());
            stmt.setInt(4,aluno.getCurso().getCodcurso());

            stmt.executeUpdate();
            System.out.println("Aluno Inserido com sucesso");


        } catch(SQLException ex){
            throw new RuntimeException("Erro ao salvar aluno: " + ex.getMessage() );
        }
    }
    public List<Aluno> listar(){
        String sql = "Select a.idade,a.matricula,a.nome as nome_aluno, c.nome as nome_curso," +
                "c.turno,c.codcurso " +
                "from aluno a " +
                "join curso c on c.codcurso = a.fkcurso";
        List<Aluno> alunos = new ArrayList<>();

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs =  stmt.executeQuery()
            ){
            while(rs.next()){

                Curso curso = new Curso(
                        rs.getInt("codcurso"),
                        rs.getString("nome_curso"),
                        rs.getString("turno")
                );
                Aluno aluno = new Aluno(
                        rs.getInt("matricula"),
                        rs.getString("nome_aluno"),
                        rs.getInt("idade"),
                        curso

                );
                alunos.add(aluno);
            }
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao listar alunos: " + ex.getMessage() );
        }
        return alunos;
    }
    public void deletar(Aluno aluno) {

        String sql = "DELETE FROM aluno where matricula = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, aluno.getMatricula());
            stmt.executeUpdate();
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao deletar aluno" + ex.getMessage() );
        }
        System.out.println("Deletado com sucesso!");
    }
    public void atualizarCursoAluno(Aluno aluno) {

        String sql = "UPDATE aluno " +
                "SET fkcurso = ? "+
                "WHERE matricula = ? " ;

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, aluno.getCurso().getCodcurso());
            stmt.setInt(2, aluno.getMatricula());

            stmt.executeUpdate();
            System.out.println("Curso do Aluno Atualizado com sucesso!");

        } catch (SQLException ex){
            throw new RuntimeException("Erro ao atualizar aluno: " + ex.getMessage() );
        }
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno " +
                "SET nome = ? , idade = ? WHERE matricula = ? ";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, aluno.getNomeAluno());
            stmt.setInt(2, aluno.getIdade());
            stmt.setInt(3, aluno.getMatricula());

            stmt.executeUpdate();
            System.out.println("Aluno Atualizado com sucesso!");

        }catch (SQLException ex){
            throw new RuntimeException("Erro ao atualizar aluno: " + ex.getMessage() );
        }
    }





}
