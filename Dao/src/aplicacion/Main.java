
package aplicacion;

import accesodatos.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        Scanner lect = new Scanner(System.in);
        int opcion = -1;
        ICrud crud = null;
        
        do{
            System.out.println("\n1. Sales Order Header.");
            System.out.println("2. Sales Order Detail.");
            System.out.println("3. Customer.");
            System.out.println("4. Special Offer Product.");
            System.out.println("0. Salir.");

            System.out.print("\nSeleccione una opcion: ");
            String opcionS = lect.nextLine();
            
            if(opcionS.isEmpty())
            {
                System.out.println("Error. Opcion Invalida. Intente de nuevo.");
                continue;
            }
            opcion=Integer.parseInt(opcionS);
            
            switch(opcion)
            {
                case 1:
                    crud = new SalesOrderHeaderImp();
                    menu(crud);
                    break;
                case 2:
                    crud = new SalesOrderDetailImp();
                    menu(crud);
                    break;
                case 3:
                    crud = new CustomerImp();
                    menu2(crud);
                    break;
                case 4:
                    crud = new SpecialOfferProductImp();
                    menu2(crud);
                    break;
                case 0:
                    System.out.println("Bye.");
                    break;
                default:
                    System.out.println("Opcion Invalida. Intentelo otra vez.");
            }
            
        }while(opcion!=0);
    }
    
    
    public static void menu( ICrud crud ){
        
        Scanner lect = new Scanner(System.in);
        int opcion = -1;
        
        do{
            System.out.println("\n1. Insertar.");
            System.out.println("2. Recuperar.");
            System.out.println("3. Actualizar.");
            System.out.println("4. Borrar.");
            System.out.println("0. Regresar.");

            System.out.print("\nSeleccione una opcion: ");
            String opcionS = lect.nextLine();
            
            if(opcionS.isEmpty())
            {
                System.out.println("Error. Opcion Invalida. Intente de nuevo.");
                continue;
            }
            opcion=Integer.parseInt(opcionS);
            
            switch(opcion)
            {
                case 1:
                    insertar(crud);
                    break;
                case 2:
                    recuperar(crud);
                    break;
                case 3:
                    actualizar(crud);
                    break;
                case 4:
                    eliminar(crud);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion Invalida. Intentelo otra vez.");
            }
        }while(opcion!=0);
    }
    
    
    public static void menu2( ICrud crud ){
        
        Scanner lect = new Scanner(System.in);
        int opcion = -1;
        
        do{
            System.out.println("\n1. Recuperar.");
            System.out.println("2. Actualizar.");
            System.out.println("0. Regresar.");

            System.out.print("\nSeleccione una opcion: ");
            String opcionS = lect.nextLine();
            
            if(opcionS.isEmpty())
            {
                System.out.println("Error. Opcion Invalida. Intente de nuevo.");
                continue;
            }
            opcion=Integer.parseInt(opcionS);
            
            switch(opcion)
            {
                case 1:
                    recuperar(crud);
                    break;
                case 2:
                    actualizar(crud);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion Invalida. Intentelo otra vez.");
            }
        }while(opcion!=0);
    }
    
    
    
    //Para polimorfisar pero hasta despues.
    public static void insertar(ICrud datos){
        datos.listar();
    }
    
    public static void recuperar(ICrud datos){
        List lista=null; //pa todos.
        
        if(datos instanceof  SalesOrderDetailImp){
            lista=((SalesOrderDetailImp)datos).listar();
	} 
        else if(datos instanceof  SalesOrderHeaderImp){
            lista=((SalesOrderHeaderImp)datos).listar();
	} 
        else if(datos instanceof  CustomerImp){
            lista=((CustomerImp)datos).listar();
	} 
	else if(datos instanceof  SpecialOfferProductImp){
            lista=((SpecialOfferProductImp)datos).listar();
	} 
        
        lista.forEach(elemento->{
            System.out.println(" "+elemento);
        });
    }
    
    public static void actualizar(ICrud datos){
        datos.listar();
    }
    
    public static void eliminar(ICrud datos){
        datos.listar();
    }  
}
