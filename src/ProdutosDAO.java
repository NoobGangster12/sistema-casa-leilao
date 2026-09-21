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
    ArrayList<ProdutosDTO> listagemVendidos = new ArrayList<>();
    conn = new conectaDAO().connectDB();
    
    try {
        prep = conn.prepareStatement("SELECT * FROM produtos WHERE status = 'Vendido'");
        resultset = prep.executeQuery();
        
 while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            
            listagemVendidos.add(produto);
        }
} catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar vendidos: " + e.getMessage());
    }
    return listagemVendidos;
}
    
    public int venderProduto(int id) {
    conn = new conectaDAO().connectDB();
    if (conn == null) {
        JOptionPane.showMessageDialog(null, "Falha na conexão com o banco!");
        return 0;
    }

    int status = 0;
    try {
        prep = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
        prep.setString(1, "Vendido");
        prep.setInt(2, id);

        status = prep.executeUpdate(); // retorna 1 se atualizou com sucesso
        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    }
    return status;
}

}
