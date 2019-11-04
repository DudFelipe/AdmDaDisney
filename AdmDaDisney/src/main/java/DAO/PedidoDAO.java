/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pedido;
import Utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eduar
 */
public class PedidoDAO {
    
    public static void inserir(Pedido p) throws SQLException, Exception{
        String sql = "INSERT INTO Pedido (Departamento, Descricao, Justificativa, Aprovacao)"
                + " VALUES (?, ?, ?, ?);";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, p.getDepartamento());
            pst.setString(2, p.getDescricao());
            pst.setString(3, p.getJustificativa());
            pst.setInt(4, p.getAprovacao());
            
            pst.execute();
        }
        finally{
            if(pst != null && !pst.isClosed()){
                pst.close();
            }
            
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
            
        }
    }
    
    public static void alterar(Pedido p) throws SQLException, Exception{
        String sql = "UPDATE Pedido SET Departamento = ?, Descricao = ?, Justificativa = ?, Aprovacao = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, p.getDepartamento());
            pst.setString(2, p.getDescricao());
            pst.setString(3, p.getJustificativa());
            pst.setInt(4, p.getAprovacao());
            pst.setInt(5, p.getId());
            
            pst.execute();
        }
        finally{
            if(pst != null && !pst.isClosed()){
                pst.close();
            }
            
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }
    }
    
    public static void excluir(int id) throws SQLException, Exception{
        String sql = "DELETE FROM pedido WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            pst.setInt(0, id);
            
            pst.execute();
        }
        finally{
            if(pst != null && !pst.isClosed()){
                pst.close();
            }
            
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }
    }
    
    public static List<Pedido> listar() throws SQLException, Exception{
        String sql = "SELECT * FROM Pedido";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        List<Pedido> pedidos = new LinkedList<Pedido>();
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Pedido p = new Pedido();
                
                p.setId(rs.getInt("id"));
                p.setDepartamento(rs.getString("Departamento"));
                p.setDescricao(rs.getString("Descricao"));
                p.setJustificativa(rs.getString("Justificativa"));
                p.setAprovacao(rs.getInt("Aprovacao"));
                
                pedidos.add(p);
            }
            
            return pedidos;
        }
        catch(Exception ex){
            return null;
        }
        finally{
            if(pst != null && !pst.isClosed()){
                pst.close();
            }
            
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }
    }
}
