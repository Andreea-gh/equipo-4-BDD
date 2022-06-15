//Esta interface es identica a su padre, con la diferencia que ya se especifica 
//los tipos de datos que recibira y retornan sus metodos.

package accesodatos;

import domain.*; 
import java.util.List;

public interface IAccesoDatosCustomer extends ICrud<SalesCustomer>{
    
    @Override
    List<SalesCustomer>listar();
    
    @Override
    void actualizar(SalesCustomer objeto);
    
    //Lista el cliente con mas ordenes solicitades en Norte America
    List<SalesCustomer>listarNorthAmerica();

    //Listar los clientes del territorio 1 y 4 que no tengan asociado un valor en personId
    List<SalesCustomer>listarClientesTerritorio();
}