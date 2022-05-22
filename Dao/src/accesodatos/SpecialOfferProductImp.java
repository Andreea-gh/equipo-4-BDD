
package accesodatos;

import conexion.Conexion;
import domain.SalesCustomer;
import domain.SalesSpecialOfferProduct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SpecialOfferProductImp implements IAccesoDatosSpecialOfferProduct {

    PreparedStatement ps = null;
    ResultSet rs = null;
    ResultSetMetaData md = null;

    @Override
    public List<SalesSpecialOfferProduct> listar() {
        System.out.println("Listar desde SqlServer");
        List<SalesSpecialOfferProduct> lista = null;
        SalesSpecialOfferProduct specialOff = null;

        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_RecuperarSpecialOfferProduct}");

            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                specialOff = new SalesSpecialOfferProduct();
                specialOff.setSpecialOfferId(rs.getInt(1));
                specialOff.setProductId(rs.getInt(2));
                specialOff.setRowguid(rs.getString(3));
                specialOff.setModifiedDate(rs.getString(4));

                lista.add(specialOff);
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return lista;
    }

    @Override
    public void actualizar(SalesSpecialOfferProduct objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
