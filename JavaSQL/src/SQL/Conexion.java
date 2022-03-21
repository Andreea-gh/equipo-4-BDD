

package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    public static Connection getConexion(){
        String conexionUrl = "jdbc:sqlserver://DESKTOP-ICRPQE4:1433;"
                + "database=master;"
                + "user=sa;"
                + "password=1234;"
                + "loginTimeOut=30"; //tiempo limite para hacer la conexion
                
        try{
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        } catch(SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
    }
}
