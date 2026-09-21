import java.sql.Connection;
import java.sql.DriverManager;

public class conectaDAO {
    public Connection connectDB() {
        Connection conn = null;
        try {
            
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/uc11", 
                "root",                            
                "14022006"                                 
            );
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
        return conn;
    }
}
