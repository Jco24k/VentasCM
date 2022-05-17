/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author 51934
 */
public class Ruta extends Padre{
    public static String HOJA = "RUTAS";
    public static String COLUMNA = "E";
    public static String COLUMNA_SIN_INDICE = "D";
    public static int ID_HOJA = 28299855;
    
    private String codigo;
    private String zona;
    private String distrito;
    private String cod_direccion;
    private String indice;

    public Ruta(String codigo, String zona, String distrito, String cod_direccion, int indice) {
        this.codigo = codigo;
        this.zona = zona;
        this.distrito = distrito;
        this.cod_direccion = cod_direccion;
        this.indice = String.valueOf(indice);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCod_direccion() {
        return cod_direccion;
    }

    public void setCod_direccion(String cod_direccion) {
        this.cod_direccion = cod_direccion;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    

    
    
}
