
package interfaces;

import java.util.List;
import sales.OrderDetails;


public interface DAOOrderDetails {
    public void registrar(OrderDetails orderD) throws Exception;
    public void modificar(OrderDetails orderD) throws Exception;
    public void eliminar(OrderDetails orderD) throws Exception;
    public List<OrderDetails> listar() throws Exception;
}
