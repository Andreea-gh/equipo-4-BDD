package accesodatos;

import domain.*; 
import java.util.List;

public interface IAccesoDatosCustomer extends ICrud<SalesCustomer>{
    
    List<SalesCustomer>listar();
    
    void actualizar(SalesCustomer objeto);
    
}
