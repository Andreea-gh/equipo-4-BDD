//Esta interface es identica a su padre, con la diferencia que ya se especifica 
//los tipos de datos que recibira y retornan sus metodos.

package accesodatos;

import domain.*; 
import java.util.List;

public interface IAccesoDatosOrderHeader extends ICrud4<SalesOrderHeader>{
    
    @Override
    List<SalesOrderHeader>listar();
    
    // listar al empleado que atendio mas ordenes por territorio
    void actualizar(SalesOrderHeader objeto);
    
    // listar cuantas ordenes atendio un cierto empleado territorio espcificando el id
    List<SalesOrderHeader>ordenesPorTerritorio(SalesOrderHeader objeto);

    //Listar los clientes del territorio 1 que tengan ordenes en otro territorio
    List<SalesOrderHeader>clientesTerritorio1();
    
}