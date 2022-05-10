
package accesodatos;

import conexion.Conexion;
import domain.OrderDetail;
import java.sql.*;
import java.util.*;


public class SalesOrderDetailImp implements ICrud4{
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public int insertar(Object objeto) {
        
        System.out.println("Insertar desde SqlServer");
        
        int rows = 0;
        
        OrderDetail order = (OrderDetail)objeto;
        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_InsertarSalesOrderDetail(?,?,?)}");
                
            ps.setInt(1, order.getSalesOrderID());
            ps.setInt(2, order.getProductID());
            ps.setInt(3, order.getOrderQty());
            
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(ps);
        }
        
        return rows;
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderDetail> listar() {
        
        System.out.println("Listar desde SqlServer");
        List<OrderDetail> lista = null;
        OrderDetail orderDet = null;

        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_RecuperarSalesOrderDetail}");

            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                orderDet = new OrderDetail();
                
                orderDet.setSalesOrderID(rs.getInt(1));
                orderDet.setSalesOrderDetailID(rs.getInt(2));
                orderDet.setCarrierTrackingNumber(rs.getString(3));
                orderDet.setOrderQty(rs.getInt(4));
                orderDet.setProductID(rs.getInt(5));
                orderDet.setSpecialOfferID(rs.getInt(6));
                orderDet.setUnitPrice(rs.getDouble(7));
                orderDet.setUnitPriceDiscount(rs.getDouble(8));
                orderDet.setLineTotal(rs.getDouble(9));
                orderDet.setRowguid(rs.getString(10));
                orderDet.setModifiedDate(rs.getString(11));

                lista.add(orderDet);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(ps);
        }

        return lista; 
    }

    @Override
    public void actualizar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public int validar(OrderDetail order){
        
        int resultado = -2;
        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_ValidarInserccionSalesOrderDetail(?,?)}");
                
            ps.setInt(1, order.getProductID());
            ps.setInt(2, order.getOrderQty());
            
            rs = ps.executeQuery();
            
            rs.next();
            resultado = rs.getInt(1);
            System.out.println("resultado = " + resultado);     
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(ps);
        }
        
        return resultado;
    }
    
    
    public void actualizarProducto(int productId,int nuevoStock) {
        
        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_ActualizarProducto(?,?)}");
                
            ps.setInt(1, productId);
            ps.setInt(2, nuevoStock);
            
            ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(ps);
        }
    }
}
