
/**
 *
 * @author Flavio Brito
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto...");
        }                
    }

// --------------------------------------------------------------------------------------------------------------
    
    public List<ProdutosDTO> listarProdutos(){
        
        String sql = "SELECT * FROM produtos";

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
}
