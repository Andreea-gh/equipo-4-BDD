package accesodatos;

import domain.*; 
import java.util.List;

public interface IAccesoDatosOrderHeader extends ICrud4<SalesOrderHeader>{
    
    List<SalesOrderHeader>listar();
    
    void actualizar(SalesOrderHeader objeto);
    
}