package DAO;

import libs.Conexao;
import java.util.ArrayList;
import java.util.List;
import Model.Disciplina;
import java.sql.*;

public class DisciplinaDAO {


    public void salvar(Disciplina disciplina){

        String sql = "INSERT INTO disciplina (coddisciplina, nome, cargahoraria) VALUES (?, ?, ?) ";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, disciplina.getcoddisciplina());
            stmt.setString(2, disciplina.getnome());
            stmt.setInt(3,disciplina.getcargahorario());
            stmt.executeUpdate();
            System.out.println("Disciplina Salvo com sucesso!");
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
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Erro ao salvar Atualizar: " + e.getMessage());
        }

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
            stmt.setInt(1, codDisciplina);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao Excluir " + e.getMessage());
        }
    }
}
