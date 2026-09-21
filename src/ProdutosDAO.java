import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Falha na conexão com o banco!");
            return 0;
        }
        
        int status = 0;
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            status = prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
        }
        return status;
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        return listagem;
    }
}
