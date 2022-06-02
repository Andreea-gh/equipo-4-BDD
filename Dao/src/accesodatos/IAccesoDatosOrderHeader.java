//Esta interface es identica a su padre, con la diferencia que ya se especifica 
//los tipos de datos que recibira y retornan sus metodos.

package accesodatos;

import domain.*; 
import java.util.List;

public interface IAccesoDatosOrderHeader extends ICrud4<SalesOrderHeader>{
    
    List<SalesOrderHeader>listar();
    
    void actualizar(SalesOrderHeader objeto);
    
}