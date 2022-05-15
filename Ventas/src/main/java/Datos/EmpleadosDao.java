/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Datos;

import Entidad.Empleado;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 *
 * @author 51934
 */
public interface EmpleadosDao {
    public List<Empleado> read()  throws GeneralSecurityException, IOException;
    public int insert(Empleado empleado)  throws GeneralSecurityException, IOException;
    public int update(Empleado empleado)  throws GeneralSecurityException, IOException;
    public int delete(Empleado empleado)  throws GeneralSecurityException, IOException;
}
