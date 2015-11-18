package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ThohaGames
 * Tho - Thomas / Ha - Hallef
 */
public class Banco {
    public static Connection con;
    
    public static void conecte(){
        String Driver = "org.postgresql.Driver";
        String user = "postgres";
        String senha = "admin";
        String url = "jdbc:postgresql://localhost:5432/rank";


        try {
            Class.forName(Driver);

            con = (Connection) DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void cadastrar(String nome, int pontos) {
        conecte();
        String INSERT = "insert into RANK (nome, pontos)"
                + "values (?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(INSERT);
            stmt.setString(1, nome);
            stmt.setInt(2, pontos);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void execute(String sql) {
        try {
            PreparedStatement stmt = con.prepareCall(sql);
            Statement st = con.createStatement();
            st.execute(sql);
            st.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
        
    public static ResultSet executeQuery(String sql) {
        try {
            Statement st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
}
