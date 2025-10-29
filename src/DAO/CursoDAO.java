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

    CursoDisciplinaDAO cursoDisciplina = new CursoDisciplinaDAO();

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
                cursoDisciplinaDAO.adicionarCursoDisciplina(d.getcoddisciplina(),curso.getCodcurso());
            }
            System.out.println("Curso cadastrado com sucesso");

        } catch(SQLException ex){
            throw new RuntimeException("Erro ao inserir: " + ex.getMessage());
        }
    }



    public List<Curso> consultar(){

        String sql =  "SELECT " +
                "c.codcurso, " +
                "c.nome AS nomecurso, " +
                "c.turno, " +
                "d.coddisciplina, " +
                "d.nome AS nomedisciplina, " +
                "d.cargahoraria " +
                "FROM curso c " +
                "LEFT JOIN cursodisciplina e ON c.codcurso = e.codcurso " +
                "LEFT JOIN disciplina d ON d.coddisciplina = e.coddisciplina " +
                "ORDER BY c.codcurso";


        List<Curso> cursos = new ArrayList<>();

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
        {
            Curso cursoAtual = null;
            int ultimoCodCurso = -1;

            while (rs.next()) {
                int codCurso = rs.getInt("codcurso");

                // Se mudou de curso, cria um novo objeto Curso
                if (codCurso != ultimoCodCurso) {
                    cursoAtual = new Curso(
                            codCurso,
                            rs.getString("nomecurso"),
                            rs.getString("turno")
                    );
                    cursos.add(cursoAtual);
                    ultimoCodCurso = codCurso;
                }
                Disciplina disciplina = new Disciplina(
                        rs.getInt("coddisciplina"),
                        rs.getString("nomedisciplina"),
                        rs.getInt("cargahoraria")
                );

                if (cursoAtual == null) {
                    System.out.println("ERR");
                    break;
                }

                cursoAtual.adicionardisciplina(disciplina);
                cursos.add(cursoAtual);
            }

        }
        catch (Exception ex){
            throw new RuntimeException("Erro ao consultar: " + ex.getMessage());
        }
        return cursos;
    }
    public void atualizar(Curso curso) {
        String sql = "UPDATE curso SET nome = ?, turno = ? WHERE codcurso = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, curso.getNomecurso());
            stmt.setString(2, curso.getTurno());
            stmt.setInt(3, curso.getCodcurso());
            stmt.executeUpdate();
            System.out.println("Curso atualizado com sucesso");

        } catch (SQLException ex){
            throw new RuntimeException("Erro ao atualizar: " + ex.getMessage());
        }
    }

    public void deletar(Curso curso) {

        String sql = "DELETE FROM curso WHERE codcurso = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
                ){

            cursoDisciplina.removerCursoDisciplina(curso.getCodcurso());

            stmt.setInt(1, curso.getCodcurso());
            stmt.executeUpdate();
            System.out.println("Curso atualizado com sucesso");

        } catch(SQLException ex){
            throw new RuntimeException("Erro ao deletar: " + ex.getMessage());
        }




    };
}

