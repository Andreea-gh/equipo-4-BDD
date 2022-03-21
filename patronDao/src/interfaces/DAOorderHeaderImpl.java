package interfaces;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOorderHeaderImpl extends Conexion implements DAOorderHeader {

    @Override
    public void registrar() throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Sales.SalesOrderHeader VALUES(?)");
            st.setString(1, null);
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar() throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Sales.SalesOrderHeader set OrderDate where SalesOrderID=?");
            st.setDate(1, null);
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar() throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE from Sales.SalesOrderHeader where SalesOrderID=?");
            st.setString(1, null);
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List listar() throws Exception {
        List lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("select * from Sales.SalesOrderHeader");
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                //falta terminar de implementar
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }
    
}
