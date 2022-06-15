
package accesodatos;

import domain.SalesSpecialOffer;
import java.util.List;

public interface IAccesoDatosSpecialOffer {
   // Listar las ofertas que tienen los productos de la categoría “Bikes”
    List<SalesSpecialOffer>listar();
}
