
package aplicacion;

import accesodatos.CustomerImp;
import accesodatos.ICrud;
import accesodatos.SalesOrderDetailImp;
import java.util.List;
import negocio.LogicaOrderDetail;

/*Clase para unicamente hacer pruebas*/


public class Test {
    public static void main(String[] args) {
        
//        ICrud datos; //Pa todos, pa todo.
//        

        

        //ORDERDETAIL ------------------------------------------------
        
        //LISTAR
//        datos = new OrderDetailImp();
//        
//        imprimir(datos);

        LogicaOrderDetail ordenes = new LogicaOrderDetail();
        
        ordenes.procesamientoInsercion();
        
    }
    
    //Para polimorfisar pero hasta despues.
    public static void insertar(ICrud datos){
        datos.listar();
    }
    
    public static void actualizar(ICrud datos){
        datos.listar();
    }
    
    public static void eliminar(ICrud datos){
        datos.listar();
    }  
}
