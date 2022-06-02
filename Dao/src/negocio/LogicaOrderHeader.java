//Esta clase se creo para programar todas las reglas de negocio de cada proceso del
//crud.

package negocio;

import accesodatos.SalesOrderHeaderImp;
import domain.SalesOrderHeader;
import java.util.*;


public class LogicaOrderHeader {
   
    private SalesOrderHeaderImp datos;
    
    public LogicaOrderHeader(){
        this.datos = new SalesOrderHeaderImp();
    }
    
    //Realiza las reglas de negocio necesarias para llevar a cabo una insercion
    // en su tabla.
    public void procesamientoInsercion(List<Integer> ordenes){
        
        if( ordenes.size() == 0)
            System.out.println("Aun no han realizado una nueva compra.\n");
        else{
            SalesOrderHeader salesOrderH = null;
            Scanner lect = new Scanner(System.in);
            String opcionS;
            int opcion, numeroOrden, idCliente, banderaOnline, billToAdress;
            double tax, freigh;

            do{
                opcion = 1;

                System.out.println("\nNumero de las ordenes: ");
                ordenes.forEach( elemento ->{
                    System.out.println("\t"+elemento+", ");
                });

                System.out.print("\nIngresa el No. de orden que deseas insertar: ");
                numeroOrden = Integer.parseInt(lect.nextLine());
                System.out.print("\nLa compra fue reliazada en:\n\t0. Prescencial\n\t1. Linea\n ");
                banderaOnline = Integer.parseInt(lect.nextLine());
                System.out.print("Ingresa el id del cliente que realizo la compra: ");
                idCliente = Integer.parseInt(lect.nextLine());
                billToAdress = (int)(Math.random()*(30000 - 405) + 405);
                tax = Math.random()*(500 - 3) + 3;
                freigh = Math.random()*(500 - 1) + 1;

                salesOrderH = new SalesOrderHeader(numeroOrden, banderaOnline, idCliente, billToAdress, tax, freigh);
                this.datos.insertar(this.datos.buscar(salesOrderH));

                for (int i = 0; i < ordenes.size(); i++) {
                    if( ordenes.get(i) == numeroOrden )
                        ordenes.remove(i);
                }

                if( ordenes.size() >= 1 )
                {
                    do{
                        System.out.println("\n¿Desea insertar otra orden?\n1. Si.\n2. No.");
                        opcionS = lect.nextLine();
                        if( opcionS.isEmpty() || (!opcionS.equals("1") && !opcionS.equals("2")) ){
                            System.out.println("\nOpcion invalida. Intente otra vez.");
                        } 
                    }while( opcionS.isEmpty() || (!opcionS.equals("1") && !opcionS.equals("2")) );
                    opcion = Integer.parseInt(opcionS);
                }
                else
                    break;

            }while(opcion == 1);
        }
                
    }
}

