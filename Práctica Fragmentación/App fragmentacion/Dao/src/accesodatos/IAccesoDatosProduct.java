
package accesodatos;

import domain.ProductionProduct;
import java.util.List;

public interface IAccesoDatosProduct {
    //Listar los productos que no estén disponibles a la venta 
    List<ProductionProduct>listar();

    //Actualizar la subcategoría de los productos con productId del 1 al 4 a la subcategoría valida 
   //para el tipo de producto 
    void actualizar(ProductionProduct objeto);
}
