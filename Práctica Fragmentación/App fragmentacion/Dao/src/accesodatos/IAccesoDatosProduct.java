
package accesodatos;

import domain.ProductionProduct;
import java.util.List;

public interface IAccesoDatosProduct {
    //Listar los productos que no estén disponibles a la venta 
    List<ProductionProduct>listar();
}
