package accesodatos;

import domain.ProductionProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;

public class ProductionProductImp implements IAccesoDatosProduct {

    @Override
    public List<ProductionProduct> listar() {
        List<ProductionProduct> lista = null;
        ProductionProduct product = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_productosNoDisponibles}");
            rs = ps.executeQuery();
            lista = new ArrayList();

            while (rs.next()) {
                product = new ProductionProduct();
                product.setProductID(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setProductNumber(rs.getString(3));
                product.setMakeFlag(rs.getInt(4));
                product.setFinishedGoodsFlag(rs.getInt(5));
                product.setColor(rs.getString(6));
                product.setSafetyStockLevel(rs.getInt(7));
                product.setReorderPoint(rs.getInt(8));
                lista.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
        }
        return lista;
    }

    @Override
    public void actualizar(ProductionProduct objeto) {
        ProductionProduct product = new ProductionProduct();
        PreparedStatement ps = null;
        try{
            Conexion con = new Conexion();
            ps = con.Conectar().prepareStatement("{call sp_ActualizarSubcategoria(?,?)}");

            ps.setInt(1,objeto.getProductID());
            ps.setInt(2, objeto.getProductSubCategoryID());

            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            Conexion.close(ps);
        }
        
    }
}
