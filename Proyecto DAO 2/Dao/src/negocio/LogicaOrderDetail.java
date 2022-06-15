//Esta clase se creo para programar todas las reglas de negocio de cada proceso del
//crud.

package negocio;

import accesodatos.*;
import domain.SalesOrderDetail;
import java.util.*;


public class LogicaOrderDetail {
   
    private SalesOrderDetailImp datos;
    
    public LogicaOrderDetail(){
        this.datos = new SalesOrderDetailImp();
    }
    
    //Realiza las reglas de negocio necesarias para llevar a cabo una insercion
    // en su tabla.
    public int procesamientoInsercion(int cola){
        
        Scanner lect = new Scanner(System.in);
        int orderId, id, cantidad, opcion;
        String opcionS;
        List<SalesOrderDetail> ordenes = new ArrayList();
        SalesOrderHeaderImp headerImp = new SalesOrderHeaderImp();
        
        orderId = headerImp.buscarId() + cola;
        System.out.println("\nNumero de orden = " + orderId);
        
        do{
            opcion = 1;
                    
            System.out.print("\nIngresa el producto id: ");
            id = Integer.parseInt(lect.nextLine());
            System.out.print("Ingresa la cantidad de productos que desea: ");
            cantidad = Integer.parseInt(lect.nextLine());

            SalesOrderDetail order = new SalesOrderDetail(orderId, id, cantidad);
            int respues = this.datos.validar(order);

            if( respues == 0 ){
                System.out.println("No existe el producto que solicita.\n");
            }
            else if( respues == -1 ){
                System.out.println("No hay la cantidad de stock del producto que solicita.\n");
            }
            else{
                System.out.println("¡Producto Agregado a su compra correctamente!\n");
                ordenes.add(order);
            }
            
            do{
                System.out.println("¿Desea comprar otro producto?\n1. Si.\n2. No.");
                opcionS = lect.nextLine();
                if( opcionS.isEmpty() || (!opcionS.equals("1") && !opcionS.equals("2")) ){
                    System.out.println("\nOpcion invalida. Intente otra vez.");
                } 
            }while( opcionS.isEmpty() || (!opcionS.equals("1") && !opcionS.equals("2")) );
            
            opcion = Integer.parseInt(opcionS);
        }while(opcion == 1);
        
      
        ordenes.forEach( elemento ->{
//            this.datos.actualizarProducto(elemento.getProductID(), elemento.getOrderQty());
            this.datos.insertar(this.datos.buscar(elemento));
        });
        
        return orderId;
    }
}
