

package dao;

import interfaces.DAOOrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sales.Customer;
import sales.OrderDetails;


public class DAOOrderDetailsImpl extends Conexion implements DAOOrderDetails{

    @Override
    public void registrar(OrderDetails orderD) throws Exception {
        try{
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Copia.copiaSalesOrderDetails (SalesOrderID,SalesOrderDetailID,CarrierTrackingNumber,OrderQty,ProductID,SpecialOfferID,UnitPrice,UnitPriceUnite,LineTotal,rowguid,ModifiedDate) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1,orderD.getSalesOrderID());
            st.setInt(2,orderD.getSalesOrderDetailID());
            st.setString(3,orderD.getCarrierTrackingNumber());
            st.setInt(4,orderD.getOrderQty());
            st.setInt(5,orderD.getProductID());
            st.setInt(6,orderD.getSpecialOfferID());
            st.setFloat(7,orderD.getUnitPrice());
            st.setFloat(8,orderD.getUnitPriceUnite());
            st.setFloat(9,orderD.getLineTotal());
            st.setString(10,orderD.getRowguid());
            st.setDate(11, orderD.getModifiedDate());
        }catch(Exception e){
            throw e;
        } finally{
            this.cerrar();
        }
    }

    @Override
    public void modificar(OrderDetails orderD) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Copia.copiaSalesOrderDetails (SalesOrderID,SalesOrderDetailID,CarrierTrackingNumber,OrderQty,ProductID,SpecialOfferID,UnitPrice,UnitPriceUnite,LineTotal,rowguid,ModifiedDate) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1, orderD.getSalesOrderID());
            st.setInt(2, orderD.getSalesOrderDetailID());
            st.setString(3, orderD.getCarrierTrackingNumber());
            st.setInt(4, orderD.getOrderQty());
            st.setInt(5, orderD.getProductID());
            st.setInt(6, orderD.getSpecialOfferID());
            st.setFloat(7, orderD.getUnitPrice());
            st.setFloat(8, orderD.getUnitPriceUnite());
            st.setFloat(9, orderD.getLineTotal());
            st.setString(10, orderD.getRowguid());
            st.setDate(11, orderD.getModifiedDate());
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(OrderDetails orderD) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE from Copia.copiaSalesOrderDetails where SalesOrderID = ? ");
            st.setInt(1, orderD.getSalesOrderID());

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<OrderDetails> listar() throws Exception {
        List<OrderDetails> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("select * from Copia.copiaSalesOrderDetails");

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetails orderD = new OrderDetails();
                orderD.setSalesOrderID(rs.getInt("SalesOrderID"));
                orderD.setSalesOrderDetailID(rs.getInt("SalesOrderDetailID"));
                orderD.setCarrierTrackingNumber(rs.getString("CarrierTrackingNumber"));
                orderD.setOrderQty(rs.getInt("OrderQty"));
                orderD.setProductID(rs.getInt("ProductID"));
                orderD.setSalesOrderID(rs.getInt("SpecialOfferID"));
                orderD.setUnitPrice(rs.getFloat("UnitePrice"));
                orderD.setUnitPriceUnite(rs.getFloat("UnitePriceUnite"));
                orderD.setLineTotal(rs.getFloat("LineTotal"));
                orderD.setRowguid(rs.getString("rowguid"));
                orderD.setModifiedDate(rs.getDate("ModifiedDate"));
                lista.add(orderD);
            }
            rs.close();
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

}
