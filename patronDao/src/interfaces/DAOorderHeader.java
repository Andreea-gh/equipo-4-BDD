
package interfaces;

import java.util.List;

public interface DAOorderHeader {
    public void registrar() throws Exception;
    public void modificar() throws Exception;
    public void eliminar() throws Exception;
    public List listar() throws Exception;
}
