
package aplicacion;

import accesodatos.CustomerImp;
import java.util.Scanner;

import javax.crypto.ExemptionMechanismException;

import accesodatos.IAccesoDatosCustomer;
import accesodatos.IAccesoDatosOrderHeader;
import accesodatos.SalesOrderHeaderImp;
import domain.SalesCustomer;
import domain.SalesOrderHeader;


public class app {
    public static void main(String[] args) {
        menu();
    }
    
    public static void menu(){
        int opc = 0;
        Scanner entrada = new Scanner(System.in);
        do{
            System.out.println("Elige una opcion");
            System.out.println("1. Listar datos de empleado que vendio mas por region");
            System.out.println("2. Listar cuanto vendio un cierto empleado por region");
            System.out.println("3. Listar los datos del cliente con más ordenes solicitadas en la región North America");
            System.out.println("4. Salir");
            opc = entrada.nextInt();
            switch(opc){
                case 1:
                SalesOrderHeader orderHeader = new SalesOrderHeader();
                
                try{
                    IAccesoDatosOrderHeader dao = new SalesOrderHeaderImp();
                    System.out.print("SalesPersonID");
                    System.out.println("\tNumeroOrdenesAtendidas");
                    for(SalesOrderHeader oh : dao.listar()){
                        System.out.print(oh.getSalesPersionId());
                        System.out.println("\t" + "\t" + oh.getNumeroOrdenes());
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
                case 2:
                orderHeader = null;
                System.out.println("Ingrese el ID del empleado");
                int SalesPersonID = entrada.nextInt();
                
                try{
                    SalesOrderHeader orderHeader2 = new SalesOrderHeader();
                    orderHeader2.setSalesPersionId(SalesPersonID);
                    IAccesoDatosOrderHeader dao = new SalesOrderHeaderImp();
                    System.out.print("SalesPersonID");
                    System.out.println("\tNumeroOrdenesAtendidas");
                    for(SalesOrderHeader oh: dao.ordenesPorTerritorio(orderHeader2)){
                        System.out.print(oh.getSalesPersionId());
                        System.out.println("\t" + "\t" + oh.getNumeroOrdenes());
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
                case 3:
                try{
                    IAccesoDatosCustomer dao = new CustomerImp();
                    System.out.print("CustomerID");
                    System.out.println("\tNumberOfOrders");
                    for(SalesCustomer c: dao.listarNorthAmerica()){
                        System.out.print(c.getCustomerID());
                        System.out.println("\t"+ "\t" + c.getNumberOfOrders());
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
            }
        }while(opc!=4);
        
    }
}
