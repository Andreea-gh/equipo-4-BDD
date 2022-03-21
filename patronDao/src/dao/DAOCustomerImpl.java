package dao;

import interfaces.DAOCustomer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sales.Customer;

public class DAOCustomerImpl extends Conexion implements DAOCustomer {

    @Override
    public void registrar(Customer custo) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Sales.Customer (AccountNumber,rowguid,ModifiedDate) VALUES(?,?,?)");
            st.setString(1, custo.getAccountNumeber());
            st.setInt(2, custo.getRowguid());
            st.setDate(3, custo.getDatetime());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

    }

    @Override
    public void modificar(Customer custo) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Sales.Customer (AccountNumber,rowguid,ModifiedDate) VALUES(?,?,?)");
            st.setString(1, custo.getAccountNumeber());
            st.setInt(2, custo.getRowguid());
            st.setDate(3, custo.getDatetime());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

    }

    @Override
    public void eliminar(Customer custo) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE from Sales.Customer where CustomerID = ? ");
            st.setInt(1, custo.getCustomerID());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Customer> listar() throws Exception {
        List<Customer> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("select * from Sales.Customer");
         
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Customer custo = new Customer();
                custo.setCustomerID(rs.getInt("CustomerID"));
                custo.setPersonID(rs.getInt("PersonID"));
                custo.setStoreID(rs.getInt("StoreID"));
                lista.add(custo);
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
