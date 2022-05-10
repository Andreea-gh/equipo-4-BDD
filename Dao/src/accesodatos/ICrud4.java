
package accesodatos;


public interface ICrud4<T> extends ICrud{
    
    int insertar(T objeto);
    
    void eliminar(int id);
    
}
