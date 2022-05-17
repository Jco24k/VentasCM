/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author 51934
 */
public class Empleado  extends Padre{
    public static String HOJA = "EMPLEADOS";
    public static String COLUMNA = "J";
    public static String COLUMNA_SIN_INDICE = "I";
    public static int ID_HOJA = 0;
    
    private String codigo;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String cod_ruta;
    private String usuario;
    private String password;
    private String indice;

    public Empleado() {
    }

    public Empleado(String codigo,int indice) {
        this.codigo = codigo;
        this.indice = String.valueOf(indice);
    }

    public Empleado(String nombre, String apellido, String dni, String telefono, String direccion, String cod_ruta, String usuario, String password) {
        this.codigo = Generar_Codigo(dni);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cod_ruta = cod_ruta;
        this.usuario = usuario;
        this.password = password;
    }

    public Empleado(String codigo, String nombre, String apellido, String dni, String telefono, String direccion, String cod_ruta, String usuario, String password,int indice) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cod_ruta = cod_ruta;
        this.usuario = usuario;
        this.password = password;
        this.indice = String.valueOf(indice);
    }
    
    
    

    public String getCodigo() {
        return codigo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCod_ruta() {
        return cod_ruta;
    }

    public void setCod_ruta(String cod_ruta) {
        this.cod_ruta = cod_ruta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIndice() {
        return indice;
    }



    @Override
    public String toString(){
        return "Empleado ("  +codigo+" ,"+nombre+ " , "+apellido+" , "+dni+","+indice+")";
    }
    
    @Override
    public String Generar_Codigo(String dni){

        return "E"+dni;
    }

    public String [] getRegistro(){
        System.out.println(codigo);
        return new String[]{codigo,nombre,apellido,dni,telefono,direccion,cod_ruta,usuario,password,"=FILA()"};
    }

    public String [] getActualizar(){
        System.out.println(codigo);
        return new String[]{codigo,nombre,apellido,dni,telefono,direccion,cod_ruta,usuario,password};
    }
    
    public String [] llenar_datos_tbl(){
        return new String[]{nombre,apellido,dni,telefono};
    }
    
    public String[] llenar_datos_texto(){
        return new String[]{codigo,nombre,apellido,dni,telefono,direccion,cod_ruta,usuario,password,indice};
    }


    
    
}
