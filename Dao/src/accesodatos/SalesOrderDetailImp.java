//En esta clase se definen los metodos de su respectiva interface. 

package accesodatos;

import conexion.Conexion;
import domain.SalesOrderDetail;
import java.sql.*;
import java.util.*;


public class SalesOrderDetailImp implements ICrud4{
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    //Metodo que inserta un registro en su respectiva tabla.
    @Override
    public int insertar(Object objeto) {
        
        System.out.println("Insertar desde SqlServer");
        
        int rows = 0;
        
        SalesOrderDetail order = (SalesOrderDetail)objeto;
        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("INSERT INTO SERVIDOR2.SALES.[Sales].[SalesOrderDetail] (SalesOrderID, [OrderQty], [ProductID],\n" +
                                                    "[SpecialOfferID], [UnitPrice], UnitPriceDiscount, [LineTotal], rowguid, ModifiedDate)\n" +
                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
                
            ps.setInt(1, order.getSalesOrderID());
            ps.setInt(2, order.getOrderQty());
            ps.setInt(3, order.getProductID());
            ps.setInt(4, order.getSpecialOfferID());
            ps.setDouble(5, order.getUnitPrice());
            ps.setDouble(6, order.getUnitPriceDiscount());
            ps.setDouble(7, order.getLineTotal());
            ps.setString(8, order.getRowguid());
            ps.setString(9, order.getModifiedDate());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
//            Conexion.close(rs);
            Conexion.close(ps);
        }
        
        return rows;
    }

    //Metodo que elimina un registro en su respectiva tabla.
    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Metodo que recupera todos los registros de su respectiva tabla.
    @Override
    public List<SalesOrderDetail> listar() {
        
        System.out.println("Listar desde SqlServer");
        List<SalesOrderDetail> lista = null;
        SalesOrderDetail orderDet = null;

        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_RecuperarSalesOrderDetail}");

            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                orderDet = new SalesOrderDetail();
                
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

    //Metodo que actualiza un registro de su respectiva tabla.
    @Override
    public void actualizar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Metodo que valida los datos de un registro antes de que se inserten. pe que el producto
    //exista, que haya en stock.
    public int validar(SalesOrderDetail order){
        
        int resultado = -2;
        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_ValidarInserccionSalesOrderDetail(?,?)}");
                
            ps.setInt(1, order.getProductID());
            ps.setInt(2, order.getOrderQty());
            
            rs = ps.executeQuery();
            
            rs.next();
            resultado = rs.getInt(1);
//            System.out.println("resultado = " + resultado);     
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(ps);
        }
        
        return resultado;
    }
    
    //Metodo que actualiza el stock de un solo producto. 
    public void actualizarProducto(int productId, int nuevoStock) {
        
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
    
    //Metodo que busca los datos necesarios para llevar a cabo una insercion en la tabla.
    public SalesOrderDetail buscar(SalesOrderDetail salesOrderD){
        
        SalesOrderDetail salesOrderDnuevo = new SalesOrderDetail();
        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_BuscaSalesOrderDetail(?,?,?)}");
                
            ps.setInt(1, salesOrderD.getSalesOrderID());
            ps.setInt(2, salesOrderD.getProductID());
            ps.setInt(3, salesOrderD.getOrderQty());
            
            rs = ps.executeQuery();
            
            rs.next();
            salesOrderDnuevo.setSalesOrderID(rs.getInt(1));
            salesOrderDnuevo.setOrderQty(rs.getInt(2));
            salesOrderDnuevo.setProductID(rs.getInt(3));
            salesOrderDnuevo.setSpecialOfferID(rs.getInt(4));
            salesOrderDnuevo.setUnitPrice(rs.getDouble(5));
            salesOrderDnuevo.setUnitPriceDiscount(rs.getDouble(6));
            salesOrderDnuevo.setLineTotal(rs.getDouble(7));
            salesOrderDnuevo.setRowguid(rs.getString(8));
            salesOrderDnuevo.setModifiedDate(rs.getString(9));
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(ps);
        }
        
        return salesOrderDnuevo;
        
    }
}
