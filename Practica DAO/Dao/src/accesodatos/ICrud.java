// Esta interface es el padre de todos.
//Interface en la que se declaran los metodos insertar y actualizar. 
//Las unicas clases que evidentmente implementaran estos metodos seran aquellas que
//unicmante se nos solicitaron insertar y actualizar datos. 

package accesodatos;

import java.util.List;


public interface ICrud<T> {
    
    List<T>listar();
    
    void actualizar(T objeto);
    
}
