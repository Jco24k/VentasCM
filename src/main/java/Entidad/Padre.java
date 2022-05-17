/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author 51934
 */
public class Padre {
    public String Generar_Codigo(String valor){
        return "P"+valor;
    }
    
    public String [] llenar_datos_tbl(){
        return new String[]{};
    }
    public Padre llenar_datos_texto(){
        return this;
    }
}
