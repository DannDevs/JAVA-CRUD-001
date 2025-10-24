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
        System.out.println("Deletado com sucesso!");
    }
   public void atualizar(Aluno aluno) { System.out.println("Atualizado com sucesso!"); }

}
