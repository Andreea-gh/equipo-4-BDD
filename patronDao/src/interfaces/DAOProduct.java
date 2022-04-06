

package interfaces;

import java.util.List;
import production.Product;



public interface DAOProduct {
    public void registrar(Product prod) throws Exception;
    public void modificar(Product prod) throws Exception;
    public void eliminar(Product prod) throws Exception;
    public List<Product> listar() throws Exception;
}
