//En esta clase se definen los metodos de su respectiva interface. 

package accesodatos;

import conexion.Conexion;
import domain.SalesOrderHeader;
import java.sql.*;
import java.util.*;


public class SalesOrderHeaderImp implements IAccesoDatosOrderHeader {

    PreparedStatement ps = null;
    ResultSet rs = null;
    ResultSetMetaData md = null;
    
    //Metodo que recupera todos los registros de su respectiva tabla.
    @Override
    public List<SalesOrderHeader> listar() {
        
        System.out.println("Listar desde SqlServer");
        List<SalesOrderHeader> lista = null;
        SalesOrderHeader salOrderHea = null;

        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_RecuperarSalesOrderHeader}");

            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                salOrderHea = new SalesOrderHeader();
                salOrderHea.setSalesOrderId(rs.getInt(1));
                salOrderHea.setRevisionNumber(rs.getInt(2));
                salOrderHea.setOrderDate(rs.getString(3));
                salOrderHea.setDuedate(rs.getString(4));
                salOrderHea.setShipDate(rs.getString(5));
                salOrderHea.setStatus(rs.getInt(6));
                salOrderHea.setOnlineOrderFlag(rs.getInt(7));
                salOrderHea.setSalesOrderNumber(rs.getString(8));
                salOrderHea.setPurchaseOrderNumber(rs.getString(9));
                salOrderHea.setAccountNumber(rs.getString(10));
                salOrderHea.setCustomerId(rs.getInt(11));
                salOrderHea.setSalesPersionId(rs.getInt(12));
                salOrderHea.setTerritoryId(rs.getInt(13));
                salOrderHea.setBillToAdressId(rs.getInt(14));
                salOrderHea.setShipToAdressId(rs.getInt(15));
                salOrderHea.setShipMethodId(rs.getInt(16));
                salOrderHea.setCreditCardId(rs.getInt(17));
                salOrderHea.setCreditCardAppovalCode(rs.getString(18));
                salOrderHea.setCurrencyRateId(rs.getInt(19));
                salOrderHea.setSubTotal(rs.getDouble(20));
                salOrderHea.setTaxAmt(rs.getDouble(21));
                salOrderHea.setFreight(rs.getDouble(22));
                salOrderHea.setTotalDue(rs.getDouble(23));
                salOrderHea.setComment(rs.getString(24));
                salOrderHea.setRowguid(rs.getString(25));
                salOrderHea.setModifiedDate(rs.getString(26));
                
                lista.add(salOrderHea);
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return lista;
    }

    //Metodo que actualiza un registro de su respectiva tabla.
    @Override
    public void actualizar(SalesOrderHeader objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Metodo que inserta un registro en su respectiva tabla.
    @Override
    public int insertar(SalesOrderHeader objeto) {
        
        int rows = 0;
        SalesOrderHeader order = (SalesOrderHeader)objeto;
        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("INSERT INTO SERVIDOR2.SALES.sales.salesOrderHeader\n" +
                                                    " (RevisionNumber, OrderDate, DueDate, ShipDate, [Status],\n" +
                                                    "  OnlineOrderFlag, SalesOrderNumber, CustomerID, TerritoryID, \n" +
                                                    "  BillToAddressID, ShipToAddressID, ShipMethodID, CreditCardID, \n" +
                                                    "  SubTotal, TaxAmt, Freight, TotalDue, rowguid, ModifiedDate) \n" +
                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
                
            ps.setInt(1, order.getRevisionNumber());
            ps.setString(2, order.getOderDate());
            ps.setString(3, order.getDuedate());
            ps.setString(4, order.getShipDate());
            ps.setDouble(5, order.getStatus());
            ps.setDouble(6, order.getOnlineOrderFlag());
            ps.setString(7, order.getSalesOrderNumber());
            ps.setInt(8, order.getCustomerId());
            ps.setInt(9, order.getTerritoryId());
            ps.setInt(10, order.getBillToAdressId());
            ps.setInt(11, order.getShipToAdressId());
            ps.setInt(12, order.getShipMethodId());
            ps.setInt(13, order.getCreditCardId());
            ps.setDouble(14, order.getSubTotal());
            ps.setDouble(15, order.getTaxAmt());
            ps.setDouble(16, order.getFreight());
            ps.setDouble(17, order.getTotalDue());
            ps.setString(18, order.getRowguid());
            ps.setString(19, order.getModifiedDate());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
            Conexion.close(ps);
        }
        
        return rows;
    }

    //Metodo que elimina un registro en su respectiva tabla.
    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Metodo que actualiza un registro de su respectiva tabla.
    @Override
    public void actualizar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Metodo que busca los datos necesarios para llevar a cabo una insercion en la tabla.
    public SalesOrderHeader buscar(SalesOrderHeader salesOrderH){
        
        SalesOrderHeader salesOrderHnuevo = new SalesOrderHeader();
        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_BuscarSalesOrderHeader(?,?,?,?,?,?)}");
                
            ps.setInt(1, salesOrderH.getSalesOrderId());
            ps.setInt(2, salesOrderH.getOnlineOrderFlag());
            ps.setInt(3, salesOrderH.getCustomerId());
            ps.setInt(4, salesOrderH.getBillToAdressId());
            ps.setDouble(5, salesOrderH.getTaxAmt());
            ps.setDouble(6, salesOrderH.getFreight());
            
            rs = ps.executeQuery();
            
            rs.next();
            salesOrderHnuevo.setRevisionNumber(rs.getInt(1));
            salesOrderHnuevo.setOrderDate(rs.getString(2));
            salesOrderHnuevo.setDuedate(rs.getString(3));
            salesOrderHnuevo.setShipDate(rs.getString(4));
            salesOrderHnuevo.setStatus(rs.getInt(5));
            salesOrderHnuevo.setOnlineOrderFlag(rs.getInt(6));
            salesOrderHnuevo.setSalesOrderNumber(rs.getString(7));
            salesOrderHnuevo.setCustomerId(rs.getInt(8));
            salesOrderHnuevo.setTerritoryId(rs.getInt(9));
            salesOrderHnuevo.setBillToAdressId(rs.getInt(10));
            salesOrderHnuevo.setShipToAdressId(rs.getInt(11));
            salesOrderHnuevo.setShipMethodId(rs.getInt(12));
            salesOrderHnuevo.setCreditCardId(rs.getInt(13));
            salesOrderHnuevo.setSubTotal(rs.getDouble(14));
            salesOrderHnuevo.setTaxAmt(rs.getDouble(15));
            salesOrderHnuevo.setFreight(rs.getDouble(16));
            salesOrderHnuevo.setTotalDue(rs.getDouble(17));
            salesOrderHnuevo.setRowguid(rs.getString(18));
            salesOrderHnuevo.setModifiedDate(rs.getString(19));
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(ps);
        }
        
        return salesOrderHnuevo;
        
    }
    
}
