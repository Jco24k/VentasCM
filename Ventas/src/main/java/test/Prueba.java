/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Datos.EmpleadosDao;
import Datos.EmpleadosJDBC;
import Entidad.Empleado;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 *
 * @author 51934
 */
public class Prueba {
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        EmpleadosDao empleado = new EmpleadosJDBC();
        Empleado empleado1 = new Empleado("Marcfit",21);

        System.out.println(empleado.insert(empleado1));
        System.out.println("Resultados");
        for(Empleado emp : empleado.read()){
            System.out.println(emp.toString());
            System.out.println("\n");
            
        }

    }
}