
package conexion;

import java.sql.*;

public class Conexion {

    Connection con = null;
//    String url="jdbc:sqlserver://192.168.0.4\\MSSQL3;database=AdventureWorks2019;integratedSecurity=true";
    String url="jdbc:sqlserver://192.168.0.3\\MSSQL3;database=AdventureWorks2019;user=sa;password=123456";
    
    public Conexion() 
    {
        try{
            con = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println("Error de Conexion" + e.getMessage());
        } 
    }

    public Connection Conectar() 
    {
        return con;
    }
    
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }
    
}
