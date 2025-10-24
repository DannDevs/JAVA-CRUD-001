package libs;

import java.sql.*;

public class Conexao{

    String url =  "jdbc:postgresql://192.168.200.171/datemp";
    String user = "admin";
    String senha = "miRRor10";

    public Connection conectar() {

        try{
            return DriverManager.getConnection(url,user,senha);
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao conectar" + ex.getMessage());
        }
    }
}
