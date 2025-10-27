package libs;

import java.sql.*;

public class Conexao{

    public static final String url = "jdbc:postgresql://192.168.200.171/datemp";
    public static final String user = "admin";
    public static final String password = "miRRor10";

    public Connection conectar() {

        try{
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao conectar" + ex.getMessage());
        }
    }
}
