
package accesodatos;

import domain.SalesOrderDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;


public class SalesOrderDetailImpl implements IAccesoDatosSalesOrderDetail {

    @Override
    public List<SalesOrderDetail> listar() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        SalesOrderDetail orderD = null;
        List<SalesOrderDetail> lista = null;

        try{
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_productoMasSolicitadoEuropa}");

            rs = ps.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                orderD = new SalesOrderDetail();

                orderD.setProductID(rs.getInt(1));
                orderD.setCantidad_Productos(rs.getInt(2));
                lista.add(orderD);   
            }
            

        }catch(Exception e){
            System.out.println(e);
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
        }

        return lista;
    }
    
}
