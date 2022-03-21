
package app;

import dao.DAOCustomerImpl;
import interfaces.DAOCustomer;
import sales.Customer;


public class main {
    public static void main(String[] args) {
       Customer custo = new Customer();
       
       
       
       try{
           DAOCustomer dao = new DAOCustomerImpl();
           
           for(Customer c : dao.listar()){
               System.out.println(c.getCustomerID());
               System.out.println(c.getPersonID());
               System.out.println(c.getStoreID());
           }
           
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
}
