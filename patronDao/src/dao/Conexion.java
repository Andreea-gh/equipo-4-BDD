package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
 public String conexionURL;
 public Connection conexion;
 public void conectar() throws Exception{
     try{
         conexionURL = "jdbc:sqlserver://DESKTOP-ICRPQE4:1433;"
                + "database=AdventureWorks2019;"
                + "user=sa;"
                + "password=1234;"
                + "loginTimeOut=30";
         conexion = DriverManager.getConnection(conexionURL);
     }catch (Exception e){
         throw e;
     }
 }
 
 public void cerrar() throws SQLException{
     if(conexion != null){
         if(!conexion.isClosed()){
             conexion.close();
         }
     }
 }
    /*public static Connection getConexion() {
        conexionUrl = "jdbc:sqlserver://DESKTOP-ICRPQE4:1433;"
                + "database=master;"
                + "user=sa;"
                + "password=1234;"
                + "loginTimeOut=30"; //tiempo limite para hacer la conexion

        try {
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public void cerrar(){
        if(con != null){
            
        }
    }*/
}
