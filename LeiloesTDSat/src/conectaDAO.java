
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Flavio Brito
 */

public class conectaDAO {
    
    private static final String URL = "jdbc:mysql://localhost:3306/uc11?" + "autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    
    private static Connection conn;
    
    public static Connection getConexao(){
       
        try {
                if( conn == null){
                        conn = DriverManager.getConnection(URL, USER, PASSWORD);
                        return conn;
                } else {
                        return conn;
                }
                
        } catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
            }
    }
}

