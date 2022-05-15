/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Datos.DatosJDBC;
import Entidad.Cliente;
import Entidad.Empleado;
import Entidad.Padre;
import Entidad.Producto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import Interface.DatosDao;

/**
 *
 * @author 51934
 */
public class Prueba {
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        DatosDao empleado = new DatosJDBC();
        Padre empleado3 = new Cliente("asdasdasd",2);
        
        Padre empleado1 = new Cliente("pan","CON MANTEQUILLA","Asdasdasd","asdasasd","asdasd");
        
        
        System.out.println(empleado.insert(empleado1,"CLIENTE"));
        
        System.out.println("DATOS INSERTADOS");
        Padre empleado2 = new Cliente("asdasdas","CON MANTEQUILLA","Asdasdasd","asdasasd","asdasd","asdasdasd",2);
        
        System.out.println(empleado.update(empleado2,"CLIENTE"));
        System.out.println("DATOS ACTUALIZADOS");
        
        System.out.println(empleado.delete(empleado3,"CLIENTE"));
        System.out.println("DATOS ELIMINADOS");
        
        System.out.println("Resultados");
        for(Padre emp : empleado.read("CLIENTE")){
            Cliente pro = (Cliente) emp;
            
            System.out.println(pro.toString());
            System.out.println("\n");
            
        }

    }
}
