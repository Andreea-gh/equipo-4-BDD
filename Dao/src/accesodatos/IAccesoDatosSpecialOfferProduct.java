package accesodatos;

import domain.*; 
import java.util.List;

public interface IAccesoDatosSpecialOfferProduct extends ICrud<SalesSpecialOfferProduct>{
    
    List<SalesSpecialOfferProduct>listar();
    
    void actualizar(SalesSpecialOfferProduct objeto);
    
}