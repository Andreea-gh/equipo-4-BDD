//Interface en la que se declaran los metodos insertar y eliminar. 
//Esta interface es hija de ICRUD, entonces los metodos que implementen esta interfaz
//seran aquellas que necesiten llevar a cabo las 4 operaciones del crud.


package accesodatos;

import domain.SalesOrderDetail;
import java.util.List;


public interface ICrud4<T> extends ICrud{
    
    int insertar(T objeto);
    
    void eliminar(int id);
    
}
