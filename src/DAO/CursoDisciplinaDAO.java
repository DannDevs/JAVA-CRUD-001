package DAO;


import Model.Curso;
import libs.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CursoDisciplinaDAO {


    public void adicionarCursoDisciplina(int disciplina, int curso){
      String sql = "INSERT INTO cursodisciplina VALUES(?,?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1,disciplina);
            stmt.setInt(2,curso);
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir CursoDisciplina" + ex.getMessage());
        }
    }

    public void atualizarCursoDisciplinas(int codcurso, int coddisciplina){
        String sql = "INSERT INTO cursodisciplina (codcurso,coddisciplina) VALUES(?,?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt =  conn.prepareStatement(sql)){
            stmt.setInt(1,codcurso);
            stmt.setInt(2,coddisciplina);

            stmt.execute();

            System.out.println("Disciplina Inserida com sucesso!");
        } catch(SQLException ex){
            throw new RuntimeException("Erro ao Atualizar a DisciplinaCurso: " + ex.getMessage());
        }



    }

    public void removerDisciplinaDoCurso(int curso,int disciplina){
        String sql = "DELETE from cursodisciplina " +
                "where codcurso = ? " +
                "AND coddisciplina = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1,curso);
            stmt.setInt(2,disciplina);
            stmt.execute();
            System.out.println("Disciplina Foi removida do curso!");

        } catch(SQLException ex){
            throw new RuntimeException("Nao Foi Possivel remover: " + ex.getMessage());
        }

    }

    public void removerCursoDisciplina(int curso){
        String sql = "DELETE FROM cursodisciplina WHERE codcurso = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            stmt.setInt(1,curso);
            stmt.execute();

        } catch (SQLException ex){
            throw new RuntimeException("Erro ao deletar CursoDisciplina" + ex.getMessage());
        }
    }
    public void removerDisciplinaEs(int coddisciplina){
        String sql = "DELETE FROM cursodisciplina where coddisciplina = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            stmt.setInt(1,coddisciplina);
            stmt.execute();

        } catch(SQLException ex){
            throw new RuntimeException("Erro ao deletar CursoDisciplina" + ex.getMessage());
        }



    }




}
