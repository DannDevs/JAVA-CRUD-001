package libs;

import java.sql.*;

public class Conexao{

    public static final String url = "jdbc:mysql://localhost:3306/dbcurso";
    public static final String user = "root";
    public static final String password = "";

    public Connection conectar() {

        try{
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao conectar" + ex.getMessage());
        }
    }
}
