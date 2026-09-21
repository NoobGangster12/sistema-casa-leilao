
import java.sql.Connection;
import java.sql.DriverManager;

public class conectaDAO {
    public Connection connectDB() {
        try {
            Connection conn = DriverManager.getConnection(
                "uc11",
                "root",                           
                "1402006"                            
            );
            return conn;
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }
}
