
package accesodatos;

import conexion.Conexion;
import domain.SalesOrderHeader;
import java.sql.*;
import java.util.*;


public class SalesOrderHeaderImp implements IAccesoDatosOrderHeader {

    PreparedStatement ps = null;
    ResultSet rs = null;
    ResultSetMetaData md = null;
    
    
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

    @Override
    public void actualizar(SalesOrderHeader objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertar(SalesOrderHeader objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
