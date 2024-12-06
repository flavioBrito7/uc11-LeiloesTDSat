
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

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
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
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
        
}

