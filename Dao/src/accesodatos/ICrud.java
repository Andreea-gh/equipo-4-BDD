
package accesodatos;

import java.util.List;


public interface ICrud<T> {
    
    List<T>listar();
    
    void actualizar(T objeto);
    
}
