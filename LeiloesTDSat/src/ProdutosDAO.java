import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio Brito
 */

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    List<ProdutosDTO> listagem = new ArrayList<>();
    
// --------------------------------------------------------------------------------------------------------------
    
    public void cadastrarProduto (ProdutosDTO produto){
         
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (  ?, ?, ? )";           

            try {                  
                    prep = conectaDAO.getConexao().prepareStatement(sql);                 
                    prep.setString(1, produto.getNome() );
                    prep.setInt(2, produto.getValor() );
                    prep.setString(3, produto.getStatus() );
                    prep.execute();
                    prep.close();

            } catch (SQLException e) {           
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto...");
            }                
    }

// --------------------------------------------------------------------------------------------------------------
    
    public List<ProdutosDTO> listarProdutosAVenda(){
        
        String sql = "SELECT * FROM produtos WHERE status = 'A Venda'";

        try {
                prep = conectaDAO.getConexao().prepareStatement(sql);        
                resultset = prep.executeQuery();

                while (resultset.next()) {
                        ProdutosDTO produto = new ProdutosDTO();
                        produto.setId(Integer.parseInt(resultset.getString("id")));
                        produto.setNome(resultset.getString("nome"));
                        produto.setValor(Integer.parseInt(resultset.getString("valor")));
                        produto.setStatus(resultset.getString("status"));
                        listagem.add(produto);                
                }
                return listagem;         

         } catch (SQLException ex) {
                System.out.println("Erro ao conectar: " + ex.getMessage());
                return null;
         }   
    }
    
    // --------------------------------------------------------------------------------------------------------------
    
    public List<ProdutosDTO> listarProdutosVendidos(){
        
        String sql = "SELECT * FROM produtos WHERE produtos.status = 'Vendido'";

        try {
                prep = conectaDAO.getConexao().prepareStatement(sql);        
                resultset = prep.executeQuery();

                while (resultset.next()) {
                        ProdutosDTO produto = new ProdutosDTO();
                        produto.setId(Integer.parseInt(resultset.getString("id")));
                        produto.setNome(resultset.getString("nome"));
                        produto.setValor(Integer.parseInt(resultset.getString("valor")));
                        produto.setStatus(resultset.getString("status"));
                        listagem.add(produto);                
                }
                return listagem;         

         } catch (SQLException ex) {
                System.out.println("Erro ao conectar: " + ex.getMessage());
                return null;
         }   
    }
    
    // --------------------------------------------------------------------------------------------------------------
    
    public void venderProduto (int idDoProduto){    
        
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";  

            try{
                prep = conectaDAO.getConexao().prepareStatement(sql);   
                prep.setString(1, String.valueOf(idDoProduto));

                prep.execute();
                prep.close();

                JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso.");

            } catch (SQLException e) {           
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda...");
            }                
     }  
}
