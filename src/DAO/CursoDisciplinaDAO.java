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

    public void atualizarCursoDisciplinas(Curso curso){
        String sql = "";


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
