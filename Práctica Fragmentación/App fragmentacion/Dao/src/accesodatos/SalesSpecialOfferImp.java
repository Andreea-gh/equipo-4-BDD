
package accesodatos;

import domain.SalesSpecialOffer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;

public class SalesSpecialOfferImp implements IAccesoDatosSpecialOffer {

    @Override
    public List<SalesSpecialOffer> listar() {
        List<SalesSpecialOffer> lista = null;
        SalesSpecialOffer specialOffer = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_ofertasProductoBikes}");
            rs = ps.executeQuery();
            lista = new ArrayList();

            while(rs.next()){
                specialOffer = new SalesSpecialOffer();
                specialOffer.setSpecialOfferID(rs.getInt(1));
                specialOffer.setDescription(rs.getString(2));
                specialOffer.setDiscountPct(rs.getFloat(3));
                specialOffer.setType(rs.getString(4));
                specialOffer.setCategory(rs.getString(5));
                specialOffer.setStartDate(rs.getDate(6));
                specialOffer.setEndDate(rs.getDate(7));
                specialOffer.setMinQty(rs.getInt(8));
                specialOffer.setMaxQty(rs.getInt(9));
                specialOffer.setRowguid(rs.getString(10));
                specialOffer.setModifiedDate(rs.getDate(11));
                lista.add(specialOffer);
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
        }
        return lista;
    }
    
}
