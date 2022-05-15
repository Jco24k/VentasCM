/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Entidad.Padre;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 *
 * @author 51934
 */
public interface DatosDao {
    public List<Padre> read(String tipo)  throws GeneralSecurityException, IOException;
    public int insert(Padre valor,String tipo)  throws GeneralSecurityException, IOException;
    public int update(Padre valor,String tipo)  throws GeneralSecurityException, IOException;
    public int delete(Padre valor,String tipo)  throws GeneralSecurityException, IOException;
}
