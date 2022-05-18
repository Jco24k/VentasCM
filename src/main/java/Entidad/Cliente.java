/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author 51934
 */
public class Cliente extends Padre {
    public static String HOJA = "CLIENTES";
    public static String COLUMNA = "G";
    public static String COLUMNA_SIN_INDICE = "F";
    public static int ID_HOJA = 1056839955;
    
    private String codigo;
    private String nombre;
    private String direccion;
    private String dni_ruc;
    private String telefono;
    private String geolocalizacion;
    private String indice;

    public Cliente() {
    }

    public Cliente(String codigo, int indice) {
        this.codigo = codigo;
        this.indice = String.valueOf(indice);
    }

    public Cliente(String nombre, String direccion, String dni_ruc, String telefono, String geolocalizacion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni_ruc = dni_ruc;
        this.telefono = telefono;
        this.geolocalizacion = geolocalizacion;
        this.codigo = Generar_Codigo(dni_ruc);
    }

    public Cliente(String codigo, String nombre, String direccion, String dni_ruc, String telefono, String geolocalizacion, int indice) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni_ruc = dni_ruc;
        this.telefono = telefono;
        this.geolocalizacion = geolocalizacion;
        
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni_ruc() {
        return dni_ruc;
    }

    public void setDni_ruc(String dni_ruc) {
        this.dni_ruc = dni_ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(String geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    public String getIndice() {
        return indice;
    }
    
    public String Generar_Codigo(String dni){

        return "C"+dni;
    }
    
    @Override
    public String toString(){
        return "CLIENTE ("  +codigo+" ,"+nombre+ " , "+dni_ruc+" , "+telefono+","+indice+")";
    }
    
    public String [] getRegistro(){
        return new String[]{codigo,nombre,direccion,dni_ruc,telefono,geolocalizacion,"=FILA()"};
    }

    public String [] getActualizar(){
        return new String[]{codigo,nombre,direccion,dni_ruc,telefono,geolocalizacion};
    }
    
    public String [] llenar_datos_tbl(){
        return new String[]{nombre,dni_ruc,direccion,telefono};
    }
    
    public Cliente llenar_datos_texto(){
        return this;
    }
    
}
