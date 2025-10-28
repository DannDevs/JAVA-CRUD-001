package DAO;

import Model.Curso;
import libs.Conexao;
import java.util.ArrayList;
import java.util.List;
import Model.Disciplina;
import java.sql.*;

public class DisciplinaDAO {

CursoDisciplinaDAO cursoDisciplinaDAO = new CursoDisciplinaDAO();

    public void salvar(Disciplina disciplina){

        String sql = "INSERT INTO disciplina (coddisciplina, nome, cargahoraria) VALUES (?, ?, ?) ";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, disciplina.getcoddisciplina());
            stmt.setString(2, disciplina.getnome());
            stmt.setInt(3,disciplina.getcargahorario());
            stmt.executeUpdate();

            System.out.println("Disciplina Salva com sucesso!");
        }
        catch (SQLException ex){
            System.out.println("Erro ao salvar Disciplina: " + ex.getMessage());
        }
    }
    public void atualizar(Disciplina disciplina){
        String sql = "UPDATE disciplina SET nome = ?, cargahoraria = ? WHERE coddisciplina = ? ";

        try(Connection conn = new Conexao().conectar()) {
            assert conn != null;
            try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
                stmt.setString(1, disciplina.getnome());
                stmt.setInt(2,disciplina.getcargahorario());
                stmt.setInt(3,disciplina.getcoddisciplina());
                stmt.executeUpdate();
                System.out.println("Disciplina Atualizada com sucesso");
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Erro ao salvar Atualizar: " + e.getMessage());
        }

    }

    public List<Disciplina> listarDisciplinasCurso(Curso curso){

        String sql =  "SELECT d.coddisciplina,d.nome FROM disciplina d" +
                "join cursodisciplina a on" +
                "d.coddisciplina = a.coddisciplina" +
                "where codcurso = ?";
        List<Disciplina> disciplinasC = null;

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
                ){
            stmt.setInt(1, curso.getCodcurso());
            while(rs.next()) {
                rs.getInt("coddisciplina");
                rs.getString("nome");
            }

        } catch(SQLException ex){
            throw new RuntimeException("Erro ao listar " + ex.getMessage());
        }

        return disciplinasC;

    }


    public Disciplina consultarDisciplina(int codDisciplina) {
        String sql = "SELECT * FROM disciplina WHERE coddisciplina = ?";
        Disciplina disciplina = null;

        try (Connection connection = new Conexao().conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, codDisciplina);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                disciplina = new Disciplina(
                        rs.getInt("coddisciplina"),
                        rs.getString("nome"),
                        rs.getInt("cargahoraria")
                );
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar Disciplina: " + ex.getMessage());
        }

        return disciplina;
    }


    public List<Disciplina> consultar() {
        String sql = "SELECT * FROM disciplina";
        List<Disciplina> disciplinas = new ArrayList<>();

          try(Connection conn = new Conexao().conectar();
              PreparedStatement stmt = conn.prepareStatement(sql);
              ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Disciplina d = new Disciplina(
                            rs.getInt("coddisciplina"),
                            rs.getString("nome"),
                            rs.getInt("cargahoraria")
                    );
                    disciplinas.add(d);

                }
          } catch (SQLException e){
            throw new RuntimeException("Erro ao Consultar" + e.getMessage());
        }
        return disciplinas;
    }

    public void excluir(int codDisciplina) {
        String sql = "DELETE FROM disciplina WHERE coddisciplina = ?";

        try (Connection conn = new Conexao().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);) {

            cursoDisciplinaDAO.removerDisciplinaEs(codDisciplina);

            stmt.setInt(1, codDisciplina);
            stmt.execute();

            System.out.println("Disciplina excluida com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao Excluir " + e.getMessage());
        }
    }
}
