

package interfaces;

import java.util.List;
import sales.Customer;


public interface DAOCustomer {
    public void registrar(Customer custo) throws Exception;
    public void modificar(Customer custo) throws Exception;
    public void eliminar(Customer custo) throws Exception;
    public List<Customer> listar() throws Exception;
    
}
