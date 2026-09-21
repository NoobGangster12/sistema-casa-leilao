import java.sql.Connection;
import java.sql.DriverManager;

public class conectaDAO {
    public Connection connectDB() {
        Connection conn = null;
        try {
            // Ajuste a porta se necessário (padrão 3306)
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/uc11", // nome do banco
                "root",                            // usuário do MySQL
                "14022006"                                 // senha do MySQL (se tiver, coloque aqui)
            );
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
        return conn;
    }
}
